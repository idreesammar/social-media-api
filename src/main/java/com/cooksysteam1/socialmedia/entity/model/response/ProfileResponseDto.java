package com.cooksysteam1.socialmedia.entity.model.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ProfileResponseDto {
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String phone;
	
}
