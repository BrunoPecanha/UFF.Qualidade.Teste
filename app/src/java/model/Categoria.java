package model;

public class Categoria {    
    public Categoria(String descricao) {
        this.descricao = descricao;       
    }  
    
    public Categoria(int id, String nome, String possuiLancamento) {
        this.id = id;
        this.descricao = nome;
        this.possuiLancamento = possuiLancamento;
    } 
    
    private int id;
    private String descricao; 
    private String possuiLancamento; 
    
    public int getId(){
        return id;
    }
    
     public String getPossuiLancamento() {
        return this.possuiLancamento;
    }
    
    public String getDescricao() {
        return this.descricao;
    }
    
     public void setDescricao(String descricao) {
         if(!descricao.isEmpty())
            this.descricao = descricao;
    }       
}
