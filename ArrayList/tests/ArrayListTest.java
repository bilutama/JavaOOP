import org.junit.jupiter.api.*;
import ru.academits.biluta.array_list.ArrayList;

class ArrayListTest {
    ArrayList<Integer> arrayList;

    @BeforeEach
    void constructList() {
        arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(null);
        arrayList.add(0, 3);
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Adding items")
    void add() {
        Assertions.assertEquals(4, arrayList.size());
        Assertions.assertEquals("[3, 1, 2, null]", arrayList.toString());
        Assertions.assertEquals(3, arrayList.get(0));
        Assertions.assertEquals(1, arrayList.get(1));
        Assertions.assertNull(arrayList.get(3));
        Assertions.assertEquals(2, arrayList.get(2));

        for (int i = 0; i < 8; ++i) {
            arrayList.add(i + 5);
        }

        Assertions.assertEquals("[3, 1, 2, null, 5, 6, 7, 8, 9, 10, 11, 12]", arrayList.toString());
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Removing items")
    void remove() {
        Assertions.assertEquals(1, arrayList.remove(1));
        Assertions.assertNull(arrayList.get(2));
    }

    @Test
    @DisplayName("Getting item by index out of bounds")
    void shouldThrowIndexOutOfBoundsException() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> arrayList.get(5));
    }
}