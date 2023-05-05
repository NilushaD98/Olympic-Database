package com.example.OlympicDatabase.controller;

import com.example.OlympicDatabase.dto.request.RequestUserUpdateDTO;
import com.example.OlympicDatabase.dto.response.ResponseAllUserDetailsDTO;
import com.example.OlympicDatabase.dto.response.ResponseUserDetailsDTO;
import com.example.OlympicDatabase.service.UserService;
import com.example.OlympicDatabase.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/users/")
@CrossOrigin(origins = "*",methods = {RequestMethod.PUT,RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST})
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(
            path={"getUserDetailsByUserName"},
            params = {"userName"}
    )
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<StandardResponse> getUserDetailsByUserName(@RequestParam(value = "userName")String userName){
        ResponseUserDetailsDTO responseUserDetailsDTO = userService.getUserDetailsByUserName(userName);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"User Details :", responseUserDetailsDTO), HttpStatus.OK
        );
    }
    @DeleteMapping(
            path = {"deleteUserByUserID"},
            params = {"userID"}
    )@PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<StandardResponse> deleteUserByUserName(@RequestParam(value = "userID")long userID){
        String deleteStatus = userService.deleteUserByUserName(userID);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"User delete Status: ", deleteStatus),HttpStatus.OK
        );
    }
    @PutMapping(
            path = {"updateUserByUserId"}
    )
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<StandardResponse> updateUserByUserId(@RequestBody RequestUserUpdateDTO requestUserUpdateDTO){
        String userUpdateStatus = userService.updateUser(requestUserUpdateDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"User Update Status : ",userUpdateStatus),HttpStatus.OK
        );
    }

    @GetMapping(
            path = {"getAllUsers"}
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<StandardResponse> getAllUsers(){
        List<ResponseAllUserDetailsDTO> responseAllUserDetailsDTOS = userService.getAllUsers();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"All User Details", responseAllUserDetailsDTOS),HttpStatus.OK
        );
    }

}
