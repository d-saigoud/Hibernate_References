package com.sai.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.sai.entity.Student;

public class DeleteStudentDemo {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			
			session = factory.getCurrentSession();
			
			session.beginTransaction();
			
			Student retrievedStudent = session.get(Student.class, 1);
			
			System.out.println("Retrieved Student: " + retrievedStudent);
			
			//Delete 1
			session.delete(retrievedStudent);
			
			//Delete 2
			session.createQuery("DELETE FROM Student where firstName='Paul'").executeUpdate();
			
			session.getTransaction().commit();
			
		}
		finally {
			factory.close();
		}
		

	}

}
