package com.tnsif.jdbcdemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

//1st step


public class JDBC_Connect {

	public static void main(String[] args) {
		
		
		String url = "jdbc:postgresql://localhost:5432/student_portal";
		String username = "postgres";
		String password = "Malini";
		
		
		try {
			
			//Load the driver - 2nd step
			Class.forName("org.postgresql.Driver");
			
			
			//Establishing the connection - 3rd step
			
			Connection con = DriverManager.getConnection(url, username, password);
			
			//Define SQL Query - 4th step
			
			Statement st = con.createStatement();
			
			//Execting the query - 5th step
			
			
			String query = "SELECT * FROM public.student_detail";
			
			//Process the Result - 6th Step
			
			ResultSet rs = st.executeQuery(query);
			
			
			while(rs.next())
				
			{
				String table = rs.getInt("student_id") + ":" 
			              + rs.getString("first_name") + ":" 
			              + rs.getString("last_name") + ":" 
			              + rs.getDate("dob") + ":" 
			              + rs.getInt("age") + ":" 
			              + rs.getString("department") + ":" 
			              + rs.getString("email");
				System.out.println(table);
			}

			
			//close the resources - 7th
			
			rs.close();
			st.close();
			con.close();
			
			
		}
		

		catch(Exception e)
		{
			e.printStackTrace();
		}
		

	}

}

