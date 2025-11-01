import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebServlet("/templates/test")
public class TemplateTest extends HttpServlet {

    final static Logger logger = LogManager.getLogger(TemplateTest.class);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        logger.debug(request.getServletPath());

        request.setAttribute("default", "Ваше имя");
        request.setAttribute("param2", "Ваше имя");

        try {
            request.getRequestDispatcher("/templates/test.html")
                    .forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}