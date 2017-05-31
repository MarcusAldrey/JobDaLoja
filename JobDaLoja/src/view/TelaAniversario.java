package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class TelaAniversario extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JDialog janela;
	private JTable table;
	private ResultSet rs;
	
	/**
	 * Create the panel.
	 */
	public TelaAniversario() {
		janela = new JDialog();
		janela.getContentPane().setBackground(Color.WHITE);
		int largura = 400;
		int altura = 500;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screen.width-largura)/2;
		int y = (screen.height-altura)/2;
		janela.setBounds(x,y,largura,altura);
		janela.setVisible(true);
		janela.getContentPane().add(this);
		setLayout(null);
		
		JLabel lblAniversariantesDoMs = new JLabel("Aniversariantes do m\u00EAs");
		lblAniversariantesDoMs.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAniversariantesDoMs.setBounds(10, 31, 337, 19);
		add(lblAniversariantesDoMs);
		
		
		String[][] aniversarios = new String [10][2];
		aniversarios[0][0] = "02/01";
		aniversarios[0][1] = "Joana Da Silva";
		aniversarios[1][0] = "05/01";
		aniversarios[1][1] = "Joaquim José";
		aniversarios[2][0] = "10/01";
		aniversarios[2][1] = "Maria 1";
		aniversarios[3][0] = "25/01";
		aniversarios[3][1] = "Maria 2 ";
		aniversarios[4][0] = "27/01";
		aniversarios[4][1] = "Maria 3";
		
		String[] columnNames = {"Data", "Nome"};
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(TelaAniversario.class.getResource("/view/1495241485_Gift.png")));
		lblNewLabel.setBounds(310, 11, 64, 64);
		add(lblNewLabel);
		
		JLabel label = new JLabel("");
		label.setBounds(144, 61, 46, 14);
		add(label);

		JButton btnNewButton = new JButton("Voltar");
		btnNewButton.setBounds(234, 400, 140, 50);
		add(btnNewButton);
		btnNewButton.setIcon(new ImageIcon(TelaCliente.class.getResource("/view/iconesair (1).png")));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 86, 364, 303);
		add(scrollPane);
		
		table = new JTable(aniversarios, columnNames);
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(aniversarios,columnNames) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] {
				String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(70);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(1).setMinWidth(200);
		table.setFillsViewportHeight(true);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setSurrendersFocusOnKeystroke(true);
		table.setRowSelectionAllowed(false);
		table.setRowSelectionAllowed(true);
		table.setDefaultRenderer(String.class, centerRenderer);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				janela.dispose();
			}
		});
	}
}
