package com.cogSci;

public class GetInQueue {
	
	private Object[] arr;
	private int size;
	
	GetInQueue(int maxSize){
		arr = new Object[maxSize];
	}
	
	public int size(){
		return size;
	}
	
	public void push(Object o){
		for( int i = arr.length-2; i <= 0; i--){
			arr[i+1] = arr[i];
			arr[0] = o;
		}
	}
	
	public Object get(int index){
		return arr[index];
	}
	
	public void clear(){
		size = 0;
	}
}