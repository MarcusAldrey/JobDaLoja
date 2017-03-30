package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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

		this.setLayout(new FlowLayout(1,50,50));

		//Cria botão de cadastro de cliente
		url = this.getClass().getResource("iconeCliente.png");
		icone = new ImageIcon(url);
		JButton botaoCliente = new JButton("    Novo cliente", icone);
		botaoCliente.addActionListener(new CadastroClienteAction());

		//Cria botão de nova compra
		url = this.getClass().getResource("iconeCompra.png");
		icone = new ImageIcon(url);
		JButton botaoCompra = new JButton("    Nova compra", icone);
		botaoCompra.addActionListener(new NovaCompraAction());

		//Cria botão de visualizar vencimentos
		url = this.getClass().getResource("iconeVencimento.png");
		icone = new ImageIcon(url);
		JButton botaoVencimentos = new JButton("    Vencimentos", icone);
		botaoVencimentos.addActionListener(new VisualizarVencimentosAction());
		
		//Cria botão de sair
		url = this.getClass().getResource("iconeSair.png");
		icone = new ImageIcon(url);
		JButton botaoSair = new JButton("   Fechar       ", icone);
		botaoSair.addActionListener(new SairAction());
		
		//Cria item de pagamento
		url = this.getClass().getResource("iconePagamento.png");
		icone = new ImageIcon(url);
		JButton botaoPagamento = new JButton("     Pagamentos", icone);
		botaoPagamento.addActionListener(new SairAction());

		//insere os botões no frame
		this.add(botaoCliente);
		this.add(botaoCompra);	
		this.add(botaoVencimentos);
		this.add(botaoPagamento);
		this.add(botaoSair);
	}

	private class CadastroClienteAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent evento) {
			new TelaCadastroCliente();
		}

	}

	private class NovaCompraAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent evento) {
			
			new TelaNovaCompra();
		}
	}

	public class VisualizarVencimentosAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent evento) {
			new TelaVencimentos();
		}

	}

	private class SairAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent evento) {
			System.exit(0);
		}
	}

}
