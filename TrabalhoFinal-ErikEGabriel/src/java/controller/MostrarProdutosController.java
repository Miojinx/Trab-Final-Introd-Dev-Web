package controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ProdutosDAO;
import Entidade.Produtos;


@WebServlet(name = "MostrarProdutos", urlPatterns = {"/MostrarProdutos"})
public class MostrarProdutosController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                   ProdutosDAO produtosDAO = new ProdutosDAO();
            try {
                ArrayList<Produtos> listaProdutos = produtosDAO.getAll();
                request.setAttribute("listaProdutos", listaProdutos);
                RequestDispatcher rd = request.getRequestDispatcher("/views/public/mostrarProdutos.jsp");
                rd.forward(request, response);
                
                
            } catch (IOException | ServletException ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha na query listar usuarios (mostrarProdutos) ");
            }
    }

}
