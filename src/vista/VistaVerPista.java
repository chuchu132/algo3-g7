package vista;

import java.awt.Container;
import java.awt.GridLayout;
import java.text.DecimalFormat;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Modelo.Juego;
import Modelo.Jugador;
import Recursos.SpriteCache;

public class VistaVerPista extends JFrame{
	
	public VistaVerPista(Jugador jugador){
		super("SiguientePista");
		super.setSize(400, 400);
		super.setResizable(false);
		String spriteName;
		SpriteCache spriteCache = new SpriteCache();
		JLabel longPista, coefPista, fondo;
		ImageIcon imagen;
		Container contenedor = getContentPane();
		contenedor.setLayout(new GridLayout(3, 1));
		Double longitud = Juego.getPista().getLongitud();
		DecimalFormat porcentajeLong = new DecimalFormat("0.00");
		String longitudString = porcentajeLong.format(longitud).toString();
		Double coeficienteAgarre = Juego.getPista().getCoeficienteAgarre();
		if (coeficienteAgarre<0.33)
			spriteName = "nieve";
		else if (coeficienteAgarre<0.66)
			spriteName = "lluvia";
			 else
				 spriteName = "despejado";
		longPista = new JLabel ("Longitud de la pista " +longitudString+ " mts");
		contenedor.add(longPista);
		imagen = new ImageIcon(spriteCache.getSprite(spriteName + "/Pista0.jpg"));
		coefPista = new JLabel ("Condiciones climatológicas: " + spriteName);
		contenedor.add(coefPista);
		fondo = new JLabel (imagen);
		contenedor.add(fondo);
		super.setAlwaysOnTop(true);
		super.setVisible(true);
	}
	
	

}
