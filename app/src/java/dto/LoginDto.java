package dto;

public class LoginDto {
    private String usuario;
    private String senha;
    
    public LoginDto(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
    }    
    
    public String getUsuario() {
        return this.usuario;
    }
   
     public String getSenha() {
        return this.senha;
    }
}
