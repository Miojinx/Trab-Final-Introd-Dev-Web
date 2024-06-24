package controller.comprador;
import Entidade.Produtos;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ProdutosDAO;
/**
 *
 * @author erikt
 */
@WebServlet(name = "ProdutoController", urlPatterns = {"/comprador/ProdutoController"})
public class ProdutoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // get parametro ação indicando o que fazer
        String acao = (String) request.getParameter("acao");
        Produtos produto = new Produtos();
        ProdutosDAO produtoDAO = new ProdutosDAO();
        RequestDispatcher rd;
        switch (acao) {
            case "Listar":
                ArrayList<Produtos> listaProdutos = produtoDAO.getAll();
                request.setAttribute("listaProdutos", listaProdutos);

                rd = request.getRequestDispatcher("/views/admin/produtos/listaProdutos.jsp");
                rd.forward(request, response);

                break;
            case "Alterar":
            case "Excluir":

                // get parametro ação indicando sobre qual categoria será a ação
                int id = Integer.parseInt(request.getParameter("id"));
                produto = produtoDAO.get(id);

                request.setAttribute("produto", produto);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/admin/produtos/formProduto.jsp");
                rd.forward(request, response);
                break;
            case "Incluir":
                request.setAttribute("produto", produto);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/admin/produtos/formProduto.jsp");
                rd.forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String nome_produto = request.getParameter("nome_produto");
        String descricao = request.getParameter("descricao");
        float preco_compra = Float.parseFloat(request.getParameter("preco_compra"));
        float preco_venda = Float.parseFloat(request.getParameter("preco_venda"));
        int quantidade_disponivel = Integer.parseInt(request.getParameter("quantidade_disponivel"));
        String liberadoVendaString = request.getParameter("liberado_venda");
        char liberado_venda = liberadoVendaString.charAt(0);
        int id_categoria = Integer.parseInt(request.getParameter("id_categoria"));
        String btEnviar = request.getParameter("btEnviar");

        RequestDispatcher rd;
        
        
        if (nome_produto.isEmpty() || descricao.isEmpty()) {
            Produtos produto = new Produtos();
            switch (btEnviar) {
                case "Alterar":
                case "Excluir":
                    try {
                    ProdutosDAO produtoDAO= new ProdutosDAO();
                    produto = produtoDAO.get(id);

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha em uma query para cadastro de usuario");
                }
                break;
            }

            request.setAttribute("produto", produto);
            request.setAttribute("acao", btEnviar);

            request.setAttribute("msgError", "É necessário preencher todos os campos");

            rd = request.getRequestDispatcher("/views/admin/produto/formCategoria.jsp");
            rd.forward(request, response);

        } else {
            
             Produtos produto = new Produtos(id,nome_produto,descricao,preco_compra,preco_venda,quantidade_disponivel,liberado_venda,id_categoria);
             ProdutosDAO produtosDAO = new ProdutosDAO();

            try {
                switch (btEnviar) {
                    case "Incluir":
                        produtosDAO.insert(produto);
                        request.setAttribute("msgOperacaoRealizada", "Inclusão realizada com sucesso");
                        break;
                    case "Alterar":
                        produtosDAO.update(produto);
                        request.setAttribute("msgOperacaoRealizada", "Alteração realizada com sucesso");
                        break;
                    case "Excluir":
                        produtosDAO.delete(id);
                        request.setAttribute("msgOperacaoRealizada", "Exclusão realizada com sucesso");
                        break;
                }

                request.setAttribute("link", "/TrabalhoFinal-ErikEGabriel/admin/ProdutoController?acao=Listar");
                rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
                rd.forward(request, response);

            } catch (IOException | ServletException ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha em uma query para cadastro de usuario");
            }
        }
    }

}

