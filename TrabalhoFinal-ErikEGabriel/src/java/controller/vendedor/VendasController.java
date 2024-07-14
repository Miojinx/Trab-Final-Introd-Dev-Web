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
                request.setAttribute("listaVendas", listaVendas);

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
        String data_venda = request.getParameter("data_venda");
        float valor_venda = Float.parseFloat(request.getParameter("valor_venda"));
        int id_cliente  = Integer.parseInt(request.getParameter("id_cliente"));
        int id_funcionario  = Integer.parseInt(request.getParameter("id_funcionario"));
        int id_produto  = Integer.parseInt(request.getParameter("id_produto"));
        String btEnviar = request.getParameter("btEnviar");

        RequestDispatcher rd;

        if (data_venda == null || quantidade_venda < 0 || valor_venda < 0 || id_cliente < 0 || id_funcionario < 0 || id_produto < 0) {
            Vendas venda = new Vendas();
            switch (btEnviar) {
                case "Alterar":
                case "Excluir":
                    try {
                        VendasDAO vendaDAO = new VendasDAO();
                        venda = vendaDAO.get(id);

                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                        throw new RuntimeException("Falha em uma query para cadastro de vendas");
                    }
                    break;
            }

            request.setAttribute("venda", venda);
            request.setAttribute("acao", btEnviar);

            request.setAttribute("msgError", "É necessário preencher todos os campos");

            rd = request.getRequestDispatcher("/views/venda/formVendas.jsp");
            rd.forward(request, response);

        } else {

            Vendas venda = new Vendas(id, quantidade_venda, data_venda, valor_venda, id_cliente, id_funcionario, id_produto);
            VendasDAO vendaDAO = new VendasDAO();

            try {
                switch (btEnviar) {
                    case "Incluir":
                        vendaDAO.insert(venda);
                        request.setAttribute("msgOperacaoRealizada", "Inclusão realizada com sucesso");
                        break;
                    case "Alterar":
                        vendaDAO.update(venda);
                        request.setAttribute("msgOperacaoRealizada", "Alteração realizada com sucesso");
                        break;
                    case "Excluir":
                        vendaDAO.delete(id);
                        request.setAttribute("msgOperacaoRealizada", "Exclusão realizada com sucesso");
                        break;
                }

                request.setAttribute("link", "/TrabalhoFinal-ErikEGabriel/vendedor/VendasController?acao=Listar");
                rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
                rd.forward(request, response);

            } catch (IOException | ServletException ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha em uma query para cadastro de vendas");
            }
        }
    }

}
