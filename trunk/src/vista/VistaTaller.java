package vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class VistaTaller {

	public VistaTaller () {
		JFrame ventana = new JFrame("Mi Taller");
		JPanel panel = new JPanel();
		JButton boton = new JButton("mi boton");
		
		panel.add(boton);
		
		ventana.add(panel);
		
		ventana.setBounds(0, 0, 500, 500);
		
		
		ventana.setVisible(true);
		
	}

}
