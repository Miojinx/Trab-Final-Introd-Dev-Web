package controller.vendedor;

import Entidade.Vendas;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.VendasDAO;
import java.util.Date;

@WebServlet(name = "VendasController", urlPatterns = {"/vendedor/VendasController"})
public class VendasController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // get parametro ação indicando o que fazer
        String acao = (String) request.getParameter("acao");
        Vendas venda = new Vendas();
        VendasDAO vendasDAO = new VendasDAO();
        RequestDispatcher rd;
        switch (acao) {
            case "Listar":
                ArrayList<Vendas> listaVendas = vendasDAO.getAll();
                request.setAttribute("listaClientes", listaVendas);

                rd = request.getRequestDispatcher("/views/venda/listaVendas.jsp");
                rd.forward(request, response);

                break;
            case "Alterar":
            case "Excluir":

                int id = Integer.parseInt(request.getParameter("id"));
                venda = vendasDAO.get(id);

                request.setAttribute("venda", venda);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/venda/formVendas.jsp");
                rd.forward(request, response);
                break;
            case "Incluir":
                request.setAttribute("venda", venda);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/venda/formVendas.jsp");
                rd.forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        int quantidade_venda = Integer.parseInt(request.getParameter("quantidade_venda"));
        Date data_venda = new request.getParameter("data_venda"); //TEM QUE TERMINAR MAS N TO SABENDO FAZER A CONVERSÃO!!!!!!!!!
        
        String btEnviar = request.getParameter("btEnviar");

        RequestDispatcher rd;

        if (nome.isEmpty() && cpf.isEmpty() && endereco.isEmpty() && bairro.isEmpty() && cidade.isEmpty() && uf.isEmpty() && cep.isEmpty() && telefone.isEmpty() && email.isEmpty()) {
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

                request.setAttribute("link", "/TrabalhoFinal-ErikEGabriel/vendedor/ClienteController?acao=Listar");
                rd = request.getRequestDispatcher("/views/comum/showMessage.jsp"); //lembrar de alterar mais tarde
                rd.forward(request, response);

            } catch (IOException | ServletException ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha em uma query para cadastro de usuario");
            }
        }
    }

}
