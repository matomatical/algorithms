package com.matomatical.ads;

public class EmptyException extends RuntimeException {
	
	public EmptyException(String message){
		super(message);
	}

	public void printMessage(){
		System.err.println(super.getMessage());
	}
}