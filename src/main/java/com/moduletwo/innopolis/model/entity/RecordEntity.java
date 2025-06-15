package com.moduletwo.innopolis.model.entity;

import com.moduletwo.innopolis.model.dto.DoctorToUserDto;
import com.moduletwo.innopolis.model.dto.ReservedRecordDto;
import com.moduletwo.innopolis.model.dto.ReservedRecordToDoctorDto;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;
import java.time.LocalDate;




@NamedNativeQuery(name = "RecordEntity.custom_findRecordByUser",
        query = "select r.id as id," +
                "concat(ud.last_name, ' ', ud.name, ' ', ud.middle_name) as doctorFio, " +
                "z.name as zoneName, s.name as specialityName, concat(to_char( r.date, 'dd.MM.yyyy'), ' ', r.time) as dateTimeRecord " +
                "from record r " +
                "inner join doctors d on d.id = r.id_doctor " +
                "inner join user_data ud on ud.id_user = d.id_user " +
                "left join speciality s on s.id = d.id_speciality " +
                "left join zone z on z.id = d.id_zone " +
                "where r.id_user = :idUser",
        resultSetMapping = "Mapping.ReservedRecordDto")
@SqlResultSetMapping(name = "Mapping.ReservedRecordDto",
        classes = @ConstructorResult(targetClass = ReservedRecordDto.class,
                columns = {@ColumnResult(name = "id"),
                        @ColumnResult(name = "doctorFio"),
                        @ColumnResult(name = "zoneName"),
                        @ColumnResult(name = "specialityName"),
                        @ColumnResult(name = "dateTimeRecord")}))

@NamedNativeQuery(name = "RecordEntity.custom_selectRecordReservedForDoctor",
        query = "SELECT r.id, concat(to_char(r.date, 'dd.MM.yyyy'), ' ', r.time) as dateTimeRecord, " +
                "concat(ud.last_name, ' ', ud.name, ' ', ud.middle_name) as patientFio " +
                " FROM RECORD R " +
                "inner join doctors d on d.id_user = :idDoctor " +
                "left join user_data ud on ud.id_user = r.id_user    " +
                "WHERE R.ID_USER IS NOT NULL " +
                "AND R.ID_DOCTOR = d.id AND  R.DATE >= CURRENT_DATE",
        resultSetMapping = "Mapping.ReservedRecordToDoctorDto")
@SqlResultSetMapping(name = "Mapping.ReservedRecordToDoctorDto",
        classes = @ConstructorResult(targetClass = ReservedRecordToDoctorDto.class,
                columns = {@ColumnResult(name = "id"),
                        @ColumnResult(name = "dateTimeRecord"),
                        @ColumnResult(name = "patientFio")}))


@Entity
@Data
@Table(name = "record")
public class RecordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="id_user")
    private Long idUser;
    @Column(name ="id_doctor")
    private Long idDoctor;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "time")
    private String time;
}
