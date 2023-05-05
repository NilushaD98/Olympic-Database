package com.example.OlympicDatabase.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestInternationlUserDTO {

    private String username;
    private String password;
    private String country;
    private String contactNumber;


}
