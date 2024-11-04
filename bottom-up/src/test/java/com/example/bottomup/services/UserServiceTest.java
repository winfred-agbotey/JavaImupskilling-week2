package com.example.bottomup.services;

import com.example.bottomup.model.User;
import com.example.bottomup.repository.UserRepository;
import com.example.bottomup.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    void testRegisterUser() {
        User user = new User("Mawuli", "mawuli@gmail.com");
        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = userService.registerUser(user);
        assertNotNull(result);
        assertEquals(user.getUsername(), result.getUsername());
    }

    @Test
    void testGetUserByUsername() {
        User user = new User("Mawuli", "mawuli@gmail.com");
        when(userRepository.findByUsername("Mawuli")).thenReturn(Optional.of(user));

        Optional<User> result = userService.getUserByUsername("Mawuli");
        assertTrue(result.isPresent());
        assertEquals(user.getUsername(), result.get().getUsername());
    }
}
