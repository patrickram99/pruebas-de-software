package org.example;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;

class StringUtilsMockitoTest {

    @Test
    @DisplayName("Mockito: stub de isEmpty para invertir su comportamiento real")
    void testMockedIsEmpty() {
        try (MockedStatic<StringUtils> mocked = mockStatic(StringUtils.class)) {
            mocked.when(() -> StringUtils.isEmpty("hola")).thenReturn(true);

            assertTrue(StringUtils.isEmpty("hola"));
            mocked.verify(() -> StringUtils.isEmpty("hola"), times(1));
        }
    }

    @Test
    @DisplayName("Mockito: stub de isBlank devuelve false para cadena vacia")
    void testMockedIsBlank() {
        try (MockedStatic<StringUtils> mocked = mockStatic(StringUtils.class)) {
            mocked.when(() -> StringUtils.isBlank("")).thenReturn(false);

            assertFalse(StringUtils.isBlank(""));
            mocked.verify(() -> StringUtils.isBlank(""));
        }
    }

    @Test
    @DisplayName("Mockito: stub de capitalize devuelve un valor fijo")
    void testMockedCapitalize() {
        try (MockedStatic<StringUtils> mocked = mockStatic(StringUtils.class)) {
            mocked.when(() -> StringUtils.capitalize(anyString())).thenReturn("MOCKED");

            assertEquals("MOCKED", StringUtils.capitalize("cualquiera"));
            assertEquals("MOCKED", StringUtils.capitalize("otra"));
            mocked.verify(() -> StringUtils.capitalize(anyString()), times(2));
        }
    }

    @Test
    @DisplayName("Mockito: stub de reverse devuelve la misma cadena")
    void testMockedReverse() {
        try (MockedStatic<StringUtils> mocked = mockStatic(StringUtils.class)) {
            mocked.when(() -> StringUtils.reverse("abc")).thenReturn("abc");

            assertEquals("abc", StringUtils.reverse("abc"));
            mocked.verify(() -> StringUtils.reverse("abc"));
        }
    }

    @Test
    @DisplayName("Mockito: stub de join controla el resultado completo")
    void testMockedJoin() {
        String[] arr = {"x", "y", "z"};
        try (MockedStatic<StringUtils> mocked = mockStatic(StringUtils.class)) {
            mocked.when(() -> StringUtils.join(arr, ",")).thenReturn("x|y|z");

            assertEquals("x|y|z", StringUtils.join(arr, ","));
            mocked.verify(() -> StringUtils.join(arr, ","));
        }
    }

    @Test
    @DisplayName("Mockito: stub de repeat ignora el conteo real")
    void testMockedRepeat() {
        try (MockedStatic<StringUtils> mocked = mockStatic(StringUtils.class)) {
            mocked.when(() -> StringUtils.repeat("a", 3)).thenReturn("AAAAA");

            assertEquals("AAAAA", StringUtils.repeat("a", 3));
            mocked.verify(() -> StringUtils.repeat("a", 3));
        }
    }

    @Test
    @DisplayName("Mockito: stub de contains fuerza true incluso si no contiene")
    void testMockedContains() {
        try (MockedStatic<StringUtils> mocked = mockStatic(StringUtils.class)) {
            mocked.when(() -> StringUtils.contains("hola", "zzz")).thenReturn(true);

            assertTrue(StringUtils.contains("hola", "zzz"));
            mocked.verify(() -> StringUtils.contains(eq("hola"), eq("zzz")));
        }
    }

    @Test
    @DisplayName("Mockito: stub de substring devuelve un fragmento simulado")
    void testMockedSubstring() {
        try (MockedStatic<StringUtils> mocked = mockStatic(StringUtils.class)) {
            mocked.when(() -> StringUtils.substring("hola", 1, 3)).thenReturn("XX");

            assertEquals("XX", StringUtils.substring("hola", 1, 3));
            mocked.verify(() -> StringUtils.substring("hola", 1, 3));
        }
    }

    @Test
    @DisplayName("Mockito: stub de swapCase para devolver siempre lo mismo")
    void testMockedSwapCase() {
        try (MockedStatic<StringUtils> mocked = mockStatic(StringUtils.class)) {
            mocked.when(() -> StringUtils.swapCase(anyString())).thenReturn("swapped");

            assertEquals("swapped", StringUtils.swapCase("Hola"));
            assertEquals("swapped", StringUtils.swapCase("Mundo"));
            mocked.verify(() -> StringUtils.swapCase(anyString()), times(2));
        }
    }

    @Test
    @DisplayName("Mockito: stub de countMatches devuelve un valor fijo y verifica la llamada")
    void testMockedCountMatches() {
        try (MockedStatic<StringUtils> mocked = mockStatic(StringUtils.class)) {
            mocked.when(() -> StringUtils.countMatches("ababab", "ab")).thenReturn(99);

            assertEquals(99, StringUtils.countMatches("ababab", "ab"));
            mocked.verify(() -> StringUtils.countMatches("ababab", "ab"), times(1));
        }
    }
}
