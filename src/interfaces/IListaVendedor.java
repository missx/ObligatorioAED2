package interfaces;

public interface IListaVendedor {

	//Devuelve el largo de la lista
	int largo();
	
	//Devuelve si la lista es vacía o no
	boolean esVacio();
	
	//Devuelve si el objeto pertenece o no a la lista
	boolean pertenece(Object o);
	
	//Agrega Objeto al principio de la lista
	void agregarPpio(Object o);
	
	//Borra el principio de la lista
	void borrarPpio();
	
	//Borra un elemento de la lista
	void borrar(Object o);

}
