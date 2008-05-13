package Modelo;

public class ProblemaTecnicoException  extends Exception {

	private static final long serialVersionUID = 1L;
    private String problema = "Problema Desconocido.";
    
    public ProblemaTecnicoException(String problema){
    	this.problema = problema;
    }
    
    public String getProblema(){
    	return problema;
    }
}
