package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import controller.Controller;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.JFormattedTextField;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class TelaNovaCompra extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Object[][] valoresDaTabela;
	private JTextField campoQuantidade;
	private JTextField campoProduto;
	JComboBox<?> comboBoxParcelas;
	int currenty = 33;
	int currentLine = 0;
	private JTextField caixaDesconto;
	JFormattedTextField vencimento;
	Object[][] produtos;
	private JTable tabelaProdutos;
	String[] columnNames;
	TextField campoValor;
	JLabel totalProduto;
	JScrollPane scrollPane;
	private JTextField campoValorx;
	JLabel valorTotalCompra;

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
		
		produtos = new Object[30][4];
		
		ResultSet rs = Controller.getCliente(CPF);
		this.setTitle("Nova Compra - " + rs.getString(1));
		contentPane.setLayout(null);
		
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 10, 46, 17);
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblNome);
		
		JLabel lblClientname = new JLabel(rs.getString(1));
		lblClientname.setBounds(66, 10, 250, 17);
		lblClientname.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblClientname);
		

		campoValorx = new JTextField();
		campoValorx.setBounds(316, 77, 57, 20);
		contentPane.add(campoValorx);
		campoValorx.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 108, 519, 205);
		contentPane.add(scrollPane);
		
		String[] columnNames = {"Quantidade", "Produto", "Valor unit. (R$)", "Total (R$)"};
		tabelaProdutos = new JTable(produtos, columnNames);
		tabelaProdutos.setRowSelectionAllowed(false);
		tabelaProdutos.setEnabled(false);
		tabelaProdutos.setShowGrid(false);
		Font cabecalho = new Font("SansSerif", Font.BOLD, 12);
		tabelaProdutos.getTableHeader().setFont(cabecalho);
		scrollPane.setViewportView(tabelaProdutos);
		
		NumberFormat amountFormat = NumberFormat.getNumberInstance();
		NumberFormatter formatter = new NumberFormatter(amountFormat);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setIcon(new ImageIcon(TelaNovaCompra.class.getResource("/view/iconeAdicionar.png")));
		btnAdicionar.setBounds(539, 108, 145, 65);
		btnAdicionar.addActionListener(new AddNewItem());
		contentPane.add(btnAdicionar);
		
		JLabel lblNewLabel_1 = new JLabel("Total da Compra:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(275, 367, 114, 14);
		contentPane.add(lblNewLabel_1);
		
		valorTotalCompra = new JLabel("R$ 00,00");
		valorTotalCompra.setFont(new Font("Tahoma", Font.BOLD, 13));
		valorTotalCompra.setBounds(410, 367, 65, 14);
		contentPane.add(valorTotalCompra);
		
		JButton btnNewButton_1 = new JButton("Confirmar compra");
		btnNewButton_1.setIcon(new ImageIcon(TelaNovaCompra.class.getResource("/view/iconeConfirmacao.png")));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton_1.setBounds(10, 403, 169, 57);
		contentPane.add(btnNewButton_1);
		
		
		
		comboBoxParcelas = new JComboBox<Object>();
		comboBoxParcelas.setBounds(75, 330, 169, 23);
		contentPane.add(comboBoxParcelas);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setIcon(new ImageIcon(TelaNovaCompra.class.getResource("/view/iconesair (1).png")));
		btnVoltar.setBounds(220, 403, 169, 57);
		btnVoltar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		contentPane.add(btnVoltar);
		
		caixaDesconto = new JTextField();
		caixaDesconto.setBounds(410, 330, 37, 23);
		contentPane.add(caixaDesconto);
		caixaDesconto.setEnabled(false);
		caixaDesconto.setEditable(false);
		caixaDesconto.setText("0");
		caixaDesconto.setColumns(10);
		
		JLabel labelPorcento = new JLabel("%");
		labelPorcento.setFont(new Font("Tahoma", Font.BOLD, 13));
		labelPorcento.setBounds(450, 330, 46, 23);
		labelPorcento.setEnabled(false);
		contentPane.add(labelPorcento);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Desconto");
		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.BOLD, 13));
		chckbxNewCheckBox.setBounds(275, 330, 87, 23);
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if( ((JCheckBox) e.getSource()).isSelected()) {
					caixaDesconto.setEnabled(true);
					caixaDesconto.setEditable(true);
					labelPorcento.setEnabled(true);
				}				
				else {
					caixaDesconto.setEnabled(false);
					caixaDesconto.setEditable(false);
					labelPorcento.setEnabled(false);
				}
			}
		});
		contentPane.add(chckbxNewCheckBox);
		
		
		
		JLabel lblNewLabel = new JLabel("Quantidade");
		lblNewLabel.setBounds(10, 60, 78, 14);
		contentPane.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		campoQuantidade = new JTextField();
		campoQuantidade.setBounds(10, 77, 78, 20);
		contentPane.add(campoQuantidade);
		campoQuantidade.setHorizontalAlignment(SwingConstants.CENTER);
		campoQuantidade.setText("1");
		campoQuantidade.addKeyListener(new OnlyDigits());
		campoQuantidade.setColumns(10);
		
		JLabel lblProduto = new JLabel("Produto");
		lblProduto.setBounds(122, 60, 147, 14);
		contentPane.add(lblProduto);
		lblProduto.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblProduto.setHorizontalAlignment(SwingConstants.CENTER);
		
		campoProduto = new JTextField();
		campoProduto.setBounds(122, 77, 147, 20);
		contentPane.add(campoProduto);
		campoProduto.setHorizontalAlignment(SwingConstants.CENTER);
		campoProduto.setColumns(10);
		
		JLabel lblValor = new JLabel("Valor");
		lblValor.setBounds(316, 60, 57, 14);
		contentPane.add(lblValor);
		lblValor.setHorizontalAlignment(SwingConstants.CENTER);
		lblValor.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel labelReais = new JLabel("R$");
		labelReais.setBounds(295, 80, 16, 14);
		contentPane.add(labelReais);
		labelReais.setHorizontalAlignment(SwingConstants.LEFT);
		labelReais.setFont(new Font("Tahoma", Font.BOLD, 11));
		//campoValorx = new TextField();
		//campoValorx.setBounds(316, 77, 57, 20);
		//contentPane.add(campoValorx);
		campoValorx.addKeyListener(new OnlyDigits());
		campoValorx.setColumns(10);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setBounds(410, 60, 57, 14);
		contentPane.add(lblTotal);
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel labelReais2 = new JLabel("R$");
		labelReais2.setBounds(390, 80, 16, 14);
		contentPane.add(labelReais2);
		labelReais2.setHorizontalAlignment(SwingConstants.LEFT);
		labelReais2.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		totalProduto = new JLabel("00,00");
		totalProduto.setBounds(410, 80, 57, 14);
		contentPane.add(totalProduto);
		totalProduto.setFont(new Font("Tahoma", Font.BOLD, 11));
		totalProduto.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblVencimento = new JLabel("Vencimento:");
		lblVencimento.setBounds(10, 368, 59, 14);
		contentPane.add(lblVencimento);
		
		vencimento = null;
		try {
			vencimento = new JFormattedTextField(new MaskFormatter("##/##/####"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		vencimento.setBounds(75, 365, 65, 20);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		String day = String.format("%02d", cal.get(Calendar.DATE));
		String month = String.format("%02d", cal.get(Calendar.MONTH) + 1);
		String year = Integer.toString(cal.get(Calendar.YEAR));
		vencimento.setText(day + month + year);
		contentPane.add(vencimento);
		
		JCheckBox chckbxVista = new JCheckBox("\u00C0 vista");
		chckbxVista.setBounds(10, 330, 59, 23);
		chckbxVista.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(comboBoxParcelas.isEnabled()) {
					comboBoxParcelas.setEnabled(false);
					vencimento.setEnabled(false);
				}
				else {
					comboBoxParcelas.setEnabled(true);
					vencimento.setEnabled(true);
				}
			}
		});
		contentPane.add(chckbxVista);
		
		
	}
	
	public class AddNewItem implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			produtos[currentLine][0] = campoQuantidade.getText();
			produtos[currentLine][1] = campoProduto.getText();
			produtos[currentLine][2] = campoValorx.getText();
			produtos[currentLine][3] = totalProduto.getText();
			String[] columnNames = {"Quantidade", "Produto", "Valor unit. (R$)", "Total (R$)"};
			tabelaProdutos = new JTable(produtos, columnNames);
			tabelaProdutos.setShowGrid(false);
			scrollPane.setViewportView(tabelaProdutos);
			Font cabecalho = new Font("SansSerif", Font.BOLD, 12);
			tabelaProdutos.getTableHeader().setFont(cabecalho);
			tabelaProdutos.setRowSelectionAllowed(false);
			tabelaProdutos.setEnabled(false);
			tabelaProdutos.setShowGrid(false);
			campoValorx.setText("");
			campoQuantidade.setText("1");
			campoProduto.setText("");
			totalProduto.setText("00,00");
			int valorTotal = 0;
			for(int i=0;i<30;i++)
				if(produtos[i][3] != null)
				valorTotal += Integer.parseInt((String) produtos[i][3]);
			valorTotalCompra.setText(Integer.toString(valorTotal));
			currentLine++;
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
			if(campoValorx.getText().equals("") || campoQuantidade.getText().equals(""))
				return;
			int valor = Integer.parseInt(campoValorx.getText());
			int total = Integer.parseInt(campoQuantidade.getText());
			totalProduto.setText(Integer.toString(valor * total));
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			char c = e.getKeyChar();
			if(!(Character.isDigit(c)|| c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE )){
				e.consume();
			}
			
		}
		
	}
}
