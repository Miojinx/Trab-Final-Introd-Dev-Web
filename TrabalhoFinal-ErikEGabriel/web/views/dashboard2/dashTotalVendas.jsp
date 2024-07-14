<%@page import="Entidade.VendasTotais"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Dashboard Vendas</title>
        <link href="http://localhost:8080/TrabalhoFinal-ErikEGabriel/views/bootstrap/bootstrap.min.css"  rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <jsp:include page="../comum/menu.jsp" />
            <div class="mt-5">
                <h1>Dashboard das Vendas</h1>

                <%
                    ArrayList<VendasTotais> listaVendasTotais = (ArrayList<VendasTotais>) request.getAttribute("listaVendasTotais");
                    for (VendasTotais vendasTotais : listaVendasTotais) {%>

                <div class="card mb-2 col-sm-6">
                    <div class="card-body">
                        <strong>Produto:</strong> <%= vendasTotais.getIdProduto()%><br>
                        <strong>Data da venda:</strong><%= vendasTotais.getDataVenda()%><br>
                        <strong>Vendas no dia:</strong> <%= vendasTotais.getVendasDia()%><br>
                        <strong>Vendas totais do produto:</strong> <%= vendasTotais.getVendasTotais()%>
                    </div>
                </div>

                <%  }%>
            </div>
        </div>
        <script src="http://localhost:8080/TrabalhoFinal-ErikEGabriel/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>

