<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Entidade.Funcionario"%>

<!DOCTYPE html>
<html lang="pt-br">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Funcionário</title>
        <link href="http://localhost:8080/TrabalhoFinal-ErikEGabriel/views/bootstrap/bootstrap.min.css"  rel="stylesheet">
    </head>

    <body>

        <div class="container">
            <jsp:include page="../comum/menu.jsp" />
            <div class="row mt-5">
                <div class="col-sm-4 offset-3">
                    <%
                        Funcionario funcionario = (Funcionario) request.getAttribute("funcionario");
                        String acao = (String) request.getAttribute("acao");
                        switch (acao) {
                            case "Incluir":
                                out.println("<h1>Incluir Funcionário</h1>");
                                break;
                            case "Alterar":
                                out.println("<h1>Alterar Funcionário</h1>");
                                break;
                            case "Excluir":
                                out.println("<h1>Excluir Funcionário</h1>");
                                break;
                        }

                        String msgError = (String) request.getAttribute("msgError");
                        if ((msgError != null) && (!msgError.isEmpty())) {%>
                    <div class="alert alert-danger" role="alert">
                        <%= msgError%>
                    </div>
                    <% }%>

                    <form action="/TrabalhoFinal-ErikEGabriel/admin/FuncionarioController" method="POST">
                        <input type="hidden" name="id" value="<%=funcionario.getId()%>" class="form-control">
                        <div class="mb-3">
                            <label for="nome" class="form-label" >Nome</label>
                            <input type="text" name="nome" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=funcionario.getNome()%>" class="form-control">
                            <label for="cpf" class="form-label" >CPF</label>
                            <input type="text" name="cpf" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=funcionario.getCpf()%>" class="form-control">
                            <label for="senha" class="form-label" >Senha</label>
                            <input type="text" name="senha" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=funcionario.getSenha()%>" class="form-control">
                            <label for="papel" class="form-label" >Papel</label>
                            <select class="form-select" id="papel" name="papel" required value="<%=funcionario.getPapel()%>" <%= acao.equals("Excluir") ? "Readonly" : ""%>>
                                <option value="0">admin</option>
                                <option value="1">vendedor</option>
                                <option value="2">comprador</option>
                            </select>
                        </div>
                        <div>
                            <input type="submit" name="btEnviar" value="<%=acao%>" class="btn btn-primary">
                            <a href="/TrabalhoFinal-ErikEGabriel/admin/FuncionarioController?acao=Listar" class="btn btn-danger">Retornar</a>
                        </div>
                    </form>

                </div>
            </div>
        </div>
        <script src="http://localhost:8080/TrabalhoFinal-ErikEGabriel/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>

</html>