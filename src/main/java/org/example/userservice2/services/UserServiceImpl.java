package org.example.userservice2.services;

import org.example.userservice2.exceptions.TokenNotFoundException;
import org.example.userservice2.exceptions.UserAlreadyExistsException;
import org.example.userservice2.exceptions.UserNotExistsException;
import org.example.userservice2.models.Token;
import org.example.userservice2.models.User;
import org.example.userservice2.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    UserRepository userRepository;
    TokenService tokenService;
    PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           TokenService tokenService,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User registerUser(String name, String email, String password) throws UserAlreadyExistsException {
        // 1. Check if the user with email already exists.
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            throw new UserAlreadyExistsException("User with email " + email + " already exists");
        }

        // 2. Create a new user.
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setHashedPassword(passwordEncoder.encode(password));

        // 3. Save the user to the database
        // 4. Return the user.
        return userRepository.save(user);
    }

    @Override
    public Token login(String email, String password) throws UserNotExistsException {
        // 1. Check if the user with email and password exists.
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            throw new UserNotExistsException("User with email " + email + " does not exist");
        }
        User user = optionalUser.get();
        if (!passwordEncoder.matches(password, user.getHashedPassword())) {
            throw new UserNotExistsException("Incorrect password");
        }

        // 2. If the user exists, create a new token.
        return tokenService.generateToken(user);
    }

    @Override
    public void logout(String token) throws TokenNotFoundException {
        // 1. Remove the token from the database.
        tokenService.removeToken(token);
    }

    @Override
    public User getUserById(Long userId) throws UserNotExistsException {
        // 1. Find the user by id.
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()){
            throw new UserNotExistsException("User with id " + userId + " does not exist");
        }

        // 2. If the user exists, return the user.
        return  optionalUser.get();
    }
}
