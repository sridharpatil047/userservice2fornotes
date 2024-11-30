package org.example.userservice2.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserServiceException extends Exception{
    public UserServiceException(String message){
        super(message);
    }
}
