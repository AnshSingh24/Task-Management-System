package com.ansh.taskmanager.service;

import com.ansh.taskmanager.dto.TaskDTO;
import com.ansh.taskmanager.entity.Task;
import com.ansh.taskmanager.entity.TaskStatus;
import com.ansh.taskmanager.entity.User;
import com.ansh.taskmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    public Task createTask(TaskDTO dto, String userEmail) {
        User user = userService.findByEmail(userEmail);

        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setDueDate(dto.getDueDate());
        task.setStatus(dto.getStatus() != null ? dto.getStatus() : TaskStatus.PENDING);
        task.setPriority(dto.getPriority());
        task.setUser(user);

        return taskRepository.save(task);
    }

    public List<Task> getUserTasks(String userEmail) {
        User user = userService.findByEmail(userEmail);
        return taskRepository.findByUserId(user.getId());
    }

    public Task updateTask(Long taskId, TaskDTO dto, String userEmail)
    {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        //Make sure user can only change their own tasks
        if (!task.getUser().getEmail().equals(userEmail)) {
            throw new RuntimeException("You are not authorized to update this task");
        }

        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setDueDate(dto.getDueDate());
        task.setStatus(dto.getStatus());
        task.setPriority(dto.getPriority());

        return taskRepository.save(task);
    }

    public void deleteTask(Long taskId, String userEmail)
    {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        //Make sure user can only delete their own tasks
        if (!task.getUser().getEmail().equals(userEmail)) {
            throw new RuntimeException("You are not authorized to delete this task");
        }

        taskRepository.delete(task);
    }

    public Task updateStatus(Long taskId, TaskStatus status, String userEmail) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        //Make sure user can only change their own tasks
        if (!task.getUser().getEmail().equals(userEmail)) {
            throw new RuntimeException("You are not authorized to update this task");
        }

        task.setStatus(status);
        return taskRepository.save(task);
    }
    
}
