package com.region20.student.app.dashboard;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@SpringBootConfiguration
@PropertySource("classpath:/application.properties")
@ComponentScan(basePackages={"com.region20.student.app.dashboard"})
public class StudentApp {

	@Bean
	public DriverManagerDataSource dataSource(){
		DriverManagerDataSource source = new DriverManagerDataSource(); 
		source.setDriverClassName("com.mysql.Driver");
		source.setUrl("jdbc:mysql://localhost:3306/StudentWebApp");
		source.setPassword("root");
		source.setUsername("root");
		
		return source; 
	}
	
	@Bean
	@Scope(value="singleton")
	public LocalSessionFactoryBean sessionFactory(){		
		LocalSessionFactoryBean session = new LocalSessionFactoryBean(); 
		session.setDataSource(dataSource());
		return session; 		
	}
	
	@Bean 
	public HibernateTransactionManager transactionManager(){
		HibernateTransactionManager manager = new HibernateTransactionManager((SessionFactory) sessionFactory()); 
		
		return manager; 
	}
	public static void main(String[] args) {
		SpringApplication.run(StudentApp.class, args);
	}

}
