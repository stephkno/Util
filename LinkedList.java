package Util;
import java.util.Iterator;

public class LinkedList<T> implements Iterable<T> {
	
	private Node<T> first;
	private int numItems = 0;
	
	public class Node<T>{
		public Node(T value) {
			data = value;
		}
		T data;
		Node<T> next;
	}
	
	public Boolean Contains(T value) {
		for (T item : this) if(item == value) return true;
		return false;
	}
	
	public void Push(T value) {
		
		Node<T> n = new Node(value);
		
		if(first == null) {
			first = n;
			numItems++;
			return;
		}

		Node<T> curr = first;
		
		while(curr.next != null) curr = curr.next;
	
		curr.next = n;
		numItems++;
		
	}
	
	public T Get(int i) {
		
		if(first == null) throw new IndexOutOfBoundsException("Index: " + i );
		
		if(i > numItems-1) return null;
		
		int c = 0;
		Node<T> curr = first;
	
		while(c < i && curr.next != null) {
			c++;
			curr = curr.next;
		}
		
		if(curr == null) return null;
	
		return curr.data;
		
		
	}
	
	public void Remove(int i) {
		
		if(i < 0 || i > numItems || first == null) return;
		
		if(i == 0) {
			first = first.next;
			numItems--;
			return;
		}

		Node<T> curr = first;

	    for (int c = 0; c < i - 1; c++) curr = curr.next;    
	    
		if(curr.next != null) {
	    	curr.next = curr.next.next;
	    	numItems--;
	    }
	
	}
	
	public int Length() {
		return numItems;
	}
	
	public String toString() {
		 Node<T> curr = first;
		 String out = "";
		 
		 while (curr != null) {
			 out += curr.data + " ";
			 curr = curr.next;
		 }
		 
		 return out;
	}
	
	@Override
	public Iterator<T> iterator(){
		
		return new Iterator<T>() {
			Node<T> item = first;
			
			@Override
			public boolean hasNext() { return item != null; }
			
			@Override
			public T next() {
				
				if(!hasNext()) throw new java.util.NoSuchElementException();
				
				Node<T> curr = item;
				item = item.next;
				return curr.data;
				
			}
		};
	}
}
