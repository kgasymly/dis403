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

@WebServlet("/showone")
public class ShowBookServlet extends BaseServlet {
    private BookDAO bookDAO;

    @Override
    public void init() {
        super.init();
        bookDAO = new BookDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if (idParam != null) {
            try {
                int id = Integer.parseInt(idParam);
                Book book = bookDAO.getBookById(id);
                if (book != null) {
                    Map<String, Object> data = new HashMap<>();
                    data.put("pageTitle", "Редактировать книгу - Библиотека");
                    data.put("book", book);
                    data.put("isEdit", true);

                    renderTemplate(request, response, "bookForm.ftlh", data);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                }
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String title = request.getParameter("title");
            String author = request.getParameter("author");
            String isbn = request.getParameter("isbn");
            int year = Integer.parseInt(request.getParameter("year"));
            String genre = request.getParameter("genre");
            boolean available = request.getParameter("available") != null;

            Book book = new Book(id, title, author, isbn, year, genre, available);

            if (bookDAO.updateBook(book)) {
                response.sendRedirect(request.getContextPath() + "/show");
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}