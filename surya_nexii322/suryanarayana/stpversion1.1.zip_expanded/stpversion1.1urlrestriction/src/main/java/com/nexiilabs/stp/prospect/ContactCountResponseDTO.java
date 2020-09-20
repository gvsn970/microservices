package com.nexiilabs.stp.prospect;

public class ContactCountResponseDTO {
private int todayCount;
private int weeklyCount;
private int monthlyCount;
private int dateFilterCount;
private String startDate;
private String endDate;
public int getTodayCount() {
	return todayCount;
}
public void setTodayCount(int todayCount) {
	this.todayCount = todayCount;
}
public int getWeeklyCount() {
	return weeklyCount;
}
public void setWeeklyCount(int weeklyCount) {
	this.weeklyCount = weeklyCount;
}
public int getMonthlyCount() {
	return monthlyCount;
}
public void setMonthlyCount(int monthlyCount) {
	this.monthlyCount = monthlyCount;
}
public int getDateFilterCount() {
	return dateFilterCount;
}
public void setDateFilterCount(int dateFilterCount) {
	this.dateFilterCount = dateFilterCount;
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
	builder.append("ContactCountResponseDTO [todayCount=");
	builder.append(todayCount);
	builder.append(", weeklyCount=");
	builder.append(weeklyCount);
	builder.append(", monthlyCount=");
	builder.append(monthlyCount);
	builder.append(", dateFilterCount=");
	builder.append(dateFilterCount);
	builder.append(", startDate=");
	builder.append(startDate);
	builder.append(", endDate=");
	builder.append(endDate);
	builder.append("]");
	return builder.toString();
}

}
