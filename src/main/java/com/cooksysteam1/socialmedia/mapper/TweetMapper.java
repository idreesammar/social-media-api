package com.cooksysteam1.socialmedia.mapper;

import com.cooksysteam1.socialmedia.entity.Tweet;
import com.cooksysteam1.socialmedia.entity.model.request.TweetRequestDto;
import com.cooksysteam1.socialmedia.entity.model.response.TweetResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface TweetMapper {

    Tweet requestToEntity(TweetRequestDto tweetRequestDto);

    List<Tweet> requestsToEntities(List<TweetRequestDto> tweetRequestDtos);

    @Mapping(source = "author", target = "author")
    TweetResponseDto entityToResponse(Tweet tweet);

    List<TweetResponseDto> entitiesToResponses(List<Tweet> tweets);
}
