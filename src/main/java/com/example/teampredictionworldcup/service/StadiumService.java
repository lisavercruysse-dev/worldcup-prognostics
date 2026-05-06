package com.example.teampredictionworldcup.service;

import com.example.teampredictionworldcup.dto.response.StadiumDTO;
import com.example.teampredictionworldcup.dto.response.StadiumInputDTO;
import com.example.teampredictionworldcup.model.Stadium;
import com.example.teampredictionworldcup.repository.StadiumRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class StadiumService {
    private final StadiumRepository stadiumRepository;

    public Stadium getById(int id) {
        return stadiumRepository.findById(id).orElse(null);
    }

    public List<StadiumDTO> getAllStadiums() {
        List<Stadium> stadiums = stadiumRepository.findAll();
        return stadiums.stream().map(s -> new StadiumDTO(s.getName(), s.getCity(), s.getStadiumCode(), s.getChecksum(), s.getCapacity())).toList();
    }

    public void save(StadiumInputDTO dto) {
        Stadium stadium = new Stadium(dto.code(), dto.name(), dto.city(), dto.capacity());
        stadiumRepository.save(stadium);
    }
}
