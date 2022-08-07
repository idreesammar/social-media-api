package com.cooksysteam1.socialmedia.mapper;

import com.cooksysteam1.socialmedia.entity.User;
import com.cooksysteam1.socialmedia.entity.model.request.UserRequestDto;
import com.cooksysteam1.socialmedia.entity.model.response.UserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring", uses = {CredentialsMapper.class})
public interface UserMapper {

    User requestToEntity(UserRequestDto userRequestDto);

    List<User> requestToEntities(List<UserRequestDto> userRequestDtos);

    @Mapping(source = "credentials.username", target = "username")
    UserResponseDto entityToResponse(User user);

    List<UserResponseDto> entitiesToResponses(List<User> users);
}
