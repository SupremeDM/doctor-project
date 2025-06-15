package com.moduletwo.innopolis.auth.service;

import com.moduletwo.innopolis.model.dto.UserDto;
import com.moduletwo.innopolis.model.entity.UserDataEntity;
import com.moduletwo.innopolis.model.entity.UserEntity;
import com.moduletwo.innopolis.repository.UserDataRepository;
import com.moduletwo.innopolis.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {

    private UserRepository userRepository;
    private UserDataRepository userDataRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setUserDataRepository(UserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
    }

    public UserDto getUserInfo() {
        UserDto userDto = new UserDto();
        UserEntity userEntity = userRepository.
                findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        UserDataEntity userDataEntity = userDataRepository.findUserData(userEntity.getId());
        userDto.setId(userEntity.getId());
        userDto.setName(userDataEntity.getName());
        userDto.setLastName(userDataEntity.getLastName());
        userDto.setMiddleName(userDataEntity.getMiddleName());
        userDto.setBirthday(userDataEntity.getBirthday());
        userDto.setPolis(userDataEntity.getPolis());
        userDto.setAuthorities(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        return userDto;
    }
}
