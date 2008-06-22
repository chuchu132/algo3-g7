package vista;

import Modelo.Pista;
import Recursos.SpriteCache;

public class VistaPista {
	private final int FOTOGRAMAS = 3;
	private int x,y;
	private String[] spriteNames;
	private int fotogramaActual;
	private Escenario escenario;
	private SpriteCache spriteCache;
	private String dirFondo;
	
	 public VistaPista(Escenario escenario, Pista pista){
		 
		 this.escenario = escenario;
         spriteCache = escenario.getSpriteCache();
         fotogramaActual = 0;
         
		 if(pista.getCoeficienteAgarre() < 0.33){
				dirFondo = "nieve/";
				}
			else{
				if(pista.getCoeficienteAgarre()< 0.66){
					dirFondo = "lluvia/";
				}
				else{
					dirFondo = "despejado/";
				}
			}
		 for(int i=0; i < FOTOGRAMAS; i++){
			spriteNames[i] = ("pista" + i + ".jpg"); 
		 }
		 
	 }

}
