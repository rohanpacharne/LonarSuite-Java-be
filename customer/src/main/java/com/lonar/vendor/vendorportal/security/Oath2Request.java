package com.lonar.vendor.vendorportal.security;

import java.util.Arrays;
import java.util.LinkedHashMap;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
@PropertySource(value = "classpath:serverurl.properties", ignoreResourceNotFound = true)
public class Oath2Request {

	@Autowired
	private Environment env;
	
	@SuppressWarnings({ "unchecked" })
	public AuthTokenInfo sendTokenRequest(String username, String password) {
		RestTemplate restTemplate = new RestTemplate();
		//password = "03af04861d20928e334fe055478b22ce855e787a41f04970b5dcbfeae486db7e";
		HttpEntity<String> request = new HttpEntity<String>(getHeadersWithClientCredentials());
		ResponseEntity<Object> response = restTemplate.exchange(
				env.getProperty("SERVER_URL")+"/oauth/token?grant_type=password&clint_id=live_test&"+"username="+ username +"&password=" + password,
				HttpMethod.POST, request, Object.class);
		LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) response.getBody();
		
		AuthTokenInfo tokenInfo = null;
		if (map != null) {
			tokenInfo = new AuthTokenInfo();
			tokenInfo.setAccess_token((String) map.get("access_token"));
			tokenInfo.setToken_type((String) map.get("token_type"));
			tokenInfo.setRefresh_token((String) map.get("refresh_token"));
			tokenInfo.setExpires_in((int) map.get("expires_in"));
			tokenInfo.setScope((String) map.get("scope"));
		} else {
		}
		return tokenInfo;
	}

	@SuppressWarnings({ "unchecked" })
	public AuthTokenInfo sendRefreshTokenRequest(String refreshToken) {
		RestTemplate restTemplate = new RestTemplate();

		HttpEntity<String> request = new HttpEntity<String>(getHeadersWithClientCredentials());
		ResponseEntity<Object> response = restTemplate.exchange(
				env.getProperty("SERVER_URL")+"/oauth/token?grant_type=refresh_token&refresh_token=" + refreshToken, HttpMethod.POST, request,
				Object.class);
		LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) response.getBody();
		AuthTokenInfo tokenInfo = null;
		if (map != null) {
			tokenInfo = new AuthTokenInfo();
			tokenInfo.setAccess_token((String) map.get("access_token"));
			tokenInfo.setToken_type((String) map.get("token_type"));
			tokenInfo.setRefresh_token((String) map.get("refresh_token"));
			// tokenInfo.setExpires_in((int) map.get("expires_in"));
			tokenInfo.setScope((String) map.get("scope"));
		} else {

		}
		return tokenInfo;
	}
	
	private HttpHeaders getHeadersWithClientCredentials() {
		String plainClientCredentials = "live-test:bGl2ZS10ZXN0";
		String base64ClientCredentials = new String(Base64.encodeBase64(plainClientCredentials.getBytes()));
		HttpHeaders headers = getHeaders();
		headers.add("Authorization", "Basic " + base64ClientCredentials);
		return headers;
	}
	
	private static HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		return headers;
	}
	//http://localhost:8080/oauth/token?grant_type=password&clint_id=live_test&username=Ravi&password=password
	//http://localhost:8080/oauth/check_token?token=2d847f83-9b56-465f-b55b-6a0390420789
	//http://localhost:8080/oauth/token?grant_type=refresh_token&refresh_token=e94892a9-7f37-4977-8bf7-0b505a77c77f
}
