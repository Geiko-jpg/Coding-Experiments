package com.exp.NaiveBayes;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class NaiveCalculation implements MethodCalculation{
	// - - > DECLARATION
	private ArrayList<PersonCredentials>calcThisList = new ArrayList<PersonCredentials>();
	private InputTransfer input;
	private static DecimalFormat df = new DecimalFormat("#.###"); // formatting to 3 decimal places
	private double PC1, PC2, p_age_yes, p_age_no, p_income_yes, p_income_no, p_student_yes, p_student_no, p_credit_yes, p_credit_no;
	private double PX_buysyes, PX_buysno, pxOnYes, pxOnNo;
	
	// - - > EXECUTION
	public NaiveCalculation(ArrayList<PersonCredentials>calcThisList, InputTransfer input) {
		this.calcThisList = calcThisList;
		this.input = input;
	}
	
	public String calculatePrediction() {
		// - - > DECLARATION
		String status;
		double PC1CNTR = 0, PC2CNTR = 0;

		// - - > CALCULATE PC1
		for(PersonCredentials pc:this.calcThisList) {
			if(pc.isBuys_computer().equals("true")) {
				PC1CNTR++; // YES
			}
		}
		
		PC1 = PC1CNTR/this.calcThisList.size();
		df.setRoundingMode(RoundingMode.HALF_EVEN);
		System.out.println("\n\nPC1: " + df.format(PC1));
		
		// - - > CALCULATE PC2
		for(PersonCredentials pc:this.calcThisList) {
			if(pc.isBuys_computer().equals("false")) {
				PC2CNTR++; // NO
			}
		}
		PC2 = PC2CNTR/this.calcThisList.size();
		df.setRoundingMode(RoundingMode.HALF_EVEN);
		System.out.println("PC2: " + df.format(PC2));
		
		// - - > CALCULATE p_age_yes and p_age_no
		int age_buys_yes = 0, age_buys_no = 0; 
		for(PersonCredentials pc:this.calcThisList) {
			if(pc.getAge().equals(input.getAge()) && pc.isBuys_computer().equals("true")) {
				age_buys_yes++;
			}else if(pc.getAge().equals(input.getAge()) && pc.isBuys_computer().equals("true")){
				age_buys_no++;
			}
		}
		p_age_yes = age_buys_yes/PC1CNTR;
		p_age_no = age_buys_no/PC2CNTR;
		
		df.setRoundingMode(RoundingMode.HALF_EVEN);
		System.out.println("P_AGE_YES: " + df.format(p_age_yes) + " | P_AGE_NO: " + df.format(p_age_no));
		
		// - - > CALCULATE p_income_yes and p_income_no
		int income_yes = 0, income_no = 0;
		for(PersonCredentials pc:this.calcThisList) {
			if(pc.getIncome().equals(input.getIncome()) && pc.isBuys_computer().equals("true")) {
				income_yes++;
			}else if(pc.getIncome().equals(input.getIncome()) && pc.isBuys_computer().equals("false")) {
				income_no++;
			}
		}
		p_income_yes = income_yes/PC1CNTR;
		p_income_no = income_no/PC2CNTR;
		
		df.setRoundingMode(RoundingMode.HALF_EVEN);
		System.out.println("P_INCOME_YES: " + df.format(p_income_yes) + " | P_INCOME_NO: " + df.format(p_income_no));
		
		// - - > CALCULATE p_student_yes and p_student_no
		int student_yes = 0, student_no = 0;
		for(PersonCredentials pc:this.calcThisList) {
			if(pc.isStudent().equals(input.isStudent()) && pc.isBuys_computer().equals("true")) {
				student_yes++;
			}else if(pc.isStudent().equals(input.isStudent()) && pc.isBuys_computer().equals("false")) {
				student_no++;
			}
		}
		p_student_yes = student_yes/PC1CNTR;
		p_student_no = student_no/PC2CNTR;
		
		df.setRoundingMode(RoundingMode.HALF_EVEN);
		System.out.println("P_STUDENT_YES: " + df.format(p_student_yes) + " | P_STUDENT_NO: " + df.format(p_student_no));
		
		// - - > CALCULATE p_credit_yes and p_credit_no
		int credit_yes = 0, credit_no = 0;
		for(PersonCredentials pc:this.calcThisList) {
			if(pc.getCredit_rating().equals(input.getCredit_rating()) && pc.isBuys_computer().equals("true")) {
				credit_yes++;
			}else if(pc.getCredit_rating().equals(input.getCredit_rating()) && pc.isBuys_computer().equals("false")) {
				credit_no++;
			}
		}
		p_credit_yes = credit_yes/PC1CNTR;
		p_credit_no = credit_no/PC2CNTR;
		
		df.setRoundingMode(RoundingMode.HALF_EVEN);
		System.out.println("P_CREDIT_YES: " + df.format(p_credit_yes) + " | P_CREDIT_NO: " + df.format(p_credit_no));
		
		// - - > CALCULATE PX_buysyes and PX_buysno | CONCLUDE PREDICTION 
		PX_buysyes = p_age_yes * p_income_yes * p_student_yes * p_credit_yes;
		PX_buysno = p_age_no * p_income_no  * p_student_no * p_credit_no;
		
		pxOnYes = PX_buysyes * PC1;
		pxOnNo = PX_buysno * PC2;
		
		System.out.println("PXYES: " + pxOnYes + " | " + "PXNO: " + pxOnNo);
		
		if(pxOnYes > pxOnNo) {
			System.out.println("\n- - > USER WILL BUY\n");
			return status = "true";
		}else {
			System.out.println("- - > USER WILL NOT BUY");
			return status = "false";
		}		
	}
}
