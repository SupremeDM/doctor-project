package com.moduletwo.innopolis.repository;

import com.moduletwo.innopolis.model.dto.RecordDto;
import com.moduletwo.innopolis.model.dto.ReservedRecordDto;
import com.moduletwo.innopolis.model.dto.ReservedRecordToDoctorDto;
import com.moduletwo.innopolis.model.entity.RecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<RecordEntity, Long> {

    @Query(value = "select * from record where id_doctor = :idDoctor and id_user is null",
            nativeQuery = true)
    List<RecordEntity> findFreeRecord(@Param("idDoctor") Long idDoctor);

    @Query(value = "UPDATE RECORD D SET ID_USER = :idUser WHERE ID = :id RETURNING *",
            nativeQuery = true)
    RecordEntity custom_updateRecord(@Param("idUser") Long idUser,
                                     @Param("id") Long id);

    @Query(nativeQuery = true)
    List<ReservedRecordDto> custom_findRecordByUser(@Param("idUser") Long idUser);

    @Query(value = "UPDATE RECORD R SET ID_USER = NULL WHERE ID = :id RETURNING *",
            nativeQuery = true)
    RecordEntity setIdUserNull(@Param("id") Long id);

    @Query(value = "SELECT r.id, r.id_doctor, r.id_user, r.date, r.time FROM RECORD R " +
            "inner join doctors d on d.id_user = :idDoctor " +
            "WHERE R.ID_USER IS NULL " +
            "AND R.ID_DOCTOR = d.id AND  R.DATE >= CURRENT_DATE",
            nativeQuery = true)
    List<RecordEntity> custom_selectRecordFreeForDoctor(@Param("idDoctor") Long idDoctor);

    @Query(nativeQuery = true)
    List<ReservedRecordToDoctorDto> custom_selectRecordReservedForDoctor(@Param("idDoctor") Long idDoctor);

}
