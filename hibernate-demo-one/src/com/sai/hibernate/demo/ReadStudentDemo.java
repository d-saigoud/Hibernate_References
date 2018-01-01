package com.sai.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.sai.entity.Student;

public class ReadStudentDemo {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			
			Student student = new Student("Paul", "Wall", "paul@abc.com");
			session.beginTransaction();
			session.save(student);
			session.getTransaction().commit();
			
			System.out.println("Generated Student id : " + student.getId());
			
			session = factory.getCurrentSession();
			
			session.beginTransaction();
			
			Student retrievedStudent = session.get(Student.class, student.getId());
			
			session.getTransaction().commit();
			
			System.out.println("Retrieved Student: " + retrievedStudent);
			
		}
		finally {
			factory.close();
		}
		

	}

}
