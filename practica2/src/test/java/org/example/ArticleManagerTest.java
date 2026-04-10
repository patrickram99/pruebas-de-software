package org.example;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ArticleManagerTest {

    @Mock
    ArticleDatabase database;

    @Mock
    User user;

    @InjectMocks
    private ArticleManager manager;

    @Test
    void ensureInjectMockWorks() {
        manager.initialize();

        verify(database).addListener(any(ArticleListener.class));
        verify(database).setUser(user);
    }
}
