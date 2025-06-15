package com.moduletwo.innopolis.admin.repository;

import com.moduletwo.innopolis.model.dto.DoctorAdminDto;
import com.moduletwo.innopolis.model.dto.UserAdminDto;
import com.moduletwo.innopolis.model.entity.UserDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdminUserRepository extends JpaRepository<UserDataEntity, Long> {

    @Query(nativeQuery = true)
    List<UserAdminDto> custom_findAllUsers();

    @Query(nativeQuery = true)
    List<DoctorAdminDto> custom_findAllDoctors();



}
