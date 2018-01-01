package com.sai.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.sai.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			
			session.beginTransaction();
			
			List<Student> studentsList = session.createQuery("from Student").getResultList();
			
			//List<Student> studentsList = session.createQuery("from Student where firstName='ABC' OR lastName='DEF'").getResultList();
			
			//List<Student> studentsList = session.createQuery("from Student where firstName LIKE '%i%' ").getResultList();
			
			session.getTransaction().commit();
			
			for(Student student : studentsList) {
				
				System.out.println(student);
				
			}
			
		}
		finally {
			factory.close();
		}
		

	}

}
