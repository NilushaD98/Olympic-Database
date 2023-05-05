package com.example.OlympicDatabase.service.IMPL;

import com.example.OlympicDatabase.dto.request.RequestVideoSaveDTO;
import com.example.OlympicDatabase.dto.response.ResponseVideoDeleteStatus;
import com.example.OlympicDatabase.dto.response.ResponseVideoDetailsDTO;
import com.example.OlympicDatabase.entity.Videos;
import com.example.OlympicDatabase.exception.VideoAlreadyExistsException;
import com.example.OlympicDatabase.exception.VideoNotFoundException;
import com.example.OlympicDatabase.repo.VideoRepo;
import com.example.OlympicDatabase.service.AdminService;
import com.example.OlympicDatabase.util.mappers.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceIMPL implements AdminService {

    @Autowired
    private VideoRepo videoRepo;

    @Autowired
    private AdminMapper adminMapper;


    @Override
    public String videoSave(RequestVideoSaveDTO requestVideoSaveDTO) {
        Optional<Videos> videos = videoRepo.findVideosByTitleEquals(requestVideoSaveDTO.getTitle());
        if(!videos.isPresent()){
            Videos videos1 = new Videos(
                    requestVideoSaveDTO.getTitle(),
                    requestVideoSaveDTO.getDescription(),
                    requestVideoSaveDTO.getStatus(),
                    requestVideoSaveDTO.getFilePath(),
                    requestVideoSaveDTO.getVideoLink()
            );
            return  videoRepo.save(videos1).getTitle()+ " saved";
        }else {
            throw new VideoAlreadyExistsException();
        }
    }
    @Override
    public List<ResponseVideoDetailsDTO> getAllVideos() {

        List<Videos> videos = videoRepo.findAll();
        List<ResponseVideoDetailsDTO> responseVideoDetailsDTOS = adminMapper.getVideosEntityToDTO(videos);
        return  responseVideoDetailsDTOS;
    }
    @Override
    public ResponseVideoDeleteStatus deleteVideo(Long videoID) {

        Optional<Videos> videos = videoRepo.findById(videoID);
        if(videos.isPresent()){
            ResponseVideoDeleteStatus responseVideoDeleteStatus = new ResponseVideoDeleteStatus(
                    videoRepo.findById(videoID).get().getTitle(),
                    videos.get().getVideoLink()
            );
            videoRepo.deleteById(videoID);
            return responseVideoDeleteStatus;
        }else {
            throw new VideoNotFoundException();
        }
    }
}
