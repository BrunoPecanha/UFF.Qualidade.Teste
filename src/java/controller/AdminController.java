package controller;

import dao.AdministradorDao;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "Admins", urlPatterns = {"/AdminController"})
public class AdminController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/admin.jsp";
    private static String LIST_ADMIN = "/listadmin.jsp";
    private AdministradorDao dao;

    public AdminController() {
        super();
        dao = new AdministradorDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward= "";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")){
            int id = Integer.parseInt(request.getParameter("id"));
           // dao.deleteUser(id);
          //  forward = LIST_USER;
          //  request.setAttribute("admins", dao.getAllUsers());    
        } else if (action.equalsIgnoreCase("edit")){
          //  forward = INSERT_OR_EDIT;
         //   int id = Integer.parseInt(request.getParameter("id"));
         //   Administrador admin = dao.Recuperar(1, 15);
          //  request.setAttribute("admin", admin);
        } else if (action.equalsIgnoreCase("listadmin")){
            forward = LIST_ADMIN;
            request.setAttribute("admins", dao.Recuperar(1, 15));
        } else {
            forward = INSERT_OR_EDIT;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

 /*  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        try {
            Date dob=null;
            String teste = request.getParameter("dob");
            System.out.println(teste);
            if(request.getParameter("dob")!=null){
                dob = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("dob"));
            }
            else{
                dob = null;
            }
                
            user.setDob(dob);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setEmail(request.getParameter("email"));
        String userid = request.getParameter("userid");
        if(userid == null || userid.isEmpty())
        {
            dao.addUser(user);
        }
        else
        {
            user.setUserid(Integer.parseInt(userid));
            dao.updateUser(user);
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST_USER);
        request.setAttribute("users", dao.getAllUsers());
        view.forward(request, response);
    }*/
}