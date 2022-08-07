package com.cooksysteam1.socialmedia.mapper;

import com.cooksysteam1.socialmedia.entity.model.request.ProfileDto;
import com.cooksysteam1.socialmedia.entity.model.response.ProfileResponseDto;
import com.cooksysteam1.socialmedia.entity.resource.Profile;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-01T19:50:44-0700",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 11.0.15 (Oracle Corporation)"
)
@Component
public class ProfileMapperImpl implements ProfileMapper {

    @Override
    public Profile requestToEntity(ProfileDto profileDto) {
        if ( profileDto == null ) {
            return null;
        }

        Profile profile = new Profile();

        profile.setFirstName( profileDto.getFirstName() );
        profile.setLastName( profileDto.getLastName() );
        profile.setEmail( profileDto.getEmail() );
        profile.setPhone( profileDto.getPhone() );

        return profile;
    }

    @Override
    public List<Profile> requestsToEntities(List<ProfileDto> profileDtos) {
        if ( profileDtos == null ) {
            return null;
        }

        List<Profile> list = new ArrayList<Profile>( profileDtos.size() );
        for ( ProfileDto profileDto : profileDtos ) {
            list.add( requestToEntity( profileDto ) );
        }

        return list;
    }

    @Override
    public ProfileResponseDto entityToResponse(Profile profile) {
        if ( profile == null ) {
            return null;
        }

        ProfileResponseDto profileResponseDto = new ProfileResponseDto();

        profileResponseDto.setFirstName( profile.getFirstName() );
        profileResponseDto.setLastName( profile.getLastName() );
        profileResponseDto.setEmail( profile.getEmail() );
        profileResponseDto.setPhone( profile.getPhone() );

        return profileResponseDto;
    }

    @Override
    public List<ProfileResponseDto> entitiesToResponses(List<Profile> profiles) {
        if ( profiles == null ) {
            return null;
        }

        List<ProfileResponseDto> list = new ArrayList<ProfileResponseDto>( profiles.size() );
        for ( Profile profile : profiles ) {
            list.add( entityToResponse( profile ) );
        }

        return list;
    }
}
