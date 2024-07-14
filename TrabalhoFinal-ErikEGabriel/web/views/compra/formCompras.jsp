<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Entidade.Compras"%>

<!DOCTYPE html>
<html lang="pt-br">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Form Compras</title>
        <link href="http://localhost:8080/TrabalhoFinal-ErikEGabriel/views/bootstrap/bootstrap.min.css"  rel="stylesheet">
    </head>

    <body>

        <div class="container">
            <jsp:include page="../comum/menu.jsp" />
            <div class="row mt-5">
                <div class="col-sm-4 offset-3">
                    <%
                        Compras compra = (Compras) request.getAttribute("compra");
                        String acao = (String) request.getAttribute("acao");
                        switch (acao) {
                            case "Incluir":
                                out.println("<h1>Incluir Compra</h1>");
                                break;
                            case "Alterar":
                                out.println("<h1>Alterar Compra</h1>");
                                break;
                            case "Excluir":
                                out.println("<h1>Excluir Compra</h1>");
                                break;
                        }

                        String msgError = (String) request.getAttribute("msgError");
                        if ((msgError != null) && (!msgError.isEmpty())) {%>
                    <div class="alert alert-danger" role="alert">
                        <%= msgError%>
                    </div>
                    <% }%>

                    <form action="/TrabalhoFinal-ErikEGabriel/comprador/ComprasController" method="POST">
                        <input type="hidden" name="id" value="<%=compra.getId()%>" class="form-control">
                        <div class="mb-3">
                            <label for="quantidade_compra" class="form-label" >Quantidade Comprada</label>
                            <input type="text" name="quantidade_compra" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=compra.getQuantidade_compra()%>" required class="form-control">
                            <label for="data_compra" class="form-label" >Data da Compra</label>
                            <input type="date" name="data_compra" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=compra.getData_compra()%>" required class="form-control">
                            <label for="valor_compra" class="form-label" >Valor da Compra</label>
                            <input type="text" name="valor_compra" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=compra.getValor_compra()%>" required class="form-control">
                            <label for="id_fornecedor" class="form-label" >ID do Fornecedor</label>
                            <input type="text" name="id_fornecedor" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=compra.getId_fornecedor()%>" required class="form-control">
                            <label for="id_produto" class="form-label" >ID do Produto</label>
                            <input type="text" name="id_produto" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=compra.getId_produto()%>" required class="form-control">
                            <label for="id_funcionario" class="form-label" >ID do Comprador</label>
                            <input type="text" name="id_funcionario" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=compra.getId_funcionario()%>" required class="form-control">
                        </div>
                        <div>
                            <input type="submit" name="btEnviar" value="<%=acao%>" class="btn btn-primary">
                            <a href="/TrabalhoFinal-ErikEGabriel/comprador/ComprasController?acao=Listar" class="btn btn-danger">Retornar</a>
                        </div>
                    </form>

                </div>
            </div>
        </div>
        <script src="http://localhost:8080/TrabalhoFinal-ErikEGabriel/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>

</html>