package Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.dom4j.io.XPP3Reader;
import org.xmlpull.v1.XmlPullParserException;


import Modelo.Auto;
import Modelo.Autoparte;
import Modelo.CajaVelocidades;
import Modelo.Carroceria;
import Modelo.Motor;
import Modelo.Nafta;
import Modelo.Pista;
import Modelo.TipoRueda;
import Modelo.SistemaCombustion;
import Modelo.TanqueCombustible;
import Modelo.Taller;
import Modelo.Jugador;

import junit.framework.TestCase;

public class TestGuardar extends TestCase {
	
	Taller miTaller;
	Motor miMotor;
	TanqueCombustible miTanque;
	CajaVelocidades miCaja;
	SistemaCombustion miSistemaCombustion;
	Carroceria miCarro;
	TipoRueda miRueda;
	Auto miAuto;
    Pista unaPista; 
    Nafta nafta;
    Jugador miJugador;
    int nroAutosTaller = 0;
    int nroAutopartesTaller = 0;
    
   	public TestGuardar(String name) {
		super(name);
	}


	protected void setUp(){
		miSistemaCombustion = new SistemaCombustion(5,100,"Turbo",0.2,1000);
		miMotor = new Motor(200,6,0.2,1000,400,9000);
		miCarro = new Carroceria(300,700,"Torino",1,0.1,1000);
		miCaja= new CajaVelocidades(5,200,80,1000);
		miTanque = new TanqueCombustible(100,70,1000);
		miRueda = new TipoRueda(100,20,0.9,1000);
		nafta = new Nafta("Nafta de Prueba",98);
		miTanque.cargarCombustible(50,nafta);
		miAuto =  new Auto(miMotor,miCaja,miSistemaCombustion,miCarro,miTanque,miRueda);
	    unaPista = new Pista(100,0.1);
	    miTaller = new Taller();
	    miJugador = new Jugador();
	    miTaller = miJugador.getTaller();
	    try{
	    	miTaller.agregarAuto(miAuto);
	    	nroAutosTaller++;
	    }
	    catch (Exception e) {
	    	/* no hace falta nada*/
	    }
	    try {
	    	miTaller.agregarAuto(miAuto);
	    	nroAutosTaller++;
	    }
	    catch (Exception e){
	    	/* no hace falta nada */
	    }
	    miTaller.elegirAuto(1);
	    miJugador.setPlata(5000);
	    try {
	    	miTaller.agregarAutoparte(miMotor);
	    	nroAutopartesTaller++;
	    }
	    catch (Exception e){
	    	/* no hace falta nada */
	    }
	    try {
	    	miTaller.agregarAutoparte(miRueda);
	    	nroAutopartesTaller++;
	    }
	    catch (Exception e){
	    	/* no hace falta nada */
	    }
	    
	}
	
	
	public void guardar(Element elem) throws IOException{
		Document document = DocumentHelper.createDocument();
		Element raiz = document.addElement("raiz");
		
		String rutaArchivo = "C: archivo.xml";
		
			raiz.add(elem);
			
			OutputFormat format = OutputFormat.createPrettyPrint();
			XMLWriter writer = new XMLWriter(new FileWriter(rutaArchivo), format);
			writer.write(document);
			writer.close();
		}
	
	public Element cargar() throws IOException,DocumentException,XmlPullParserException {
		String rutaArchivo = "C: archivo.xml";
		
			File aFile = new File(rutaArchivo);
			XPP3Reader xmlReader = new XPP3Reader();
			Document doc = null;
			
			doc = xmlReader.read(aFile);
			
			
			Element element = doc.getRootElement();
			Iterator it = element.elementIterator();
			
			Element elem = (Element)it.next();
			return (elem);
	}
	
	
	
	public boolean compararSC(SistemaCombustion sistema1, SistemaCombustion sistema2){
		if ((sistema1.getPeso() == sistema2.getPeso()) && 
				(sistema1.getPrecio() == sistema2.getPrecio()) &&
				(sistema1.getVidaUtil() == sistema2.getVidaUtil()) &&
				(sistema1.getTipo().equals(sistema2.getTipo())) &&
				(sistema1.getVidaUtilInicial() == 
										sistema2.getVidaUtilInicial()) &&
				(sistema1.getPlus() == sistema2.getPlus()))
				return true;
		return false;
	}
	
	public boolean compararMotor(Motor motor1, Motor motor2){
		if 		((motor1.getPeso() == motor2.getPeso()) && 
				(motor1.getPrecio() == motor2.getPrecio()) &&
				(motor1.getVidaUtil() == motor2.getVidaUtil()) &&
				(motor1.getVidaUtilInicial() == motor2.getVidaUtilInicial()) &&
				(motor1.getEstado() == motor2.getEstado()) &&
				(motor1.getCilindros()== motor2.getCilindros()) &&
				(motor1.getCubicaje() == motor2.getCubicaje()) &&
				(motor1.getFuerzaInstantanea() == motor2.getFuerzaInstantanea()) &&
				(motor1.getFuerzaMaxima() == motor2.getFuerzaMaxima()) &&
				(motor1.getHP() == motor2.getHP()))
			return true;
		return false;
	}
	
	public boolean compararCarroceria(Carroceria carroceria1, Carroceria carroceria2){
		if		((carroceria1.getPeso() == carroceria2.getPeso()) &&
				(carroceria1.getPrecio() == carroceria2.getPrecio()) &&
				(carroceria1.getVidaUtil() == carroceria2.getVidaUtil()) &&
				(carroceria1.getVidaUtilInicial() == carroceria2.getVidaUtilInicial()) &&
				(carroceria1.getColor() == carroceria2.getColor()) &&
				(carroceria1.getModelo().equals(carroceria2.getModelo()) &&
				(carroceria1.getPlusVelocidad() == carroceria2.getPlusVelocidad())))
			return true;
		return false;
	}
	
	public boolean compararCaja(CajaVelocidades caja1,CajaVelocidades caja2){
		if		((caja1.getPeso()== caja2.getPeso()) &&
				(caja1.getPrecio() == caja2.getPrecio()) &&
				(caja1.getVidaUtil() == caja2.getVidaUtil()) &&
				(caja1.getVidaUtilInicial() == caja2.getVidaUtilInicial())&&
				(caja1.getCambioActual() == caja2.getCambioActual())&&
				(caja1.getCantidadCambios() == caja2.getCantidadCambios()))
			return true;
		return false;
	}
	
	public boolean compararTanque(TanqueCombustible tanque1, TanqueCombustible tanque2){
		if ((tanque1.getPeso() == tanque2.getPeso())&& 
		   (tanque1.getPrecio() == tanque2.getPrecio())&&
		   (tanque1.getVidaUtil() == tanque2.getVidaUtil())&&
		   (tanque1.getVidaUtilInicial() == tanque2.getVidaUtilInicial())&&
		   (tanque1.getCapacidadMaxima() == tanque2.getCapacidadMaxima())&&
		   (tanque1.cantidadCombustible() == tanque2.cantidadCombustible()) &&
		   (compararNafta(tanque1.getTipoNafta(),tanque2.getTipoNafta())))
			return true;
		return false;
			
	}
	
	public boolean compararNafta(Nafta nafta1, Nafta nafta2){
		if ((nafta1.getNombre().equals(nafta2.getNombre())) &&
		   (nafta1.getOctanaje() == nafta2.getOctanaje())&&
		   (nafta1.getPrecio() == nafta2.getPrecio()))
			return true;
		return false;
	}
	
	public boolean compararRueda(TipoRueda rueda1, TipoRueda rueda2){
		if ((rueda1.getPeso() == rueda2.getPeso()) &&
		   (rueda1.getPrecio() == rueda2.getPrecio())&&
		   (rueda1.getVidaUtil() == rueda2.getVidaUtil())&&
		   (rueda1.getVidaUtilInicial() == rueda2.getVidaUtilInicial())&&
		   (rueda1.getCoeficienteAgarre() == rueda2.getCoeficienteAgarre()))
			return true;
		return false;
	}
	
	public boolean compararPista(Pista pista1, Pista pista2){
		if ((pista1.getCoeficienteAgarre() == pista2.getCoeficienteAgarre()) &&
		   (pista1.getLongitud() == pista2.getLongitud()))
			return true;
		return false;
	}
	
	public boolean compararAuto(Auto auto1, Auto auto2){
		if ((auto1.getAceleracion() == auto2.getAceleracion())&&
		   (auto1.getDeltaAvance() == auto2.getDeltaAvance())&&
		   (auto1.getEstado() == auto2.getEstado())&&
		   (auto1.getVelocidad() == auto2.getVelocidad())&&
		   (compararSC(auto1.getSistemaCombustion(),auto2.getSistemaCombustion()))&&
		   (compararMotor(auto1.getMotor(),auto2.getMotor()))&&
		   (compararCarroceria(auto1.getCarroceria(),auto2.getCarroceria()))&&
		   (compararCaja(auto1.getCaja(),auto2.getCaja()))&&
		   (compararTanque(auto1.getTanque(),auto2.getTanque()))&&
		   (compararRueda(auto1.getRueda(),auto2.getRueda())))
			return true;
		return false;
	}
	
	public boolean compararTaller(Taller taller1, Taller taller2){
		if ((compararAuto(taller1.getAutoActual(),taller2.getAutoActual()))&&
		   (compararListaAutopartes(taller1.getListaDeMisAutopartes(),
				   taller2.getListaDeMisAutopartes()))&&
		   (compararListaAutos(taller1.getListaDeMisAutos(),taller2.getListaDeMisAutos())))
			return true;
		return false;
	}
	
	public boolean compararListaAutos(ArrayList<Auto> listaAutos1,ArrayList<Auto> listaAutos2){
		Iterator it1 = listaAutos1.iterator();
		Iterator it2 = listaAutos2.iterator();
	
		
		while ((it1.hasNext()) && (it2.hasNext())){
			Auto auto1 = (Auto)it1.next();
			Auto auto2 = (Auto)it2.next();
			if (!(compararAuto(auto1,auto2))) return false;			
		}
		if ((it1.hasNext() == false) && (it2.hasNext() == false)) return true;
		return false;
	}
	public boolean compararListaAutopartes(ArrayList<Autoparte> listaPartes1,
					ArrayList<Autoparte> listaPartes2){
		Autoparte parte1 = null;
		Autoparte parte2 = null;
		for (int i = 0; i<nroAutopartesTaller; i++){
			parte1 = listaPartes1.get(i);
			parte2 = listaPartes2.get(i);
			if (!(compararAutoparte(parte1,parte2))) return false;
			}
		return true;
		
	}
	
	public boolean compararAutoparte(Autoparte parte1, Autoparte parte2){
		if ((parte1 instanceof CajaVelocidades) && (parte2 instanceof CajaVelocidades)){
			return compararCaja((CajaVelocidades)parte1,(CajaVelocidades)parte2);
		}
		else if ((parte1 instanceof Carroceria)&&(parte1 instanceof Carroceria)) {
			return compararCarroceria((Carroceria)parte1,(Carroceria)parte2);
		}	
		else if ((parte1 instanceof Motor)&&(parte1 instanceof Motor)){
			return compararMotor((Motor)parte1,(Motor)parte2);
		}
		
		else if ((parte1 instanceof TipoRueda)&&(parte1 instanceof TipoRueda)){
			return compararRueda((TipoRueda)parte1,(TipoRueda)parte2);
		}
		
		else if ((parte1 instanceof SistemaCombustion)&&(parte1 instanceof SistemaCombustion)){
			return compararSC((SistemaCombustion)parte1,(SistemaCombustion)parte2);
		}
		
		else if ((parte1 instanceof TanqueCombustible)&&(parte1 instanceof TanqueCombustible)){
			return compararTanque((TanqueCombustible)parte1,(TanqueCombustible)parte2);
		}
		else return false;
		
		
		}

	
	
	public boolean compararJugador (Jugador jugador1, Jugador jugador2){
		if ((compararTaller(jugador1.getTaller(),jugador2.getTaller()))&&
		   (jugador1.getPlata() == jugador2.getPlata()))
			return true;
		return false;
	}
	
	public void testGuardarSistemaCombustion(){
		boolean bien = false;
		try{
			guardar(this.miSistemaCombustion.serialize());
		}
		catch (IOException e){
			System.out.println("se produjo un error");
		}
		try{
			Element elem = cargar();
			SistemaCombustion sistemaCombustionAux = new SistemaCombustion(elem);
			
					bien = compararSC(miSistemaCombustion,sistemaCombustionAux);

		} catch (IOException e1) {
				System.out.println("IO Exception");
		} catch (DocumentException e1) {
				System.out.println("Document Exception");
		} catch (XmlPullParserException e1) {
				System.out.println("XML Pull Parser Exception");
		}	
		assertEquals(true,bien);
		
	}
	
	public void testGuardarMotor(){
		boolean bien = false;
		try{
			guardar(this.miMotor.serialize());
		}
		catch (IOException e){
			System.out.println("se produjo un error");
		}
		try{
			Element elem = cargar();
			Motor motorAux = new Motor (elem);
						bien = compararMotor(this.miMotor,motorAux);

		} catch (IOException e1) {
				System.out.println("IO Exception");
		} catch (DocumentException e1) {
				System.out.println("Document Exception");
		} catch (XmlPullParserException e1) {
				System.out.println("XML Pull Parser Exception");
		}	
		assertEquals(true,bien);
		
	}
	
	public void testGuardarCarroceria(){
		boolean bien = false;
		try{
			guardar(this.miCarro.serialize());
		}
		catch (IOException e){
			System.out.println("se produjo un error");
		}
		try{
			Element elem = cargar();
			Carroceria carroAux = new Carroceria(elem);
			
					bien = compararCarroceria(this.miCarro,carroAux);

		} catch (IOException e1) {
				System.out.println("IO Exception");
		} catch (DocumentException e1) {
				System.out.println("Document Exception");
		} catch (XmlPullParserException e1) {
				System.out.println("XML Pull Parser Exception");
		}	
		assertEquals(true,bien);
		
	}
	
	public void testGuardarCaja(){
		boolean bien = false;
		try{
			guardar(this.miCaja.serialize());
		}
		catch (IOException e){
			System.out.println("se produjo un error");
		}
		try{
			Element elem = cargar();
			CajaVelocidades cajaAux = new CajaVelocidades(elem);
			
					bien = compararCaja(this.miCaja,cajaAux);

		} catch (IOException e1) {
				System.out.println("IO Exception");
		} catch (DocumentException e1) {
				System.out.println("Document Exception");
		} catch (XmlPullParserException e1) {
				System.out.println("XML Pull Parser Exception");
		}	
		assertEquals(true,bien);
		
	}
	
	public void testGuardarTanque(){
		boolean bien = false;
		try{
			guardar(this.miTanque.serialize());
		}
		catch (IOException e){
			System.out.println("se produjo un error");
		}
		try{
			Element elem = cargar();
			TanqueCombustible tanqueAux = new TanqueCombustible(elem);
			
					bien = compararTanque(this.miTanque,tanqueAux);

		} catch (IOException e1) {
				System.out.println("IO Exception");
		} catch (DocumentException e1) {
				System.out.println("Document Exception");
		} catch (XmlPullParserException e1) {
				System.out.println("XML Pull Parser Exception");
		}	
		assertEquals(true,bien);
		
	}
	
	public void testGuardarRueda(){
		boolean bien = false;
		try{
			guardar(this.miRueda.serialize());
		}
		catch (IOException e){
			System.out.println("se produjo un error");
		}
		try{
			Element elem = cargar();
			TipoRueda ruedaAux = new TipoRueda(elem);
			
					bien = compararRueda(this.miRueda,ruedaAux);

		} catch (IOException e1) {
				System.out.println("IO Exception");
		} catch (DocumentException e1) {
				System.out.println("Document Exception");
		} catch (XmlPullParserException e1) {
				System.out.println("XML Pull Parser Exception");
		}	
		assertEquals(true,bien);
		
	}
	
	public void testGuardarAuto(){
		boolean bien = false;
		try{
			guardar(this.miAuto.serialize());
		}
		catch (IOException e){
			System.out.println("se produjo un error");
		}
		try{
			Element elem = cargar();
			Auto autoAux = new Auto(elem);
			
					bien = compararAuto(this.miAuto,autoAux);

		} catch (IOException e1) {
				System.out.println("IO Exception");
		} catch (DocumentException e1) {
				System.out.println("Document Exception");
		} catch (XmlPullParserException e1) {
				System.out.println("XML Pull Parser Exception");
		}	
		assertEquals(true,bien);
		
	}
	
	public void testGuardarPista(){
		boolean bien = false;
		try{
			guardar(this.unaPista.serialize());
		}
		catch (IOException e){
			System.out.println("se produjo un error");
		}
		try{
			Element elem = cargar();
			Pista pistaAux = new Pista(elem);
			
					bien = compararPista(this.unaPista,pistaAux);

		} catch (IOException e1) {
				System.out.println("IO Exception");
		} catch (DocumentException e1) {
				System.out.println("Document Exception");
		} catch (XmlPullParserException e1) {
				System.out.println("XML Pull Parser Exception");
		}	
		assertEquals(true,bien);
		
	}
	
	public void testGuardarNafta(){
		boolean bien = false;
		try{
			guardar(this.nafta.serialize());
		}
		catch (IOException e){
			System.out.println("se produjo un error");
		}
		try{
			Element elem = cargar();
			Nafta naftaAux = new Nafta(elem);
			
					bien = compararNafta(this.nafta,naftaAux);

		} catch (IOException e1) {
				System.out.println("IO Exception");
		} catch (DocumentException e1) {
				System.out.println("Document Exception");
		} catch (XmlPullParserException e1) {
				System.out.println("XML Pull Parser Exception");
		}	
		assertEquals(true,bien);
		
	}
	
	public void testGuardarTaller(){
		boolean bien = false;
		try{
			guardar(this.miTaller.serialize());
		}
		catch (IOException e){
			System.out.println("se produjo un error");
		}
		try{
			Element elem = cargar();
			Taller tallerAux = new Taller(elem);
			
					bien = compararTaller(this.miTaller,tallerAux);

		} catch (IOException e1) {
				System.out.println("IO Exception");
		} catch (DocumentException e1) {
				System.out.println("Document Exception");
		} catch (XmlPullParserException e1) {
				System.out.println("XML Pull Parser Exception");
		}	
		assertEquals(true,bien);
		
	}
	
	public void testGuardarJugador(){
		boolean bien = false;
		try{
			guardar(this.miJugador.serialize());
		}
		catch (IOException e){
			System.out.println("se produjo un error");
		}
		try{
			Element elem = cargar();
			Jugador jugadorAux = new Jugador(elem);
			
					bien = compararJugador(this.miJugador,jugadorAux);

		} catch (IOException e1) {
				System.out.println("IO Exception");
		} catch (DocumentException e1) {
				System.out.println("Document Exception");
		} catch (XmlPullParserException e1) {
				System.out.println("XML Pull Parser Exception");
		}	
		assertEquals(true,bien);
		
	}
	
}