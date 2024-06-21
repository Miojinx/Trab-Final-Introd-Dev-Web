package controller.vendedor;

import Entidade.Clientes;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ClienteDAO;

@WebServlet(name = "CategoriaController", urlPatterns = {"/vendedor/ClienteController"})
public class ClienteController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // get parametro ação indicando o que fazer
        String acao = (String) request.getParameter("acao");
        Clientes cliente = new Clientes();
        ClienteDAO clienteDAO = new ClienteDAO();
        RequestDispatcher rd;
        switch (acao) {
            case "Listar":
                ArrayList<Clientes> listaCliente = clienteDAO.getAll();
                request.setAttribute("listaCliente", listaCliente);

                rd = request.getRequestDispatcher("/views/cliente/listaClientes.jsp");
                rd.forward(request, response);

                break;
            case "Alterar":
            case "Excluir":

                // get parametro ação indicando sobre qual cliente será a ação
                int id = Integer.parseInt(request.getParameter("id"));
                cliente = clienteDAO.get(id);

                request.setAttribute("cliente", cliente);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/cliente/formClientes.jsp");
                rd.forward(request, response);
                break;
            case "Incluir":
                request.setAttribute("cliente", cliente);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/cliente/formClientes.jsp");
                rd.forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        String endereco = request.getParameter("endereco");
        String bairro = request.getParameter("bairro");
        String cidade = request.getParameter("cidade");
        String uf = request.getParameter("uf");
        String cep = request.getParameter("cep");
        String telefone = request.getParameter("telefone");
        String email = request.getParameter("email");
        
        String btEnviar = request.getParameter("btEnviar");

        RequestDispatcher rd;

        if (nome.isEmpty() || cpf.isEmpty() || endereco.isEmpty() || bairro.isEmpty() || cidade.isEmpty() || uf.isEmpty() || cep.isEmpty() || telefone.isEmpty() || email.isEmpty()) {
            Clientes cliente = new Clientes();
            switch (btEnviar) {
                case "Alterar":
                case "Excluir":
                    try {
                        ClienteDAO clienteDAO = new ClienteDAO();
                        cliente = clienteDAO.get(id);

                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                        throw new RuntimeException("Falha em uma query para cadastro de usuario");
                    }
                    break;
            }

            request.setAttribute("cliente", cliente);
            request.setAttribute("acao", btEnviar);

            request.setAttribute("msgError", "É necessário preencher todos os campos");

            rd = request.getRequestDispatcher("/views/cliente/formClientes.jsp");
            rd.forward(request, response);

        } else {

            Clientes cliente = new Clientes(id, nome, cpf, endereco, bairro, cidade, uf, cep, telefone, email);
            ClienteDAO clienteDAO = new ClienteDAO();

            try {
                switch (btEnviar) {
                    case "Incluir":
                        clienteDAO.insert(cliente);
                        request.setAttribute("msgOperacaoRealizada", "Inclusão realizada com sucesso");
                        break;
                    case "Alterar":
                        clienteDAO.update(cliente);
                        request.setAttribute("msgOperacaoRealizada", "Alteração realizada com sucesso");
                        break;
                    case "Excluir":
                        clienteDAO.delete(id);
                        request.setAttribute("msgOperacaoRealizada", "Exclusão realizada com sucesso");
                        break;
                }

                request.setAttribute("link", "/aplicacaoMVC/vendedor/ClienteController?acao=Listar");
                rd = request.getRequestDispatcher("/views/comum/showMessage.jsp"); //lembrar de alterar mais tarde
                rd.forward(request, response);

            } catch (IOException | ServletException ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha em uma query para cadastro de usuario");
            }
        }
    }

}
