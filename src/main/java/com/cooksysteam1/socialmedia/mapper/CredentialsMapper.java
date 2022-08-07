package com.cooksysteam1.socialmedia.mapper;

import com.cooksysteam1.socialmedia.entity.model.request.CredentialsDto;
import com.cooksysteam1.socialmedia.entity.resource.Credentials;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CredentialsMapper {
    Credentials requestToEntity(CredentialsDto credentialsRequestDto);

    List<Credentials> requestsToEntities(List<CredentialsDto> credentialsRequestDtos);
}
