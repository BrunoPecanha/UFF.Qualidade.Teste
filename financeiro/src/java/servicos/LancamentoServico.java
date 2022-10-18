package servicos;

import Infra.financeiroContext;
import dao.CategoriaDao;
import dao.ContaDao;
import dao.LancamentoDao;
import dto.ResumoDto;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import model.Administrador;
import model.Categoria;
import model.Conta;
import model.Lancamento;

public class LancamentoServico  {

    private Connection contexto;
    private LancamentoDao daoLancamento;
    private ContaDao daoConta;
    
    public LancamentoServico() {
        contexto = financeiroContext.getConnection();
        daoLancamento = new LancamentoDao();
        daoConta = new ContaDao();       
    }          
    
    public ResumoDto CarregarResumoLancamentos(int idUsuario) 
    {  
        ResumoDto resumo = new ResumoDto();
        resumo.listaCategoriaXPercentual = new ArrayList(); // Para tela inicial
        resumo.lancamentos = daoLancamento.RecuperarLancamentosUsuario(idUsuario);      
        resumo.contas = daoConta.RecuperarPorUsuario(idUsuario);
         
        for (int i = 0; i <= resumo.lancamentos.size()-1; i++) {
            if (resumo.lancamentos.get(i).getOperacao().equals("D") && resumo.lancamentos.get(i).getProcessado().equals("S")){
                resumo.debitos += resumo.lancamentos.get(i).getValor();
            }
            else if (resumo.lancamentos.get(i).getOperacao().equals("C") && resumo.lancamentos.get(i).getProcessado().equals("S")) {
                resumo.creditos += resumo.lancamentos.get(i).getValor();
            }
        }
        return resumo;
    }
}
            
 