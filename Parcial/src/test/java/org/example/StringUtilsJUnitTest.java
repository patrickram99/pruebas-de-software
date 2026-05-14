package org.example;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringUtilsJUnitTest {

    @Test
    @DisplayName("isEmpty: true para null y \"\", false en otro caso")
    void testIsEmpty() {
        assertTrue(StringUtils.isEmpty(null));
        assertTrue(StringUtils.isEmpty(""));
        assertFalse(StringUtils.isEmpty(" "));
        assertFalse(StringUtils.isEmpty("hola"));
    }

    @Test
    @DisplayName("isBlank: true para null, \"\" y solo espacios")
    void testIsBlank() {
        assertTrue(StringUtils.isBlank(null));
        assertTrue(StringUtils.isBlank(""));
        assertTrue(StringUtils.isBlank("   "));
        assertFalse(StringUtils.isBlank("a"));
        assertFalse(StringUtils.isBlank("  a  "));
    }

    @Test
    @DisplayName("capitalize: pone en mayuscula la primera letra")
    void testCapitalize() {
        assertEquals("Hola", StringUtils.capitalize("hola"));
        assertEquals("Mundo", StringUtils.capitalize("mundo"));
        assertEquals("", StringUtils.capitalize(""));
        assertNull(StringUtils.capitalize(null));
    }

    @Test
    @DisplayName("reverse: invierte el orden de los caracteres")
    void testReverse() {
        assertEquals("aloh", StringUtils.reverse("hola"));
        assertEquals("321", StringUtils.reverse("123"));
        assertEquals("", StringUtils.reverse(""));
        assertNull(StringUtils.reverse(null));
    }

    @Test
    @DisplayName("join: une elementos con un separador")
    void testJoin() {
        String[] arr = {"a", "b", "c"};
        assertEquals("a,b,c", StringUtils.join(arr, ","));
        assertEquals("a-b-c", StringUtils.join(arr, '-'));
        assertEquals("abc", StringUtils.join(arr, (String) null));
        assertNull(StringUtils.join((Object[]) null, ","));
    }

    @Test
    @DisplayName("repeat: repite un String n veces")
    void testRepeat() {
        assertEquals("aaa", StringUtils.repeat("a", 3));
        assertEquals("ababab", StringUtils.repeat("ab", 3));
        assertEquals("", StringUtils.repeat("a", 0));
        assertEquals("", StringUtils.repeat("a", -2));
        assertNull(StringUtils.repeat(null, 3));
    }

    @Test
    @DisplayName("contains: verifica si un String contiene una secuencia")
    void testContains() {
        assertTrue(StringUtils.contains("hola mundo", "mundo"));
        assertTrue(StringUtils.contains("abc", ""));
        assertFalse(StringUtils.contains("hola", "xyz"));
        assertFalse(StringUtils.contains(null, "a"));
        assertFalse(StringUtils.contains("abc", null));
    }

    @Test
    @DisplayName("substring: extrae una porcion de forma null-safe")
    void testSubstring() {
        assertEquals("ola", StringUtils.substring("hola", 1));
        assertEquals("ol", StringUtils.substring("hola", 1, 3));
        assertEquals("la", StringUtils.substring("hola", -2));
        assertEquals("", StringUtils.substring("hola", 10));
        assertNull(StringUtils.substring(null, 1));
    }

    @Test
    @DisplayName("swapCase: invierte la mayuscula/minuscula de cada letra")
    void testSwapCase() {
        assertEquals("hOLA", StringUtils.swapCase("Hola"));
        assertEquals("JaVa", StringUtils.swapCase("jAvA"));
        assertEquals("", StringUtils.swapCase(""));
        assertNull(StringUtils.swapCase(null));
    }

    @Test
    @DisplayName("countMatches: cuenta ocurrencias de una subcadena")
    void testCountMatches() {
        assertEquals(3, StringUtils.countMatches("ababab", "ab"));
        assertEquals(2, StringUtils.countMatches("hola mundo", "o"));
        assertEquals(0, StringUtils.countMatches("abc", "z"));
        assertEquals(0, StringUtils.countMatches(null, "a"));
        assertEquals(0, StringUtils.countMatches("abc", ""));
    }
}
