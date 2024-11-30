package org.example.userservice2.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserNotExistsException extends UserServiceException {
    public UserNotExistsException(String message) {
        super(message);
    }
}
