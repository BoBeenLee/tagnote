package kr.tagnote.util;

public class CommonUtils {
	public static String getRandomColor() {
		int random = (int) (Math.random() * Math.pow(16, 6));
//		System.out.println("random : " + random);
		return String.format("%06X", random);
	}
}
