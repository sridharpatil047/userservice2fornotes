package org.example.userservice2.dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.userservice2.models.Role;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RolesDto {
    String name;
    String description;

    public static RolesDto from(Role role) {
        RolesDto rolesDto = new RolesDto();
        rolesDto.setName(role.getName());
        rolesDto.setDescription(role.getDescription());
        return rolesDto;
    }
    public static List<RolesDto> from(List<Role> roles) {
        List<RolesDto> rolesDtos = new ArrayList<>();
        for (Role role : roles) {
            rolesDtos.add(RolesDto.from(role));
        }
        return rolesDtos;
    }
}
