package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import java.awt.Component;
import javax.swing.Box;

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
	public TelaCliente(String nome) throws SQLException, ClassNotFoundException {
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
		contentPane.setLayout(null);
		
		ResultSet rs = Controller.getCliente(nome);
		
		
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
		lblEndereo.setBounds(10, 127, 77, 16);
		contentPane.add(lblEndereo);
		
		JLabel lblCompras = new JLabel("Compras");
		lblCompras.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCompras.setBounds(10, 171, 77, 16);
		contentPane.add(lblCompras);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setIcon(new ImageIcon(TelaCliente.class.getResource("/view/iconesair (1).png")));
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		btnVoltar.setBounds(204, 410, 170, 50);
		getContentPane().add(btnVoltar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 198, 364, 201);
		contentPane.add(scrollPane);
		
		JButton btnNovaCompra = new JButton("Nova Compra");
		btnNovaCompra.setIcon(new ImageIcon(TelaCliente.class.getResource("/view/rsz_iconecompra.png")));
		btnNovaCompra.setBounds(10, 410, 170, 50);
		btnNovaCompra.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFrame frame = null;
				try {
					frame = new TelaNovaCompra(rs.getString(1));
				} catch (SQLException | ClassNotFoundException e1) {
					JOptionPane.showMessageDialog(null, "Não foi possível acessar o bando de dados");
					e1.printStackTrace();
				}
				frame.setVisible(true);
			}
		});
		contentPane.add(btnNovaCompra);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(384, 12, 300, 448);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Detalhes da compra");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(85, 11, 130, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Produtos Comprados");
		lblNewLabel_1.setBounds(10, 236, 100, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblParcelas = new JLabel("Parcelas");
		lblParcelas.setBounds(10, 37, 100, 14);
		panel.add(lblParcelas);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 261, 280, 176);
		panel.add(scrollPane_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 62, 280, 162);
		panel.add(scrollPane_2);
		
		JLabel lblAniversrio = new JLabel("Anivers\u00E1rio:");
		lblAniversrio.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAniversrio.setBounds(10, 105, 91, 16);
		contentPane.add(lblAniversrio);
		
		String niver = rs.getString(4);
		JLabel niverTxt = new JLabel();
		if(!(niver == null)) 
			niverTxt = new JLabel(Controller.converterSqlToPad(niver));
		niverTxt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		niverTxt.setBounds(97, 105, 115, 16);
		contentPane.add(niverTxt);
		
		JLabel label_1 = new JLabel((String) null);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_1.setBounds(95, 105, 115, 16);
		contentPane.add(label_1);
		
		JLabel label = new JLabel((String) null);
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(105, 108, 115, 16);
		contentPane.add(label);
		
		JLabel enderecoCliente = new JLabel();
		enderecoCliente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		enderecoCliente.setBounds(85, 127, 289, 16);
		
		JLabel restoEndereco = new JLabel();
		restoEndereco.setText("<dynamic>");
		restoEndereco.setFont(new Font("Tahoma", Font.PLAIN, 14));
		restoEndereco.setBounds(10, 144, 289, 16);
		contentPane.add(restoEndereco);
		
		String endereco = rs.getString(5);
		String resto = "";
		if(endereco.length() > 45)
			resto = endereco.substring(44);
		enderecoCliente.setText(endereco);
		restoEndereco.setText(resto);
		contentPane.add(enderecoCliente);		
		
		this.setTitle("La Victoria - Informações de cliente");
	}
}
