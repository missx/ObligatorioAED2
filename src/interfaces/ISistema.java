package interfaces;

import sistema.Enumerados;
import sistema.Retorno;
import sistema.Enumerados.Rubro;
import sistema.Enumerados.TipoPropiedad;


// Interfaz del sistema
// No modificar esta clase!!!!!!!!!
public interface ISistema {

	Retorno inicializarSistema (int cantPuntos);
	
	Retorno destruirSistema();
	
	Retorno registrarEsquina(double coordX, double coordY);
	
	Retorno registrarPuntoInteres(double coordX, double coordY, Rubro rubro, String nombre);
	
	Retorno registrarPropiedad(double coordX, double coordY, TipoPropiedad tipoPropiedad, String direccion);
	
	Retorno eliminarPuntoMapa(double coordX, double coordY);
	
	Retorno registrarTramo(double coordXi, double coordYi, double coordXf, double coordYf, int largo);
	
	Retorno eliminarTramo(double coordXi, double coordYi, double coordXf, double coordYf);
	
	Retorno registrarVendedor(String cedula, String nombre, String email, String celular);
	
	Retorno listadoVendedores();
	
	Retorno eliminarVendedor(String cedula);
	
	Retorno verMapa();
	
	Retorno puntoInteresMasCercano(String direccionPropiedad, Rubro rubroPuntoInteres);
	
	Retorno caminoMinimo(String direccionPropiedad, Double coordX, Double coordY);
	
	Retorno listadoPropiedades(String cedulaVendedor);
	
}
