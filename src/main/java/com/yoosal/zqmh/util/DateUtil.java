/*
 * Created on 2006-3-9
 *
 */
package com.yoosal.zqmh.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author lbj
 * 
 */
public class DateUtil {
	public static String getToday() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		return sdf.format(cal.getTime());
	}

	public static String getYesterday() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -1);
		return sdf.format(cal.getTime());
	}
	//得到当前的时间 精确到分
	public static String getTodayM ()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Calendar cal = Calendar.getInstance();
		return sdf.format(cal.getTime()).substring(0, 16);
	}
	public static final String normalTimeFormat = "yyyy-MM-dd HH:mm:ss";

	public static final String normalDateFormat = "yyyy-MM-dd";

	public static String formatDate(Date date) {
		String ret = "";

		SimpleDateFormat sdf = new SimpleDateFormat(normalDateFormat);
		try {
			ret = sdf.format(date);
		} catch (Exception e) {
		}

		return ret;
	}
	static SimpleDateFormat sdf3 = new SimpleDateFormat(normalTimeFormat);
	public static String formatTime(Date date) {
		String ret;
		try {
			ret = sdf3.format(date);
		} catch (Exception e) {
			return "";
		}

		return ret;
	}

	public static String formatDate(Date date, String format) {
		String ret = "";

		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			ret = sdf.format(date);
		} catch (Exception e) {
		}

		return ret;
	}

	public static Date parseDate(String dateString, String formate) {
		Date ret = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(formate);
			ret = sdf.parse(dateString);
		} catch (Exception e) {
		}
		return ret;
	}

	public static Date parseDate(String s){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");        
		try {
			return sdf.parse(s);
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}
	
	public static long getTime(String dateString, String formate) {
		long time = 0;
		try {
			Date ret = null;
			SimpleDateFormat sdf = new SimpleDateFormat(formate);
			ret = sdf.parse(dateString);
			time = ret.getTime();
		} catch (Exception e) {
		}
		return time;
	}

	public static Date rollDate(int rollDateCount) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, rollDateCount);
		return cal.getTime();
	}

	public static Date rollDate(Date date, int rollDateCount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, rollDateCount);
		return cal.getTime();
	}

	/**
	 * 返回当前时间的字符串<br/>
	 * 格式：yyyy-MM-dd HH:mm:ss<br/>
	 * @return
	 */
	public static String getNow() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		return sdf.format(cal.getTime());
	}
	
	public static String getNowStr() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		return sdf.format(cal.getTime());
	}
	
	public static String formatTime(String dateTime){
		return dateTime.replace(".0","");
	}
	
	public static String formatTime(String dateTime , String formate){
		Date date = parseDate(dateTime.replace(".0",""),"yyyy-MM-dd HH:mm:ss");
		return formatDate(date, formate);
	}
	
	 
	/**
	 * 
	 * 功能: 判断一个时间的 时辰是否在 某个范围之内
	 * <p>作者 李双 Apr 6, 2012 2:10:32 PM
	 * @param str  日期字符串
	 * @param start 开始范围
	 * @param end 结束范围
	 * @return
	 */
	public static boolean isRangeTime(Calendar ca,int start,int end){
		if(start<=ca.get(Calendar.HOUR_OF_DAY) && ca.get(Calendar.HOUR_OF_DAY)<end){
			return true;
		}
		return false;
	}

	/**
	 * 获得未来几天的时间
	 * @param i 未来的天数
	 * @return
	 */
	public static String getFutureTime(int i) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, i);
		return sdf.format(cal.getTime());
	}
	
	/**
	 * 获得未来几天的时间
	 * @param i 未来的天数
	 * @return
	 */
	public static String getFutureDate(int i) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, i);
		return sdf.format(cal.getTime());
	}
	
	/**
	 * 判断时间是否过期
	 * @param endTime 截止时间
	 * @return
	 */
	public static boolean isPast(String endTime) {
		DateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = sdf.parse(endTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Date().before(date);
	}
	
	/**
	 * 获得QQ用户首次登录赠送代金券的截止时间
	 * @return
	 */
	public static Calendar getQQPastDate() {
		//把截止时间设置为 2012-07-01 00:00:00
    	Calendar cal = Calendar.getInstance();
		cal.set(2012, 1, 1, 0, 0, 0);
		return cal;
	}
	public static String getFutureTime(int i,String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, 3);
		return sdf.format(cal.getTime());
	}
	
	/**
	 * 比较两个时间的大小，Time1>Time2 返回1；Time1<Time2 返回-1；Time1=TIme2 返回0
	 * @param Time1
	 * @param Time2
	 * @return
	 */
	public static int compareTime(String Time1, String Time2) {
		try {
			Date dt1 = parseDate(Time1, normalTimeFormat);
			Date dt2 = parseDate(Time2, normalTimeFormat);
			if (dt1.getTime() > dt2.getTime()) {
				return 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				return -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * 说明：获取某日期前n天的日期
	 */
	public static String getBackFromDate(String date, int days){

		String newDate = "";

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(DateUtil.parseDate(date));
		calendar.add(Calendar.DAY_OF_YEAR, -days);
		newDate = DateUtil.formatDate(calendar.getTime());

		return newDate;
	}
		/**
	 * 返回两个时间的差
	 * @param time1
	 * @param time2
	 * @return
	 */
	public static long timeDiff(String time1, String time2) {
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return sdf.parse(time1).getTime()-sdf.parse(time2).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static String timeDiffStr(String startDate, String endDate) {
		String format = "yyyy-MM-dd HH:mm:ss";
		long second = (DateUtil.parseDate(endDate, format).getTime()-DateUtil.parseDate(startDate, format).getTime())/1000;
		int minute = (int)second/60;
		int hour = minute/60;
		int day = hour/24;
		String result = (day>0?day+"天":"")+(hour>0?(hour-day*24)+"小时":"")+
				(minute>0?(minute-hour*60)+"分":"")+(second>0?(second-minute*60+"秒"):"");
		return result;
	}
	/**
	 * 取今天的当前小时数
	 * @return  0-23
	 */
	public static int getHour() {
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		return hour;
	}
	/**
	 * 获得未来几天的时间
	 * @param i 未来的秒数
	 * @return
	 */
	public static String getFutureSecond(String time, int i) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date ret = null;
		try {
			ret = sdf.parse(time);
		} catch (ParseException e) {
			return getNow();
		}
		Calendar cal=Calendar.getInstance();
		cal.setTime(ret);
		cal.add(Calendar.SECOND, i);
		return sdf.format(cal.getTime());
	}
}
