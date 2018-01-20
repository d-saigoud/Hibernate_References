package com.sai.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.sai.entity.Course;
import com.sai.entity.Instructor;
import com.sai.entity.InstructorDetail;
import com.sai.entity.Student;

public class CreateCoursesDemo {

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
			
			//create some courses
			Course course1 = new Course("Java Programming");
			Course course2 = new Course("Android App Development");
			Course course3 = new Course("iOS App Development");
			
			//add courses to instructor
			instructor.addCourse(course1);
			instructor.addCourse(course2);
			instructor.addCourse(course3);
			
			//save the courses
			session.save(course1);
			session.save(course2);
			session.save(course3);
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		}
		finally {
			
			session.close();
			
			factory.close();
		}
		

	}

}
