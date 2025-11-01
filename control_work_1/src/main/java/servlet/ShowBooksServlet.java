package servlet;

import dao.BookDAO;
import model.Book;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/show")
public class ShowBooksServlet extends BaseServlet {
    private BookDAO bookDAO;

    @Override
    public void init() {
        super.init();
        bookDAO = new BookDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Book> books = bookDAO.getAllBooks();

        Map<String, Object> data = new HashMap<>();
        data.put("pageTitle", "Все книги - Библиотека");
        data.put("books", books);

        renderTemplate(request, response, "showBooks.ftlh", data);
    }
}