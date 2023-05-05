package com.example.OlympicDatabase.util.mappers;

import com.example.OlympicDatabase.auth.User;
import com.example.OlympicDatabase.dto.response.ResponseAllUserDetailsDTO;
import com.example.OlympicDatabase.dto.response.ResponseUserDetailsDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMappers {
    ResponseUserDetailsDTO entityToDTO(User user);

    List<ResponseAllUserDetailsDTO> EntitytoToDTOList(List<User> users);
}
