package com.cooksysteam1.socialmedia.service;

import com.cooksysteam1.socialmedia.entity.model.request.CredentialsDto;
import com.cooksysteam1.socialmedia.entity.model.request.UserRequestDto;
import com.cooksysteam1.socialmedia.entity.model.response.TweetResponseDto;
import com.cooksysteam1.socialmedia.entity.model.response.UserResponseDto;
import com.cooksysteam1.socialmedia.entity.resource.Credentials;

import java.util.List;

public interface UserService {

    List<UserResponseDto> getAllUsers();

    UserResponseDto createAUser(UserRequestDto userRequestDto);

    UserResponseDto updateAUser(String username, UserRequestDto userRequestDto);

    UserResponseDto deleteAUserByUsername(String username, CredentialsDto credentialsDto);
  
    UserResponseDto getUserDtoByUsername(String username);

	List<UserResponseDto> getFollowers(String username);

	List<UserResponseDto> getFollowing(String username);

   List<TweetResponseDto> getTweetFeed(String username);
    
	void followUser(String username, CredentialsDto credentialsDto);

	void unfollowUser(String username, CredentialsDto credentialsDto);

    List<TweetResponseDto> getUserMentions(String username);

    List<TweetResponseDto> getUserTweets(String username);
}
