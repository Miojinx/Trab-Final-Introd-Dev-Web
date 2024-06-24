<%@page import="Entidade.Produtos"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Produtos</title>
        <link href="http://localhost:8080/TrabalhoFinal-ErikEGabriel/views/bootstrap/bootstrap.min.css"  rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <jsp:include page="../comum/menu.jsp" />
            <div class="mt-5">
                <h1>Produtos disponíveis em nossa loja</h1>

                <%
                    ArrayList<Produtos> listaProdutos = (ArrayList<Produtos>) request.getAttribute("listaProdutos");
                    for (Produtos produto : listaProdutos) {%>

                <div class="card mb-2 col-sm-6">
                    <div class="card-body">
                        <strong>Número:</strong> <%= produto.getId()%><br>
                        <strong><%= produto.getNome_produto()%></strong><br>
                        <strong>Preço:</strong> <%= produto.getPreco_venda()%><br>
                        <strong>Quantidade Disponível</strong> <%= produto.getQuantidade_disponivel()%>
                    </div>
                </div>

                <%  }%>
            </div>
        </div>
        <script src="http://localhost:8080/TrabalhoFinal-ErikEGabriel/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>

