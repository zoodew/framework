package com.kh.di.pet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 230310 4교시
//@Data				// 게터세터
//@NoArgsConstructor	// 기본 생성자
//@AllArgsConstructor	// 매개변수 생성자
public class Dog extends Pet{
//	private String name;
	
	public Dog() {
	}
	
	public Dog(String name) {
		super(name);
	}
	
	@Override		// Pet클래스에 있는 추상메소드를 Dog에 맞게 재정의
	public String bark() {
		return "멍멍!";
	}
}
