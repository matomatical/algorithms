package com.matomatical.ads;

public class IndexException extends RuntimeException {
	
	public IndexException(String message){
		super(message);
	}

	public void printMessage(){
		System.err.println(super.getMessage());
	}
}