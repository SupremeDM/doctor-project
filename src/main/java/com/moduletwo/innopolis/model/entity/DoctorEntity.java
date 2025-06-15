package com.moduletwo.innopolis.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "doctors")
public class DoctorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="id_user")
    private Long idUser;

    @Column(name ="id_zone")
    private Long idZone;

    @Column(name ="id_speciality")
    private Long idSpeciality;

}




