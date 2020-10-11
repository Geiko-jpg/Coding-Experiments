package com.exp.NaiveBayes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TextWriter {
	private ArrayList<PersonCredentials> listCredentials;
	
	public TextWriter() {}
	
	public void createFile() {
		try {
			File textDataBase = new File("PersonDataBase.txt");
			if(textDataBase.createNewFile()) {
				System.out.println("File Created: " + textDataBase.getName());
			} else {
				System.out.println("File already exists!");
			}
		} catch(IOException e) {
			System.out.println("Error!");
			e.printStackTrace();
		}
	}
	
	public void writeToFile(PersonCredentials xData) {
		try {
			PersonCredentials writeTheCredential = xData;
			FileWriter write = new FileWriter("PersonDataBase.txt", true);
			BufferedWriter bw = new BufferedWriter(write);
			
			bw.write(writeTheCredential.getAge() + "|" + writeTheCredential.getIncome() + "|" + writeTheCredential.isStudent() + "|" +
			writeTheCredential.getCredit_rating() + "|" + writeTheCredential.isBuys_computer() + "\n");
			bw.close();
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void readTheFile() throws IOException {
		ArrayList<PersonCredentials> createList = new ArrayList<PersonCredentials>();
		
		FileInputStream stream = null;
		try {
			stream = new FileInputStream("PersonDataBase.txt");
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		String strline;
		try {
			while((strline = reader.readLine()) != null) {
				
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
}
