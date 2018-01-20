package com.sai.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.sai.entity.Course;
import com.sai.entity.Instructor;
import com.sai.entity.InstructorDetail;
import com.sai.entity.Student;

public class FetchJoinDemo {

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
			
			Query<Instructor> query = session.createQuery("select i from Instructor i "
															+ "join fetch i.courses "
															+ "where i.id=:instructorId", 
															Instructor.class);
			
			query.setParameter("instructorId", instructorId);
			
			//executing the query to get instructor
			Instructor instructor = query.getSingleResult();
			
			System.out.println("Instructor: " + instructor);
			
			//commit transaction
			session.getTransaction().commit();
			
			session.close();

			//get courses for the instructor
			System.out.println("Courses: " + instructor.getCourses());
			
			System.out.println("Done!");
			
		}
		finally {
			
			//session.close();
			
			factory.close();
		}
		

	}

}
