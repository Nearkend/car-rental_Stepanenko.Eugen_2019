package ua.nure.stepanenko.thesis.web.listener;

import org.apache.log4j.PropertyConfigurator;
import ua.nure.stepanenko.thesis.web.command.util.CommandUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener("/*")
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        log("Context init started");

        ServletContext servletContext = servletContextEvent.getServletContext();
        initLog4j(servletContext);
        initCommandContainer();

        log("Context init finished");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        log("Context destroy started");

        CommandUtils.getService().shutdownNow();

        log("Context destroy finished");
    }

    private void initLog4j(ServletContext servletContext) {
        log("Log4j init started");

        PropertyConfigurator.configure(
                servletContext.getRealPath("WEB-INF/resources/log4j.properties"));

        log("Log4j init finished");
    }

    private void initCommandContainer() {
        log("CommandContainer initialization started");



        log("CommandContainer initialization finished");
    }

    private void log(String msg) {
        System.out.println("[ContextListener] " + msg);
    }
}
