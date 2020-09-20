package com.nexiilabs.stp.prospect;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class Sample {

	public static void main(String[] args) {
		NumberFormat numberFormat = NumberFormat.getInstance();
		LocalDate today = LocalDate.now();

	    // Go backward to get Monday
	    LocalDate monday = today;
	    while (monday.getDayOfWeek() != DayOfWeek.MONDAY) {
	      monday = monday.minusDays(1);
	    }

	    // Go forward to get Sunday
	    LocalDate sunday = today;
	    while (sunday.getDayOfWeek() != DayOfWeek.SUNDAY) {
	      sunday = sunday.plusDays(1);
	    }

	    System.out.println("Today: " + today);
	    System.out.println("Monday of the Week: " + monday.toString());
	    System.out.println("Sunday of the Week: " + sunday.toString());
	    System.out.println("MONTH::"+today.withDayOfMonth(1));
	    System.out.println(" LocalDate.now()::"+today.minusMonths(1).withDayOfMonth(1));
	    String number = "10000";
	    double amount = Double.parseDouble(number);
	    DecimalFormat formatter = new DecimalFormat("#,###.00");
	    System.out.println(formatter.format(amount));
	    System.out.println("amount"+numberFormat.format(amount));
	  }

	}

