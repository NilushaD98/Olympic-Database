package com.example.OlympicDatabase.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestVideoSaveDTO {
    private String title;
    private String description;
    private String status;
    private String filePath;
    private String videoLink;


}
