package ua.com.alevel;

import org.junit.jupiter.api.*;
import ua.com.alevel.dynamicarray.DynamicArray;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DynamicArrayTest {
    DynamicArray dynamicArray = new DynamicArray();

    @Test
    @Order(1)
    public void shouldAddObjectToDynamicArray() {
        dynamicArray.add("a");
        dynamicArray.add("b");
        Assertions.assertEquals("a", dynamicArray.getbyindex(0));
    }

    @Test
    @Order(2)
    public void shouldDynamicArrayAddIncrementSize() {
        int expected = dynamicArray.Size() + 1;
        dynamicArray.add("c");
        Assertions.assertEquals(expected, dynamicArray.Size());
    }

    @Test
    @Order(3)
    public void shouldDynamicArrayDeleteObject() {
        dynamicArray.add("d");
        dynamicArray.add("e");
        dynamicArray.add("f");
        String expected = (String) dynamicArray.getbyindex(0);
        dynamicArray.delete(0);
        Assertions.assertNotEquals(expected, dynamicArray.getbyindex(0));
    }

    @Test
    @Order(4)
    public void shouldDynamicArrayDeleteDecrementSize() {
        int expected = dynamicArray.Size() - 1;
        dynamicArray.delete(1);
        Assertions.assertEquals(expected, dynamicArray.Size());
    }
}