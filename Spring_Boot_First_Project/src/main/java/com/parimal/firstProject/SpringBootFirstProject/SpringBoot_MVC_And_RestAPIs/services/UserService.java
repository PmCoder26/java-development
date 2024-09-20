package com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.services;


import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.dto.LoginDTO;
import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.dto.SignUpDTO;
import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.dto.UserDTO;
import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.entities.UserEntity;
import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.exceptions.ResourceNotFoundException;
import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new BadCredentialsException("User with " + username + " not found!"));
    }

    public UserEntity getUserById(Long userId){
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with id: " + userId + " not found!"));
    }

    public UserEntity getUserByEmail(String email){
        return userRepository.findByEmail(email).orElse(null);
    }

    public UserDTO signUp(SignUpDTO signUpDTO) {
        Optional<UserEntity> user = userRepository.findByEmail(signUpDTO.getEmail());
        if(user.isPresent()){
            throw new BadCredentialsException("User with this email already exists!");
        }
        else{
             UserEntity toCreate = modelMapper.map(signUpDTO, UserEntity.class);
             toCreate.setPassword(passwordEncoder.encode(toCreate.getPassword()));
             UserEntity savedUser = userRepository.save(toCreate);
             return modelMapper.map(savedUser, UserDTO.class);
        }
    }

    public List<UserDTO> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        List<UserDTO> userDTOS = users.stream()
                .map(entity -> modelMapper.map(entity, UserDTO.class))
                .toList();
        return userDTOS;
    }


    public UserEntity save(UserEntity user) {
        return userRepository.save(user);
    }
}
