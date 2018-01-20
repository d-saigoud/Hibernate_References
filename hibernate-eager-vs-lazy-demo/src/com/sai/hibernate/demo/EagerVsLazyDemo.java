package com.sai.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.sai.entity.Course;
import com.sai.entity.Instructor;
import com.sai.entity.InstructorDetail;
import com.sai.entity.Student;

public class EagerVsLazyDemo {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration()
										.configure("hibernate.cfg.xml")
										.addAnnotatedClass(Instructor.class)
										.addAnnotatedClass(InstructorDetail.class)
										.addAnnotatedClass(Course.class)
										.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			
			//start a transaction
			session.beginTransaction();
			
			//get instructor from DB
			int instructorId = 1;
			Instructor instructor = session.get(Instructor.class, instructorId);
			
			System.out.println("Instructor: " + instructor);
			
			//get courses for the instructor
			System.out.println("Courses: " + instructor.getCourses());
			
			
			//commit transaction
			session.getTransaction().commit();
			
//			session.close();

//			//get courses for the instructor
//			System.out.println("Courses: " + instructor.getCourses());
			
			System.out.println("Done!");
			
		}
		finally {
			
			//session.close();
			
			factory.close();
		}
		

	}

}
