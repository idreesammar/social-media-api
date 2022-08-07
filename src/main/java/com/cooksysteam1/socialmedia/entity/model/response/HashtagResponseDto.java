package com.cooksysteam1.socialmedia.entity.model.response;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class HashtagResponseDto {
	
	private String label;
	
	private Timestamp firstUsed;
	
	private Timestamp lastUsed;
	
}
