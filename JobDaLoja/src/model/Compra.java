package model;

import java.util.Date;
import java.util.List;

public class Compra {
	
	private static int idCont;
	private int id;
	private Cliente comprador;
	private List<Produto> produtos;
	private Date date;
	private float valorTotal;
	private Date dataDeVencimento;
	
	public Compra() {
		this.id = idCont;
		idCont++;
	}
	
	/**
	 * @return the dataDeVencimento
	 */
	public Date getDataDeVencimento() {
		return dataDeVencimento;
	}



	/**
	 * @param dataDeVencimento the dataDeVencimento to set
	 */
	public void setDataDeVencimento(Date dataDeVencimento) {
		this.dataDeVencimento = dataDeVencimento;
	}



	/**
	 * @return the comprador
	 */
	public Cliente getComprador() {
		return comprador;
	}

	/**
	 * @param comprador the comprador to set
	 */
	public void setComprador(Cliente comprador) {
		this.comprador = comprador;
	}

	/**
	 * @return the produtos
	 */
	public List<Produto> getProdutos() {
		return produtos;
	}

	/**
	 * @param produtos the produtos to set
	 */
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the valorTotal
	 */
	public float getValorTotal() {
		return valorTotal;
	}

	/**
	 * @param valorTotal the valorTotal to set
	 */
	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
	}

	/**
	 * @return the idCont
	 */
	public static int getIdCont() {
		return idCont;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	
}
