package Modelo;

import java.util.Observable;
import java.util.Observer;

public class CajaAutomatica extends CajaVelocidades implements Observer{



	public CajaAutomatica(int cantidadCambios,double precio, double peso, float vidaUtilInicial) {

		super(cantidadCambios,precio,peso,vidaUtilInicial);


	}

	public void subirCambio(){} 
	public void bajarCambio(){}
	public void setCambio(int numeroCambio) {}

	public void update(Observable o, Object arg) {
		if(o instanceof Motor){
			if( ((Motor)o).getRevolucionesActuales() == ((Motor)o).getRevolucionesMaximas() ){
				super.subirCambio();
			}
			else{
				if(((Motor)o).getRevolucionesActuales() == Motor.REVOLUCIONES_MINIMAS){
					super.bajarCambio();
				}
			}

		}

	}


}
