package com.moduletwo.innopolis.repository;

import com.moduletwo.innopolis.model.dto.DoctorAdminDto;
import com.moduletwo.innopolis.model.dto.DoctorToUserDto;
import com.moduletwo.innopolis.model.entity.UserDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserDataRepository extends JpaRepository<UserDataEntity, Long> {
    @Query(value = "select * from user_data where id_user = :idUser",
            nativeQuery = true)
    UserDataEntity findUserData(@Param("idUser") Long idUser);

    @Query(nativeQuery = true)
    List<DoctorToUserDto> custom_findAllDoctorsForUser();


}
