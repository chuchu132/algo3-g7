package Modelo;

import java.util.Observable;
import java.util.Observer;

public class CajaAutomatica extends CajaVelocidades implements Observer{
  private int cantidadCambios;


	public CajaAutomatica(int cantidadCambios,double precio, double peso, float vidaUtilInicial) {

		super(cantidadCambios,precio,peso,vidaUtilInicial);
        this.cantidadCambios = cantidadCambios;

	}

	public void subirCambio(){} 
	public void bajarCambio(){}
	public void setCambio(int numeroCambio) {}

	public void update(Observable o, Object arg) {
		int revActualesMotor  = ((Motor)o).getRevolucionesActuales();
		
			if( revActualesMotor > (0.90* ((Motor)o).getRevolucionesMaximas() )){
			 	if(super.getCambioActual()!= cantidadCambios){
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

	

}
