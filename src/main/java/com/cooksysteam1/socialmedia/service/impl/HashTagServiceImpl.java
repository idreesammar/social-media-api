package com.cooksysteam1.socialmedia.service.impl;

import com.cooksysteam1.socialmedia.controller.exception.NotAuthorizedException;
import com.cooksysteam1.socialmedia.controller.exception.NotFoundException;
import com.cooksysteam1.socialmedia.entity.Hashtag;
import com.cooksysteam1.socialmedia.entity.Tweet;
import com.cooksysteam1.socialmedia.entity.model.response.HashtagResponseDto;
import com.cooksysteam1.socialmedia.entity.model.response.TweetResponseDto;
import com.cooksysteam1.socialmedia.mapper.HashtagMapper;
import com.cooksysteam1.socialmedia.mapper.TweetMapper;
import com.cooksysteam1.socialmedia.repository.HashtagRepository;
import com.cooksysteam1.socialmedia.service.HashtagService;
import lombok.RequiredArgsConstructor;
import java.util.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HashTagServiceImpl implements HashtagService {

	private final HashtagMapper hashtagMapper;

	private final HashtagRepository hashtagRepository;

	private final TweetMapper tweetMapper;

	@Override
	public List<HashtagResponseDto> getAllHashtags() {
		return hashtagMapper.entitiesToResponses(hashtagRepository.findAll());
	}

	@Override
	public List<TweetResponseDto> getHashtagsByLabel(String label) {
		List<Tweet>tweets = getValidHashtagByLabel(label).getTweets();
		tweets.sort(Comparator.comparing(Tweet::getPosted).reversed());
		return tweetMapper.entitiesToResponses(tweets);
	}

	private void validateLabel(String label) {
		if (label == null || label.isBlank())
			throw new NotAuthorizedException
				("Invalid username. Expected username to not be null or empty but was false.");
	}

	public Hashtag getValidHashtagByLabel(String label) {
		validateLabel(label);
		Optional<Hashtag> hashtagOptional = hashtagRepository.findHashtagByLabelIgnoreCase(label);
		return validateOptionalAndReturnsHashtag(hashtagOptional);
	}

	private Hashtag validateOptionalAndReturnsHashtag(Optional<Hashtag> hashtagOptional) {
		return hashtagOptional.orElseThrow
			(() -> new NotFoundException("Invalid username. Expected to find a user by username but was false."));
	}
}
