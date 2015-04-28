package kr.tagnote.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class CommonUtils {
	private static final int RANDOM_ID_LENGTH = 10;
	private static final String RANDOM_STR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	public static String getRandomColor() {
		int random = (int) (Math.random() * Math.pow(16, 6));
		// System.out.println("random : " + random);
		return String.format("%06X", random);
	}

	public static String getRandomId() {
		String rnd = "";

		for (int i = 0; i < RANDOM_ID_LENGTH; i++)
			rnd += "" + ((char) RANDOM_STR.charAt((int) (Math.random() * RANDOM_STR.length())));
		return rnd;
	}

	public static String urlEncode(String str) {
		String encodeStr = null;
		try {
			encodeStr = URLEncoder.encode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return encodeStr;
	}
}
