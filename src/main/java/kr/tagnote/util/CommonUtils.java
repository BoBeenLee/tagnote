package kr.tagnote.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CommonUtils {
	private static final int RANDOM_ID_LENGTH = 10;
	private static final String RANDOM_STR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	public static String getRandomColor() {
		int random = (int) (Math.random() * Math.pow(16, 6));
		// System.out.println("random : " + random);
		int noWhite = 48;

		if (noWhite < random)
			random -= noWhite; // ffffff 방지
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

	public static List convertStrToList(String str) {
		int[] strBracket = new int[str.length()];

		procBracket(str, strBracket);
		List list = (List) toList(str, strBracket, 1, str.length() - 2);
		return list;
	}

	// 대각선 구분 위치
	private static void procBracket(String str, int[] strBracket) {
		Stack<Integer> stack = new Stack<Integer>();

		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '[') {
				stack.push(i);
			} else if (str.charAt(i) == ']') {
				int idx = stack.pop();
				strBracket[idx] = i;
			}
		}
	}

	// depth 0, 1, 2, 3, 4 ....
	// array [] [] [] []
	private static Object toList(String str, int[] strBracket, int sIdx, int lIdx) {
		List res = new ArrayList();
		boolean isFirst = true;
		int cIdx = sIdx;

		for (;;) {
			int listIdx = str.indexOf('[', cIdx);
			int commaIdx = str.indexOf(',', cIdx + 1);

			// depth가 증가하는지 여부를 파악
			if (listIdx != -1 && commaIdx != -1 && listIdx < commaIdx)
				cIdx = listIdx;
			else if (listIdx != -1 && commaIdx == -1)
				cIdx = listIdx;

			// 대각선이 있을 경우 depth를 증가시켜 해당 구역에 대한 toList를 구한다.
			if (str.charAt(cIdx) == '[') {
				int nextIdx = strBracket[cIdx];
				// System.out.println((cIdx + 1) + " - " + (nextIdx - 1));
				res.add(toList(str, strBracket, cIdx + 1, nextIdx - 1));
				cIdx = nextIdx;
				if (isFirst)
					isFirst = false;

			} else if (isFirst || str.charAt(cIdx) == ',') {
				// 처음 일 경우 ,가 맨 앞에 존재하지 않아 예외처리로 따로 -1을 더한다.
				if (isFirst)
					cIdx -= 1;
				int nextIdx = str.indexOf(',', cIdx + 1);
				// 끝에 도달했을 경우
				if (nextIdx > lIdx || nextIdx == -1)
					nextIdx = lIdx + 1;
				// System.out.println(str.substring(cIdx + 1, nextIdx).trim());
				res.add(str.substring(cIdx + 1, nextIdx).trim());

				if (isFirst)
					isFirst = false;
				cIdx = nextIdx;
			}
			// 다음 ,위치로 이동시킨다.
			int nextIdx = str.indexOf(',', cIdx);

			// 끝에 도달했을 경우, 끝냄.
			if (nextIdx > lIdx || nextIdx == -1)
				break;
			cIdx = nextIdx;
		}
		// System.out.println("finish");
		return res;
	}

	public static byte[] getURLToBytes(String urlStr, long size) {
		URL url = null;
		try {
			url = new URL(urlStr);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		InputStream is = null;
		byte[] byteChunk = new byte[(int) size]; // Or whatever size you want to
		// read in at a time.

		try {
			is = url.openStream();
			int n;

			while ((n = is.read(byteChunk)) > 0)
				baos.write(byteChunk, 0, n);
		} catch (IOException e) {
			System.err.printf("Failed while reading bytes from %s: %s", url.toExternalForm(), e.getMessage());
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return byteChunk;
	}
}
