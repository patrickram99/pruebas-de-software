package org.example;

public interface EmailService {
    boolean sendWelcomeEmail(String email, String name);
    boolean sendVerificationEmail(String email, String verificationCode);
}