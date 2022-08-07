package com.cooksysteam1.socialmedia.entity.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProfileDto {
	
	private String firstName;
	
	private String lastName;

	@NonNull
	private String email;
	
	private String phone;
	
}
