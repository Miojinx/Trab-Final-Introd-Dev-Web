<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Entidade.Clientes"%>

<!DOCTYPE html>
<html lang="pt-br">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Cliente</title>
        <link href="http://localhost:8080/TrabalhoFinal-ErikEGabriel/views/bootstrap/bootstrap.min.css"  rel="stylesheet">
    </head>

    <body>

        <div class="container">
            <jsp:include page="../comum/menu.jsp" />
            <div class="row mt-5">
                <div class="col-sm-4 offset-3">
                    <%
                        Clientes cliente = (Clientes) request.getAttribute("cliente");
                        String acao = (String) request.getAttribute("acao");
                        switch (acao) {
                            case "Incluir":
                                out.println("<h1>Incluir Cliente</h1>");
                                break;
                            case "Alterar":
                                out.println("<h1>Alterar Cliente</h1>");
                                break;
                            case "Excluir":
                                out.println("<h1>Excluir Cliente</h1>");
                                break;
                        }

                        String msgError = (String) request.getAttribute("msgError");
                        if ((msgError != null) && (!msgError.isEmpty())) {%>
                    <div class="alert alert-danger" role="alert">
                        <%= msgError%>
                    </div>
                    <% }%>

                    <form action="/TrabalhoFinal-ErikEGabriel/vendedor/ClienteController" method="POST">
                        <input type="hidden" name="id" value="<%=cliente.getId()%>" class="form-control">
                        <div class="mb-3">
                            <label for="nome" class="form-label" >Nome</label>
                            <input type="text" name="nome" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=cliente.getNome()%>" class="form-control">
                            <label for="cpf" class="form-label" >CPF</label>
                            <input type="text" name="cpf" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=cliente.getCpf()%>" class="form-control">
                            <label for="endereco" class="form-label" >Endereço</label>
                            <input type="text" name="endereco" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=cliente.getEndereco()%>" class="form-control">
                            <label for="bairro" class="form-label" >Bairro</label>
                            <input type="text" name="bairro" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=cliente.getBairro()%>" class="form-control">
                            <label for="cidade" class="form-label" >Cidade</label>
                            <input type="text" name="cidade" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=cliente.getCidade()%>" class="form-control">
                            <label for="uf" class="form-label" >UF</label>
                            <input type="text" name="uf" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=cliente.getUf()%>" class="form-control">
                            <label for="cep" class="form-label" >CEP</label>
                            <input type="text" name="cep" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=cliente.getCep()%>" class="form-control">
                            <label for="telefone" class="form-label" >Telefone</label>
                            <input type="text" name="telefone" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=cliente.getTelefone()%>" class="form-control">
                            <label for="email" class="form-label" >Email</label>
                            <input type="text" name="email" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=cliente.getEmail()%>" class="form-control">
                        </div>
                        <div>
                            <input type="submit" name="btEnviar" value="<%=acao%>" class="btn btn-primary">
                            <a href="/TrabalhoFinal-ErikEGabriel/vendedor/ClienteController?acao=Listar" class="btn btn-danger">Retornar</a>
                        </div>
                    </form>

                </div>
            </div>
        </div>
        <script src="http://localhost:8080/TrabalhoFinal-ErikEGabriel/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>

</html>