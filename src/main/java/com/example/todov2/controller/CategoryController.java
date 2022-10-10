package com.example.todov2.controller;

import com.example.todov2.entity.Category;
import com.example.todov2.search.CategorySearchValues;
import com.example.todov2.service.CategoryService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<Category>> findAll() {
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Category> add(@RequestBody Category category) {

        if (category.getTitle() == null || category.getTitle().trim().length() == 0) {
            return new ResponseEntity("title must not be empty", HttpStatus.BAD_REQUEST);
        }

        if (categoryService.findByTitle(category.getTitle()) != null) {
            return new ResponseEntity("category with the same name already exists", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(categoryService.save(category));
    }

    @PutMapping("/update")
    public ResponseEntity<Category> update(@RequestBody Category category) {
        if (category.getId() == 0) {
            return new ResponseEntity("missed param: id", HttpStatus.BAD_REQUEST);
        }

        if (category.getTitle() == null) {
            return new ResponseEntity("missed param: title", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(categoryService.save(category));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable long id) {
        categoryService.delete(id);
        return new  ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/search")
    public List<Category> search(@RequestBody CategorySearchValues searchValues) {
        return categoryService.search(searchValues);
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
