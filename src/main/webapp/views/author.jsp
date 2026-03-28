<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page import="java.util.List"%>
<%@ page import="domain.Author"%>

<%
List<Author> authors = (List<Author>) request.getAttribute("authors");
Author editAuthor = (Author) request.getAttribute("editAuthor");
pageContext.setAttribute("authors", authors);
%>

<!DOCTYPE html>
<html lang="ru">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Авторы</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/bootstrap.min.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
<div class="container-fluid">
    <jsp:include page="/views/header.jsp" />

    <div class="container-fluid mt-4">
        <div class="row justify-content-start">
            <div class="col-8 border bg-light px-4">
                <h3>Список авторов</h3>

                <table class="table">
                    <thead>
                        <tr>
                            <th>Код</th>
                            <th>ФИО</th>
                            <th>Телефон</th>
                            <th>Эл.почта</th>
                            <th>Рейтинг</th>
                            <th>Редактировать</th>
                            <th>Удалить</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="author" items="${authors}">
                            <tr>
                                <td>${author.getId()}</td>
                                <td>${author.getFullName()}</td>
                                <td>${author.getPhone()}</td>
                                <td>${author.getEmail()}</td>
                                <td>${author.getRating()}</td>
                                <td>
                                    <a href="<%= request.getContextPath() %>/author?action=edit&id=${author.getId()}"
                                       class="btn btn-outline-primary btn-sm">Ред.</a>
                                </td>
                                <td>
                                    <a href="<%= request.getContextPath() %>/author?action=delete&id=${author.getId()}"
                                       class="btn btn-outline-danger btn-sm">Удал.</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

            <div class="col-4 border px-4">
                <form method="POST" action="<%= request.getContextPath() %>/author">
                    <h3><%= editAuthor != null ? "Редактировать автора" : "Новый автор" %></h3>
                    <br>

                    <input type="hidden" name="id" value="<%= editAuthor != null ? editAuthor.getId() : "" %>">

                    <div class="mb-3 row">
                        <label class="col-sm-3 col-form-label">ФИО</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" name="fullName"
                                   value="<%= editAuthor != null ? editAuthor.getFullName() : "" %>"/>
                        </div>
                    </div>

                    <div class="mb-3 row">
                        <label class="col-sm-3 col-form-label">Телефон</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" name="phone"
                                   value="<%= editAuthor != null ? editAuthor.getPhone() : "" %>"/>
                        </div>
                    </div>

                    <div class="mb-3 row">
                        <label class="col-sm-3 col-form-label">Эл.почта</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" name="email"
                                   value="<%= editAuthor != null ? editAuthor.getEmail() : "" %>"/>
                        </div>
                    </div>

                    <div class="mb-3 row">
                        <label class="col-sm-3 col-form-label">Рейтинг</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" name="rating"
                                   value="<%= editAuthor != null ? editAuthor.getRating() : "" %>"/>
                        </div>
                    </div>

                    <p>
                        <button type="submit" class="btn btn-primary">
                            <%= editAuthor != null ? "Сохранить" : "Добавить" %>
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