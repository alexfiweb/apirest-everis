package com.car.Boundary;

import java.text.SimpleDateFormat;
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
import com.car.Entity.Brand;
import com.car.Entity.Car;
import com.car.Entity.Model;
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

	public Response getCarsByBrand(String brand, String model) {
		
		try {
			logger.info("getting all cars with the brand: "+brand);
			if(brand.equals("all")) {
				session.beginTransaction();
				Query query = session.createQuery("select c from Car as c, Model as m WHERE c.model.id = m.id AND m.name LIKE :model")
						.setParameter("model", "%"+model+"%");
				List<Car> resultList = (List<Car>) query.getResultList();
				session.getTransaction().commit();
				response = Response.status(200).entity(resultList).build();
			}
			else {
				session.beginTransaction();
				Query query = session.createQuery("select c from Car as c JOIN Model as m ON c.model.id = m.id JOIN Brand as b ON b.id = m.brand.id WHERE b.name = :brand AND m.name LIKE :model")
						.setParameter("brand", brand)
						.setParameter("model", "%"+model+"%");
				List<Car> resultList = (List<Car>) query.getResultList();
				session.getTransaction().commit();
				response = Response.status(200).entity(resultList).build();
			}		

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

			if (car == null) {
				logger.error("Can't get the car, there is no car with id " + id);
				session.getTransaction().commit();
				response = Response.status(404).build();
			} else {
				logger.info("Getting the car: " + car.toString());
				session.getTransaction().commit();
				
				/*final String NEW_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
				SimpleDateFormat sdf = new SimpleDateFormat(NEW_FORMAT);	
				car.setRegistration(sdf.format(car.getRegistration()));*/
				
				response = Response.status(200).entity(car).build();
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			response = Response.status(500).build();
		}

		return response;

	}

	public Car createCar(Car car) throws ConstraintViolationException, Exception {

		logger.info("Creating the car: " + car.toString());
		session.beginTransaction();
		session.save(car);
		Car carSaved = session.get(Car.class, car.getId());
		session.getTransaction().commit();
		return car;
	}

	/*
	 * public Response createCar(Car car) {
	 * 
	 * try { logger.info("Creating the car: "+car.toString());
	 * session.beginTransaction(); session.save(car);
	 * session.getTransaction().commit(); response =
	 * Response.status(200).entity(car).build();
	 * 
	 * } catch (ConstraintViolationException e) { Set<ConstraintViolation<Car>>
	 * violations = validator.validate(car); for (ConstraintViolation<Car> violation
	 * : violations) { //logger.error(violation.getMessage());
	 * logger.error(e.getMessage()); response =
	 * Response.status(400).entity(violation.getMessage()).build(); }
	 * 
	 * } catch (Exception e) { logger.error(e.getMessage()); response =
	 * Response.status(500).build(); }
	 * 
	 * return response; }
	 */

	public Response updateCar(Car car, int id) {

		try {
			session.beginTransaction();
			Car carToUpdate = session.get(Car.class, id);
			if (carToUpdate == null) {
				logger.error("Can't update the car, there is no car with id " + id);
				session.getTransaction().commit();
				response = Response.status(404).build();
			} else {
				int modelId = car.getModel().getId();
				Model model = session.get(Model.class, modelId);
				//session.getTransaction().commit();
				carToUpdate.setModel(model);
				carToUpdate.setCountry(car.getCountry());
				carToUpdate.setRegistration(car.getRegistration());
				carToUpdate.setLastUpdate(new Date());
				session.update(carToUpdate);
				session.getTransaction().commit();
				response = Response.status(200).entity(carToUpdate).build();
				logger.info("Updating the car: " + carToUpdate.toString());
			}

		} catch (ConstraintViolationException e) {
			Set<ConstraintViolation<Car>> violations = validator.validate(car);
			for (ConstraintViolation<Car> violation : violations) {
				// logger.error(violation.getMessage());
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
			if (car == null) {
				logger.error("Can't delete the car, there is no car with id " + id);
				session.getTransaction().commit();
				response = Response.status(404).build();
			} else {
				logger.info("Deleting the car: " + car.toString());
				session.delete(car);
				session.getTransaction().commit();
				response = Response.status(200).entity(car).build();
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			response = Response.status(500).build();
		}

		return response;
	}

	public Response getAllBrands() {

		try {
			logger.info("getting all brands");
			session.beginTransaction();
			Query query = session.createQuery("from Brand");
			List<Brand> resultList = (List<Brand>) query.getResultList();
			List<Brand> brands = resultList;
			session.getTransaction().commit();
			response = Response.status(200).entity(brands).build();

		} catch (Exception e) {
			logger.error(e.getMessage());
			response = Response.status(500).build();
		}

		return response;
	}
	
	public Response getModelsByBrand(int brandid) {

		try {
			logger.info("getting all models by brand id: "+brandid);
			session.beginTransaction();
			Query query = session.createQuery("SELECT m FROM Model as m JOIN Brand as b ON m.brand.id = b.id AND m.brand.id = :brandid")
					.setParameter("brandid", brandid);
			List<Model> resultList = (List<Model>) query.getResultList();
			List<Model> models = resultList;
			session.getTransaction().commit();
			response = Response.status(200).entity(models).build();

		} catch (Exception e) {
			logger.error(e.getMessage());
			response = Response.status(500).build();
		}

		return response;
	}
	
	public Response getAllModels(int brandid) {

		try {
			logger.info("getting all models");
			session.beginTransaction();
			if(brandid == 0) {
				Query query = session.createQuery("from Model");
				List<Model> resultList = (List<Model>) query.getResultList();
				List<Model> models = resultList;
				session.getTransaction().commit();
				response = Response.status(200).entity(models).build();
			}
			else {
				Query query = session.createQuery("SELECT m FROM Model as m JOIN Brand as b ON m.brand.id = b.id AND m.brand.id = :brandid")
						.setParameter("brandid", brandid);
				List<Model> resultList = (List<Model>) query.getResultList();
				List<Model> models = resultList;
				session.getTransaction().commit();
				response = Response.status(200).entity(models).build();
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			response = Response.status(500).build();
		}

		return response;
	}
}
