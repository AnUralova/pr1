<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="ru">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Книги</title>
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
                            <th scope="col">Код</th>
                            <th scope="col">Название</th>
                            <th scope="col">Автор</th>
                            <th scope="col">Переплет</th>
                            <th scope="col">Издательство</th>
                            <th scope="col">Год</th>
                            <th scope="col">Жанр</th>
                            <th scope="col">Редактировать</th>
                            <th scope="col">Удалить</th>
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
                                <td width="20">
                                    <a href="#" role="button" class="btn btn-outline-primary btn-sm">
                                        Ред.
                                    </a>
                                </td>
                                <td width="20">
                                    <a href="#" role="button" class="btn btn-outline-danger btn-sm">
                                        Удал.
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

            <div class="col-4 border px-4">
                <form method="POST" action="<%= request.getContextPath() %>/book">
                    <h3>Новая книга</h3>
                    <br>

                    <div class="mb-3 row">
                        <label class="col-sm-4 col-form-label">Название</label>
                        <div class="col-sm-8">
                            <input type="text" name="title" class="form-control" />
                        </div>
                    </div>

                    <div class="mb-3 row">
                        <label class="col-sm-4 col-form-label">Переплет</label>
                        <div class="col-sm-8">
                            <input type="text" name="binding" class="form-control" />
                        </div>
                    </div>

                    <div class="mb-3 row">
                        <label class="col-sm-4 col-form-label">Издательство</label>
                        <div class="col-sm-8">
                            <input type="text" name="publisher" class="form-control" />
                        </div>
                    </div>

                    <div class="mb-3 row">
                        <label class="col-sm-4 col-form-label">Год</label>
                        <div class="col-sm-8">
                            <input type="text" name="publicationYear" class="form-control" />
                        </div>
                    </div>

                    <div class="mb-3 row">
                        <label class="col-sm-4 col-form-label">Жанр</label>
                        <div class="col-sm-8">
                            <input type="text" name="genre" class="form-control" />
                        </div>
                    </div>

                    <div class="mb-3 row">
                        <label class="col-sm-4 col-form-label">Автор</label>
                        <div class="col-sm-8">
                            <select name="authorId" class="form-control">
                                <option value="">Выберите автора</option>
                                <c:forEach var="author" items="${authors}">
                                    <option value="${author.getId()}">
                                        ${author.getFullName()}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <p>
                        <button type="submit" class="btn btn-primary">
                            Добавить
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