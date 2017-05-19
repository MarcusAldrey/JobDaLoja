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
		
		PreparedStatement user = con.prepareStatement("INSERT INTO Clientes VALUES (?,?,?,?,?)");
		user.setString(1, nome);
		user.setString(2, CPF);
		user.setString(3, telefone);
		user.setString(4, converterPadToSql(dataDeNascimento));
		if(numero != 0)
			user.setString(5, "Rua " + rua + ", bairro " + bairro + ", Nº " + numero + ", " + cidade +", " + estado);
		else
			user.setString(5, "");
		user.execute();
	}
	
	public void novaCompra(String CPFcomprador, float valorTotal, Date dataDeVencimento) {
		
	}
	
	public void getCPFdeComprador(String nome) {
		
	}
	
	public void pagarCompra() {
		
	}
	
	public static ResultSet getCliente(String nome) throws SQLException, ClassNotFoundException {
		Statement clientes = con.createStatement();
		ResultSet rs = clientes.executeQuery("SELECT Nome, CPF, Telefone, DataDeNascimento, Endereco FROM Clientes WHERE Nome='" + nome + "'");
		return rs;		
	}
	
	public static ResultSet getClientes() throws ClassNotFoundException, SQLException {
		new Controller();
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
	 * recebe 2 para compras que vencem nos prï¿½ximos 3 dias
	 * recebe 3 para compras que vencem na prï¿½xima semana 	
	 */
	public List<Compra>	getComprasAVencer(int codigo) {
		return null;
	}
	
	public List<Compra> getComprasVencidas() {
		return null;
	}
	
	public static String converterPadToSql(String data) {
		String dia = data.substring(0, 2);
		String mes = data.substring(3,5);
		String ano = data.substring(6,10);
		String dataFormatada = ano + "-" + mes + "-" + dia;
		return dataFormatada;
	}
	
	public static String converterSqlToPad(String data) {
		String dataDeNascimento = data;
		String ano = dataDeNascimento.substring(0, 4);
		String mes = dataDeNascimento.substring(5,7);
		String dia = dataDeNascimento.substring(8,10);
		
		String dataFormatada = dia + "/" + mes + "/" + ano;
		return dataFormatada;
	}

	public static ResultSet getCompras(String cPF) throws SQLException {
		// TODO Auto-generated method stub
		Statement compras = con.createStatement();
		ResultSet rs = compras.executeQuery("SELECT rowid, Data, QtdParcelas, ValorTotal FROM Compras WHERE CPFComprador='" + cPF + "'");
		return rs;
	}
	
}
