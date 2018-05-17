package com.car.Control;

import java.util.List;

import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.core.Response;
import com.car.Utils.AuthUtil;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.car.Entity.Car;
import com.car.Utils.HibernateUtil;

public class test {

	public static void main(String[] args) {
		
		Logger logger = Logger.getLogger(test.class);
		AuthUtil auth = new AuthUtil();
		String token = 
				"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL2FsZXhmaXdlYi5ldS5hdXRoMC5jb20vIiwic3ViIjoiYXV0aDB8NWFmMDc0NDc5MjA4YjgwNThhNGFkZDJjIiwiYXVkIjoielpCRTZ6VUkyclY3aUJoenJIeUk1TmFpYUtwdEZJVmYiLCJpYXQiOjE1MjU4NjE3NTgsImV4cCI6bnVsbH0.m8IHg23QDG3W3tJ1iLgknz8fx93kdexoT15MbhNpFE8";
		// TODO Auto-generated method stub
		String brand = "bmw";
		String country = "spain";
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		//Car car = new Car(brand, country);
		//session.save(car);		
		
		Query query = (Query) session.createQuery("from Car");
		List<Car> cars = (List<Car>) query.getResultList();
		session.getTransaction().commit();
		if(auth.verifyToken(token)) {
			logger.info("token correct");
			System.out.println("token correcto");
		}
		else {
			logger.warn("token incorrect");
			System.out.println("token invalido");
		}
	}

}
