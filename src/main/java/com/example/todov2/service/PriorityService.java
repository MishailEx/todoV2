package com.example.todov2.service;

import com.example.todov2.entity.Priority;
import com.example.todov2.repository.PriorityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PriorityService {

    private PriorityRepository priorityRepository;

    public PriorityService(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    @Transactional
    public Priority save(Priority priority) {
        if (priorityRepository.findByTitle(priority.getTitle()) != null) {
            return null;
        }
        return priorityRepository.save(priority);
    }

    public Priority findById(Long id) {
        return priorityRepository.findById(id).get();
    }

    public Priority findByTitle(String title) {
        return priorityRepository.findByTitle(title);
    }

    @Transactional
    public void delete(long id) {
        priorityRepository.deleteById(id);
    }
}
