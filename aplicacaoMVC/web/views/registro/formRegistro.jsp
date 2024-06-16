<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Exemplo MVC</title>
        <link href="views/bootstrap/bootstrap.min.css"  rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <jsp:include page="../comum/menu.jsp" />
            <div class="col-sm-6 offset-3 mt-5">

                <h3>Registro</h3>

                <%
                    String msgError = (String) request.getAttribute("msgError");
                    if ((msgError != null) && (!msgError.isEmpty())) {%>
                <div class="alert alert-danger" role="alert">
                    <%= msgError%>
                </div>
                <% }%>
                <form action="RegistrarController" method="POST">
                    <div class="mb-3">
                        <label for="cpf" class="form-label">Nome</label>
                        <input type="text" name="nome" class="form-control" placeholder="Seu nome">
                    </div>
                    <div class="mb-3">
                        <label for="cpf" class="form-label">Endereço</label>
                        <input type="text" name="endereco" class="form-control" placeholder="Seu endereço">
                    </div>
                    <div class="mb-3">
                        <label for="cpf" class="form-label">CPF</label>
                        <input type="text" name="cpf" class="form-control" placeholder="999.999.999-99">
                    </div>
                    <div class="mb-3">
                        <label for="senha" class="form-label">Senha</label>
                        <input type="password" name="senha" value="111" class="form-control">
                    </div>
                    <div class="mb-3">
                        <label for="senha" class="form-label">Redigite a senha</label>
                        <input type="password" name="senha2" value="111" class="form-control">
                    </div>
                    <div class="row">
                        <div class="col-sm-2">
                            <input type="submit" value="Enviar" class="btn btn-primary">  
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <script src="views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>

