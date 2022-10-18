package controller;

import dao.AdministradorDao;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Administrador;


@WebServlet(name = "Admins", urlPatterns = {"/AdminController"})
public class AdminController extends HttpServlet {        
    private static String LIST_ADMIN = "/listadmin.jsp";
    private AdministradorDao dao;

    public AdminController() {
        super();
        dao = new AdministradorDao();
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
            RequestDispatcher view = request.getRequestDispatcher(LIST_ADMIN);
            request.setAttribute("admins", dao.Recuperar(1, 15));
            view.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {        
        String acao = request.getParameter("action");           
        
        if (acao.equalsIgnoreCase("salvar")){            
            try {
                String adminId = request.getParameter("id_admin");

                if(adminId == null || adminId.isEmpty()) {
                    dao.Salvar(new Administrador(request.getParameter("nome"), request.getParameter("CPF"), request.getParameter("senha")));
                }
                else {
                    dao.Salvar(new Administrador(parseInt(adminId), request.getParameter("nome"), request.getParameter("CPF"), request.getParameter("senha")));                       
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } 
        }
        else if (acao.equalsIgnoreCase("delete")){
           int id = Integer.parseInt(request.getParameter("id_exclusao"));
           dao.Deletar(id);                        
       }
        
        RequestDispatcher view = request.getRequestDispatcher(LIST_ADMIN);
        request.setAttribute("admins", dao.Recuperar(1, 15));
        view.forward(request, response);
    }
}
