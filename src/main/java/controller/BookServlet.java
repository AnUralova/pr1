package controller;

import dao.AuthorDbDAO;
import dao.BookDbDAO;
import dao.ConnectionProperty;
import domain.Author;
import domain.Book;
import exception.DAOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/book")
public class BookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    ConnectionProperty prop;

    public BookServlet() throws IOException {
        super();
        prop = new ConnectionProperty();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        List<Book> books;
        List<Author> authors;

        BookDbDAO bookDao = new BookDbDAO();
        AuthorDbDAO authorDao = new AuthorDbDAO();

        try {
            books = bookDao.findAll();
            authors = authorDao.findAll();

            for (Book book : books) {
                book.setAuthor(authorDao.findById(book.getIdAuthor(), authors));
            }

            request.setAttribute("books", books);
            request.setAttribute("authors", authors);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        if ("/book".equals(request.getServletPath())) {
            request.getRequestDispatcher("/views/book.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}