package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import model.Compra;
import util.DataBase;

public class Controller {
	
	private static Connection con;
		
	public Controller() throws ClassNotFoundException, SQLException {
		con = DataBase.getConnection();
	}
	
	public static void cadastrarCliente(String nome, String telefone, String CPF, String estado, String cidade, String bairro, String rua, int numero, String dataDeNascimento) throws ClassNotFoundException, SQLException {
		String dia = dataDeNascimento.substring(0, 2);
		String mes = dataDeNascimento.substring(3,5);
		String ano = dataDeNascimento.substring(6,10);
		String dataFormatada = ano + "-" + mes + "-" + dia;
		Date dataSql = Date.valueOf(dataFormatada);	
			
		PreparedStatement user = con.prepareStatement("INSERT INTO Clientes VALUES (?,?,?,?)");
		user.setString(1, nome);
		user.setString(2, CPF);
		user.setString(3, telefone);
		user.setDate(4, dataSql);
		user.execute();
	}
	
	public void novaCompra(String CPFcomprador, float valorTotal, Date dataDeVencimento) {
		
	}
	
	public void getCPFdeComprador(String nome) {
		
	}
	
	public void pagarCompra() {
		
	}
	
	public static ResultSet getCliente(String CPF) throws SQLException, ClassNotFoundException {
		Statement clientes = con.createStatement();
		ResultSet rs = clientes.executeQuery("SELECT Nome, CPF, Telefone FROM Clientes WHERE CPF='" + CPF + "'");
		return rs;		
	}
	
	public static ResultSet getClientes() throws ClassNotFoundException, SQLException {
		Statement statement = con.createStatement();
		ResultSet rs = statement.executeQuery("SELECT Nome, CPF, Telefone FROM Clientes");
		return rs;
	}
	
	public static ResultSet getClientes(String value) throws ClassNotFoundException, SQLException {
		Statement statement = con.createStatement();
		ResultSet rs = statement.executeQuery("SELECT Nome, CPF, Telefone FROM Clientes WHERE Nome LIKE '" + value + "%'");
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
