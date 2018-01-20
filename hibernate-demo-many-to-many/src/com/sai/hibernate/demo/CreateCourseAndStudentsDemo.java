package com.sai.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.sai.entity.Course;
import com.sai.entity.Instructor;
import com.sai.entity.InstructorDetail;
import com.sai.entity.Review;
import com.sai.entity.Student;

public class CreateCourseAndStudentsDemo {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration()
										.configure("hibernate.cfg.xml")
										.addAnnotatedClass(Instructor.class)
										.addAnnotatedClass(InstructorDetail.class)
										.addAnnotatedClass(Course.class)
										.addAnnotatedClass(Review.class)
										.addAnnotatedClass(Student.class)
										.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			
			//start a transaction
			session.beginTransaction();
			
			//create a course
			Course course = new Course("React JS Development");
			
			
			//save the course and use cascade all
			System.out.println("Saving Course: " + course);
			session.save(course);
			System.out.println("Saved the course");
			
			//create the students
			Student student1 = new Student("Raj", "Kanduri", "raj@abc.com");
			Student student2 = new Student("Bhuvan", "Raj", "bhuvan@abc.com");
			
			//add students to the course
			course.addStudent(student1);
			course.addStudent(student2);
			
			//save the students
			System.out.println("Saving students...");
			session.save(student1);
			session.save(student2);
			System.out.println("Saved Students: " + course.getStudents());
			
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
