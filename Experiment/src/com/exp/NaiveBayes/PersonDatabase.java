package com.exp.NaiveBayes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.sql.*;

import com.mysql.jdbc.Statement;

public class PersonDatabase {
    // - - > DECLARATIONS
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/people_entries";
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
        System.out.println("Concluding database transfer...");
    }
}
