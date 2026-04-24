package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BinaryTreeTest {

    private BinaryTree tree;

    @BeforeEach
    void setUp() {
        tree = new BinaryTree();
    }

    // --- find ---

    @Test
    void findOnEmptyTreeReturnsNull() {
        assertNull(tree.find(5));
    }

    @Test
    void findExistingElement() {
        tree.put(10);
        tree.put(5);
        tree.put(15);

        assertEquals(10, tree.find(10).data);
        assertEquals(5, tree.find(5).data);
        assertEquals(15, tree.find(15).data);
    }

    @Test
    void findNonExistingElementReturnsParent() {
        tree.put(10);
        BinaryTree.Node result = tree.find(5);
        // 5 doesn't exist, returns parent node (10)
        assertEquals(10, result.data);
    }

    // --- put ---

    @Test
    void putOnEmptyTreeSetsRoot() {
        tree.put(10);
        assertNotNull(tree.getRoot());
        assertEquals(10, tree.getRoot().data);
    }

    @Test
    void putSmallerValueGoesLeft() {
        tree.put(10);
        tree.put(5);
        assertEquals(5, tree.getRoot().left.data);
    }

    @Test
    void putLargerValueGoesRight() {
        tree.put(10);
        tree.put(15);
        assertEquals(15, tree.getRoot().right.data);
    }

    @Test
    void putSetsParentReference() {
        tree.put(10);
        tree.put(5);
        assertEquals(tree.getRoot(), tree.getRoot().left.parent);
    }

    @Test
    void putMultipleElements() {
        tree.put(10);
        tree.put(5);
        tree.put(15);
        tree.put(3);
        tree.put(7);

        assertEquals(10, tree.getRoot().data);
        assertEquals(5, tree.getRoot().left.data);
        assertEquals(15, tree.getRoot().right.data);
        assertEquals(3, tree.getRoot().left.left.data);
        assertEquals(7, tree.getRoot().left.right.data);
    }

    // --- remove ---

    @Test
    void removeLeafNode() {
        tree.put(10);
        tree.put(5);
        tree.put(15);

        assertTrue(tree.remove(5));
        assertNull(tree.getRoot().left);
    }

    @Test
    void removeNodeWithOneChild() {
        tree.put(10);
        tree.put(5);
        tree.put(3);

        assertTrue(tree.remove(5));
        assertEquals(3, tree.getRoot().left.data);
    }

    @Test
    void removeNodeWithTwoChildren() {
        tree.put(10);
        tree.put(5);
        tree.put(15);
        tree.put(12);
        tree.put(20);

        assertTrue(tree.remove(15));
        // successor (20) replaces 15
        BinaryTree.Node right = tree.getRoot().right;
        assertNotNull(right);
        assertEquals(12, right.left.data);
    }

    @Test
    void removeRoot() {
        tree.put(10);
        assertTrue(tree.remove(10));
        assertNull(tree.getRoot());
    }

    @Test
    void removeRootWithLeftChild() {
        tree.put(10);
        tree.put(5);
        assertTrue(tree.remove(10));
        assertEquals(5, tree.getRoot().data);
    }

    @Test
    void removeRootWithRightChild() {
        tree.put(10);
        tree.put(15);
        assertTrue(tree.remove(10));
        assertEquals(15, tree.getRoot().data);
    }

    @Test
    void removeNonExistingReturnsFalse() {
        tree.put(10);
        assertFalse(tree.remove(99));
    }

    // --- findSuccessor ---

    @Test
    void findSuccessorReturnsSmallestInRightSubtree() {
        tree.put(10);
        tree.put(15);
        tree.put(12);
        tree.put(20);

        BinaryTree.Node node = tree.find(10);
        BinaryTree.Node successor = tree.findSuccessor(node);
        assertEquals(12, successor.data);
    }

    @Test
    void findSuccessorWithNoRightChild() {
        tree.put(10);
        BinaryTree.Node node = tree.find(10);
        BinaryTree.Node successor = tree.findSuccessor(node);
        assertSame(node, successor);
    }

    // --- Mockito: spy to verify internal calls ---

    @Test
    void putCallsFindToLocateParent() {
        BinaryTree spyTree = spy(new BinaryTree());
        spyTree.put(10);
        spyTree.put(5);

        // put should call find to locate where to insert
        verify(spyTree).find(5);
    }

    @Test
    void removeCallsFindToLocateNode() {
        BinaryTree spyTree = spy(new BinaryTree());
        spyTree.put(10);
        spyTree.put(5);

        // Reset interactions from put calls
        clearInvocations(spyTree);

        spyTree.remove(5);

        verify(spyTree).find(5);
    }

    @Test
    void removeWithTwoChildrenCallsFindSuccessor() {
        BinaryTree spyTree = spy(new BinaryTree());
        spyTree.put(10);
        spyTree.put(5);
        spyTree.put(15);

        spyTree.remove(10);

        verify(spyTree).findSuccessor(any());
    }
}
