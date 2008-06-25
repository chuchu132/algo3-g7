package Modelo;

import java.util.ArrayList;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

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
	
	public Element serialize(){
		Document document = DocumentHelper.createDocument();
		Element taller = document.addElement("taller");
		Element autos = taller.addElement("autos");
		for (Auto auto : this.misAutos) {
			autos.add(auto.serialize());
		}
		
		Element autoPartes = taller.addElement("autopartes");
		for (Autoparte autoparte : this.misAutopartes){
			if (autoparte instanceof CajaAutomatica)
				autoPartes.add(((CajaAutomatica) autoparte).serialize());
			if (autoparte instanceof CajaVelocidades)
				autoPartes.add(((CajaVelocidades) autoparte).serialize());
			if (autoparte instanceof Carroceria)
				autoPartes.add(((Carroceria) autoparte).serialize());
			if (autoparte instanceof Motor)
				autoPartes.add(((Motor) autoparte).serialize());
			if (autoparte instanceof SistemaCombustion)
				autoPartes.add(((SistemaCombustion) autoparte).serialize());
			if (autoparte instanceof TanqueCombustible)
				autoPartes.add(((TanqueCombustible) autoparte).serialize());
			if (autoparte instanceof TipoRueda)
				autoPartes.add(((TipoRueda) autoparte).serialize());
		}
		
		taller.add(this.getAutoActual().serialize());
		
		return taller;
	}
	
}