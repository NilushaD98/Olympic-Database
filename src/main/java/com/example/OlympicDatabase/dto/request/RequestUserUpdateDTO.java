package com.example.OlympicDatabase.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestUserUpdateDTO {
    private Long id;
    private String username;
    private String region;
    private String contactNumber;
}
