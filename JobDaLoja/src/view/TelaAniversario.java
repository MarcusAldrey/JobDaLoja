package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import controller.Controller;
import javax.swing.SwingConstants;

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
		janela.setBounds(x,y,400,500);
		janela.setVisible(true);
		janela.getContentPane().add(this);
		setLayout(null);
		
		JLabel lblAniversariantesDoMs = new JLabel("Aniversariantes do m\u00EAs");
		lblAniversariantesDoMs.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAniversariantesDoMs.setBounds(10, 31, 337, 19);
		add(lblAniversariantesDoMs);
		
		JLabel lblNenhumAniversarianteNeste = new JLabel("Nenhum aniversariante neste m\u00EAs");
		lblNenhumAniversarianteNeste.setHorizontalAlignment(SwingConstants.CENTER);
		lblNenhumAniversarianteNeste.setBounds(10, 189, 364, 14);
		add(lblNenhumAniversarianteNeste);
				
		try {
			
			rs = Controller.getAniversariantes();
			int qtdLinhas = Controller.getTamanhoRetorno();
			String data;
			String nome;

			if(qtdLinhas>0){
				lblNenhumAniversarianteNeste.setVisible(false);
				String[][] aniversarios = new String [qtdLinhas][2];
				int i=0;
				while(rs.next()){

					nome = rs.getString(1);
					data = Controller.converterSqlToPad(rs.getString(2));
					aniversarios[i][0] = data;
					aniversarios[i][1] = nome;
					i++;
				}
				
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
				
				//table = new JTable(aniversarios, columnNames);
				
				ResultSet aniversariante = Controller.getAniversarianteDia();
				int qtdAniversariantes = Controller.getTamanhoRetorno();
				
				table = new JTable();
				

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
				
				while(aniversariante.next()){
					for(int j=0;j<table.getModel().getRowCount();j++){
						String texto = (String) table.getModel().getValueAt(j, 0);
						String nomePessoa = (String) table.getModel().getValueAt(j, 1);
						String data2 = Controller.converterSqlToPad(aniversariante.getString(1));
						String telefone = aniversariante.getString(2);
						if(texto.equals(data2)){
							//table.getModel().setValueAt(nomePessoa+" "+telefone+" "+"HOJE!!!!!", j, 1);
							table.getModel().setValueAt(nomePessoa+" - - "+telefone+" - - "+"HOJE!!", j, 1);
						}
					}
				}
				
				table.getColumnModel().getColumn(0).setPreferredWidth(70);
				table.getColumnModel().getColumn(1).setPreferredWidth(200);
				table.getColumnModel().getColumn(1).setMinWidth(200);
				table.setFillsViewportHeight(true);
				table.setFont(new Font("Tahoma", Font.PLAIN, 14));
				table.setSurrendersFocusOnKeystroke(true);
				table.setRowSelectionAllowed(false);
				table.setRowSelectionAllowed(true);					
				
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						janela.dispose();
					}
				});				
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao acessar o banco de dados");
		}
	}
}
