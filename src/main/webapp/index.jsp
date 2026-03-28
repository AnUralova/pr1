<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Управление библиотекой</title>

    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>

<jsp:include page="/views/header.jsp" />

    <div class="container main-content">
        <div class="list-group text-center py-3 px-3 shadow-sm rounded bg-white main-block">
            <h2 class="mb-4">Функции системы</h2>

            <a href="<%= request.getContextPath() %>/book">Книги</a>
			<a href="<%= request.getContextPath() %>/author">Авторы</a>
        </div>

        
        </div>

<jsp:include page="/views/footer.jsp" />

    <script src="js/jquery-3.6.4.js"></script>
    <script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>