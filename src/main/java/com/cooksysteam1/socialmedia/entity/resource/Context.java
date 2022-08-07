package com.cooksysteam1.socialmedia.entity.resource;

import com.cooksysteam1.socialmedia.entity.Tweet;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Context {

    private Tweet target;

    private List<Tweet> before;

    private List<Tweet> after;

}
