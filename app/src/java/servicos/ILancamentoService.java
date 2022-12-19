package servicos;

import dto.ResumoDto;
import java.util.List;
import model.Lancamento;

public interface ILancamentoService  {   
    
    public ResumoDto GerarResumoLancamentos(int idUsuario);    
    public ResumoDto ProcessarLancamentos(List<Lancamento> lancamentos);    
    public List<Lancamento> GerarBaixaLancamento(List<Lancamento> lancamentos);    
    public void ProcessarLancamento(int id);        
    public Boolean LancamentoJaExiste(String token);    
    public List<Lancamento> RecuperarLancamentos(int idUsuario);     
    public void Salvar(Lancamento lancamentos, String token);    
    public void Atualizar(Lancamento lancamentos );     
    public void Deletar(int lancamentoId);
}        
 