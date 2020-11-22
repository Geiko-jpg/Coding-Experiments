package com.java.bobbyBox;

import java.util.Scanner;

public class StorageApplication {
	private static String[] bobbyStorage;
	private static int option, retoOpt, ctr = 0, cap;
	private static Scanner scan = new Scanner(System.in);
	
	private StorageApplication(int cap){
		bobbyStorage = new String[this.cap]; // - - > INITIALIZE
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub	
		System.out.println("WELCOME TO BOBBY'S BOX STORAGE EXERCISE...");
		System.out.print("PLEASE ENTER BOBBY'S STORAGE CAPACITY +> ");
		cap = scan.nextInt();
		new StorageApplication(cap);
		
		do {
			// COUNTER
			try {
				ctr = 0;
				for(String temp:bobbyStorage) {
					if(temp != null) {
						ctr++;
					}
				}			
			}catch(NullPointerException npe) {
				npe.printStackTrace();
			}
			System.out.println("\nMAX CAP: " + bobbyStorage.length + " | CURRENT BOX CAP: " + ctr + "\n");
			
			// - - > OPTIONS
			System.out.println("PLEASE SELECT AN OPTION:");
			System.out.println("[1] ADD AN ITEM TO THE BOX");
			System.out.println("[2] PRINT ALL ITEMS IN THE BOX");
			System.out.println("[3] GET AN ITEM IN THE BOX");
			System.out.println("[4] EXIT PROGRAM");
			System.out.print("OPTION +> ");
			option = scan.nextInt();
			
			// - - > SWITCH-CASING PORTION
			switch(option) {
				case 1:
					String item = addItems();
					try {
						if(ctr == bobbyStorage.length) { // REFRESH IF FULL
							bobbyStorage[0] = null; // DELETE
							for(int i = 0; i < bobbyStorage.length; i++) {
								if(bobbyStorage[i] == null && i != bobbyStorage.length-1) {
									bobbyStorage[i] = bobbyStorage[i+1];
									bobbyStorage[i+1] = null;
								}
								else if(i == bobbyStorage.length-1) {
									bobbyStorage[i] = item;
									break;
								}
							}
						}else {
							for(int i = 0; i < bobbyStorage.length; i++) {
								if(bobbyStorage[i] == null) {
									bobbyStorage[i] = item;
									break;
								}
							}
						}
					}catch(NullPointerException npe) {
						npe.printStackTrace();
					}
					System.out.print("\nRETURN? [Y=1 | N=2]: ");
					retoOpt = scan.nextInt();
					break;
				case 2:	
					try {
						printItems();
					}catch(NullPointerException npe) {
						npe.printStackTrace();
					}
					System.out.print("\nRETURN? [Y=1 | N=2]: ");
					retoOpt = scan.nextInt();
					break;
				case 3:
					try{
						getItem();
					}catch(NullPointerException npe) {
						npe.printStackTrace();
					}
					System.out.print("\nRETURN? [Y=1 | N=2]: ");
					retoOpt = scan.nextInt();
					break;
				case 4:
					System.exit(0);
					break;
				default:
					retoOpt = 1;
					break;
			}
		}while(retoOpt == 1);
	}

	private static String addItems() {
		// - - > DECLARATIONS
		String item;
		
		// - - > EXECUTIONS
		System.out.println("\nADD ITEM TO STORAGE...\n");
		System.out.print("INPUT ITEM +> ");
		item = scan.next();
		
		return item;
	}
	
	private static void printItems() {
		System.out.println("\nPRINTING ITEMS IN STORAGE...\n");
		for(String items:bobbyStorage) {
			if(items != null) {
				System.out.println(items);
			}
		}
	}
	
	private static void getItem() {
		System.out.println("\nGETTING ITEM FROM FIRST INDEX...\n");
		System.out.println("ITEM AT INDEX[0] +> " + bobbyStorage[0]);
		
		// DELETE AFTER + REFRESH
		bobbyStorage[0] = null;
		try {
			for(int i = 0; i < bobbyStorage.length; i++) {
				if(bobbyStorage[i] == null && i != bobbyStorage.length-1) {
					bobbyStorage[i] = bobbyStorage[i+1];
					bobbyStorage[i+1] = null;
				}
				else if(i == bobbyStorage.length-1) {
					break;
				}
			}
		}catch(NullPointerException npe) {
			npe.printStackTrace();
		}
		
	}
}
