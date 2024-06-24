package controller.admin;
import Entidade.Funcionario;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.FuncionarioDAO;

@WebServlet(name = "FuncionarioController", urlPatterns = {"/admin/FuncionarioController"})
public class FuncionarioController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // get parametro ação indicando o que fazer
        String acao = (String) request.getParameter("acao");
        Funcionario funcionario = new Funcionario();
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        RequestDispatcher rd;
        switch (acao) {
            case "Listar":
                ArrayList<Funcionario> listaFuncionarios = funcionarioDAO.getAll();
                request.setAttribute("listaFuncionarios", listaFuncionarios);

                rd = request.getRequestDispatcher("/views/funcionario/listaFuncionarios.jsp");
                rd.forward(request, response);

                break;
            case "Alterar":
            case "Excluir":

                // get parametro ação indicando sobre qual categoria será a ação
                int id = Integer.parseInt(request.getParameter("id"));
                funcionario = funcionarioDAO.get(id);

                request.setAttribute("funcionario", funcionario);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/funcionario/formFuncionarios.jsp");
                rd.forward(request, response);
                break;
            case "Incluir":
                request.setAttribute("funcionario", funcionario);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/funcionario/formFuncionarios.jsp");
                rd.forward(request, response);
        }

    }
    
@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        String senha = request.getParameter("senha");
        char papel = request.getParameter("papel").charAt(0);
        String btEnviar = request.getParameter("btEnviar");

        RequestDispatcher rd;

        if (nome.isEmpty() && cpf.isEmpty() && senha.isEmpty()) {
            Funcionario funcionario = new Funcionario();
            switch (btEnviar) {
                case "Alterar":
                case "Excluir":
                    try {
                    FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
                    funcionario = funcionarioDAO.get(id);

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha em uma query para cadastro de funcionário");
                }
                break;
            }

            request.setAttribute("funcionario", funcionario);
            request.setAttribute("acao", btEnviar);

            request.setAttribute("msgError", "É necessário preencher todos os campos");

            rd = request.getRequestDispatcher("/views/funcionario/formFuncionario.jsp");
            rd.forward(request, response);

        } else {
            
             Funcionario funcionario = new Funcionario(id, nome, cpf, senha, papel);
             FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

            try {
                switch (btEnviar) {
                    case "Incluir":
                        funcionarioDAO.insert(funcionario);
                        request.setAttribute("msgOperacaoRealizada", "Inclusão realizada com sucesso");
                        break;
                    case "Alterar":
                        funcionarioDAO.update(funcionario);
                        request.setAttribute("msgOperacaoRealizada", "Alteração realizada com sucesso");
                        break;
                    case "Excluir":
                        funcionarioDAO.delete(id);
                        request.setAttribute("msgOperacaoRealizada", "Exclusão realizada com sucesso");
                        break;
                }

                request.setAttribute("link", "/TrabalhoFinal-ErikEGabriel/admin/FuncionarioController?acao=Listar");
                rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
                rd.forward(request, response);

            } catch (IOException | ServletException ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha em uma query para cadastro de usuario");
            }
        }
    }

}
