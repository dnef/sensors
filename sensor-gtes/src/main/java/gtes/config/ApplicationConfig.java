package gtes.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import gtes.dao.impl.UserDAOImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.beans.PropertyVetoException;
import java.util.Properties;

@Configuration
@PropertySource(value = "classpath:util.properties")
@ComponentScan(basePackages = {"gtes"})
@EnableTransactionManagement
//<bean class="org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping"/>
//<bean class="org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter"/>
@EnableWebMvc
public class ApplicationConfig {
    static final Logger logger = LogManager.getLogger(UserDAOImpl.class.getName());

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Value("${jdbc.driverClass}")
    private String driverClass;
    @Value("${jdbc.url}")
    private String jdbcUrl;
    @Value("${jdbc.username}")
    private String user;
    @Value("${jdbc.password}")
    private String password;
    @Value("${c3p0.maxStatements}")
    private int maxStatements;
    @Value("${c3p0.maxStatementsPerConnection}")
    private int maxStatementsPerConnection;
    @Value("${c3p0.idleConnectionTestPeriod}")
    private int idleConnectionTestPeriod;
    @Value("${c3p0.minPoolSize}")
    private int minPoolSize;
    @Value("${c3p0.maxPoolSize}")
    private int maxPoolSize;
    @Value("${c3p0.maxIdleTime}")
    private int maxIdleTime;
    @Value("${c3p0.checkoutTimeout}")
    private int checkoutTimeout;
    @Value("${c3p0.testConnectionOnCheckin}")
    private boolean testConnectionOnCheckin;
    @Value("${c3p0.privilegeSpawnedThreads}")
    private boolean privilegeSpawnedThreads;
    @Value("${c3p0.contextClassLoaderSource}")
    private String contextClassLoaderSource;
    @Value("${hibernate.dialect}")
    private String hibernateDialect;
    @Value("${hibernate.show_sql}")
    private String hibernateShowSql;
    @Value("${hibernate.connection.url}")
    private String hibernateConnectionUrl;
    @Value("${hibernate.connection.driver_class}")
    private String hibernateDriverClass;
    @Value("${javax.persistence.validation.mode}")
    private String validationMode;

    //   <mvc:annotation-driven/>
    // <mvc:annotation-driven validator="validator"/>
    //<bean id="validator" class="org.springframework.validation.beanvalidation.LocalValidatorFactoryBean"/>
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setBasename("messages");
        return messageSource;
    }

    @Bean
    LocalValidatorFactoryBean getValidator() {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource(messageSource());
        validator.setParameterNameDiscoverer(new LocalVariableTableParameterNameDiscoverer());
        return validator;

    }

    @Bean
    MethodValidationPostProcessor getValidationPostProcessor(LocalValidatorFactoryBean validator) {
        MethodValidationPostProcessor processor = new MethodValidationPostProcessor();
        processor.setValidator(validator);
        return processor;
    }
//    @Bean
//    public javax.validation.Validator localValidatorFactoryBean() {
//        return new LocalValidatorFactoryBean();
//    }


    @Bean(name = "dataSource")
    public ComboPooledDataSource getComboPooledDataSource() {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass(driverClass);
            dataSource.setJdbcUrl(jdbcUrl);
            dataSource.setUser(user);
            dataSource.setPassword(password);
            //options
            dataSource.setMaxStatements(maxStatements);
            dataSource.setMaxStatementsPerConnection(maxStatementsPerConnection);
            dataSource.setIdleConnectionTestPeriod(idleConnectionTestPeriod);
            dataSource.setMinPoolSize(minPoolSize);
            dataSource.setMaxPoolSize(maxPoolSize);
            dataSource.setMaxIdleTime(maxIdleTime);
            dataSource.setCheckoutTimeout(checkoutTimeout);
            dataSource.setTestConnectionOnCheckout(testConnectionOnCheckin);
            dataSource.setPrivilegeSpawnedThreads(privilegeSpawnedThreads);
            dataSource.setContextClassLoaderSource(contextClassLoaderSource);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
            logger.info("datasource: {}", e);
        }
        return dataSource;
    }

    @Bean(name = "sessionFactory")
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(getComboPooledDataSource());
        sessionFactory.setPackagesToScan(new String[]{"gtes.entity"});
        sessionFactory.setHibernateProperties(hibernateProperties());
        logger.info("sessionFactory created");
        return sessionFactory;
    }

    @Bean(name = "transactionManager")
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager tm = new HibernateTransactionManager();
        tm.setSessionFactory(sessionFactory().getObject());
        return tm;
    }

    Properties hibernateProperties() {
        return new Properties() {
            {
                setProperty("hibernate.dialect", hibernateDialect);
                setProperty("hibernate.show_sql", hibernateShowSql);
                setProperty("hibernate.connection.url", hibernateConnectionUrl);
                setProperty("hibernate.connection.driver_class", hibernateDriverClass);
                setProperty("javax.persistence.validation.mode", validationMode);
            }
        };
    }

}
