package com.cooksysteam1.socialmedia.service;

import java.util.List;

import com.cooksysteam1.socialmedia.entity.model.response.HashtagResponseDto;
import com.cooksysteam1.socialmedia.entity.model.response.TweetResponseDto;

public interface HashtagService {

	List<HashtagResponseDto> getAllHashtags();

	List<TweetResponseDto> getHashtagsByLabel(String label);
}
