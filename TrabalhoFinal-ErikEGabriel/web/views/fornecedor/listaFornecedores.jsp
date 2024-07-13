<%@page import="Entidade.Fornecedores"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Lista Fornecedores</title>
         <link href="http://localhost:8080/TrabalhoFinal-ErikEGabriel/views/bootstrap/bootstrap.min.css"  rel="stylesheet">

    </head>
    <body>
        <div class="container">
             <jsp:include page="../comum/menu.jsp" />
            <div class="mt-5">

                <h2>Lista de Fornecedores</h2>

                <a href="/TrabalhoFinal-ErikEGabriel/comprador/FornecedoresController?acao=Incluir" class="mb-2 btn btn-primary">Incluir</a>
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th scope="col">Id</th>
                                <th scope="col">Razão Social</th>
                                <th scope="col">CNPJ</th>
                                <th scope="col">Endereço</th>
                                <th scope="col">Bairro</th>
                                <th scope="col">Cidade</th>
                                <th scope="col">UF</th>
                                <th scope="col">CEP</th>
                                <th scope="col">Telefone</th>
                                <th scope="col">Email</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                ArrayList<Fornecedores> listaFornecedores = (ArrayList<Fornecedores>) request.getAttribute("listaFornecedores");

                                for (Fornecedores fornecedor : listaFornecedores) {
                                    out.println("<tr>");
                                    out.println("<th>" + fornecedor.getId() + "</th>");
                                    out.println("<td>" + fornecedor.getRazao_social()+ "</td>");
                                    out.println("<td>" + fornecedor.getCnpj()+ "</td>");
                                    out.println("<td>" + fornecedor.getEndereco()+ "</td>");
                                    out.println("<td>" + fornecedor.getBairro()+ "</td>");
                                    out.println("<td>" + fornecedor.getCidade()+ "</td>");
                                    out.println("<td>" + fornecedor.getUf()+ "</td>");
                                    out.println("<td>" + fornecedor.getCep()+ "</td>");
                                    out.println("<td>" + fornecedor.getTelefone()+ "</td>");
                                    out.println("<td>" + fornecedor.getEmail()+ "</td>");
                                    %>
                            <td>
                            <a href="/TrabalhoFinal-ErikEGabriel/comprador/FornecedoresController?acao=Alterar&id=<%=fornecedor.getId()%>" class="btn btn-warning">Alterar</a>
                            <a href="/TrabalhoFinal-ErikEGabriel/comprador/FornecedoresController?acao=Excluir&id=<%=fornecedor.getId()%>" class="btn btn-danger">Excluir</a></td>
                            
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