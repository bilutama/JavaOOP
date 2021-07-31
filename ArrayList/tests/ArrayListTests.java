import org.junit.jupiter.api.*;
import ru.academits.biluta.array_list.ArrayList;

class ArrayListTests {
    ArrayList<Integer> arrayList;

    @BeforeEach
    void constructor() {
        // Let default List has 6 items
        arrayList = new ArrayList<>();
        arrayList.add(null);
        arrayList.add(2);
        arrayList.add(null);
        arrayList.add(3);
        arrayList.add(1, 1);
        arrayList.add(null);
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Get an item by index")
    void get() {
        Assertions.assertNull(arrayList.get(0));
        Assertions.assertNull(arrayList.get(3));
        Assertions.assertNull(arrayList.get(arrayList.size() - 1));

        Assertions.assertEquals(1, arrayList.get(1));
        Assertions.assertEquals(2, arrayList.get(2));
        Assertions.assertEquals(3, arrayList.get(4));
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Set value by index")
    void set() {
        Assertions.assertNull(arrayList.set(5, 0));
        Assertions.assertNull(arrayList.set(3, 0));
        Assertions.assertNull(arrayList.set(0, 0));

        Assertions.assertEquals("[0, 1, 2, 0, 3, 0]", arrayList.toString());

        Assertions.assertEquals(2, arrayList.set(2, 20));
        Assertions.assertEquals(20, arrayList.set(2, 30));
        Assertions.assertEquals(3, arrayList.set(4, 40));

        Assertions.assertEquals("[0, 1, 30, 0, 40, 0]", arrayList.toString());
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Add an item")
    void add() {
        Assertions.assertEquals("[null, 1, 2, null, 3, null]", arrayList.toString());

        for (int i = 0; i < 3; ++i) {
            arrayList.add(i);
        }

        Assertions.assertEquals("[null, 1, 2, null, 3, null, 0, 1, 2]", arrayList.toString());
        arrayList.add(null);
        Assertions.assertNull(arrayList.get(arrayList.size() - 1));
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Add items from external Collection")
    void addAll() {
        ArrayList<Integer> newList = new ArrayList<>();
        newList.add(null);
        newList.add(10);
        newList.add(20);

        arrayList.addAll(newList);
        Assertions.assertEquals("[null, 1, 2, null, 3, null, null, 10, 20]", arrayList.toString());
        Assertions.assertEquals(20, arrayList.get(arrayList.size() - 1));

        arrayList.addAll(0, newList);
        Assertions.assertEquals("[null, 10, 20, null, 1, 2, null, 3, null, null, 10, 20]", arrayList.toString());
        Assertions.assertNull(arrayList.get(0));
        Assertions.assertEquals(10, arrayList.get(1));
        Assertions.assertEquals(1, arrayList.get(4));
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Contents the item")
    void contain() {
        Assertions.assertTrue(arrayList.contains(2));
        Assertions.assertTrue(arrayList.contains(null));
        Assertions.assertFalse(arrayList.contains(1000));
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Contents all items from external Collection")
    void containAll() {
        ArrayList<Integer> newList1 = new ArrayList<>();
        newList1.add(null);
        newList1.add(7);
        newList1.add(8);

        ArrayList<Integer> newList2 = new ArrayList<>();
        newList2.add(null);
        newList2.add(2);
        newList2.add(3);

        Assertions.assertFalse(arrayList.containsAll(newList1));
        Assertions.assertTrue(arrayList.containsAll(newList2));
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Index of the item")
    void indexOf() {
        Assertions.assertEquals(4, arrayList.indexOf(3));
        Assertions.assertEquals(0, arrayList.indexOf(null));
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Last index of the item")
    void lastIndexOf() {
        Assertions.assertEquals(4, arrayList.lastIndexOf(3));
        Assertions.assertEquals(5, arrayList.lastIndexOf(null));
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Remove first occurrence of the item")
    void remove() {
        Integer val4 = 10;
        Assertions.assertFalse(arrayList.remove(val4));
        Integer val5 = 500;
        Assertions.assertFalse(arrayList.remove(val5));

        Integer val1 = null;
        Assertions.assertTrue(arrayList.remove(val1));
        Assertions.assertEquals("[1, 2, null, 3, null]", arrayList.toString());

        Assertions.assertTrue(arrayList.remove(val1));
        Assertions.assertEquals("[1, 2, 3, null]", arrayList.toString());

        Integer val2 = 1;
        Assertions.assertTrue(arrayList.remove(val2));
        Assertions.assertEquals("[2, 3, null]", arrayList.toString());

        Integer val3 = 2;
        Assertions.assertTrue(arrayList.remove(val3));
        Assertions.assertEquals("[3, null]", arrayList.toString());

        Assertions.assertTrue(arrayList.remove(val1));
        Assertions.assertEquals("[3]", arrayList.toString());

        Integer val6 = 3;
        Assertions.assertTrue(arrayList.remove(val6));
        Assertions.assertEquals("[]", arrayList.toString());

        Assertions.assertFalse(arrayList.remove(val1));
        Assertions.assertEquals("[]", arrayList.toString());
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Remove an item by index")
    void removeByIndex() {
        Assertions.assertEquals(3, arrayList.remove(4));
        Assertions.assertNull(arrayList.remove(0));
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Remove all items that external Collection contains")
    void removeAll() {
        ArrayList<Integer> newList1 = new ArrayList<>();
        newList1.add(null);
        newList1.add(7);
        newList1.add(8);

        ArrayList<String> newList2 = new ArrayList<>();
        newList2.add("text");

        Assertions.assertTrue(arrayList.removeAll(newList1));
        Assertions.assertEquals("[1, 2, 3]", arrayList.toString());

        Assertions.assertFalse(arrayList.removeAll(newList2));
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Retain only those items that external Collection contains")
    void retainAll() {
        ArrayList<Integer> newList1 = new ArrayList<>();
        newList1.add(null);
        newList1.add(1);
        newList1.add(2);
        newList1.add(3);

        Assertions.assertFalse(arrayList.retainAll(newList1));
        Assertions.assertEquals("[null, 1, 2, null, 3, null]", arrayList.toString());

        ArrayList<Integer> newList2 = new ArrayList<>();
        newList2.add(null);
        newList2.add(3);

        Assertions.assertTrue(arrayList.retainAll(newList2));
        Assertions.assertFalse(arrayList.retainAll(newList2));
        Assertions.assertEquals("[null, null, 3, null]", arrayList.toString());
    }

    @Test
    @DisplayName("Export list to array")
    void toArray() {
        Object[] array;
        array = arrayList.toArray();
        Assertions.assertArrayEquals(array, new Integer[]{null, 1, 2, null, 3, null});
    }

    @Test
    @DisplayName("Export list to array with casting")
    void toArrayWithCasting() {
        Integer[] array = new Integer[0];
        array = arrayList.toArray(array);
        Assertions.assertArrayEquals(array, new Integer[]{null, 1, 2, null, 3, null});
    }

    @Test
    @DisplayName("Index out of bounds")
    void shouldThrowIndexOutOfBoundsException() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> arrayList.get(7));
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> arrayList.get(-5));
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> arrayList.set(7, 0));
    }
}