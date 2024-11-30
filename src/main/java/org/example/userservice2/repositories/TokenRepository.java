package org.example.userservice2.repositories;

import org.example.userservice2.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByToken(String token);

    Optional<Token> findByTokenAndExpireAtIsGreaterThanAndDeleted(
            String token,
            Long expireAt,
            boolean deleted
    );
}
