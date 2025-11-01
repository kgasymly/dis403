package servlet;

import dao.BookDAO;
import model.Book;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/add")
public class AddBookServlet extends BaseServlet {
    private BookDAO bookDAO;

    @Override
    public void init() {
        super.init();
        bookDAO = new BookDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, Object> data = new HashMap<>();
        data.put("pageTitle", "Добавить книгу - Библиотека");
        data.put("isEdit", false);

        renderTemplate(request, response, "bookForm.ftlh", data);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String title = request.getParameter("title");
            String author = request.getParameter("author");
            String isbn = request.getParameter("isbn");
            int year = Integer.parseInt(request.getParameter("year"));
            String genre = request.getParameter("genre");
            boolean available = request.getParameter("available") != null;

            Book book = new Book(0, title, author, isbn, year, genre, available);

            if (bookDAO.addBook(book)) {
                response.sendRedirect(request.getContextPath() + "/show");
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}