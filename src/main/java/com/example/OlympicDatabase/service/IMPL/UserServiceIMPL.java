package com.example.OlympicDatabase.service.IMPL;

import com.example.OlympicDatabase.auth.User;
import com.example.OlympicDatabase.dto.request.RequestInternationlUserDTO;
import com.example.OlympicDatabase.dto.request.RequestLocalUserSaveDTO;
import com.example.OlympicDatabase.dto.request.RequestUserUpdateDTO;
import com.example.OlympicDatabase.dto.response.ResponseAllUserDetailsDTO;
import com.example.OlympicDatabase.dto.response.ResponseUserDetailsDTO;
import com.example.OlympicDatabase.exception.UserIsAdminException;
import com.example.OlympicDatabase.exception.UserNameDuplicationException;
import com.example.OlympicDatabase.exception.UserNotFoundException;
import com.example.OlympicDatabase.repo.UserRepository;
import com.example.OlympicDatabase.service.UserService;
import com.example.OlympicDatabase.util.mappers.UserMappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceIMPL implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMappers userMappers;


    @Override
    public String addLocalUser(RequestLocalUserSaveDTO requestLocalUserSaveDTO) {
        String encodedPassword = passwordEncoder.encode(requestLocalUserSaveDTO.getPassword());
        Optional<User> user = Optional.ofNullable(userRepository.findByUsername(requestLocalUserSaveDTO.getUsername()));
        if(user.isPresent()){
            throw  new UserNameDuplicationException();
        }else {
            User user1 = new User(
                    requestLocalUserSaveDTO.getUsername(),
                    encodedPassword,
                    "USER",
                    "Japan",
                    requestLocalUserSaveDTO.getContactNumber()
            );
            return userRepository.save(user1).getUsername() + " Saved";
        }
    }

    @Override
    public String addInternationalUser(RequestInternationlUserDTO requestInternationlUserDTO) {
        String encodedPassword = passwordEncoder.encode(requestInternationlUserDTO.getPassword());
        Optional<User> user = Optional.ofNullable(userRepository.findByUsername(requestInternationlUserDTO.getUsername()));
        if(!user.isPresent()){
            User user1 = new User(
                    requestInternationlUserDTO.getUsername(),
                    encodedPassword,
                    "USER",
                    requestInternationlUserDTO.getCountry(),
                    requestInternationlUserDTO.getContactNumber()
            );
            return userRepository.save(user1).getUsername()+" Saved ";
        }else {
            throw new UserNameDuplicationException();
        }
    }

    @Override
    public Long getUserID(String username) {
        Optional<User> user1 = Optional.ofNullable(userRepository.findByUsername(username));
        if(user1.isPresent()){
            return userRepository.findByUsername(username).getId();
        }else {
            throw new UserNotFoundException();
        }
    }

    @Override
    public ResponseUserDetailsDTO getUserDetailsByUserName(String userName) {
        Optional<User> user1 = Optional.ofNullable(userRepository.findByUsername(userName));
        if(user1.isPresent()){
            User user = userRepository.findByUsername(userName);
            ResponseUserDetailsDTO responseUserDetailsDTO = userMappers.entityToDTO(user);
            return responseUserDetailsDTO;
        }else {
            throw new UserNotFoundException();
        }
    }

    @Override
    public String deleteUserByUserName(long userID) {
        Optional<User> user = userRepository.findById(userID);
        User user1 = null;
        if((user.get().getRole()).equals("USER")){
            if(user.isPresent()){
                user1 = userRepository.getById(userID);
                userRepository.deleteById(userID);
                return user1.getUsername()+" deleted";
            }else {
                throw new UserNotFoundException();
            }
        }else {
            throw new UserIsAdminException();
        }
    }

    @Override
    public String updateUser(RequestUserUpdateDTO requestUserUpdateDTO) {
        Optional<User> user = userRepository.findById(requestUserUpdateDTO.getId());

        if (user.isPresent()){
            User user1 = userRepository.getById(requestUserUpdateDTO.getId());
            user1.setUsername(requestUserUpdateDTO.getUsername());
            user1.setRegion(requestUserUpdateDTO.getRegion());
            user1.setContactNumber(requestUserUpdateDTO.getContactNumber());
            userRepository.save(user1);
            return requestUserUpdateDTO.getUsername()+" updated";
        }else {
            throw new UserNotFoundException();
        }
    }

    @Override
    public List<ResponseAllUserDetailsDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<ResponseAllUserDetailsDTO> responseAllUserDetailsDTOS = userMappers.EntitytoToDTOList(users);
        return responseAllUserDetailsDTOS;
    }
}
