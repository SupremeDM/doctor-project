package com.moduletwo.innopolis.repository;

import com.moduletwo.innopolis.model.entity.DoctorEntity;
import com.moduletwo.innopolis.model.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface DoctorsRepository extends JpaRepository<DoctorEntity, Long> {
    @Query(value = "DELETE FROM DOCTORS D WHERE ID_USER = :idUser RETURNING *",
            nativeQuery = true)
    DoctorEntity deleteDoctor(@Param("idUser") Long idUser);

    @Query(value = "UPDATE DOCTORS D SET ID_SPECIALITY = :idSpeciality, ID_ZONE = :idZone WHERE ID_USER = :idUser RETURNING *",
            nativeQuery = true)
    DoctorEntity update(@Param("idUser") Long idUser,
                        @Param("idSpeciality") Long idSpeciality,
                        @Param("idZone") Long idZone);


    @Query(value = "INSERT INTO USERS_ROLES(ID_USER, ID_ROLE) VALUES(:idUser, 8) RETURNING *",
            nativeQuery = true)
    Object addNewRolesDoctorToUser(@Param("idUser") Long idUser);

    @Query(value = "DELETE FROM USERS_ROLES WHERE ID_USER = :idUser AND ID_ROLE = 8 RETURNING *",
            nativeQuery = true)
    Object deleteRolesDoctorToUser(@Param("idUser") Long idUser);


}

