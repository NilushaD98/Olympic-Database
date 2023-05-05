package com.example.OlympicDatabase.util.mappers;

import com.example.OlympicDatabase.dto.request.RequestVideoSaveDTO;
import com.example.OlympicDatabase.dto.response.ResponseVideoDetailsDTO;
import com.example.OlympicDatabase.entity.Videos;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdminMapper {
    List<ResponseVideoDetailsDTO> getVideosEntityToDTO(List<Videos> videos);
}
