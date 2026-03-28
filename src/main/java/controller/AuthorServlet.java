package controller;

import domain.Author;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/author")
public class AuthorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final List<Author> authors = new ArrayList<>();

    @Override
    public void init() throws ServletException {
        if (authors.isEmpty()) {
            authors.add(new Author(1L, "Пушкин А.С.", "+7 (999)-111-11-11", "pushkin@mail.ru", 5.0));
            authors.add(new Author(2L, "Толстой Л.Н.", "+7 (999)-222-22-22", "tolstoy@mail.ru", 4.9));
            authors.add(new Author(3L, "Чехов А.П.", "+7 (999)-333-33-33", "chehov@mail.ru", 4.8));
            authors.add(new Author(4L, "Достоевский Ф.М.", "+7 (999)-444-44-44", "dostoevsky@mail.ru", 5.0));
        }
    }

    public static List<Author> getAuthors() {
        return authors;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        String idStr = request.getParameter("id");

        if ("delete".equals(action) && idStr != null) {
            Long id = Long.parseLong(idStr);
            authors.removeIf(a -> a.getId().equals(id));
            response.sendRedirect(request.getContextPath() + "/author");
            return;
        }

        if ("edit".equals(action) && idStr != null) {
            Long id = Long.parseLong(idStr);
            for (Author a : authors) {
                if (a.getId().equals(id)) {
                    request.setAttribute("editAuthor", a);
                    break;
                }
            }
        }

        request.setAttribute("authors", authors);
        request.getRequestDispatcher("/views/author.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String idStr = request.getParameter("id");
        String fullName = request.getParameter("fullName");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String ratingStr = request.getParameter("rating");

        double rating = 0.0;
        try {
            rating = Double.parseDouble(ratingStr);
        } catch (Exception ignored) {
        }

        if (idStr != null && !idStr.isEmpty()) {
            Long id = Long.parseLong(idStr);
            for (Author a : authors) {
                if (a.getId().equals(id)) {
                    a.setFullName(fullName);
                    a.setPhone(phone);
                    a.setEmail(email);
                    a.setRating(rating);
                    break;
                }
            }
        } else {
            Long newId = (long) (authors.size() + 1);
            authors.add(new Author(newId, fullName, phone, email, rating));
        }

        response.sendRedirect(request.getContextPath() + "/author");
    }
}