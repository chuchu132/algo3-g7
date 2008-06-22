package Modelo;

import java.awt.Image;

import Recursos.SoundCache;
import Recursos.SpriteCache;
import controlador.ControladorAuto;
import vista.Escenario;
import vista.VistaCarrera;

public class PrincipalPrueba implements Escenario{
	private Motor miMotor;
	
	private TanqueCombustible miTanque;
	
	private CajaVelocidades miCaja;
	private SistemaCombustion miSistemaCombustion;
	private Carroceria miCarro;
	private TipoRueda miRueda;
	
	private SoundCache soundCache;
	private SpriteCache spriteCache;
	private Pista unaPista;
	private Auto miAuto;
	private Carrera picada;
	private ControladorAuto controlAuto;
	private VistaCarrera vistaCarrera;
	
	public PrincipalPrueba(){
		spriteCache = new SpriteCache();
		miSistemaCombustion = new SistemaCombustion(5,100,"Turbo",0.2,1);
		miMotor = new Motor(200,6,0.2,1000,400,1);
		miCarro = new Carroceria(300,700,"Torino",1,0.1,1);
		miCaja= new CajaVelocidades(5,200,80,1);
		miTanque = new TanqueCombustible(100,70,1);
		miRueda = new TipoRueda(100,20,0.9,1);
		miAuto =  new Auto(miMotor,miCaja,miSistemaCombustion,miCarro,miTanque,miRueda);
	    miAuto.cargarCombustible(100, new Nafta("Nafta de Prueba",98));
	    miAuto.encender();
	    unaPista = new Pista(1000,0.7);
		picada = new Carrera(unaPista,miAuto,0.01);
		controlAuto = new ControladorAuto(miAuto);
		vistaCarrera = new VistaCarrera(controlAuto,controlAuto,unaPista,this,picada);
		
		
	}
	
	public void pinta(){
		vistaCarrera.pintarCarrera();
	}
	
    public void run(){
    	picada.correr();
    }
	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y,
			int width, int height) {
		// TODO Auto-generated method stub
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
		
		for(int i=0;i<100000000;i++){
			prueba.pinta();
		}
		
		//prueba.run();
		
	}




	

}
