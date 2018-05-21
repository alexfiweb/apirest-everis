package com.car.Boundary;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.auth0.json.mgmt.users.User;
import com.car.Entity.Car;
import com.car.Utils.HibernateUtil;

@Stateless
public class CarService {
	
	public Response response;
	public SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	public Session session = sessionFactory.openSession();
	Logger logger = Logger.getLogger(CarService.class);
	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	Validator validator = factory.getValidator();
	
	
	public Response getAllCars() {
		
		try {
			logger.info("getting all cars");
			session.beginTransaction();
			Query query = session.createQuery("from Car");
			List<Car> resultList = (List<Car>) query.getResultList();
			List<Car> cars = resultList;
			session.getTransaction().commit();
			response = Response.status(200).entity(cars).build();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			response = Response.status(500).build();
		}
		
		return response;
	}
	
	public Response getCar(int id) {
		
		try {
			session.beginTransaction();
			Car car = session.get(Car.class, id);
			
			if (car==null) {
				logger.error("Can't get the car, there is no car with id "+id);
				session.getTransaction().commit();
				response = Response.status(404).build();
			}
			else {
				logger.info("Getting the car: "+car.toString());
				session.getTransaction().commit();
				response = Response.status(200).entity(car).build();
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			response = Response.status(500).build();
		}
		
		return response;
		
	}
	
	public Response createCar(Car car) {
		
		try {
			logger.info("Creating the car: "+car.toString());
			session.beginTransaction();
			session.save(car);
			session.getTransaction().commit();
			response = Response.status(200).entity(car).build();
			
		} catch (ConstraintViolationException e) {
			Set<ConstraintViolation<Car>> violations = validator.validate(car);
			for (ConstraintViolation<Car> violation : violations) {
			    //logger.error(violation.getMessage());
				logger.error(e.getMessage());
			    response = Response.status(400).entity(violation.getMessage()).build();
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			response = Response.status(500).build();
		}
		
		return response;
	}
	
	public Response updateCar(Car car, int id) {
		
		try {
			session.beginTransaction();
			Car carToUpdate = session.get(Car.class, id);
			if(carToUpdate==null) {
				logger.error("Can't update the car, there is no car with id "+id);
				session.getTransaction().commit();
				response = Response.status(404).build();
			}
			else {
				carToUpdate.setBrand(car.getBrand());
				carToUpdate.setCountry(car.getCountry());
				carToUpdate.setRegistration(car.getRegistration());
				carToUpdate.setLastUpdate(new Date());
				session.update(carToUpdate);
				session.getTransaction().commit();
				response = Response.status(200).entity(car).build();
				logger.info("Updating the car: "+carToUpdate.toString());
			}
			
		} catch (ConstraintViolationException e) {
			Set<ConstraintViolation<Car>> violations = validator.validate(car);
			for (ConstraintViolation<Car> violation : violations) {
			    //logger.error(violation.getMessage());
				logger.error(e.getMessage());
			    response = Response.status(400).entity(violation.getMessage()).build();
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			response = Response.status(500).build();
		}
		
		return response;
	}
	
	public Response deleteCar(int id) {
		
		try {
			session.beginTransaction();
			Car car = session.get(Car.class, id);
			if(car==null) {
				logger.error("Can't delete the car, there is no car with id "+id);
				session.getTransaction().commit();
				response = Response.status(404).build();
			}
			else {
				logger.info("Deleting the car: "+car.toString());
				session.delete(car);
				session.getTransaction().commit();
				response = Response.status(200).entity(car).build();
			}
			
		} catch (Exception e){
			logger.error(e.getMessage());
			response = Response.status(500).build();
		}
		
		return response;
	}
}
