package com.matomatical.ads;

public class NotFoundException extends RuntimeException {
	
	public NotFoundException(String message){
		super(message);
	}

	public void printMessage(){
		System.err.println(super.getMessage());
	}
}