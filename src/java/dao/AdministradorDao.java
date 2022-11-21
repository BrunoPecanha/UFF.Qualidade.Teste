package dao;

import Infra.financeiroContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Administrador;

public class AdministradorDao implements IAdministradorDao {

    private Connection contexto;
    
    public AdministradorDao() {
        contexto = financeiroContext.getConnection();
    }      
    
    
    public Administrador Recuperar(int id) {  
        Administrador admin = null;
        try {
            PreparedStatement ppStatment = contexto.prepareStatement("select * from administradores where id=?");            
            ppStatment.setInt(1, id);
            
            ResultSet regitro = ppStatment.executeQuery();
            
            if (regitro.next()) {                
                int _id = regitro.getInt("id");
                String nome = regitro.getString("nome");
                String _cpf = regitro.getString("cpf");
                String _senha = regitro.getString("senha");  
                admin = new Administrador(_id, nome, _cpf, _senha);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    } 
            
    public Administrador Recuperar(String cpf, String senha) {  
        Administrador admin = null;
        try {
            PreparedStatement ppStatment = contexto.prepareStatement("select * from administradores where cpf=? and senha=?");
            
            ppStatment.setString(1, cpf);
            ppStatment.setString(2, senha);
            
            ResultSet regitro = ppStatment.executeQuery();
            
            if (regitro.next()) {                
                int id = regitro.getInt("id");
                String nome = regitro.getString("nome");
                String _cpf = regitro.getString("cpf");
                String _senha = regitro.getString("senha");  
                admin = new Administrador(id, nome, _cpf, _senha);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    } 
    
    
    @Override
    public List<Administrador> Recuperar(int paginaAtual, int qtdRegistros) {  
        int deslocamento = qtdRegistros * (paginaAtual -1);        
        
        // Professor, a ideia era paginas, mas a gente resolveu não criar mais essa complexidade, porém a ideia nós deixamos aqui.
        List<Administrador> listaAdmins = new ArrayList();
        try {
            PreparedStatement ppStatment = contexto.prepareStatement("SELECT *" +
                                  "FROM administradores\n" +
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
                Administrador admin = new Administrador(id, nome, cpf, senha);
                
                listaAdmins.add(admin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaAdmins;
    }   
    
    @Override
    public void Salvar(Administrador adm) {
        try {            
            if (adm.getId() == 0) {
                 PreparedStatement ppStatment = contexto
                    .prepareStatement("insert into administradores(nome, cpf, senha) values (?,?,?)");
           
                ppStatment.setString(1, adm.getNome());
                ppStatment.setString(2, adm.getCPF());
                ppStatment.setString(3, adm.getSenha());
                ppStatment.executeUpdate();
            }
            else {
                PreparedStatement preparedStatement = contexto
                    .prepareStatement("update administradores set nome=?, cpf=?, senha=? where id=?");
            
                preparedStatement.setString(1, adm.getNome());
                preparedStatement.setString(2, adm.getCPF());         
                preparedStatement.setString(3, adm.getSenha());
                preparedStatement.setInt(4, adm.getId());
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
                    .prepareStatement("delete from administradores where id=?");
           
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }    
}
