package sistema;

import sistema.Enumerados.Rubro;
import sistema.Enumerados.TipoPropiedad;

public class Mainclass {
	public static void main(String[] args) {
		Sistema s = new Sistema();
		s.inicializarSistema(5);
		
		s.registrarEsquina(-34.9055,-56.1892);
		s.registrarPropiedad(-34.9014, -56.1780, TipoPropiedad.APARTAMENTO, "hola");
		s.registrarPuntoInteres(-34.9058, -56.1927, Rubro.CAJERO, "hola");
		//s.registrarPropiedad(-34.9007, -56.1876, TipoPropiedad.APARTAMENTO, "hola");
		s.registrarPuntoInteres(-34.9032, -56.2040, Rubro.CAJERO, "hola");

		s.tableHash.imprimirLista();
		System.out.println();
		
		s.eliminarPuntoMapa(-34.9032, -56.2040);
		System.out.println();
		
		s.registrarPropiedad(-34.9019, -56.1972, TipoPropiedad.APARTAMENTO, "hola");
		
		s.tableHash.imprimirLista();
		
		s.verMapa();
	}
}
