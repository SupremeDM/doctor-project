package com.moduletwo.innopolis.record.controller;

import com.moduletwo.innopolis.admin.service.AdminUsersService;
import com.moduletwo.innopolis.constants.RoleConstant;
import com.moduletwo.innopolis.factory.MainControllerInterface;
import com.moduletwo.innopolis.model.dto.*;
import com.moduletwo.innopolis.model.entity.DoctorEntity;
import com.moduletwo.innopolis.model.entity.RecordEntity;
import com.moduletwo.innopolis.record.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/records/")
public class RecordController extends MainControllerInterface {
    @Autowired
    private RecordService recordService;

    @GetMapping("find_all_doctors")
    public ResponseEntity<List<DoctorToUserDto>> findAllDoctor() {
        return new ResponseEntity<>(recordService.findAllDoctor(), HttpStatus.OK);
    }

    @GetMapping("find_free_record/{idDoctor}")
    public ResponseEntity<List<RecordDto>> findFreeRecord(@PathVariable("idDoctor") Long idDoctor) {
        return new ResponseEntity<>(recordService.findFreeRecord(idDoctor), HttpStatus.OK);
    }

    @PostMapping("save_reserve_record")
    public ResponseEntity<Object> findAllDoctor(@RequestBody RecordEntity recordEntity) {
        if(! (checkFieldFromFrontend(recordEntity.getId()) &&
                checkFieldFromFrontend(recordEntity.getIdUser()))) {
            return new ResponseEntity<>(HttpEntity.EMPTY, HttpStatus.valueOf(500));
        }
        Boolean isUpdate = recordService.saveReserveRecord(recordEntity);
        if(isUpdate) {
            return new ResponseEntity<>(HttpEntity.EMPTY, HttpStatus.valueOf(200));
        } else {
            return new ResponseEntity<>(HttpEntity.EMPTY, HttpStatus.valueOf(505));
        }
    }

    @GetMapping("find_reserved_record/{idUser}")
    public ResponseEntity<List<ReservedRecordDto>> findReservedRecord(@PathVariable("idUser") Long idUser) {
        return new ResponseEntity<>(recordService.findReservedRecordUser(idUser), HttpStatus.OK);
    }

    @GetMapping("free_reserved_record/{id}")
    public ResponseEntity<Object> freeReservedRecord(@PathVariable("id") Long id) {
        if(id == null) {
            return new ResponseEntity<>(HttpEntity.EMPTY, HttpStatus.valueOf(500));
        }
        Boolean isUpdate = recordService.freeReserveRecord(id);
        if(isUpdate) {
            return new ResponseEntity<>(HttpEntity.EMPTY, HttpStatus.valueOf(200));
        } else {
            return new ResponseEntity<>(HttpEntity.EMPTY, HttpStatus.valueOf(505));
        }
    }

    @GetMapping("find_free_record_to_doctor/{idDoctor}")
    public ResponseEntity<List<RecordEntity>> findFreeRecordToDoctor(@PathVariable("idDoctor") Long idDoctor) {
        return new ResponseEntity<>(recordService.findFreeRecordToDoctor(idDoctor), HttpStatus.OK);
    }

    @GetMapping("find_reserved_record_to_doctor/{idDoctor}")
    public ResponseEntity<List<ReservedRecordToDoctorDto>> findReservedRecordToDoctor(@PathVariable("idDoctor") Long idDoctor) {
        return new ResponseEntity<>(recordService.findReervedRecordToDoctor(idDoctor), HttpStatus.OK);
    }

}
