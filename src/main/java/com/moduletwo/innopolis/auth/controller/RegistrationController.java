package com.moduletwo.innopolis.auth.controller;


import com.moduletwo.innopolis.auth.service.RegistrationService;
import com.moduletwo.innopolis.factory.MainControllerInterface;
import com.moduletwo.innopolis.model.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController extends MainControllerInterface {
    @Autowired
    private RegistrationService registratotionService;

    @PostMapping("/registration")
    public ResponseEntity<Object> registrationUser(@RequestBody UserDto userDto) {
        if(!checkUserRegistration(userDto)) {
            return new ResponseEntity<>(HttpStatus.valueOf(500));
        }

        if(!registratotionService.checkUsername(userDto.getUsername())) {
            return new ResponseEntity<>("Имя пользователя занято", HttpStatus.valueOf(500));
        }

        Boolean isRegUser = registratotionService.userRegistration(userDto);
        if(isRegUser) {
            return new ResponseEntity<>(HttpEntity.EMPTY, HttpStatus.valueOf(200));
        } else {
            return new ResponseEntity<>(HttpEntity.EMPTY, HttpStatus.valueOf(505));
        }
    }

    private Boolean checkUserRegistration(UserDto userDto) {
        return checkFieldFromFrontend(userDto.getLastName()) &&
                checkFieldFromFrontend(userDto.getName()) &&
                checkFieldFromFrontend(userDto.getUsername()) &&
                checkFieldFromFrontend(userDto.getPassword()) &&
                checkFieldFromFrontend(userDto.getBirthday());
    }
}
