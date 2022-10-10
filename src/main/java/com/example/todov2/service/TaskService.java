package com.example.todov2.service;

import com.example.todov2.entity.Task;
import com.example.todov2.repository.TaskRepository;
import com.example.todov2.search.TaskSearchValues;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class TaskService {

    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task findById(long id) {
        return taskRepository.findById(id).get();
    }

    @Transactional
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Transactional
    public void delete(long id) {
        taskRepository.deleteById(id);
    }

    public List<Task> search(TaskSearchValues taskSearchValues) {
        return taskRepository.findByParam(taskSearchValues.getTitle(), taskSearchValues.getCompleted(),
                taskSearchValues.getPriorityId(), taskSearchValues.getCategoryId());
    }
}
