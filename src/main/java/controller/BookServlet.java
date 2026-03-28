package controller;

import domain.Author;
import domain.Book;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/book")
public class BookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final List<Book> books = new ArrayList<>();

    @Override
    public void init() throws ServletException {
        if (books.isEmpty()) {
            List<Author> authors = AuthorServlet.getAuthors();

            if (authors.size() >= 4) {
                books.add(new Book(1L, "Евгений Онегин", "твёрдый", "АСТ", 2020, "роман", authors.get(0).getId(), authors.get(0)));
                books.add(new Book(2L, "Война и мир", "мягкий", "Эксмо", 2018, "роман", authors.get(1).getId(), authors.get(1)));
                books.add(new Book(3L, "Вишневый сад", "твёрдый", "Просвещение", 2019, "пьеса", authors.get(2).getId(), authors.get(2)));
                books.add(new Book(4L, "Преступление и наказание", "мягкий", "Феникс", 2021, "роман", authors.get(3).getId(), authors.get(3)));
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        String idStr = request.getParameter("id");

        if ("delete".equals(action) && idStr != null) {
            Long id = Long.parseLong(idStr);
            books.removeIf(b -> b.getId().equals(id));
            response.sendRedirect(request.getContextPath() + "/book");
            return;
        }

        if ("edit".equals(action) && idStr != null) {
            Long id = Long.parseLong(idStr);
            for (Book b : books) {
                if (b.getId().equals(id)) {
                    request.setAttribute("editBook", b);
                    break;
                }
            }
        }

        request.setAttribute("books", books);
        request.setAttribute("authors", AuthorServlet.getAuthors());
        request.getRequestDispatcher("/views/book.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String idStr = request.getParameter("id");
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
        if (idAuthor != null) {
            for (Author a : AuthorServlet.getAuthors()) {
                if (a.getId().equals(idAuthor)) {
                    selectedAuthor = a;
                    break;
                }
            }
        }

        if (idStr != null && !idStr.isEmpty()) {
            Long id = Long.parseLong(idStr);
            for (Book b : books) {
                if (b.getId().equals(id)) {
                    b.setTitle(title);
                    b.setBinding(binding);
                    b.setPublisher(publisher);
                    b.setPublicationYear(publicationYear);
                    b.setGenre(genre);
                    b.setIdAuthor(idAuthor);
                    b.setAuthor(selectedAuthor);
                    break;
                }
            }
        } else {
            Long newId = (long) (books.size() + 1);
            books.add(new Book(newId, title, binding, publisher, publicationYear, genre, idAuthor, selectedAuthor));
        }

        response.sendRedirect(request.getContextPath() + "/book");
    }
}