package org.example.userservice2.services;


import org.example.userservice2.exceptions.TokenNotFoundException;
import org.example.userservice2.exceptions.UserNotExistsException;
import org.example.userservice2.models.Token;
import org.example.userservice2.models.User;

public interface TokenService {
    Token generateToken(User user);
    User validateToken(String token) throws TokenNotFoundException;
    void removeToken(String token) throws TokenNotFoundException;
}
