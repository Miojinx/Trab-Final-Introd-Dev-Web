<%@page import="Entidade.Clientes"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Lista Clientes</title>
         <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css"  rel="stylesheet">

    </head>
    <body>
        <div class="container">
             <jsp:include page="../../comum/menu.jsp" />
            <div class="mt-5">

                <h2>Lista de Clientes</h2>

                <a href="/TrabalhoFinal-ErikEGabriel/vendedor/ClienteController?acao=Incluir" class="mb-2 btn btn-primary">Incluir</a>
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th scope="col">Id</th>
                                <th scope="col">Nome</th>
                                <th scope="col">CPF</th>
                                <th scope="col">Endereço</th>
                                <th scope="col">Bairro</th>
                                <th scope="col">Cidade</th>
                                <th scope="col">UF</th>
                                <th scope="col">Telefone</th>
                                <th scope="col">Email</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                ArrayList<Clientes> listaClientes = (ArrayList<Clientes>) request.getAttribute("listaClientes");

                                for (Clientes cliente : listaClientes) {
                                    out.println("<tr>");
                                    out.println("<th>" + cliente.getId() + "</th>");
                                    out.println("<td>" + cliente.getNome()+ "</td>");
                                    out.println("<td>" + cliente.getCpf()+ "</td>");
                                    out.println("<td>" + cliente.getEndereco()+ "</td>");
                                    out.println("<td>" + cliente.getBairro()+ "</td>");
                                    out.println("<td>" + cliente.getCidade()+ "</td>");
                                    out.println("<td>" + cliente.getUf()+ "</td>");
                                    out.println("<td>" + cliente.getTelefone()+ "</td>");
                                    out.println("<td>" + cliente.getEmail()+ "</td>");
                                    %>
                            <td>
                            <a href="/TrabalhoFinal-ErikEGabriel/vendedor/ClienteController?acao=Alterar&id=<%=cliente.getId()%>" class="btn btn-warning">Alterar</a>
                            <a href="/TrabalhoFinal-ErikEGabriel/vendedor/ClienteController?acao=Excluir&id=<%=cliente.getId()%>" class="btn btn-danger">Excluir</a></td>
                            
                            <%   out.println("</tr>");
                                }
                            %>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>

    </body>
</html>