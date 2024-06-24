import Entidade.Funcionario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/vendedor/FuncionarioController")
public class FuncionarioController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acao = request.getParameter("acao");

        if ("Criar".equals(acao)) {
            String nome = request.getParameter("nome");
            String cpf = request.getParameter("cpf");
            String senha = request.getParameter("senha");
            char papel = request.getParameter("papel").charAt(0);

            Funcionario funcionario = new Funcionario(0, nome, cpf, senha, papel);
            
            // adicionar código para salvar o funcionário no banco de dados

            // Redirecionar ou encaminhar para uma página de sucesso
            response.sendRedirect("listaFuncionarios.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Implementar lógica para métodos GET se necessário
    }
}