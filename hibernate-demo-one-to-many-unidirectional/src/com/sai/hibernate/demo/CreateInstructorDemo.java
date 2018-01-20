package com.sai.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.sai.entity.Course;
import com.sai.entity.Instructor;
import com.sai.entity.InstructorDetail;
import com.sai.entity.Student;

public class CreateInstructorDemo {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration()
										.configure("hibernate.cfg.xml")
										.addAnnotatedClass(Instructor.class)
										.addAnnotatedClass(InstructorDetail.class)
										.addAnnotatedClass(Course.class)
										.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			
			
			Instructor instructor = new Instructor("Graham", "Antony", "abc@abc.com");

			InstructorDetail instructorDetail = new InstructorDetail("youtubeChannel", "Playing Cricket, watching movies");
			
			instructor.setInstructorDetail(instructorDetail);
			
			instructorDetail.setInstructor(instructor);
			
			session.beginTransaction();
			
			session.save(instructorDetail);
			
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		}
		finally {
			
			session.close();
			
			factory.close();
		}
		

	}

}
