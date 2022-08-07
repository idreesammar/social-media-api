package com.cooksysteam1.socialmedia.entity.resource;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Embeddable;

@Embeddable
@Data
@NoArgsConstructor
public class Profile {

    private String firstName;

    private String lastName;

    @NonNull
    private String email;

    private String phone;

}
