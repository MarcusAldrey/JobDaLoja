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
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.text.MaskFormatter;

import org.omg.CORBA.DATA_CONVERSION;

import controller.Controller;

public class TelaNovoCliente extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNome;
	private JFormattedTextField cpf_1;
	private JFormattedTextField telefone_1;
	private JTextField txtCidade;
	private JTextField textRua;
	private JTextField textBairro;
	private JTextField textNumero;
	private JFormattedTextField dataDeNascimento_1;
	private JTextField txtddd;
	JComboBox<String> comboBoxEstado;

	/**
	 * Create the frame.
	 */
	public TelaNovoCliente() {
		setResizable(false);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNome.setBounds(10, 39, 46, 14);
		contentPane.add(lblNome);
		
		try {
			cpf_1 = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
			cpf_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
			cpf_1.setSize(100, 20);
			cpf_1.setLocation(65, 61);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cpf_1.setPreferredSize(new Dimension(100,20));
		contentPane.add(cpf_1);
		
		try {
			dataDeNascimento_1 = new JFormattedTextField(new MaskFormatter("##/##/####"));
			dataDeNascimento_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
			dataDeNascimento_1.setLocation(130, 111);
			dataDeNascimento_1.setSize(75, 20);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		dataDeNascimento_1.setPreferredSize(new Dimension(75,20));
		contentPane.add(dataDeNascimento_1);
		
		try {
			telefone_1 = new JFormattedTextField(new MaskFormatter("9####-####"));
			telefone_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
			telefone_1.setSize(100, 20);
			telefone_1.setLocation(95, 86);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		telefone_1.setPreferredSize(new Dimension(85,20));
		contentPane.add(telefone_1);
		
		
		
		JLabel lblCPF = new JLabel("CPF:");
		lblCPF.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCPF.setBounds(10, 64, 46, 14);
		contentPane.add(lblCPF);
		
		JLabel label = new JLabel("Telefone:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label.setBounds(10, 89, 55, 14);
		contentPane.add(label);
		
		JLabel lblEndereo = new JLabel("Endere\u00E7o");
		lblEndereo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEndereo.setBounds(10, 157, 57, 14);
		contentPane.add(lblEndereo);
		
		JLabel lblDadosPessoais = new JLabel("Dados Pessoais");
		lblDadosPessoais.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDadosPessoais.setBounds(10, 11, 94, 14);
		contentPane.add(lblDadosPessoais);
		
		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCidade.setBounds(10, 182, 46, 14);
		contentPane.add(lblCidade);
		
		JLabel lblRua = new JLabel("Rua:");
		lblRua.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRua.setBounds(10, 232, 46, 14);
		contentPane.add(lblRua);
		
		txtCidade = new JTextField();
		txtCidade.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtCidade.setText("Feira de Santana");
		txtCidade.setBounds(61, 179, 100, 20);
		contentPane.add(txtCidade);
		txtCidade.setColumns(10);
		
		String[] estados = {"AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RO", "RS", "RR", "SC", "SE", "SP", "TO"};

		comboBoxEstado = new JComboBox<String>();
		comboBoxEstado.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBoxEstado.setBounds(225, 179, 40, 20);
		for(String estado : estados)
			comboBoxEstado.addItem(estado);
		comboBoxEstado.setSelectedIndex(4);
		contentPane.add(comboBoxEstado);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEstado.setBounds(180, 182, 46, 14);
		contentPane.add(lblEstado);
		
		textRua = new JTextField();
		textRua.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textRua.setColumns(10);
		textRua.setBounds(61, 229, 100, 20);
		contentPane.add(textRua);
		
		JLabel lblBairro = new JLabel("Bairro:");
		lblBairro.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblBairro.setBounds(10, 207, 46, 14);
		contentPane.add(lblBairro);
		
		textBairro = new JTextField();
		textBairro.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textBairro.setColumns(10);
		textBairro.setBounds(61, 204, 100, 20);
		contentPane.add(textBairro);
		
		textNumero = new JTextField();
		textNumero.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textNumero.setBounds(225, 204, 65, 20);
		contentPane.add(textNumero);
		textNumero.setColumns(10);
		
		JLabel lblN = new JLabel("N\u00BA:");
		lblN.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblN.setBounds(180, 207, 46, 14);
		contentPane.add(lblN);
		
		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento:");
		lblDataDeNascimento.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDataDeNascimento.setBounds(10, 114, 115, 14);
		contentPane.add(lblDataDeNascimento);
		
		JButton btnLimparCampos = new JButton("Limpar");
		btnLimparCampos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnLimparCampos.setIcon(new ImageIcon(TelaNovoCliente.class.getResource("/view/1491377312_flat_icons-grafiche (3).png")));
		btnLimparCampos.setBounds(200, 294, 170, 50);
		btnLimparCampos.addActionListener(new LimparAction());
		contentPane.add(btnLimparCampos);
		
		JButton btnSalvarCliente = new JButton("Adicionar");
		btnSalvarCliente.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSalvarCliente.setIcon(new ImageIcon(TelaNovoCliente.class.getResource("/view/iconeConfirmacao.png")));
		btnSalvarCliente.setBounds(10, 294, 170, 50);
		btnSalvarCliente.addActionListener(new AdicionarClienteAction());
		contentPane.add(btnSalvarCliente);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnVoltar.setIcon(new ImageIcon(TelaNovoCliente.class.getResource("/view/iconesair (1).png")));
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnVoltar.setBounds(390, 294, 170, 50);
		btnVoltar.addActionListener(new SairAction());
		contentPane.add(btnVoltar);
		
		URL url = this.getClass().getResource("logo.jpg"); 
		Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(url);
		this.setIconImage(iconeTitulo);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int largura = 600;
		int altura = 400;
		int x = (screen.width-largura)/2;
		int y = (screen.height-altura)/2;
		setBounds(x,y,largura,altura);
		setTitle("La Victoria - Novo Cliente");
		
		txtNome = new JTextField();
		txtNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtNome.setBounds(65, 36, 295, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		txtddd = new JTextField();
		txtddd.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtddd.setText("75");
		txtddd.setBounds(65, 86, 22, 20);
		contentPane.add(txtddd);
		txtddd.setColumns(10);
	}
	
	public class AdicionarClienteAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			try {
				Controller.cadastrarCliente(txtNome.getText(),  txtddd.getText() + telefone_1.getText(), cpf_1.getText(), comboBoxEstado.getSelectedItem().toString(), txtCidade.getText(), textBairro.getText(), textRua.getText(), Integer.parseInt(textNumero.getText()), dataDeNascimento_1.getText());
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, "Algum campo foi preenchido incorretamente");
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				JOptionPane.showMessageDialog(null, "JDBC não instalado");
				e1.printStackTrace();
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, "Não foi possível acessar o banco de dados");
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "Cliente adicionado com sucesso!");
			fecharTela();
		}
		
	}
	
	public class SairAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			fecharTela();			
		}
		
	}
	
	public class LimparAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			txtNome.setText("");
			cpf_1.setText("");
			telefone_1.setText("");
			txtddd.setText("");
			textBairro.setText("");
			textNumero.setText("");
			textRua.setText("");
			txtCidade.setText("");
			dataDeNascimento_1.setText("");
			comboBoxEstado.setSelectedIndex(4);
		}
		
	}
	
	private void fecharTela() {
		this.dispose();		
	}
}
