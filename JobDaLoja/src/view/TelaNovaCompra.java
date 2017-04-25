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
		campoQuantidade.setColumns(10);
		
		campoProduto = new JTextField();
		campoProduto.setHorizontalAlignment(SwingConstants.CENTER);
		campoProduto.setColumns(10);
		campoProduto.setBounds(110, 33, 147, 20);
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
		campoValor.setBounds(305, 33, 57, 20);
		campoValor.setColumns(10);
		
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
		
		JButton btnX = new JButton("");
		btnX.setIcon(new ImageIcon(TelaNovaCompra.class.getResource("/view/1493126292_Artboard_1.png")));
		btnX.setBounds(467, 33, 40, 20);
		painelInterno.add(btnX);
		
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
		
		JLabel labelTotal = new JLabel("00,00");
		labelTotal.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelTotal.setHorizontalAlignment(SwingConstants.CENTER);
		labelTotal.setBounds(400, 36, 57, 14);
		painelInterno.add(labelTotal);
		
		JButton btnNewButton = new JButton("Novo item");
		btnNewButton.setIcon(new ImageIcon(TelaNovaCompra.class.getResource("/view/1493128794_flat-style-circle-add.png")));
		btnNewButton.setBounds(550, 42, 134, 51);
		btnNewButton.addActionListener(new AddNewItem());
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Total da Compra:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(275, 333, 114, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("R$ 00,00");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(410, 333, 65, 14);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton_1 = new JButton("Confirmar compra");
		btnNewButton_1.setIcon(new ImageIcon(TelaNovaCompra.class.getResource("/view/1493128621_Checkmark.png")));
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
			
			campoQuantidade = new JTextField();
			campoQuantidade.setHorizontalAlignment(SwingConstants.CENTER);
			campoQuantidade.setText("-");
			campoQuantidade.setBounds(110, currenty, 147, 20);
			painelInterno.add(campoQuantidade);
			campoQuantidade.setColumns(10);
			
			
			
		}
		
	}
}
