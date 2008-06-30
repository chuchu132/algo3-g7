package vista;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;

public class VistaOpcionesAutoEnCarrera extends JDialog {
	
	private final int ANCHO = 200;
	private final int ALTO = 230;
	private VistaAutoEnPista vistaAuto;
	private ControladorOpcionesCarrera controlador;
	private JCheckBox velocidad,estado,combustible,cambioActual,tiempo, posicion;
	private JButton botonAceptar;
	
	public VistaOpcionesAutoEnCarrera(VistaAutoEnPista vistaAutoEnPista) {
	
	 vistaAuto = vistaAutoEnPista;
	 Container contenedor = getContentPane();
	 contenedor.setLayout(new  GridLayout(7,1));
	 
	 botonAceptar = new JButton("Aceptar");
	 botonAceptar.addActionListener( new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
					cerrarVentana();
			}
	 });
	 velocidad = new JCheckBox("Velocidad",vistaAuto.isVelocidad());
	 contenedor.add(velocidad);
	 estado = new JCheckBox("Estado",vistaAuto.isEstado());
	 contenedor.add(estado);
	 combustible = new JCheckBox("Combustible",vistaAuto.isCombustible());
	 contenedor.add(combustible);
	 cambioActual = new JCheckBox("Cambio Actual",vistaAuto.isCambioActual());
	 contenedor.add(cambioActual);
	 tiempo = new JCheckBox ("Tiempo", vistaAuto.isTiempo());
	 contenedor.add(tiempo);
	 posicion = new JCheckBox("Posicion", vistaAuto.isPosicion());
	 contenedor.add(posicion);
	 
	 contenedor.add(botonAceptar);
	 
	 controlador = new ControladorOpcionesCarrera();
	 
	 velocidad.addItemListener(controlador);
	 combustible.addItemListener(controlador);
	 estado.addItemListener(controlador);
	 cambioActual.addItemListener(controlador);
	 tiempo.addItemListener(controlador);
	 posicion.addItemListener(controlador);
	 setModal(true);
	 setSize(ANCHO,ALTO);
	 setResizable(false);
	 setTitle("DATOS");
	 setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	 setLocation(0,0);
	 setVisible(true);
	}
	
	private void cerrarVentana() {
		this.dispose();
		
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
			if (e.getSource()==tiempo){
				vistaAuto.setTiempo (tiempo.isSelected()?true:false);
			}
			if (e.getSource()==posicion){
				vistaAuto.setPosicion(posicion.isSelected()?true:false);
			}
			}
			
		}
		
}
	

