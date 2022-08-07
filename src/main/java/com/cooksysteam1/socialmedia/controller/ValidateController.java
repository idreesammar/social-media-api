package com.cooksysteam1.socialmedia.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cooksysteam1.socialmedia.service.ValidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.cooksysteam1.socialmedia.service.ValidateService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/validate")
public class ValidateController {
	
	private final ValidateService validateService;
	
	@GetMapping("/tag/exists/{label}")
  @ResponseStatus(HttpStatus.OK)
	public boolean hashtagExists (@PathVariable String label) {
		return validateService.hashtagExists(label);
	}

  @GetMapping("/username/exists/@{username}")
  @ResponseStatus(HttpStatus.OK)
  public boolean checkUsernameExist(@PathVariable String username) {
      return validateService.checkIfUsernameExist(username);
  }

  @GetMapping("/username/available/@{username}")
  @ResponseStatus(HttpStatus.OK)
  public boolean checkUsernameAvailable(@PathVariable String username) {
      return validateService.checkUsernameAvailable(username);
  }
}
