package com.nexiilabs.stp.prospect;

public class FollowupReportsCountModel {

	private int todayUpdateCount;
	private int todayNotUpdateCount;
	private int weeklyUpdateCount;
	private int weeklyNotUpdateCount;
	private int monthlyUpdateCount;
	private int monthlyNotUpdateCount;
	private int DateRangeUpdateCount;
	private int DateRangeNotUpdateCount;
	private String startDate;
	private String endDate;
	public int getTodayUpdateCount() {
		return todayUpdateCount;
	}
	public void setTodayUpdateCount(int todayUpdateCount) {
		this.todayUpdateCount = todayUpdateCount;
	}
	public int getTodayNotUpdateCount() {
		return todayNotUpdateCount;
	}
	public void setTodayNotUpdateCount(int todayNotUpdateCount) {
		this.todayNotUpdateCount = todayNotUpdateCount;
	}
	public int getWeeklyUpdateCount() {
		return weeklyUpdateCount;
	}
	public void setWeeklyUpdateCount(int weeklyUpdateCount) {
		this.weeklyUpdateCount = weeklyUpdateCount;
	}
	public int getWeeklyNotUpdateCount() {
		return weeklyNotUpdateCount;
	}
	public void setWeeklyNotUpdateCount(int weeklyNotUpdateCount) {
		this.weeklyNotUpdateCount = weeklyNotUpdateCount;
	}
	public int getMonthlyUpdateCount() {
		return monthlyUpdateCount;
	}
	public void setMonthlyUpdateCount(int monthlyUpdateCount) {
		this.monthlyUpdateCount = monthlyUpdateCount;
	}
	public int getMonthlyNotUpdateCount() {
		return monthlyNotUpdateCount;
	}
	public void setMonthlyNotUpdateCount(int monthlyNotUpdateCount) {
		this.monthlyNotUpdateCount = monthlyNotUpdateCount;
	}
	public int getDateRangeUpdateCount() {
		return DateRangeUpdateCount;
	}
	public void setDateRangeUpdateCount(int dateRangeUpdateCount) {
		DateRangeUpdateCount = dateRangeUpdateCount;
	}
	public int getDateRangeNotUpdateCount() {
		return DateRangeNotUpdateCount;
	}
	public void setDateRangeNotUpdateCount(int dateRangeNotUpdateCount) {
		DateRangeNotUpdateCount = dateRangeNotUpdateCount;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FollowupReportsCountModel [todayUpdateCount=");
		builder.append(todayUpdateCount);
		builder.append(", todayNotUpdateCount=");
		builder.append(todayNotUpdateCount);
		builder.append(", weeklyUpdateCount=");
		builder.append(weeklyUpdateCount);
		builder.append(", weeklyNotUpdateCount=");
		builder.append(weeklyNotUpdateCount);
		builder.append(", monthlyUpdateCount=");
		builder.append(monthlyUpdateCount);
		builder.append(", monthlyNotUpdateCount=");
		builder.append(monthlyNotUpdateCount);
		builder.append(", DateRangeUpdateCount=");
		builder.append(DateRangeUpdateCount);
		builder.append(", DateRangeNotUpdateCount=");
		builder.append(DateRangeNotUpdateCount);
		builder.append(", startDate=");
		builder.append(startDate);
		builder.append(", endDate=");
		builder.append(endDate);
		builder.append("]");
		return builder.toString();
	}
}