package com.exp.NaiveBayes;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class NaiveCalculation {
	// - - > DECLARATION
	private ArrayList<PersonCredentials>calcThisList = new ArrayList<PersonCredentials>();
	private static DecimalFormat df = new DecimalFormat("#.###"); // formatting to 3 decimal places
	private double PC1, PC2, p_age_yes, p_age_no, p_income_yes, p_income_no, p_student_yes, p_student_no, p_credit_yes, p_credit_no;
	private double PX_buysyes, PX_buysno;
	
	// - - > EXECUTION
	public NaiveCalculation(ArrayList<PersonCredentials>calcThisList) {
		this.calcThisList = calcThisList;
	}
	
	public void calculatePrediction() {
		
	}
}
