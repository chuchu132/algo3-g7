package Modelo;

import java.util.ArrayList;

import Excepciones.NotAutoException;
import Excepciones.NotAutoparteException;;
public class Taller {
	
	ArrayList<Auto> misAutos;
	ArrayList<Autoparte> misAutopartes;
	Auto autoActual;
	
	public Taller(){
		autoActual = null;
		misAutopartes = new ArrayList<Autoparte>();
		misAutos = new ArrayList<Auto>();
	}
	
	public void agregarAuto(Auto newAuto) throws NotAutoException{
		if (newAuto instanceof Auto)
			misAutos.add(newAuto);
		else
			throw new NotAutoException();
	}
		
	public void elegirAuto(int auto){
		try{
			autoActual = misAutos.get(auto);
		}
		catch (IndexOutOfBoundsException e){//MUY PRONTO
			autoActual = null;
			}
	}
	
	public void elegirAutoparte (int parte){
		try{
			Autoparte p;
			p = misAutopartes.get(parte);
			try {
				cambiarParte(p);
			}
			catch (NotAutoparteException e){
				// completar...
			}
		}
		catch (IndexOutOfBoundsException e){
			
		}
	}
	
	/* HAY Q VERIFICAR SI autoAcutual == null */
	public void cargarNaftaAlAutoActual(double litros,Nafta nafta){
		autoActual.cargarCombustible(litros, nafta);
	}
	
	public void vaciarTanqueAutoActual(){
		autoActual.vaciarTanque();
	}

	
	public void agregarAutoparte(Autoparte parte)throws NotAutoparteException{
		if (parte instanceof Autoparte){
				misAutopartes.add(parte);
		}
		else throw new NotAutoparteException();
	}
	
	public void cambiarParte(Autoparte parte)throws NotAutoparteException{
		
		Autoparte unaParte;
		
		if (parte instanceof CajaVelocidades){
			unaParte = autoActual.cambiarCaja((CajaVelocidades)parte);
		}
		else if (parte instanceof Carroceria){
			unaParte = autoActual.cambiarCarroceria((Carroceria)parte);
		}
		else if (parte instanceof Motor){
			unaParte = autoActual.cambiarMotor((Motor)parte);
		}
		
		else if (parte instanceof TipoRueda){
			unaParte = autoActual.cambiarRueda((TipoRueda)parte);
		}
		
		else if (parte instanceof SistemaCombustion){
			unaParte = autoActual.cambiarSitemaCombustion((SistemaCombustion)parte);
		}
		
		else if (parte instanceof TanqueCombustible){
			unaParte = autoActual.cambiarTanque((TanqueCombustible)parte);
		}
		else throw new NotAutoparteException();
		
		try {
			agregarAutoparte(unaParte);
		}
		catch (NotAutoparteException e){
			
			parte = null;
			
		}
				
	}
	
	public void listarMisAutopartes() {
		System.out.println(misAutopartes.toString());
	}

	public void listarMisAutos() {
		System.out.println(misAutos.toString());
		
	}
	
	public Auto getAutoActual(){
		return autoActual;
	}
}