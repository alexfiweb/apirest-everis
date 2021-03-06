package com.car.Control;


import java.util.Set;

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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.apache.log4j.Logger;

import com.car.Boundary.CarService;
import com.car.Entity.Car;
import com.car.Utils.AuthUtil;


@Path("/cars")
@Consumes("application/json")
@Produces("application/json")
//@Produces(value = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class CarResource {
	
	private Response response;
	Logger logger = Logger.getLogger(CarResource.class);
	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	Validator validator = factory.getValidator();
	
	
	@EJB
	public CarService carservice;
	
	@GET
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
	
	@GET
	@Path("/test")
	public Response getCarsByBrand(@HeaderParam("authorization") final String authorization, @QueryParam("brand") String brand, @QueryParam("model") String model) {
		if(AuthUtil.verifyToken(authorization)) {
			logger.info("Calling getCarsByBrand method");
			response = carservice.getCarsByBrand(brand, model);
		}
		else {
			logger.warn("Can't call getCarsByBrand method, invalid authentication");
			response = Response.status(401).build();
		}
		return response;
	}
	
	@POST
	public Response createCar(@HeaderParam("authorization") final String authorization, Car car) {
		if(AuthUtil.verifyToken(authorization)) {
			logger.info("Calling createCar method");
			try {
				response = Response.status(201).entity(carservice.createCar(car)).build();
			} catch (ConstraintViolationException e) {
				Set<ConstraintViolation<Car>> violations = validator.validate(car);
				for (ConstraintViolation<Car> violation : violations) {
					logger.error(e.getMessage());
				    response = Response.status(400).entity(violation.getMessage()).build();
				}
				logger.error(e.getMessage());
				
			} catch (Exception e) {
				logger.error(e.getMessage());
				response = Response.status(500).build();
			}
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
	
	@GET
	@Path("brands")
	public Response getAllBrands(@HeaderParam("authorization") final String authorization) {
		if(AuthUtil.verifyToken(authorization)) {
			logger.info("Calling getAllBrands method");
			response = carservice.getAllBrands();
		}
		else {
			logger.warn("Can't call getAllBrands method, invalid authentication");
			response = Response.status(401).build();
		}
		return response;
	}
	
	@GET
	@Path("models")
	public Response getAllModels(@HeaderParam("authorization") final String authorization, @QueryParam("brand") int brand) {
		logger.info(brand);
		if(AuthUtil.verifyToken(authorization)) {
			logger.info("Calling getAllModels method");
			response = carservice.getAllModels(brand);
		}
		else {
			logger.warn("Can't call getAllModels method, invalid authentication");
			response = Response.status(401).build();
		}
		return response;
	}
	

}
