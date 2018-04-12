package org.ricki.catalog;

import com.vaadin.spring.annotation.EnableVaadin;
import com.vaadin.spring.server.SpringVaadinServlet;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.ricki.catalog.system.security.AuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@PropertySource({"classpath:application.properties"})
//@EnableVaadinNavigation
@EnableVaadin
@ComponentScan(basePackages = {"org.ricki.catalog", "org.ricki.catalog.web"})
public class SpringAppConfig implements WebApplicationInitializer {

  @Inject
  private Environment env;

  @Bean
  public LocalSessionFactoryBean sessionFactory() {
    LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
    sessionFactory.setDataSource(restDataSource());
    sessionFactory.setPackagesToScan(
            new String[]{"org.ricki.catalog.entity"});
    sessionFactory.setHibernateProperties(hibernateProperties());

    return sessionFactory;
//    return null;
  }

  @Bean
  public DataSource restDataSource() {
    BasicDataSource dataSource = new BasicDataSource();
    dataSource.setDriverClassName(env.getProperty("spring.dataSource.driverClassName"));
    dataSource.setUrl(env.getProperty("spring.dataSource.url"));
    dataSource.setUsername(env.getProperty("spring.dataSource.username"));
    dataSource.setPassword(env.getProperty("spring.dataSource.password"));

    return dataSource;
  }

  @Bean
  @Inject
  public HibernateTransactionManager transactionManager(
          SessionFactory sessionFactory) {

    HibernateTransactionManager txManager
            = new HibernateTransactionManager();
    txManager.setSessionFactory(sessionFactory);

    return txManager;
  }

  @Bean
  public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
    return new PersistenceExceptionTranslationPostProcessor();
  }

  Properties hibernateProperties() {
    return new Properties() {
      {
        setProperty("hibernate.hbm2ddl.auto",
                env.getProperty("spring.jpa.hibernate.ddl-auto"));
        setProperty("hibernate.dialect",
                env.getProperty("spring.jpa.properties.hibernate.dialect"));
        setProperty("hibernate.globally_quoted_identifiers",
                "true");
      }
    };
  }


  @Override
  public void onStartup(ServletContext servletContext) throws ServletException {
    AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
    rootContext.register(SpringAppConfig.class);
    servletContext.addListener(new ContextLoaderListener(rootContext));


    SpringVaadinServlet appSpringVaadinServlet = new SpringVaadinServlet();
//    appSpringVaadinServlet.setServiceUrlPath("/");
    ServletRegistration.Dynamic dispatcher = servletContext.addServlet(
            "vaadin", appSpringVaadinServlet);
    dispatcher.setLoadOnStartup(1);
    dispatcher.addMapping("/*");

    AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();

    ServletRegistration.Dynamic springDispatcher = servletContext.addServlet("marker", new DispatcherServlet(dispatcherContext));
    springDispatcher.setLoadOnStartup(1);
    springDispatcher.addMapping("/token/*");


//    appSpringVaadinServlet.getServletContext().addFilter("auth", new AuthFilter());
    servletContext.addFilter("auth", new AuthFilter()).addMappingForServletNames(null, false, "vaadin");
  }


}

/*

plan
id, date, specie_id, actionVoc_id
-----------------------


 */