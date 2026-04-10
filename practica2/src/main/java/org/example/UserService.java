package org.example;

public class UserService {

    private final UserRepository userRepository;
    private final EmailService emailService;

    public UserService(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    public boolean registerUser(String email, String name) {
        // Check if user already exists
        if (userRepository.emailExists(email)) {
            return false;
        }

        // Create and save user
        User user = new User(email, name);
        userRepository.save(user);

        // Send welcome email
        return emailService.sendWelcomeEmail(email, name);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}