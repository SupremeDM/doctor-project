package com.moduletwo.innopolis.model.dto;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class ReservedRecordToDoctorDto {
    @Id
    private Integer id;
    private String dateTimeRecord;
    private String patientFio;


}
