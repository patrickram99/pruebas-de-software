package org.example;

public interface UserRepository {
    void save(User user);
    User findByEmail(String email);
    boolean emailExists(String email);
}