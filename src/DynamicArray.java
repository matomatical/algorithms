package com.matomatical.ads;

public class DynamicArray<Data>{

	private static final int INITIAL_SIZE = 1;

	private Data[] elements;
	private int capacity, n;

	public DynamicArray(){
		
		// overcoming generic array creation error with a cast,
		// as per lectures and http://stackoverflow.com/a/2924453/5395650
		elements = (Data[]) new Object[INITIAL_SIZE];

		capacity = INITIAL_SIZE;
		n = 0;
	}

	public Data get(int i){
		if(i < n){
			return elements[i];
		} else {
			throw new IndexException("Index " + i + " out of bounds");
		}
	}

	public void set(int i, Data data){
		if(i < n){
			elements[i] = data;
		} else {
			throw new IndexException("Index " + i + " out of bounds");
		}
	}

	public void add(Data data){
		elements[n++] = data;
		if(n==capacity){
			resize(n * 2);
		}
	}

	public Data remove(){
		Data data = elements[--n];
		elements[n] = null;

		if(n <= capacity / 4){
			resize(n / 2);
		}
		return data;
	}

	private void resize(int capacity){
		
		Data[] elements = (Data[]) new Object[capacity];
		for(int i = 0; i < n; i++){
			elements[i] = this.elements[i];
		}
		this.elements = elements;
		this.capacity = capacity;
	}
}