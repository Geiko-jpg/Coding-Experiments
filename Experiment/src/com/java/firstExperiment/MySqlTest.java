
package com.java.firstExperiment;

import java.sql.DriverManager;
import java.lang.System.Logger;
import java.util.*;
import java.sql.*;

/**
 * @author: kyle
 */

public class MySqlTest{
    // JDBC driver name and database url
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/test";
    
    // Database credentials
    private static final String USER = "root";
    private static final String PASS = "Verifone1";

    public static void main(String[] args){
        Connection conn = null;
        Statement stmt = null;
        
        try{
            // STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);
            
            // STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            // STEP 4: Execute a query
            System.out.println("Inserting records into the table...");
            stmt = conn.createStatement();

            String sql = "INSERT INTO test " + "VALUES (1, 'Kyle', 'Degrano')";
            stmt.executeUpdate(sql);
            
            sql = "INSERT INTO test " + "VALUES (2, 'Francee', 'Castro')"; 
            stmt.executeUpdate(sql);
            System.out.println("Inserted records into the table...");

        }catch(SQLException se){
            // HANDLING ERRORS FOR JDBC
            se.printStackTrace();
        }catch(Exception e){
            // HANDLE ERRORS FOR Class.forName
            e.printStackTrace();
        }finally{
            // Block used to close resources
            try{
                if(stmt != null){
                    conn.close();
                }
            }catch(SQLException se){
                // pass
            }
            try{
                if(conn != null){
                    conn.close();
                }
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }
}