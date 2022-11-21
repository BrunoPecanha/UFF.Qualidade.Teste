package dao;

import Infra.financeiroContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Categoria;

public class CategoriaDao implements ICategoriaDao {

    private Connection contexto;
    
    public CategoriaDao() {
        contexto = financeiroContext.getConnection();
    }            
            
     private boolean CategoriaEmUso(int id) throws SQLException {
        PreparedStatement ppStatment = contexto.prepareStatement("SELECT * FROM LANCAMENTOS WHERE ID_CATEGORIA=?");
            
        ppStatment.setInt(1, id);
        ResultSet regitro = ppStatment.executeQuery();
        
        return regitro.next();
    }
    
    
    @Override
    public List<Categoria> Recuperar(int paginaAtual, int qtdRegistros) {  
        int deslocamento = qtdRegistros * (paginaAtual -1);        
        
        // Professor, a ideia era paginas, mas a gente resolveu não criar mais essa complexidade, porém a ideia nós deixamos aqui.
        List<Categoria> listCategorias = new ArrayList();
        try {
            PreparedStatement ppStatment = contexto.prepareStatement("SELECT *" +
                                  " FROM categorias \n" +
                                  "LIMIT ?\n" +
                                  "OFFSET ?");
            
            ppStatment.setInt(1, qtdRegistros);
            ppStatment.setInt(2, deslocamento);
            
            ResultSet regitro = ppStatment.executeQuery();
            
            while (regitro.next()) {
                String displayDelete = this.CategoriaEmUso(regitro.getInt("id")) ? "none" : "normal";
                int id = regitro.getInt("id");
                String descricao = regitro.getString("descricao");               
                Categoria categoria = new Categoria(id, descricao, displayDelete);
                
                listCategorias.add(categoria);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listCategorias;
    }   

    public Categoria Recuperar(int id) {    
        Categoria categoria = null;
        try {
            PreparedStatement preparedStatement = contexto.prepareStatement("select * from categorias where id=?");
            preparedStatement.setInt(1, id);
            ResultSet db = preparedStatement.executeQuery();

            if (db.next()) {
                String displayDelete = this.CategoriaEmUso(db.getInt("id")) ? "none" : "normal";
                categoria = new Categoria(db.getInt("id"), db.getString("descricao"), displayDelete);                          
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categoria;
    }
    
    
    @Override
    public void Salvar(Categoria categoria) {
        try {            
            if (categoria.getId() == 0) {
                 PreparedStatement ppStatment = contexto
                    .prepareStatement("insert into categorias(descricao) values (?)");
           
                ppStatment.setString(1, categoria.getDescricao());
                ppStatment.executeUpdate();
            }
            else {
                PreparedStatement preparedStatement = contexto
                    .prepareStatement("update categorias set descricao=? where id=?");
            
                preparedStatement.setString(1, categoria.getDescricao());
                preparedStatement.setInt(2, categoria.getId());
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
                    .prepareStatement("delete from categorias where id=?");
           
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }    
}
