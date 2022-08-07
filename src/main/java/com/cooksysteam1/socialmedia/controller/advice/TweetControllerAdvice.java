package com.cooksysteam1.socialmedia.controller.advice;

import com.cooksysteam1.socialmedia.controller.exception.BadRequestException;
import com.cooksysteam1.socialmedia.controller.exception.NotAuthorizedException;
import com.cooksysteam1.socialmedia.controller.exception.NotFoundException;
import com.cooksysteam1.socialmedia.entity.model.response.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@ResponseBody
public class TweetControllerAdvice {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto handleNotFoundException(NotFoundException notFoundException, HttpServletRequest httpServletRequest) {
        return new ErrorDto(notFoundException.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleNotFoundException(BadRequestException badRequestException, HttpServletRequest httpServletRequest) {
        return new ErrorDto(badRequestException.getMessage());
    }

    @ExceptionHandler(NotAuthorizedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleNotFoundException(NotAuthorizedException notAuthorizedException, HttpServletRequest httpServletRequest) {
        return new ErrorDto(notAuthorizedException.getMessage());
    }
}
