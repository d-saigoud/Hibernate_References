package com.sai.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.sai.entity.Course;
import com.sai.entity.Instructor;
import com.sai.entity.InstructorDetail;
import com.sai.entity.Review;

public class CreateCourseReviewsDemo {

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
			
			//create a course
			Course course = new Course("React JS Development");
			
			//add some reviews
			Review review1 = new Review("Great Course");
			Review review2 = new Review("Great Explaination");
			
			course.addReview(review1);
			course.addReview(review2);
			
			//save the course and use cascade all
			System.out.println("Saving Course: " + course);
			System.out.println("Reviews for this course: " + course.getReviews());
			session.save(course);
			
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
