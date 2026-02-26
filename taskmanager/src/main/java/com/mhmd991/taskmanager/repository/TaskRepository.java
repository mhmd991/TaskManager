package com.mhmd991.taskmanager.repository;

import com.mhmd991.taskmanager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    // Spring automatically creates methods like:
    // - save(task) - saves a task
    // - findAll() - gets all tasks
    // - findById(id) - gets one task
    // - deleteById(id) - deletes a task
    // You don't need to write these! Spring does it for you!
}