package by.rabtsevich.web;

import by.rabtsevich.ApplicationConfiguration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import java.util.Set;

public class WebAppInitializer implements ServletContainerInitializer {

    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) {

        AnnotationConfigWebApplicationContext context
                = new AnnotationConfigWebApplicationContext();
        context.register(ApplicationConfiguration.class);

        DispatcherServlet dispatcherServlet =
                new DispatcherServlet(context);

        final ServletRegistration.Dynamic dispatcher =
                servletContext.addServlet("dispatcher", dispatcherServlet);
        dispatcher.addMapping("/");
        dispatcher.setLoadOnStartup(1);
    }
}
