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

@WebServlet("/editauthor")
public class EditAuthorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    ConnectionProperty prop;

    public EditAuthorServlet() throws IOException {
        super();
        prop = new ConnectionProperty();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        List<Author> authors;
        Author editAuthor = null;
        AuthorDbDAO dao = new AuthorDbDAO();

        try {
            authors = dao.findAll();
            request.setAttribute("authors", authors);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        String strId = request.getParameter("id");
        Long id = null;
        if (strId != null) {
            id = Long.parseLong(strId);
        }

        try {
            editAuthor = dao.findById(id);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        request.setAttribute("authorEdit", editAuthor);
        request.getRequestDispatcher("/views/editauthor.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        AuthorDbDAO dao = new AuthorDbDAO();

        String strId = request.getParameter("id");
        Long id = null;
        if (strId != null) {
            id = Long.parseLong(strId);
        }

        String fullName = request.getParameter("fullName");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String ratingStr = request.getParameter("rating");

        double rating = 0.0;
        try {
            rating = Double.parseDouble(ratingStr);
        } catch (Exception ignored) {
        }

        Author editAuthor = new Author(id, fullName, phone, email, rating);

        try {
            dao.update(editAuthor);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "/author");
    }
}