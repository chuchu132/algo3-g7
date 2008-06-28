package Modelo;

import java.util.ArrayList;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import Excepciones.NoExisteAutoException;
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
		else{
			throw new NotAutoException();
			}
		if(autoActual == null){
			autoActual=newAuto;
		}
	}
		
	public void elegirAuto(int auto){
		try{
			autoActual = misAutos.get(auto);
		}
		catch (IndexOutOfBoundsException e){
			autoActual = null;
			}
	}
		
	
	public void cargarNaftaAlAutoActual(double litros,Nafta nafta)throws NoExisteAutoException{
		try{
		autoActual.cargarCombustible(litros, nafta);
		}
		catch(NullPointerException e){throw new NoExisteAutoException();}
		
		
		
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
	
	public void cambiarParte(int indice)throws NotAutoparteException,NoExisteAutoException{
		Autoparte parte = null;
		Autoparte unaParte;
		try{
		 parte = misAutopartes.remove(indice);
				
		
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
		
		
			agregarAutoparte(unaParte);
		}
		catch (IndexOutOfBoundsException e) {}
		catch (NullPointerException e2){
			misAutopartes.add(indice, parte);
			  throw new NoExisteAutoException();
		}
				
	}
	
	public ArrayList<Autoparte> getListaDeMisAutopartes() {
		return misAutopartes;
	}

	public ArrayList<Auto> getListaDeMisAutos() {
		return misAutos;
		
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