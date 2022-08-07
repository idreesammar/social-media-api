package com.cooksysteam1.socialmedia.mapper;

import com.cooksysteam1.socialmedia.entity.model.response.ContextDto;
import com.cooksysteam1.socialmedia.entity.resource.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {TweetMapper.class})
public interface ContextMapper {

//    @Mapping(source = "before", target = "before")
    ContextDto entityToResponse(Context context);
}
