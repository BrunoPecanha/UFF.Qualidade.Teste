package model;

import java.sql.Date;

public class Lancamento {    
    
    public Lancamento(int contaId, int categoriaId, double valor, String operacao, Date data, String descricao) {
        this.contaId = contaId;
        this.categoriaId = categoriaId;
        this.valor = valor;
        this.operacao = operacao.toUpperCase();
        this.data = data;
        this.descricao = descricao;
    }  
    
    public Lancamento(int id, int contaId, int categoriaId, double valor, String operacao, Date data, String descricao, String processado) {
        this.id = id;
        this.contaId = contaId;
        this.categoriaId = categoriaId;
        this.valor = valor;
        this.operacao = operacao;
        this.data = data;
        this.descricao = descricao;
        this.processado = processado;
    } 
    
    private int id;
    private int contaId;
    private int categoriaId;
    private double valor;
    private String operacao;
    private Date data;
    private String descricao;
    private String processado;
    
    
    public String getProcessado() {
        return this.processado;
    }   
    
    public int getId(){
        return this.id;
    }
    
    public int getContaId() {
        return this.contaId;
    }
   
    public int getCategoriaId() {
        return this.categoriaId;
    }
    
    public double getValor() {
        return this.valor;
    }
    
    public void setValor(double valor) {
        this.valor = valor;
    }
    
    public String getOperacao() {
        return this.operacao;
    }
    
    public void setOperacao(String operacao) {
        this.operacao = operacao;
    } 
    
    public Date getData() {
        return this.data;
    }
    
    public void setData(Date data) {
        this.data = data;
    }  
   
     public String getDescricao() {
        return this.descricao;
    }
    
    public void setNDescricao(String descricao) {
        this.descricao = descricao;
    }    
    
    public void processarLancamento() {
        this.processado = "S";
    }
}
