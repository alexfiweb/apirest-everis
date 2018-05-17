package com.car.Utils;

import java.io.UnsupportedEncodingException;

import com.auth0.client.auth.AuthAPI;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

public class AuthUtil {
	public static String clientKey = "QTnismf7NfxVUG3XLQOzmOqM0EbHIbsd4ij7qPkYxXmKxzqWOorNi-ktDfmikDRf";
	public static String domain = "alexfiweb.eu.auth0.com";
	public static String clientId = "zZBE6zUI2rV7iBhzrHyI5NaiaKptFIVf";
	public static AuthAPI auth = new AuthAPI(domain,clientId,clientKey);
	
	public boolean verifyToken(String headerToken) {

		String[] dividedHeader = headerToken.split(" ");
		String token = dividedHeader[1];
		boolean authorized = false;
		try {
			Algorithm algorithm = Algorithm.HMAC256(AuthUtil.clientKey);
			JWTVerifier verifier = JWT.require(algorithm).withIssuer("https://alexfiweb.eu.auth0.com/").build();
			//In case it doesn´t verify it throws an exception
			DecodedJWT jwt = verifier.verify(token);
			authorized = true;
		} catch (UnsupportedEncodingException exception){
		    //UTF-8 encoding not supported
		} catch (JWTVerificationException exception){
		    //Invalid signature/claims
		}
		return authorized;
	}
}