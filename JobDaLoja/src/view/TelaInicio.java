package view;

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
import java.awt.Color;
import java.awt.Dimension;

public class TelaInicio extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	URL url;
	ImageIcon icone;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
		contentPane = new JPanel();
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
		botaoCliente.setBounds(100, 156, 200, 70);
		botaoCliente.addActionListener(new CadastroClienteAction());
		JButton botaoSair = new JButton("     Fechar   ", icone);
		botaoSair.setBounds(100, 396, 200, 70);
		botaoSair.addActionListener(new SairAction());
		contentPane.setLayout(null);
		contentPane.add(botaoSair);
		contentPane.add(botaoCliente);
		JButton botaoBuscarCliente = new JButton("Buscar cliente", new ImageIcon(TelaInicio.class.getResource("/view/1491380374_magnifyingglass.png")));
		botaoBuscarCliente.setBounds(100, 236, 200, 70);
		botaoBuscarCliente.addActionListener(new ProcurarClienteAction());
		contentPane.add(botaoBuscarCliente);
		JButton botaoVencimentos = new JButton("Vencimentos", new ImageIcon(TelaInicio.class.getResource("/view/iconeVencimento.png")));
		botaoVencimentos.setBounds(100, 316, 200, 70);
		botaoVencimentos.addActionListener(new VisualizarVencimentosAction());
		contentPane.add(botaoVencimentos);
	}
	
	private class CadastroClienteAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent evento) {
			TelaNovoCliente frame = new TelaNovoCliente();
			frame.setVisible(true);
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
			TelaCliente frame = null;
			try {
				try {
					frame = new TelaCliente("6891886503");
				} catch (ClassNotFoundException e) {
					JOptionPane.showMessageDialog(null, "JDBC não instalado");
					e.printStackTrace();
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Ocorreu um erro ao tentar acessar o banco de dados");
				e.printStackTrace();
			}
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
}
