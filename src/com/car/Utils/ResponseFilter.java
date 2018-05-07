package com.car.Utils;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;


@Provider
public class ResponseFilter implements ContainerResponseFilter, ContainerRequestFilter {

	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
		MultivaluedMap<String, Object> headers = responseContext.getHeaders();
		headers.add("Access-Control-Allow-Origin", "*");
		headers.add("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, Authorization");
		headers.add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
		headers.add("Access-Control-Max-Age", "2000");
		headers.add("X-Powered-By", "AlexfiWeb");
	}
	
	public void filter(ContainerRequestContext requestContext) throws IOException  {
		requestContext.getHeaders();
		System.out.println(requestContext.getHeaders());
	}

	
}