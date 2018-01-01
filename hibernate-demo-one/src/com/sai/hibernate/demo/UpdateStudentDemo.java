package com.sai.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.sai.entity.Student;

public class UpdateStudentDemo {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			
			session = factory.getCurrentSession();
			
			session.beginTransaction();
			
			Student retrievedStudent = session.get(Student.class, 1);
			
			System.out.println("Retrieved Student: " + retrievedStudent);
			
			//Update 1
			retrievedStudent.setFirstName("XXXX");
			
			System.out.println("Updated Student: " + retrievedStudent);
			
			//Update 2
			session.createQuery("UPDATE Student set email='xxx@abc.com' where firstName='XXXX'").executeUpdate();
			
			session.getTransaction().commit();
			
			
			
			
			
		}
		finally {
			factory.close();
		}
		

	}

}
