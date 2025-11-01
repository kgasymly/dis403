package servlet;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseServlet extends HttpServlet {
    protected Configuration freemarkerConfig;

    @Override
    public void init() {
        ServletContext context = getServletContext();
        freemarkerConfig = (Configuration) context.getAttribute("freemarkerConfiguration");
    }

    protected void renderTemplate(HttpServletRequest request, HttpServletResponse response,
                                  String templateName, Map<String, Object> data)
            throws IOException {
        if (data == null) {
            data = new HashMap<>();
        }

        // Context path теперь будет /controlwork
        data.put("contextPath", request.getContextPath());

        response.setContentType("text/html; charset=UTF-8");

        try {
            Template template = freemarkerConfig.getTemplate(templateName);
            template.process(data, response.getWriter());
        } catch (TemplateException e) {
            throw new IOException("Error processing FreeMarker template", e);
        }
    }
}