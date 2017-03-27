package model;

public class Produto {
	
	private String nome;
	private String tipo;
	private float preco;
	private int quantidadeEmEstoque;
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
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}
	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	/**
	 * @return the preco
	 */
	public float getPreco() {
		return preco;
	}
	/**
	 * @param preco the preco to set
	 */
	public void setPreco(float preco) {
		this.preco = preco;
	}
	/**
	 * @return the quantidadeEmEstoque
	 */
	public int getQuantidadeEmEstoque() {
		return quantidadeEmEstoque;
	}
	/**
	 * @param quantidadeEmEstoque the quantidadeEmEstoque to set
	 */
	public void setQuantidadeEmEstoque(int quantidadeEmEstoque) {
		this.quantidadeEmEstoque = quantidadeEmEstoque;
	}
	
}
