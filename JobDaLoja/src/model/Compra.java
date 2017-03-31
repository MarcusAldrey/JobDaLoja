package model;


import java.sql.Date;
import java.util.List;

public class Compra {
	
	private static int idCont;
	private int id;
	private Cliente comprador;
	private List<Produto> produtos;
	private Date dataDaCompra;
	private float valorTotal;
	private Date dataDeVencimento;
	private boolean paga;
	
	public Compra() {
		this.id = idCont;
		idCont++;
		setPaga(false);
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
	 * @return the dataDaCompra
	 */
	public Date getDate() {
		return dataDaCompra;
	}

	/**
	 * @param dataDaCompra the dataDaCompra to set
	 */
	public void setDate(Date date) {
		this.dataDaCompra = date;
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

	/**
	 * @return the paga
	 */
	public boolean isPaga() {
		return paga;
	}

	/**
	 * @param paga the paga to set
	 */
	public void setPaga(boolean paga) {
		this.paga = paga;
	}
	
	
}
