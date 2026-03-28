<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ page import="domain.Book"%>
<%@ page import="domain.Author"%>

<!DOCTYPE html>
<html lang="ru">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Книги</title>

<link rel="stylesheet" href="css/bootstrap.min.css">
</head>

<body>

<div class="container-fluid">
    <jsp:include page="/views/header.jsp" />

    <div class="container mt-4">

        <h2>Список книг</h2>

        <%
            Author a1 = new Author(1L, "Пушкин А.С.", "", "", 5);
            Author a2 = new Author(2L, "Толстой Л.Н.", "", "", 5);

            Book b1 = new Book(1L, "Евгений Онегин", "твёрдый", "АСТ", 2020, "роман", 1L, a1);
            Book b2 = new Book(2L, "Война и мир", "мягкий", "Эксмо", 2018, "роман", 2L, a2);

            Book[] books = new Book[]{b1, b2};
        %>

        <table class="table table-striped">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Название</th>
                    <th>Автор</th>
                    <th>Жанр</th>
                    <th>Год</th>
                </tr>
            </thead>

            <tbody>
                <% for (Book b : books) { %>
                <tr>
                    <td><%= b.getId() %></td>
                    <td><%= b.getTitle() %></td>
                    <td><%= b.getAuthor() %></td>
                    <td><%= b.getGenre() %></td>
                    <td><%= b.getPublicationYear() %></td>
                </tr>
                <% } %>
            </tbody>
        </table>

    </div>

    <jsp:include page="/views/footer.jsp" />
</div>

</body>
</html>