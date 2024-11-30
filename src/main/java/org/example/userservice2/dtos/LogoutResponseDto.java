package org.example.userservice2.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogoutResponseDto {
    String message;

    public static LogoutResponseDto from(String message){
        LogoutResponseDto logoutResponseDto = new LogoutResponseDto();
        logoutResponseDto.setMessage(message);
        return logoutResponseDto;
    }
}
