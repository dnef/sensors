package gtes.config;

import gtes.converter.RoleToUserProfileConverter;
import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;



@EnableWebMvc //<mvc:annotation-driven>
@Configuration
@ComponentScan(basePackages = {"gtes"}) //<context:component-scan base-package=''>
public class MVCConfig implements WebMvcConfigurer {
    @Autowired
    private ApplicationContext applicationContext;
    /**
     * <mvc:resources mapping="/resources/**" location="/resources/" />
     */
    @Autowired
    RoleToUserProfileConverter roleToUserProfileConverter;
    @Override
    public void addFormatters(FormatterRegistry registry){
        registry.addConverter(roleToUserProfileConverter);
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    ///------
    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver(){
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(this.applicationContext);
        templateResolver.setPrefix("/WEB-INF/views/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setCacheable(true);
        return templateResolver;
    }
    @Bean
    public SpringTemplateEngine templateEngine(){
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.addDialect(new SpringSecurityDialect());
        templateEngine.setEnableSpringELCompiler(true);
        templateEngine.addDialect(new Java8TimeDialect());
        return templateEngine;
    }
    @Bean
    public ThymeleafViewResolver viewResolver(){
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setCharacterEncoding("UTF-8");
        viewResolver.setContentType("text/html");
        viewResolver.setOrder(1);
        return viewResolver;
    }
//    @Bean(name = "xmlViewResolver")
//    public XmlViewResolver getXmlViewResolver() {
//        XmlViewResolver xmlViewResolver = new XmlViewResolver();
//        Resource resource = new ClassPathResource("pdf-config.xml");
//        xmlViewResolver.setOrder(0);
//        xmlViewResolver.setLocation(resource);
//        return xmlViewResolver;
//    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry){

        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/403").setViewName("403");
        registry.addViewController("/index").setViewName("index");
    }



}
