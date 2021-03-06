package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

@SuppressWarnings("serial")

public class TelaPrincipal extends JFrame{

	URL url;
	ImageIcon icone;

	public TelaPrincipal() {
		super("La Victoria");
		criarMenu();
		criarpainelBotoes();
	}

	public void criarMenu() {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		JMenu menuArquivo = new JMenu("Cadastrar");

		//Cria item de cadastro de cliente
		JMenuItem menuItemCliente = new JMenuItem("Cliente", icone);
		menuItemCliente.addActionListener(new CadastroClienteAction());
		menuArquivo.add(menuItemCliente);
	
		//Cria menu de consulta
		JMenu menuVencimento = new JMenu("Vencimentos");

		//Cria item de nova consulta
		JMenuItem menuItemVencimentos = new JMenuItem("Vencimentos", icone);
		menuItemVencimentos.addActionListener(new VisualizarVencimentosAction());
		menuVencimento.add(menuItemVencimentos);

		menuArquivo.addSeparator();

		//Cria item de sair
		JMenuItem menuItemSair = new JMenuItem("Sair", icone);
		menuItemSair.addActionListener(new SairAction());
		menuArquivo.add(menuItemSair);

		//Cria barra de menu
		JMenuBar barraDeMenu = new JMenuBar();
		this.setJMenuBar(barraDeMenu);

		//insere os menus com itens na barra de menu
		//barraDeMenu.add(menuArquivo);
		//barraDeMenu.add(menuVencimento);

	}

	public void criarpainelBotoes() {

		this.setLayout(new FlowLayout(100,100,50));

		//Cria bot�o de cadastro de cliente
		url = this.getClass().getResource("iconeCliente.png");
		icone = new ImageIcon(url);
		JButton botaoCliente = new JButton("    Novo cliente", icone);
		botaoCliente.addActionListener(new CadastroClienteAction());

		//Cria bot�o de procurar cliente
		url = this.getClass().getResource("1491380374_magnifyingglass.png");
		icone = new ImageIcon(url);
		JButton botaoCompra = new JButton("    Buscar cliente", icone);
		botaoCompra.addActionListener(new ProcurarClienteAction());

		//Cria bot�o de visualizar vencimentos
		url = this.getClass().getResource("iconeVencimento.png");
		icone = new ImageIcon(url);
		JButton botaoVencimentos = new JButton("    Vencimentos", icone);
		botaoVencimentos.addActionListener(new VisualizarVencimentosAction());
		
		//Cria bot�o de sair
		url = this.getClass().getResource("iconeSair.png");
		icone = new ImageIcon(url);
		JButton botaoSair = new JButton("   Fechar       ", icone);
		botaoSair.addActionListener(new SairAction());
		
		
		

		//insere os bot�es no frame
		this.add(botaoCliente);
		this.add(botaoCompra);	
		this.add(botaoVencimentos);
		this.add(botaoSair);
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
					JOptionPane.showMessageDialog(null, "JDBC n�o instalado");
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
				JOptionPane.showMessageDialog(null, "JDBC n�o instalado");
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
