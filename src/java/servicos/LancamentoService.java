package servicos;

import dao.ContaDao;
import dao.LancamentoDao;
import dto.ResumoDto;
import java.util.ArrayList;
import java.util.List;
import model.Lancamento;

public class LancamentoService  {
    private final LancamentoDao daoLancamento;
    private final ContaDao daoConta;
    
    public LancamentoService() {
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
    
    public void Processar(int usuario) {
         List<Lancamento> lancamentos = daoLancamento.RecuperarLancamentosUsuario(usuario); 
         
         for (int i = 0; i <= lancamentos.size()-1; i++) {
                lancamentos.get(i).processarLancamento();
                daoLancamento.Processar(lancamentos.get(i));
          }
    }
    
    public Boolean LancamentoJaExiste(String token) {
        return daoLancamento.LancamentoJaExiste(token);
    }
    
    public void Salvar(Lancamento lancamentos, String token) {
        daoLancamento.Salvar(lancamentos, token);
    }
    
     public void Atualizar(Lancamento lancamentos ) {
        daoLancamento.Atualizar(lancamentos);
    }  
     
      public void Deletar(int lancamentoId) {
        daoLancamento.Deletar(lancamentoId);
    } 
}        
 