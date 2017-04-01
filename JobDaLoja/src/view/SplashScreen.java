package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

@SuppressWarnings("serial")
public class SplashScreen extends JWindow {

	private int duracao; 

	public SplashScreen(int duracao) {
		this.duracao = duracao;
	}

	/**
	 * Deixa a splashScreen vis�vel pelo tempo do seu atributo dura��o
	 */
	public void mostrarSplash() {        
		JPanel content = (JPanel)getContentPane();
		// Configura a posi��o e o tamanho da janela
		int largura = 720;
		int altura = 216;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screen.width-largura)/2;
		int y = (screen.height-altura)/2;
		setBounds(x,y,largura,altura);
		// Constr�i o splash screen
		URL url = this.getClass().getResource("splashScreen.jpg");  
		JLabel label = new JLabel(new ImageIcon(url));
		content.add(label);
		setVisible(true);

		// Faz a tela ficar um tempo vis�vel
		try { Thread.sleep(duracao); } catch (Exception e) {}        
		setVisible(false);        
	}

	/**
	 * Mostra a splashScreen pelo tempo da sua dura��o e fecha
	 */
	public void mostrarSplashESair() {        
		//mostrarSplash();
		dispose();    
	}
}

