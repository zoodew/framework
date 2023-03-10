package com.kh.di.pet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 230310 5교시
@Data				// 게터세터
@NoArgsConstructor	// 기본 생성자
@AllArgsConstructor	// 매개변수 생성자
public abstract class Pet {
	
// 230310 5교시
	// Dog, Cat의 공통적인 부분을 추상메소드로 만들어줘야 사용가능
	private String name;
	
	public abstract String bark();
	
	// Dog, Cat 클래스에서 Pet클래스를 상속하도록 만들기
}
