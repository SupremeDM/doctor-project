package com.moduletwo.innopolis.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.util.Collection;

@Data
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    private Boolean activity;
    @ManyToMany( cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "users_roles",
    joinColumns = @JoinColumn(name = "id_user"),
    inverseJoinColumns = @JoinColumn(name = "id_role")
    )
    private Collection<RoleEntity> roles;
}
