package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class TelaNovoCliente extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Create the frame.
	 */
	public TelaNovoCliente() {
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 11, 46, 14);
		contentPane.add(lblNome);
		
		textField = new JTextField();
		textField.setBounds(43, 8, 295, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
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
	}
}
