package com.sai.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.sai.entity.Course;
import com.sai.entity.Instructor;
import com.sai.entity.InstructorDetail;
import com.sai.entity.Review;
import com.sai.entity.Student;

public class AddCoursesForStudentDemo {

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

			//get the student from database
			int studentId = 2;
			Student student = session.get(Student.class, studentId);
			
			System.out.println("Student retrieved: " + student);
			System.out.println("Courses for this Student: " + student.getCourses());
			
			//create more courses
			Course course1 = new Course("Android App Development");
			Course course2 = new Course("J2EE Complete Guide");
			
			//add student to courses
			course1.addStudent(student);
			course2.addStudent(student);
			
			//save courses
			System.out.println("Saving courses...");
			session.save(course1);
			session.save(course2);
			
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
