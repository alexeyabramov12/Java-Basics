package main;


import main.model.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import main.model.Task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @PostMapping("/tasks/")
    public ResponseEntity<Task> add (Task task) {
        if (task == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        task.setCreationTime(LocalDateTime.now());
        task.setDone(false);
        Task saveTask = taskRepository.save(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveTask);
    }

    @GetMapping("/tasks/")
    public List<Task> list() {
        return StreamSupport.stream(taskRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> get(@PathVariable int id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(optionalTask.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @DeleteMapping("/tasks/{id}")
    private ResponseEntity<Task> delete(@PathVariable int id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            taskRepository.delete(optionalTask.get());
            return ResponseEntity.status(HttpStatus.OK).body(optionalTask.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PatchMapping("/tasks/{id}")
    private ResponseEntity<Task> update(@PathVariable int id,
                                        @RequestParam(required = false) Boolean isDone,
                                        @RequestParam(required = false) String title,
                                        @RequestParam(required = false) String description) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task updateTask = optionalTask.get();
            if (isDone != null && updateTask.isDone() != isDone) {
                updateTask.setDone(isDone);
            }
            if (title != null && !updateTask.getTitle().equals(title)) {
                updateTask.setTitle(title);
            }
            if (description != null && !updateTask.getDescription().equals(description)) {
                updateTask.setDescription(description);
            }
            taskRepository.save(updateTask);
            return ResponseEntity.status(HttpStatus.OK).body(updateTask);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
