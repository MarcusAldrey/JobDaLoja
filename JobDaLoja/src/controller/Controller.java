package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import model.Compra;
import util.DataBase;

public class Controller {

	private static Connection con;

	public Controller() throws ClassNotFoundException, SQLException {
		con = DataBase.getConnection();
	}

	public static void cadastrarCliente(String nome, String telefone, String CPF, String estado, String cidade, String bairro, String rua, int numero, String dataDeNascimento) throws ClassNotFoundException, SQLException {

		PreparedStatement user = con.prepareStatement("INSERT INTO Clientes VALUES (?,?,?,?,?,?)");
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

	public static void novaCompra(String nomeComprador, float valorTotal, String dataCompra, String formaDePagamento, float valorParcela, String[] datasDeVencimento) throws SQLException {
		PreparedStatement compra = con.prepareStatement("INSERT INTO Compras VALUES (?,?,?,?,?)");
		compra.setString(2, nomeComprador);
		compra.setString(3, converterPadToSql(dataCompra));
		compra.setFloat(4, valorTotal);
		compra.setString(5, formaDePagamento + " - " + datasDeVencimento.length + "X");
		compra.execute();
		Statement statement = con.createStatement();
		ResultSet rs = statement.executeQuery("SELECT IDCompra FROM Compras WHERE NomeComprador = '" + nomeComprador + "' AND Data = '" + converterPadToSql(dataCompra) + "' AND ValorTotal = "+valorTotal);
		int idCompra = rs.getInt(1);
		if(formaDePagamento.equals("Crediario")) {
			for(int i = 0; i < datasDeVencimento.length; i++) {
				PreparedStatement parcelas = con.prepareStatement("INSERT INTO Parcelas VALUES (?,?,?,?,?,?)");
				parcelas.setInt(2, idCompra);
				parcelas.setFloat(3, valorParcela);
				parcelas.setFloat(4, 0);
				parcelas.setString(5, converterPadToSql(datasDeVencimento[i]));
				parcelas.setBoolean(6, false);
				parcelas.execute();
			}
		}
	}

	public static ResultSet getCompras(String nome) throws SQLException {
		Statement compras = con.createStatement();
		ResultSet rs = compras.executeQuery("SELECT IDCompra, Data, ValorTotal, FormaDePagamento FROM Compras WHERE NomeComprador = '" + nome + "'");
		return rs;
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
	
	public static ResultSet getParcelas(int IDCompra) throws SQLException {
		Statement statement = con.createStatement();
		ResultSet rs = statement.executeQuery("SELECT IdParcela, ValorTotal, ValorPago, Vencimento FROM Parcelas WHERE IdCompra = " + IDCompra);
		return rs;
	}

	public static ResultSet getAniversariantes() throws SQLException{
		Calendar c = Calendar.getInstance();
		String mesAtual = String.format("%02d", c.get(Calendar.MONTH)+1);
		Statement statement = con.createStatement();
		ResultSet rs = statement.executeQuery("SELECT Nome,DataDeNascimento FROM C.lientes WHERE DataDeNascimento REGEXP BINARY '\\d{4}-("+mesAtual+")-\\d{2}'");
		System.out.println(rs.getString(1)+"  "+rs.getString(2));
		return rs;
	}
	public static String converterPadToSql(String data) {
		String dia = data.substring(0, 2);
		String mes = data.substring(3,5);
		String ano = data.substring(6,10);
		String dataFormatada = ano + "-" + mes + "-" + dia;
		return dataFormatada;
	}

	public static String getMesPad(String data) {
		String mes = data.substring(3,5);
		return mes;
	}

	public static String getDiaPad(String data) {
		String dia = data.substring(0, 2);
		return dia;
	}

	public static String getAnoPad(String data) {
		String ano = data.substring(6,10);
		return ano;
	}
	
	public static String getMesSQL(String data) {
		String mes = data.substring(5,7);
		return mes;
	}

	public static String getDiaSQL(String data) {
		String dia = data.substring(8,10);
		return dia;
	}

	public static String getAnoSQL(String data) {
		String ano = data.substring(0, 4);
		return ano;
	}

	public static String converterSqlToPad(String data) {
		String dataDeNascimento = data;
		String ano = dataDeNascimento.substring(0, 4);
		String mes = dataDeNascimento.substring(5,7);
		String dia = dataDeNascimento.substring(8,10);

		String dataFormatada = dia + "/" + mes + "/" + ano;
		return dataFormatada;
	}

}
