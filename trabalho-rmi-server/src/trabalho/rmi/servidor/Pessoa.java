package trabalho.rmi.servidor;

import java.util.Date;

public class Pessoa {

	private int id;
	private String nome;
	private Double saldo = 0.0;
	private Date ultimoSaque = null;
	public Date getUltimoSaque() {
		return ultimoSaque;
	}
	public void setUltimoSaque(Date ultimoSaque) {
		this.ultimoSaque = ultimoSaque;
	}
	public Double getSaldo() {
		return saldo;
	}
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	} 
	
}
