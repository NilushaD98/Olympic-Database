package com.example.OlympicDatabase.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1")
@CrossOrigin(origins = "*",methods = {RequestMethod.PUT,RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST})
public class LogoutController {

    @GetMapping("/logout")
    public ResponseEntity<String> loginPage(@RequestParam(required = false) boolean logout) {
        if (logout) {
            return ResponseEntity.ok("You have been logged out successfully");
        }
        return ResponseEntity.ok("Welcome to the login page");
    }

}
