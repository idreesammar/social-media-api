package com.cooksysteam1.socialmedia.mapper;

import com.cooksysteam1.socialmedia.entity.Hashtag;
import com.cooksysteam1.socialmedia.entity.model.request.HashtagRequestDto;
import com.cooksysteam1.socialmedia.entity.model.response.HashtagResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HashtagMapper {

    Hashtag requestToEntity(HashtagRequestDto hashtagRequestDto);

    List<Hashtag>  requestsToEntities(List<HashtagRequestDto> hashtagRequestDtos);

    HashtagResponseDto entityToResponse(Hashtag hashtag);

    List<HashtagResponseDto> entitiesToResponses(List<Hashtag> hashtags);

}
