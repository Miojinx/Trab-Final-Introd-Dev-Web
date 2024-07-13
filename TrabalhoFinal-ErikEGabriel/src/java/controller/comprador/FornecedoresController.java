package controller.comprador;

import Entidade.Fornecedores;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.FornecedoresDAO;

@WebServlet(name = "FornecedoresController", urlPatterns = {"/comprador/FornecedoresController"})
public class FornecedoresController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String acao = (String) request.getParameter("acao");
        Fornecedores fornecedor = new Fornecedores();
        FornecedoresDAO fornecedorDAO = new FornecedoresDAO();
        RequestDispatcher rd;
        switch (acao) {
            case "Listar":
                ArrayList<Fornecedores> listaFornecedores = fornecedorDAO.getAll();
                request.setAttribute("listaFornecedores", listaFornecedores);
                
                rd = request.getRequestDispatcher("/views/fornecedor/listaFornecedores.jsp");
                rd.forward(request, response);
                
                break;
            case "Alterar":
            case "Excluir":

                // get parametro ação indicando sobre qual categoria será a ação
                int id = Integer.parseInt(request.getParameter("id"));
                fornecedor = fornecedorDAO.get(id);
                
                request.setAttribute("fornecedor", fornecedor);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);
                
                rd = request.getRequestDispatcher("/views/fornecedor/formFornecedores.jsp");
                rd.forward(request, response);
                break;
            case "Incluir":
                request.setAttribute("fornecedor", fornecedor);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);
                
                rd = request.getRequestDispatcher("/views/fornecedor/formFornecedores.jsp");
                rd.forward(request, response);
        }
        
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        String razao_social = request.getParameter("razao_social");
        String cnpj = request.getParameter("cnpj");
        String endereco = request.getParameter("endereco");
        String bairro = request.getParameter("bairro");
        String cidade = request.getParameter("cidade");
        String uf = request.getParameter("uf");
        String cep = request.getParameter("cep");
        String telefone = request.getParameter("telefone");
        String email = request.getParameter("email");
        String btEnviar = request.getParameter("btEnviar");
        
        RequestDispatcher rd;
        
        if (razao_social.isEmpty() || cnpj.isEmpty() || endereco.isEmpty() || bairro.isEmpty() || cidade.isEmpty() || uf.isEmpty() || cep.isEmpty() || telefone.isEmpty() || email.isEmpty()) {
            Fornecedores fornecedor = new Fornecedores();
            switch (btEnviar) {
                case "Alterar":
                case "Excluir":
                    try {
                        FornecedoresDAO fornecedorDAO = new FornecedoresDAO();
                        fornecedor = fornecedorDAO.get(id);
                        
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                        throw new RuntimeException("Falha em uma query para cadastro de fornecedor");
                    }
                    break;
            }
            
            request.setAttribute("fornecedor", fornecedor);
            request.setAttribute("acao", btEnviar);
            
            request.setAttribute("msgError", "É necessário preencher todos os campos");
            
            rd = request.getRequestDispatcher("/views/fornecedor/formFornecedores.jsp");
            rd.forward(request, response);
            
        } else {
            
            Fornecedores fornecedor = new Fornecedores(id, razao_social, cnpj, endereco, bairro, cidade, uf, cep, telefone, email);
            FornecedoresDAO fornecedorDAO = new FornecedoresDAO();
            
            try {
                switch (btEnviar) {
                    case "Incluir":
                        fornecedorDAO.insert(fornecedor);
                        request.setAttribute("msgOperacaoRealizada", "Inclusão realizada com sucesso");
                        break;
                    case "Alterar":
                        fornecedorDAO.update(fornecedor);
                        request.setAttribute("msgOperacaoRealizada", "Alteração realizada com sucesso");
                        break;
                    case "Excluir":
                        fornecedorDAO.delete(id);
                        request.setAttribute("msgOperacaoRealizada", "Exclusão realizada com sucesso");
                        break;
                }
                
                request.setAttribute("link", "/TrabalhoFinal-ErikEGabriel/comprador/FornecedoresController?acao=Listar");
                rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
                rd.forward(request, response);
                
            } catch (IOException | ServletException ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha em uma query para cadastro de Fornecedor");
            }
        }
    }
    
}
