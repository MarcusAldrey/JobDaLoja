package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;

import controller.Controller;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.JFormattedTextField;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

public class TelaNovaCompra extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Object[][] valoresDaTabela;
	private JTextField campoQuantidade;
	private JTextField campoProduto;
	JPanel painelInterno;
	JComboBox<?> comboBoxParcelas;
	int currenty = 33;
	int currentLine = 1;
	private JTextField caixaDesconto;
	Object[][] produtos;

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
		
		produtos = new Object[8][4];
		
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 42, 530, 271);
		contentPane.add(scrollPane);
		
		painelInterno = new JPanel();
		scrollPane.setViewportView(painelInterno);
		painelInterno.setBorder(new CompoundBorder());
		painelInterno.setLayout(null);
		
		campoQuantidade = new JTextField();
		campoQuantidade.setHorizontalAlignment(SwingConstants.CENTER);
		campoQuantidade.setText("1");
		campoQuantidade.setBounds(10, 33, 78, 20);
		painelInterno.add(campoQuantidade);
		campoQuantidade.addKeyListener(new OnlyDigits());
		produtos[0][0] = campoQuantidade;
		campoQuantidade.setColumns(10);
		
		campoProduto = new JTextField();
		campoProduto.setHorizontalAlignment(SwingConstants.CENTER);
		campoProduto.setColumns(10);
		campoProduto.setBounds(110, 33, 147, 20);
		produtos[0][1] = campoProduto;
		painelInterno.add(campoProduto);
		
		JLabel lblNewLabel = new JLabel("Quantidade");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 5, 78, 14);
		painelInterno.add(lblNewLabel);
		
		NumberFormat amountFormat = NumberFormat.getNumberInstance();
		NumberFormatter formatter = new NumberFormatter(amountFormat);
		
		JLabel lblProduto = new JLabel("Produto");
		lblProduto.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblProduto.setHorizontalAlignment(SwingConstants.CENTER);
		lblProduto.setBounds(110, 5, 147, 14);
		painelInterno.add(lblProduto);
		JFormattedTextField campoValor = new JFormattedTextField(formatter);
		campoValor.setHorizontalAlignment(SwingConstants.CENTER);
		campoValor.addKeyListener(new OnlyDigits());
		campoValor.setBounds(305, 33, 57, 20);
		campoValor.setColumns(10);
		produtos[0][2] = campoValor;
		painelInterno.add(campoValor);
		
		JLabel lblValor = new JLabel("Valor");
		lblValor.setHorizontalAlignment(SwingConstants.CENTER);
		lblValor.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblValor.setBounds(305, 5, 57, 14);
		painelInterno.add(lblValor);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTotal.setBounds(400, 5, 57, 14);
		painelInterno.add(lblTotal);
		
		JLabel labelReais = new JLabel("R$");
		labelReais.setHorizontalAlignment(SwingConstants.LEFT);
		labelReais.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelReais.setBounds(284, 36, 16, 14);
		painelInterno.add(labelReais);
		
		JLabel labelReais2 = new JLabel("R$");
		labelReais2.setHorizontalAlignment(SwingConstants.LEFT);
		labelReais2.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelReais2.setBounds(379, 36, 16, 14);
		painelInterno.add(labelReais2);
		
		JLabel totalProduto = new JLabel("00,00");
		totalProduto.setFont(new Font("Tahoma", Font.BOLD, 11));
		totalProduto.setHorizontalAlignment(SwingConstants.CENTER);
		totalProduto.setBounds(400, 36, 57, 14);
		produtos[0][3] = totalProduto;
		painelInterno.add(totalProduto);
		
		JButton btnNewButton = new JButton("Novo item");
		btnNewButton.setIcon(new ImageIcon(TelaNovaCompra.class.getResource("/view/1493128794_flat-style-circle-add.png")));
		btnNewButton.setBounds(550, 42, 134, 51);
		btnNewButton.addActionListener(new AddNewItem());
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Total da Compra:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(275, 367, 114, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel valorTotalCompra = new JLabel("R$ 00,00");
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
		
		JCheckBox chckbxVista = new JCheckBox("\u00C0 vista");
		chckbxVista.setBounds(10, 330, 59, 23);
		chckbxVista.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(comboBoxParcelas.isEnabled())
					comboBoxParcelas.setEnabled(false);
				else
					comboBoxParcelas.setEnabled(true);
			}
		});
		contentPane.add(chckbxVista);
		
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
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Desconto");
		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.BOLD, 13));
		chckbxNewCheckBox.setBounds(275, 330, 87, 23);
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if( ((JCheckBox) e.getSource()).isSelected()) {
					caixaDesconto.setEnabled(true);
					caixaDesconto.setEditable(true);
				}				
				else {
					caixaDesconto.setEnabled(false);
					caixaDesconto.setEditable(false);
				}
			}
		});
		contentPane.add(chckbxNewCheckBox);
		
		JLabel label = new JLabel("%");
		label.setFont(new Font("Tahoma", Font.BOLD, 13));
		label.setBounds(450, 330, 46, 23);
		contentPane.add(label);
	}
	
	public class AddNewItem implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			currenty += 30;
			
			campoQuantidade = new JTextField();
			campoQuantidade.setHorizontalAlignment(SwingConstants.CENTER);
			campoQuantidade.setText("1");
			campoQuantidade.setBounds(10, currenty, 78, 20);
			painelInterno.add(campoQuantidade);
			campoQuantidade.setColumns(10);
			produtos[currentLine][0] = campoQuantidade;
			
			campoProduto = new JTextField();
			campoProduto.setHorizontalAlignment(SwingConstants.CENTER);
			campoProduto.setVisible(true);
			campoProduto.setBounds(110, currenty, 147, 20);
			painelInterno.add(campoProduto);
			campoProduto.setColumns(10);
			produtos[currentLine][1] = campoProduto;
			
			JLabel labelReais = new JLabel("R$");
			labelReais.setHorizontalAlignment(SwingConstants.LEFT);
			labelReais.setFont(new Font("Tahoma", Font.BOLD, 11));
			labelReais.setBounds(284, currenty-15, 16, 14);
			labelReais.setVisible(true);
			painelInterno.add(labelReais);
			labelReais.setSize(new Dimension(25,50));
			
			JTextField campoValor = new JTextField();
			campoValor.setHorizontalAlignment(SwingConstants.CENTER);
			campoValor.setVisible(true);
			campoValor.setBounds(305, currenty, 57, 20);
			painelInterno.add(campoValor);
			campoValor.setColumns(10);
			produtos[currentLine][2] = campoValor;
			
			JLabel labelReais2 = new JLabel("R$");
			labelReais2.setHorizontalAlignment(SwingConstants.LEFT);
			labelReais2.setFont(new Font("Tahoma", Font.BOLD, 11));
			labelReais2.setBounds(379, currenty-15, 16, 14);
			labelReais2.setVisible(true);
			painelInterno.add(labelReais2);
			labelReais2.setSize(new Dimension(25,50));
			
			JLabel labelTotal = new JLabel("00,00");
			labelTotal.setFont(new Font("Tahoma", Font.BOLD, 11));
			labelTotal.setHorizontalAlignment(SwingConstants.CENTER);
			labelTotal.setBounds(400, currenty-15, 57, 14);
			painelInterno.add(labelTotal);
			labelTotal.setSize(new Dimension(57,50));
			labelTotal.setVisible(true);
			produtos[currentLine][3] = labelTotal;
			
			
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
			if(!(Character.isDigit(c)|| c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE )){
				e.consume();
			}
		}
		
	}
	
	
}
