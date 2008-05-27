package Modelo;

public class Rueda extends Autoparte{
   
	double coeficienteAgarre;
    
	public Rueda(double precio, double peso,double coeficiente){
		super(peso,precio);
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
    
}
