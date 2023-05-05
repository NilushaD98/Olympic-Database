package com.example.OlympicDatabase.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseVideoDetailsDTO {

    private Long id;
    private String title;
    private String description;
    private String status;
    private String filePath;
    private String videoLink;
}
