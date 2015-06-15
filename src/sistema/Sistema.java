package sistema;

import dominio.Esquina;
import dominio.Propiedad;
import dominio.Punto;
import dominio.PuntoDeInteres;
import dominio.Vendedor;
import estructuras.ArbolB;
import estructuras.Dijkstra;
import estructuras.DijkstraRubroCercano;
import estructuras.GrafoMatriz;
import estructuras.Hash;
import estructuras.HashPropiedad;
import estructuras.Lista;
import estructuras.NodoLista;
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
	public HashPropiedad tableHashProp;

	
	//**************************INICIALIZAR/DESTRUIR SISTEMA ******************************************************
	
	//PRE: El valor de “cantPuntos” > 0.
	//POST: Quedan todas las estructuras inicializadas para manejar el sistema (Hash, Arboles, etc).
	public Retorno inicializarSistema (int cantPuntos) {
		Retorno ret;
		//Verificar si la cantidad de puntos ingresado es menor o igual a 0
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

	
	
	
	//*************************REGISTRAR/ELIMINAR PUNTOS (ESQUINAS,PUNTO DE INT, PROPIEDADES)*********************
	
	//PRE: Alguna celda del Hash = null || celda del hash = (0.0,0.0).
		//coordX y coordY no existe en el sistema.
	//POST: La esquina queda registrada en el sistema.
	@Override
	public Retorno registrarEsquina(double coordX, double coordY) {
		//Verificar si hay lugar para ingresar la esquina
		if(!matrizMapa.hayLugar()){
			return new Retorno(Resultado.ERROR_1);
		}
		
		//Verificar si la las coordenadas ya existen
		if(tableHash.pertenece(coordX, coordY)){
			return new Retorno(Resultado.ERROR_2);
		}
		
		//Ingresar la esquina en el Hash general y en el grafo
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
		//Verificar si hay lugar para ingresar la esquina
		if(!matrizMapa.hayLugar()){
			return new Retorno(Resultado.ERROR_1);
		}
		
		//Verificar si el nombre ingresado es nulo o vacío
		if(nombre == null || nombre == ""){
			return new Retorno(Resultado.ERROR_2);
		}
		
		//Verificar si la las coordenadas ya existen
		if(tableHash.pertenece(coordX, coordY)){
			return new Retorno (Resultado.ERROR_3);
		}
		
		//Ingresar el Punto de Interes en el Hash general y en el grafo
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
			return new Retorno (Resultado.ERROR_4);
		}
		//si la dirección ya existe
		if(tableHashProp.pertenece(direccion)){
			return new Retorno(Resultado.ERROR_5);
		}
		//Se busca vendedor asignado y se agrega la propiedad al Hash general de puntos
		Vendedor vendAsignado = (Vendedor)this.queueDeVendedores.front();
		Propiedad p = new Propiedad(coordX, coordY, tipoPropiedad, direccion, vendAsignado.getCedula());
		int pos = tableHash.insertar(p);
		matrizMapa.agregarVertice(pos);
		
		//se asigna al hash de propiedades del sistema
		this.tableHashProp.insertar(p);
		
		//se le asigna la propiedad al vendedor
		System.out.println("nombre vendedor asignado " +vendAsignado.getNombre());
		vendAsignado.getHashPropiedades().insertar(p);
		//lo saco del frente de la queue y lo mando al final
		this.queueDeVendedores.dequeue();
		this.queueDeVendedores.enqueue(vendAsignado);
		
		return new Retorno(Resultado.OK);
	}

	//PRE: El vendedor existe. Tiene al menos una propiedad. 
	//POST: Retorna un listado que es un string con las coordenadas de todas las propiedades de las que se encarga el vendedor
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
		System.out.println("es vacio " + v.getCedula() + v.getHashPropiedades().esVacio());
		String retorno = "";
		retorno = v.listarPropiedadesDelVendedor();
		retorno = retorno.substring(0, retorno.length() - 1);
		return new Retorno(Resultado.OK, retorno);
	}
	
	//PRE: coordX y coordY existen en el sistema
	//POST: El punto queda eliminado del sistema.
	@Override
	public Retorno eliminarPuntoMapa(double coordX, double coordY) {
		if(tableHash.pertenece(coordX, coordY)){
			//Elimino vertice del Grafo
			int pos = tableHash.devolverPosActual(coordX, coordY);
			matrizMapa.eliminarVertice(pos);
			
			//Obtengo el punto para ver si es propiedad
			Punto pto = tableHash.getTabla()[pos];
			if(pto instanceof Propiedad){ //si propiedad, lo elimino de la tabla del vendedor
				String ci = ((Propiedad) pto).getCedulaVendedor();
				Vendedor v = this.arbolDeVendedores.Buscar(new Vendedor(ci)).getDato();
				v.getHashPropiedades().eliminarPropiedad(((Propiedad) pto).getDireccion());
				
				//Elimino objeto del hash de propiedades general
				this.tableHashProp.eliminarPropiedad((((Propiedad) pto).getDireccion()));
			}
			//Elimino objeto del hash de puntos
			tableHash.eliminarPunto(pos);
			return new Retorno(Resultado.OK);
		}
		else{
			return new Retorno(Resultado.ERROR_1);
		}
	}

	
	//**********************************REGISTRAR/ELIMINAR TRAMOS**********************************************
	
	//PRE: El valor de “peso” > 0
		//coordXi, coordYi, coordXf y coordYf existen en el sistema.
		//No exista un tramo con las mismas coordenadas en el sistema
	//POST:  El tramo queda registrado en el sistema
	@Override
	public Retorno registrarTramo(double coordXi, double coordYi, double coordXf, double coordYf, int peso) {
		//Verificar si el peso es menor o igual a 0
		if(peso <= 0){
			return new Retorno(Resultado.ERROR_1);
		}
		
		//Verificar si las coordenadas del tramo no existen en el sistema
		if(!tableHash.pertenece(coordXi, coordYi) || !tableHash.pertenece(coordXf, coordYf)){
			return new Retorno (Resultado.ERROR_2);
		}

        int origen = tableHash.devolverPosActual(coordXi, coordYi);
        int destino = tableHash.devolverPosActual(coordXf, coordYf);
        
        //Verificar si existe el tramo en el grafo
		if(matrizMapa.existeArista(origen, destino, coordXi, coordYi, coordXf, coordYf)){
			return new Retorno (Resultado.ERROR_3);
		}

		//Agregar arista al grafo, como es no dirigido se ingresa 2 veces cambiando origen y destino
		matrizMapa.agregarArista(origen, destino, peso, coordXi, coordYi, coordXf, coordYf);
		matrizMapa.agregarArista(destino, origen, peso, coordXf, coordYf, coordXi, coordYi);
			
		return new Retorno(Resultado.OK);
	}

	//PRE: Existen ambas coordenadas, i y f. Existe un tramo desde las coordenadas i a las f.
	//POST: Elimina el tramo existente desde las coordenadas de inicio hasta las de fin. 
	@Override
	public Retorno eliminarTramo(double coordXi, double coordYi, double coordXf, double coordYf) {
		if(!tableHash.pertenece(coordXi, coordYi) || !tableHash.pertenece(coordXf, coordYf)){
			return new Retorno (Resultado.ERROR_1);
		}
		
		int origen = tableHash.devolverPosActual(coordXi, coordYi);
        int destino = tableHash.devolverPosActual(coordXf, coordYf);
        
		//Verificar si no existe el tramo en el grafo
		if(!matrizMapa.existeArista(origen, destino, coordXi, coordYi, coordXf, coordYf)){
			return new Retorno (Resultado.ERROR_2);
		}
				
		//Eliminar arista del grafo
		matrizMapa.eliminarArista(origen, destino);
		matrizMapa.eliminarArista(destino, origen);

		return new Retorno(Resultado.OK);
	}

	
	
	
	//***************************************VENDEDOR**********************************************************
	
	//PRE: La cédula cedula cumple con el formato N.NNN.NNN-N. El celular celular cumple con el formato 09NNNNNNN. El email email cumple con el formato regular de emails. El vendedor está registrado en el sistema.
	//POST: El vendedor queda registrado en el sistema.
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
			Vendedor v = new Vendedor(cedula, nombre, email, celular);
			this.arbolDeVendedores.insertarElemento(v);
			this.queueDeVendedores.enqueue(v);
			Retorno ret = new Retorno(Resultado.OK);
			return ret;
		}
	}

	//PRE: El vendedor está registrado en el sistema. Hay al menos un vendedor más para reasignar las propiedades del que se va a eliminar.
	//POST: Eliminar el vendedor de cédula cedula, reasignando sus propiedades a los demás vendedores existentes
	@Override
	public Retorno eliminarVendedor(String cedula) {
		//el vendedor no está ingresado
		if(!this.arbolDeVendedores.existeElemento(new Vendedor(cedula))){
			return new Retorno(Resultado.ERROR_1);
		}
		//solo hay un solo vendedor registrado
		if(this.arbolDeVendedores.cantNodos() == 1){
			return new Retorno(Resultado.ERROR_2);
		}
		//se pudo eliminar exitosamente
		Vendedor vendedorAEliminar = new Vendedor(cedula);
		//ver si tiene propiedades
		if(!this.arbolDeVendedores.Buscar(vendedorAEliminar).getDato().getHashPropiedades().esVacio()){
			//asignar propiedades a otros vendedores
			int recorridos = 1;
			int key = 0;
			while(recorridos <= this.tableHash.getTamañoTabla()){			
				if(this.tableHashProp.getTabla()[key].getDato() != null &&
						this.tableHashProp.getTabla()[key].getDato().getCedulaVendedor().equals(cedula)){
					//asignamos a primer vendedor de la queue y lo insertamos al final
					Vendedor vNuevo = (Vendedor)this.queueDeVendedores.front();
					this.queueDeVendedores.dequeue();
					vNuevo.getHashPropiedades().insertar(this.tableHashProp.getTabla()[key].getDato());
					//cambiamos la ci en la prop
					this.tableHashProp.getTabla()[key].getDato().setCedulaVendedor(vNuevo.getCedula());
					this.queueDeVendedores.enqueue(vNuevo);
				}else{
					key++;
					recorridos++;
					if(key == this.tableHashProp.getTamañoTabla()){
						key = 0;
					}
				}
			}				
		}
		this.arbolDeVendedores.eliminar(vendedorAEliminar);
		this.queueDeVendedores.borrarElemento(vendedorAEliminar);
		//TODO asignar sus props
		return new Retorno(Resultado.OK);
	}
	
	//PRE: Hay al menos un vendedor registrado
	//POST: Retorna un listado que es un string con el nombre, cédula y celular de cada vendedor registrado en el sistema
	@Override
	public Retorno listadoVendedores() {
		String resultadoString = this.arbolDeVendedores.mostrarInOrder();
		resultadoString = resultadoString.substring(0, resultadoString.length() - 1);
		return new Retorno(Resultado.OK, resultadoString);
	}


	
	
	//***********************************MAPAS Y CAMINOS*******************************************************
	
	//PRE: Hay al menos un punto registrado en el sistema.
	//POST: Abre una ventana de un navegador que muestra el mapa de google con los puntos que fueron registrados.
	@Override
	public Retorno verMapa() {
		Utils.crearMapa(this.tableHash);
		return new Retorno(Resultado.OK);
	}

	//PRE: La dirección direccionPropiedad existe. Hay al menos un punto de interés del rubro rubroPuntoInteres y es alcanzable desde la dirección direccionPropiedad.
	//POST: Retorna un string listando las coordenadas de cada punto que forman parte del camino hasta el punto de interés más cercano.
	@Override
	public Retorno puntoInteresMasCercano(String direccionPropiedad, Rubro rubroPuntoInteres) {
		//propiedad de esa direccion no existe 
		if(!this.tableHashProp.pertenece(direccionPropiedad)){
			return new Retorno(Resultado.ERROR_1);
		}
		//si no hay ningun punto de interes de ese rubro
		if(!this.tableHash.existePuntoDeEseRubro(rubroPuntoInteres)){
			return new Retorno(Resultado.ERROR_2);
		}
		//si hay algún punto pero no es alcanzable
		Propiedad p = this.tableHashProp.devolverPropiedad(direccionPropiedad);
		int keyATableHash = 0;
		if(p != null){
			keyATableHash = this.tableHash.devolverPosActual(p.getCoordX(), p.getCoordY());
		}
		DijkstraRubroCercano drc = new DijkstraRubroCercano(); 
		drc.dijkstra(matrizMapa, tableHash, rubroPuntoInteres, keyATableHash);
		if(drc.resultadoFinal == null){
			return new Retorno(Resultado.ERROR_3); //error3 si no es alcanzable
		}
		return new Retorno(Resultado.OK, drc.resultadoFinal);
	}

	//PRE: La dirección direccionPropiedad existe. El punto de interés existe. Hay por lo menos un camino posible.
	//POST: Retorna un string listando las coordenadas de cada punto que forman parte del camino mínimo hasta ese punto de interés.
	@Override
	public Retorno caminoMinimo(String direccionPropiedad, Double coordX, Double coordY) {
		//esa direccion no esta registrada en el sistema
		if(!this.tableHashProp.pertenece(direccionPropiedad)){
			return new Retorno(Resultado.ERROR_1);
		}
		//ambas coordenadas no estan registradas
		if(!this.tableHash.pertenece(coordX, coordY)){
			return new Retorno(Resultado.ERROR_2);
		}
		//no hay camino posible entre prop y pto de interes
		Propiedad p = this.tableHashProp.devolverPropiedad(direccionPropiedad);
		int keyDePropEnTableHash = 0;
		if(p != null){
			keyDePropEnTableHash = this.tableHash.devolverPosActual(p.getCoordX(), p.getCoordY());
		}
		int keyDePtoInteresEnTableHash = this.tableHash.devolverPosActual(coordX, coordY);
		if(this.matrizMapa.sonAdyacentes(keyDePropEnTableHash, keyDePtoInteresEnTableHash)){
			return new Retorno(Resultado.ERROR_3);
		}
		
		Dijkstra d = new Dijkstra();

		d.dijkstra(matrizMapa, keyDePropEnTableHash, keyDePtoInteresEnTableHash);
		
		int[] caminoMin = d.imprimirCamino(matrizMapa);
		String coordenadas = this.tableHash.mostrarCoordsDeKeysEnVector(caminoMin);
		System.out.println(coordenadas);
		return new Retorno(Resultado.OK, coordenadas);
	}

}
