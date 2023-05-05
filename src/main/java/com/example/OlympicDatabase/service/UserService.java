package com.example.OlympicDatabase.service;

import com.example.OlympicDatabase.dto.request.RequestInternationlUserDTO;
import com.example.OlympicDatabase.dto.request.RequestLocalUserSaveDTO;
import com.example.OlympicDatabase.dto.request.RequestUserUpdateDTO;
import com.example.OlympicDatabase.dto.response.ResponseAllUserDetailsDTO;
import com.example.OlympicDatabase.dto.response.ResponseUserDetailsDTO;

import java.util.List;

public interface UserService {
    String addLocalUser(RequestLocalUserSaveDTO requestLocalUserSaveDTO);

    String addInternationalUser(RequestInternationlUserDTO requestInternationlUserDTO);

    Long getUserID(String username);

    ResponseUserDetailsDTO getUserDetailsByUserName(String userName);

    String deleteUserByUserName(long userID);

    String updateUser(RequestUserUpdateDTO requestUserUpdateDTO);

    List<ResponseAllUserDetailsDTO> getAllUsers();
}
