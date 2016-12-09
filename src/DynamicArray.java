package com.matomatical.ads;

public class DynamicArray<Item>{

	private static final int INITIAL_SIZE = 1;

	private Item[] elements;
	private int capacity, n;
	
	@SuppressWarnings("unchecked")
	public DynamicArray() {
		
		// overcoming generic array creation error with a cast,
		// as per lectures and http://stackoverflow.com/a/2924453/5395650
		elements = (Item[]) new Object[INITIAL_SIZE];

		capacity = INITIAL_SIZE;
		n = 0;
	}

	public void add(Item data) {
		elements[n++] = data;
		if(n==capacity){
			resize(capacity * 2);
		}
	}

	public Item remove() {
		Item data = elements[--n];
		elements[n] = null;

		if(n <= capacity / 4){
			resize(capacity / 2);
		}
		return data;
	}

	public Item get(int i) throws IndexException {
		if(i < 0 || i >= n){
			throw new IndexException("Index " + i + " out of bounds");
		}
		
		return elements[i];
	}

	public void set(int i, Item data) throws IndexException {
		if(i < 0 || i > n){
			throw new IndexException("Index " + i + " out of bounds");
		}

		// if what would be the next element is indexed,
		// we should automatically add an index
		if(i == n){
			this.add(data);

		// otherwise just modify as usual
		} else {
			elements[i] = data;
		}
	}
	
	@SuppressWarnings("unchecked")
	private void resize(int capacity) {

		// don't let the capacity ever become zero
		capacity = (capacity < 1 ? 1 : capacity);
		
		
		// overcoming generic array creation error with a cast,
		// as per lectures and http://stackoverflow.com/a/2924453/5395650
		Item[] elements = (Item[]) new Object[capacity];
		this.capacity = capacity;

		for(int i = 0; i < n; i++){
			elements[i] = this.elements[i];
		}
		this.elements = elements;
	}

	/** The current array length (the number of array elements) */
	public int length() {
		return n;
	}

	/** The current array capacity (size reachable before resize) */
	public int capacity() {
		return capacity;
	}
}