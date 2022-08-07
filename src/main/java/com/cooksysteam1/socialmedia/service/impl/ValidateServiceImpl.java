package com.cooksysteam1.socialmedia.service.impl;


import com.cooksysteam1.socialmedia.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.cooksysteam1.socialmedia.repository.HashtagRepository;
import com.cooksysteam1.socialmedia.service.ValidateService;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class ValidateServiceImpl implements ValidateService {
	
    private final HashtagRepository hashtagRepository;
    private final UserRepository userRepository;

    @Override
    public boolean hashtagExists(String label) {
      return hashtagRepository.existsByLabel(label);
    }
    
    @Override
    public boolean checkIfUsernameExist(String username) {
        return userRepository.existsByCredentials_UsernameAndDeletedFalse(username);
    }

    @Override
    public boolean checkUsernameAvailable(String username) {
        return userRepository.existsByCredentials_Username(username);
    }
}
