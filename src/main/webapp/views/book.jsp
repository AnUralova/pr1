<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page import="java.util.List"%>
<%@ page import="domain.Book"%>
<%@ page import="domain.Author"%>

<%
List<Book> books = (List<Book>) request.getAttribute("books");
List<Author> authors = (List<Author>) request.getAttribute("authors");
Book editBook = (Book) request.getAttribute("editBook");

pageContext.setAttribute("books", books);
pageContext.setAttribute("authors", authors);
%>

<!DOCTYPE html>
<html lang="ru">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Книги</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/bootstrap.min.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
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
                            <th>Редактировать</th>
                            <th>Удалить</th>
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
                                <td>
                                    <a href="<%= request.getContextPath() %>/book?action=edit&id=${book.getId()}"
                                       class="btn btn-outline-primary btn-sm">Ред.</a>
                                </td>
                                <td>
                                    <a href="<%= request.getContextPath() %>/book?action=delete&id=${book.getId()}"
                                       class="btn btn-outline-danger btn-sm">Удал.</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

            <div class="col-4 border px-4">
                <form method="POST" action="<%= request.getContextPath() %>/book">
                    <h3><%= editBook != null ? "Редактировать книгу" : "Новая книга" %></h3>
                    <br>

                    <input type="hidden" name="id" value="<%= editBook != null ? editBook.getId() : "" %>">

                    <div class="mb-3 row">
                        <label class="col-sm-4 col-form-label">Название</label>
                        <div class="col-sm-8">
                            <input type="text" name="title" class="form-control"
                                   value="<%= editBook != null ? editBook.getTitle() : "" %>">
                        </div>
                    </div>

                    <div class="mb-3 row">
                        <label class="col-sm-4 col-form-label">Переплет</label>
                        <div class="col-sm-8">
                            <input type="text" name="binding" class="form-control"
                                   value="<%= editBook != null ? editBook.getBinding() : "" %>">
                        </div>
                    </div>

                    <div class="mb-3 row">
                        <label class="col-sm-4 col-form-label">Издательство</label>
                        <div class="col-sm-8">
                            <input type="text" name="publisher" class="form-control"
                                   value="<%= editBook != null ? editBook.getPublisher() : "" %>">
                        </div>
                    </div>

                    <div class="mb-3 row">
                        <label class="col-sm-4 col-form-label">Год</label>
                        <div class="col-sm-8">
                            <input type="text" name="publicationYear" class="form-control"
                                   value="<%= editBook != null ? editBook.getPublicationYear() : "" %>">
                        </div>
                    </div>

                    <div class="mb-3 row">
                        <label class="col-sm-4 col-form-label">Жанр</label>
                        <div class="col-sm-8">
                            <input type="text" name="genre" class="form-control"
                                   value="<%= editBook != null ? editBook.getGenre() : "" %>">
                        </div>
                    </div>

                    <div class="mb-3 row">
                        <label class="col-sm-4 col-form-label">Автор</label>
                        <div class="col-sm-8">
                            <select name="authorId" class="form-control">
                                <option value="">Выберите автора</option>
                                <c:forEach var="author" items="${authors}">
                                    <option value="${author.getId()}"
                                        <%= (editBook != null && editBook.getIdAuthor() != null
                                            && editBook.getIdAuthor().toString().equals(String.valueOf(pageContext.findAttribute("author") != null ? ((Author)pageContext.findAttribute("author")).getId() : "")))
                                            ? "selected" : "" %>>
                                        ${author.getFullName()}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <p>
                        <button type="submit" class="btn btn-primary">
                            <%= editBook != null ? "Сохранить" : "Добавить" %>
                        </button>
                    </p>
                </form>
            </div>
        </div>
    </div>

    <jsp:include page="/views/footer.jsp" />
</div>
</body>
</html>