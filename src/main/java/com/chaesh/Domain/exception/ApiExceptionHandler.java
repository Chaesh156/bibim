package com.chaesh.Domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {MemberNotExistException.class})
    public ResponseEntity<ApiException> handleMemberNotExistException(MemberNotExistException e){
        ApiException apiException = ApiException.builder()
                .code(ExceptionCode.MEMBER_NOT_FOUND_EXCEPTION)
                .localDateTime(LocalDateTime.now())
                .build();
        apiException.setDetail(e.getMessage());

        return new ResponseEntity<>(apiException, HttpStatus.NOT_FOUND);
    }


}
