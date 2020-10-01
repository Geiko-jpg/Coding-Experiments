package com.kyle.timeCalc;

import java.util.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * @author kyle
 * @purpose Kyle and Francee relationship duration calculator
 * @date 06-09-2020
 */

public class Calculation {
	private static Date date = new Date();
	private static LocalDate ld = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	private static int monthDays[] = new int[] {31, 28, 31, 30, 31, 30,
												31, 31, 30, 31, 30, 31};
	private static int y, m, d, days;
	
	private static double lmonths, lyrs;
	private static DecimalFormat df = new DecimalFormat("#.##");
	
	
	// - - > CLASS METHODS
	
	Calculation() {
		Calculation.y = ld.getYear();
		Calculation.m = ld.getMonthValue();
		Calculation.d = ld.getDayOfMonth();
	}
	public int countLeapYears(int y, int m, int d) {
		int years = y;
		// - - > EXECUTIONS
		if(m <= 2) {
			years--;
		}
		
		return years / 4 - years / 100 + years / 400;
	}
	public int getDiff(int y, int m, int d, int y2, int m2, int d2) {
		long n1 = (y*365) + d;
		// - - > EXECUTIONS
		for(int i = 0; i < m - 1; i++) {
			n1 += monthDays[i];
		}
		n1 += countLeapYears(y, m, d);
		long n2 = (y2*365) + d2; 
		for(int i = 0; i < m2 - 1; i++) {
			n2 += monthDays[i];
		} 
		n2 += countLeapYears(y2, m2, d2);
		
		System.out.println(n1 + " " + n2);
		
		return (int)(n1-n2);
	}
	public void displayCalculation() {		
		LocalDateTime myDateObj = LocalDateTime.now();
		DateTimeFormatter newFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		String formattedDate = myDateObj.format(newFormat);
		
		int dt2d, dt2m, dt2y;
		dt2d = 21; dt2m = 2; dt2y = 2020; // SET FOUNDATION DATE HERE 
		// - - > EXECUTIONS
		days = getDiff(y, m, d, dt2y, dt2m, dt2d);
		lmonths = days / 30.0D; 
		lyrs = lmonths / 12.0D;
		//  - - >
		System.out.println("CALCULATING RELATIONSHIP DURATION...\n");
		
		System.out.println("DATE TODAY IS +> " + formattedDate + "\n");
		
		System.out.println("DAYS TOGETHER: " + days);
		
		df.setRoundingMode(RoundingMode.HALF_EVEN);
		System.out.println("MONTHS TOGETHER: " + df.format(lmonths));
		
		df.setRoundingMode(RoundingMode.HALF_EVEN);
		System.out.println("YEARS TOGETHER: " + df.format(lyrs) + "\n");
		
		System.out.print("!SUMMARY: YOU'VE BEEN WITH FRANCEE FOR " + (int)lyrs + " YEAR/S ");
		System.out.print((int)lmonths-((int)lyrs*12) + " MONTH/S AND ");
		System.out.println(days-((int)lmonths*30) + " DAY/S...\n");
		
		if(days % 30 == 0) {
			System.out.print("!NOTE: HAPPY MONTHSARY!!!");
		}
		else if(lmonths % 12 == 0) {
			System.out.print("!NOTE: HAPPY ANNIVERSARY!!!");
		}
	}
}
