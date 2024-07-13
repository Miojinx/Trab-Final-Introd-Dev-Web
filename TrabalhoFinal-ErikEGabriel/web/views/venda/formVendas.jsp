<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Entidade.Vendas"%>

<!DOCTYPE html>
<html lang="pt-br">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Form Vendas</title>
        <link href="http://localhost:8080/TrabalhoFinal-ErikEGabriel/views/bootstrap/bootstrap.min.css"  rel="stylesheet">
    </head>

    <body>

        <div class="container">
            <jsp:include page="../comum/menu.jsp" />
            <div class="row mt-5">
                <div class="col-sm-4 offset-3">
                    <%
                        Vendas venda = (Vendas) request.getAttribute("venda");
                        String acao = (String) request.getAttribute("acao");
                        switch (acao) {
                            case "Incluir":
                                out.println("<h1>Incluir Venda</h1>");
                                break;
                            case "Alterar":
                                out.println("<h1>Alterar Venda</h1>");
                                break;
                            case "Excluir":
                                out.println("<h1>Excluir Venda</h1>");
                                break;
                        }

                        String msgError = (String) request.getAttribute("msgError");
                        if ((msgError != null) && (!msgError.isEmpty())) {%>
                    <div class="alert alert-danger" role="alert">
                        <%= msgError%>
                    </div>
                    <% }%>

                    <form action="/TrabalhoFinal-ErikEGabriel/vendedor/VendasController" method="POST">
                        <input type="hidden" name="id" value="<%=venda.getId()%>" class="form-control">
                        <div class="mb-3">
                            <label for="quantidade_venda" class="form-label" >Quantidade Vendida</label>
                            <input type="text" name="quantidade_venda" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=venda.getQuantidade_venda()%>" class="form-control">
                            <label for="data_venda" class="form-label" >Data da Venda</label>
                            <input type="date" name="data_venda" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=venda.getData_venda()%>" class="form-control">
                            <label for="valor_venda" class="form-label" >Valor da Venda</label>
                            <input type="text" name="valor_venda" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=venda.getValor_venda()%>" class="form-control">
                            <label for="id_cliente" class="form-label" >ID do Cliente</label>
                            <input type="text" name="id_cliente" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=venda.getId_cliente()%>" class="form-control">
                            <label for="id_produto" class="form-label" >ID do Produto</label>
                            <input type="text" name="id_produto" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=venda.getId_produto()%>" class="form-control">
                            <label for="id_funcionario" class="form-label" >ID do Vendedor</label>
                            <input type="text" name="id_funcionario" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=venda.getId_funcionario()%>" class="form-control">
                        </div>
                        <div>
                            <input type="submit" name="btEnviar" value="<%=acao%>" class="btn btn-primary">
                            <a href="/TrabalhoFinal-ErikEGabriel/vendedor/VendasController?acao=Listar" class="btn btn-danger">Retornar</a>
                        </div>
                    </form>

                </div>
            </div>
        </div>
        <script src="http://localhost:8080/TrabalhoFinal-ErikEGabriel/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>

</html>