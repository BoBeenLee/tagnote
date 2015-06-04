package kr.tagnote.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class HttpUtils {
	public static String getJson(RestTemplate restTemplate, String url, HashMap<String, String> params) {
		String json = null;
		UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(url);
		Set<String> keys = null;

		if (params != null)
			keys = params.keySet();
		if (keys != null)
			for (String key : keys)
				uriComponentsBuilder.queryParam(key, params.get(key));
		json = restTemplate.getForObject(uriComponentsBuilder.build().toUri(), String.class);
		// System.out.println("getJson : " + json);
		return json;
	}

	public static String getServerURL(HttpServletRequest request) {
		String scheme = request.getScheme(); // http
		String serverName = request.getServerName(); // hostname.com
		int serverPort = request.getServerPort(); // 80
		String contextPath = request.getContextPath(); // /mywebapp
		String servletPath = request.getServletPath(); // /servlet/MyServlet
		String pathInfo = request.getPathInfo(); // /a/b;c=123
		String queryString = request.getQueryString(); // d=789

		// Reconstruct original requesting URL
		StringBuffer url = new StringBuffer();
		url.append(scheme).append("://").append(serverName);

		if ((serverPort != 80) && (serverPort != 443)) {
			url.append(":").append(serverPort);
		}

		url.append(contextPath); // .append(servletPath);
		/*
		 * if (pathInfo != null) { url.append(pathInfo); } if (queryString !=
		 * null) { url.append("?").append(queryString); }
		 */
		return url.toString();
	}
}
