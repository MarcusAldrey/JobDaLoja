package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.Controller;

import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TelaVencimentos extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JTable tableVencimentos;
	JRadioButton rdbtnVencidas;
	JRadioButton rdbtnHoje;
	Object[][] parcelasVencidas;
	JScrollPane scrollPane;

	/**
	 * Create the frame.
	 */
	public TelaVencimentos() {
		URL url = this.getClass().getResource("logo.jpg"); 
		Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(url);
		setTitle("La Victoria -  Vencimentos");
		this.setIconImage(iconeTitulo);		
		setResizable(false);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int largura = 500;
		int altura = 500;
		int x = (screen.width-largura)/2;
		int y = (screen.height-altura)/2;
		setBounds(x,y,largura,altura);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		rdbtnHoje = new JRadioButton("Mostrar compras que vencem HOJE");
		rdbtnHoje.setBackground(Color.WHITE);
		rdbtnHoje.setBounds(6, 7, 281, 23);
		contentPane.add(rdbtnHoje);
		
		rdbtnVencidas = new JRadioButton("Mostrar compras vencidas");
		rdbtnVencidas.setBackground(Color.WHITE);
		rdbtnVencidas.setBounds(6, 58, 157, 23);
		contentPane.add(rdbtnVencidas);
		
		ButtonGroup buttons = new ButtonGroup();
		buttons.add(rdbtnHoje);
		buttons.add(rdbtnVencidas);
		
		JLabel lblOu = new JLabel("Ou");
		lblOu.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblOu.setBounds(11, 37, 46, 14);
		contentPane.add(lblOu);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 116, 478, 283);
		contentPane.add(scrollPane);
		
		tableVencimentos = new JTable();
		scrollPane.setViewportView(tableVencimentos);
		
		JButton btnNewButton = new JButton("Voltar");
		btnNewButton.setBounds(344, 410, 140, 50);
		contentPane.add(btnNewButton);
		btnNewButton.setIcon(new ImageIcon(TelaCliente.class.getResource("/view/iconesair (1).png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		rdbtnHoje.addActionListener(new MostrarVencimentosAction());
		rdbtnVencidas.addActionListener(new MostrarVencimentosAction());

	}
	
	@SuppressWarnings("resource")
	private void atualizarTabela() throws SQLException {
		String[] columnNames = {"Cliente", "Valor da parcela","Valor já pago", "Data de vencimento"};
		ResultSet rs = null;
		
		if(rdbtnHoje.isSelected())
			rs = Controller.getComprasVencidas(1);
		
		else if(rdbtnVencidas.isSelected())
			rs = Controller.getComprasVencidas(2);
		if(rs == null)
			return;
		
		int size = 0;
		while(rs.next())
			size++;


		if(rdbtnHoje.isSelected())
			rs = Controller.getComprasVencidas(1);
		else if(rdbtnVencidas.isSelected())
			rs = Controller.getComprasVencidas(2);

		if(rs == null)
			return;
		
		parcelasVencidas = new Object[size][4];
		
		int cont = 0;
		while(rs.next()) {
			for(int z = 0; z<4; z++) {
				ResultSet nomeComprador;
				if(z==0) {
					nomeComprador = Controller.getNomeComprador(rs.getInt(1));
					parcelasVencidas[cont][z] = nomeComprador.getString(1);
				}
				else if(z==3)
					parcelasVencidas[cont][z] = Controller.converterSqlToPad(rs.getString(z+1));
				else
					parcelasVencidas[cont][z] = "R$ " + rs.getString(z+1);
			}
			cont++;
		}
		tableVencimentos = new JTable(parcelasVencidas, columnNames);
		tableVencimentos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tableVencimentos.setRowHeight(30);
		tableVencimentos.setRowSelectionAllowed(true);
		tableVencimentos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				String nomeDoCliente = tableVencimentos.getValueAt(tableVencimentos.getSelectedRow(), 0).toString();
				JFrame frame = null;
				try {
					frame = new TelaCliente(nomeDoCliente);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame.setVisible(true);
				dispose();
			}
		});
		tableVencimentos.getColumnModel().getColumn(0).setPreferredWidth(40);
		scrollPane.setViewportView(tableVencimentos);		
	}
	
	public class MostrarVencimentosAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try {
				atualizarTabela();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Não foi possível acessar o banco de dados!");
				e1.printStackTrace();
			}			
		}
		
	}
}
