package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.MaskFormatter;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JSeparator;

public class TelaCliente extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableCompras;
	private JTable tableParcelas;
	private int IDCompraAtual;
	Object[][] valoresCompras;
	Object[][] valoresParcelas;
	JScrollPane scrollPaneParcelas;
	private JTextField modifiOutrosDeb;
	JLabel outrosDebitos;
	String nomeCliente;
	JButton buttonSalvarEdicao;
	JButton buttonEditar;
	JTextField lblClientname;
	JTextField lblCpfCliente;
	JTextField lbllTelefoneCliente;
	JTextField lblniverTxt;
	JTextField lblenderecoCliente;
	JTextField lbltextEmail;
	Border bordaPadrao;
	Color bgpadrao;
	
	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public TelaCliente(String nome) throws SQLException, ClassNotFoundException {
		nomeCliente = nome;
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
		
		buttonEditar = new JButton("");
		buttonEditar.setIcon(new ImageIcon(TelaCliente.class.getResource("/view/pencil.png")));
		buttonEditar.setBounds(334, 39, 40, 35);
		buttonEditar.addActionListener(new StartEditionAction());
		
		buttonSalvarEdicao = new JButton("");
		buttonSalvarEdicao.setIcon(new ImageIcon(TelaCliente.class.getResource("/view/pencil (1).png")));
		buttonSalvarEdicao.setBounds(334, 82, 40, 35);
		buttonSalvarEdicao.setVisible(false);
		buttonSalvarEdicao.addActionListener(new EndEditionAction());
		contentPane.add(buttonSalvarEdicao);
		contentPane.add(buttonEditar);


		JLabel lbl_Nome = new JLabel("Nome:");
		lbl_Nome.setBounds(10, 39, 46, 16);
		lbl_Nome.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lbl_Nome);

		lblClientname = new JTextField(rs.getString(1));
		lblClientname.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblClientname.setBounds(60, 39, 314, 20);
		contentPane.add(lblClientname);

		JLabel lbl_Cpf = new JLabel("CPF:");
		lbl_Cpf.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_Cpf.setBounds(10, 64, 46, 16);
		contentPane.add(lbl_Cpf);

		lblCpfCliente = new JTextField(rs.getString(2));
		lblCpfCliente.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCpfCliente.setBounds(47, 64, 115, 20);
		contentPane.add(lblCpfCliente);

		JLabel lbl_Telefone = new JLabel("Telefone:");
		lbl_Telefone.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_Telefone.setBounds(10, 86, 64, 16);
		contentPane.add(lbl_Telefone);

		lbllTelefoneCliente = new JTextField(rs.getString(3));
		lbllTelefoneCliente.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbllTelefoneCliente.setBounds(79, 86, 115, 20);
		contentPane.add(lbllTelefoneCliente);

		JLabel lblDadosPessoais = new JLabel("Dados pessoais");
		lblDadosPessoais.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDadosPessoais.setBounds(10, 12, 115, 16);
		contentPane.add(lblDadosPessoais);

		JLabel lbl_Endereo = new JLabel("Endere\u00E7o:");
		lbl_Endereo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_Endereo.setBounds(10, 154, 77, 16);
		contentPane.add(lbl_Endereo);

		JLabel lblCompras = new JLabel("Compras");
		lblCompras.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCompras.setBounds(10, 195, 77, 16);
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
		scrollPane.setBounds(10, 222, 364, 177);
		contentPane.add(scrollPane);

		ResultSet compras = Controller.getCompras(nome);
		int count = 0;
		while (compras.next())
			++count;
		valoresCompras = new Object[count][4];	
		compras = Controller.getCompras(nome);
		if(count > 0) {
			int cont = 0;
			while(compras.next()) {
				for(int z = 0; z<4; z++)
					if(z==1)
						valoresCompras[cont][z] = Controller.converterSqlToPad(compras.getString(z+1));
					else if(z==2)
						valoresCompras[cont][z] = compras.getString(z+1);
					else
						valoresCompras[cont][z] = compras.getString(z+1);
				cont++;
			}
		}
		String[] columnNames = {"ID", "Data", "Valor Total", "Forma de Pagamento"};
		tableCompras = new JTable(valoresCompras, columnNames);
		tableCompras.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tableCompras.getColumnModel().getColumn(0).setPreferredWidth(17);
		tableCompras.getColumnModel().getColumn(1).setPreferredWidth(25);
		tableCompras.getColumnModel().getColumn(2).setPreferredWidth(50);
		tableCompras.setRowHeight(30);
		tableCompras.setRowSelectionAllowed(true);

		tableCompras.getSelectionModel().addListSelectionListener(new ListenerParcelas());

		scrollPane.setViewportView(tableCompras);

		JButton btnNovaCompra = new JButton("Nova Compra");
		btnNovaCompra.setIcon(new ImageIcon(TelaCliente.class.getResource("/view/rsz_iconecompra.png")));
		btnNovaCompra.setBounds(10, 410, 170, 50);
		btnNovaCompra.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				JFrame frame = null;
				try {
					frame = new TelaNovaCompra(nomeCliente);
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

		JLabel lblNewLabel = new JLabel("Pagamentos");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(85, 11, 130, 14);
		panel.add(lblNewLabel);

		scrollPaneParcelas = new JScrollPane();
		scrollPaneParcelas.setBounds(10, 187, 280, 200);
		panel.add(scrollPaneParcelas);

		tableParcelas = new JTable();
		scrollPaneParcelas.setViewportView(tableParcelas);

		JLabel lblParcelas = new JLabel("Parcelas");
		lblParcelas.setBounds(10, 162, 88, 14);
		panel.add(lblParcelas);
		lblParcelas.setFont(new Font("Tahoma", Font.BOLD, 12));

		JLabel lblOutrosDbitos = new JLabel("Outros D\u00E9bitos:");
		lblOutrosDbitos.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblOutrosDbitos.setBounds(10, 52, 97, 14);
		panel.add(lblOutrosDbitos);

		modifiOutrosDeb = new JTextField();
		modifiOutrosDeb.setBounds(10, 93, 45, 27);
		panel.add(modifiOutrosDeb);
		modifiOutrosDeb.addKeyListener(new OnlyDigits());
		modifiOutrosDeb.setColumns(10);

		JButton btnAddOutrosdeb = new JButton("");
		btnAddOutrosdeb.setBounds(65, 93, 30, 27);
		panel.add(btnAddOutrosdeb);
		btnAddOutrosdeb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				atualizarOutrosDebitos(1);
				modifiOutrosDeb.setText("");
			}
		});
		btnAddOutrosdeb.setIcon(new ImageIcon(TelaCliente.class.getResource("/view/plus.png")));

		JButton btnLessOutroDeb = new JButton("");
		btnLessOutroDeb.setBounds(105, 93, 30, 27);
		panel.add(btnLessOutroDeb);
		btnLessOutroDeb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				atualizarOutrosDebitos(2);
				modifiOutrosDeb.setText("");
			}
		});
		btnLessOutroDeb.setIcon(new ImageIcon(TelaCliente.class.getResource("/view/minus-symbol-inside-a-circle.png")));

		JLabel lblNewLabel_1 = new JLabel("R$");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(120, 52, 26, 14);
		panel.add(lblNewLabel_1);

		if(rs.getString(6) != null)
			outrosDebitos = new JLabel(rs.getString(6));
		else
			outrosDebitos = new JLabel("0");
		outrosDebitos.setFont(new Font("Tahoma", Font.BOLD, 12));
		outrosDebitos.setBounds(145, 52, 57, 14);
		panel.add(outrosDebitos);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 149, 280, 2);
		panel.add(separator);
		btnAddOutrosdeb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});

		JLabel lbl_Aniversrio = new JLabel("Nascimento");
		lbl_Aniversrio.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_Aniversrio.setBounds(10, 108, 91, 16);
		contentPane.add(lbl_Aniversrio);

		String niver = rs.getString(4);
		try {
			lblniverTxt = new JFormattedTextField(new MaskFormatter("##/##/####"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(!(niver == null)) 
		lblniverTxt.setText(Controller.converterSqlToPad(niver));
		lblniverTxt.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblniverTxt.setBounds(97, 108, 97, 20);
		contentPane.add(lblniverTxt);

		JLabel label_1 = new JLabel((String) null);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_1.setBounds(95, 105, 115, 16);
		contentPane.add(label_1);

		JLabel label = new JLabel((String) null);
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(105, 108, 115, 16);
		contentPane.add(label);

		lblenderecoCliente = new JTextField();
		lblenderecoCliente.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblenderecoCliente.setBounds(85, 154, 289, 23);

		JLabel restoEndereco = new JLabel();
		restoEndereco.setText("<dynamic>");
		restoEndereco.setFont(new Font("Tahoma", Font.PLAIN, 14));
		restoEndereco.setBounds(10, 181, 289, 16);
		contentPane.add(restoEndereco);

		JButton btnSalvarMudanEmParcelas = new JButton("Salvar Mudan\u00E7as");
		btnSalvarMudanEmParcelas.setIcon(new ImageIcon(TelaCliente.class.getResource("/view/iconeConfirmacao.png")));
		btnSalvarMudanEmParcelas.setBounds(65, 398, 170, 45);
		btnSalvarMudanEmParcelas.addActionListener(new SalvarMudancasEmParcelasAction());
		panel.add(btnSalvarMudanEmParcelas);

		String endereco = rs.getString(5);
		String resto = "";
		if(endereco.length() > 45)
			resto = endereco.substring(44);
		lblenderecoCliente.setText(endereco);
		restoEndereco.setText(resto);
		contentPane.add(lblenderecoCliente);		
		
		JLabel lbl_Email = new JLabel("Email: ");
		lbl_Email.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_Email.setBounds(10, 130, 46, 16);
		contentPane.add(lbl_Email);
		
		lbltextEmail = new JTextField(rs.getString(7));
		lbltextEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbltextEmail.setBounds(60, 130, 314, 20);
		contentPane.add(lbltextEmail);

		this.setTitle("La Victoria - Informações de cliente");
		
		bordaPadrao = lblClientname.getBorder();
		bgpadrao = lblClientname.getBackground();
		
		lblClientname.setEditable(false);
		lblCpfCliente.setEditable(false);
		lbllTelefoneCliente.setEditable(false);
		lblniverTxt.setEditable(false);
		lblenderecoCliente.setEditable(false);
		lbltextEmail.setEditable(false);
		
		lblClientname.setBackground(null);
		lblClientname.setBorder(null);
		
		
		lblCpfCliente.setBackground(null);
		lblCpfCliente.setBorder(null);
		
		lbllTelefoneCliente.setBackground(null);
		lbllTelefoneCliente.setBorder(null);
		
		lblniverTxt.setBackground(null);
		lblniverTxt.setBorder(null);
		
		lblenderecoCliente.setBackground(null);
		lblenderecoCliente.setBorder(null);
		
		lbltextEmail.setBackground(null);
		lbltextEmail.setBorder(null);
	}

	public class ListenerParcelas implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			// TODO Auto-generated method stub
			if(arg0.getValueIsAdjusting()) {
				IDCompraAtual = Integer.parseInt(tableCompras.getValueAt(tableCompras.getSelectedRow(), 0).toString());
				System.out.println(IDCompraAtual);
			}
			try {
				atualizarTabela(IDCompraAtual);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Não foi possível acessar o banco de dados");
				e.printStackTrace();
			}
		}


	}

	public class SalvarMudancasEmParcelasAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int quantidadeParcelas = valoresParcelas.length;
			for(int i = 0; i<quantidadeParcelas; i++) {
				int IDParcela = Integer.parseInt((String) valoresParcelas[i][0]);
				float totalParcela = Float.parseFloat(((String) valoresParcelas[i][1]));
				float novoValor = Float.parseFloat((String) tableParcelas.getValueAt(i, 2));
				if(novoValor > totalParcela) {
					JOptionPane.showMessageDialog(null, "O valor que você inseriu (" + novoValor + ") é maior que o valor da parcela, isso não é permitido!");
					return;
				}
				if(novoValor == totalParcela)
					try {
						Controller.atualizarParcelaPaga(IDParcela, true);
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				else if(novoValor < totalParcela)
					try {
						Controller.atualizarParcelaPaga(IDParcela, false);
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				try {
					Controller.atualizarValorPagoParcela(IDParcela, novoValor);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Não foi possível acessar o bando de dados");
					e1.printStackTrace();
				}
			}
			JOptionPane.showMessageDialog(null, "Valores salvos com sucesso!");
		}

	}

	private void atualizarTabela(int id) throws SQLException {
		ResultSet parcelas = Controller.getParcelas(id);
		int size = 0;
		while (parcelas.next())
			++size;
		valoresParcelas = new Object[size][4];	
		parcelas = Controller.getParcelas(id);
		if(size > 0) {
			int cont = 0;
			while(parcelas.next()) {
				for(int z = 0; z<4; z++)
					if(z==3)
						valoresParcelas[cont][z] = Controller.converterSqlToPad(parcelas.getString(z+1));
					else if(z==0)
						valoresParcelas[cont][z] = parcelas.getString(z+1);
					else
						valoresParcelas[cont][z] = parcelas.getString(z+1);
				cont++;
			}
		}
		String[] columnNames = {"ID", "Valor Total", "Valor Pago", "Vencimento"};
		tableParcelas = new JTable(valoresParcelas, columnNames);
		tableParcelas.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tableParcelas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				int IdParcela = Integer.parseInt((String) tableParcelas.getValueAt(tableParcelas.getSelectedRow(), 0));
				System.out.println(IdParcela);
			}
		});
		scrollPaneParcelas.setViewportView(tableParcelas);
		tableParcelas.repaint();
	}

	private void atualizarOutrosDebitos(int modificator) {
		float valorAtual = Float.parseFloat(outrosDebitos.getText());
		float modifi = Float.parseFloat(modifiOutrosDeb.getText());
		float novoValor = 0;
		if(modificator == 1) {
			novoValor = valorAtual + modifi;
		}
		else if(modificator == 2) {
			novoValor = valorAtual - modifi;
		}
		try {
			Controller.atualizarOutrosDebitos(nomeCliente, novoValor);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Não foi possível acessar o banco de dados");
			e.printStackTrace();
		}
		outrosDebitos.setText(Float.toString(novoValor));		
	}
	
	public class StartEditionAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			buttonEditar.setVisible(false);
			buttonSalvarEdicao.setVisible(true);
			lblClientname.setEditable(true);
			lblCpfCliente.setEditable(true);
			lbllTelefoneCliente.setEditable(true);
			lblniverTxt.setEditable(true);
			lblenderecoCliente.setEditable(true);
			lbltextEmail.setEditable(true);
			lblClientname.requestFocus();
			
			lblClientname.setBackground(bgpadrao);
			lblClientname.setBorder(bordaPadrao);
			
			
			lblCpfCliente.setBackground(bgpadrao);
			lblCpfCliente.setBorder(bordaPadrao);
			
			lbllTelefoneCliente.setBackground(bgpadrao);
			lbllTelefoneCliente.setBorder(bordaPadrao);
			
			lblniverTxt.setBackground(bgpadrao);
			lblniverTxt.setBorder(bordaPadrao);
			
			lblenderecoCliente.setBackground(bgpadrao);
			lblenderecoCliente.setBorder(bordaPadrao);
			
			lbltextEmail.setBackground(bgpadrao);
			lbltextEmail.setBorder(bordaPadrao);
		}
		
	}
	
	public class EndEditionAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			buttonEditar.setVisible(true);
			buttonSalvarEdicao.setVisible(false);
			
			lblClientname.setEditable(false);
			lblCpfCliente.setEditable(false);
			lbllTelefoneCliente.setEditable(false);
			lblniverTxt.setEditable(false);
			lblenderecoCliente.setEditable(false);
			lbltextEmail.setEditable(false);
			
			lblClientname.setBackground(null);
			lblClientname.setBorder(null);
			
			
			lblCpfCliente.setBackground(null);
			lblCpfCliente.setBorder(null);
			
			lbllTelefoneCliente.setBackground(null);
			lbllTelefoneCliente.setBorder(null);
			
			lblniverTxt.setBackground(null);
			lblniverTxt.setBorder(null);
			
			lblenderecoCliente.setBackground(null);
			lblenderecoCliente.setBorder(null);
			
			lbltextEmail.setBackground(null);
			lbltextEmail.setBorder(null);
			try {
				Controller.editarDadosCliente(nomeCliente, lblClientname.getText(),lblCpfCliente.getText(),lbllTelefoneCliente.getText(),lblniverTxt.getText(),lblenderecoCliente.getText(), lbltextEmail.getText());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "Não foi possível alterar as informações do cliente");
				return;
			}
			nomeCliente = lblClientname.getText();		
		}
		
	}
	public class OnlyDigits implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			char c = e.getKeyChar();
			if(!(Character.isDigit(c)|| c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE || c == KeyEvent.VK_PERIOD)){
				e.consume();			
			}

		}

	}
}
