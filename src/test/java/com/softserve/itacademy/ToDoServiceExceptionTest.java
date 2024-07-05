package com.softserve.itacademy;

import com.softserve.itacademy.exception.EntityNotFoundException;
import com.softserve.itacademy.exception.NullEntityReferenceException;
import com.softserve.itacademy.model.ToDo;
import com.softserve.itacademy.repository.ToDoRepository;
import com.softserve.itacademy.service.impl.ToDoServiceImpl;
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
class ToDoServiceExceptionTest {

    @Mock
    private ToDoRepository todoRepository;

    @InjectMocks
    private ToDoServiceImpl todoService;

    private ToDo todo;

    @BeforeEach
    void setUp() {
        todo = new ToDo();
        todo.setId(1L);
    }

    @Test
    void create_shouldThrowNullEntityReferenceException_whenToDoIsNull() {
        NullEntityReferenceException exception = assertThrows(NullEntityReferenceException.class, () -> todoService.create(null));
        assertEquals("ToDo cannot be null", exception.getMessage());
    }

    @Test
    void readById_shouldThrowEntityNotFoundException_whenToDoNotFound() {
        when(todoRepository.findById(anyLong())).thenReturn(Optional.empty());
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> todoService.readById(1L));
        assertEquals("ToDo not found", exception.getMessage());
    }

    @Test
    void update_shouldThrowEntityNotFoundException_whenToDoIsNull() {
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> todoService.update(null));
        assertEquals("ToDo cannot be null", exception.getMessage());
    }

    @Test
    void update_shouldThrowEntityNotFoundException_whenToDoNotFound() {
        when(todoRepository.findById(anyLong())).thenReturn(Optional.empty());
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> todoService.update(todo));
        assertEquals("ToDo not found", exception.getMessage());
    }

    @Test
    void delete_shouldThrowEntityNotFoundException_whenToDoNotFound() {
        when(todoRepository.findById(anyLong())).thenReturn(Optional.empty());
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> todoService.delete(1L));
        assertEquals("ToDo not found", exception.getMessage());
    }

    @Test
    void delete_shouldThrowNullEntityReferenceException_whenIdIsInvalid() {
        NullEntityReferenceException exception = assertThrows(NullEntityReferenceException.class, () -> todoService.delete(-1L));
        assertEquals("Wrong id", exception.getMessage());
    }
}

