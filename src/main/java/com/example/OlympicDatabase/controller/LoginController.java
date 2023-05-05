package com.example.OlympicDatabase.controller;

import com.example.OlympicDatabase.auth.CustomUserDetailsService;
import com.example.OlympicDatabase.dto.LoginResponse;
import com.example.OlympicDatabase.dto.UserLoginDTO;
import com.example.OlympicDatabase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
@CrossOrigin(origins = "*",methods = {RequestMethod.PUT,RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST})
public class LoginController {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private UserService userService;

    @PostMapping(value="/login",consumes = {"application/xml","application/json"})
    public ResponseEntity<?> login(@RequestBody UserLoginDTO loginDto) {

        try {
            Authentication authentication = customUserDetailsService.authenticateUser(loginDto.getUserName(), loginDto.getPassword());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(loginDto.getUserName());
            String role = userDetails.getAuthorities().iterator().next().getAuthority();
            Long loggedID = userService.getUserID(userDetails.getUsername());
            return ResponseEntity.ok(new LoginResponse(role, userDetails.getUsername(), loggedID));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}