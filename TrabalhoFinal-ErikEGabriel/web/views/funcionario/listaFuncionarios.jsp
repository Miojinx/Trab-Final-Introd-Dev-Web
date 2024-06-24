<%@page import="Entidade.Funcionario"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Lista Funcionarios</title>
         <link href="http://localhost:8080/TrabalhoFinal-ErikEGabriel/views/bootstrap/bootstrap.min.css"  rel="stylesheet">

    </head>
    <body>
        <div class="container">
             <jsp:include page="../../comum/menu.jsp" />
            <div class="mt-5">

                <h1>Área Restrita</h1>
                <h2>Lista de Categorias</h2>

                <a href="/TrabalhoFinal-ErikEGabriel/admin/FuncionarioController?acao=Incluir" class="mb-2 btn btn-primary">Incluir</a>
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th scope="col">Id</th>
                                <th scope="col">Nome</th>
                                <th scope="col">CPF</th>
                                <th scope="col">Senha</th>
                                <th scope="col">Papel</th>
                                <th scope="col">Ações</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                ArrayList<Funcionario> listaFuncionarios = (ArrayList<Funcionario>) request.getAttribute("listaFuncionarios");

                                for (Funcionario funcionario : listaFuncionarios) {
                                    out.println("<tr>");
                                    out.println("<th>" + funcionario.getId() + "</th>");
                                    out.println("<td>" + funcionario.getNome()+ "</td>");
                                    out.println("<td>" + funcionario.getCpf()+ "</td>");
                                    out.println("<td>" + funcionario.getSenha()+ "</td>");
                                    out.println("<td>" + funcionario.getPapel()+ "</td>");
                                    %>
                            <td>
                            <a href="/TrabalhoFinal-ErikEGabriel/admin/FuncionarioController?acao=Alterar&id=<%=funcionario.getId()%>" class="btn btn-warning">Alterar</a>
                            <a href="/TrabalhoFinal-ErikEGabriel/admin/FuncionarioController?acao=Excluir&id=<%=funcionario.getId()%>" class="btn btn-danger">Excluir</a></td>
                            
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

