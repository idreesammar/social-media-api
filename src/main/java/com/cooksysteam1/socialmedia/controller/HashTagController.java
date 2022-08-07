package com.cooksysteam1.socialmedia.controller;

import java.util.List;

import com.cooksysteam1.socialmedia.entity.model.response.TweetResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.cooksysteam1.socialmedia.entity.model.response.HashtagResponseDto;
import com.cooksysteam1.socialmedia.service.HashtagService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tags")
public class HashTagController {
	
	private final HashtagService hashtagService;
	
	@GetMapping
	public List<HashtagResponseDto> getAllHashtags() {
		return hashtagService.getAllHashtags();
	}

	@GetMapping("/{label}")
	@ResponseStatus(HttpStatus.FOUND)
	public List<TweetResponseDto> getHashtagsByLabel(@PathVariable String label) {
		return hashtagService.getHashtagsByLabel(label);
	}
}
