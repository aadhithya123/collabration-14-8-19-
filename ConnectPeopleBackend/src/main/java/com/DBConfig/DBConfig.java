package com.DBConfig;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.WeChat.Blog;
import com.WeChat.UserDetails;
import com.WeChat.Blogcomment;
import com.WeChat.Forum;
import com.WeChat.Friend;
import com.WeChat.Job;
import com.WeChat.ProfilePicture;

@Configuration
@EnableTransactionManagement
public class DBConfig {

	
	//========================================DATA SOURCE=====================================================================
	
	
    public DataSource getOracleDatasource()
    {
    	
    	DriverManagerDataSource dt=new DriverManagerDataSource();
    	dt.setDriverClassName("oracle.jdbc.driver.OracleDriver");
    	dt.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
    	dt.setUsername("hr");
    	dt.setPassword("oracle");
		return dt;
    }
    
    
  //========================================SESSION FACTORY=====================================================================
    
    
    @Autowired
    @Bean
    public SessionFactory getSessionFactory() {	
    	Properties pro=new Properties();
    	pro.put("hibernate.hbm2ddl.auto","update");
    	pro.put("hibernate.dialect","org.hibernate.dialect.Oracle10gDialect");
    	LocalSessionFactoryBuilder builder=new LocalSessionFactoryBuilder(getOracleDatasource());
    	builder.addProperties(pro);
    	builder.addAnnotatedClass(UserDetails.class);
    	builder.addAnnotatedClass(Blog.class);
    	builder.addAnnotatedClass(Blogcomment.class);
    	builder.addAnnotatedClass(Job.class);
    	builder.addAnnotatedClass(Friend.class);
    	builder.addAnnotatedClass(ProfilePicture.class);
    	builder.addAnnotatedClass(Forum.class);
    	SessionFactory session=builder.buildSessionFactory();
    	return session;
    }
    
    
  //========================================HIBERNATE TRANSACTION MANAGER========================================================
    
    
    @Autowired
    @Bean("tx")
    public HibernateTransactionManager getHibernateTransctionManager() {
    	HibernateTransactionManager tx=new HibernateTransactionManager(getSessionFactory());
    	return tx;
    }
    
}
