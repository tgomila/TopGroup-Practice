package com.tg.practice.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HibernateUtil {
	private static Logger log = LoggerFactory.getLogger(HibernateUtil.class);
	private static SessionFactory sessionFactory;

	static {
		try {
			sessionFactory = new Configuration().configure("hibernate.cfg.xml")
					.buildSessionFactory();
			log.info("Session factory creado correctamente");
		} catch (Exception e) {
			System.err.println("Initial SessionFactory creation failed." + e);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
