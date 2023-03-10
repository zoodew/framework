package com.kh.di.pet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 230310 5교시
//@Data
@NoArgsConstructor
//@AllArgsConstructor
public class Cat extends Pet {

	//private String name;
	
	public Cat(String name) {
		super(name);	// 부모 클래스(Pet.java)의
	}
		
	@Override	// Pet클래스에 있는 추상메소드를 Cat에 맞게 재정의
	public String bark() {
		return "야옹~~";
	}

}
