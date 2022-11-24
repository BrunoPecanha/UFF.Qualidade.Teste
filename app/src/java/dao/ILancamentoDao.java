package dao;

import java.util.List;
import model.Lancamento;

public interface ILancamentoDao {
    public List<Lancamento> Recuperar(int paginaAtual, int qtdRegistros);    
    public void Salvar(Lancamento lancamento, String token);
    public void Atualizar(Lancamento lancamento);
    public void Processar(Lancamento lancamento);
    public void Deletar(int id);
    public Boolean LancamentoJaExiste(String token);
    public List<Lancamento> RecuperarLancamentosUsuario(int idUsuario);
}
