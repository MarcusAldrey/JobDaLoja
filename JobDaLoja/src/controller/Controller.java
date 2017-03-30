package controller;

import java.util.Date;
import java.util.List;

import model.Cliente;
import model.Compra;

public class Controller {
	
	public void cadastrarCliente(String nome, String telefone, String CPF, String Estado, String cidade, String bairro, String rua, int numero) {
		
	}
	
	public void novaCompra(String CPFcomprador, float valorTotal, Date dataDeVencimento) {
		
	}
	
	public void getCPFdeComprador(String nome) {
		
	}
	
	public void pagarCompra() {
		
	}
	
	public Cliente getCliente(String CPF) {
		return null;		
	}
	
	public List<Cliente> getClientes(String nome) {
		return null;		
	}
	
	public Compra getCompra(int id) {
		return null;
	}
	
	public void PagarCompra(int id) {
		getCompra(id).setPaga(true); 
	}
	
	/*
	 * recebe 1 para compras que vencem no dia
	 * recebe 2 para compras que vencem nos próximos 3 dias
	 * recebe 3 para compras que vencem na próxima semana 	
	 */
	public List<Compra>	getComprasAVencer(int codigo) {
		return null;
	}
	
	public List<Compra> getComprasVencidas() {
		return null;
	}
	
}
