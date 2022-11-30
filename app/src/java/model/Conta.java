package model;

public class Conta {    
    public Conta(int idUsuario, String descricao, String banco, String agencia, String numeroConta) {
        this.usuarioId = idUsuario;
        this.descricao = descricao;        
        this.banco = banco;      
        this.agencia = agencia;
        this.numeroConta = numeroConta;        
    }  
    
    public Conta(int id, int idUsuario, String descricao, String banco, String agencia, String numeroConta, String possuiLancamento) {
        this.id = id;
        this.usuarioId = idUsuario;
        this.descricao = descricao;        
        this.banco = banco;      
        this.agencia = agencia;
        this.numeroConta = numeroConta;
        this.possuiLancamento = possuiLancamento;
    } 
    
    private int id;
    private int usuarioId;
    private String descricao;     
    private String banco; 
    private String agencia; 
    private String numeroConta; 
    private String possuiLancamento; 
    
    public int getId(){
        return this.id;
    }
    
     public String getPossuiLancamento() {
        return this.possuiLancamento;
    }
    
    
    public int getUsuarioId() {
        return this.usuarioId;
    }
    
    public String getDescricao() {
        return this.descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao.isEmpty() ? this.descricao : descricao;
    }
    
    public String getBanco() {
        return this.banco;
    }
    
    public void setBanco(String banco) {
        this.banco = banco;
    } 
    
    public String getAgencia() {
        return this.agencia;
    }
    
    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }  
   
     public String getNumeroConta() {
        return this.numeroConta;
    }
    
    public void setNumeroConta(String numeroConta) {
        this.agencia = numeroConta;
    }    
}