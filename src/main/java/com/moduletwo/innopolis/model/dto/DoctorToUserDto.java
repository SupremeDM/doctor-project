package com.moduletwo.innopolis.model.dto;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class DoctorToUserDto {

    @Id
    private Long idDoctor;
    private String doctorFio;
    private String specialityName;
    private String zoneName;

}
