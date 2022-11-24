package controller;

import dao.CategoriaDao;
import dao.LancamentoDao;
import dto.ResumoDto;
import java.io.IOException;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import java.sql.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Lancamento;
import servicos.LancamentoService;

@WebServlet(name = "Lancamentos", urlPatterns = {"/LancamentoController"})
public class LancamentoController extends HttpServlet {        
    private static String LIST_LANCAMENTOS = "/listlancamento.jsp";    
    private CategoriaDao daoCategoria;
    private LancamentoService lancamentoServico;
  
    
    public LancamentoController() {
        super();        
        lancamentoServico = new LancamentoService();
        daoCategoria = new CategoriaDao();
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
            ResumoDto resumo = lancamentoServico.CarregarResumoLancamentos(usuario);
                 
            request.setAttribute("debitos", resumo.debitos);
            request.setAttribute("creditos",  resumo.creditos);
            request.setAttribute("total",  resumo.creditos - resumo.debitos); 
            request.setAttribute("lancamentos", resumo.lancamentos);
            request.setAttribute("contaslancamento", resumo.contas);
            request.setAttribute("categoriaslancamento", daoCategoria.Recuperar(1, 100));
            RequestDispatcher view = request.getRequestDispatcher(LIST_LANCAMENTOS);       
            view.forward(request, response);
        }   
    }

    @Override 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {        
        String sessaoValida = request.getParameter("session");
        if ("".equals(sessaoValida)){
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
        else {
            int usuario = parseInt(request.getParameter("id"));
            String token = request.getParameter("token");
            String acao = request.getParameter("action");           

            if (acao.equalsIgnoreCase("salvar")){   
                
                if (lancamentoServico.LancamentoJaExiste(token)) {
                    ResumoDto resumo = lancamentoServico.CarregarResumoLancamentos(usuario); // Pegar id do usuario

                    request.setAttribute("debitos", resumo.debitos);
                    request.setAttribute("creditos",  resumo.creditos);
                    request.setAttribute("total",  resumo.creditos - resumo.debitos); 
                    request.setAttribute("lancamentos", resumo.lancamentos);
                    request.setAttribute("contaslancamento", resumo.contas);
                    request.setAttribute("categoriaslancamento", daoCategoria.Recuperar(1, 100));
                    RequestDispatcher view = request.getRequestDispatcher(LIST_LANCAMENTOS);       
                    view.forward(request, response);
                    
                    return;
                }
                 
                
                try {
                    String lancamentoId = request.getParameter("id_lancamento");
                    String contaId = request.getParameter("id_conta");
                    String categoriaId = request.getParameter("id_categoria");
                    String operacao = request.getParameter("operacao");

                    long dataAtual = System.currentTimeMillis();  
                    Date data = new java.sql.Date(dataAtual);            

                    if(lancamentoId == null || lancamentoId.isEmpty()) {
                        lancamentoServico.Salvar(new Lancamento(parseInt(contaId), parseInt(categoriaId), parseDouble(request.getParameter("valor")), request.getParameter("operacao"), data, request.getParameter("descricao")), token);
                    }
                    else {
                        lancamentoServico.Atualizar(new Lancamento(parseInt(lancamentoId), parseInt(contaId), parseInt(categoriaId), parseDouble(request.getParameter("valor")), operacao, data, request.getParameter("descricao"), null));
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }           
            }
            else if (acao.equalsIgnoreCase("delete")){
                int id = Integer.parseInt(request.getParameter("id_exclusao"));
                lancamentoServico.Deletar(id);                         
           }            
            else if (acao.equalsIgnoreCase("processar")){
                lancamentoServico.Processar(usuario);
            }

            ResumoDto resumo = lancamentoServico.CarregarResumoLancamentos(usuario); // Pegar id do usuario

            request.setAttribute("debitos", resumo.debitos);
            request.setAttribute("creditos",  resumo.creditos);
            request.setAttribute("total",  resumo.creditos - resumo.debitos); 
            request.setAttribute("lancamentos", resumo.lancamentos);
            request.setAttribute("contaslancamento", resumo.contas);
            request.setAttribute("categoriaslancamento", daoCategoria.Recuperar(1, 100));
            RequestDispatcher view = request.getRequestDispatcher(LIST_LANCAMENTOS);       
            view.forward(request, response);
        }
    }
}
