package com.example.OlympicDatabase.repo;

import com.example.OlympicDatabase.entity.Videos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VideoRepo extends JpaRepository<Videos,Long> {


    Optional<Videos> findVideosByTitleEquals(String title);
}
