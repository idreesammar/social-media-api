package com.cooksysteam1.socialmedia.entity.model.response;

import java.sql.Timestamp;

import com.cooksysteam1.socialmedia.entity.resource.Profile;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserResponseDto {
    
	private String username;
    
    private Profile profile;
    
    private Timestamp joined;
}