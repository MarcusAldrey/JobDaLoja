package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controller.Controller;


public class TelaListarClientes extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField textFieldNomeCPF;

	/**
	 * Create the panel.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public TelaListarClientes() throws ClassNotFoundException, SQLException {
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nome/CPF:");
		lblNewLabel.setBounds(10, 44, 59, 14);
		add(lblNewLabel);
		
		JRadioButton rdbtnProcuraPorNome = new JRadioButton("Procura por nome");
		rdbtnProcuraPorNome.setBackground(Color.LIGHT_GRAY);
		rdbtnProcuraPorNome.setSelected(true);
		rdbtnProcuraPorNome.setBounds(79, 11, 111, 23);
		add(rdbtnProcuraPorNome);
		
		JRadioButton rdbtnProcurarPorCPF = new JRadioButton("Procura por CPF");
		rdbtnProcurarPorCPF.setBackground(Color.LIGHT_GRAY);
		rdbtnProcurarPorCPF.setBounds(192, 11, 109, 23);
		add(rdbtnProcurarPorCPF);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtnProcuraPorNome);
		buttonGroup.add(rdbtnProcurarPorCPF);
		
		
		String[] columnNames = {"Nome", "CPF", "Telefone"};
		Object[][] valores = new Object[2][3];
		ResultSet rs = Controller.getClientes();
		int cont = 0;
		while(rs.next()) {
			for(int y = 0; y<3; y++)
				valores[cont][y] = rs.getString(y+1);
			cont++;
		}
				
		JButton btnNewButton = new JButton("Voltar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(10, 312, 111, 38);
		add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 69, 564, 232);
		add(scrollPane);
		table = new JTable(valores,columnNames);
		table.setEnabled(false);
		Font cabecalho = new Font("SansSerif", Font.BOLD + Font.ITALIC, 12);
		table.getTableHeader().setFont(cabecalho);
		scrollPane.setViewportView(table);
		
		textFieldNomeCPF = new JTextField();
		textFieldNomeCPF.setBounds(68, 41, 506, 20);
		add(textFieldNomeCPF);
		textFieldNomeCPF.setColumns(10);
		
		JDialog janela = new JDialog();
		int largura = 600;
		int altura = 400;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screen.width-largura)/2;
		int y = (screen.height-altura)/2;
		janela.setBounds(x,y,largura,altura);
		janela.setVisible(true);
		janela.getContentPane().add(this);
		janela.setTitle("La Victoria - Procurar cliente");
	}
}
