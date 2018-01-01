package com.sai.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.sai.entity.Instructor;
import com.sai.entity.InstructorDetail;
import com.sai.entity.Student;

public class CreateDemo {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration()
										.configure("hibernate.cfg.xml")
										.addAnnotatedClass(Instructor.class)
										.addAnnotatedClass(InstructorDetail.class)
										.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			
			
			InstructorDetail instructorDetail = new InstructorDetail("youtubeChannel", "Playing Cricket, watching movies");
			
			Instructor instructor = new Instructor("Sai Goud", "Durgappagari", "abc@abc.com");
			
			instructor.setInstructorDetail(instructorDetail);
			
			instructorDetail.setInstructor(instructor);
			
			session.beginTransaction();
			
			session.save(instructorDetail);
			
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		}
		finally {
			factory.close();
		}
		

	}

}
