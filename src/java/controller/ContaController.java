package controller;

import dao.ContaDao;
import dao.UsuarioDao;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Conta;


@WebServlet(name = "Contas", urlPatterns = {"/ContaController"})
public class ContaController extends HttpServlet {        
    private static String LIST_CONTAS = "/listconta.jsp";
    private ContaDao dao;

    public ContaController() {
        super();
        dao = new ContaDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {              
        HttpSession session = request.getSession(true);   
        String sessaoValida = request.getParameter("session");        
        String deslogou = request.getParameter("deslogar");
        
        if ("sim".equals(deslogou)){
            session.invalidate();
            request.getRequestDispatcher("login.jsp").forward(request, response);     
            
        } else if ("".equals(sessaoValida)){
            session.invalidate();
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
        else {
            int usuario = parseInt(request.getParameter("id"));
            request.setAttribute("contas", dao.RecuperarPorUsuario(usuario));
            RequestDispatcher view = request.getRequestDispatcher(LIST_CONTAS);       
            view.forward(request, response);
        }
    }   

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {        
        String acao = request.getParameter("action");           
        int usuario = parseInt(request.getParameter("id"));
        if (acao.equalsIgnoreCase("salvar")){            
            try {
                String contaId = request.getParameter("id_conta");
                String banco = request.getParameter("bancos");   

                 if(contaId == null || contaId.isEmpty()) {
                    dao.Salvar(new Conta(usuario, request.getParameter("descricao"), request.getParameter("bancos"), request.getParameter("agencia"), request.getParameter("cc")));
                }
                else {
                    dao.Salvar(new Conta(parseInt(contaId), usuario, request.getParameter("descricao"), banco, request.getParameter("agencia"), request.getParameter("cc"), ""));
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } 
        }
        else if (acao.equalsIgnoreCase("delete")){
            int id = Integer.parseInt(request.getParameter("id_exclusao"));
            dao.Deletar(id);                         
       }
        
        RequestDispatcher view = request.getRequestDispatcher(LIST_CONTAS);
        request.setAttribute("contas", dao.RecuperarPorUsuario(usuario));
        view.forward(request, response);
    }
}
