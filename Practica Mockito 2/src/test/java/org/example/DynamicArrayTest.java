package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DynamicArrayTest {

    private DynamicArray<Integer> array;

    @BeforeEach
    void setUp() {
        array = new DynamicArray<>();
    }

    // --- constructor ---

    @Test
    void defaultConstructorCreatesEmptyArray() {
        assertTrue(array.isEmpty());
        assertEquals(0, array.getSize());
    }

    @Test
    void constructorWithCapacity() {
        DynamicArray<String> arr = new DynamicArray<>(4);
        assertTrue(arr.isEmpty());
        assertEquals(0, arr.getSize());
    }

    // --- add ---

    @Test
    void addIncreasesSize() {
        array.add(1);
        assertEquals(1, array.getSize());
        assertFalse(array.isEmpty());
    }

    @Test
    void addMultipleElements() {
        array.add(10);
        array.add(20);
        array.add(30);

        assertEquals(3, array.getSize());
        assertEquals(10, array.get(0));
        assertEquals(20, array.get(1));
        assertEquals(30, array.get(2));
    }

    @Test
    void addBeyondInitialCapacityResizes() {
        DynamicArray<Integer> small = new DynamicArray<>(2);
        small.add(1);
        small.add(2);
        small.add(3); // triggers resize

        assertEquals(3, small.getSize());
        assertEquals(3, small.get(2));
    }

    // --- put ---

    @Test
    void putReplacesElement() {
        array.add(10);
        array.add(20);

        array.put(0, 99);
        assertEquals(99, array.get(0));
        assertEquals(20, array.get(1));
    }

    // --- get ---

    @Test
    void getReturnsCorrectElement() {
        array.add(100);
        assertEquals(100, array.get(0));
    }

    // --- remove ---

    @Test
    void removeReturnsRemovedElement() {
        array.add(10);
        array.add(20);
        array.add(30);

        Integer removed = array.remove(1);
        assertEquals(20, removed);
        assertEquals(2, array.getSize());
    }

    @Test
    void removeShiftsElements() {
        array.add(10);
        array.add(20);
        array.add(30);

        array.remove(0);
        assertEquals(20, array.get(0));
        assertEquals(30, array.get(1));
    }

    @Test
    void removeFirstElement() {
        array.add(1);
        array.add(2);
        assertEquals(1, array.remove(0));
        assertEquals(1, array.getSize());
        assertEquals(2, array.get(0));
    }

    @Test
    void removeLastElement() {
        array.add(1);
        array.add(2);
        assertEquals(2, array.remove(1));
        assertEquals(1, array.getSize());
    }

    // --- isEmpty ---

    @Test
    void isEmptyTrueForNewArray() {
        assertTrue(array.isEmpty());
    }

    @Test
    void isEmptyFalseAfterAdd() {
        array.add(1);
        assertFalse(array.isEmpty());
    }

    // --- toString ---

    @Test
    void toStringEmptyArray() {
        assertEquals("[]", array.toString());
    }

    @Test
    void toStringWithElements() {
        array.add(1);
        array.add(2);
        assertEquals("[1, 2]", array.toString());
    }

    // --- iterator ---

    @Test
    void iteratorTraversesAllElements() {
        array.add(10);
        array.add(20);
        array.add(30);

        Iterator<Integer> it = array.iterator();
        assertTrue(it.hasNext());
        assertEquals(10, it.next());
        assertEquals(20, it.next());
        assertEquals(30, it.next());
        assertFalse(it.hasNext());
    }

    @Test
    void iteratorNextThrowsWhenExhausted() {
        array.add(1);
        Iterator<Integer> it = array.iterator();
        it.next();
        assertThrows(NoSuchElementException.class, it::next);
    }

    @Test
    void iteratorWorksWithForEach() {
        array.add(5);
        array.add(10);

        int sum = 0;
        for (Integer val : array) {
            sum += val;
        }
        assertEquals(15, sum);
    }

    // --- stream ---

    @Test
    void streamWorksCorrectly() {
        array.add(1);
        array.add(2);
        array.add(3);

        int sum = array.stream().mapToInt(Integer::intValue).sum();
        assertEquals(6, sum);
    }

    @Test
    void streamFilterWorks() {
        array.add(1);
        array.add(2);
        array.add(3);
        array.add(4);

        long evenCount = array.stream().filter(x -> x % 2 == 0).count();
        assertEquals(2, evenCount);
    }

    // --- Mockito: spy to verify method interactions ---

    @Test
    void addOnSpyVerifiesCalls() {
        DynamicArray<String> spyArray = spy(new DynamicArray<>());

        spyArray.add("hello");
        spyArray.add("world");

        verify(spyArray, times(2)).add(anyString());
        assertEquals(2, spyArray.getSize());
    }

    @Test
    void removeOnSpyVerifiesReturnValue() {
        DynamicArray<String> spyArray = spy(new DynamicArray<>());

        spyArray.add("A");
        spyArray.add("B");
        spyArray.add("C");

        String removed = spyArray.remove(1);

        verify(spyArray).remove(1);
        assertEquals("B", removed);
        assertEquals(2, spyArray.getSize());
    }

    @Test
    void getOnSpyReturnsCorrectValue() {
        DynamicArray<Integer> spyArray = spy(new DynamicArray<>());

        spyArray.add(42);

        Integer result = spyArray.get(0);

        verify(spyArray).get(0);
        assertEquals(42, result);
    }
}
