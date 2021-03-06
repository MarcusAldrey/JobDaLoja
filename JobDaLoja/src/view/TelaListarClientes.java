package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.Controller;
import javax.swing.border.TitledBorder;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;


public class TelaListarClientes extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField textFieldNomeCPF;
	private JDialog janela;
	private Object[][] valores;
	private ResultSet rs;
	private JScrollPane scrollPane;
	private JLabel lblNenhumClienteEncontrado;

	/**
	 * Create the panel.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public TelaListarClientes() throws ClassNotFoundException, SQLException {
		setBackground(Color.WHITE);
		setLayout(null);

		JLabel lblNewLabel = new JLabel("Nome/CPF:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 14, 71, 14);
		add(lblNewLabel);
		
		ButtonGroup buttonGroup = new ButtonGroup();

		updateTable(null);

		scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane.setBounds(10, 42, 564, 347);
		add(scrollPane);
		
		lblNenhumClienteEncontrado = new JLabel("Nenhum cliente encontrado");
		lblNenhumClienteEncontrado.setEnabled(false);
		lblNenhumClienteEncontrado.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane.setColumnHeaderView(lblNenhumClienteEncontrado);
		
		updateTable(null);

		textFieldNomeCPF = new JTextField();
		textFieldNomeCPF.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFieldNomeCPF.setToolTipText("");
		textFieldNomeCPF.setBounds(91, 11, 240, 20);
		add(textFieldNomeCPF);
		textFieldNomeCPF.setColumns(10);
		textFieldNomeCPF.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				try {
					updateTable(textFieldNomeCPF.getText());
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}			
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				try {
					updateTable(textFieldNomeCPF.getText());
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}					
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				try {
					updateTable(textFieldNomeCPF.getText());
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}	
			}
		});

		janela = new JDialog();
		janela.getContentPane().setBackground(Color.WHITE);
		int largura = 600;
		int altura = 500;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screen.width-largura)/2;
		int y = (screen.height-altura)/2;
		janela.setBounds(x,y,largura,altura);
		janela.setVisible(true);
		janela.getContentPane().add(this);

		JButton button = new JButton("Voltar");
		button.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button.setIcon(new ImageIcon(TelaListarClientes.class.getResource("/view/iconesair (1).png")));
		button.setBounds(404, 400, 170, 50);
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				janela.dispose();				
			}
		});
		add(button);

		janela.setTitle("La Victoria - Procurar cliente");
		URL url = getClass().getResource("logo.jpg"); 
		Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(url);
		janela.setIconImage(iconeTitulo);
	}

	private void updateTable(String value) throws ClassNotFoundException, SQLException {
		if(value == null || value == "")
			rs = Controller.getClientes();
		else
			rs = Controller.getClientes(value);	

		int count = 0;
		while (rs.next())
			++count;
		valores = new Object[count][3];		
		int cont = 0;
		if(value == null)
			rs = Controller.getClientes();
		else
			rs = Controller.getClientes(value);	
		while(rs.next()) {
			for(int y = 0; y<3; y++)
				valores[cont][y] = rs.getString(y+1);
			cont++;
		}
		String[] columnNames = {"Nome", "CPF", "Telefone"};
		table = new JTable(valores,columnNames);
		if(cont == 0) {
			columnNames[0] = "";
			columnNames[1] = "Nenhum cliente encontrado";
			columnNames[2] = "";
			table = new JTable(valores,columnNames);
			table.setFont(new Font("SansSerif", Font.PLAIN, 12));
		}
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		Font cabecalho = new Font("SansSerif", Font.BOLD + Font.ITALIC, 12);
		table.getTableHeader().setFont(cabecalho);
		table.setRowSelectionAllowed(true);
		table.setRowHeight(30);
		table.setDefaultEditor(Object.class, null);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		if(scrollPane != null)
			scrollPane.setViewportView(table);

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(e.getValueIsAdjusting()) {
					String nomeDoCliente = table.getValueAt(table.getSelectedRow(), 0).toString();
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
					janela.dispose();
				}
			}
		});
	}
}





