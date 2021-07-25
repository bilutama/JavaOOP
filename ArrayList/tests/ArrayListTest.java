import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.academits.biluta.array_list.ArrayList;

import org.junit.jupiter.api.Assertions;

class ArrayListTest {
    @org.junit.jupiter.api.Test
    @DisplayName("Adding some items to ArrayList")
    void add() {
        ArrayList<Integer> arrayList = new ArrayList<>();

        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(null);
        arrayList.add(0, 3);

        Assertions.assertEquals(4, arrayList.size());
        Assertions.assertEquals("[3, 1, 2, null]", arrayList.toString());
        Assertions.assertEquals(3,arrayList.get(0));
        Assertions.assertEquals(1,arrayList.get(1));
        Assertions.assertNull(arrayList.get(3));
        Assertions.assertEquals(2,arrayList.get(2));
    }

    @Test
    @DisplayName("Item with index out of bounds")
    void shouldThrowIndexOutOfBoundsException(){
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> arrayList.get(1));
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Removing items")
    void remove() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(null);
        arrayList.add(3);

        Assertions.assertEquals(2, arrayList.remove(1));
        Assertions.assertEquals(3, arrayList.get(2));
    }


}