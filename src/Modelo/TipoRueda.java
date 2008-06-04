package Modelo;

import Excepciones.ProblemaTecnicoException;
import Excepciones.RuedaRotaException;

public class TipoRueda extends Autoparte{
   
	double coeficienteAgarre;
	private final double factorDeDesgaste = 0.00005;
	
    
	public TipoRueda(double precio, double peso,double coeficiente, float vidaUtilInicial){
		super(peso,precio, vidaUtilInicial);
		this.coeficienteAgarre = coeficiente;
	
	}
	
	
	public double getCoeficienteAgarre() {
		return coeficienteAgarre;
	}
    
	//cuando baja la vida util la rueda pierde agarre
	
	public void simular(double tiempo)throws ProblemaTecnicoException {
		double desgaste;
		double porcentage;
		desgaste = factorDeDesgaste*tiempo;
		/*define un porcentaje de valor peque�o para restar en el 
		coeficiente de agarre, los numeros son solo convencion de valores
		para conservar magnitud*/
		porcentage = tiempo*(Math.pow(coeficienteAgarre,5));
		super.gastar(desgaste);
		this.coeficienteAgarre *= (1-porcentage);
		if( getVidaUtil() < getVidaUtilMinima() ){
			throw new RuedaRotaException();}
	}
	
}
