package com.car.Utils;


import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.car.Entity.Brand;
import com.car.Entity.Car;
import com.car.Entity.Country;
import com.car.Entity.Model;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
     
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            // loads configuration and mappings
            Configuration configuration = new Configuration().configure();
            configuration.addAnnotatedClass(Brand.class);
            configuration.addAnnotatedClass(Car.class);
            configuration.addAnnotatedClass(Country.class);
            configuration.addAnnotatedClass(Model.class);
            ServiceRegistry serviceRegistry
                = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
             
            // builds a session factory from the service registry
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);           
        }
         
        return sessionFactory;
    }
}