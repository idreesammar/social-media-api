package com.cooksysteam1.socialmedia.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.cooksysteam1.socialmedia.controller.exception.BadRequestException;
import com.cooksysteam1.socialmedia.controller.exception.NotAuthorizedException;
import com.cooksysteam1.socialmedia.controller.exception.NotFoundException;
import com.cooksysteam1.socialmedia.entity.Tweet;
import com.cooksysteam1.socialmedia.entity.User;
import com.cooksysteam1.socialmedia.entity.model.request.CredentialsDto;
import com.cooksysteam1.socialmedia.entity.model.request.ProfileDto;
import com.cooksysteam1.socialmedia.entity.model.request.UserRequestDto;
import com.cooksysteam1.socialmedia.entity.model.response.TweetResponseDto;
import com.cooksysteam1.socialmedia.entity.model.response.UserResponseDto;
import com.cooksysteam1.socialmedia.entity.resource.Credentials;
import com.cooksysteam1.socialmedia.mapper.CredentialsMapper;
import com.cooksysteam1.socialmedia.mapper.TweetMapper;
import com.cooksysteam1.socialmedia.mapper.UserMapper;
import com.cooksysteam1.socialmedia.repository.TweetRepository;
import com.cooksysteam1.socialmedia.repository.UserRepository;
import com.cooksysteam1.socialmedia.service.UserService;
import lombok.RequiredArgsConstructor;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	private final UserMapper userMapper;

	private final TweetRepository tweetRepository;

	private final TweetMapper tweetMapper;

	private final CredentialsMapper credentialsMapper;

	@Override
	public List<UserResponseDto> getAllUsers() {
		List<User> users = userRepository.findAllUsersByDeletedFalse();
		if (users.isEmpty())
			throw new NotFoundException("Unknown error. Expected to find all users but was false.");
		return userMapper.entitiesToResponses(users);
	}

	@Override
	public UserResponseDto updateAUser(String username, UserRequestDto userRequestDto) {
		validateUserRequestDto(userRequestDto);
		validateUsername(username);
		Optional<User> userOptional = userRepository
			.findUserByCredentials(credentialsMapper.requestToEntity(userRequestDto.getCredentials()));
		User user = validateOptionalAndReturnsUser(userOptional);
		user.getCredentials().setUsername(username);
		return userMapper.entityToResponse(userRepository.saveAndFlush(user));
	}

	@Override
	public UserResponseDto deleteAUserByUsername(String username, CredentialsDto credentialsDto) {
		validateCredentialsRequestDto(credentialsDto);
		validateUsername(username);
		Optional<User> userOptional = userRepository.findUserByCredentials_UsernameAndDeletedFalse(username);
		User user = validateOptionalAndReturnsUser(userOptional);
		validateUserCredentialsAgainstCredentialsDto(user.getCredentials(), credentialsDto);
		user.setDeleted(true);
		return userMapper.entityToResponse(userRepository.saveAndFlush(user));
	}

	@Override
	public UserResponseDto createAUser(UserRequestDto userRequestDto) {
		validateUserRequestDto(userRequestDto);
		Optional<User> userOptional = userRepository.findUserByCredentials
			(credentialsMapper.requestToEntity(userRequestDto.getCredentials()));
		User user = validateOptionalAndReturnsUser(userOptional);
		if (user.getId() != null) {
			validateUserAccountReactivation(user.getCredentials());
			user.setDeleted(false);
		}
		return userMapper.entityToResponse(userRepository.saveAndFlush(user));
	}

	@Override
	public UserResponseDto getUserDtoByUsername(String username) {
		User user = getUserByUsername(username);
		return userMapper.entityToResponse(user);
	}

	
	@Override
	public List<TweetResponseDto> getTweetFeed(String username) {
		List<Tweet> tweets = tweetRepository.findTweetsByAuthor_DeletedFalseAndAuthor_Credentials_Username(username);
		if (tweets.isEmpty()) throw new NotFoundException("Invalid username. Expected tweets to be present but was false.");
		tweets.stream().filter(Objects::isNull).forEach(tweet -> tweets.addAll(List.of(tweet.getInReplyTo(), tweet.getRepostOf())));
		tweets.sort(Collections.reverseOrder(Comparator.comparing(Tweet::getPosted)));
		return tweetMapper.entitiesToResponses(tweets);
 }
 
	@Override
	public List<UserResponseDto> getFollowing(String username) {
		User user = getUserByUsername(username);
		return userMapper.entitiesToResponses
			(user.getFollowing().stream().filter(followee -> !followee.isDeleted()).collect(Collectors.toList()));
	}
  
  	@Override
	public List<UserResponseDto> getFollowers(String username) {
		User user = getUserByUsername(username);
		return userMapper.entitiesToResponses
				(user.getFollowers().stream().filter(follower -> !follower.isDeleted()).collect(Collectors.toList()));
	}

	@Override
	public void followUser(String username, CredentialsDto credentialsDto) {
		User userToFollow = getUserByUsername(username);
		validateCredentialsRequestDto(credentialsDto);
		User follower = getUserByUsername(credentialsDto.getUsername());
		if (follower.getFollowing().contains(userToFollow))
			throw new BadRequestException(credentialsDto.getUsername() + " is already following " + username);
		follower.getFollowing().add(userToFollow);
		userToFollow.getFollowers().add(follower);
		userRepository.saveAndFlush(follower);
		userRepository.saveAndFlush(userToFollow);
	}

	@Override
	public void unfollowUser(String username, CredentialsDto credentialsDto) {
		User userToUnfollow = getUserByUsername(username);
		validateCredentialsRequestDto(credentialsDto);
		User follower = getUserByUsername(credentialsDto.getUsername());
		if (!follower.getFollowing().contains(userToUnfollow))
			throw new BadRequestException(credentialsDto.getUsername() + " is not currently following " + username);
		follower.getFollowing().remove(userToUnfollow);
		userToUnfollow.getFollowers().remove(follower);
		userRepository.saveAndFlush(follower);
		userRepository.saveAndFlush(userToUnfollow);
	}

	@Override
	public List<TweetResponseDto> getUserMentions(String username) {
		User user = getUserByUsername(username);
		List<Tweet> tweets = user.getTweetMentions();
		return tweetMapper.entitiesToResponses(tweets);
	}

	@Override
	public List<TweetResponseDto> getUserTweets(String username) {
		User user = getUserByUsername(username);
		List<Tweet> tweets = user.getTweets();
		return tweetMapper.entitiesToResponses(tweets);
	}

	private User getUserByUsername(String username) {
		validateUsername(username);
		Optional<User> optionalUser = userRepository.findUserByCredentials_UsernameAndDeletedFalse(username);
		return validateOptionalAndReturnsUser(optionalUser);
	}

	private void validateUserCredentialsAgainstCredentialsDto(Credentials userCredentials, CredentialsDto credentialsDto) {
		if (!userCredentials.equals(credentialsMapper.requestToEntity(credentialsDto)))
			throw new NotAuthorizedException("Invalid credentials. Expected credentials to match but was false.");
	}

	private User validateOptionalAndReturnsUser(Optional<User> userOptional) {
		return userOptional.orElseThrow
				(() -> new NotFoundException("Invalid username. Expected to find a user by username but was false."));
	}

	private void validateUserAccountReactivation(Credentials credentials) {
		if (userRepository.existsUserByCredentialsAndDeletedFalse(credentials))
			throw new NotAuthorizedException("Invalid credentials. Expected credentials to unique but was false.");
	}

	private void validateUserRequestDto(UserRequestDto userRequestDto) {
		if (userRequestDto == null)
			throw new BadRequestException("Invalid user. Expected user not to be null but was false.");
		validateCredentialsRequestDto(userRequestDto.getCredentials());
		validateProfileRequestDto(userRequestDto.getProfile());
	}

	private void validateProfileRequestDto(ProfileDto profileDto) {
		if (profileDto == null || profileDto.getEmail() == null || profileDto.getEmail().isBlank())
			throw new BadRequestException("Invalid profile. Expected email field not to be null but was false.");
	}

	private void validateUsername(String username) {
		if (username == null || username.isBlank())
			throw new NotAuthorizedException
				("Invalid username. Expected username to not be null or empty but was false.");
	}

	private void validateCredentialsRequestDto(CredentialsDto credentialsRequestDto) {
		if (credentialsRequestDto == null || credentialsRequestDto.getUsername() == null
			|| credentialsRequestDto.getPassword() == null || credentialsRequestDto.getUsername().isBlank()
			|| credentialsRequestDto.getPassword().isBlank()) {
			throw new BadRequestException("Invalid credentials. Expected fields not to be null but was false.");
		}
	}
}
