package com.moduletwo.innopolis.model.dto;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class DoctorAdminDto {
    @Id
    private Integer id;
    @Id
    private  Long idUser;

    private String name;

    private String lastName;

    private String middleName;

    private String specialityName;
    private String zoneName;

    @Id
    private  Integer idSpeciality;

    @Id
    private  Integer idZone;

    @Id
    private Integer idDoctor;


}

//package com.moduletwo.innopolis.model.dto;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class DoctorAdminDto {
//    private Integer id;
//    private Long idUser;
//    private String name;
//    private String lastName;
//    private String middleName;
//    private String specialityName;
//    private String zoneName;
//    private Integer idSpeciality;
//    private Integer idZone;
//    private Integer idDoctor;
//}