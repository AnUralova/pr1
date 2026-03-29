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

        request.getRequestDispatcher("/views/book.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        BookDbDAO dao = new BookDbDAO();
        AuthorDbDAO authorDao = new AuthorDbDAO();

        String title = request.getParameter("title");
        String binding = request.getParameter("binding");
        String publisher = request.getParameter("publisher");
        String publicationYearStr = request.getParameter("publicationYear");
        String genre = request.getParameter("genre");
        String authorIdStr = request.getParameter("authorId");

        int publicationYear = 0;
        try {
            publicationYear = Integer.parseInt(publicationYearStr);
        } catch (Exception ignored) {
        }

        Long idAuthor = null;
        try {
            idAuthor = Long.parseLong(authorIdStr);
        } catch (Exception ignored) {
        }

        Author author = null;
        try {
            if (idAuthor != null) {
                author = authorDao.findById(idAuthor);
            }
        } catch (DAOException e) {
            e.printStackTrace();
        }

        Book newBook = new Book(title, binding, publisher, publicationYear, genre, idAuthor, author);

        try {
            Long index = dao.insert(newBook);
            System.out.println("Adding book result: " + index);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        doGet(request, response);
    }
}