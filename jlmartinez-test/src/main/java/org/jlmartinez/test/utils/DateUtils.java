package org.jlmartinez.test.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
	
	public static String localDateTimeToString(LocalDateTime dateTime, DateTimeFormatter formatter) {
		return dateTime.format(formatter);
	}
}
