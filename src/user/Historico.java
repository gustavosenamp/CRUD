package user;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Historico {
    private String aluCpf;
    private double peso;
    private String dataHora;
    
    public Historico(String aluCpf, double peso, String dataHora) {
        this.aluCpf = aluCpf;
        this.peso = peso;
        this.dataHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public String getAluCpf() {
        return aluCpf;
    }

    public void setAluId(String aluCpf) {
        this.aluCpf = aluCpf;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }
    
    
}