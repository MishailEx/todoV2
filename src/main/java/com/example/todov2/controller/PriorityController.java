package com.example.todov2.controller;

import com.example.todov2.entity.Priority;
import com.example.todov2.service.PriorityService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/priority")
public class PriorityController {

    private PriorityService priorityService;

    public PriorityController(PriorityService priorityService) {
        this.priorityService = priorityService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Priority> findById(@PathVariable Long id) {
        return ResponseEntity.ok(priorityService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Priority> add(@RequestBody Priority priority) {

        if (priority.getTitle() == null || priority.getTitle().trim().length() == 0) {
            return new ResponseEntity("title must not be empty", HttpStatus.BAD_REQUEST);
        }

        if (priority.getColor() == null || priority.getColor().trim().length() == 0) {
            return new ResponseEntity("color must not be empty", HttpStatus.BAD_REQUEST);
        }

        if (priorityService.findByTitle(priority.getTitle()) != null) {
            return new ResponseEntity("Priority with the same name already exists", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(priorityService.save(priority));
    }

    @PutMapping("/update")
    public ResponseEntity<Priority> update(@RequestBody Priority priority) {
        if (priority.getId() == 0) {
            return new ResponseEntity("missed param: id", HttpStatus.BAD_REQUEST);
        }

        if (priority.getTitle() == null) {
            return new ResponseEntity("missed param: title", HttpStatus.BAD_REQUEST);
        }

        if (priority.getColor() == null) {
            return new ResponseEntity("missed param: color", HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(priorityService.save(priority));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable long id) {
        priorityService.delete(id);
        return new  ResponseEntity(HttpStatus.OK);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity EmptyResultDataAccessException() {
        return new ResponseEntity("fail, this id was not found", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity noSuchException() {
        return new ResponseEntity("fail, this id was not found", HttpStatus.BAD_REQUEST);
    }
}
