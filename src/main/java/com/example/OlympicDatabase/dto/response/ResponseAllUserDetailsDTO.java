package com.example.OlympicDatabase.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseAllUserDetailsDTO {
    private Long id;
    private String username;
    private String region;
    private String contactNumber;
}
