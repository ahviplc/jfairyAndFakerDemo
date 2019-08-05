package com.lc.demoapp.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
 
/**
 *  提供跟日期相关的一些通用方法
 *  @date 2018年6月11日10:36:00
 *
 */
public class TimeUtil {
	//private static SimpleDateFormat yyyyDf = new SimpleDateFormat("yyyy");

	public static String date(String fmt) {
		return new SimpleDateFormat(fmt).format(new Date());
	}

	public static String date(String fmt, long t) {
		return new SimpleDateFormat(fmt).format(new Date(t));
	}

	public static String date8() {
		return new SimpleDateFormat("yyyyMMdd").format(new Date());
	}

	public static String date8(Date date) {
		return new SimpleDateFormat("yyyyMMdd").format(date);
	}

	public static String time6() {
		return new SimpleDateFormat("HHmmss").format(new Date());
	}

	public static String time6(Date date) {
		return new SimpleDateFormat("HHmmss").format(date);
	}

	public static String datetime14() {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	}
	
	public static String datetime12() {
		return new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
	}
	
	public static String datetime10() {
		return new SimpleDateFormat("yyyyMMddHH").format(new Date());
	}

	public static String datetime14(Date date) {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(date);
	}

	public static String datetime14(long t) {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(t));
	}

	public static String calcMonth(String month6, int m) {
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(month6.substring(0, 4)), Integer
				.parseInt(month6.substring(4, 6)) - 1, 1);
		cal.add(Calendar.MONTH, m);
		return new SimpleDateFormat("yyyyMM").format(cal.getTime());
	}

	public static String calcDay(String day8, int d) {
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(day8.substring(0, 4)), Integer.parseInt(day8
				.substring(4, 6)) - 1, Integer.parseInt(day8.substring(6, 8)));
		cal.add(Calendar.DATE, d);
		return new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
	}

	public static String calcSecond(String time14, int s) {
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(time14.substring(0, 4)), Integer
				.parseInt(time14.substring(4, 6)) - 1, Integer.parseInt(time14
				.substring(6, 8)), Integer.parseInt(time14.substring(8, 10)),
				Integer.parseInt(time14.substring(10, 12)), Integer
						.parseInt(time14.substring(12, 14)));
		cal.add(Calendar.SECOND, s);
		return new SimpleDateFormat("yyyyMMddHHmmss").format(cal.getTime());
	}

	public static long toMilliSec(String time14) {
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(time14.substring(0, 4)), Integer
				.parseInt(time14.substring(4, 6)) - 1, Integer.parseInt(time14
				.substring(6, 8)), Integer.parseInt(time14.substring(8, 10)),
				Integer.parseInt(time14.substring(10, 12)), Integer
						.parseInt(time14.substring(12, 14)));
		return cal.getTimeInMillis();
	}

	public static int getActualMaximum(String day8, int field) {
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(day8.substring(0, 4)), Integer.parseInt(day8
				.substring(4, 6)) - 1, Integer.parseInt(day8.substring(6, 8)));
		return cal.getActualMaximum(field);
	}

	public static int getActualMinimum(String day8, int field) {
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(day8.substring(0, 4)), Integer.parseInt(day8
				.substring(4, 6)) - 1, Integer.parseInt(day8.substring(6, 8)));
		return cal.getActualMinimum(field);
	}

	/**
	 * 
	 * 得到系统时间年份
	 * 
	 * @return
	 */
	public static String getDateYear() {
		return new SimpleDateFormat("yyyy").format(new Date());
	}

	/**
	 * 处理银行的checkDate没有年份的情冄1�7
	 * 
	 * @param v
	 * @return
	 */
	public static String formatBankCheckDate(String v) {
		if (v.length() == 4) {
			String yyyy = getDateYear();
			String stldate = yyyy + v;
			try {
				SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");

				long slttime = fmt.parse(stldate).getTime();

				long currtime = System.currentTimeMillis();

				long dis = slttime - currtime;

				long day1 = 24 * 3600 * 1000;

				if (Math.abs(dis) >= day1) {

					if (slttime < currtime) {

						yyyy = String.valueOf(Integer.parseInt(yyyy) + 1);

						stldate = yyyy + v;

					} else {

						yyyy = String.valueOf(Integer.parseInt(yyyy) - 1);

						stldate = yyyy + v;

					}

				}

				return stldate;
			} catch (Exception e) {

			}
		}
		return v;

	}
		/**
		 * String
		 * @param time
		 * @return
		 */
		public static String parseTimestampToDStr(Timestamp time) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
			formatter.format(time);
			return formatter.format(time);
		}
		
		/**
		 * String
		 * @param time
		 * @return
		 */
		public static String parseTimestampToTStr(Timestamp time) {
			SimpleDateFormat formatter = new SimpleDateFormat("HHmmss");
			formatter.format(time);
			return formatter.format(time);
		}
		public static String formatDateTime(Date date) {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
		}
		
		
		/**
		 * 获取前一小时所在天
		 * 返回格式 yyyyMMdd
		 */
		public static String getLastHourDay(){
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(System.currentTimeMillis());
			c.add(Calendar.HOUR_OF_DAY, -1);
			Date date = c.getTime();
			return new SimpleDateFormat("yyyyMMdd").format(date);
		}
		
		/**
		 * 获取前一小时所在天
		 * 返回格式 yyyyMMddHH
		 */
		public static String getLastHour(){
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(System.currentTimeMillis());
			c.add(Calendar.HOUR_OF_DAY, -1);
			Date date = c.getTime();
			return new SimpleDateFormat("yyyyMMddHH").format(date);
		}
		
		public static String getYestoday(){
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(System.currentTimeMillis());
			c.add(Calendar.DAY_OF_MONTH, -1);
			Date date = c.getTime();
			return new SimpleDateFormat("yyyyMMdd").format(date);
		}
		
		/**
		 * 返回两个月之前的数据
		 * yyyy-MM-dd HH:mm:ss
		 * @return
		 */
		public static String getLast2Month(){
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(System.currentTimeMillis());
			c.add(Calendar.MONTH, -2);
			
			Date date = c.getTime();
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
		}
		
		/** 获取当天的上一个周日 */
		public static String getPreSunday(){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Calendar cal = Calendar.getInstance();
			try {
				cal.setTime(new Date());
			} catch (Exception e) {
			}
			;
			if (cal.get(Calendar.DAY_OF_WEEK) != 1) {
				cal.add(Calendar.WEEK_OF_MONTH, +1);
			}
			cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
			cal.add(Calendar.WEEK_OF_MONTH, -1);
			return sdf.format(cal.getTime());
		}
		
		public static void main(String[] args) {
			System.out.println(getLast2Month());
		}
		
		/**
		 * 设置时间的时分钟秒
		 * @param date
		 * @param hours
		 * @param minutes
		 * @param seconds
		 * @return
		 */
		public static Date setTime(Date date,int hours,int minutes,int seconds){
			Date resDate = date;
			resDate.setHours(hours);
			resDate.setMinutes(minutes);
			resDate.setSeconds(seconds);
			return resDate;
		}
		/**
		 * 分钟转成天小时分钟
		 * @param min
		 * @return
		 */
		public static String minConvertDayHourMin(Double min){
			String html = "0分钟";
			if(min!=null){
				Double m = (Double) min;
				String format;
				Object[] array;
				Integer days = (int)(m/(60*24));
				Integer hours = (int)(m/(60)-days*24);
				Integer minutes = (int)(m - hours*60 - days*24*60);
				if(days>0){
					if(hours==0 && minutes==0){
						format = "%1$,d天";
						array=new Object[]{days};
					}else if(hours==0){
						format = "%1$,d天%2$,d分钟";
						array=new Object[]{days,minutes};
					}else if(minutes==0){
						format = "%1$,d天%2$,d小时";
						array=new Object[]{days,hours};
					}else{
						format = "%1$,d天%2$,d小时%3$,d分钟";
						array=new Object[]{days,hours,minutes};
					}
				}else if(hours>0){
					if(minutes==0){
						format="%1$,d小时";
						array=new Object[]{hours};
					}else{
						format="%1$,d小时%2$,d分钟";
						array=new Object[]{hours,minutes};
					}
				}else{
					format="%1$,d分钟";
					array=new Object[]{minutes};
				}
				html= String.format(format, array);
			}
			return html;
		}
}