package sistema;

import dominio.Esquina;
import dominio.Propiedad;
import dominio.PuntoDeInteres;
import dominio.Vendedor;
import estructuras.ArbolB;
import estructuras.GrafoMatriz;
import estructuras.Hash;
import estructuras.HashPropiedad;
import estructuras.Queue;
import interfaces.ISistema;
import sistema.Enumerados.Rubro;
import sistema.Enumerados.TipoPropiedad;
import sistema.Retorno.Resultado;

public class Sistema implements ISistema {
	
	//atributos
	public Queue queueDeVendedores;
	public ArbolB arbolDeVendedores;
	public GrafoMatriz matrizMapa;
	public Hash tableHash;
	public HashPropiedad tableHashProp; //xa que necesitamos el hash este aca?

	//PRE: El valor de “cantPuntos” > 0.
	//POST: Quedan todas las estructuras inicializadas para manejar el sistema (Hash, Arboles, etc).
	public Retorno inicializarSistema (int cantPuntos) {
		Retorno ret;
		if(cantPuntos <= 0){
			ret = new Retorno(Resultado.ERROR_1);
			return ret;
		}
		this.queueDeVendedores = new Queue();
		this.arbolDeVendedores = new ArbolB();
		this.matrizMapa = new GrafoMatriz();
		this.matrizMapa.crearGrafo(cantPuntos);
		this.tableHash = new Hash(cantPuntos);
		this.tableHashProp = new HashPropiedad(cantPuntos);
		
		ret = new Retorno(Resultado.OK);
		return ret;
	}
	
	//PRE:  ------
	//POST: Se destruyen todas las estructuras creadas.
	public Retorno destruirSistema() {
		this.queueDeVendedores = null;
		this.arbolDeVendedores = null;
		this.matrizMapa = null;
		this.tableHash = null;
		this.tableHashProp = null;
		
		return new Retorno(Resultado.OK);
	}

	//PRE: Alguna celda del Hash = null || celda del hash = (0.0,0.0).
		//coordX y coordY no existe en el sistema.
	//POST: La esquina queda registrada en el sistema.
	@Override
	public Retorno registrarEsquina(double coordX, double coordY) {
		if(!matrizMapa.hayLugar()){
			System.out.println("No hay lugar");
			return new Retorno(Resultado.ERROR_1);
		}
		
		if(tableHash.pertenece(coordX, coordY)){
			System.out.println("hay uno igual");
			return new Retorno(Resultado.ERROR_2);
		}
		
		Esquina e = new Esquina(coordX, coordY);
		int pos = tableHash.insertar(e);
		matrizMapa.agregarVertice(pos);
		
		return new Retorno(Resultado.OK);
	}

	//PRE: El valor de nombre != null || nombre != “” (vacio)
		//Alguna celda del Hash = null || celda del hash = (0.0,0.0).
		//coordX y coordY no existe en el sistema.
	//POST: El punto de interes queda registrado en el sistema.
	@Override
	public Retorno registrarPuntoInteres(double coordX, double coordY, Rubro rubro, String nombre) {
		if(!matrizMapa.hayLugar()){
			System.out.println("No hay lugar");
			return new Retorno(Resultado.ERROR_1);
		}
		
		if(nombre == null || nombre == ""){
			return new Retorno(Resultado.ERROR_2);
		}
		
		if(tableHash.pertenece(coordX, coordY)){
			System.out.println("hay uno igual");
			return new Retorno (Resultado.ERROR_3);
		}
		
		PuntoDeInteres pi = new PuntoDeInteres(coordX, coordY, rubro, nombre);
		int pos = tableHash.insertar(pi);
		matrizMapa.agregarVertice(pos);
		
		return new Retorno(Resultado.OK);
	}

	//PRE: Alguna celda del Hash = null || celda del hash = (0.0,0.0).
		//El valor de direccion != null || direccion != “” (vacio) y no exista en el sistema
		//Hay al menos un vendedor registrado
		//coordX y coordY no existe en el sistema.
	//POST: La propiedad queda registrada en el sistema.
	@Override
	public Retorno registrarPropiedad(double coordX, double coordY, TipoPropiedad tipoPropiedad, String direccion) {
		//no hay lugar
		if(!matrizMapa.hayLugar()){
			return new Retorno(Resultado.ERROR_1);
		}
		//si la direccion es null o vacia
		if(direccion == null || direccion == ""){
			return new Retorno(Resultado.ERROR_2);
		}
		//si no hay vendedores registrados
		if(this.arbolDeVendedores.esArbolVacio()){
			return new Retorno(Resultado.ERROR_3);
		}
		//si las coordenadas ya están siendo usadas
		if(tableHash.pertenece(coordX, coordY)){
			System.out.println("hay uno igual");
			return new Retorno (Resultado.ERROR_4);
		}
		//si la dirección ya existe
		if(tableHashProp.existeDireccion(direccion)){
			System.out.println("Error 5");
			return new Retorno(Resultado.ERROR_5);
		}
		//Se agrega la propiedad al Hash general
		Propiedad p = new Propiedad(coordX, coordY, tipoPropiedad, direccion);

		int pos = tableHash.insertar(p);
		matrizMapa.agregarVertice(pos);
		
		//se le asigna al vendedor
		Vendedor vendAsignado = (Vendedor)this.queueDeVendedores.front();
		vendAsignado.getHashPropiedades().insertar(p);
		//lo saco del frente de la queue y lo mando al final
		this.queueDeVendedores.dequeue();
		this.queueDeVendedores.enqueue(vendAsignado);
		
		return new Retorno(Resultado.OK);
	}

	//PRE: coordX y coordY existen en el sistema
	//POST: El punto queda eliminado del sistema.
	@Override
	public Retorno eliminarPuntoMapa(double coordX, double coordY) {
		if(tableHash.pertenece(coordX, coordY)){
			//Elimino vertice del Grafo
			int pos = tableHash.devolverPosActual(coordX, coordY);
			matrizMapa.eliminarVertice(pos);
			
			//Elimino objeto del hash
			tableHash.eliminarPunto(pos);
			tableHash.imprimirLista();
		}
		else{
			System.out.println("no hay uno igual");
			return new Retorno(Resultado.ERROR_1);
		}
		
		return new Retorno();
	}

	//PRE: El valor de “peso” > 0
		//coordXi, coordYi, coordXf y coordYf existen en el sistema.
		//No exista un tramo con las mismas coordenadas en el sistema
	//POST:  El tramo queda registrado en el sistema
	@Override
	public Retorno registrarTramo(double coordXi, double coordYi, double coordXf, double coordYf, int peso) {
		if(peso <= 0){
			return new Retorno(Resultado.ERROR_1);
		}
		
		if(!tableHash.pertenece(coordXi, coordYi) || !tableHash.pertenece(coordXf, coordYf)){
			return new Retorno (Resultado.ERROR_2);
		}
		
		//Si existe el tramo en el sistema error3
		
        int origen = tableHash.devolverPosActual(coordXi, coordYi);
        int destino = tableHash.devolverPosActual(coordXf, coordYf);

		matrizMapa.agregarArista(origen, destino, peso);
		matrizMapa.agregarArista(destino, origen, peso);
			
		return new Retorno(Resultado.OK);
	}

	@Override
	public Retorno eliminarTramo(double coordXi, double coordYi, double coordXf, double coordYf) {
		if(!tableHash.pertenece(coordXi, coordYi) || !tableHash.pertenece(coordXf, coordYf)){
			return new Retorno (Resultado.ERROR_1);
		}
		
		//Si existe el tramo en el sistema error2
		
		int origen = tableHash.devolverPosActual(coordXi, coordYi);
        int destino = tableHash.devolverPosActual(coordXf, coordYf);
        
		matrizMapa.eliminarArista(origen, destino);
		matrizMapa.eliminarArista(destino, origen);
        
		return new Retorno(Resultado.OK);
	}

	@Override
	public Retorno registrarVendedor(String cedula, String nombre, String email, String celular) {
		if(!Utils.verificarCedula(cedula)){
			Retorno ret = new Retorno(Resultado.ERROR_1);
			return ret;
		}else if(!Utils.verificarCelular(celular)){
			Retorno ret = new Retorno(Resultado.ERROR_2);
			return ret;
		}else if(!Utils.verificarMail(email)){
			Retorno ret = new Retorno(Resultado.ERROR_3);
			return ret;
		}else if(this.arbolDeVendedores.existeElemento(new Vendedor(cedula))){
			Retorno ret = new Retorno(Resultado.ERROR_4);
			return ret;
		}else{
			//insertar vendedor en el arbol y la queue
			this.arbolDeVendedores.insertarElemento(new Vendedor(cedula, nombre, email, celular));
			this.queueDeVendedores.enqueue(new Vendedor(cedula, nombre, email, celular));
			Retorno ret = new Retorno(Resultado.OK);
			return ret;
		}
	}

	@Override
	public Retorno listadoVendedores() {
		String resultadoString = this.arbolDeVendedores.mostrarInOrder();
		return new Retorno(Resultado.OK, resultadoString);
	}

	@Override
	public Retorno eliminarVendedor(String cedula) {
		//el vendedor no está ingresado
		if(!this.arbolDeVendedores.existeElemento(new Vendedor(cedula))){
			return new Retorno(Resultado.ERROR_1);
		}else{
			//se pudo eliminar exitosamente
			this.arbolDeVendedores.eliminar(new Vendedor(cedula));
			this.queueDeVendedores.borrarElemento(new Vendedor(cedula));
			return new Retorno(Resultado.OK);
		}
	}

	@Override
	public Retorno verMapa() {
		Utils.crearMapa(this.tableHash);
		return new Retorno(Resultado.OK);
	}

	@Override
	public Retorno puntoInteresMasCercano(String direccionPropiedad, Rubro rubroPuntoInteres) {
		//TODO reemplazar por su implementacion
		return new Retorno();
	}

	@Override
	public Retorno caminoMinimo(String direccionPropiedad, Double coordX, Double coordY) {
		//TODO reemplazar por su implementacion
		return new Retorno();
	}

	@Override
	public Retorno listadoPropiedades(String cedulaVendedor) {
		//si el vendedor no existe
		if(!this.arbolDeVendedores.existeElemento(new Vendedor(cedulaVendedor))){
			return new Retorno(Resultado.ERROR_1);
		}
		//si no tiene propiedades
		Vendedor v = this.arbolDeVendedores.Buscar(new Vendedor(cedulaVendedor)).getDato();
		if(v.getHashPropiedades().esVacio()){
			return new Retorno(Resultado.ERROR_2);
		}
		//si no listar propiedades
		return new Retorno(Resultado.OK, v.listarPropiedades());
	}

	

	
}
