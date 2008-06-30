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
		JLabel longPista, coefPista;
		ImageIcon imagen;
		Container contenedor = getContentPane();
		contenedor.setLayout(new GridLayout(2, 1));
		Double longitud = Juego.getPista().getLongitud();
		DecimalFormat porcentajeLong = new DecimalFormat("0.00");
		String longitudString = porcentajeLong.format(longitud).toString();
		Double coeficienteAgarre = Juego.getPista().getCoeficienteAgarre();
		if (coeficienteAgarre<0.33)
			spriteName = "nieve/Pista0.jpg";
		else if (coeficienteAgarre<0.66)
			spriteName = "lluvia/Pista0.jpg";
			 else
				 spriteName = "despejado/Pista0.jpg";
		DecimalFormat porcentajeCoef = new DecimalFormat("0.00");
		String coeficienteAgarreString = porcentajeCoef.format(coeficienteAgarre).toString();
		
		longPista = new JLabel (longitudString);
		contenedor.add(longPista);
		imagen = (ImageIcon) spriteCache.getSprite(spriteName);
		coefPista = new JLabel (coeficienteAgarreString, imagen , 0);
		contenedor.add(coefPista);
		super.setAlwaysOnTop(true);
		super.setVisible(true);
	}
	
	

}
