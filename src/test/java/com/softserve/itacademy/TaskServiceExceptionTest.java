package com.softserve.itacademy;

import com.softserve.itacademy.exception.EntityNotFoundException;
import com.softserve.itacademy.exception.NullEntityReferenceException;
import com.softserve.itacademy.model.Task;
import com.softserve.itacademy.repository.TaskRepository;
import com.softserve.itacademy.service.impl.TaskServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceExceptionTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    private Task task;

    @BeforeEach
    void setUp() {
        task = new Task();
        task.setId(1L);
    }

    @Test
    void create_shouldThrowNullEntityReferenceException_whenTaskIsNull() {
        NullEntityReferenceException exception = assertThrows(NullEntityReferenceException.class, () -> taskService.create(null));
        assertEquals("Task cannot be null", exception.getMessage());
    }

    @Test
    void readById_shouldThrowEntityNotFoundException_whenTaskNotFound() {
        when(taskRepository.findById(anyLong())).thenReturn(Optional.empty());
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> taskService.readById(1L));
        assertEquals("Task with id 1 not found", exception.getMessage());
    }

    @Test
    void update_shouldThrowEntityNotFoundException_whenTaskIsNull() {
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> taskService.update(null));
        assertEquals("Task cannot be null", exception.getMessage());
    }

    @Test
    void update_shouldThrowEntityNotFoundException_whenTaskNotFound() {
        when(taskRepository.findById(anyLong())).thenReturn(Optional.empty());
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> taskService.update(task));
        assertEquals("Task with id 1 not found", exception.getMessage());
    }

    @Test
    void delete_shouldThrowEntityNotFoundException_whenTaskNotFound() {
        when(taskRepository.findById(anyLong())).thenReturn(Optional.empty());
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> taskService.delete(1L));
        assertEquals("Task with id 1 not found", exception.getMessage());
    }

    @Test
    void delete_shouldThrowEntityNotFoundException_whenIdIsInvalid() {
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> taskService.delete(0L));
        assertEquals("Task with id 0 not found", exception.getMessage());
    }
}
