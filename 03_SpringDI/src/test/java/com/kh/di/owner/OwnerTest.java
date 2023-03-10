package com.kh.di.owner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.kh.di.pet.Cat;
import com.kh.di.pet.Dog;

class OwnerTest {

// 230310 4교시 5교시
	// 자바에서 결합도를 낮추는 법
	@Test
	void nothing() {
	}
	
	// 가장 먼저 하던 테스트 = 인스턴스가 
	@Test
	void create() {
	// DI (Dependency Injection) 의존성 주입 테스트
		// 기존에 자바 애플리케이션에서는 다형성을 통해서 객체간의 결합도를 느슨하게 만들어준다.
		// 1) 생성자를 통한 의존성 주입
//		Owner owner = new Owner("문인수" , 20, new Dog("보리"));
//		Owner owner = new Owner("문인수" , 20, new Cat("콜리"));
		Owner owner = new Owner();

		
//		Owner라는 인스턴스는 Dog라는 인스턴스를 참조한다.
//		Owner는 Dog를 의존한다.

		
		owner.setName("문인수");
		owner.setAge(20);
//		// 2) 메소드를 통한 의존성 주입. setter를 통해 주입 한 것
		owner.setPet(new Cat("콜리"));
//			// Owner.java의 private Dog dog; 필요한 인스턴스를 Owner외부(Dog)에서 생성해 넘겨줌(= 의존성 주입)

// 230310 5교시
		// 결합도가 높은 코드
		// "보리"를 "콜리"로 하나의 코드를 수정했는데 바꿀 코드가 많음. Owner.java의 dog도 cat으로, 아래의 코드도 getDog을 getCat으로
		System.out.println(owner);
		System.out.println(owner.getPet());
		System.out.println(owner.getPet().getName());
		
		// 결합도를 낮추는 법 다형성
		// Pet이라는 추상클래스를 만들어서 Cat과 Dog이 상속하도록 만들기
		
	}
	
	
// 230310 5교시
	// 스프링 DI 설정 방법
	@Test
	void contextTest() {
		ApplicationContext context = 
					new GenericXmlApplicationContext("spring/root-context.xml");	// 매개값에 xml파일의 경로를 주면 됨. classpath 를 기준으로
					// classPath : 클래스 파일을 찾는 경로
// 230310 6교시
		// bean의 id를 매개값으로 줌
//		Object owner = context.getBean("moon");	 기본
//		Owner owner = (Owner) context.getBean("moon");
			// 기본적으로는 Object 타입으로 받아와서 원하는 타입으로 받으려면 그 타입으로 형변환 해주기. 
		Owner owner = context.getBean("hong", Owner.class);
			// 형변환 없이 받아오는 코드
		
		System.out.println(owner);
		
		assertThat(context).isNotNull();
		assertThat(owner).isNotNull();
		assertThat(owner.getPet()).isNotNull();
	}

}
