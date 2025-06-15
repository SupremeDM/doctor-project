package com.moduletwo.innopolis.service;

import com.moduletwo.innopolis.model.entity.RoleEntity;
import com.moduletwo.innopolis.model.entity.UserEntity;
import com.moduletwo.innopolis.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserDetailsService {
    private UserRepository userRepository;
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = this.findByUsername(username);
        if(userEntity == null) {
            throw new UsernameNotFoundException(String.format("User %s not found", username));
        }
        return new User(userEntity.getUsername(), userEntity.getPassword(),
                mapRolesToAuthorities(userEntity.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<RoleEntity> roles) {
        return roles.stream().map(i ->
                new SimpleGrantedAuthority(i.getName())).collect(Collectors.toList());
    }
}
