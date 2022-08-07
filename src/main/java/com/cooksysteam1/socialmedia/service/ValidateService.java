package com.cooksysteam1.socialmedia.service;

public interface ValidateService {

  boolean hashtagExists(String label);

  boolean checkIfUsernameExist(String username);

  boolean checkUsernameAvailable(String username);
  
}
