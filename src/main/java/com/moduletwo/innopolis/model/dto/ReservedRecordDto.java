package com.moduletwo.innopolis.model.dto;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class ReservedRecordDto {
    @Id
    private Long id;
    private String doctorFio;
    private String zoneName;
    private String specialityName;
    private String dateTimeRecord;
}
