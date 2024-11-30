package org.example.userservice2.exceptions;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TokenNotFoundException extends Exception {
    public TokenNotFoundException(String message) {
        super(message);
    }
}
