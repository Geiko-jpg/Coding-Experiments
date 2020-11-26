package com.exp.NaiveBayes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.sql.*;

import com.mysql.jdbc.Statement;

public class PersonDatabase {
    // - - > DECLARATIONS
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; // primary driver for mysql
    private static final String DB_URL = "jdbc:mysql://localhost/people_entries"; // target database
    private static final String USERNAME = "root";
    private static final String PASS = "Verifone1";
    private PersonCredentials pCreds;
    
    // - - > Constructor
    public PersonDatabase(PersonCredentials pCreds) {
    	this.pCreds = pCreds;
    }
    
    // - - > INITIATE LOGGER
    private static final Logger log = Logger.getLogger(PersonDatabase.class.getName()); // optional

	public void WriteToDatabase(){ //PersonCredentials personInfo
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
        	
        	String relay = "INSERT INTO members_data " + "VALUES (null, '" + pCreds.getAge() + "', '" + pCreds.getIncome() + "', '" +
        	pCreds.isStudent() + "', '" + pCreds.getCredit_rating() + "', '" + pCreds.isBuys_computer() + "')";
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
	
	public static ArrayList<PersonCredentials> readDatabase() {
		ArrayList<PersonCredentials> transfer = new ArrayList<PersonCredentials>();
		Connection conn = null;
		Statement stmt = null;
		
		try {
			Class.forName(JDBC_DRIVER);
			System.out.println("Connecting to database for extraction...");
			
			conn = DriverManager.getConnection(DB_URL, USERNAME, PASS);
			System.out.println("Connection established successfully...");
			System.out.println("\nExtracting Data...");
			
			stmt = (Statement)conn.createStatement();
			String sql = "SELECT member_age, member_income, member_student, member_creditrating, member_buyscomputer FROM members_data"; // sql command / query
			ResultSet rs = stmt.executeQuery(sql);
			
			// LOOP EXTRACTION
			while(rs.next()) {
				String age = rs.getString("member_age");
				String income = rs.getString("member_income");
				String student = rs.getString("member_student");
				String creditRating = rs.getString("member_creditrating");
				String buysComputer = rs.getString("member_buyscomputer");
				
				PersonCredentials buildEntry = new PersonCredentials.PersonCredentialsBuilder()
						.setAge(age)
						.setIncome(income)
						.setStudent(student)
						.setCreditRating(creditRating)
						.setBuysComputer(buysComputer)
						.build();
				transfer.add(buildEntry);
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
		return transfer;
	}
}
