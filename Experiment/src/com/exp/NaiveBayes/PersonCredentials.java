package com.exp.NaiveBayes;

public class PersonCredentials {
	// - - > DECLARATIONS
	private int age;
	private String income;
	private String student;
	private String credit_rating;
	private String buys_computer;
	
	private PersonCredentials(){}

	// - - > BUILD MEMBERS
	public static class PersonCredentialsBuilder {
		private int age;
		private String income;
		private String student;
		private String credit_rating;
		private String buys_computer;
		
		public PersonCredentialsBuilder setAge (int xAge) {
			this.age = xAge;
			return this;
		}
		
		public PersonCredentialsBuilder setIncome (String xIncome) {
			this.income = xIncome;
			return this;
			
		}
		
		public PersonCredentialsBuilder setStudent (String xStudent) {
			this.student = xStudent;
			return this;
		}
		
		public PersonCredentialsBuilder setCreditRating (String xCreditRating) {
			this.credit_rating = xCreditRating;
			return this;
		}
		
		public PersonCredentialsBuilder setBuysComputer (String xBuysComputer) {
			this.buys_computer = xBuysComputer;
			return this;
		}
		
		public PersonCredentials build () {
			PersonCredentials returnPersonCredentials = new PersonCredentials();
			returnPersonCredentials.age = this.age;
			returnPersonCredentials.income = this.income;
			returnPersonCredentials.student = this.student;
			returnPersonCredentials.credit_rating = this.credit_rating;
			returnPersonCredentials.buys_computer = this.buys_computer;
			
			return returnPersonCredentials;
		}
	}
	
	// - - > GETTERS
	public int getAge() {
		return age;
	}

	public String getIncome() {
		return income;
	}

	public String isStudent() {
		return student;
	}

	public String getCredit_rating() {
		return credit_rating;
	}

	public String isBuys_computer() {
		return buys_computer;
	}
}
