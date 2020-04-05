package gtes.config;

import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException{
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(ApplicationConfig.class,SecurityConfig.class); //SecurityConfig.class

        servletContext.addListener(new ContextLoaderListener(rootContext));
        //для maximumSessions
        servletContext.addListener(new HttpSessionEventPublisher());

        AnnotationConfigWebApplicationContext servletAppContext = new AnnotationConfigWebApplicationContext();
        servletAppContext.register(MVCConfig.class);

        DispatcherServlet dispatcherServlet = new DispatcherServlet(servletAppContext);
        // throw NoHandlerFoundException to controller ExceptionHandler.class. Used for <error-page> analogue
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);

        //register and map the dispatcher servlet
        //note Dispatcher servlet with constructor arguments
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", dispatcherServlet);
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");

        FilterRegistration.Dynamic charsetFilter = servletContext.addFilter("charsetFilter",new CharacterEncodingFilter());
        charsetFilter.setInitParameter("encoding","UTF-8");
        charsetFilter.setInitParameter("forceEncoding","true");
        charsetFilter.addMappingForUrlPatterns(null,false,"/*");
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
//        return new Class[SecurityConfig.class];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[0];
    }

    @Override
    protected String[] getServletMappings() {
        return new String[0];
    }
}
