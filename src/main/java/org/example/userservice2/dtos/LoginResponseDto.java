package org.example.userservice2.dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.userservice2.models.Token;

    @Getter
    @Setter
    public class LoginResponseDto {
        String token;
        public static LoginResponseDto from(Token token){
            LoginResponseDto loginResponseDto = new LoginResponseDto();
            loginResponseDto.setToken(token.getToken());
            return loginResponseDto;
        }
    }
