package com.matomatical.ads;

public class TreeException extends RuntimeException {
	
	public TreeException(String message){
		super(message);
	}

	public void printMessage(){
		System.err.println(super.getMessage());
	}
}