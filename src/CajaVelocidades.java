
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
		embragando = false;
		relaciones = new int [cantidadCambios];
		tiempodeFuerza = new int [cantidadCambios];
		
		relaciones[0] = 0;
		for(int i = 1; i < cantidadCambios; i++) 
			relaciones[i] = (1/ (i ^ 2)); 
		
	}
	
	public void presionarEmbrague(){
		embragando = true;
	}
	
	public void soltarEmbrague(){
		embragando = false;
	}
	
	public void subirCambio () {
		if(embragando == true) {
			if(cambioActual < cantidadCambios)
				cambioActual++;
		}
	}
	
	public void bajarCambio (){
		if (embragando == true) {
			if(cambioActual > 0)
				cambioActual--;
		}
	}

	public int getCambioActual (){
		return cambioActual;
	}
	
	public boolean getEstadoEmbrague () {
		return embragando;
	}
			
	public int obtenerRelacion() {
		return relaciones[cambioActual];
	}


}
