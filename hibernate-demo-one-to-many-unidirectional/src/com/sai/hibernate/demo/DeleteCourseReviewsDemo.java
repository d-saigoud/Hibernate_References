package com.sai.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.sai.entity.Course;
import com.sai.entity.Instructor;
import com.sai.entity.InstructorDetail;
import com.sai.entity.Review;

public class DeleteCourseReviewsDemo {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration()
										.configure("hibernate.cfg.xml")
										.addAnnotatedClass(Instructor.class)
										.addAnnotatedClass(InstructorDetail.class)
										.addAnnotatedClass(Course.class)
										.addAnnotatedClass(Review.class)
										.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			
			//start a transaction
			session.beginTransaction();
			
			//get the course
			int courseId = 11;
			Course course = session.get(Course.class, courseId);
			
			//print the course
			System.out.println("Deleting Course: " + course);
			
			//print the course reviews
			System.out.println("Course reviews: " + course.getReviews());
			
			session.delete(course);
			
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
