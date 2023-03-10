package com.kh.di.owner;

import com.kh.di.pet.Dog;
import com.kh.di.pet.Pet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 230310 4교시
@Data				// 게터세터
@NoArgsConstructor	// 기본 생성자
@AllArgsConstructor	// 매개변수 생성자
public class Owner {
		// ctrl 1 create new JUnit Test case for 'Owner.java' 테스트 만들기
	
	private String name;
	
	private int age;
	
	private Pet pet;
	

}
