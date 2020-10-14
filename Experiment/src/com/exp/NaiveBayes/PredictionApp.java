package com.exp.NaiveBayes;

import java.util.Scanner;

public class PredictionApp {
	private static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.print("INPUT FNAME: ");
		String fName = scan.nextLine();
		
		System.out.print("INPUT LNAME: ");
		String lName = scan.nextLine();
		
		PersonDatabase adapter = new PersonDatabase();
		adapter.WriteToDatabase(fName, lName);
	}

}
