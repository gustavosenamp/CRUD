package user;

import java.util.Date;

public class Aluno {
	private String cpf;
	private String nome;
	private Date data;
	private double peso;
	private double altura;
	
	public Aluno(String cpf, String nome, Date data, double peso, double altura) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.data = data;
		this.peso = peso;
		this.altura = altura;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}
	
	
}
