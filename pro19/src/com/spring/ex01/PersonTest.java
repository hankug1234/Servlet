package com.spring.ex01;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;

public class PersonTest {
	public static void main(String[] args) {
		//BeanFactory factory = new XmlBeanFactory(new FileSystemResource("person.xml"));
		ApplicationContext context = new ClassPathXmlApplicationContext("person.xml");
		PersonService person0 = (PersonService) context.getBean("personService");
		person0.sayHello();
		/*
		PersonService person0 = (PersonService) context.getBean("personService");
		person0.sayHello();
		
		PersonService person = (PersonService) factory.getBean("personService");
		person.sayHello();
		
		PersonService person1 = (PersonService) factory.getBean("personService1");
		person1.sayHello();
		
		PersonService person2 = (PersonService) factory.getBean("personService2");
		person2.sayHello();
		*/
	}

}
