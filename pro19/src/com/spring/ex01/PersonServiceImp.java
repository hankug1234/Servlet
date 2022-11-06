package com.spring.ex01;

public class PersonServiceImp implements PersonService{
	private String name;
	private int age;
	
	public PersonServiceImp() {
		
	}
	
	public PersonServiceImp(String name) {
		this.name = name;
	}
	
	public PersonServiceImp(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void sayHello() {
		System.out.println("name : "+this.name);
		System.out.println("age : "+this.age);
	}

}
