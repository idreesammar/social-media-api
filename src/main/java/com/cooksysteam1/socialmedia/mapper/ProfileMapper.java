package com.cooksysteam1.socialmedia.mapper;

import com.cooksysteam1.socialmedia.entity.model.request.ProfileDto;
import com.cooksysteam1.socialmedia.entity.model.response.ProfileResponseDto;
import com.cooksysteam1.socialmedia.entity.resource.Profile;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    Profile requestToEntity(ProfileDto profileDto);

    List<Profile> requestsToEntities(List<ProfileDto> profileDtos);

    ProfileResponseDto entityToResponse(Profile profile);

    List<ProfileResponseDto> entitiesToResponses(List<Profile> profiles);
}
