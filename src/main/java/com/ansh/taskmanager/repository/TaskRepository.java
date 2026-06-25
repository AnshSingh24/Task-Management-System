package com.ansh.taskmanager.repository;

import com.ansh.taskmanager.entity.Task;
import com.ansh.taskmanager.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUserId(Long userID);
    List<Task> findByUserIdAndStatus(Long userID, TaskStatus status);
}
