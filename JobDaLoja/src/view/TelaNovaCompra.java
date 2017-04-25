package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.Controller;

public class TelaNovaCompra extends JFrame {

	private JPanel contentPane;
	private JTable table;
	Object[][] valoresDaTabela;

	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public TelaNovaCompra(String CPF) throws ClassNotFoundException, SQLException {
		URL url = this.getClass().getResource("logo.jpg"); 
		Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(url);
		this.setIconImage(iconeTitulo);		
		setResizable(false);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int largura = 700;
		int altura = 500;
		int x = (screen.width-largura)/2;
		int y = (screen.height-altura)/2;
		setBounds(x,y,largura,altura);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		ResultSet rs = Controller.getCliente(CPF);
		this.setTitle("Nova Compra - " + rs.getString(1));
		contentPane.setLayout(null);
		
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 10, 46, 17);
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblNome);
		
		JLabel lblClientname = new JLabel(rs.getString(1));
		lblClientname.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblClientname.setBounds(66, 10, 250, 17);
		contentPane.add(lblClientname);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 42, 550, 214);
		contentPane.add(scrollPane);
		
		valoresDaTabela = new Object[][] {null, null, null, null};
		
		table = new JTable(1, 4);
		table.setModel(new DefaultTableModel(valoresDaTabela, new String[] {"Quantidade", "Produto", "Valor", "Valor total"}));
		table.setRowSelectionAllowed(false);
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Novo item");
		btnNewButton.setBounds(570, 42, 114, 36);
		contentPane.add(btnNewButton);
	}
	
	
}
