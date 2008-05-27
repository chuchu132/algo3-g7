package Modelo;

public class TipoRueda extends Autoparte{
   
	double coeficienteAgarre;
    
	public TipoRueda(double precio, double peso,double coeficiente, long vidaUtilInicial){
		super(peso,precio, vidaUtilInicial);
		this.coeficienteAgarre = coeficiente;
	}
	
	//cuando baja la vida util la rueda pierde agarre
	public void gastar(double porcentage){
		super.gastar(porcentage);
		this.coeficienteAgarre *= (1- porcentage);
	}
	
	public double getCoeficienteAgarre() {
		return coeficienteAgarre;
	}
    
	public void simular(double tiempo) {
		//CODIFICAR
	}
	
}
