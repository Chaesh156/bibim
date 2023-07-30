package com.chaesh.Domain.exception;

public class MemberNotExistException extends RuntimeException{
    public MemberNotExistException(String message){
        super(message);
    }
}
