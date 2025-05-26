<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%
    List<String> images = (List<String>) request.getAttribute("images");
%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Meu Portfólio</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <style>
        .gallery {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
        }

        .gallery img {
            width: 200px;
            height: auto;
            margin: 10px;
            border-radius: 8px;
            border: 1px solid #ccc;
            box-shadow: 0 0 5px rgba(0,0,0,0.2);
            transition: transform 0.2s;
        }

        .gallery img:hover {
            transform: scale(1.05);
        }

        .card-frame {
            border: 2px solid #007bff;
            border-radius: 12px;
            padding: 20px;
            background-color: #ffffff;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
    </style>
</head>
<body class="bg-light">

<div class="container py-5">

    <div class="text-center mb-4">
        <h2>Meu Portfólio</h2>
    </div>

    <form action="${pageContext.request.contextPath}/portfolio" method="post" enctype="multipart/form-data" class="mb-5">
        <div class="form-group">
            <label for="image">Adicionar nova imagem:</label>
            <input type="file" name="image" id="image" class="form-control-file" required>
        </div>
        <button type="submit" class="btn btn-primary">Enviar</button>
        <a href="${pageContext.request.contextPath}/teacher/homeTeacher.jsp" class="btn btn-danger">Voltar para o Menu</a>
    </form>

    <div class="card-frame">
        <div class="gallery">
            <%
                if (images != null && !images.isEmpty()) {
                    for (String img : images) {
            %>
                        <img src="<%= request.getContextPath() + "/" + img %>" alt="Imagem do portfólio">
            <%
                    }
                } else {
            %>
                <p class="text-center w-100">Nenhuma imagem cadastrada no portfólio.</p>
            <%
                }
            %>
        </div>
    </div>

</div>

</body>
</html>
