package com.cooksysteam1.socialmedia.controller;

import com.cooksysteam1.socialmedia.entity.model.request.CredentialsDto;
import com.cooksysteam1.socialmedia.entity.model.request.UserRequestDto;
import com.cooksysteam1.socialmedia.entity.model.response.TweetResponseDto;
import com.cooksysteam1.socialmedia.entity.model.response.UserResponseDto;
import com.cooksysteam1.socialmedia.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponseDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PatchMapping("/@{username}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserResponseDto updateAUser(@PathVariable String username, @RequestBody UserRequestDto userRequestDto) {
        return userService.updateAUser(username, userRequestDto);
    }

    @DeleteMapping("/@{username}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDto deleteAUserByUsername( @PathVariable String username, @RequestBody CredentialsDto credentialsDto) {
        return userService.deleteAUserByUsername(username, credentialsDto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDto createAUser(@RequestBody UserRequestDto userRequestDto) {
        return userService.createAUser(userRequestDto);
    }
    
    @GetMapping("/@{username}")
	public UserResponseDto getUserByUsername(@PathVariable String username) {
		return userService.getUserDtoByUsername(username);
	}
    
    @GetMapping("/@{username}/followers")
    public List<UserResponseDto> getFollowers (@PathVariable String username) {
    	return userService.getFollowers(username);
    }
    
    @GetMapping("/@{username}/following")
    public List<UserResponseDto> getFollowing (@PathVariable String username) {
    	return userService.getFollowing(username);
    }

    @GetMapping("/@{username}/feed")
    @ResponseStatus(HttpStatus.FOUND)
    public List<TweetResponseDto> getTweetFeed(@PathVariable String username) {
        return userService.getTweetFeed(username);
    }
    
    @PostMapping("/@{username}/follow")
    public void followUser(@PathVariable String username, @RequestBody CredentialsDto credentialsDto) {
    	userService.followUser(username, credentialsDto);
    }
    
    @PostMapping("/@{username}/unfollow")
    public void unfollowUser (@PathVariable String username, @RequestBody CredentialsDto credentialsDto) {
    	userService.unfollowUser(username, credentialsDto);
    }

    @GetMapping("/@{username}/mentions")
    @ResponseStatus(HttpStatus.FOUND)
    public List<TweetResponseDto> getUserMentions(@PathVariable String username) {
        return userService.getUserMentions(username);
    }

    @GetMapping("/@{username}/tweets")
    @ResponseStatus(HttpStatus.FOUND)
    public List<TweetResponseDto> getUserTweets(@PathVariable String username) {
        return userService.getUserTweets(username);
    }
}
