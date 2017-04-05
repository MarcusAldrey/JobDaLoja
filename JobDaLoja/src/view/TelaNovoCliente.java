package view;

import java.awt.Dimension;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Canvas;
import java.awt.Panel;

public class TelaNovoCliente extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JFormattedTextField cpf_1;
	private JFormattedTextField telefone_1;
	private JTextField txtFeiraDeSantana;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JFormattedTextField dataDeNascimento_1;

	/**
	 * Create the frame.
	 */
	public TelaNovoCliente() {
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 39, 46, 14);
		contentPane.add(lblNome);
		
		textField = new JTextField();
		textField.setBounds(61, 36, 295, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		try {
			cpf_1 = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
			cpf_1.setSize(100, 20);
			cpf_1.setLocation(61, 61);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cpf_1.setPreferredSize(new Dimension(100,20));
		contentPane.add(cpf_1);
		
		JFormattedTextField dataDeNascimento = null;
		try {
			dataDeNascimento_1 = new JFormattedTextField(new MaskFormatter("##/##/####"));
			dataDeNascimento_1.setLocation(120, 111);
			dataDeNascimento_1.setSize(75, 20);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		dataDeNascimento_1.setPreferredSize(new Dimension(75,20));
		contentPane.add(dataDeNascimento_1);
		
		try {
			telefone_1 = new JFormattedTextField(new MaskFormatter("(##)#####-####"));
			telefone_1.setSize(100, 20);
			telefone_1.setLocation(61, 86);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		telefone_1.setPreferredSize(new Dimension(85,20));
		contentPane.add(telefone_1);
		
		
		
		JLabel lblCPF = new JLabel("CPF:");
		lblCPF.setBounds(10, 64, 46, 14);
		contentPane.add(lblCPF);
		
		JLabel label = new JLabel("Telefone:");
		label.setBounds(10, 89, 46, 14);
		contentPane.add(label);
		
		JLabel lblEndereo = new JLabel("Endere\u00E7o");
		lblEndereo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEndereo.setBounds(10, 157, 57, 14);
		contentPane.add(lblEndereo);
		
		JLabel lblDadosPessoais = new JLabel("Dados Pessoais");
		lblDadosPessoais.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDadosPessoais.setBounds(10, 11, 94, 14);
		contentPane.add(lblDadosPessoais);
		
		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setBounds(10, 182, 46, 14);
		contentPane.add(lblCidade);
		
		JLabel lblRua = new JLabel("Rua:");
		lblRua.setBounds(10, 232, 46, 14);
		contentPane.add(lblRua);
		
		txtFeiraDeSantana = new JTextField();
		txtFeiraDeSantana.setText("Feira de Santana");
		txtFeiraDeSantana.setBounds(61, 179, 100, 20);
		contentPane.add(txtFeiraDeSantana);
		txtFeiraDeSantana.setColumns(10);
		
		String[] estados = {"AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RO", "RS", "RR", "SC", "SE", "SP", "TO"};

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(222, 179, 40, 20);
		for(String estado : estados)
			comboBox.addItem(estado);
		comboBox.setSelectedIndex(4);
		contentPane.add(comboBox);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(180, 182, 46, 14);
		contentPane.add(lblEstado);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(61, 229, 100, 20);
		contentPane.add(textField_1);
		
		JLabel lblBairro = new JLabel("Bairro:");
		lblBairro.setBounds(10, 207, 46, 14);
		contentPane.add(lblBairro);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(61, 204, 100, 20);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setBounds(222, 204, 65, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblN = new JLabel("N\u00BA:");
		lblN.setBounds(180, 207, 46, 14);
		contentPane.add(lblN);
		
		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento:");
		lblDataDeNascimento.setBounds(10, 114, 100, 14);
		contentPane.add(lblDataDeNascimento);
		
		JButton btnLimparCampos = new JButton("Limpar campos");
		btnLimparCampos.setIcon(new ImageIcon(TelaNovoCliente.class.getResource("/view/1491377312_flat_icons-grafiche (3).png")));
		btnLimparCampos.setBounds(200, 294, 170, 50);
		contentPane.add(btnLimparCampos);
		
		JButton btnSalvarCliente = new JButton("Salvar Cliente");
		btnSalvarCliente.setIcon(new ImageIcon(TelaNovoCliente.class.getResource("/view/iconeConfirmacao.png")));
		btnSalvarCliente.setBounds(10, 294, 170, 50);
		contentPane.add(btnSalvarCliente);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setIcon(new ImageIcon(TelaNovoCliente.class.getResource("/view/iconesair (1).png")));
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnVoltar.setBounds(390, 294, 170, 50);
		contentPane.add(btnVoltar);
		
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
		setTitle("La Victoria - Novo Cliente");
	}
}
