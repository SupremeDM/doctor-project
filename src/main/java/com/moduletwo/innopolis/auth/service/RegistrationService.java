package com.moduletwo.innopolis.auth.service;

import com.moduletwo.innopolis.model.dto.UserDto;
import com.moduletwo.innopolis.model.entity.RoleEntity;
import com.moduletwo.innopolis.model.entity.UserDataEntity;
import com.moduletwo.innopolis.model.entity.UserEntity;
import com.moduletwo.innopolis.repository.UserDataRepository;
import com.moduletwo.innopolis.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class RegistrationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserDataRepository userDataRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Boolean userRegistration(UserDto userDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDto.getUsername());
        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userEntity.setRoles(Arrays.asList(new RoleEntity("ROLE_USER")));
        userEntity.setActivity(true);
        Long id =  userRepository.save(userEntity).getId();
        if(id == null) {
            return false;
        }
        UserDataEntity userDataEntity = new UserDataEntity();
        userDataEntity.setName(userDto.getName());
        userDataEntity.setLastName(userDto.getLastName());
        userDataEntity.setMiddleName(userDto.getMiddleName());
        userDataEntity.setBirthday(userDto.getBirthday());
        userDataEntity.setPolis(userDto.getPolis());
        userDataEntity.setIdUser(id);
        return userDataRepository.saveAndFlush(userDataEntity).getId() == null ? false : true;
    }

    public Boolean checkUsername(String username) {
        return userRepository.findByUsername(username) == null  ? true : false;
    }
}
