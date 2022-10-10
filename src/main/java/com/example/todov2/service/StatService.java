package com.example.todov2.service;

import com.example.todov2.entity.Stat;
import com.example.todov2.repository.StatRepository;
import org.springframework.stereotype.Service;

@Service
public class StatService {

    private StatRepository statRepository;

    public StatService(StatRepository statRepository) {
        this.statRepository = statRepository;
    }

    public Stat findById(long id) {
        return statRepository.findById(id).get();
    }
}
