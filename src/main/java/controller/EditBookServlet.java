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

@WebServlet("/editbook")
public class EditBookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    ConnectionProperty prop;

    public EditBookServlet() throws IOException {
        super();
        prop = new ConnectionProperty();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        List<Book> books = null;
        List<Author> authors = null;
        Book editBook = null;

        BookDbDAO daoBook = new BookDbDAO();
        AuthorDbDAO daoAuthor = new AuthorDbDAO();

        try {
            authors = daoAuthor.findAll();
            request.setAttribute("authors", authors);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        try {
            books = daoBook.findAll();
            authors = daoAuthor.findAll();
            for (Book book : books) {
                book.setAuthor(daoAuthor.findById(book.getIdAuthor(), authors));
            }
        } catch (DAOException e) {
            e.printStackTrace();
        }

        String strId = request.getParameter("id");
        Long id = null;
        if (strId != null) {
            id = Long.parseLong(strId);
        }

        try {
            editBook = daoBook.findById(id);
            if (editBook != null && authors != null) {
                editBook.setAuthor(daoAuthor.findById(editBook.getIdAuthor(), authors));
            }
        } catch (DAOException e) {
            e.printStackTrace();
        }

        request.setAttribute("bookEdit", editBook);
        request.setAttribute("books", books);
        request.getRequestDispatcher("/views/editbook.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        BookDbDAO dao = new BookDbDAO();
        AuthorDbDAO daoAuthor = new AuthorDbDAO();

        String strId = request.getParameter("id");
        Long id = null;
        if (strId != null) {
            id = Long.parseLong(strId);
        }

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

        Author selectedAuthor = null;
        try {
            if (idAuthor != null) {
                selectedAuthor = daoAuthor.findById(idAuthor);
            }
        } catch (DAOException e) {
            e.printStackTrace();
        }

        Book editBook = new Book(id, title, binding, publisher, publicationYear, genre, idAuthor, selectedAuthor);

        try {
            dao.update(editBook);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "/book");
    }
}