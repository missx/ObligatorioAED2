package estructuras;

import interfaces.ILista;

public class Lista implements ILista{

	private NodoLista inicio;
	private NodoLista fin;
	
	public NodoLista getInicio() {
		return inicio;
	}

	public void setInicio(NodoLista inicio) {
		this.inicio = inicio;
	}

	public NodoLista getFin() {
		return fin;
	}

	public void setFin(NodoLista fin) {
		this.fin = fin;
	}
	
	public Lista(){
		this.inicio = null;
		this.fin = null;
	}
	

	@Override
	public boolean esVacia() {
		return this.inicio == null;
	}

	@Override
	public void vaciarLista() {
		while(this.inicio != null)
			this.borrarInicio();
	}

	@Override
	public void insertarInicio(Object elem) {
		NodoLista nuevo = new NodoLista(elem);
		//ponemos como siguiente del nuevo, el primer nodo actual
		nuevo.setSig(this.getInicio());
		//asignamos el nuevo como primero
		this.setInicio(nuevo);
		if(this.fin == null) this.fin = nuevo;
	}

	@Override
	public void insertarFin(Object elem) {
		if(this.esVacia()) this.insertarInicio(elem);
		else{
			NodoLista aux = this.inicio;
			while(aux.getSig() != null) aux = aux.getSig();
			NodoLista nuevo = new NodoLista(elem);
			aux.setSig(nuevo);
			this.fin = nuevo;
		}
	}

	@Override
	public void borrarInicio() {
		if(!this.esVacia()){
			this.inicio = this.inicio.getSig();
		}
	}

	@Override
	public void borrarFin() {
		if(!this.esVacia()){
			if(this.inicio == this.fin){
				this.borrarInicio();
			}
			else{
				NodoLista aux = this.inicio;
				while(aux.getSig().getSig() != null)
					aux = aux.getSig();
				this.fin = aux;
				this.fin.setSig(null);
				
			}
		}
	}

	@Override
	public void borrarUnElem(Object elem) {
		if(!this.esVacia()){
			if(this.inicio.getDato().equals(elem))
				this.borrarInicio();
			else{
				NodoLista aux = this.getInicio();
				while(aux.getSig() != null && !aux.getSig().getDato().equals(elem))
					aux = aux.getSig();
				if(aux.getSig() != null){
					System.out.println("aux getsig");
					NodoLista aBorrar = aux.getSig();
					aux.setSig(aBorrar.getSig());
					aBorrar.setSig(null);
				}
				
			}
		}
	}

	@Override
	public int largo() {
		int cont = 0;
		
		if(!this.esVacia()){
			NodoLista aux = this.inicio;
			while(aux!= null){
				cont++;
				aux = aux.getSig();
			}
		}
		return cont;
	}

	@Override
	public boolean existe(Object elem) {
		if(!this.esVacia()){
			NodoLista aux = this.getInicio();
			while(aux != null && !aux.getDato().equals(elem)) aux = aux.getSig();
			return aux != null;
		}
		return false;
	}

	
	
}
