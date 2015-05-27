package sistema;

import dominio.Vendedor;
import estructuras.ArbolB;
import estructuras.GrafoMatriz;
import estructuras.Queue;
import interfaces.ISistema;
import sistema.Enumerados.Rubro;
import sistema.Enumerados.TipoPropiedad;
import sistema.Retorno.Resultado;



public class Sistema implements ISistema {

	public Queue queueDeVendedores;
	public ArbolB arbolDeVendedores;
	public GrafoMatriz matrizMapa;
	
	
	public Retorno inicializarSistema (int cantPuntos) {
		// inicializar arboles y queue de vendedores
		this.queueDeVendedores = new Queue();
		this.arbolDeVendedores = new ArbolB();
		this.matrizMapa = new GrafoMatriz();
		this.matrizMapa.crearGrafo(10);
		//TODO inicializar resto de estructuras
		
		return new Retorno();
	}
	
	public Retorno destruirSistema() {
		this.queueDeVendedores = null;
		this.arbolDeVendedores = null;
		this.matrizMapa = null;
		// TODO reemplazar con otras estructuras
		return new Retorno(Resultado.OK);
	}

	@Override
	public Retorno registrarEsquina(double coordX, double coordY) {
		// TODO reemplazar por su implementacion
		return new Retorno();
	}

	@Override
	public Retorno registrarPuntoInteres(double coordX, double coordY, Rubro rubro, String nombre) {
		// TODO reemplazar por su implementacion
		return new Retorno();
	}

	@Override
	public Retorno registrarPropiedad(double coordX, double coordY, TipoPropiedad tipoPropiedad, String direccion) {
		// TODO reemplazar por su implementacion
		return new Retorno();
	}

	@Override
	public Retorno eliminarPuntoMapa(double coordX, double coordY) {
		// TODO reemplazar por su implementacion
		return new Retorno();
	}

	@Override
	public Retorno registrarTramo(double coordXi, double coordYi, double coordXf, double coordYf, int largo) {
		// TODO reemplazar por su implementacion
		return new Retorno();
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
			this.arbolDeVendedores.insertarElemento(new Vendedor(nombre, cedula, celular, email));
			this.queueDeVendedores.enqueue(new Vendedor(nombre, cedula, celular, email));
			Retorno ret = new Retorno(Resultado.OK);
			return ret;
		}
	}

	@Override
	public Retorno listadoVendedores() {
		// TODO reemplazar por su implementacion
		return new Retorno();
	}

	@Override
	public Retorno eliminarVendedor(String cedula) {
		// TODO reemplazar por su implementacion
		return new Retorno();
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
