package com.autofin.api.custom.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
 
@ResponseStatus(HttpStatus.ACCEPTED)
public class RecordNotFoundException extends RuntimeException
{
    private static final long serialVersionUID = 1L;
 
    public RecordNotFoundException(String message) 
    {
        super(message);
    }
}