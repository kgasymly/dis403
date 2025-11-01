package listener;

import freemarker.template.Configuration;
import freemarker.template.Version;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class FreeMarkerConfigListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Configuration cfg = new Configuration(new Version(2, 3, 32));

        cfg.setClassLoaderForTemplateLoading(
                Thread.currentThread().getContextClassLoader(),
                "template"
        );

        cfg.setDefaultEncoding("UTF-8");
        cfg.setLocale(java.util.Locale.forLanguageTag("ru"));
        cfg.setNumberFormat("0.##########");

        ServletContext context = sce.getServletContext();
        context.setAttribute("freemarkerConfiguration", cfg);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}