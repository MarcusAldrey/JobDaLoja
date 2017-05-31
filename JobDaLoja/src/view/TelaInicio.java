package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import java.awt.Label;

public class TelaInicio extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLayeredPane contentPane;
	URL url;
	ImageIcon icone;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//SplashScreen splash = new SplashScreen(5000);
		//splash.mostrarSplashESair();
		try {
			new Controller();
		} catch (ClassNotFoundException e1) {
			JOptionPane.showMessageDialog(null, "JDBC não instalado");
			e1.printStackTrace();
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "Arquivo de banco de dados não encontrado");
			e1.printStackTrace();
			return;
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInicio frame = new TelaInicio();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaInicio() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaInicio.class.getResource("/view/logo.jpg")));
		setTitle("La Victoria Boutique");
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screen.width-400)/2;
		int y = (screen.height-600)/2;
		setBounds(x, y, 400, 600);
		contentPane = new JLayeredPane();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		//Cria botão de cadastro de cliente
		url = this.getClass().getResource("iconeCliente.png");
		icone = new ImageIcon(url);

		//Cria botão de procurar cliente
		url = this.getClass().getResource("1491380374_magnifyingglass.png");
		icone = new ImageIcon(url);

		//Cria botão de visualizar vencimentos
		url = this.getClass().getResource("iconeVencimento.png");
		icone = new ImageIcon(url);

		//Cria botão de sair
		url = this.getClass().getResource("iconeSair.png");
		icone = new ImageIcon(url);
		JButton botaoCliente = new JButton("Novo cliente", new ImageIcon(TelaInicio.class.getResource("/view/iconeCliente.png")));
		botaoCliente.setBounds(101, 125, 200, 70);
		botaoCliente.addActionListener(new CadastroClienteAction());
		JButton botaoSair = new JButton("     Fechar   ", icone);
		botaoSair.setBounds(101, 449, 200, 70);
		botaoSair.addActionListener(new SairAction());
		contentPane.setLayout(null);
		
		JLabel NotificaoVencimento = new JLabel("");
		NotificaoVencimento.setIcon(new ImageIcon(TelaInicio.class.getResource("/view/warning.png")));
		NotificaoVencimento.setBounds(281, 273, 46, 42);
		contentPane.add(NotificaoVencimento, new Integer(2));
		
		JLabel NotificacaoNiver = new JLabel("");
		NotificacaoNiver.setIcon(new ImageIcon(TelaInicio.class.getResource("/view/warning.png")));
		NotificacaoNiver.setBounds(281, 354, 46, 42);
		contentPane.add(NotificacaoNiver, new Integer(2));
		contentPane.add(botaoSair);
		contentPane.add(botaoCliente);
		JButton botaoBuscarCliente = new JButton("Clientes", new ImageIcon(TelaInicio.class.getResource("/view/1491380374_magnifyingglass.png")));
		botaoBuscarCliente.setBounds(101, 206, 200, 70);
		botaoBuscarCliente.addActionListener(new ProcurarClienteAction());
		contentPane.add(botaoBuscarCliente);
		
		JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIcon(new ImageIcon(TelaInicio.class.getResource("/view/f65ef0d8-572b-4a47-9791-b63fa017c01e.jpg")));
		label.setBounds(10, 11, 374, 103);
		contentPane.add(label);
		JButton botaoVencimentos = new JButton("Vencimentos", new ImageIcon(TelaInicio.class.getResource("/view/iconeVencimento.png")));
		botaoVencimentos.setBounds(101, 287, 200, 70);
		botaoVencimentos.addActionListener(new VisualizarVencimentosAction());
		contentPane.add(botaoVencimentos);
		
		JButton btnAniversrios = new JButton("Aniversariantes", new ImageIcon(TelaInicio.class.getResource("/view/iconeNiver.png")));
		btnAniversrios.setBounds(101, 368, 200, 70);
		btnAniversrios.addActionListener(new AniversariosAction());
		contentPane.add(btnAniversrios);
		
	}
	
	private class CadastroClienteAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent evento) {
			TelaNovoCliente frame = new TelaNovoCliente();
			frame.setVisible(true);
		}

	}
	
	private class AniversariosAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			new TelaAniversario();
		}
		
	}

	@SuppressWarnings("unused")
	private class NovaCompraAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent evento) {
			
		}
	}

	public class VisualizarVencimentosAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent evento) {
			TelaVencimentos frame = new TelaVencimentos();
			frame.setVisible(true);
		}

	}
	
	public class ProcurarClienteAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent evento) {
			try {
				new TelaListarClientes();
			} catch (ClassNotFoundException e) {
				JOptionPane.showMessageDialog(null, "JDBC não instalado");
				e.printStackTrace();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Ocorreu um erro ao tentar acessar o banco de dados");
				e.printStackTrace();
			}
		}

	}

	private class SairAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent evento) {
			System.exit(0);
		}
	}
	
	private void atualizarTabela() {
		
	}
}
