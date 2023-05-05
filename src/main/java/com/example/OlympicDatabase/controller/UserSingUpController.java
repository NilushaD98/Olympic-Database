package com.example.OlympicDatabase.controller;

import com.example.OlympicDatabase.dto.request.RequestInternationlUserDTO;
import com.example.OlympicDatabase.dto.request.RequestLocalUserSaveDTO;
import com.example.OlympicDatabase.service.UserService;
import com.example.OlympicDatabase.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/usersSingup/")
@CrossOrigin(origins = "*",methods = {RequestMethod.PUT,RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST})
public class UserSingUpController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "saveLocalUser")
    public ResponseEntity<StandardResponse> saveLocalUser(@RequestBody RequestLocalUserSaveDTO requestLocalUserSaveDTO){
        String userSaveStatus = userService.addLocalUser(requestLocalUserSaveDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"User Save Status : ",userSaveStatus), HttpStatus.ACCEPTED
        );
    }
    @PostMapping(path = "saveInternationalUser")
    public ResponseEntity<StandardResponse> saveInternationalUse(@RequestBody RequestInternationlUserDTO requestInternationlUserDTO){
        String InternationalUserSaveStatus = userService.addInternationalUser(requestInternationlUserDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"User Save Status : ",InternationalUserSaveStatus), HttpStatus.OK
        );
    }

}
