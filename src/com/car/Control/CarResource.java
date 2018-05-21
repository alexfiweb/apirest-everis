package com.car.Control;


import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.car.Boundary.CarService;
import com.car.Entity.Car;
import com.car.Utils.AuthUtil;


@Path("/cars")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
//@Produces(value = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class CarResource {
	
	private Response response;
	Logger logger = Logger.getLogger(CarResource.class);
	
	@EJB
	public CarService carservice;
	
	@GET
	@Path("/")
	public Response getAllCars(@HeaderParam("authorization") final String authorization) {
		if(AuthUtil.verifyToken(authorization)) {
			logger.info("Calling getAllCars method");
			response = carservice.getAllCars();
		}
		else {
			logger.warn("Can't call getAllCars method, invalid authentication");
			response = Response.status(401).build();
		}
		return response;
	}
	
	@GET
	@Path("/{id}")
	public Response getCar(@HeaderParam("authorization") final String authorization, @PathParam("id") int id) {
		if(AuthUtil.verifyToken(authorization)) {
			logger.info("Calling getCar method");
			response = carservice.getCar(id);
		}
		else {
			logger.warn("Can't call getCar method, invalid authentication");
			response = Response.status(401).build();
		}
		return response;
	}
	
	@POST
	@Path("/")
	public Response createCar(@HeaderParam("authorization") final String authorization, Car car) {
		if(AuthUtil.verifyToken(authorization)) {
			logger.info("Calling createCar method");
			response = carservice.createCar(car);
		}
		else {
			logger.warn("Can't call createCar method, invalid authentication");
			response = Response.status(401).build();
		}
		return response;
	}
	
	@PUT
	@Path("/{id}")
	public Response updateCar(@HeaderParam("authorization") final String authorization, Car car, @PathParam("id") int id) {
		if(AuthUtil.verifyToken(authorization)) {
			logger.info("Calling updateCar method");
			response = carservice.updateCar(car, id);
		}
		else {
			logger.warn("Can't call updateCar method, invalid authentication");
			response = Response.status(401).build();
		}
		return response;
	}

	@DELETE
	@Path("/{id}")
	public Response deleteCar(@HeaderParam("authorization") final String authorization, @PathParam("id") int id) {
		if(AuthUtil.verifyToken(authorization)) {
			logger.info("Calling deleteCar method");
			response = carservice.deleteCar(id);
		}
		else {
			logger.warn("Can't call deleteCar method, invalid authentication");
			response = Response.status(401).build();
		}
		return response;
	}
}
