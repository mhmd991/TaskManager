package com.mhmd991.taskmanager.controller;

import com.mhmd991.taskmanager.model.Task;
import com.mhmd991.taskmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TaskController {

    @Autowired  // This connects to our repository automatically
    private TaskRepository taskRepository;

    // Show all tasks on the main page
    @GetMapping("/")
    public String home(Model model) {
        List<Task> allTasks = taskRepository.findAll();
        long completedCount = allTasks.stream()
                .filter(Task::isCompleted)
                .count();

        model.addAttribute("tasks", allTasks);
        model.addAttribute("completedCount", completedCount);
        model.addAttribute("newTask", new Task());
        return "index";
    }

    // Add a new task
    @PostMapping("/tasks/add")
    public String addTask(@ModelAttribute Task task) {
        taskRepository.save(task);
        return "redirect:/";  // Go back to home page
    }

    // Toggle task completion
    @GetMapping("/tasks/toggle/{id}")
    public String toggleTask(@PathVariable Long id) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task != null) {
            task.setCompleted(!task.isCompleted());  // Flip the status
            taskRepository.save(task);
        }
        return "redirect:/";
    }

    // Delete a task
    @GetMapping("/tasks/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskRepository.deleteById(id);
        return "redirect:/";
    }

}