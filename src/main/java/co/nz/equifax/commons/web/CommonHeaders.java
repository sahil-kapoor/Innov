package co.nz.equifax.commons.web;

import org.springframework.http.HttpHeaders;

public class CommonHeaders {
	
	
	public static HttpHeaders getCommonHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Access-Control-Allow-Origin", "*");
		headers.add("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, OPTIONS");
		headers.add("Access-Control-Allow-Headers", "origin, x-requested-with, content-type");
		headers.add("Access-Control-Max-Age", "120000");
		return headers;
	}
	
}
