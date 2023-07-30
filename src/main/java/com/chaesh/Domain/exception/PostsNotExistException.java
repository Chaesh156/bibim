package com.chaesh.Domain.exception;

public class PostsNotExistException extends RuntimeException{
    public PostsNotExistException(String message){
        super(message);
    }
}
