package com.moduletwo.innopolis.model.dto;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserAdminDto {
    @Id
    private Long id;
    @Id
    private  Long idUser;
    private String name;

    private String lastName;

    private String middleName;

    private String birthday;
    private String polis;

    private Boolean isDoctor;
}
