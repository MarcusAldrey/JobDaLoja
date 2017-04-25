package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TelaVencimentos extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Create the frame.
	 */
	public TelaVencimentos() {
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
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("hoje");
		rdbtnNewRadioButton.setBounds(6, 34, 55, 23);
		contentPane.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNosPrximos = new JRadioButton("nos Pr\u00F3ximos 3 dias");
		rdbtnNosPrximos.setBounds(63, 34, 128, 23);
		contentPane.add(rdbtnNosPrximos);
		
		JRadioButton rdbtnNosPrximos_1 = new JRadioButton("nos pr\u00F3ximos 7 dias");
		rdbtnNosPrximos_1.setBounds(193, 34, 157, 23);
		contentPane.add(rdbtnNosPrximos_1);
		
		JRadioButton rdbtnMostrarComprasVencidas = new JRadioButton("Mostrar compras vencidas");
		rdbtnMostrarComprasVencidas.setBounds(6, 86, 157, 23);
		contentPane.add(rdbtnMostrarComprasVencidas);
		
		ButtonGroup buttons = new ButtonGroup();
		buttons.add(rdbtnNewRadioButton);
		buttons.add(rdbtnMostrarComprasVencidas);
		buttons.add(rdbtnNosPrximos_1);
		buttons.add(rdbtnNosPrximos);
		
		JLabel lblMostraComprasQue = new JLabel("Mostra compras que vencem");
		lblMostraComprasQue.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMostraComprasQue.setBounds(10, 11, 180, 14);
		contentPane.add(lblMostraComprasQue);
		
		JLabel lblOu = new JLabel("Ou");
		lblOu.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblOu.setBounds(10, 65, 46, 14);
		contentPane.add(lblOu);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 116, 678, 344);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
}
