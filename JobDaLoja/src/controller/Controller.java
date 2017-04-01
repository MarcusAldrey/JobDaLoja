package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import model.Cliente;
import model.Compra;
import util.DataBase;

public class Controller {
	
	private static Connection con;
		
	public Controller() throws ClassNotFoundException, SQLException {
		con = DataBase.getConnection();
	}
	
	public void cadastrarCliente(String nome, String telefone, String CPF, String Estado, String cidade, String bairro, String rua, int numero) throws ClassNotFoundException, SQLException {
		Statement user = con.prepareStatement("INSERT INTO Users Values (?,?,?)");
		Statement adress = con.prepareStatement("INSERT INTO Adresses Values (?,?,?,?,?");
	}
	
	public void novaCompra(String CPFcomprador, float valorTotal, Date dataDeVencimento) {
		
	}
	
	public void getCPFdeComprador(String nome) {
		
	}
	
	public void pagarCompra() {
		
	}
	
	public ResultSet getCliente(String CPF) throws SQLException {
		Statement clientes = con.createStatement();
		ResultSet rs = clientes.executeQuery("SELECT Nome, CPF, Telefone FROM Clientes");
		return rs;		
	}
	
	public static ResultSet getClientes() throws ClassNotFoundException, SQLException {
		Connection con = DataBase.getConnection();
		Statement statement = con.createStatement();
		ResultSet rs = statement.executeQuery("SELECT Nome, CPF, Telefone FROM Clientes");
		return rs;
	}
	
	public Compra getCompra(int id) {
		return null;
	}
	
	public void PagarCompra(int id) {
		getCompra(id).setPaga(true); 
	}
	
	/*
	 * recebe 1 para compras que vencem no dia
	 * recebe 2 para compras que vencem nos pr�ximos 3 dias
	 * recebe 3 para compras que vencem na pr�xima semana 	
	 */
	public List<Compra>	getComprasAVencer(int codigo) {
		return null;
	}
	
	public List<Compra> getComprasVencidas() {
		return null;
	}
	
}
