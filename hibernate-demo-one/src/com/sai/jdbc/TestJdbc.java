package com.sai.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {

	public static void main(String[] args) {
		
		try {
			
			//Class.forName("");
			
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hb_student_tracker?useSSL=false", "hbstudent", "hbstudent");
			
			conn.close();
			
		}
		catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
	}
	
}
