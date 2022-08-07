package com.cooksysteam1.socialmedia.entity.resource;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Credentials {

    @NonNull
    private String username;

    @NonNull
    private String password;

}
