package com.example.OlympicDatabase.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseUserDetailsDTO {
    private Long id;
    private String username;
    private String password;
    private String region;
    private String contactNumber;
}
