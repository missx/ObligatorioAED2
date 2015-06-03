package sistema;

import dominio.Esquina;
import dominio.Propiedad;
import dominio.PuntoDeInteres;
import dominio.Vendedor;
import estructuras.ArbolB;
import estructuras.GrafoMatriz;
import estructuras.Hash;
import estructuras.Queue;
import interfaces.ISistema;
import sistema.Enumerados.Rubro;
import sistema.Enumerados.TipoPropiedad;
import sistema.Retorno.Resultado;



public class Sistema implements ISistema {

	public Queue queueDeVendedores;
	public ArbolB arbolDeVendedores;
	public GrafoMatriz matrizMapa;
	public Hash tableHash;
	
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
		
		ret = new Retorno(Resultado.OK);
		return ret;
	}
	
	public Retorno destruirSistema() {
		this.queueDeVendedores = null;
		this.arbolDeVendedores = null;
		this.matrizMapa = null;
		this.tableHash = null;
		
		return new Retorno(Resultado.OK);
	}

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

	@Override
	public Retorno registrarPropiedad(double coordX, double coordY, TipoPropiedad tipoPropiedad, String direccion) {
		if(!matrizMapa.hayLugar()){
			return new Retorno(Resultado.ERROR_1);
		}
		
		if(direccion == null || direccion == ""){
			return new Retorno(Resultado.ERROR_2);
		}
		
		//Falta error 3 si no existen vendedores registrados
		
		if(tableHash.pertenece(coordX, coordY)){
			System.out.println("hay uno igual");
			return new Retorno (Resultado.ERROR_4);
		}
		
		//Falta error 5 si la direccion  direccion ya existe en el sistema
		
		//Falta asignarle el vendedor
		
		Propiedad p = new Propiedad(coordX, coordY, tipoPropiedad, direccion);
		int pos = tableHash.insertar(p);
		matrizMapa.agregarVertice(pos);
		
		return new Retorno(Resultado.OK);
	}

	@Override
	public Retorno eliminarPuntoMapa(double coordX, double coordY) {
		// TODO reemplazar por su implementacion
		return new Retorno();
	}

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
		// TODO reemplazar por su implementacion
		return new Retorno();
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
		// TODO reemplazar por su implementacion
		return new Retorno();
	}

	@Override
	public Retorno puntoInteresMasCercano(String direccionPropiedad, Rubro rubroPuntoInteres) {
		// TODO reemplazar por su implementacion
		return new Retorno();
	}

	@Override
	public Retorno caminoMinimo(String direccionPropiedad, Double coordX, Double coordY) {
		// TODO reemplazar por su implementacion
		return new Retorno();
	}

	@Override
	public Retorno listadoPropiedades(String cedulaVendedor) {
		// TODO reemplazar por su implementacion
		return new Retorno();
	}

	

	
}
