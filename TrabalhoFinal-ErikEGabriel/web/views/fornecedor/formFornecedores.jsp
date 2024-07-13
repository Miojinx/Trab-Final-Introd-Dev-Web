<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Entidade.Fornecedores"%>

<!DOCTYPE html>
<html lang="pt-br">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Fornecedor</title>
        <link href="http://localhost:8080/TrabalhoFinal-ErikEGabriel/views/bootstrap/bootstrap.min.css"  rel="stylesheet">
    </head>

    <body>

        <div class="container">
            <jsp:include page="../comum/menu.jsp" />
            <div class="row mt-5">
                <div class="col-sm-4 offset-3">
                    <%
                        Fornecedores fornecedor = (Fornecedores) request.getAttribute("fornecedor");
                        String acao = (String) request.getAttribute("acao");
                        switch (acao) {
                            case "Incluir":
                                out.println("<h1>Incluir Fornecedor</h1>");
                                break;
                            case "Alterar":
                                out.println("<h1>Alterar Fornecedor</h1>");
                                break;
                            case "Excluir":
                                out.println("<h1>Excluir Fornecedor</h1>");
                                break;
                        }

                        String msgError = (String) request.getAttribute("msgError");
                        if ((msgError != null) && (!msgError.isEmpty())) {%>
                    <div class="alert alert-danger" role="alert">
                        <%= msgError%>
                    </div>
                    <% }%>

                    <form action="/TrabalhoFinal-ErikEGabriel/comprador/FornecedoresController" method="POST">
                        <input type="hidden" name="id" value="<%=fornecedor.getId()%>" class="form-control">
                        <div class="mb-3">
                            <label for="razao_social" class="form-label" >Razão Social</label>
                            <input type="text" name="razao_social" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=fornecedor.getRazao_social()%>" class="form-control">
                            
                            <label for="cnpj" class="form-label" >CNPJ</label>
                            <input type="text" name="cnpj" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=fornecedor.getCnpj()%>" class="form-control">

                            <label for="endereco" class="form-label" >Endereco</label>
                            <input type="text" name="endereco" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=fornecedor.getEndereco()%>" class="form-control">

                            <label for="bairro" class="form-label" >Bairro</label>
                            <input type="text" name="bairro" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=fornecedor.getBairro()%>" class="form-control">
                            
                            <label for="cidade" class="form-label" >Cidade</label>
                            <input type="text" name="cidade" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=fornecedor.getCidade()%>" class="form-control">
                            
                            <label for="uf" class="form-label" >UF</label>
                            <input type="text" name="uf" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=fornecedor.getUf()%>" class="form-control">
                            
                            <label for="cep" class="form-label" >CEP</label>
                            <input type="text" name="cep" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=fornecedor.getCep()%>" class="form-control">
                            
                            <label for="telefone" class="form-label" >Telefone</label>
                            <input type="text" name="telefone" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=fornecedor.getTelefone()%>" class="form-control">
                            
                            <label for="email" class="form-label" >Email</label>
                            <input type="text" name="email" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=fornecedor.getEmail()%>" class="form-control">
                        </div>
                        <div>
                            <input type="submit" name="btEnviar" value="<%=acao%>" class="btn btn-primary">
                            <a href="/TrabalhoFinal-ErikEGabriel/comprador/FornecedoresController?acao=Listar" class="btn btn-danger">Retornar</a>
                        </div>
                    </form>

                </div>
            </div>
        </div>
        <script src="http://localhost:8080/TrabalhoFinal-ErikEGabriel/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>

</html>