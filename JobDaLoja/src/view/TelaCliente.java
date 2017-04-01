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
import javax.swing.border.EmptyBorder;

import controller.Controller;

public class TelaCliente extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public TelaCliente(String CPF) throws SQLException, ClassNotFoundException {
		URL url = this.getClass().getResource("logo.jpg"); 
		Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(url);
		this.setIconImage(iconeTitulo);		
		setResizable(false);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int largura = 600;
		int altura = 400;
		int x = (screen.width-largura)/2;
		int y = (screen.height-altura)/2;
		setBounds(x,y,largura,altura);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ResultSet rs = Controller.getCliente(CPF);
		
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 39, 46, 16);
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblNome);
		
		JLabel lblClientname = new JLabel(rs.getString(1));
		lblClientname.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblClientname.setBounds(60, 39, 348, 16);
		contentPane.add(lblClientname);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCpf.setBounds(10, 61, 46, 16);
		contentPane.add(lblCpf);
		
		JLabel lblCpfCliente = new JLabel(rs.getString(2));
		lblCpfCliente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCpfCliente.setBounds(47, 61, 115, 16);
		contentPane.add(lblCpfCliente);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTelefone.setBounds(10, 83, 64, 16);
		contentPane.add(lblTelefone);
		
		JLabel labelTelefoneCliente = new JLabel(rs.getString(3));
		labelTelefoneCliente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelTelefoneCliente.setBounds(79, 83, 115, 16);
		contentPane.add(labelTelefoneCliente);
		
		JLabel lblDadosPessoais = new JLabel("Dados pessoais");
		lblDadosPessoais.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDadosPessoais.setBounds(10, 12, 115, 16);
		contentPane.add(lblDadosPessoais);
		
		JLabel lblEndereo = new JLabel("Endere\u00E7o:");
		lblEndereo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEndereo.setBounds(10, 105, 115, 16);
		contentPane.add(lblEndereo);
		
		JLabel label = new JLabel("(xx) 9xxxx-xxxx");
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(84, 105, 115, 16);
		contentPane.add(label);
		
		JLabel lblCompras = new JLabel("Compras");
		lblCompras.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCompras.setBounds(10, 130, 77, 16);
		contentPane.add(lblCompras);
		
		JButton btnNewButton = new JButton("Voltar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		btnNewButton.setBounds(10, 312, 111, 38);
		getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 157, 574, 143);
		contentPane.add(scrollPane);
		
		JButton btnNovaCompra = new JButton("Nova Compra");
		btnNovaCompra.setBounds(150, 312, 111, 38);
		contentPane.add(btnNovaCompra);
		this.setTitle("La Victoria - Informações de cliente");
	}
}
