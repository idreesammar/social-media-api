package com.cooksysteam1.socialmedia.service;

import java.util.List;
import com.cooksysteam1.socialmedia.entity.model.request.CredentialsDto;
import com.cooksysteam1.socialmedia.entity.model.request.TweetRequestDto;
import com.cooksysteam1.socialmedia.entity.model.response.ContextDto;
import com.cooksysteam1.socialmedia.entity.model.response.HashtagResponseDto;
import com.cooksysteam1.socialmedia.entity.model.response.TweetResponseDto;
import com.cooksysteam1.socialmedia.entity.model.response.UserResponseDto;

public interface TweetService {

	List<TweetResponseDto> getAllTweets();

	TweetResponseDto getTweetDtoById(Long id);

	TweetResponseDto deleteTweetById(Long id, CredentialsDto credentialsDto);

	void likeTweetById(Long id, CredentialsDto credentialsDto);

	List<UserResponseDto> getLikesOfTweet(Long id);

    ContextDto getContextById(Long id);

	TweetResponseDto createTweet(TweetRequestDto tweetRequestDto);

    TweetResponseDto createReplyTweet(Long id, TweetRequestDto tweetRequestDto);

	TweetResponseDto createRepostTweet(Long id, TweetRequestDto tweetRequestDto);

	List<HashtagResponseDto> getTweetHashtags(Long id);

	List<TweetResponseDto> getTweetReplies(Long id);

	List<TweetResponseDto> getTweetReposts(Long id);

	List<UserResponseDto> getTweetMentions(Long id);
}
