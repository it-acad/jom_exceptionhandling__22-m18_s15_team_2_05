package com.softserve.itacademy;

import com.softserve.itacademy.exception.EntityNotFoundException;
import com.softserve.itacademy.exception.NullEntityReferenceException;
import com.softserve.itacademy.model.User;
import com.softserve.itacademy.repository.UserRepository;
import com.softserve.itacademy.service.impl.UserServiceImpl;
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
class UserServiceExceptionTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
    }

    @Test
    void create_shouldThrowNullEntityReferenceException_whenUserIsNull() {
        NullEntityReferenceException exception = assertThrows(NullEntityReferenceException.class, () -> userService.create(null));
        assertEquals("User cannot be null", exception.getMessage());
    }

    @Test
    void readById_shouldThrowEntityNotFoundException_whenUserNotFound() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> userService.readById(1L));
        assertEquals("User with id 1 not found", exception.getMessage());
    }

    @Test
    void update_shouldThrowNullEntityReferenceException_whenUserIsNull() {
        NullEntityReferenceException exception = assertThrows(NullEntityReferenceException.class, () -> userService.update(null));
        assertEquals("User cannot be null", exception.getMessage());
    }

    @Test
    void update_shouldThrowEntityNotFoundException_whenUserNotFound() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> userService.update(user));
        assertEquals("User with id 1 not found", exception.getMessage());
    }

    @Test
    void delete_shouldThrowEntityNotFoundException_whenUserNotFound() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> userService.delete(1L));
        assertEquals("User with id 1 not found", exception.getMessage());
    }
}
