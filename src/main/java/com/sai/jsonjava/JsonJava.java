package com.sai.jsonjava;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonJava {
	
	public static void main(String[] args) throws Exception {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = null;
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Business", "root", "mysqladmin");
		
		Statement statement = conn.createStatement();
		ResultSet resultSet =  statement.executeQuery("select * from CustomerInfo where purchasedDate=CURDATE() and Location ='Asia' LIMIT 1");
		CustomerDetails customerDetails = new CustomerDetails();
		while(resultSet.next()) {
			
			customerDetails.setCourseName(resultSet.getString(1));
			customerDetails.setPurchaseDate(resultSet.getString(2));
			customerDetails.setAmount(resultSet.getInt(3));
			customerDetails.setLocation(resultSet.getString(4));
			
		}
		conn.close();
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue( new File("\\Users\\sv\\Documents\\spring-course\\jsonjava\\customerdetails.json"), customerDetails);
	}

}
