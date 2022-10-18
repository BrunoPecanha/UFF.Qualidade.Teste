package dao;

import Infra.financeiroContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Usuario;

public class UsuarioDao implements IUsuarioDao {

    private Connection contexto;
    
    public UsuarioDao() {
        contexto = financeiroContext.getConnection();
    }  
    
    public Usuario Recuperar(int id) {  
        Usuario usuario = null;
        try {
            PreparedStatement ppStatment = contexto.prepareStatement("select * from usuarios where id=?");            
            ppStatment.setInt(1, id);
            
            ResultSet regitro = ppStatment.executeQuery();
            
            if (regitro.next()) {                
                int _id = regitro.getInt("id");
                String nome = regitro.getString("nome");
                String _cpf = regitro.getString("cpf");
                String _senha = regitro.getString("senha");  
                String _suspenso = regitro.getString("suspenso");  
                usuario = new Usuario(_id, nome, _cpf, _senha, _suspenso);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    } 
    
    public Usuario Recuperar(String cpf, String senha) {  
        Usuario usuarioOuAdmin = null;
        PreparedStatement ppStatment = null;
        try {
            
            if (senha != null && cpf != null)  {
                ppStatment = contexto.prepareStatement("select * from usuarios where cpf=? and senha=?");
                ppStatment.setString(1, cpf);
                ppStatment.setString(2, senha);
            }   
         
            ResultSet regitro = ppStatment.executeQuery();
            
            if (regitro.next()) {                
                int id = regitro.getInt("id");
                String nome = regitro.getString("nome");
                String _cpf = regitro.getString("cpf");
                String _senha = regitro.getString("senha");    
                String suspenso = regitro.getString("suspenso");    
                usuarioOuAdmin = new Usuario(id, nome, _cpf, _senha, suspenso);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarioOuAdmin;
    } 
            
    @Override
    public List<Usuario> Recuperar(int paginaAtual, int qtdRegistros) {  
        int deslocamento = qtdRegistros * (paginaAtual -1);        
        
        // Professor, a ideia era paginas, mas a gente resolveu não criar mais essa complexidade, porém a ideia nós deixamos aqui.
        List<Usuario> listaUsuarios = new ArrayList();
        try {
            PreparedStatement ppStatment = contexto.prepareStatement("SELECT *" +
                                  "FROM usuarios\n" +
                                  "LIMIT ?\n" +
                                  "OFFSET ?");
            
            ppStatment.setInt(1, qtdRegistros);
            ppStatment.setInt(2, deslocamento);
            
            ResultSet regitro = ppStatment.executeQuery();
            
            while (regitro.next()) {
                int id = regitro.getInt("id");
                String nome = regitro.getString("nome");
                String cpf = regitro.getString("cpf");
                String senha = regitro.getString("senha");    
                String suspenso = regitro.getString("suspenso");    
                Usuario admin = new Usuario(id, nome, cpf, senha, suspenso);
                
                listaUsuarios.add(admin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaUsuarios;
    }   
    
    @Override
    public void Salvar(Usuario usuario) {
        try {            
            if (usuario.getId() == 0) {
                 PreparedStatement ppStatment = contexto
                    .prepareStatement("insert into usuarios(nome, cpf, senha, suspenso) values (?,?,?,?)");
           
                ppStatment.setString(1, usuario.getNome());
                ppStatment.setString(2, usuario.getCPF());
                ppStatment.setString(3, usuario.getSenha());
                ppStatment.setString(4, (usuario.getSuspenso()));
                ppStatment.executeUpdate();
            }
            else {
                PreparedStatement preparedStatement = contexto
                    .prepareStatement("update usuarios set nome=?, cpf=?, senha=?, suspenso=? where id=?");
            
                preparedStatement.setString(1, usuario.getNome());
                preparedStatement.setString(2, usuario.getCPF());         
                preparedStatement.setString(3, usuario.getSenha());
                preparedStatement.setString(4, usuario.getSuspenso());
                preparedStatement.setInt(5, usuario.getId());
                preparedStatement.executeUpdate();
          }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Deletar(int id) {
         try {
            PreparedStatement preparedStatement = contexto
                    .prepareStatement("delete from usuarios where id=?");
           
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }    
}
