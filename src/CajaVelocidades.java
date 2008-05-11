
public class CajaVelocidades extends Autoparte{
	private int cambioActual;
	private int cantidadCambios;
	private double [] relaciones;
	
	
	public CajaVelocidades (int cantidadCambios,double precio, double peso){
		super(precio,peso,1);
		cambioActual = 0;
		this.cantidadCambios = cantidadCambios;
		relaciones = new double [cantidadCambios + 1];
		
		relaciones[0] = 0;
		for(int i = 1; i < (cantidadCambios + 1); i++) 
			relaciones[i] = ((cantidadCambios+1-i)/cantidadCambios ); 
	}

		
	public void subirCambio () {
		if(cambioActual < cantidadCambios)
			cambioActual++;
		 }
	
	public void bajarCambio (){
		if(cambioActual > 0)
				cambioActual--;
	}
	

	public int getCambioActual (){
		return cambioActual;
	}
	
				
	public double obtenerRelacion() {
		return relaciones[cambioActual];
	}


}
