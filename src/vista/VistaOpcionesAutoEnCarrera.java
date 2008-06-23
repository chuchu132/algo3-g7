package vista;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;

public class VistaOpcionesAutoEnCarrera extends JFrame {
	
	private final int ANCHO = 350;
	private final int ALTO = 60;
	private VistaAutoEnPista vistaAuto;
	private ControladorOpcionesCarrera controlador;
	JCheckBox velocidad,estado,combustible;
	
	public VistaOpcionesAutoEnCarrera(VistaAutoEnPista vistaAutoEnPista) {
	
	 vistaAuto = vistaAutoEnPista;
	 Container contenedor = getContentPane();
	 contenedor.setLayout(new  FlowLayout());
	 
	 velocidad = new JCheckBox("Velocidad");
	 contenedor.add(velocidad);
	 estado = new JCheckBox("Estado");
	 contenedor.add(estado);
	 combustible = new JCheckBox("Combustible");
	 contenedor.add(combustible);
	 
	 controlador = new ControladorOpcionesCarrera();
	 
	 velocidad.addItemListener(controlador);
	 combustible.addItemListener(controlador);
	 estado.addItemListener(controlador);
	 
	 setSize(ANCHO,ALTO);
	 setVisible(true);
	 setResizable(false);
	 setTitle("OPCIONES DE CARRERA");
	 setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	 setLocation(Escenario.WIDTH,0);
	}
	
	private class ControladorOpcionesCarrera implements ItemListener{

		public void itemStateChanged(ItemEvent e) {
			if(e.getSource() == velocidad){
				vistaAuto.setVelocidad(	velocidad.isSelected()?	true: false);
				}
			if(e.getSource() == estado){
				vistaAuto.setEstado(estado.isSelected()? true: false);
				}
			if(e.getSource() == combustible){
				vistaAuto.setCombustible(combustible.isSelected()? true : false);
				}
				
			}
			
		}
		
}
	

