package com.example.OlympicDatabase.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "videos")
public class Videos {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private String status;
    private String filePath;
    private String videoLink;

    public Videos(String title, String description, String status, String filePath, String videoLink) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.filePath = filePath;
        this.videoLink = videoLink;
    }
}
