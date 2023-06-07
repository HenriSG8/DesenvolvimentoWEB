<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="Paginainicial.jsp" method="POST">
                <% String mensagem = (String) request.getAttribute("mensagem");
                  if (mensagem != null) { %>
                <p id="Aviso"><%= mensagem %></p>
                <% } %>
                <input type="submit" value="Pagina inicial">
            </form>
</body>
</html>