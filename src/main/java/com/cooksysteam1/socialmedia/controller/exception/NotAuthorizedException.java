package com.cooksysteam1.socialmedia.controller.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NotAuthorizedException extends RuntimeException{
    private final static long serialVersionUID = -9556005374278628L;

    private String message;
}
