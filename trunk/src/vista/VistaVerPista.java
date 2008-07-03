package vista;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Modelo.Juego;
import Recursos.SpriteCache;

public class VistaVerPista extends JDialog{
	
	private static final long serialVersionUID = 1L;

	public VistaVerPista(Juego juego){
	  	JPanel panelEtiquetas = new JPanel();
	  	panelEtiquetas.setLayout(new GridLayout(2,1));
		
	  	String spriteName;
		SpriteCache spriteCache = new SpriteCache();
		JLabel longPista, coefPista, fondo;
		ImageIcon imagen;
		Container contenedor = getContentPane();
		contenedor.setLayout(new BorderLayout());
		
		Double longitud = juego.getPista().getLongitud();
		DecimalFormat porcentajeLong = new DecimalFormat("0.00");
		String longitudString = porcentajeLong.format(longitud).toString();
		Double coeficienteAgarre = juego.getPista().getCoeficienteAgarre();
		
		if (coeficienteAgarre<0.33)
			spriteName = "nieve";
		else if (coeficienteAgarre<0.66)
			spriteName = "lluvia";
			 else
				 spriteName = "despejado";
		longPista = new JLabel ("Longitud de la pista " +longitudString+ " mts");
		panelEtiquetas.add(longPista);
		imagen = new ImageIcon(spriteCache.getSprite(spriteName + "/Pista0.jpg"));
		coefPista = new JLabel ("Condiciones climatológicas: " + spriteName);
		panelEtiquetas.add(coefPista);
	
		fondo = new JLabel (imagen);
		
		contenedor.add(panelEtiquetas,BorderLayout.NORTH);
		contenedor.add(fondo,BorderLayout.SOUTH);
		setTitle("Siguiente Pista");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 400);
		setResizable(false);
		setModal(true);
		setVisible(true);
	}
	
	

}
