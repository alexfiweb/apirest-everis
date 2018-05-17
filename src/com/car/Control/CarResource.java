package com.car.Control;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.car.Boundary.CarService;
import com.car.Entity.Car;
import com.car.Utils.HibernateUtil;


@Path("/cars")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
//@Produces(value = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class CarResource {
	public Response response;
	public CarService carservice = new CarService();
	
	@GET
	@Path("/")
	public Response getAllCars() {
		response = carservice.getAllCars();
		return response;
	}
	
	@GET
	@Path("/{id}")
	public Response getCar(@PathParam("id") int id) {
		response = carservice.getCar(id);
		return response;
	}
	
	@POST
	@Path("/")
	public Response createCar(Car car) {
		response = carservice.createCar(car);
		return response;
	}
	
	@PUT
	@Path("/")
	public Response updateCar(Car car) {
		response = carservice.updateCar(car);
		return response;
	}
	//comment
	@DELETE
	@Path("/{id}")
	public Response deleteCar(@PathParam("id") int id) {
		response = carservice.deleteCar(id);
		return response;
	}
}
