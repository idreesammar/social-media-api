package com.cooksysteam1.socialmedia.mapper;

import com.cooksysteam1.socialmedia.entity.Hashtag;
import com.cooksysteam1.socialmedia.entity.model.request.HashtagRequestDto;
import com.cooksysteam1.socialmedia.entity.model.response.HashtagResponseDto;
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
public class HashtagMapperImpl implements HashtagMapper {

    @Override
    public Hashtag requestToEntity(HashtagRequestDto hashtagRequestDto) {
        if ( hashtagRequestDto == null ) {
            return null;
        }

        Hashtag hashtag = new Hashtag();

        hashtag.setLabel( hashtagRequestDto.getLabel() );

        return hashtag;
    }

    @Override
    public List<Hashtag> requestsToEntities(List<HashtagRequestDto> hashtagRequestDtos) {
        if ( hashtagRequestDtos == null ) {
            return null;
        }

        List<Hashtag> list = new ArrayList<Hashtag>( hashtagRequestDtos.size() );
        for ( HashtagRequestDto hashtagRequestDto : hashtagRequestDtos ) {
            list.add( requestToEntity( hashtagRequestDto ) );
        }

        return list;
    }

    @Override
    public HashtagResponseDto entityToResponse(Hashtag hashtag) {
        if ( hashtag == null ) {
            return null;
        }

        HashtagResponseDto hashtagResponseDto = new HashtagResponseDto();

        hashtagResponseDto.setLabel( hashtag.getLabel() );
        hashtagResponseDto.setFirstUsed( hashtag.getFirstUsed() );
        hashtagResponseDto.setLastUsed( hashtag.getLastUsed() );

        return hashtagResponseDto;
    }

    @Override
    public List<HashtagResponseDto> entitiesToResponses(List<Hashtag> hashtags) {
        if ( hashtags == null ) {
            return null;
        }

        List<HashtagResponseDto> list = new ArrayList<HashtagResponseDto>( hashtags.size() );
        for ( Hashtag hashtag : hashtags ) {
            list.add( entityToResponse( hashtag ) );
        }

        return list;
    }
}
