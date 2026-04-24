package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CircleLinkedListTest {

    private CircleLinkedList<Integer> list;

    @BeforeEach
    void setUp() {
        list = new CircleLinkedList<>();
    }

    // --- getSize ---

    @Test
    void newListHasSizeZero() {
        assertEquals(0, list.getSize());
    }

    // --- append ---

    @Test
    void appendIncreasesSize() {
        list.append(1);
        assertEquals(1, list.getSize());

        list.append(2);
        assertEquals(2, list.getSize());
    }

    @Test
    void appendNullThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> list.append(null));
    }

    @Test
    void appendMultipleElements() {
        list.append(10);
        list.append(20);
        list.append(30);
        assertEquals(3, list.getSize());
        assertEquals("[ 10 , 20 , 30 ]", list.toString());
    }

    // --- toString ---

    @Test
    void toStringEmptyList() {
        assertEquals("[  ]", list.toString());
    }

    @Test
    void toStringSingleElement() {
        list.append(42);
        assertEquals("[ 42 ]", list.toString());
    }

    @Test
    void toStringMultipleElements() {
        list.append(1);
        list.append(2);
        list.append(3);
        assertEquals("[ 1 , 2 , 3 ]", list.toString());
    }

    // --- remove ---

    @Test
    void removeFirstElement() {
        list.append(10);
        list.append(20);
        list.append(30);

        Integer removed = list.remove(0);
        assertEquals(10, removed);
        assertEquals(2, list.getSize());
        assertEquals("[ 20 , 30 ]", list.toString());
    }

    @Test
    void removeLastElement() {
        list.append(10);
        list.append(20);
        list.append(30);

        Integer removed = list.remove(2);
        assertEquals(30, removed);
        assertEquals(2, list.getSize());
        assertEquals("[ 10 , 20 ]", list.toString());
    }

    @Test
    void removeMiddleElement() {
        list.append(10);
        list.append(20);
        list.append(30);

        Integer removed = list.remove(1);
        assertEquals(20, removed);
        assertEquals(2, list.getSize());
        assertEquals("[ 10 , 30 ]", list.toString());
    }

    @Test
    void removeOnlyElement() {
        list.append(42);
        Integer removed = list.remove(0);
        assertEquals(42, removed);
        assertEquals(0, list.getSize());
    }

    @Test
    void removeNegativeIndexThrows() {
        list.append(1);
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
    }

    @Test
    void removeIndexEqualToSizeThrows() {
        list.append(1);
        list.append(2);
        // size is 2, so index 2 is out of bounds
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(2));
    }

    @Test
    void removeIndexGreaterThanSizeThrows() {
        list.append(1);
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(5));
    }

    @Test
    void removeFromEmptyListThrows() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));
    }

    // --- Mockito: spy to verify behavior ---

    @Test
    void appendCallsOnSpyVerifiesSize() {
        CircleLinkedList<String> spyList = spy(new CircleLinkedList<>());

        spyList.append("A");
        spyList.append("B");

        verify(spyList, times(2)).append(anyString());
        assertEquals(2, spyList.getSize());
    }

    @Test
    void removeCallsOnSpyVerifiesReturn() {
        CircleLinkedList<String> spyList = spy(new CircleLinkedList<>());

        spyList.append("X");
        spyList.append("Y");

        String removed = spyList.remove(0);

        verify(spyList).remove(0);
        assertEquals("X", removed);
        assertEquals(1, spyList.getSize());
    }
}
