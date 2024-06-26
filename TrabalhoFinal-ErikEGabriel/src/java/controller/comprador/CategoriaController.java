package controller.comprador;

import Entidade.Categorias;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CategoriaDAO;

@WebServlet(name = "CategoriaController", urlPatterns = {"/comprador/CategoriaController"})
public class CategoriaController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // get parametro ação indicando o que fazer
        String acao = (String) request.getParameter("acao");
        Categorias categoria = new Categorias();
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        RequestDispatcher rd;
        switch (acao) {
            case "Listar":
                ArrayList<Categorias> listaCategorias = categoriaDAO.getAll();
                request.setAttribute("listaCategorias", listaCategorias);

                rd = request.getRequestDispatcher("/views/categoria/listaCategorias.jsp");
                rd.forward(request, response);

                break;
            case "Alterar":
            case "Excluir":

                // get parametro ação indicando sobre qual categoria será a ação
                int id = Integer.parseInt(request.getParameter("id"));
                categoria = categoriaDAO.get(id);

                request.setAttribute("categoria", categoria);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/categoria/formCategorias.jsp");
                rd.forward(request, response);
                break;
            case "Incluir":
                request.setAttribute("categoria", categoria);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/categoria/formCategorias.jsp");
                rd.forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String nomeCategoria = request.getParameter("nomeCategoria");
        String btEnviar = request.getParameter("btEnviar");

        RequestDispatcher rd;

        if (nomeCategoria.isEmpty()) {
            Categorias categoria = new Categorias();
            switch (btEnviar) {
                case "Alterar":
                case "Excluir":
                    try {
                    CategoriaDAO categoriaDAO = new CategoriaDAO();
                    categoria = categoriaDAO.get(id);

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha em uma query para cadastro de usuario");
                }
                break;
            }

            request.setAttribute("categoria", categoria);
            request.setAttribute("acao", btEnviar);

            request.setAttribute("msgError", "É necessário preencher todos os campos");

            rd = request.getRequestDispatcher("/views/categoria/formCategorias.jsp");
            rd.forward(request, response);

        } else {
            
             Categorias categoria = new Categorias(id,nomeCategoria);
             CategoriaDAO categoriaDAO = new CategoriaDAO();

            try {
                switch (btEnviar) {
                    case "Incluir":
                        categoriaDAO.insert(categoria);
                        request.setAttribute("msgOperacaoRealizada", "Inclusão realizada com sucesso");
                        break;
                    case "Alterar":
                        categoriaDAO.update(categoria);
                        request.setAttribute("msgOperacaoRealizada", "Alteração realizada com sucesso");
                        break;
                    case "Excluir":
                        categoriaDAO.delete(id);
                        request.setAttribute("msgOperacaoRealizada", "Exclusão realizada com sucesso");
                        break;
                }

                request.setAttribute("link", "/TrabalhoFinal-ErikEGabriel/comprador/CategoriaController?acao=Listar");
                rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
                rd.forward(request, response);

            } catch (IOException | ServletException ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha em uma query para cadastro de usuario");
            }
        }
    }

}
