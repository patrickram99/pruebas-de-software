import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BSTTest {

    private BST<Integer> bst;

    @BeforeEach
    public void setUp() {
        bst = new BST<Integer>();
    }

    // INSERT

    @Test
    public void testInsertEnArbolVacio() {
        bst.insert(10);
        assertTrue(bst.search(10));
    }

    @Test
    public void testInsertVariosElementos() {
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);
        assertTrue(bst.search(10));
        assertTrue(bst.search(5));
        assertTrue(bst.search(15));
    }

    @Test
    public void testInsertElementoDuplicado() {
        bst.insert(10);
        bst.insert(10);
        // Al eliminar una vez no debe existir, confirmando que no se duplico
        bst.delete(10);
        assertFalse(bst.search(10));
    }

    @Test
    public void testInsertOrdenAscendente() {
        // Insercion 1->2->3->4 genera arbol degenerado a la derecha (altura 3)
        bst.insert(1);
        bst.insert(2);
        bst.insert(3);
        bst.insert(4);
        assertTrue(bst.search(1));
        assertTrue(bst.search(4));
        assertEquals(3, bst.height());
    }

    @Test
    public void testInsertOrdenDescendente() {
        // Insercion 4->3->2->1 genera arbol degenerado a la izquierda (altura 3)
        bst.insert(4);
        bst.insert(3);
        bst.insert(2);
        bst.insert(1);
        assertTrue(bst.search(1));
        assertTrue(bst.search(4));
        assertEquals(3, bst.height());
    }

    @Test
    public void testInsertUnSoloElemento() {
        bst.insert(42);
        assertTrue(bst.search(42));
        assertEquals(0, bst.height());
        assertEquals(1, bst.countLeaves());
    }

    // SEARCH

    @Test
    public void testSearchEnArbolVacio() {
        assertFalse(bst.search(10));
    }

    @Test
    public void testSearchElementoExistente() {
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);
        assertTrue(bst.search(5));
    }

    @Test
    public void testSearchElementoInexistente() {
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);
        assertFalse(bst.search(99));
    }

    @Test
    public void testSearchRaiz() {
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);
        assertTrue(bst.search(10));
    }

    @Test
    public void testSearchHojaIzquierda() {
        bst.insert(10);
        bst.insert(5);
        assertTrue(bst.search(5));
    }

    @Test
    public void testSearchHojaDerecha() {
        bst.insert(10);
        bst.insert(15);
        assertTrue(bst.search(15));
    }

    @Test
    public void testSearchElementoMenorQueTodos() {
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);
        assertFalse(bst.search(1));
    }

    @Test
    public void testSearchElementoMayorQueTodos() {
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);
        assertFalse(bst.search(100));
    }

    // DELETE

    @Test
    public void testDeleteHoja() {
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);
        bst.delete(5);
        assertFalse(bst.search(5));
        assertTrue(bst.search(10));
        assertTrue(bst.search(15));
    }

    @Test
    public void testDeleteNodoConUnHijoIzquierdo() {
        bst.insert(10);
        bst.insert(5);
        bst.insert(3);
        bst.delete(5);
        assertFalse(bst.search(5));
        assertTrue(bst.search(3));
        assertTrue(bst.search(10));
    }

    @Test
    public void testDeleteNodoConUnHijoDerecho() {
        bst.insert(10);
        bst.insert(5);
        bst.insert(7);
        bst.delete(5);
        assertFalse(bst.search(5));
        assertTrue(bst.search(7));
        assertTrue(bst.search(10));
    }

    @Test
    public void testDeleteNodoConDosHijos() {
        // Caso critico: eliminar nodo con dos hijos requiere reemplazo por sucesor/predecesor
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);
        bst.insert(3);
        bst.insert(7);
        bst.delete(5);
        assertFalse(bst.search(5));
        assertTrue(bst.search(3));
        assertTrue(bst.search(7));
        assertTrue(bst.search(10));
        assertTrue(bst.search(15));
    }

    @Test
    public void testDeleteRaiz() {
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);
        bst.delete(10);
        assertFalse(bst.search(10));
        assertTrue(bst.search(5));
        assertTrue(bst.search(15));
    }

    @Test
    public void testDeleteUnicoElemento() {
        bst.insert(10);
        bst.delete(10);
        assertFalse(bst.search(10));
    }

    @Test
    public void testDeleteElementoInexistente() {
        bst.insert(10);
        assertThrows(RuntimeException.class, () -> bst.delete(99));
    }

    @Test
    public void testDeleteEnArbolVacio() {
        assertThrows(RuntimeException.class, () -> bst.delete(10));
    }

    @Test
    public void testDeleteTodosLosElementos() {
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);
        bst.delete(5);
        bst.delete(15);
        bst.delete(10);
        assertFalse(bst.search(10));
        assertFalse(bst.search(5));
        assertFalse(bst.search(15));
    }

    // HEIGHT

    @Test
    public void testHeightArbolVacio() {
        assertEquals(-1, bst.height());
    }

    @Test
    public void testHeightUnNodo() {
        bst.insert(10);
        assertEquals(0, bst.height());
    }

    @Test
    public void testHeightArbolBalanceado() {
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);
        assertEquals(1, bst.height());
    }

    @Test
    public void testHeightArbolDegeneradoIzquierda() {
        bst.insert(4);
        bst.insert(3);
        bst.insert(2);
        bst.insert(1);
        assertEquals(3, bst.height());
    }

    @Test
    public void testHeightArbolDegeneradoDerecha() {
        bst.insert(1);
        bst.insert(2);
        bst.insert(3);
        bst.insert(4);
        assertEquals(3, bst.height());
    }

    @Test
    public void testHeightArbolCompleto() {
        // Arbol completo de 3 niveles: raiz 10, nivel 1: {5,15}, nivel 2: {3,7,12,20}
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);
        bst.insert(3);
        bst.insert(7);
        bst.insert(12);
        bst.insert(20);
        assertEquals(2, bst.height());
    }

    @Test
    public void testHeightDespuesDeDelete() {
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);
        bst.insert(3);
        assertEquals(2, bst.height());
        bst.delete(3);
        assertEquals(1, bst.height());
    }

    // WIDTH

    @Test
    public void testWidthArbolVacio() {
        assertEquals(0, bst.width());
    }

    @Test
    public void testWidthUnNodo() {
        bst.insert(10);
        assertEquals(1, bst.width());
    }

    @Test
    public void testWidthArbolBalanceado() {
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);
        assertEquals(2, bst.width());
    }

    @Test
    public void testWidthArbolDegenerado() {
        // Arbol degenerado: cada nivel tiene 1 nodo, ancho maximo = 1
        bst.insert(1);
        bst.insert(2);
        bst.insert(3);
        assertEquals(1, bst.width());
    }

    @Test
    public void testWidthArbolCompleto() {
        // Arbol completo: nivel mas ancho tiene 4 hojas
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);
        bst.insert(3);
        bst.insert(7);
        bst.insert(12);
        bst.insert(20);
        assertEquals(4, bst.width());
    }

    @Test
    public void testWidthArbolAsimetrico() {
        // Subarboles desiguales: izquierdo tiene 2 hijos, derecho es hoja
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);
        bst.insert(3);
        bst.insert(7);
        assertEquals(2, bst.width());
    }

    // COUNT LEAVES

    @Test
    public void testCountLeavesArbolVacio() {
        assertEquals(0, bst.countLeaves());
    }

    @Test
    public void testCountLeavesUnNodo() {
        bst.insert(10);
        assertEquals(1, bst.countLeaves());
    }

    @Test
    public void testCountLeavesArbolBalanceado() {
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);
        assertEquals(2, bst.countLeaves());
    }

    @Test
    public void testCountLeavesArbolDegenerado() {
        bst.insert(1);
        bst.insert(2);
        bst.insert(3);
        // Solo el ultimo nodo es hoja en un arbol degenerado
        assertEquals(1, bst.countLeaves());
    }

    @Test
    public void testCountLeavesArbolCompleto() {
        // 4 hojas: {3, 7, 12, 20}
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);
        bst.insert(3);
        bst.insert(7);
        bst.insert(12);
        bst.insert(20);
        assertEquals(4, bst.countLeaves());
    }

    @Test
    public void testCountLeavesDespuesDeDelete() {
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);
        assertEquals(2, bst.countLeaves());
        bst.delete(15);
        assertEquals(1, bst.countLeaves());
    }

    @Test
    public void testCountLeavesArbolConSoloHijosIzquierdos() {
        bst.insert(10);
        bst.insert(5);
        bst.insert(3);
        bst.insert(1);
        assertEquals(1, bst.countLeaves());
    }

    @Test
    public void testCountLeavesArbolConSoloHijosDerechos() {
        bst.insert(1);
        bst.insert(5);
        bst.insert(10);
        bst.insert(15);
        assertEquals(1, bst.countLeaves());
    }
}
