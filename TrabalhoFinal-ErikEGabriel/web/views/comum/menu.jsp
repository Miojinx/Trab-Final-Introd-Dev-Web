<%@page contentType="text/html" pageEncoding="UTF-8" import="Entidade.Funcionario" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="/TrabalhoFinal-ErikEGabriel/home">Home</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav">
                <%
                    // testar se está logado 
                    HttpSession sessao = request.getSession(false); //0 = admin, 1 = vendedor, 2 = comprador
                    if (sessao != null) {
                        Funcionario funcionarioLogado = (Funcionario) session.getAttribute("funcionario");
                        if (funcionarioLogado != null && funcionarioLogado.getPapel() == '0') { %>      
                            <a class="nav-link" href="/TrabalhoFinal-ErikEGabriel/admin/dashboard">Dashboard</a>
                            <a class="nav-link" href="/TrabalhoFinal-ErikEGabriel/admin/FuncionarioController?acao=Listar">Funcionários</a>
                            <a class="nav-link" href="/TrabalhoFinal-ErikEGabriel/admin/logOut">Logout</a>
                <% } else if (funcionarioLogado != null && funcionarioLogado.getPapel() == '1') { %>
                            <a class="nav-link" href="/TrabalhoFinal-ErikEGabriel/vendedor/ClienteController?acao=Listar">Clientes</a>
                            <a class="nav-link" href="/TrabalhoFinal-ErikEGabriel/admin/logOut">Logout</a>
                <% } else if (funcionarioLogado != null && funcionarioLogado.getPapel() == '2') { %>
                            <a class="nav-link" href="/TrabalhoFinal-ErikEGabriel/comprador/CategoriaController?acao=Listar">Categorias</a>
                            <a class="nav-link" href="/TrabalhoFinal-ErikEGabriel/comprador/ProdutoController?acao=Listar">Produtos</a>
                            <a class="nav-link" href="/TrabalhoFinal-ErikEGabriel/admin/logOut">Logout</a>
                <%  } else { %>
                            <a class="nav-link" href="/TrabalhoFinal-ErikEGabriel/MostrarProdutos">Produtos</a>
                            <a class="nav-link" href="/TrabalhoFinal-ErikEGabriel/AutenticaController?acao=Login">Login</a>
                <%    }
                    }%>
            </div>
        </div>
    </div>
</nav>
