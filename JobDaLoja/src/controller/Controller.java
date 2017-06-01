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
	private static int tamanhoRetorno;
	
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
		compra.setString(5, formaDePagamento);
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
		ResultSet rs = clientes.executeQuery("SELECT Nome, CPF, Telefone, DataDeNascimento, Endereco, OutrosDebitos FROM Clientes WHERE Nome='" + nome + "'");
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
		ResultSet tamanho = statement.executeQuery("SELECT COUNT (Nome) FROM Clientes WHERE DataDeNascimento LIKE '____-"+mesAtual+"-__'");
		tamanhoRetorno = tamanho.getInt(1);
		
		ResultSet rs = statement.executeQuery("SELECT Nome,DataDeNascimento FROM Clientes WHERE DataDeNascimento LIKE '____-"+mesAtual+"-__'");
		return rs;
	}
	
	public static ResultSet getAniversarianteDia() throws SQLException{
		Calendar c = Calendar.getInstance();
		String mesAtual = String.format("%02d", c.get(Calendar.MONTH)+1);
		String diaAtual = String.format("%02d", c.get(Calendar.DAY_OF_MONTH));
		Statement statement = con.createStatement();
		
		ResultSet tamanho = statement.executeQuery("SELECT Nome,DataDeNascimento FROM Clientes WHERE DataDeNascimento LIKE '____-"+mesAtual+"-"+diaAtual+"'");
		tamanhoRetorno = tamanho.getInt(1);
		
		ResultSet rs = statement.executeQuery("SELECT DataDeNascimento FROM Clientes WHERE DataDeNascimento LIKE '____-"+mesAtual+"-"+diaAtual+"'");
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

	public static void atualizarValorPagoParcela(int iDParcela, float novoValor) throws SQLException {
		// TODO Auto-generated method stub
		Statement statement = con.createStatement();
		statement.execute("UPDATE Parcelas SET ValorPago = "+ novoValor + " WHERE IdParcela = " + iDParcela);
	}
	
	public static void atualizarParcelaPaga(int iDParcela, boolean pago) throws SQLException {
		// TODO Auto-generated method stub
		Statement statement = con.createStatement();
		int n;
		if(pago == true)
			n=1;
		else 
			n=0;
		statement.execute("UPDATE Parcelas SET Pago = "+ n + " WHERE IdParcela = " + iDParcela);
	}
	
	public static ResultSet getNomeComprador(int idCompra) throws SQLException {
		Statement statement = con.createStatement();
		ResultSet rs = statement.executeQuery("SELECT NomeComprador FROM Compras WHERE IDCompra = " + idCompra);
		return rs;
	}

	public static void atualizarOutrosDebitos(String nomeCliente, float novoValor) throws SQLException {
		// TODO Auto-generated method stub
		Statement statement = con.createStatement();
		statement.execute("UPDATE Clientes SET OutrosDebitos = "+ novoValor + " WHERE Nome = '" + nomeCliente + "'");
	}
	
	/* 1 para vencidas hoje 
	 * 2 para vencidas nos próximos 3 dias
	 * 3 para vencidas nos proximos 7 dias
	 * 4 para já vencidas
	 */
	public static ResultSet getComprasVencidas(int modificador) throws SQLException {
		String dataAtual;
		Calendar cal = Calendar.getInstance();
		String day = String.format("%02d", cal.get(Calendar.DATE));
		String month = String.format("%02d", cal.get(Calendar.MONTH) + 1);
		String year = Integer.toString(cal.get(Calendar.YEAR));
		dataAtual = year + "-" + month + "-" + day;
		Statement statement = con.createStatement();
		ResultSet rs = null;
		if (modificador == 1) {
			rs = statement.executeQuery("SELECT IdCompra, ValorTotal, ValorPago, Vencimento FROM Parcelas WHERE Vencimento = '" + dataAtual + "' AND Pago = 0");
		}
		else if(modificador == 2) 
			rs = statement.executeQuery("SELECT IdCompra, ValorTotal, ValorPago, Vencimento FROM Parcelas WHERE Vencimento < '" + dataAtual + "' AND Pago = 0");
		return rs;
	}

	public static int getTamanhoRetorno() {
		// TODO Auto-generated method stub
		return tamanhoRetorno;
	}
}
