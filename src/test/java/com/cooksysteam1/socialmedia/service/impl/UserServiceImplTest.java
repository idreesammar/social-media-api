//package com.cooksysteam1.socialmedia.service.impl;
//
//import com.cooksysteam1.socialmedia.controller.exception.BadRequestException;
//import com.cooksysteam1.socialmedia.entity.User;
//import com.cooksysteam1.socialmedia.entity.model.request.CredentialsDto;
//import com.cooksysteam1.socialmedia.entity.model.request.ProfileDto;
//import com.cooksysteam1.socialmedia.entity.model.request.UserRequestDto;
//import com.cooksysteam1.socialmedia.entity.model.response.UserResponseDto;
//import com.cooksysteam1.socialmedia.entity.resource.Credentials;
//import com.cooksysteam1.socialmedia.entity.resource.Profile;
//import com.cooksysteam1.socialmedia.mapper.CredentialsMapper;
//import com.cooksysteam1.socialmedia.mapper.ProfileMapper;
//import com.cooksysteam1.socialmedia.mapper.TweetMapper;
//import com.cooksysteam1.socialmedia.mapper.UserMapper;
//import com.cooksysteam1.socialmedia.repository.TweetRepository;
//import com.cooksysteam1.socialmedia.repository.UserRepository;
//import com.cooksysteam1.socialmedia.service.UserService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class UserServiceImplTest {
//
//    private UserRepository userRepository;
//
//    private UserMapper userMapper;
//
//    private TweetRepository tweetRepository;
//
//    private TweetMapper tweetMapper;
//
//    private CredentialsMapper credentialsMapper;
//
//    private UserService userService;
//
//    @BeforeEach
//    void setup() {
//        userRepository = mock(UserRepository.class);
//        userMapper = mock(UserMapper.class);
//        tweetRepository = mock(TweetRepository.class);
//        tweetMapper = mock(TweetMapper.class);
//        credentialsMapper = mock(CredentialsMapper.class);
//        userService = new UserServiceImpl(userRepository, userMapper, tweetRepository, tweetMapper, credentialsMapper);
//    }
//
//    @Test
//    void createAUser_validDataPassed_returnsResponseDto() {
//        // GIVEN
//        CredentialsDto credentialsDto = new CredentialsDto();
//        credentialsDto.setUsername("Jakebld");
//        credentialsDto.setPassword("whatqqq");
//
//        Credentials credentials = new Credentials();
//        credentials.setUsername(credentialsDto.getUsername());
//        credentials.setPassword(credentialsDto.getPassword());
//
//        ProfileDto profileDto = new ProfileDto();
//        profileDto.setEmail("jake@gmail.com");
//
//        Profile profile = new Profile();
//        profile.setEmail(profileDto.getEmail());
//
//        UserRequestDto userRequestDto = new UserRequestDto();
//        userRequestDto.setCredentials(credentialsDto);
//        userRequestDto.setProfile(profileDto);
//
//        User user = new User();
//        user.setId(2L);
//        user.setDeleted(true);
//        user.setCredentials(credentials);
//        user.setProfile(profile);
//
//        UserResponseDto userResponseDto = new UserResponseDto();
//        userResponseDto.setUsername(credentials.getUsername());
//
//        // WHEN
//        when(credentialsMapper.requestToEntity(credentialsDto)).thenReturn(credentials);
//        when(userRepository.findUserByCredentials(credentials)).thenReturn(Optional.of(user));
//        when(userMapper.requestToEntity(userRequestDto)).thenReturn(user);
//        when(userRepository.saveAndFlush(user)).thenReturn(user);
//        when(userRepository.existsUserByCredentialsAndDeletedFalse(credentials)).thenReturn(false);
//        when(userMapper.entityToResponse(user)).thenReturn(userResponseDto);
//        UserResponseDto result = userService.createAUser(userRequestDto);
//
//        // THEN
//        assertNotNull(result, "Expected result to not be null but was false.");
//        assertEquals(result.getUsername(), credentials.getUsername(), "Expected usernames to be equal but was false.");
//        assertFalse(user.isDeleted());
//        verify(userRepository).findUserByCredentials(eq(credentials));
//        verify(credentialsMapper).requestToEntity(eq(credentialsDto));
//        verify(userMapper).entityToResponse(eq(user));
//    }
//
//    @Test
//    void createAUser_invalidProfilePassed_throwsException() {
//        // GIVEN
//        CredentialsDto credentialsDto = new CredentialsDto();
//        credentialsDto.setUsername("Jakebld");
//        credentialsDto.setPassword("whatqqq");
//
//        ProfileDto profileDto = new ProfileDto();
//        profileDto.setEmail("");
//
//        UserRequestDto userRequestDto = new UserRequestDto();
//        userRequestDto.setCredentials(credentialsDto);
//        userRequestDto.setProfile(profileDto);
//
//        // WHEN
//        // THEN
//        assertThrows(BadRequestException.class,
//                     ()-> userService.createAUser(userRequestDto),
//            "Expected BadRequestException to be thrown but was false.");
//    }
//
//    @Test
//    void createAUser_invalidCredentialsPassed_throwsException() {
//        // GIVEN
//        CredentialsDto credentialsDto = new CredentialsDto();
//        credentialsDto.setUsername("");
//        credentialsDto.setPassword("whatqqq");
//
//        ProfileDto profileDto = new ProfileDto();
//        profileDto.setEmail("Jake@gmail.com");
//
//        UserRequestDto userRequestDto = new UserRequestDto();
//        userRequestDto.setCredentials(credentialsDto);
//        userRequestDto.setProfile(profileDto);
//
//        // WHEN
//        // THEN
//        assertThrows(BadRequestException.class,
//                () -> userService.createAUser(userRequestDto),
//                "Expected BadRequestException to be thrown but was false.");
//    }
//
//}