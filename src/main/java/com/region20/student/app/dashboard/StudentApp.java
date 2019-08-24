package com.region20.student.app.dashboard;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@SpringBootApplication
@EnableAutoConfiguration
@PropertySource("classpath:/application.properties")
@ComponentScan(basePackages={"com.region20.student.app.dashboard"})
public class StudentApp extends SpringBootServletInitializer{

	@Autowired
	private Environment env; 
	
	@Bean
	public DriverManagerDataSource dataSource(){
		DriverManagerDataSource source = new DriverManagerDataSource(); 
		source.setDriverClassName("com.mysql.cj.jdbc.Driver");
		source.setUrl("jdbc:mysql://localhost:3306/StudentWebApp");
		source.setPassword("root");
		source.setUsername("root");
		
		return source; 
	}
			
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

         LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
         entityManagerFactoryBean.setDataSource(dataSource());
         entityManagerFactoryBean.setPackagesToScan(new String[]{"com.region20.student.app.dashboard.persistence"});

         HibernateJpaVendorAdapter vendor = new HibernateJpaVendorAdapter(); 
         entityManagerFactoryBean.setJpaVendorAdapter(vendor);
         entityManagerFactoryBean.setJpaProperties(additionalProperties());
         return entityManagerFactoryBean;
     }

    @Bean
    public PlatformTransactionManager transactionManager(final EntityManagerFactory emf) {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
    
    private Properties additionalProperties() {
        final Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        hibernateProperties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
        hibernateProperties.setProperty("hibernate.cache.use_second_level_cache", "false");
        
    
        return hibernateProperties;
    }


	public static void main(String[] args) {
		SpringApplication.run(StudentApp.class, args);
	}

}
