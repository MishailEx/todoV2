package com.example.todov2.repository;

import com.example.todov2.entity.Priority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriorityRepository extends JpaRepository<Priority, Long> {

    Priority findByTitle(String title);
}
