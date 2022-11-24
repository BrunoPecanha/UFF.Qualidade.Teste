package dao;

import Infra.financeiroContext;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Lancamento;

public class LancamentoDao implements ILancamentoDao {

    private Connection contexto;
    
    public LancamentoDao() {
        contexto = financeiroContext.getConnection();
    }  
    
    @Override
    public List<Lancamento> Recuperar(int paginaAtual, int qtdRegistros) {  
        int deslocamento = qtdRegistros * (paginaAtual -1);        
        
        List<Lancamento> lstLancamentos = new ArrayList();
        try {
            PreparedStatement ppStatment = contexto.prepareStatement("SELECT *" +
                                  " FROM lancamentos \n" +
                                  "LIMIT ?\n" +
                                  "OFFSET ?");
            
            ppStatment.setInt(1, qtdRegistros);
            ppStatment.setInt(2, deslocamento);
            
            ResultSet regitro = ppStatment.executeQuery();
            
            while (regitro.next()) {
                int id = regitro.getInt("id");
                int contaId = regitro.getInt("id_conta");   
                int categoriaId = regitro.getInt("id_categoria"); 
                double valor = regitro.getDouble("valor"); 
                String operacao = regitro.getString("operacao"); 
                Date data = regitro.getDate("data"); 
                String descricao = regitro.getString("descricao"); 
                String processado = regitro.getString("processado"); 
                Lancamento lancamento = new Lancamento(id, contaId, categoriaId, valor, operacao, data, descricao, processado);
                
                lstLancamentos.add(lancamento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lstLancamentos;
    }   
    
     @Override
     public Boolean LancamentoJaExiste(String token) {
        Boolean existe = false; 
         
        try {
            PreparedStatement ppStatment = contexto.prepareStatement("SELECT * FROM lancamentos where token=?");

            ppStatment.setString(1, token);
            
            ResultSet regitro = ppStatment.executeQuery();
            existe = regitro.next();
            
            } catch (SQLException e) {
            e.printStackTrace();
        }
        return existe;           
    }
    
    @Override
     public List<Lancamento> RecuperarLancamentosUsuario(int idUsuario) {  
        List<Lancamento> lstLancamentos = new ArrayList();
        try {
            PreparedStatement ppStatment = contexto.prepareStatement("SELECT * FROM lancamentos l, contas c, usuarios u where l.id_conta = c.id AND c.id_usuario=u.id and u.id =?");

            ppStatment.setInt(1, idUsuario);
            
            ResultSet regitro = ppStatment.executeQuery();
            
            while (regitro.next()) {
                int id = regitro.getInt("id");
                int contaId = regitro.getInt("id_conta");   
                int categoriaId = regitro.getInt("id_categoria"); 
                double valor = regitro.getDouble("valor"); 
                String operacao = regitro.getString("operacao"); 
                Date data = regitro.getDate("data"); 
                String descricao = regitro.getString("descricao"); 
                String processado = regitro.getString("processado"); 
                Lancamento lancamento = new Lancamento(id, contaId, categoriaId, valor, operacao, data, descricao, processado);
                
                lstLancamentos.add(lancamento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lstLancamentos;
    }   
    
    
    @Override
    public void Salvar(Lancamento lancamento, String token) {
        try {    
                PreparedStatement ppStatment = contexto
                    .prepareStatement("insert into lancamentos(id_conta, id_categoria, valor, operacao, data, descricao, token) values (?,?,?,?,?,?,?)");
           
                ppStatment.setInt(1, lancamento.getContaId());
                ppStatment.setInt(2, lancamento.getCategoriaId());
                ppStatment.setDouble(3, lancamento.getValor());
                ppStatment.setString(4, lancamento.getOperacao());
                ppStatment.setDate(5, lancamento.getData());
                ppStatment.setString(6, lancamento.getDescricao());   
                 ppStatment.setString(7, token);
                ppStatment.executeUpdate();
           
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Atualizar(Lancamento lancamento) {
        try {
                PreparedStatement ppStatment = contexto
                    .prepareStatement("update lancamentos set id_conta=?, id_categoria=?, valor=?, operacao=?,  data=?, descricao=? where id=?");
                
                ppStatment.setInt(1, lancamento.getContaId());
                ppStatment.setInt(2, lancamento.getCategoriaId());
                ppStatment.setDouble(3, lancamento.getValor());
                ppStatment.setString(4, lancamento.getOperacao());
                ppStatment.setDate(5, lancamento.getData());
                ppStatment.setString(6, lancamento.getDescricao());  
                ppStatment.setInt(7, lancamento.getId());  
                ppStatment.executeUpdate();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
     public void Processar(Lancamento lancamento) {
        try {                        
                PreparedStatement ppStatment = contexto
                    .prepareStatement("update lancamentos set id_conta=?, id_categoria=?, valor=?, operacao=?,  data=?, descricao=?, processado=? where id=?");
                
                ppStatment.setInt(1, lancamento.getContaId());
                ppStatment.setInt(2, lancamento.getCategoriaId());
                ppStatment.setDouble(3, lancamento.getValor());
                ppStatment.setString(4, lancamento.getOperacao());
                ppStatment.setDate(5, lancamento.getData());
                ppStatment.setString(6, lancamento.getDescricao());                
                ppStatment.setString(7, lancamento.getProcessado());   
                ppStatment.setInt(8, lancamento.getId());   
                ppStatment.executeUpdate();
        }        
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void Deletar(int id) {
         try {
            PreparedStatement preparedStatement = contexto
                    .prepareStatement("delete from lancamentos where id=?");
           
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }    
}
