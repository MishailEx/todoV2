package com.example.todov2.repository;

import com.example.todov2.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("select t from Task t where :title is null or :title = ''" +
            " or lower(t.title) like lower(concat('%', :title, '%')) and " +
            "(:completed is null or t.completed = :completed) and " +
            "(:priorityId is null or t.priority = :priorityId) and " +
            "(:categoryId is null or t.category = :categoryId)")
    List<Task> findByParam(@Param("title") String title, @Param("completed") Integer completed,
                           @Param("priorityId") Long priorityId, @Param("categoryId") Long categoryId);
}
