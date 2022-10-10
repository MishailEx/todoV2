package com.example.todov2.controller;

import com.example.todov2.entity.Task;
import com.example.todov2.search.TaskSearchValues;
import com.example.todov2.service.TaskService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/task")
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> findById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.findById(id));
    }

    @PostMapping
    public ResponseEntity add(@RequestBody Task task) {

        if (task.getTitle() == null || task.getTitle().trim().length() == 0) {
            return new ResponseEntity("title must not be empty", HttpStatus.BAD_REQUEST);
        }

        if (task.getId() != 0) {
            return new ResponseEntity("id must not be filled", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(taskService.save(task));
    }

    @PutMapping("/update")
    public ResponseEntity<Task> update(@RequestBody Task task) {
        if (task.getId() == 0) {
            return new ResponseEntity("missed param: id", HttpStatus.BAD_REQUEST);
        }

        if (task.getTitle() == null) {
            return new ResponseEntity("missed param: title", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(taskService.save(task));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable long id) {
        taskService.delete(id);
        return new  ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/search")
    public List<Task> search(@RequestBody TaskSearchValues searchValues) {
        return taskService.search(searchValues);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity noSuchException() {
        return new ResponseEntity("fail, this id was not found", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity EmptyResultDataAccessException() {
        return new ResponseEntity("fail, this id was not found", HttpStatus.BAD_REQUEST);
    }
}
