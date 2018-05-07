package com.car.Boundary;

import java.util.List;

import javax.persistence.Query;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.car.Entity.Car;
import com.car.Utils.HibernateUtil;

public class CarService {
	
	public Response response;
	public SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	public Session session = sessionFactory.openSession();
	Transaction transaction = session.beginTransaction();
	
	public Response getAllCars() {
		Query query = session.createQuery("from Car");
		List<Car> cars = (List<Car>) query.getResultList();
		session.getTransaction().commit();
		return Response.status(200).entity(cars).build();
	}
	
	public Response getCar(int id) {
		Car car = session.get(Car.class, id);
		session.getTransaction().commit();
		return Response.status(200).entity(car).build();
	}
	
	public Response createCar(Car car) {
		session.save(car);
		session.getTransaction().commit();
		return Response.status(200).entity(car).build();
	}
	
	public Response updateCar(Car car) {
		session.update(car);
		session.getTransaction().commit();
		return Response.status(200).entity(car).build();
	}
	
	public Response deleteCar(int id) {
		Car car = session.get(Car.class, id);
		try {
			
			session.delete(car);
			response = Response.status(200).entity(car).build();
			session.getTransaction().commit();
			
		} catch (Exception e){
			response = Response.status(500).build();
			session.getTransaction().commit();
		}
		return response;
	}
}
