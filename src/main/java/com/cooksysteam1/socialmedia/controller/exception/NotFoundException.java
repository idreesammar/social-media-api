package com.cooksysteam1.socialmedia.controller.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NotFoundException extends RuntimeException {
    private final static long serialVersionUID = -4209079127085990559L;
    
    private String message;
}
