package com.sai.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.sai.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			
			Student student1 = new Student("ABC", "DEF", "abc@abc.com");
			Student student2 = new Student("GHI", "JKL", "ghi@abc.com");
			Student student3 = new Student("MNO", "PQR", "mno@abc.com");
			
			session.beginTransaction();
			session.save(student1);
			session.save(student2);
			session.save(student3);
			session.getTransaction().commit();
			
		}
		finally {
			factory.close();
		}
		
	}

}
