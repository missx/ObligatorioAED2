package interfaces;

public interface ILista {

boolean esVacia();
	
	void vaciarLista();
	
	void insertarInicio(Object elem);
	
	void insertarFin(Object elem);
	
	void borrarInicio();

	void borrarFin();
	
	void borrarUnElem(Object elem);
	
	int largo();
	
	boolean existe(Object elem);

}
