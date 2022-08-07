package com.cooksysteam1.socialmedia.mapper;

import com.cooksysteam1.socialmedia.entity.Tweet;
import com.cooksysteam1.socialmedia.entity.model.request.TweetRequestDto;
import com.cooksysteam1.socialmedia.entity.model.response.TweetResponseDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-01T19:50:44-0700",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 11.0.15 (Oracle Corporation)"
)
@Component
public class TweetMapperImpl implements TweetMapper {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Tweet requestToEntity(TweetRequestDto tweetRequestDto) {
        if ( tweetRequestDto == null ) {
            return null;
        }

        Tweet tweet = new Tweet();

        tweet.setContent( tweetRequestDto.getContent() );

        return tweet;
    }

    @Override
    public List<Tweet> requestsToEntities(List<TweetRequestDto> tweetRequestDtos) {
        if ( tweetRequestDtos == null ) {
            return null;
        }

        List<Tweet> list = new ArrayList<Tweet>( tweetRequestDtos.size() );
        for ( TweetRequestDto tweetRequestDto : tweetRequestDtos ) {
            list.add( requestToEntity( tweetRequestDto ) );
        }

        return list;
    }

    @Override
    public TweetResponseDto entityToResponse(Tweet tweet) {
        if ( tweet == null ) {
            return null;
        }

        TweetResponseDto tweetResponseDto = new TweetResponseDto();

        tweetResponseDto.setAuthor( userMapper.entityToResponse( tweet.getAuthor() ) );
        tweetResponseDto.setId( tweet.getId() );
        tweetResponseDto.setPosted( tweet.getPosted() );
        tweetResponseDto.setContent( tweet.getContent() );
        tweetResponseDto.setInReplyTo( entityToResponse( tweet.getInReplyTo() ) );
        tweetResponseDto.setRepostOf( entityToResponse( tweet.getRepostOf() ) );

        return tweetResponseDto;
    }

    @Override
    public List<TweetResponseDto> entitiesToResponses(List<Tweet> tweets) {
        if ( tweets == null ) {
            return null;
        }

        List<TweetResponseDto> list = new ArrayList<TweetResponseDto>( tweets.size() );
        for ( Tweet tweet : tweets ) {
            list.add( entityToResponse( tweet ) );
        }

        return list;
    }
}
