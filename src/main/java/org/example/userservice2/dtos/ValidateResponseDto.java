package org.example.userservice2.dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.userservice2.models.User;

import java.util.List;

@Getter
@Setter
public class ValidateResponseDto {
    Long id;
    String name;
    String email;
    List<RolesDto> roles;
    boolean emailVerified;

    public static ValidateResponseDto from(User user) {
        ValidateResponseDto validateResponseDto = new ValidateResponseDto();
        validateResponseDto.setId(user.getId());
        validateResponseDto.setName(user.getName());
        validateResponseDto.setEmail(user.getEmail());
        validateResponseDto.setEmailVerified(user.isEmailVerified());
        validateResponseDto.setRoles(RolesDto.from(user.getRoles()));
        return validateResponseDto;
    }
}
