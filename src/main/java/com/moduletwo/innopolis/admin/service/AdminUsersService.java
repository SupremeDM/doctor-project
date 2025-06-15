package com.moduletwo.innopolis.admin.service;

import com.moduletwo.innopolis.admin.repository.AdminUserRepository;
import com.moduletwo.innopolis.model.dto.DoctorAdminDto;
import com.moduletwo.innopolis.model.dto.UserAdminDto;
import com.moduletwo.innopolis.model.dto.UserDto;
import com.moduletwo.innopolis.model.entity.*;
import com.moduletwo.innopolis.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminUsersService {

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Autowired
    private DoctorsRepository doctorsRepository;

    @Autowired
    private SpecialityRepository specialityRepository;
    @Autowired
    private ZoneRepository zoneRepository;

    @Autowired
    private RecordRepository recordRepository;
    @Autowired
    private UserRepository userRepository;


    public List<UserAdminDto> searchAllUsers() {
        return adminUserRepository.custom_findAllUsers();
    }

    public List<DoctorAdminDto> searchAllDoctors() {
        return adminUserRepository.custom_findAllDoctors();
    }

    @Transactional
    public Boolean addNewDoctor(DoctorEntity doctorEntity) {
        if(doctorsRepository.addNewRolesDoctorToUser(doctorEntity.getIdUser()) == null) {
            return false;
        }
        return doctorsRepository.save(doctorEntity).getId() != null ? true : false;
    }

    public Boolean updateDoctor(DoctorEntity doctorEntity) {
        return doctorsRepository.update(doctorEntity.getIdUser(), doctorEntity.getIdSpeciality(),
                doctorEntity.getIdZone()).getId() != null ? true : false;
    }

    @Transactional
    public Boolean deleteDoctor(Long id) {
        if(doctorsRepository.deleteRolesDoctorToUser(id) == null) {
            return false;
        }
        return doctorsRepository.deleteDoctor(id).getId() != null ? true : false;
    }

    public List<SpecialityEntity> searchAllSpeciality() {
        return specialityRepository.findAll(); }
    public List<ZoneEntity> searchAllZone() {
        return zoneRepository.findAll();
    }

    public Boolean addNewRecord(RecordEntity recordEntity) {
        return recordRepository.save(recordEntity).getId() != null ? true : false;
    }



}

