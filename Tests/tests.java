package Tests;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Util.ArrayList;
import Util.HashMap;

class tests {
	
	// White box testing for hashmap object
	
	// default constructor test
    @Test
    public void testHashMapDefaultConstructor() {
        
    	HashMap<String> map = new HashMap<>();
        
    	// map should be empty and contain no keys/values
        assertEquals(0, map.Size());
        assertNull(map.Get("test"));
        
    }
    
    // test the hashmap explicit size constructor
    @Test
    public void testLengthConstructor() {
    	
    	// create new hash map with 20 buckets
        HashMap<String> map = new HashMap<>(20);
        
        assertEquals(0, map.Size());
        assertNull(map.Get("test"));
        
    }
    
    // test putting an item in the map
    @Test
    public void testPutGetItem() {
    	
        HashMap<String> map = new HashMap<>();
        map.Put("key", "value");
        assertEquals(map.Size(), 1);
        assertEquals("value", map.Get("key"));
        
        // test overwriting
        map.Put("key", "newvalue");
        assertEquals(map.Size(), 1);
        assertEquals("newvalue", map.Get("key"));
        
    }

    // test the remove function
    @Test
    public void testPutRemoveGet() {
    	
        HashMap<String> map = new HashMap<>();
        
        map.Put("keya", "valuea");
        map.Put("keyb", "valueb");

        assertEquals(map.Size(), 2);
        assertEquals("valuea", map.Get("keya"));
        
        // test removing
        map.Remove("keya");
        
        assertEquals(map.Size(), 1);
        assertNull(map.Get("keya"));
        
    }
    
    // test load factor and rehash function
    @Test
    public void testPutRehash() {

        HashMap<String> map = new HashMap<>();
        
        // trigger rehash by inserting more items than buckets (default buckets is 8)
        for (int i = 0; i < 15; i++) {
            map.Put("key" + i, "value" + i);
        }
        
        assertEquals(15, map.Size());
        
        // make sure all items are still present after rehash
        for (int i = 0; i < 15; i++) {
            assertEquals("value" + i, map.Get("key" + i));
        }
        
    }
    
    // test that it doesn't return nonexistent item
    @Test
    public void testPutGetNonexistantKey() {

        HashMap<String> map = new HashMap<>();

        map.Put("keya", "a");
        assertNull(map.Get("keyb"));
    }
    
    // test contains func
    @Test
    public void testContainsKey() {

        HashMap<String> map = new HashMap<>();

        map.Put("keya", "a");
        assertTrue(map.Contains("keya"));
    }
    
    // test toArray function
    @Test
    public void testToArrayFunc() {

        HashMap<String> map = new HashMap<>();
        
        map.Put("keya", "a");
        map.Put("keyb", "b");
        map.Put("keyc", "c");
        
        Object[] array = map.toArray();
        assertEquals(array.length, 3);
        
        boolean a = false, b = false, c = false;
        for (Object val : array) {
            if ("a".equals(val)) a = true;
            if ("b".equals(val)) b = true;
            if ("c".equals(val)) c = true;
        }
        
        assertTrue(a);
        assertTrue(b);
        assertTrue(c);
        
    }
    
    @Test
    public void testIterator() {
        HashMap<String> map = new HashMap<>();
        
        map.Put("keya", "a");
        map.Put("keyb", "b");
        map.Put("keyc", "c");
        
        int count = 0;
        for (String value : map) {
            assertNotNull(value);
            count++;
        }
        
        assertEquals(count,3);
    }
 
    @Test
    public void testIteratorIsEmpty() {
    	HashMap<String> map = new HashMap<>();
        Iterator<String> iter = map.iterator();
        assertFalse(iter.hasNext());
    }
    
    private ArrayList<Integer> list;
    
    @BeforeEach
    public void setUp() {
        list = new ArrayList<>();
    }
    
    @Test
    public void testNewListIsEmpty() {
        assertEquals(0, list.Length());
    }
    
    @Test
    public void testPushSingleElement() {
        list.Push(42);
        assertEquals(1, list.Length());
        assertEquals(42, list.Get(0));
    }
    
    @Test
    public void testPushMultipleElements() {
        list.Push(1);
        list.Push(2);
        list.Push(3);
        assertEquals(3, list.Length());
        assertEquals(1, list.Get(0));
        assertEquals(2, list.Get(1));
        assertEquals(3, list.Get(2));
    }
    
    @Test
    public void testPushNullElement() {
        list.Push(null);
        assertEquals(1, list.Length());
        assertNull(list.Get(0));
    }
    
    @Test
    public void testExpandBeyondInitialCapacity() {
        for (int i = 0; i < 9; i++) {
            list.Push(i);
        }
        assertEquals(9, list.Length());
        for (int i = 0; i < 9; i++) {
            assertEquals(i, list.Get(i));
        }
    }
    
    @Test
    public void testExpandMultipleTimes() {
        // Push enough elements to trigger multiple expansions
        for (int i = 0; i < 100; i++) {
            list.Push(i);
        }
        assertEquals(100, list.Length());
        for (int i = 0; i < 100; i++) {
            assertEquals(i, list.Get(i));
        }
    }
    
    @Test
    public void testGetNegativeIndex() {
        list.Push(1);
        assertThrows(IndexOutOfBoundsException.class, () -> list.Get(-1));
    }
    
    @Test
    public void testGetIndexTooLarge() {
        list.Push(1);
        assertThrows(IndexOutOfBoundsException.class, () -> list.Get(10));
    }
    
    @Test
    public void testGetAtBoundary() {
        list.Push(1);
        assertEquals(1, list.Get(0));
    }
    
    @Test
    public void testGetFromEmptyList() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.Get(0));
    }
    
    @Test
    public void testIteratorOnEmptyList() {
        Iterator<Integer> iter = list.iterator();
        assertFalse(iter.hasNext());
    }
    
    @Test
    public void testIteratorHasNext() {
        list.Push(1);
        list.Push(2);
        Iterator<Integer> iter = list.iterator();
        assertTrue(iter.hasNext());
    }
    
    @Test
    public void testIteratorNext() {
        list.Push(10);
        list.Push(20);
        list.Push(30);
        Iterator<Integer> iter = list.iterator();
        assertEquals(10, iter.next());
        assertEquals(20, iter.next());
        assertEquals(30, iter.next());
    }
    
    @Test
    public void testIteratorThrowsWhenExhausted() {
        list.Push(1);
        Iterator<Integer> iter = list.iterator();
        iter.next();
        assertThrows(NoSuchElementException.class, iter::next);
    }
    
    @Test
    public void testIteratorWithForEachLoop() {
        list.Push(1);
        list.Push(2);
        list.Push(3);
        int sum = 0;
        for (Integer i : list) {
            sum += i;
        }
        assertEquals(6, sum);
    }
    
    @Test
    public void testMultipleIterators() {
        list.Push(1);
        list.Push(2);
        Iterator<Integer> iter1 = list.iterator();
        Iterator<Integer> iter2 = list.iterator();
        assertEquals(1, iter1.next());
        assertEquals(1, iter2.next());
        assertEquals(2, iter1.next());
        assertEquals(2, iter2.next());
    }
    
    // String type tests
    @Test
    public void testWithStringType() {
        ArrayList<String> strList = new ArrayList<>();
        strList.Push("Hello");
        strList.Push("World");
        assertEquals(2, strList.Length());
        assertEquals("Hello", strList.Get(0));
        assertEquals("World", strList.Get(1));
    }
    
    // Edge case tests
    @Test
    public void testPushAfterExpansion() {
        for (int i = 0; i < 8; i++) {
            list.Push(i);
        }
        list.Push(8); // expansion
        list.Push(9); // post expansion
        assertEquals(10, list.Length());
        assertEquals(9, list.Get(9));
    }
    
    @Test
    public void testIteratorAfterExpansion() {
        for (int i = 0; i < 20; i++) {
            list.Push(i);
        }
        int count = 0;
        for (Integer i : list) {
            assertEquals(count++, i);
        }
        assertEquals(20, count);
    }
    
    @Test
    public void testCustomObjectType() {
        class Person {
            String name;
            Person(String name) { this.name = name; }
        }
        
        ArrayList<Person> people = new ArrayList<>();
        Person p1 = new Person("Alice");
        Person p2 = new Person("Bob");
        people.Push(p1);
        people.Push(p2);
        
        assertEquals(2, people.Length());
        assertEquals("Alice", people.Get(0).name);
        assertEquals("Bob", people.Get(1).name);
    }

}


