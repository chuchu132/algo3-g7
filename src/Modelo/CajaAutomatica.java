package Modelo;

import java.text.DecimalFormat;
import java.util.Observable;
import java.util.Observer;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class CajaAutomatica extends CajaVelocidades implements Observer{



	public CajaAutomatica(int cantidadCambios,double precio, double peso, float vidaUtilInicial) {

		super(cantidadCambios,precio,peso,vidaUtilInicial);
        

	}
	public String toString(){
		DecimalFormat porcentage = new DecimalFormat("0.00");
		return ("Caja Automatica. " + " Vida Util: " + porcentage.format(100*this.getPorcentajeVidaUtil())+ "%" );
	}
	
	public void subirCambio(){} 
	public void bajarCambio(){}
	public void setCambio(int numeroCambio) {}
    public int getCantidadCambios(){return 0;};
    
	public void update(Observable o, Object arg) {
		int revActualesMotor  = ((Motor)o).getRevolucionesActuales();
		
			if( revActualesMotor > (0.90* ((Motor)o).getRevolucionesMaximas() )){
			 	if(super.getCambioActual()!=super.getCantidadCambios()){
				super.subirCambio();
				((Motor)o).setRevolucionesActuales((int)(revActualesMotor*Motor.COEF_SUBIR_CAMBIO));
			 	}
			}
			else{
				if(revActualesMotor == Motor.REVOLUCIONES_MINIMAS){
				   if(super.getCambioActual() != 0){
					super.bajarCambio();
					((Motor)o).setRevolucionesActuales((int)(revActualesMotor*Motor.COEF_BAJAR_CAMBIO));
				   }
				}
				else{
					if(super.getCambioActual() == 0){
						super.subirCambio();
					}
				}
			}
		

		}
		
	public Element serialize(){
		Document document = DocumentHelper.createDocument();
		Element cajaVelocidades = document.addElement("cajaAutomatica");
		this.grabar(cajaVelocidades);
		cajaVelocidades.addAttribute("cantidadDeCambios",Integer.toString(this.getCantidadCambios()));
		return cajaVelocidades;
	}
	

}
