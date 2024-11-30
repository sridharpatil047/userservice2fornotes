package org.example.userservice2.services;

import org.apache.commons.lang3.RandomStringUtils;
import org.example.userservice2.exceptions.TokenNotFoundException;
import org.example.userservice2.models.Token;
import org.example.userservice2.models.User;
import org.example.userservice2.repositories.TokenRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TokenServiceImpl implements TokenService {
    TokenRepository tokenRepository;

    public TokenServiceImpl(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public Token generateToken(User user){
        // 1. Generate a token
        Token token = new Token();
        token.setToken(RandomStringUtils.randomAlphabetic(10));
        token.setUser(user);
        token.setExpireAt(System.currentTimeMillis() + 1000 * 60 * 60 * 24);

        // 2. Save the token to the database
        // 3. Return the token
        return tokenRepository.save(token);
    }

    @Override
    public User validateToken(String token) throws TokenNotFoundException {

        Optional<Token> optionalToken = tokenRepository
                .findByTokenAndExpireAtIsGreaterThanAndDeleted(
                        token,
                        System.currentTimeMillis(),
                        false
                );
        if (optionalToken.isEmpty()) {
            throw new TokenNotFoundException("Token not found");
        }

        return optionalToken.get().getUser();
    }

//    @Override
//    public User validateToken(String token) throws TokenNotFoundException {
//
//        // 1. Get the token from the database
//        Optional<Token> optionalToken = tokenRepository.findByToken(token);
//        if (optionalToken.isEmpty()) {
//            throw new TokenNotFoundException("Token not found");
//        }
//
//        // 2. Check if the token is expired and not soft deleted
//        Token token1 = optionalToken.get();
//        if (token1.getExpireAt() < System.currentTimeMillis() || token1.isDeleted()) {
//            throw new TokenNotFoundException("Token is expired or user is logged out");
//        }
//
//        // 3. Return the user
//        return token1.getUser();
//    }



    @Override
    public void removeToken(String token) throws TokenNotFoundException {
        // 1. Get the token from the database
        Optional<Token> optionalToken = tokenRepository.findByToken(token);
        if (optionalToken.isEmpty()) {
            throw new TokenNotFoundException("Token not found");
        }

        // 2. Soft delete the token
        Token token1 = optionalToken.get();
        token1.setDeleted(true);
        tokenRepository.save(token1);
    }
}
