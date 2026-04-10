package org.example;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    EmailService emailService;

    @Test
    void testSuccessfulUserRegistration() {
        // Arrange: Configure mock behavior
        when(userRepository.emailExists("john@example.com")).thenReturn(false);
        when(emailService.sendWelcomeEmail("john@example.com", "John")).thenReturn(true);

        UserService userService = new UserService(userRepository, emailService);

        // Act: Execute the method under test
        boolean result = userService.registerUser("john@example.com", "John");

        // Assert: Verify the outcome
        assertTrue(result);

        // Verify interactions with mocks
        verify(userRepository).save(any(User.class));
        verify(emailService).sendWelcomeEmail("john@example.com", "John");
    }

    @Test
    void testUserRegistrationWithExistingEmail() {
        // Arrange: User already exists
        when(userRepository.emailExists("existing@example.com")).thenReturn(true);

        UserService userService = new UserService(userRepository, emailService);

        // Act & Assert
        assertFalse(userService.registerUser("existing@example.com", "Jane"));
    }
}
