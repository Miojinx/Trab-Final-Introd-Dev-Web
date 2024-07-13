<%@page import="Entidade.Produtos"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Lista Produtos</title>
         <link href="http://localhost:8080/TrabalhoFinal-ErikEGabriel/views/bootstrap/bootstrap.min.css"  rel="stylesheet">

    </head>
    <body>
        <div class="container">
             <jsp:include page="../comum/menu.jsp" />
            <div class="mt-5">

                <h2>Lista de Produtos</h2>

                <a href="/TrabalhoFinal-ErikEGabriel/comprador/ProdutosController?acao=Incluir" class="mb-2 btn btn-primary">Incluir</a>
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th scope="col">Id</th>
                                <th scope="col">Nome</th>
                                <th scope="col">Descrição</th>
                                <th scope="col">Preço de compra</th>
                                <th scope="col">Preço de venda</th>
                                <th scope="col">Quantidade disponível</th>
                                <th scope="col">Liberado para venda</th>
                                <th scope="col">Id da Categoria</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                ArrayList<Produtos> listaProdutos = (ArrayList<Produtos>) request.getAttribute("listaProdutos");

                                for (Produtos produto : listaProdutos) {
                                    out.println("<tr>");
                                    out.println("<th>" + produto.getId() + "</th>");
                                    out.println("<td>" + produto.getNome_produto()+ "</td>");
                                    out.println("<td>" + produto.getDescricao()+ "</td>");
                                    out.println("<td>" + produto.getPreco_compra()+ "</td>");
                                    out.println("<td>" + produto.getPreco_venda()+ "</td>");
                                    out.println("<td>" + produto.getQuantidade_disponivel()+ "</td>");
                                    out.println("<td>" + produto.getLiberado_venda()+ "</td>");
                                    out.println("<td>" + produto.getId_categoria()+ "</td>");
                                    %>
                            <td>
                            <a href="/TrabalhoFinal-ErikEGabriel/comprador/ProdutosController?acao=Alterar&id=<%=produto.getId()%>" class="btn btn-warning">Alterar</a>
                            <a href="/TrabalhoFinal-ErikEGabriel/comprador/ProdutosController?acao=Excluir&id=<%=produto.getId()%>" class="btn btn-danger">Excluir</a></td>
                            
                            <%   out.println("</tr>");
                                }
                            %>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <script src="http://localhost:8080/TrabalhoFinal-ErikEGabriel/views/bootstrap/bootstrap.bundle.min.js"></script>

    </body>
</html>