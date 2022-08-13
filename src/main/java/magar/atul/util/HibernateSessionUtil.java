package magar.atul.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import magar.atul.model.Employee;
import magar.atul.model.Payroll;
import magar.atul.model.Project;



public class HibernateSessionUtil {
	
	private static SessionFactory factory;
	
	public static SessionFactory buildSessionFactory() {
		
		//load configuration
		factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Employee.class)
				.addAnnotatedClass(Payroll.class)
				.addAnnotatedClass(Project.class)
				.buildSessionFactory();
		
		return factory;
	}
	

}

