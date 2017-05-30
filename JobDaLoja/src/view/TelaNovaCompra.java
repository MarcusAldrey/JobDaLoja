package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Calendar;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import controller.Controller;
import javax.swing.JToggleButton;

public class TelaNovaCompra extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Object[][] valoresDaTabela;
	private JTextField campoQuantidade;
	private JTextField campoProduto;
	JComboBox<String> comboBoxParcelasCrediario;
	int currenty = 33;
	int currentLine = 0;
	private JTextField caixaDesconto;
	JFormattedTextField vencimento;
	private JFormattedTextField vencimento_1;
	Object[][] produtos;
	private JTable tabelaProdutos;
	String[] columnNames;
	TextField campoValor;
	JLabel totalProduto;
	JScrollPane scrollPane;
	private JTextField campoValorx;
	JLabel valorSubTotalCompra;
	float valorSubTotal;
	float valorTotalProduto;
	float desconto;
	float valorTotalCompra;
	JRadioButton rdbtnCredirio;
	JRadioButton rdbtnRadioButtonVista;
	JRadioButton rdbtnCarto;
	JComboBox<Object> comboBoxParcelaCarto;
	JLabel lblVencimento;
	JLabel lblValorDesconto;
	JLabel lblvalorTotalCompra;
	float[] valorDeParcelas = new float[6];

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
		scrollPane.setBounds(10, 108, 512, 205);
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

		JButton btnAdicionar = new JButton("Adicionar item");
		btnAdicionar.setIcon(new ImageIcon(TelaNovaCompra.class.getResource("/view/1493128794_flat-style-circle-add.png")));
		btnAdicionar.setBounds(532, 107, 152, 57);
		btnAdicionar.addActionListener(new AddNewItem());
		contentPane.add(btnAdicionar);

		JLabel lblNewLabel_1 = new JLabel("Subtotal:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(275, 367, 114, 14);
		contentPane.add(lblNewLabel_1);

		valorSubTotalCompra = new JLabel("R$ 00,00");
		valorSubTotalCompra.setFont(new Font("Tahoma", Font.BOLD, 13));
		valorSubTotalCompra.setBounds(410, 367, 86, 14);
		contentPane.add(valorSubTotalCompra);

		JButton btnFinalizarCompra = new JButton("Finalizar");
		btnFinalizarCompra.setIcon(new ImageIcon(TelaNovaCompra.class.getResource("/view/iconeConfirmacao.png")));
		btnFinalizarCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnFinalizarCompra.setBounds(532, 403, 152, 57);
		contentPane.add(btnFinalizarCompra);



		comboBoxParcelasCrediario = new JComboBox<String>();
		comboBoxParcelasCrediario.setEnabled(false);
		comboBoxParcelasCrediario.setBounds(83, 403, 169, 23);
		contentPane.add(comboBoxParcelasCrediario);

		JButton btnVoltar = new JButton("Cancelar");
		btnVoltar.setIcon(new ImageIcon(TelaNovaCompra.class.getResource("/view/1493125655_Artboard_1.png")));
		btnVoltar.setBounds(532, 330, 152, 57);
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
		caixaDesconto.addKeyListener(new DescontoListener());

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
					caixaDesconto.setText("0");
					atualizarTotaleSubTotal();				
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

		lblVencimento = new JLabel("Primeiro vencimento em:");
		lblVencimento.setEnabled(false);
		lblVencimento.setBounds(15, 440, 131, 14);
		contentPane.add(lblVencimento);

		vencimento = null;
		try {
			vencimento_1 = new JFormattedTextField(new MaskFormatter("##/##/####"));
			vencimento_1.setEnabled(false);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		vencimento_1.setBounds(138, 437, 65, 20);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		String day = String.format("%02d", cal.get(Calendar.DATE));
		String month = String.format("%02d", cal.get(Calendar.MONTH) + 1);
		String year = Integer.toString(cal.get(Calendar.YEAR));
		vencimento_1.setText(day + month + year);
		contentPane.add(vencimento_1);


		JLabel lblFormaDePagamento = new JLabel("Forma de pagamento:");
		lblFormaDePagamento.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFormaDePagamento.setBounds(10, 324, 169, 14);
		contentPane.add(lblFormaDePagamento);



		JLabel lblTotal_1 = new JLabel("Total:");
		lblTotal_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTotal_1.setBounds(275, 424, 114, 14);
		contentPane.add(lblTotal_1);

		lblvalorTotalCompra = new JLabel("R$ 00,00");
		lblvalorTotalCompra.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblvalorTotalCompra.setBounds(410, 424, 86, 14);
		contentPane.add(lblvalorTotalCompra);

		lblValorDesconto = new JLabel("R$ 00,00");
		lblValorDesconto.setForeground(new Color(0, 153, 0));
		lblValorDesconto.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblValorDesconto.setBounds(410, 392, 86, 14);
		contentPane.add(lblValorDesconto);

		JSeparator separator = new JSeparator();
		separator.setBounds(275, 414, 192, 10);
		contentPane.add(separator);

		JLabel label_2 = new JLabel("-");
		label_2.setForeground(new Color(0, 153, 0));
		label_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		label_2.setBounds(383, 391, 23, 14);
		contentPane.add(label_2);

		rdbtnRadioButtonVista = new JRadioButton("\u00C0 vista");
		rdbtnRadioButtonVista.setBounds(10, 341, 70, 23);
		contentPane.add(rdbtnRadioButtonVista);

		ButtonGroup formaDePagamento = new ButtonGroup();

		rdbtnCarto = new JRadioButton("Cart\u00E3o");
		rdbtnCarto.setBounds(10, 367, 65, 23);
		contentPane.add(rdbtnCarto);

		String parcelas[] = {" 1X", " 2X", " 3X", " 4X", " 5X", " 6X", " 7X", " 8X", " 9X"};
		comboBoxParcelaCarto = new JComboBox<Object>(parcelas);
		comboBoxParcelaCarto.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBoxParcelaCarto.setEnabled(false);
		comboBoxParcelaCarto.setBounds(83, 368, 57, 23);		
		contentPane.add(comboBoxParcelaCarto);

		rdbtnCredirio = new JRadioButton("Credi\u00E1rio");
		rdbtnCredirio.setBounds(10, 403, 70, 23);
		contentPane.add(rdbtnCredirio);

		formaDePagamento.add(rdbtnCredirio);
		formaDePagamento.add(rdbtnCarto);
		formaDePagamento.add(rdbtnRadioButtonVista);

		rdbtnCredirio.addActionListener(new FormaDePagamentoAction());
		rdbtnCarto.addActionListener(new FormaDePagamentoAction());
		rdbtnRadioButtonVista.addActionListener(new FormaDePagamentoAction());

	}

	public class AddNewItem implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(!campoProduto.getText().equals("") && !campoValorx.getText().equals("")) {
				produtos[currentLine][0] = campoQuantidade.getText();
				produtos[currentLine][1] = campoProduto.getText();
				produtos[currentLine][2] = campoValorx.getText();
				produtos[currentLine][3] = Float.toString(valorTotalProduto);
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
				valorSubTotal = 0;
				valorTotalProduto = 0;
				for(int i=0;i<30;i++)
					if(produtos[i][3] != null)
						valorSubTotal += Float.parseFloat((String) produtos[i][3]);
				atualizarTotaleSubTotal();
				currentLine++;
			}
		}

	}

	public class DescontoListener implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			atualizarTotaleSubTotal();
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			char c = e.getKeyChar();
			if(!(Character.isDigit(c)|| c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE))
				e.consume();
		}

	}

	public class FormaDePagamentoAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			comboBoxParcelaCarto.setEnabled(false);
			comboBoxParcelasCrediario.setEnabled(false);
			vencimento_1.setEnabled(false);
			vencimento_1.setEditable(false);
			lblVencimento.setEnabled(false);
			if(rdbtnCarto.isSelected())
				comboBoxParcelaCarto.setEnabled(true);
			else if(rdbtnCredirio.isSelected()) {
				comboBoxParcelasCrediario.setEnabled(true);
				vencimento_1.setEnabled(true);
				vencimento_1.setEditable(true);
				lblVencimento.setEnabled(true);
			}
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
			float valor = Float.parseFloat(campoValorx.getText());
			valorTotalProduto = Float.parseFloat(campoQuantidade.getText()) * valor;
			totalProduto.setText(String.format("%.2f", valorTotalProduto));
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
	
	public void atualizarTotaleSubTotal() {
		valorSubTotalCompra.setText("R$ " + String.format("%.2f", valorSubTotal));
		desconto = (valorSubTotal*Float.parseFloat(caixaDesconto.getText()))/100;
		lblValorDesconto.setText("R$ " + String.format("%.2f", desconto));
		valorTotalCompra = valorSubTotal - desconto;
		lblvalorTotalCompra.setText("R$ " + String.format("%.2f", valorTotalCompra));
		atualizarParcelasCrediario();
	}
		
	public void atualizarParcelasCrediario() {
		comboBoxParcelasCrediario.removeAllItems();
		for(int i = 0; i < 6; i++) {
			valorDeParcelas[i] = valorTotalCompra/(i+1);
			String parcela = i+1 + "X de R$ " + String.format("%.2f", valorTotalCompra/(i+1));
			comboBoxParcelasCrediario.addItem(parcela);
		}
	}
}
