package com.example.teampredictionworldcup.service;

import com.example.teampredictionworldcup.model.Stadium;
import com.example.teampredictionworldcup.repository.StadiumRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class StadiumService {
    private final StadiumRepository stadiumRepository;

    public Stadium getById(int id) {
        return stadiumRepository.findById(id).orElse(null);
    }
}
