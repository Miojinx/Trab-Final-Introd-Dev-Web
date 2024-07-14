package controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.VendasDAO;
import Entidade.VendasTotais;
import Entidade.Vendas;

@WebServlet(name = "DashboardVendas", urlPatterns = {"/admin/DashboardVendas"})
public class DashboardVendasController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                   VendasDAO vendaDAO = new VendasDAO();
            try {
                ArrayList<VendasTotais> listaVendasTotais = vendaDAO.getVendasTotais();
                request.setAttribute("listaVendasTotais", listaVendasTotais);
                RequestDispatcher rd = request.getRequestDispatcher("/views/dashboard2/dashTotalVendas.jsp");
                rd.forward(request, response);
                
                
            } catch (IOException | ServletException ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha na query listar usuarios (mostrarProdutos) ");
            }
    }

}
