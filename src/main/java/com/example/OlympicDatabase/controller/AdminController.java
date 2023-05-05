package com.example.OlympicDatabase.controller;

import com.example.OlympicDatabase.dto.request.RequestVideoSaveDTO;
import com.example.OlympicDatabase.dto.response.ResponseVideoDeleteStatus;
import com.example.OlympicDatabase.dto.response.ResponseVideoDetailsDTO;
import com.example.OlympicDatabase.service.AdminService;
import com.example.OlympicDatabase.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/admins/")
@CrossOrigin(origins = "*",methods = {RequestMethod.PUT,RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST})
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping(
            path = {"videoSave"}
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<StandardResponse> videoSave(@RequestBody RequestVideoSaveDTO requestVideoSaveDTO){
        String videoSaveStatuse = adminService.videoSave(requestVideoSaveDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Video Save Status ",videoSaveStatuse), HttpStatus.OK
        );
    }
    @GetMapping(
            path = {"getAllVideos"}
    )
    public ResponseEntity<StandardResponse> getAllVideos(){
        List<ResponseVideoDetailsDTO> responseVideoDetailsDTOS = adminService.getAllVideos();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"All Videos : ",responseVideoDetailsDTOS),HttpStatus.OK
        );
    }
    @DeleteMapping(
            path = {"deleteVideoByID"},
            params = {"videoID"}
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<StandardResponse> deleteVideoByID(@RequestParam(value = "videoID") Long videoID){
        ResponseVideoDeleteStatus responseVideoDeleteStatus = adminService.deleteVideo(videoID);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Video deleted status : ",responseVideoDeleteStatus),HttpStatus.OK
        );
    }

}
