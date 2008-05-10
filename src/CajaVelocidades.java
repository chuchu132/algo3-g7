
public class CajaVelocidades extends Autoparte{
	private int cambioActual;
	private int cantidadCambios;
	private boolean embragando;
	private int [] relaciones;
	private int [] tiempodeFuerza;
	
	public CajaVelocidades (int cantidadCambios,double precio, double peso){
		super(precio,peso,1);
		cambioActual = 0;
		this.cantidadCambios = cantidadCambios;
		relaciones = new int [cantidadCambios];
		tiempodeFuerza = new int [cantidadCambios];
		
		relaciones[0] = 0;
		for(int i = 1; i < cantidadCambios; i++) 
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
	
				
	public int obtenerRelacion() {
		return relaciones[cambioActual];
	}


}
