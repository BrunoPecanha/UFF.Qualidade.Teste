package dao;

import java.util.List;
import model.Usuario;

public interface IUsuarioDao {
    public List<Usuario> Recuperar(int paginaAtual, int qtdRegistros); 
    public Usuario Recuperar(String cpf, String senha);
    public Usuario Recuperar(int id);
    public void Salvar(Usuario usuario);
    public void Deletar(int id);
}
