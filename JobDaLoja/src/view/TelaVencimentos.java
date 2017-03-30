package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import model.Endereco;


public class TelaVencimentos extends TelaCadastro {

	private JTextField nome;
	private JFormattedTextField cpf;
	private JFormattedTextField dataDeNascimento;
	private JTextField crmv;
	private JTextField rua;
	private JTextField bairro;
	private JFormattedTextField telefone;
	private JTextField cidade;
	private JTextField NumdaCasa;
	private JComboBox<String> estado;
	private JComboBox<String> sexo;
	
	public TelaVencimentos() {
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

		//Cria campo de CPF
		label = new JLabel("CPF:");
		painel.add(label);
		try {
			cpf = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cpf.setPreferredSize(new Dimension(100,20));
		painel.add(cpf);

		//Cria campo sexo
		label = new JLabel("Sexo:");
		painel.add(label);
		String[] s = {"Masculino", "Feminino"};
		sexo = new JComboBox<String>(s);
		painel.add(sexo);

		//cria campo de data de nascimento
		label = new JLabel("Data de Nascimento:");
		painel.add(label);
		try {
			dataDeNascimento = new JFormattedTextField(new MaskFormatter("##/##/####"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		dataDeNascimento.setPreferredSize(new Dimension(75,20));
		painel.add(dataDeNascimento);

		//Cria campo de crmv
		label = new JLabel("CRMV:");
		painel.add(label);
		crmv = new JTextField(20);
		painel.add(crmv);

		//cria campo de telefone
		label = new JLabel("Telefone:");
		painel.add(label);
		try {
			telefone = new JFormattedTextField(new MaskFormatter("(##)####-####"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		telefone.setPreferredSize(new Dimension(85,20));
		painel.add(telefone);


		//cria campo de cidade
		label = new JLabel("Cidade:");
		painel.add(label);
		cidade = new JTextField(20);
		painel.add(cidade);

		//cria campo de rua
		label = new JLabel("Rua:");
		painel.add(label);
		rua = new JTextField(15);
		painel.add(rua);

		//cria campo de bairro
		label = new JLabel("Bairro:");
		painel.add(label);
		bairro = new JTextField(15);
		painel.add(bairro);

		//cria campo de n�mero da casa
		label = new JLabel("N�:");
		painel.add(label);
		NumdaCasa = new JTextField(4);
		painel.add(NumdaCasa);


		//Cria ComboBox de estados
		painel.add(new JLabel("Estado:"));
		String[] listaDeEstados = Endereco.listarEstados();
		estado = new JComboBox<String>(listaDeEstados);
		painel.add(estado);

		janela.add(painel, BorderLayout.CENTER);
	}

	public class LimparAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			nome.setText("");
			cpf.setText("");
			dataDeNascimento.setText("");
			rua.setText("");
			bairro.setText("");
			telefone.setText("");
			cidade.setText("");
			NumdaCasa.setText("");
			crmv.setText("");
			estado.setSelectedIndex(0);
			sexo.setSelectedIndex(0);
		}

	}

	public class AdicionarAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(null, "Veterin�rio cadastrado com sucesso!");
		}

	}

	public class VisualizarAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			criarTelaDeVisualizacao();
			JTextArea texto = new JTextArea();
			texto.setBackground(Color.CYAN);
			texto.append("---NOVO VETERIN�RIO---\n");
			texto.append("Nome do Veterin�rio: " + nome.getText());
			texto.append("\nSexo: " + sexo.getSelectedItem());
			texto.append("\nCPF: " + cpf.getText());
			texto.append("\nN�mero do CRMV: " + crmv.getText());
			texto.append("\nTelefone: " + telefone.getText());
			texto.append("\nData de nascimento: " + dataDeNascimento.getText());
			texto.append("\n\nENDERE�O");
			texto.append("\nRua: " + rua.getText());
			texto.append("\nN�mero da casa: " + NumdaCasa.getText());
			texto.append("\nBairro: " + bairro.getText());
			texto.append("\nEstado: " + estado.getSelectedItem());
			texto.append("\nCidade: " + cidade.getText());
			visualizacao.add(texto, BorderLayout.CENTER);
		}

	}	
}
