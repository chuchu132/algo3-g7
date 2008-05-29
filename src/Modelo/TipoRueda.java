package Modelo;

import Excepciones.ProblemaTecnicoException;

public class TipoRueda extends Autoparte{
   
	double coeficienteAgarre;
	private double factorDeDesgaste = 0.5;
    
	public TipoRueda(double precio, double peso,double coeficiente, long vidaUtilInicial){
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
		desgaste = 100/(getPorcentajeVidaUtil()-(factorDeDesgaste*tiempo));
		/*define un porcentage de valor pequeño para restar en el 
		coeficiente de agarre, los numeros son solo convencion de valores
		para conservar magnitud*/
		porcentage = tiempo/(Math.pow(coeficienteAgarre,5));
		super.gastar(desgaste);
		this.coeficienteAgarre *= (1-porcentage);
		if( getVidaUtil() < getVidaUtilMinima() ){
			throw new ProblemaTecnicoException();}
	}
	
}
