package controller;

import dao.CategoriaDao;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Categoria;


@WebServlet(name = "Categorias", urlPatterns = {"/CategoriaController"})
public class CategoriaController extends HttpServlet {        
    private static String LIST_CATEGORIA = "/listcategoria.jsp";
    private CategoriaDao dao;

    public CategoriaController() {
        super();
        dao = new CategoriaDao();
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
            request.setAttribute("categorias", dao.Recuperar(1, 15));
            RequestDispatcher view = request.getRequestDispatcher(LIST_CATEGORIA);       
            view.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {        
        String acao = request.getParameter("action");           
        
        if (acao.equalsIgnoreCase("salvar")){            
            try {
                String categoriaId = request.getParameter("id_categoria");

                if(categoriaId == null || categoriaId.isEmpty()) {
                    dao.Salvar(new Categoria(request.getParameter("descricao")));
                }
                else {
                    dao.Salvar(new Categoria(parseInt(categoriaId), request.getParameter("descricao"), ""));
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } 
        }
        else if (acao.equalsIgnoreCase("delete")){
            int id = Integer.parseInt(request.getParameter("id_exclusao"));
            dao.Deletar(id);                       
       }
        
        RequestDispatcher view = request.getRequestDispatcher(LIST_CATEGORIA);
        request.setAttribute("categorias", dao.Recuperar(1, 15));
        view.forward(request, response);
    }
}
