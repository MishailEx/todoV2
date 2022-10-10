package com.example.todov2.repository;

import com.example.todov2.entity.Stat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatRepository extends JpaRepository<Stat, Long> {
}
