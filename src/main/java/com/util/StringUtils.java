package com.util;

import java.util.regex.Matcher;

/**
 * Created by sxs on 16/8/9.
 */
public class StringUtils extends org.apache.commons.lang.StringUtils {
	/**
	 * replace '{i}' in the message with parameters, 'i' is the index of the param
	 * eg. ("I am {0} years old", 21), then '21' will replace the '{i}' in the string
	 * so the result is "I am 21 years old"
	 * @param message
	 * @param params
	 */
	public static String format(String message, Object... params) {
		if (message == null) return null;
		if (params == null || params.length == 0) return message;
		
		for (int i = 0; i < params.length; i++) {
			// Matcher.quoteReplacement will fix the bug of replaceAll: No Group 5. quote "$" to "\$"
			message = message.replaceAll("\\{"+ i +"\\}", params[i] == null ? "null" : Matcher.quoteReplacement(String.valueOf(params[i].toString())));
		}
		
		return message;
	}

}
