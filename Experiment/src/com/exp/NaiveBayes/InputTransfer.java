package com.exp.NaiveBayes;

public class InputTransfer extends PersonCredentials{
	// - - > Declarations
	private String age;
	private String income;
	private String student;
	private String credit_rating;

	private InputTransfer() {
		super();
	}
	
	public static class InputTransferBuilder{
		private String age;
		private String income;
		private String student;
		private String credit_rating;
		
		public InputTransferBuilder setAge (String xAge) {
			this.age = xAge;
			return this;
		}
		
		public InputTransferBuilder setIncome (String xIncome) {
			this.income = xIncome;
			return this;
			
		}
		
		public InputTransferBuilder setStudent (String xStudent) {
			this.student = xStudent;
			return this;
		}
		
		public InputTransferBuilder setCreditRating (String xCreditRating) {
			this.credit_rating = xCreditRating;
			return this;
		}
		
		public InputTransfer build() {
			InputTransfer retInputTransfer = new InputTransfer();
			retInputTransfer.age = this.age;
			retInputTransfer.income = this.income;
			retInputTransfer.student = this.student;
			retInputTransfer.credit_rating = this.credit_rating;
			
			return retInputTransfer;
		}
	}
	
	// - - > GETTERS
	public String getAge() {
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
}
