<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page import="java.util.List"%>
<%@ page import="domain.Author"%>
<%@ page import="domain.Book"%>
<%
Book editBook = (Book) request.getAttribute("bookEdit");
List<Author> authorsList = (List<Author>) request.getAttribute("authors");
%>
<!DOCTYPE html>
<html lang="ru">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Редактирование книги</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/bootstrap.min.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
<script defer src="<%= request.getContextPath() %>/js/jquery-3.6.4.js"></script>
<script defer src="<%= request.getContextPath() %>/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="/views/header.jsp" />
    <div class="container-fluid mt-4">
        <div class="row justify-content-start">
            <div class="col-8 border bg-light px-4">
                <h3>Список книг</h3>
                <table class="table">
                    <thead>
                        <tr>
                            <th>Код</th>
                            <th>Название</th>
                            <th>Автор</th>
                            <th>Переплет</th>
                            <th>Издательство</th>
                            <th>Год</th>
                            <th>Жанр</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="book" items="${books}">
                            <tr>
                                <td>${book.getId()}</td>
                                <td>${book.getTitle()}</td>
                                <td>${book.getAuthor()}</td>
                                <td>${book.getBinding()}</td>
                                <td>${book.getPublisher()}</td>
                                <td>${book.getPublicationYear()}</td>
                                <td>${book.getGenre()}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

            <div class="col-4 border px-4">
                <form method="POST" action="">
                    <h3>Редактировать книгу</h3>
                    <br>

                    <div class="mb-3 row">
                        <label class="col-sm-4 col-form-label">Код</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" name="id" readonly
                                   value="${bookEdit.getId()}" />
                        </div>
                    </div>

                    <div class="mb-3 row">
                        <label class="col-sm-4 col-form-label">Название</label>
                        <div class="col-sm-8">
                            <input type="text" name="title" class="form-control"
                                   value="${bookEdit.getTitle()}" />
                        </div>
                    </div>

                    <div class="mb-3 row">
                        <label class="col-sm-4 col-form-label">Переплет</label>
                        <div class="col-sm-8">
                            <input type="text" name="binding" class="form-control"
                                   value="${bookEdit.getBinding()}" />
                        </div>
                    </div>

                    <div class="mb-3 row">
                        <label class="col-sm-4 col-form-label">Издательство</label>
                        <div class="col-sm-8">
                            <input type="text" name="publisher" class="form-control"
                                   value="${bookEdit.getPublisher()}" />
                        </div>
                    </div>

                    <div class="mb-3 row">
                        <label class="col-sm-4 col-form-label">Год</label>
                        <div class="col-sm-8">
                            <input type="text" name="publicationYear" class="form-control"
                                   value="${bookEdit.getPublicationYear()}" />
                        </div>
                    </div>

                    <div class="mb-3 row">
                        <label class="col-sm-4 col-form-label">Жанр</label>
                        <div class="col-sm-8">
                            <input type="text" name="genre" class="form-control"
                                   value="${bookEdit.getGenre()}" />
                        </div>
                    </div>

                    <div class="mb-3 row">
                        <label class="col-sm-4 col-form-label">Автор</label>
                        <div class="col-sm-8">
                            <select name="authorId" class="form-control">
                                <option value="">Выберите автора</option>
                                <% if (authorsList != null && editBook != null) {
                                    for (Author a : authorsList) {
                                        boolean selected = editBook.getIdAuthor() != null
                                                && editBook.getIdAuthor().equals(a.getId());
                                %>
                                    <option value="<%= a.getId() %>" <%= selected ? "selected" : "" %>>
                                        <%= a.getFullName() %>
                                    </option>
                                <%  }
                                } %>
                            </select>
                        </div>
                    </div>

                    <p>
                        <button type="submit" class="btn btn-primary">Редактировать</button>
                        <a href="<c:url value='/book'/>" role="button" class="btn btn-secondary">Отменить</a>
                    </p>
                </form>
            </div>
        </div>
    </div>
    <jsp:include page="/views/footer.jsp" />
</div>
</body>
</html>