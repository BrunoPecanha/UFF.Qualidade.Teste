package dao;

import java.util.List;
import model.Lancamento;

public interface ILancamentoDao {
    public List<Lancamento> Recuperar(int paginaAtual, int qtdRegistros);    
    public void Salvar(Lancamento lancamento, Boolean ehProcessamento);
    public void Deletar(int id);
}
