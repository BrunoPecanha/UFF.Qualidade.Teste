package controller;

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
import model.Usuario;


@WebServlet(name = "Usuarios", urlPatterns = {"/UsuarioController"})
public class UsuarioController extends HttpServlet {        
    private static String LIST_USUARIO = "/listusuario.jsp";
    private UsuarioDao dao;

    public UsuarioController() {
        super();
        dao = new UsuarioDao();
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
            request.setAttribute("usuarios", dao.Recuperar(1, 15));
            RequestDispatcher view = request.getRequestDispatcher(LIST_USUARIO);       
            view.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {        
        String acao = request.getParameter("action");           
        
        if (acao.equalsIgnoreCase("salvar")){            
            try {
                String usuarioId = request.getParameter("id_usuario");

                if(usuarioId == null || usuarioId.isEmpty()) {
                    dao.Salvar(new Usuario(request.getParameter("nome"), request.getParameter("CPF"), request.getParameter("senha"), request.getParameter("suspenso")));
                }
                else {
                    dao.Salvar(new Usuario(parseInt(usuarioId), request.getParameter("nome"), request.getParameter("CPF"), request.getParameter("senha"), request.getParameter("suspenso")));
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } 
        }
        else if (acao.equalsIgnoreCase("delete")){
            int id = Integer.parseInt(request.getParameter("id_exclusao"));
           dao.Deletar(id);                        
       }
        
        RequestDispatcher view = request.getRequestDispatcher(LIST_USUARIO);
        request.setAttribute("usuarios", dao.Recuperar(1, 15));
        view.forward(request, response);
    }
}
