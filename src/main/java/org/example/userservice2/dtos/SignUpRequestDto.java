package org.example.userservice2.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequestDto {
    @NotBlank(message = "Name cannot be blank")
    String name;

    @NotNull(message = "Email is required")
    @Email(message = "Email should be valid")
    String email;
    @NotNull(message = "Password is required")
    @NotBlank(message = "Password cannot be blank")
    String password;
}
