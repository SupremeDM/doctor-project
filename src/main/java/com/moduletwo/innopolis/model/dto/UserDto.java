package com.moduletwo.innopolis.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDate;
import java.util.Collection;

@Data
public class UserDto {

    private Long id;
    private String name;
    private String lastName;
    private String middleName;
    private String polis;

    private LocalDate birthday;
    private Collection<? extends GrantedAuthority> authorities;
    private String username;
    private String password;
}
