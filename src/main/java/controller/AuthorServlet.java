package controller;

import dao.AuthorDbDAO;
import dao.ConnectionProperty;
import domain.Author;
import exception.DAOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/author")
public class AuthorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    ConnectionProperty prop;

    public AuthorServlet() throws IOException {
        super();
        prop = new ConnectionProperty();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        List<Author> authors;
        AuthorDbDAO dao = new AuthorDbDAO();

        try {
            authors = dao.findAll();
            request.setAttribute("authors", authors);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("/views/author.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        AuthorDbDAO dao = new AuthorDbDAO();

        String fullName = request.getParameter("fullName");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String ratingStr = request.getParameter("rating");

        double rating = 0.0;
        try {
            rating = Double.parseDouble(ratingStr);
        } catch (Exception ignored) {
        }

        Author newAuthor = new Author(fullName, phone, email, rating);

        try {
            Long index = dao.insert(newAuthor);
            System.out.println("Adding author result: " + index);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        doGet(request, response);
    }
}