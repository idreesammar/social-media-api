package com.cooksysteam1.socialmedia.mapper;

import com.cooksysteam1.socialmedia.entity.model.request.CredentialsDto;
import com.cooksysteam1.socialmedia.entity.resource.Credentials;
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
public class CredentialsMapperImpl implements CredentialsMapper {

    @Override
    public Credentials requestToEntity(CredentialsDto credentialsRequestDto) {
        if ( credentialsRequestDto == null ) {
            return null;
        }

        Credentials credentials = new Credentials();

        credentials.setUsername( credentialsRequestDto.getUsername() );
        credentials.setPassword( credentialsRequestDto.getPassword() );

        return credentials;
    }

    @Override
    public List<Credentials> requestsToEntities(List<CredentialsDto> credentialsRequestDtos) {
        if ( credentialsRequestDtos == null ) {
            return null;
        }

        List<Credentials> list = new ArrayList<Credentials>( credentialsRequestDtos.size() );
        for ( CredentialsDto credentialsDto : credentialsRequestDtos ) {
            list.add( requestToEntity( credentialsDto ) );
        }

        return list;
    }
}
