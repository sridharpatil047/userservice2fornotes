package org.example.userservice2.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Token extends BaseModel{
    private String token;
    @ManyToOne
    private User user;
    private Long expireAt;
}
