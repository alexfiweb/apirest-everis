package com.car.Control;

import java.util.List;

import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.core.Response;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.car.Entity.Car;
import com.car.Utils.HibernateUtil;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String brand = "bmw";
		String country = "spain";
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Car car = new Car(brand, country);
		session.save(car);		
		
		Query query = session.createQuery("from Car");
		List<Car> cars = (List<Car>) query.getResultList();
		session.getTransaction().commit();
	}

}
