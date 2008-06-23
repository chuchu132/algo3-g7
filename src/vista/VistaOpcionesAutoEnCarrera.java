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
	JCheckBox velocidad,estado,combustible,cambioActual;
	
	public VistaOpcionesAutoEnCarrera(VistaAutoEnPista vistaAutoEnPista) {
	
	 vistaAuto = vistaAutoEnPista;
	 Container contenedor = getContentPane();
	 contenedor.setLayout(new  FlowLayout());
	 
	 velocidad = new JCheckBox("Velocidad",vistaAuto.isVelocidad());
	 contenedor.add(velocidad);
	 estado = new JCheckBox("Estado",vistaAuto.isEstado());
	 contenedor.add(estado);
	 combustible = new JCheckBox("Combustible",vistaAuto.isCombustible());
	 contenedor.add(combustible);
	 cambioActual = new JCheckBox("Cambio Actual",vistaAuto.isCambioActual());
	 contenedor.add(cambioActual);
	 controlador = new ControladorOpcionesCarrera();
	 
	 velocidad.addItemListener(controlador);
	 combustible.addItemListener(controlador);
	 estado.addItemListener(controlador);
	 cambioActual.addItemListener(controlador);
	 
	 setSize(ANCHO,ALTO);
	 setVisible(true);
	 setResizable(true);
	 setTitle("OPCIONES DE CARRERA");
	 setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
			if(e.getSource() == cambioActual){
				vistaAuto.setCambioActual(cambioActual.isSelected()? true: false);
			}
				
			}
			
		}
		
}
	

