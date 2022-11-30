package dto;

import java.util.List;
import model.Conta;
import model.Lancamento;

public class ResumoDto {    
    public double debitos;
    public double creditos;   
    public List<Lancamento> lancamentos;
    public List<Conta> contas;
    public List<CategoriaXPercentualDto> listaCategoriaXPercentual;
}
