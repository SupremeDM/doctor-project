package com.moduletwo.innopolis.record.service;

import com.moduletwo.innopolis.model.dto.DoctorToUserDto;
import com.moduletwo.innopolis.model.dto.RecordDto;
import com.moduletwo.innopolis.model.dto.ReservedRecordDto;
import com.moduletwo.innopolis.model.dto.ReservedRecordToDoctorDto;
import com.moduletwo.innopolis.model.entity.RecordEntity;
import com.moduletwo.innopolis.model.entity.UserDataEntity;
import com.moduletwo.innopolis.repository.RecordRepository;
import com.moduletwo.innopolis.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecordService {

    @Autowired
    private UserDataRepository userDataRepository;

    @Autowired
    private RecordRepository recordRepository;


    public List<DoctorToUserDto> findAllDoctor() {
        return userDataRepository.custom_findAllDoctorsForUser();
    }

    public List<RecordDto> findFreeRecord(Long idDoctor) {
        List<RecordEntity> listRecordEntity = recordRepository.findFreeRecord(idDoctor);
        List<RecordDto> recordDtoList = new ArrayList<>();
        for(RecordEntity recordEntity: listRecordEntity) {
            RecordDto recordDto = new RecordDto();
            recordDto.setId(recordEntity.getId());
            recordDto.setIdDoctor(recordEntity.getIdDoctor());
            recordDto.setDateAndTimeRecord(recordEntity.getDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + " " + recordEntity.getTime());
            recordDtoList.add(recordDto);
        }
        return recordDtoList;
    }

    public Boolean saveReserveRecord(RecordEntity recordEntity) {
        return recordRepository.custom_updateRecord(recordEntity.getIdUser(), recordEntity.getId()).getId() != null ? true : false;
    }

    public Boolean freeReserveRecord(Long id) {
        return recordRepository.setIdUserNull(id).getId() != null ? true : false;
    }

    public List<ReservedRecordDto> findReservedRecordUser(Long idUser) {
       return recordRepository.custom_findRecordByUser(idUser);
    }

    public List<RecordEntity> findFreeRecordToDoctor(Long idDoctor) {
        return recordRepository.custom_selectRecordFreeForDoctor(idDoctor);
    }

    public List<ReservedRecordToDoctorDto> findReervedRecordToDoctor(Long idDoctor) {
        return recordRepository.custom_selectRecordReservedForDoctor(idDoctor);
    }
}
