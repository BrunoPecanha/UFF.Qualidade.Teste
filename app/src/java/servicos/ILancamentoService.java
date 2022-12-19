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
    
    public ResumoDto GerarResumoLancamentos(int idUsuario) 
    {  
        ResumoDto resumo = ProcessarLancamentos(daoLancamento.RecuperarLancamentosUsuario(idUsuario));
        resumo.listaCategoriaXPercentual = new ArrayList();        
        resumo.contas = daoConta.RecuperarPorUsuario(idUsuario);
        
        return resumo;
    }
    
    public ResumoDto ProcessarLancamentos(List<Lancamento> lancamentos) {
        ResumoDto resumo = new ResumoDto();
        resumo.lancamentos = lancamentos;            
         
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
    
    public List<Lancamento> GerarBaixaLancamento(List<Lancamento> lancamentos) {         
         for (int i = 0; i <= lancamentos.size()-1; i++) {
                lancamentos.get(i).processarLancamento();
                daoLancamento.Processar(lancamentos.get(i));
          }
         return lancamentos;
    }
    
    public void ProcessarLancamento(int id) {
        List<Lancamento> lancamentos = daoLancamento.RecuperarLancamentosUsuario(id); 
        this.GerarBaixaLancamento(lancamentos);
    }
    
    // Para corrigir um bug de session ao atualizar a tela
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
 