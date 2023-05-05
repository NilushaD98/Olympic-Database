package com.example.OlympicDatabase.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestLocalUserSaveDTO {

    private String username;
    private String password;
    private String contactNumber;
}
