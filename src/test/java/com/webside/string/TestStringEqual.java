/**
 * 
 */
package com.webside.string;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

/**
 * @ClassName TestStringEqual
 * @Description TODO
 *
 * @author wjggwm
 * @data 2017年1月3日 下午5:47:16
 */
public class TestStringEqual {

	@Test
	public void testEquals()
	{
		String str1 = "/scheduleJob/pauseTrigger.html";
		String str2 = "/scheduleJob/pasueTrigger.html";
		System.out.print(str1.equals(str2));
	}
	public static void main(String[] args) {
		test();
	}
	// 获得指定日期的前一天 
	public static String getSpecifiedDayBefore(String specifiedDay){ 
		//SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		Calendar c = Calendar.getInstance(); 
		Date date=null; 
		try { 
		date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay); 
		} catch (ParseException e) { 
		e.printStackTrace(); 
		} 
		c.setTime(date); 
		int day=c.get(Calendar.DATE); 
		c.set(Calendar.DATE,day-1); 
	
		String dayBefore=new SimpleDateFormat("yyyy-MM-dd").format(c.getTime()); 
		return dayBefore; 
	} 
	
	public static void test() {
		 // 获取当月第一天和最后一天  
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
		try {
			date = format.parse("2019-09");
		} catch (ParseException e) {
			e.printStackTrace();
		}
        String firstday, lastday;  
        // 获取前月的第一天  
        Calendar cale = Calendar.getInstance();
        cale.setTime(date);
        cale.add(Calendar.MONTH, 0);  
        cale.set(Calendar.DAY_OF_MONTH, 1);  
        firstday = format2.format(cale.getTime());  
        // 获取前月的最后一天  
        cale.add(Calendar.MONTH, 1);  
        cale.set(Calendar.DAY_OF_MONTH, 0);  
        lastday = format2.format(cale.getTime());  
        System.out.println("本月第一天和最后一天分别是 ： " + firstday + " and " + lastday);
	}
	
}
