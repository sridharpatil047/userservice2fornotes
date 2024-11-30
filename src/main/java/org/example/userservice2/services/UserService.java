package org.example.userservice2.services;


import org.example.userservice2.exceptions.TokenNotFoundException;
import org.example.userservice2.exceptions.UserAlreadyExistsException;
import org.example.userservice2.exceptions.UserNotExistsException;
import org.example.userservice2.models.Token;
import org.example.userservice2.models.User;

public interface UserService {
    User registerUser(String name, String email, String password) throws UserAlreadyExistsException;
    Token login(String email, String password) throws UserNotExistsException;
    void logout(String token) throws TokenNotFoundException;
    User getUserById(Long userId) throws UserNotExistsException;
}
