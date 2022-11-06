package com.spring.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CalcTest {
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("AOPTest.xml");
		Calculator cal = (Calculator) context.getBean("test");
		cal.add(100, 20);
	}

}
