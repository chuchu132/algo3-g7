package vista;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;

public class VistaOpcionesCarrera extends JFrame {
	
	private final int ANCHO = 350;
	private final int ALTO = 60;
	JCheckBox velocidad,estado,combustible;
	
	public VistaOpcionesCarrera() {
	 Container contenedor = getContentPane();
	 contenedor.setLayout(new  FlowLayout());
	 
	 velocidad = new JCheckBox("Velocidad");
	 contenedor.add(velocidad);
	 estado = new JCheckBox("Estado");
	 contenedor.add(estado);
	 combustible = new JCheckBox("Combustible");
	 contenedor.add(combustible);
	 
	 setSize(ANCHO,ALTO);
	 setVisible(true);
	 setResizable(false);
	 setTitle("OPCIONES DE CARRERA");
	 setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	 setLocation(Escenario.WIDTH,0);
	}
	
	public class ControladorOpcionesCarrera implements ItemListener{

		public void itemStateChanged(ItemEvent e) {
			if(e.getSource() == velocidad){
				
			}
			
		}
		
	}
	
}
