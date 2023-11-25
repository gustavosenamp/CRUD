package user;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Historico {
    private String aluCpf;
    private double peso;
    private String dataHora;

    DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("uuuu-MM-dd");
    String dataFormatada = formatterData.format(LocalDateTime.now());  //formata data atual

    DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm:ss");
    String horaFormatada = formatterHora.format(LocalDateTime.now());   //formata hora atual
    
    public Historico(String aluCpf, double peso) {
        this.aluCpf = aluCpf;
        this.peso = peso;
        this.dataHora = dataFormatada + " " + horaFormatada;
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