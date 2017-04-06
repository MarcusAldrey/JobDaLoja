package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controller.Controller;

public class Principal {
			
	public void criarTela() throws ClassNotFoundException, SQLException {
		TelaPrincipal telaPrincipal = new TelaPrincipal();
		telaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		URL url = this.getClass().getResource("logo.jpg"); 
		Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(url);
		telaPrincipal.setIconImage(iconeTitulo);
		telaPrincipal.setBackground(Color.black);
		int largura = 600;
		int altura = 400;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screen.width-largura)/2;
		int y = (screen.height-altura)/2;
		telaPrincipal.setSize(new Dimension(altura, largura));
		telaPrincipal.setResizable(false);
		telaPrincipal.setLocation(x, y);
		SplashScreen splash = new SplashScreen(5000);
		splash.mostrarSplashESair();
		telaPrincipal.setVisible(true);
		new Controller();
	}

	public static void main(String[] args) {
		try {
			new Principal().criarTela();
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC não instalado");
			e.printStackTrace();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Arquivo de banco de dados não encontrado");
			e.printStackTrace();
		}
	}
}