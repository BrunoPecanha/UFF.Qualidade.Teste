package dao;

import Infra.financeiroContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Conta;

public class ContaDao implements IContaDao {

    private Connection contexto;
    
    public ContaDao() {
        contexto = financeiroContext.getConnection();
    }            
        
    private boolean ContaPossuiLancamento(int id) throws SQLException {
        PreparedStatement ppStatment = contexto.prepareStatement("SELECT * FROM LANCAMENTOS WHERE ID_CONTA=?");
            
        ppStatment.setInt(1, id);
        ResultSet regitro = ppStatment.executeQuery();
        
        return regitro.next();
    }
    
    public Conta Recuperar(int id) {
      
        Conta conta = null;
        try {
            PreparedStatement ppStatment = contexto.prepareStatement("SELECT * FROM CONTAS WHERE ID=?");
            ppStatment.setInt(1, id);
            
            ResultSet regitro = ppStatment.executeQuery();

            if(regitro.next()) {
                String displayDelete = this.ContaPossuiLancamento(regitro.getInt("id")) ? "none" : "normal";
                int _id = regitro.getInt("id");
                int usuarioId = regitro.getInt("id_usuario");   
                String descricao = regitro.getString("nome_conta"); 
                String banco = regitro.getString("banco"); 
                String agencia = regitro.getString("agencia"); 
                String numeroConta = regitro.getString("conta_corrente"); 
                conta = new Conta(_id, usuarioId, descricao, banco, agencia, numeroConta, displayDelete);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conta;
    }   
    
    public List<Conta> RecuperarPorUsuario(int idUsuario) {
      
        List<Conta> contas = new ArrayList();
        try {
            PreparedStatement ppStatment = contexto.prepareStatement("SELECT * FROM CONTAS C, USUARIOS u\n" +                                                                               
                                                                                "WHERE c.id_usuario = u.id\n" +    
                                                                                "and u.id =?");
            ppStatment.setInt(1, idUsuario);
            
            ResultSet regitro = ppStatment.executeQuery();

           while (regitro.next()) {
                String displayDelete = this.ContaPossuiLancamento(regitro.getInt("id")) ? "none" : "normal";
                int _id = regitro.getInt("id");
                int usuarioId = regitro.getInt("id_usuario");   
                String descricao = regitro.getString("nome_conta"); 
                String banco = regitro.getString("banco"); 
                String agencia = regitro.getString("agencia"); 
                String numeroConta = regitro.getString("conta_corrente"); 
                contas.add(new Conta(_id, usuarioId, descricao, banco, agencia, numeroConta, displayDelete));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contas;
    }  
    
    @Override
    public List<Conta> Recuperar(int paginaAtual, int qtdRegistros) {  
        int deslocamento = qtdRegistros * (paginaAtual -1);        
        
        // Professor, a ideia era paginas, mas a gente resolveu não criar mais essa complexidade, porém a ideia nós deixamos aqui.
        List<Conta> lstContas = new ArrayList();
        try {
            PreparedStatement ppStatment = contexto.prepareStatement("SELECT *" +
                                  " FROM contas \n" +
                                  "LIMIT ?\n" +
                                  "OFFSET ?");
            
            ppStatment.setInt(1, qtdRegistros);
            ppStatment.setInt(2, deslocamento);
            
            ResultSet regitro = ppStatment.executeQuery();
            
            while (regitro.next()) {
                String displayDelete = this.ContaPossuiLancamento(regitro.getInt("id")) ? "none" : "normal";
                int id = regitro.getInt("id");
                int usuarioId = regitro.getInt("id_usuario");   
                String descricao = regitro.getString("nome_conta"); 
                String banco = regitro.getString("banco"); 
                String agencia = regitro.getString("agencia"); 
                String numeroConta = regitro.getString("conta_corrente"); 
                Conta conta = new Conta(id, usuarioId, descricao, banco, agencia, numeroConta, displayDelete);
                
                lstContas.add(conta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lstContas;
    }   
    
    @Override
    public void Salvar(Conta conta) {
        try {            
            if (conta.getId() == 0) {
                 PreparedStatement ppStatment = contexto
                    .prepareStatement("insert into contas(id_usuario, nome_conta, banco, agencia, conta_corrente) values (?,?,?,?,?)");
           
                ppStatment.setInt(1, conta.getUsuarioId());
                ppStatment.setString(2, conta.getDescricao());
                ppStatment.setString(3, conta.getBanco());
                ppStatment.setString(4, conta.getAgencia());
                ppStatment.setString(5, conta.getNumeroConta());
                ppStatment.executeUpdate();
            }
            else {
                PreparedStatement preparedStatement = contexto
                    .prepareStatement("update contas set nome_conta=?, banco=?, agencia=?, conta_corrente=? where id=?");
                
                preparedStatement.setString(1, conta.getDescricao());
                preparedStatement.setString(2, conta.getBanco());
                preparedStatement.setString(3, conta.getAgencia());
                preparedStatement.setString(4, conta.getNumeroConta());
                preparedStatement.setInt(5, conta.getId());
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
                    .prepareStatement("delete from contas where id=?");
           
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }    
}
