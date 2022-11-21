package dao;

import java.util.List;
import model.Conta;

public interface IContaDao {
    public List<Conta> Recuperar(int paginaAtual, int qtdRegistros);    
    public void Salvar(Conta categoria);
    public void Deletar(int id);
}
