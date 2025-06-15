package com.moduletwo.innopolis.admin.controller;

import com.moduletwo.innopolis.admin.service.AdminUsersService;
import com.moduletwo.innopolis.constants.RoleConstant;
import com.moduletwo.innopolis.factory.MainControllerInterface;
import com.moduletwo.innopolis.model.dto.DoctorAdminDto;
import com.moduletwo.innopolis.model.dto.UserAdminDto;
import com.moduletwo.innopolis.model.dto.UserDto;
import com.moduletwo.innopolis.model.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(RoleConstant.ADMIN_API)
public class AdminController extends MainControllerInterface {
    @Autowired
    private AdminUsersService adminUsersService;

    @GetMapping("find_all_users")
    public ResponseEntity<List<UserAdminDto>> findAllUsers() {
        return new ResponseEntity<>(adminUsersService.searchAllUsers(), HttpStatus.OK);
    }

    @GetMapping("find_all_doctors")
    public ResponseEntity<List<DoctorAdminDto>> findAllDoctors() {
        return new ResponseEntity<>(adminUsersService.searchAllDoctors(), HttpStatus.OK);
    }

    @PostMapping( "add_new_doctor")
    public ResponseEntity<Object> addNewDoctor(@RequestBody DoctorEntity doctorEntity) {
        if(!checkAddNewDoctor(doctorEntity)) {
            return new ResponseEntity<>(HttpStatus.valueOf(500));
        }
        Boolean isAdd = adminUsersService.addNewDoctor(doctorEntity);
        if(isAdd) {
            return new ResponseEntity<>(HttpEntity.EMPTY, HttpStatus.valueOf(200));
        } else {
            return new ResponseEntity<>(HttpEntity.EMPTY, HttpStatus.valueOf(505));
        }
    }

    @PostMapping( "delete_doctor")
    public ResponseEntity<Object> deleteDoctor(@RequestBody DoctorEntity doctorEntity) {
        if(doctorEntity.getIdUser() == null) {
            return new ResponseEntity<>(HttpStatus.valueOf(500));
        }
        Boolean isDeleted = adminUsersService.deleteDoctor(doctorEntity.getIdUser());
        if(isDeleted) {
            return new ResponseEntity<>(HttpEntity.EMPTY, HttpStatus.valueOf(200));
        } else {
            return new ResponseEntity<>(HttpEntity.EMPTY, HttpStatus.valueOf(505));
        }
    }

    @GetMapping("find_all_speciality")
    public ResponseEntity<List<SpecialityEntity>> searchAllSpeciality() {
        return new ResponseEntity<>(adminUsersService.searchAllSpeciality(), HttpStatus.OK);
    }

    @GetMapping("find_all_zone")
    public ResponseEntity<List<ZoneEntity>> searchAllZone() {
        return new ResponseEntity<>(adminUsersService.searchAllZone(), HttpStatus.OK);
    }

    @PostMapping( "update_doctor")
    public ResponseEntity<Object> updateDoctor(@RequestBody DoctorEntity doctorEntity) {
        if(!checkUpdateDoctor(doctorEntity)) {
            return new ResponseEntity<>(HttpStatus.valueOf(500));
        }
        Boolean isUpdate = adminUsersService.updateDoctor(doctorEntity);
        if(isUpdate) {
            return new ResponseEntity<>(HttpEntity.EMPTY, HttpStatus.valueOf(200));
        } else {
            return new ResponseEntity<>(HttpEntity.EMPTY, HttpStatus.valueOf(505));
        }
    }

    @PostMapping( "add_new_record")
    public ResponseEntity<Object> addNewRecord(@RequestBody RecordEntity recordEntity) {
        if(!checkAddNewRecord(recordEntity)) {
            return new ResponseEntity<>(HttpStatus.valueOf(500));
        }
        Boolean isAdd = adminUsersService.addNewRecord(recordEntity);
        if(isAdd) {
            return new ResponseEntity<>(HttpEntity.EMPTY, HttpStatus.valueOf(200));
        } else {
            return new ResponseEntity<>(HttpEntity.EMPTY, HttpStatus.valueOf(505));
        }
    }



    private Boolean checkAddNewDoctor(DoctorEntity doctorEntity) {
        return checkFieldFromFrontend(doctorEntity.getIdUser());
    }

    private Boolean checkUpdateDoctor(DoctorEntity doctorEntity) {
        return checkFieldFromFrontend(doctorEntity.getIdUser()) &&
                checkFieldFromFrontend(doctorEntity.getIdSpeciality()) &&
                checkFieldFromFrontend(doctorEntity.getId()) &&
                checkFieldFromFrontend(doctorEntity.getIdZone());
    }

    private Boolean checkAddNewRecord(RecordEntity recordEntity) {
        return checkFieldFromFrontend(recordEntity.getDate()) &&
                checkFieldFromFrontend(recordEntity.getIdDoctor()) &&
                checkFieldFromFrontend(recordEntity.getTime());
    }
}
