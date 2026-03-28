<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="ru">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Авторы</title>
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
                <h3>Список авторов</h3>

                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">Код</th>
                            <th scope="col">ФИО</th>
                            <th scope="col">Телефон</th>
                            <th scope="col">Эл.почта</th>
                            <th scope="col">Рейтинг</th>
                            <th scope="col">Редактировать</th>
                            <th scope="col">Удалить</th>
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
                <form method="POST" action="">
                    <h3>Новый автор</h3>
                    <br>

                    <div class="mb-3 row">
                        <label class="col-sm-3 col-form-label">ФИО</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" name="fullName" />
                        </div>
                    </div>

                    <div class="mb-3 row">
                        <label class="col-sm-3 col-form-label">Телефон</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" name="phone" />
                        </div>
                    </div>

                    <div class="mb-3 row">
                        <label class="col-sm-3 col-form-label">Эл.почта</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" name="email" />
                        </div>
                    </div>

                    <div class="mb-3 row">
                        <label class="col-sm-3 col-form-label">Рейтинг</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" name="rating" />
                        </div>
                    </div>

                    <p>
                        <button type="submit" class="btn btn-primary" disabled>
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