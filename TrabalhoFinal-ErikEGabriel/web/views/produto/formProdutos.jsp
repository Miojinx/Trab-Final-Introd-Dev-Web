<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Entidade.Produtos"%>

<!DOCTYPE html>
<html lang="pt-br">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Form Produtos</title>
        <link href="http://localhost:8080/TrabalhoFinal-ErikEGabriel/views/bootstrap/bootstrap.min.css"  rel="stylesheet">
    </head>

    <body>

        <div class="container">
            <jsp:include page="../comum/menu.jsp" />
            <div class="row mt-5">
                <div class="col-sm-4 offset-3">
                    <%
                        Produtos produto = (Produtos) request.getAttribute("produto");
                        String acao = (String) request.getAttribute("acao");
                        switch (acao) {
                            case "Incluir":
                                out.println("<h1>Incluir Produto</h1>");
                                break;
                            case "Alterar":
                                out.println("<h1>Alterar Produto</h1>");
                                break;
                            case "Excluir":
                                out.println("<h1>Excluir Produto</h1>");
                                break;
                        }

                        String msgError = (String) request.getAttribute("msgError");
                        if ((msgError != null) && (!msgError.isEmpty())) {%>
                    <div class="alert alert-danger" role="alert">
                        <%= msgError%>
                    </div>
                    <% }%>

                    <form action="/TrabalhoFinal-ErikEGabriel/comprador/ProdutosController" method="POST">
                        <input type="hidden" name="id" value="<%=produto.getId()%>" class="form-control">
                        <div class="mb-3">
                            <label for="nome_produto" class="form-label" >Nome</label>
                            <input type="text" name="nome_produto" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=produto.getNome_produto()%>" placeholder="" required class="form-control">
                            <label for="descricao" class="form-label" >Descrição</label>
                            <input type="text" name="descricao" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=produto.getDescricao()%>" placeholder="" required class="form-control">
                            <label for="preco_compra" class="form-label" >Preço de Compra</label>
                            <input type="text" name="preco_compra" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=produto.getPreco_compra()%>" required class="form-control">
                            <label for="preco_venda" class="form-label" >Preço de Venda</label>
                            <input type="text" name="preco_venda" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=produto.getPreco_venda()%>" required class="form-control">
                            <label for="quantidade_disponivel" class="form-label" >Quantidade disponível</label>
                            <input type="text" name="quantidade_disponivel" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=produto.getQuantidade_disponivel()%>" required class="form-control">
                            <label for="liberado_venda" class="form-label" >Liberado para venda</label>
                           <select class="form-select" id="liberado_venda" name="liberado_venda" required value="<%=produto.getLiberado_venda()%>" <%= acao.equals("Excluir") ? "Readonly" : ""%>>
                                <option value="S">Sim</option>
                                <option value="N">Não</option>
                            </select>
                            <label for="id_categoria" class="form-label" >ID da categoria</label>
                            <input type="text" name="id_categoria" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=produto.getId_categoria()%>" required class="form-control">
                        </div>
                        <div>
                            <input type="submit" name="btEnviar" value="<%=acao%>" class="btn btn-primary">
                            <a href="/TrabalhoFinal-ErikEGabriel/comprador/ProdutosController?acao=Listar" class="btn btn-danger">Retornar</a>
                        </div>
                    </form>

                </div>
            </div>
        </div>
        <script src="http://localhost:8080/TrabalhoFinal-ErikEGabriel/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>

</html>