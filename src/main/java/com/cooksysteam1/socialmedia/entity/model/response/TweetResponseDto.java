package com.cooksysteam1.socialmedia.entity.model.response;

import java.sql.Timestamp;

import com.cooksysteam1.socialmedia.entity.model.request.TweetRequestDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class TweetResponseDto {
	
	private Long id;
	
	private UserResponseDto author;
	
	private Timestamp posted;
	
	private String content;
	
	private TweetResponseDto inReplyTo;
	
	private TweetResponseDto repostOf; 
	
	
}
