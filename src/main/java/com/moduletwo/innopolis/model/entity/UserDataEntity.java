package com.moduletwo.innopolis.model.entity;

import com.moduletwo.innopolis.model.dto.DoctorAdminDto;
import com.moduletwo.innopolis.model.dto.DoctorToUserDto;
import com.moduletwo.innopolis.model.dto.UserAdminDto;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@NamedNativeQuery(name = "UserDataEntity.custom_findAllUsers",
        query = "select ud.id, " +
                "   ud.id_user as idUser, ud.name as name, ud.last_name as lastName, ud.middle_name as middleName," +
                " to_char(ud.birthday, 'dd.MM.yyyy') as birthday, ud.polis,   " +
                "CASE WHEN d.id is not null then true else false end as isDoctor " +
                "from user_data ud " +
                "left join doctors d on d.id_user = ud.id_user",
        resultSetMapping = "Mapping.UserAdminDto")
@SqlResultSetMapping(name = "Mapping.UserAdminDto",
        classes = @ConstructorResult(targetClass = UserAdminDto.class,
                columns = {@ColumnResult(name = "id"),
                            @ColumnResult(name = "idUser"),
                        @ColumnResult(name = "name"),
                        @ColumnResult(name = "lastName"),
                        @ColumnResult(name = "middleName"),
                        @ColumnResult(name = "birthday"),
                        @ColumnResult(name = "polis"),
                        @ColumnResult(name = "isDoctor")}))

@NamedNativeQuery(name = "UserDataEntity.custom_findAllDoctors",
        query = "select ud.id, " +
                "   ud.id_user as idUser, ud.name as name, ud.last_name as lastName, ud.middle_name as middleName," +
                "  z.name as zoneName, s.name as specialityName, z.id as idZone, s.id as idSpeciality, d.id as idDoctor  " +
                "from user_data ud " +
                "inner join doctors d on d.id_user = ud.id_user " +
                "left join speciality s on s.id = d.id_speciality " +
                "left join zone z on z.id = d.id_zone ",
        resultSetMapping = "Mapping.DoctorAdminDto")
//@SqlResultSetMapping(name = "Mapping.DoctorAdminDto",
//        classes = @ConstructorResult(targetClass = DoctorAdminDto.class,
//                columns = {@ColumnResult(name = "id"),
//                        @ColumnResult(name = "idUser"),
//                        @ColumnResult(name = "name"),
//                        @ColumnResult(name = "lastName"),
//                        @ColumnResult(name = "middleName"),
//                        @ColumnResult(name = "zoneName"),
//                        @ColumnResult(name = "specialityName"),
//                        @ColumnResult(name = "idZone"),
//                        @ColumnResult(name = "idSpeciality"),
//                        @ColumnResult(name = "idDoctor")}))



@SqlResultSetMapping(name = "Mapping.DoctorAdminDto",
        classes = @ConstructorResult(targetClass = DoctorAdminDto.class,
                columns = {
                        @ColumnResult(name = "id", type = Integer.class),
                        @ColumnResult(name = "idUser", type = Long.class),
                        @ColumnResult(name = "name", type = String.class),
                        @ColumnResult(name = "lastName", type = String.class),
                        @ColumnResult(name = "middleName", type = String.class),
                        @ColumnResult(name = "zoneName", type = String.class),
                        @ColumnResult(name = "specialityName", type = String.class),
                        @ColumnResult(name = "idZone", type = Integer.class),
                        @ColumnResult(name = "idSpeciality", type = Integer.class),
                        @ColumnResult(name = "idDoctor", type = Integer.class)
                }))


@NamedNativeQuery(name = "UserDataEntity.custom_findAllDoctorsForUser",
        query = "select d.id as idDoctor," +
                "concat(ud.last_name, ' ', ud.name, ' ', ud.middle_name) as doctorFio, " +
                "z.name as zoneName, s.name as specialityName " +
                "from user_data ud " +
                "inner join doctors d on d.id_user = ud.id_user " +
                "left join speciality s on s.id = d.id_speciality " +
                "left join zone z on z.id = d.id_zone ",
        resultSetMapping = "Mapping.DoctorToUserDto")
@SqlResultSetMapping(name = "Mapping.DoctorToUserDto",
        classes = @ConstructorResult(targetClass = DoctorToUserDto.class,
                columns = {@ColumnResult(name = "idDoctor"),
                        @ColumnResult(name = "doctorFio"),
                        @ColumnResult(name = "zoneName"),
                        @ColumnResult(name = "specialityName")}))


@Data
@Entity
@Table(name = "user_data")
public class UserDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "id_user")
    private Long idUser;
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "polis")
    private String polis;


}
