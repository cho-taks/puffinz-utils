package com.puffinz.utils.util;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Months;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

@Component
public class DateUtil {
	
	public final static String FORMAT_YYYYMMDD_WITH_BAR = "yyyy-MM-dd";
	public final static String FORMAT_YYYYMMDD_NONE_BAR = "yyyyMMdd";
	
	public int getBetweenDays(String sdate, String edate) {
		
		DateTime dt1 = new DateTime(sdate);
		DateTime dt2 = new DateTime(edate);
		
		return Days.daysBetween(dt1.withTimeAtStartOfDay(), dt2.withTimeAtStartOfDay()).getDays();
	}

	public String getDate(String pattern) {
		DateTime dt = new DateTime();
		return dt.toString(pattern.toString());
	}
	
	public String getDate(String pattern, DateTime dt) {
		return dt.toString(pattern.toString()); 
	}
	
	public String getPlusYears(String pattern, int plus) {
		DateTime dt = new DateTime();
		dt = dt.plusYears(plus);
		return dt.toString(pattern.toString());
	}
	
	public String getPlusMonths(String pattern, int plus) {
		DateTime dt = new DateTime();
		dt = dt.plusMonths(plus);
		return dt.toString(pattern.toString());
	}
	
	public String getPlusDays(String pattern, int plus) {
		return getPlusDays(new DateTime(), pattern, plus); 
	}
	
	public String getPlusDays(DateTime dt, String pattern, int plus) {
		dt = dt.plusDays(plus);
		return dt.toString(pattern);
	}
	
	public String getPlusHours(String pattern, int plus) {
		DateTime dt = new DateTime();
		dt = dt.plusHours(plus);
		return dt.toString(pattern.toString());
	}

	public int getBetweenMonths(String sdate, String edate) {
		
		DateTime dt1 = new DateTime(sdate);
		DateTime dt2 = new DateTime(edate);
		
		return Months.monthsBetween(dt1.withTimeAtStartOfDay(), dt2.withTimeAtStartOfDay()).getMonths();
	}
	
	public long getMillis(String date) {
		DateTimeFormatter formatter = DateTimeFormat.forPattern(FORMAT_YYYYMMDD_WITH_BAR);
		DateTime dt = formatter.parseDateTime(date);
		return dt.getMillis();
	}
}