package Modelo;

import java.awt.Image;
import java.util.ArrayList;

import Recursos.SoundCache;
import Recursos.SpriteCache;
import controlador.ControladorAuto;
import vista.Escenario;
import vista.VistaCarrera;


public class PrincipalPrueba  implements Escenario{
	
	
	private Motor miMotor;
	private TanqueCombustible miTanque;
	private CajaAutomatica miCaja;
	private SistemaCombustion miSistemaCombustion;
	private Carroceria miCarro;
	private TipoRueda miRueda;
	private Pista unaPista;
	private Auto miAuto;
	private Carrera picada;
	
	private SoundCache soundCache;
	private SpriteCache spriteCache;

	private ControladorAuto controlAuto;
	private VistaCarrera vistaCarrera;
    private ArrayList<Auto> corredores;	
	
	public PrincipalPrueba(){
		
		spriteCache = new SpriteCache();
		miSistemaCombustion = new SistemaCombustion(5,100,"Turbo",0.2,1);
		miMotor = new Motor(200,6,0.2,1000,400,1);
		//miCarro = new Carroceria(300,700,"Porsche Cayman S",1,0.1,1);
		miCarro = new Carroceria(300,700,"Ferrari F430",1,0.1,1);
		//miCarro = new Carroceria(300,700,"Lamborghini Gallardo",1,0.1,1);
		miCaja= new CajaAutomatica(4,200,80,1);
		miTanque = new TanqueCombustible(1000,70,1);
		miRueda = new TipoRueda(100,20,0.9,1);
		miAuto =  new Auto(miMotor,miCaja,miSistemaCombustion,miCarro,miTanque,miRueda);
	    miAuto.cargarCombustible(1000, new Nafta("Nafta de Prueba",98));
	    miAuto.encender();
	    unaPista = new Pista(1000,0.5);
   	    corredores = new ArrayList<Auto>();
	    picada = new Carrera(unaPista,corredores,miAuto,100);
		controlAuto = new ControladorAuto(miAuto);
		vistaCarrera = new VistaCarrera(controlAuto,controlAuto,unaPista,this,picada);
		picada.addObserver(vistaCarrera);
		miAuto.addObserver(vistaCarrera);
		
	}
	
    public void run(){
    	picada.correr();
    }
	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y,int width, int height) {
		return false;
	}
	
	public SoundCache getSoundCache() {
		
		return soundCache;
	}
	
	public SpriteCache getSpriteCache() {
	
		return spriteCache;
	}

	
	
	public static void main(String[] args) {
		
		PrincipalPrueba prueba = new  PrincipalPrueba();
		
	    prueba.run();
			
		}
		
		
		
	}




	


