package com.cooksysteam1.socialmedia.controller;

import java.util.List;

import com.cooksysteam1.socialmedia.entity.model.request.TweetRequestDto;
import com.cooksysteam1.socialmedia.entity.model.response.ContextDto;
import com.cooksysteam1.socialmedia.entity.model.response.HashtagResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.cooksysteam1.socialmedia.entity.model.request.CredentialsDto;
import com.cooksysteam1.socialmedia.entity.model.response.TweetResponseDto;
import com.cooksysteam1.socialmedia.entity.model.response.UserResponseDto;
import com.cooksysteam1.socialmedia.service.TweetService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tweets")
public class TweetController {
	
	private final TweetService tweetService;
	
	@GetMapping
	public List<TweetResponseDto> getAllTweets() {
		return tweetService.getAllTweets();
	}
	
	@GetMapping("/{id}")
	public TweetResponseDto getTweetById(@PathVariable Long id) {
		return tweetService.getTweetDtoById(id);
	}
	
	@DeleteMapping("/{id}")
	public TweetResponseDto deleteTweetById(@PathVariable Long id, @RequestBody CredentialsDto credentialsDto) {
		return tweetService.deleteTweetById(id, credentialsDto);
	}
	
	@PostMapping("/{id}/like")
	public void likeTweetById(@PathVariable Long id, @RequestBody CredentialsDto credentialsDto) {
		tweetService.likeTweetById(id, credentialsDto);
	}
	
	@GetMapping("/{id}/likes")
	public List<UserResponseDto> getLikesOfTweet (@PathVariable Long id) {
		return tweetService.getLikesOfTweet(id);
	}

	@GetMapping("/{id}/context")
	@ResponseStatus(HttpStatus.FOUND)
	public ContextDto getContextById(@PathVariable Long id) {
		return tweetService.getContextById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public TweetResponseDto createTweet(@RequestBody TweetRequestDto tweetRequestDto) {
		return tweetService.createTweet(tweetRequestDto);
	}

	@PostMapping("/{id}/reply")
	@ResponseStatus(HttpStatus.CREATED)
	public TweetResponseDto createReplyTweet(@PathVariable Long id, @RequestBody TweetRequestDto tweetRequestDto) {
		return tweetService.createReplyTweet(id, tweetRequestDto);
	}

	@PostMapping("/{id}/repost")
	@ResponseStatus(HttpStatus.CREATED)
	public TweetResponseDto createRepostTweet(@PathVariable Long id, @RequestBody TweetRequestDto tweetRequestDto) {
		return tweetService.createRepostTweet(id, tweetRequestDto);
	}

	@GetMapping("/{id}/tags")
	@ResponseStatus(HttpStatus.FOUND)
	public List<HashtagResponseDto> getTweetHashtags(@PathVariable Long id) {
		return tweetService.getTweetHashtags(id);
	}

	@GetMapping("/{id}/replies")
	@ResponseStatus(HttpStatus.FOUND)
	public List<TweetResponseDto> getTweetReplies(@PathVariable Long id) {
		return tweetService.getTweetReplies(id);
	}

	@GetMapping("/{id}/reposts")
	@ResponseStatus(HttpStatus.FOUND)
	public List<TweetResponseDto> getTweetReposts(@PathVariable Long id) {
		return tweetService.getTweetReposts(id);
	}

	@GetMapping("{id}/mentions")
	@ResponseStatus(HttpStatus.FOUND)
	public List<UserResponseDto> getTweetMentions(@PathVariable Long id) {
		return tweetService.getTweetMentions(id);
	}
}
