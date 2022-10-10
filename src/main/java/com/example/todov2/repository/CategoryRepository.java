package com.example.todov2.repository;

import com.example.todov2.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByTitle(String title);

    @Query("select c from Category c where " +
            ":title is null or :title = '' " +
            "or lower(c.title) like lower(concat('%', :title, '%') ) order by c.title asc ")
    List <Category> findByTitleAll(@Param("title") String title);
}
