package Modelo;

import java.util.ArrayList;

import Excepciones.ProductoInexistenteException;

public abstract class FabricaDeProductosVendibles  {

	 //public  FabricaDeProductosVendibles();
	abstract public ArrayList<Producto> armarLista();
	abstract public Vendible crearProductoNumero(int n)throws ProductoInexistenteException;

}
