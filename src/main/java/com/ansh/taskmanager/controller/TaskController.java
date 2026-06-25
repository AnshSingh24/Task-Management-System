package com.ansh.taskmanager.controller;

import com.ansh.taskmanager.dto.TaskDTO;
import com.ansh.taskmanager.entity.Task;
import com.ansh.taskmanager.entity.TaskStatus;
import com.ansh.taskmanager.service.TaskService;
import jakarta.validation.Valid;

import org.aspectj.internal.lang.annotation.ajcDeclareAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;

@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {
        List<Task> tasks = taskService.getUserTasks(principal.getName());
        model.addAttribute("tasks", tasks);
        model.addAttribute("taskDTO", new TaskDTO());
        model.addAttribute("statuses", TaskStatus.values());
        return "dashboard";
    }

    @PostMapping("/tasks/create")
    public String createTask(@Valid @ModelAttribute("taskDTO") TaskDTO dto,
                                BindingResult result, Model model, Principal principal) {
            if (result.hasErrors()) {
                List<Task> tasks = taskService.getUserTasks(principal.getName());
                model.addAttribute("tasks", tasks);
                model.addAttribute("statuses", TaskStatus.values());
                return "dashboard";
            }
            taskService.createTask(dto, principal.getName());
            return "redirect:/dashboard";
        }
    
    @GetMapping("/tasks/edit/{id}")
    public String showEditTaskPage(@PathVariable Long id, Model model, Principal principal) {
        List<Task> tasks = taskService.getUserTasks(principal.getName());
        Task task = tasks.stream().filter(t -> t.getId().equals(id)).findFirst().orElseThrow(() -> new RuntimeException("Task not found"));

        TaskDTO dto =new TaskDTO();
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setDueDate(task.getDueDate());
        dto.setPriority(task.getPriority());
        dto.setStatus(task.getStatus());

        model.addAttribute("taskDTO", dto);
        model.addAttribute("taskId", id);
        model.addAttribute("statuses", TaskStatus.values());
        return "edit_task";
    }

    @PostMapping("/tasks/edit/{id}")
    public String updateTask(@PathVariable Long id, @Valid @ModelAttribute("taskDTO") TaskDTO dto,
                             BindingResult result, Model model, Principal principal) {
        if (result.hasErrors()) {
            model.addAttribute("taskId", id);
            model.addAttribute("statuses", TaskStatus.values());
            return "edit_task";
        }
        taskService.updateTask(id, dto, principal.getName());
        return "redirect:/dashboard";
    }

    @GetMapping("/tasks/delete/{id}")
    public String deleteTask(@PathVariable Long id, Principal principal) {
        taskService.deleteTask(id, principal.getName());
        return "redirect:/dashboard";
    }

    @GetMapping("/tasks/status/{id}/{status}")
    public String updateStatus(@PathVariable Long id, @PathVariable TaskStatus status, Principal principal) {
        taskService.updateStatus(id, status, principal.getName());
        return "redirect:/dashboard";
    }
}
