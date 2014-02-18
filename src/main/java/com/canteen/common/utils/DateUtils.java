package com.canteen.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {

	/**
	 * 获得当前时间 类型:"2007-06-25 16:09" 精确到分钟
	 * 
	 * @return
	 */
	public static final String getYMDHMTime() {
		Date aDate = new Date();
		SimpleDateFormat df = null;
		String returnValue = "";
		if (aDate != null) {
			df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			returnValue = df.format(aDate);
		}
		return (returnValue);
	}

	/**
	 * 获得当前时间 类型:"2007-06-25 16:09:09" 精确到秒
	 * 
	 * @return
	 */
	public static final String getYMDHMSTime() {
		Date aDate = new Date();
		SimpleDateFormat df = null;
		String returnValue = "";
		if (aDate != null) {
			df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			returnValue = df.format(aDate);
		}
		return (returnValue);
	}

	/**
	 * 获得当前时间 类型:"2007-06-25" 精确到天
	 * 
	 * @return
	 */
	public static final String getYMDTime() {
		Date aDate = new Date();
		SimpleDateFormat df = null;
		String returnValue = "";
		if (aDate != null) {
			df = new SimpleDateFormat("yyyy-MM-dd");
			returnValue = df.format(aDate);
		}
		return (returnValue);
	}

	/**
	 * 
	 * @return
	 */
	public static final String getHHmmssTime() {
		Date aDate = new Date();
		SimpleDateFormat df = null;
		String returnValue = "";
		if (aDate != null) {
			df = new SimpleDateFormat("HH:mm:ss");
			returnValue = df.format(aDate);
		}
		return (returnValue);
	}

	public static Date getDayByMinus(int days) {
		Calendar cd = Calendar.getInstance();
		cd.setTime(new Date());
		cd.add(Calendar.DATE, days);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String day = sdf.format(cd.getTime());
		try {
			return sdf.parse(day);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取指定日期的上一天
	 *
	 */
	public static String getYesterdayDate(String strDate) {
		DateFormat df = DateFormat.getDateInstance();
		Date date = null;
		try {
			date = df.parse(strDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar cd = Calendar.getInstance();
		cd.setTime(date);
		cd.add(Calendar.MILLISECOND, -24 * 60 * 60 * 1000);
		Date yesterday = cd.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String strYesterday = sdf.format(yesterday);
		return strYesterday;
	}

    public static String getDateMillisecond(String strDate) {
        DateFormat df = DateFormat.getDateInstance();
        Date date = null;
        try {
            date = df.parse(strDate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date.getTime()+"";
    }

	/**
	 * 获得time2 距离 time1的天数
	 * 
	 * @param dateFormat
	 * @param time1
	 *            目标日期
	 * @param time2
	 *            基本日期
	 * @return
	 */
	public static long getQuot(String dateFormat, String time1, String time2) {
		long quot = 0;
		SimpleDateFormat ft = new SimpleDateFormat(dateFormat);
		try {
			Date date1 = ft.parse(time1);
			Date date2 = ft.parse(time2);
			quot = date1.getTime() - date2.getTime();
			quot = quot / 1000 / 60 / 60 / 24;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return quot;
	}

	public static String getToday() {
		String time = "";
		time = getToday("yyyy-MM-dd");
		return time;
	}

	public static Date getTodayDate() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return df.parse(getToday());
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * @param format
	 *            根据指定的格式时间类型返回当前时间
	 * @return
	 */
	public static String getToday(String format) {
		String time = "";
		SimpleDateFormat df = null;
		Calendar cal = new GregorianCalendar();
		df = new SimpleDateFormat(format);
		time = df.format(cal.getTime());
		return time;
	}
}
