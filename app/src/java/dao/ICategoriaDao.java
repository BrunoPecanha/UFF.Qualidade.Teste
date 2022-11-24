package dao;

import java.util.List;
import model.Categoria;

public interface ICategoriaDao {
    public List<Categoria> Recuperar(int paginaAtual, int qtdRegistros);
    public Categoria Recuperar(int id);
    public void Salvar(Categoria categoria);
    public void Deletar(int id);
}