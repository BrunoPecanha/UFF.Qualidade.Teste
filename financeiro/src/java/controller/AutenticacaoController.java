package controller;

import dao.UsuarioDao;
import dao.AdministradorDao;
import dto.ResumoDto;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Administrador;
import model.Usuario;
import servicos.LancamentoServico;


@WebServlet(name = "Autenticacao", urlPatterns = {"/AutenticacaoController"})
public class AutenticacaoController extends HttpServlet {        
   
    private UsuarioDao daoUsuario;
    private AdministradorDao daoAdmin;
    private LancamentoServico  servicoLancamento;

    public AutenticacaoController() {
        super();
        daoUsuario = new UsuarioDao();
        daoAdmin = new AdministradorDao();
        servicoLancamento = new LancamentoServico();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {        
        HttpSession session = request.getSession(true);    
        String deslogou = request.getParameter("deslogar");
        
        if ("sim".equals(deslogou)){
            session.invalidate();
            request.getRequestDispatcher("login.jsp").forward(request, response);                    
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {   
            String cpf = request.getParameter("cpf");           
            String senha = request.getParameter("senha");   
            
            if (cpf.isEmpty() || senha.isEmpty()) {
                return;
            }
            
            Usuario usuario;
            Administrador admin = null;
            HttpSession session = request.getSession(true);    

            usuario = daoUsuario.Recuperar(cpf.replaceAll("\\D", ""), senha);
            if (usuario == null)         
                admin = daoAdmin.Recuperar(cpf.replaceAll("\\D", ""), senha);        

            if ((usuario != null && usuario.getSuspenso().equals("N")) || admin != null) {
              if (usuario != null) {
                 session.setAttribute("usuarioLogado",usuario); 
                 session.setAttribute("cpf", usuario.getCPF());
                 session.setAttribute("idUsuarioLogado", usuario.getId());
                 session.setAttribute("adm", "display: none");
                 session.setAttribute("usu", "display: normal");

                ResumoDto resumo = servicoLancamento.CarregarResumoLancamentos(usuario.getId());
                session.setAttribute("debitos", resumo.debitos); 
                session.setAttribute("creditos", resumo.creditos);
                session.setAttribute("total", resumo.creditos-resumo.debitos);    

              }
              else {
                session.setAttribute("usuarioLogado", admin);                     
                session.setAttribute("cpf", admin.getCPF());
                session.setAttribute("idUsuarioLogado", admin.getId());
                session.setAttribute("adm", "display: normal");
                session.setAttribute("usu", "display: none");
              }

              request.getRequestDispatcher("home.jsp").forward(request, response);
            }          
            else {                      
                request.getRequestDispatcher("login.jsp").forward(request, response);            
            }          
     }    
}