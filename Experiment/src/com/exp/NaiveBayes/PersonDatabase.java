package com.exp.NaiveBayes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.sql.*;

import com.mysql.jdbc.Statement;

public class PersonDatabase {
    // - - > DECLARATIONS
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; // primary driver for mysql
    private static final String DB_URL = "jdbc:mysql://localhost/people_entries"; // target database
    private static final String USERNAME = "root";
    private static final String PASS = "Verifone1";
    
    // - - > INITIATE LOGGER
    private static final Logger log = Logger.getLogger(PersonDatabase.class.getName()); // optional

	public void WriteToDatabase(String fName, String lName){ //PersonCredentials personInfo
        Connection conn = null;
        Statement statement = null;
        
        try {
        	// register JDBC driver and assert connection to database
        	Class.forName(JDBC_DRIVER);
        	conn = DriverManager.getConnection(DB_URL, USERNAME, PASS);
        	System.out.println("Connected to database (people_entries) successfully");
        	
        	// data insertion
        	System.out.println("Initiate data insertion to database");
        	statement = (Statement) conn.createStatement();
        	
        	String relay = "INSERT INTO members " + "VALUES (null, '" + fName + "', '" + lName + "')";
        	statement.executeUpdate(relay);
        	
        }catch(SQLException se) {
        	se.printStackTrace();
        }catch(Exception e) {
        	e.printStackTrace();
        }finally {
        	try {
        		if(statement != null) {
        			conn.close();
        		}
        	}catch(SQLException se) {
        		// pass
        	}
        	try {
        		if(conn != null) {
        			conn.close();
        		}
        	}catch(SQLException se) {
        		se.printStackTrace();
        	}
        }
        System.out.println("Concluding database transfer...\n");
    }
	
	public void readDatabase() {
		Connection conn = null;
		Statement stmt = null;
		
		try {
			Class.forName(JDBC_DRIVER);
			System.out.println("Connecting to database for extraction...");
			
			conn = DriverManager.getConnection(DB_URL, USERNAME, PASS);
			System.out.println("Connection established successfully...");
			System.out.println("\nExtracting Data...");
			
			stmt = (Statement)conn.createStatement();
			String sql = "SELECT id, firstname, lastname FROM members"; // sql command / query
			ResultSet rs = stmt.executeQuery(sql);
			
			// LOOP EXTRACTION
			while(rs.next()) {
				int id = rs.getInt("id");
				String firstName = rs.getString("firstname");
				String lastName = rs.getString("lastname");
				
				// Display Values
				System.out.print("ID: " + id);
				System.out.print(" | FIRSTNAME: " + firstName);
				System.out.println(" | LASTNAME: " + lastName + "\n");
			}
			rs.close();
		}catch(SQLException se) {
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(stmt != null) {
					conn.close();
				}
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}
		System.out.println("END PROCESS ~~");
	}
}
