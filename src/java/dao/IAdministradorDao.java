package dao;

import java.util.List;
import model.Administrador;

public interface IAdministradorDao {
    public List<Administrador> Recuperar(int paginaAtual, int qtdRegistros);
    public Administrador Recuperar(int id);
    public void Salvar(Administrador adm);
    public void Deletar(int id);
}
