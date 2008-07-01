package Modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import Excepciones.NoExisteAutoException;
import Excepciones.NotAutoException;
import Excepciones.NotAutoparteException;;
public class Taller extends Observable{
	
	ArrayList<Auto> misAutos;
	ArrayList<Autoparte> misAutopartes;
	Auto autoActual;
	
	public Taller(){
		autoActual = null;
		misAutopartes = new ArrayList<Autoparte>();
		misAutos = new ArrayList<Auto>();
	}
	
	public Taller(Element elemTaller) {
		Iterator it = elemTaller.elementIterator();
		
		Element elemListaAutos = (Element) it.next();
		Element elemListaAutopartes = (Element) it.next();
		Element elemAutoActual = (Element) it.next();
		
		this.cargarListaAutos(elemListaAutos);
		this.cargarListaAutopartes(elemListaAutopartes);
		if (!(elemAutoActual.getName().equals("none")))
			autoActual = new Auto(elemAutoActual);
			
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
		
	public void elegirAuto(int auto) {
		try{
			autoActual = misAutos.get(auto);
		}
		catch (IndexOutOfBoundsException e){
			autoActual = null;
			}
		setChanged();
		notifyObservers();
	}
		
	
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
			setChanged();
			notifyObservers();
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
		if (autoActual != null)
			taller.add(this.getAutoActual().serialize());
		else taller.addElement("none");
		
		return taller;
	}

	public void deserialize(Element elemTaller) {
		Iterator it = elemTaller.elementIterator();
		
		Element elemListaAutos = (Element) it.next();
		Element elemListaAutopartes = (Element) it.next();
		Element elemAutoActual = (Element) it.next();
		
		this.cargarListaAutos(elemListaAutos);
		this.cargarListaAutopartes(elemListaAutopartes);
		if (!(elemAutoActual.getName().equals("none")))
			this.autoActual = new Auto(elemAutoActual);
							
	}

	private void cargarListaAutopartes(Element elemListaAutopartes) {
		Iterator it = elemListaAutopartes.elementIterator();
		while ( it.hasNext()){
			Element elemAutoparte = (Element) it.next();
			if (elemAutoparte.getName().equals("cajaAutomatica")){
				CajaAutomatica autoParte = new CajaAutomatica(elemAutoparte);
				misAutopartes.add(autoParte);
			}
			if (elemAutoparte.getName().equals("cajaVelocidades")){
				CajaVelocidades autoParte = new CajaVelocidades(elemAutoparte);
				misAutopartes.add(autoParte);
			}
			if (elemAutoparte.getName().equals("carroceria")){
				Carroceria autoParte = new Carroceria(elemAutoparte);
				misAutopartes.add(autoParte);
			}
			if (elemAutoparte.getName().equals("motor")) { 
				Motor autoParte = new Motor(elemAutoparte);
				misAutopartes.add(autoParte);
			}
			if (elemAutoparte.getName().equals("sistemaCombustion")) { 
				SistemaCombustion autoParte = new SistemaCombustion(elemAutoparte);
				misAutopartes.add(autoParte);
			}
			if (elemAutoparte.getName().equals("tanqueCombustible")) { 
				TanqueCombustible autoParte = new TanqueCombustible(elemAutoparte);
				misAutopartes.add(autoParte);
			}
			if (elemAutoparte.getName().equals("tipoRueda")) { 
				TipoRueda autoParte = new TipoRueda(elemAutoparte);
				misAutopartes.add(autoParte);
			}
		}
			
	}

	private void cargarListaAutos(Element elemListaAutos) {
		Iterator it = elemListaAutos.elementIterator();
		while ( it.hasNext()){
			Element elemAuto = (Element) it.next();
			Auto auto = new Auto(elemAuto);
			misAutos.add(auto);		
		}
		
	}	
	
}