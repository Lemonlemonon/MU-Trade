package com.fyp.mumarket.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

/**
 * Project common utility class
 * @author Administrator
 *
 */
public class StringUtil {
	
	
	/**
	 * Return a date string in the specified format
	 * @param date
	 * @param formatter
	 * @return
	 */
	public static String getFormatterDate(Date date,String formatter){
		SimpleDateFormat sdf = new SimpleDateFormat(formatter);
		return sdf.format(date);
	}
	
	/**
	 * Determine if the request is an AJAX request
	 * @param request
	 * @return
	 */
	public static boolean isAjax(HttpServletRequest request){
		String header = request.getHeader("X-Requested-With");
		if("XMLHttpRequest".equals(header))return true;
		return false;
	}
	
	/**
	 * Read a string from a stream
	 * @param inputStream
	 * @return
	 */
	public static String getStringFromInputStream(InputStream inputStream){
		String string = "";
		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"GB2312"));
			String buf = null;
			try {
				while((buf = bufferedReader.readLine()) != null){
					string += buf;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					bufferedReader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return string;
	}
}
