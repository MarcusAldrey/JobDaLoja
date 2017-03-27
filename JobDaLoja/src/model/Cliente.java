package model;

import java.util.Date;
import java.util.List;

public class Cliente {
	
	private String nome;
	private String telefone;
	private List<Compra> compras;
	private Endereco endereco;
	private Date dataDeNasc;
	
	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * @return the telefone
	 */
	public String getTelefone() {
		return telefone;
	}
	/**
	 * @param telefone the telefone to set
	 */
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	/**
	 * @return the compras
	 */
	public List<Compra> getCompras() {
		return compras;
	}
	/**
	 * @param compras the compras to set
	 */
	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}
	/**
	 * @return the endereco
	 */
	public Endereco getEndereco() {
		return endereco;
	}
	/**
	 * @param endereco the endereco to set
	 */
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	/**
	 * @return the dataDeNasc
	 */
	public Date getDataDeNasc() {
		return dataDeNasc;
	}
	/**
	 * @param dataDeNasc the dataDeNasc to set
	 */
	public void setDataDeNasc(Date dataDeNasc) {
		this.dataDeNasc = dataDeNasc;
	}
		
}
