package com.exp.AI;

import java.io.File;
import java.io.IOException;

public class TextWriter {
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
	
	
}
