<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ page import="domain.Author"%>

<!DOCTYPE html>
<html lang="ru">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Авторы</title>

<link rel="stylesheet" href="css/bootstrap.min.css">
</head>

<body>

<div class="container-fluid">
    <jsp:include page="/views/header.jsp" />

    <div class="container mt-4">

        <h2>Список авторов</h2>

        <%
            Author a1 = new Author(1L, "Пушкин А.С.", "123456", "pushkin@mail.ru", 5);
            Author a2 = new Author(2L, "Толстой Л.Н.", "654321", "tolstoy@mail.ru", 5);

            Author[] authors = new Author[]{a1, a2};
        %>

        <table class="table table-striped">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>ФИО</th>
                    <th>Email</th>
                    <th>Телефон</th>
                    <th>Рейтинг</th>
                </tr>
            </thead>

            <tbody>
                <% for (Author a : authors) { %>
                <tr>
                    <td><%= a.getId() %></td>
                    <td><%= a.getFullName() %></td>
                    <td><%= a.getEmail() %></td>
                    <td><%= a.getPhone() %></td>
                    <td><%= a.getRating() %></td>
                </tr>
                <% } %>
            </tbody>
        </table>

    </div>

    <jsp:include page="/views/footer.jsp" />
</div>

</body>
</html>