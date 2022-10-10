package com.example.todov2.controller;

import com.example.todov2.entity.Stat;
import com.example.todov2.service.StatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stat")
public class StatController {

    private final long defaultId = 1;

    private StatService statService;

    public StatController(StatService statService) {
        this.statService = statService;
    }

    @GetMapping("/id")
    public ResponseEntity<Stat> findById() {
        return ResponseEntity.ok(statService.findById(defaultId));
    }
}
