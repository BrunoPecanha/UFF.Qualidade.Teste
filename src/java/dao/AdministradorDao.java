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
            
    @Override
    public List<Administrador> Recuperar(int paginaAtual, int qtdRegistros) {  
        int deslocamento = qtdRegistros * (paginaAtual -1);        
        
        List<Administrador> listaAdmins = new ArrayList<Administrador>();
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
    public Administrador Recuperar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Salvar(Administrador adm) {
        try {
           PreparedStatement ppStatment = contexto
           .prepareStatement("insert into administradores(nome, cpf, senha) values (?,?,?)");
           
            ppStatment.setString(1, adm.getNome());
            ppStatment.setString(2, adm.getCPF());
            ppStatment.setString(2, adm.getSenha());
            ppStatment.executeUpdate();
        }
        catch(SQLException e) {
            
        }
    }

    @Override
    public void Deletar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
