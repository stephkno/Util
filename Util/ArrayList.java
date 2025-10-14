package Util;
import java.util.Iterator;

// custom (simple) arraylist class
public class ArrayList<T> implements Iterable<T> {
	private T[] array;

	// index of last item in list
	private int listSize = 0;
	final private int initSize = 8;

	public ArrayList(){
		array = (T[])new Object[initSize];
	}
	
	private void Expand() {
	
		T[] newArray = (T[])new Object[array.length*2];
		
		for(int i = 0; i < array.length; i++) newArray[i] = array[i];
		
		array = newArray;
		
	}

	// push new T to internal array
	public void Push(T item) {
		if(listSize >= array.length) Expand();
		array[listSize++] = item;
	}
	
	// return item at index i
	public T Get(int i) {
		
		if(listSize == 0) throw new IndexOutOfBoundsException("Index: " + i );

		if(i > array.length || i < 0) throw new IndexOutOfBoundsException("Index: " + i );
		
		return array[i];
	}
	
	public int Length() {
		return listSize;
	}
	
	// implements a list iterator
	@Override
	public Iterator<T> iterator(){
		
		return new Iterator<T>() {
			int item = 0;
			
			@Override
			public boolean hasNext() { return item < listSize; }
			
			@Override 
			public T next() {
				if(!hasNext()) throw new java.util.NoSuchElementException();
				return array[item++];
			}
		};
		
	}

}