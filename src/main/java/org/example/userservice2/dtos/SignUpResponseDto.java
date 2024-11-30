package org.example.userservice2.dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.userservice2.models.User;

@Getter
@Setter
public class SignUpResponseDto {
    Long id;
    String name;
    String email;

    public static SignUpResponseDto from(User user) {
        SignUpResponseDto signUpResponseDto = new SignUpResponseDto();
        signUpResponseDto.setId(user.getId());
        signUpResponseDto.setName(user.getName());
        signUpResponseDto.setEmail(user.getEmail());
        return signUpResponseDto;
    }
}
