package com.example.OlympicDatabase.service;

import com.example.OlympicDatabase.dto.request.RequestVideoSaveDTO;
import com.example.OlympicDatabase.dto.response.ResponseVideoDeleteStatus;
import com.example.OlympicDatabase.dto.response.ResponseVideoDetailsDTO;

import java.util.List;

public interface AdminService {
    String videoSave(RequestVideoSaveDTO requestVideoSaveDTO);

    List<ResponseVideoDetailsDTO> getAllVideos();

    ResponseVideoDeleteStatus deleteVideo(Long videoID);


}
