package controller.comprador;

import Entidade.Compras;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ComprasDAO;

@WebServlet(name = "ComprasController", urlPatterns = {"/comprador/ComprasController"})
public class ComprasController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // get parametro ação indicando o que fazer
        String acao = (String) request.getParameter("acao");
        Compras compra = new Compras();
        ComprasDAO compraDAO = new ComprasDAO();
        RequestDispatcher rd;
        switch (acao) {
            case "Listar":
                ArrayList<Compras> listaCompras = compraDAO.getAll();
                request.setAttribute("listaCompras", listaCompras);

                rd = request.getRequestDispatcher("/views/compra/listaCompras.jsp");
                rd.forward(request, response);

                break;
            case "Alterar":
            case "Excluir":

                int id = Integer.parseInt(request.getParameter("id"));
                compra = compraDAO.get(id);

                request.setAttribute("compra", compra);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/compra/formCompras.jsp");
                rd.forward(request, response);
                break;
            case "Incluir":
                request.setAttribute("compra", compra);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/compra/formCompras.jsp");
                rd.forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        int quantidade_compra = Integer.parseInt(request.getParameter("quantidade_compra"));
        String data_compra = request.getParameter("data_compra");
        float valor_compra = Float.parseFloat(request.getParameter("valor_compra"));
        int id_fornecedor = Integer.parseInt(request.getParameter("id_fornecedor"));
        int id_produto = Integer.parseInt(request.getParameter("id_produto"));
        int id_funcionario = Integer.parseInt(request.getParameter("id_funcionario"));
                
        String btEnviar = request.getParameter("btEnviar");

        RequestDispatcher rd;

        if (data_compra == null) {
            Compras compra = new Compras();
            switch (btEnviar) {
                case "Alterar":
                case "Excluir":
                    try {
                        ComprasDAO compraDAO = new ComprasDAO();
                        compra = compraDAO.get(id);

                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                        throw new RuntimeException("Falha em uma query para cadastro de Compras");
                    }
                    break;
            }

            request.setAttribute("compra", compra);
            request.setAttribute("acao", btEnviar);

            request.setAttribute("msgError", "É necessário preencher todos os campos");

            rd = request.getRequestDispatcher("/views/compra/formCompras.jsp");
            rd.forward(request, response);

        } else {

            Compras compra = new Compras(id, quantidade_compra, data_compra, valor_compra, id_fornecedor, id_funcionario, id_produto);
            ComprasDAO compraDAO = new ComprasDAO();

            try {
                switch (btEnviar) {
                    case "Incluir":
                        compraDAO.insert(compra);
                        request.setAttribute("msgOperacaoRealizada", "Inclusão realizada com sucesso");
                        break;
                    case "Alterar":
                        compraDAO.update(compra);
                        request.setAttribute("msgOperacaoRealizada", "Alteração realizada com sucesso");
                        break;
                    case "Excluir":
                        compraDAO.delete(id);
                        request.setAttribute("msgOperacaoRealizada", "Exclusão realizada com sucesso");
                        break;
                }

                request.setAttribute("link", "/TrabalhoFinal-ErikEGabriel/comprador/ComprasController?acao=Listar");
                rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
                rd.forward(request, response);

            } catch (IOException | ServletException ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha em uma query para cadastro de Compras");
            }
        }
    }

}
