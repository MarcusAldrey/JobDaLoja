package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;


public class TelaPagamentos extends TelaCadastro {

	private JTextField nome;
	private JTextField idade;
	private JFormattedTextField cpfDoDono;
	private JComboBox<String> especies;
	private JTextField cor;


	public TelaPagamentos() {
		super();
		botaoAdicionar.addActionListener(new AdicionarAction());
		botaoLimpar.addActionListener(new LimparAction());		
		botaoVisualizar.addActionListener(new VisualizarAction());
	}

	@Override
	protected void inserirCampos() {
		JPanel painel = new JPanel();
		painel.setLayout(new FlowLayout(20,10,50));		
		painel.setBorder(BorderFactory.createLineBorder(Color.black));

		//Cria campo de nome
		JLabel label = new JLabel("Nome:");
		painel.add(label);
		nome = new JTextField(30);
		painel.add(nome);

		//cria campo de idade do Animal
		label = new JLabel("Idade:");
		painel.add(label);
		idade = new JTextField();
		idade.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				char c = arg0.getKeyChar();

				if(!(Character.isDigit(c)|| c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE )){
					arg0.consume();
				}
			}
		});
		idade.setPreferredSize(new Dimension(30, 20));
		painel.add(idade);

		//Cria campo de CPF do dono
		label = new JLabel("CPF do dono:");
		painel.add(label);
		try {
			cpfDoDono = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cpfDoDono.setPreferredSize(new Dimension(100,20));
		painel.add(cpfDoDono);

		//cria campo de cor do Animal
		label = new JLabel("   Cor:");
		painel.add(label);
		cor = new JTextField(10);
		painel.add(cor);

		//Cria ComboBox de esp�cie
		painel.add(new JLabel("Esp�cie"));
		String[] tipos = new String[4];
		tipos[0] = "Gato";
		tipos[1] = "Cachorro";
		tipos[2] = "Cavalo";
		tipos[3] = "Outro";
		especies = new JComboBox<String>(tipos);
		painel.add(especies);

		janela.add(painel, BorderLayout.CENTER);
	}

	public class LimparAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			nome.setText("");
			cpfDoDono.setText("");
			cor.setText("");
			idade.setText("");
			especies.setSelectedIndex(0);
		}

	}

	public class AdicionarAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(null, "Compra registrada com sucesso!");
		}

	}

	public class VisualizarAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			criarTelaDeVisualizacao();
			JTextArea texto = new JTextArea();
			texto.setBackground(Color.CYAN);
			texto.append("---NOVO ANIMAL---\n");
			texto.append("Nome: " + nome.getText());
			texto.append("\nCPF do dono: " + cpfDoDono.getText());
			texto.append("\nIdade do animal: " + idade.getText());
			texto.append("\nEsp�cie: " + especies.getSelectedItem());
			texto.append("\nCor: " + cor.getText());
			visualizacao.add(texto, BorderLayout.CENTER);
		}

	}	
}

