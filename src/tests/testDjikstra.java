package tests;

import static org.junit.Assert.assertEquals;
import interfaces.ISistema;

import org.junit.Test;

import dominio.Esquina;
import estructuras.Dijkstra;
import estructuras.DijkstraRubroCercano;
import sistema.Retorno;
import sistema.Sistema;
import sistema.Enumerados.Rubro;
import sistema.Enumerados.TipoPropiedad;

public class testDjikstra {

	@Test
	public void testCaminoMasCorto(){
		Sistema s = new Sistema();
		s.inicializarSistema(8);
		
		Retorno ret = s.registrarEsquina(1.0,1.0);
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		ret = s.registrarPuntoInteres(2.0, 2.0, Rubro.CAJERO, "hola");
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		ret = s.registrarPuntoInteres(3.0, 3.0, Rubro.CAJERO, "hola");
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		ret = s.registrarEsquina(4.0, 4.0);
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		ret = s.registrarEsquina(5.0, 5.0);
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		ret = s.registrarPuntoInteres(6.0, 6.0, Rubro.CAJERO, "hola");
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		ret = s.registrarEsquina(7.0, 7.0);
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		ret = s.registrarEsquina(8.0, 8.0);
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		
		ret = s.registrarTramo(1.0, 1.0, 2.0, 2.0, 2);
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		ret = s.registrarTramo(1.0, 1.0, 4.0, 4.0, 14);
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		
		ret = s.registrarTramo(2.0, 2.0, 3.0, 3.0, 1);
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		ret = s.registrarTramo(2.0, 2.0, 6.0, 6.0, 2);
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		ret = s.registrarTramo(2.0, 2.0, 4.0, 4.0, 1);
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		ret = s.registrarTramo(2.0, 2.0, 5.0, 5.0, 9);
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		
		ret = s.registrarTramo(3.0, 3.0, 6.0, 6.0, 4);
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		
		ret = s.registrarTramo(4.0, 4.0, 7.0, 7.0, 6);
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		ret = s.registrarTramo(4.0, 4.0, 5.0, 5.0, 1);
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		
		ret = s.registrarTramo(5.0, 5.0, 6.0, 6.0, 1);
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		ret = s.registrarTramo(5.0, 5.0, 8.0, 8.0, 1);
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		ret = s.registrarTramo(5.0, 5.0, 7.0, 7.0, 3);
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		
		ret = s.registrarTramo(6.0, 6.0, 8.0, 8.0, 3);
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		
		ret = s.registrarTramo(7.0, 7.0, 8.0, 8.0, 1);
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		
		int keyOrig = s.tableHash.devolverPosActual(7.0,7.0);
		int keyFin = s.tableHash.devolverPosActual(2.0, 2.0);
		
		s.tableHash.imprimirLista();
		
		//caminoMinimo(String direccion, Double coordX, Double coordY);
		
		Dijkstra d = new Dijkstra();
		d.dijkstra(s.matrizMapa, keyOrig, keyFin);
		d.imprimirCamino(s.matrizMapa);
		System.out.println(" ");
	}
	
	@Test
	public void testCaminoMasCorto2(){
		Sistema s = new Sistema();
		s.inicializarSistema(8);
		assertEquals(Retorno.Resultado.OK, s.registrarVendedor("5.555.555-5", "Omar", "omar@gmail.com", "098123456").resultado);
		assertEquals(Retorno.Resultado.OK, s.registrarPropiedad(1.0, 1.0, TipoPropiedad.CASA, "Rio Negro 1188").resultado);//P1

		Retorno ret = s.registrarPuntoInteres(2.0, 2.0, Rubro.CAJERO, "hola");
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		ret = s.registrarPuntoInteres(3.0, 3.0, Rubro.CAJERO, "hola");
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		ret = s.registrarEsquina(4.0, 4.0);
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		ret = s.registrarEsquina(5.0, 5.0);
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		ret = s.registrarPuntoInteres(6.0, 6.0, Rubro.CAJERO, "hola");
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		ret = s.registrarEsquina(7.0, 7.0);
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		ret = s.registrarEsquina(8.0, 8.0);
		assertEquals(Retorno.Resultado.OK, ret.resultado);

		ret = s.registrarTramo(1.0, 1.0, 2.0, 2.0, 1);
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		ret = s.registrarTramo(1.0, 1.0, 5.0, 5.0, 3);
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		
		ret = s.registrarTramo(2.0, 2.0, 4.0, 4.0, 6);
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		ret = s.registrarTramo(2.0, 2.0, 5.0, 5.0, 1);
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		
		ret = s.registrarTramo(3.0, 3.0, 4.0, 4.0, 2);
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		
		ret = s.registrarTramo(4.0, 4.0, 6.0, 6.0, 4);
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		
		ret = s.registrarTramo(5.0, 5.0, 6.0, 6.0, 7);
		assertEquals(Retorno.Resultado.OK, ret.resultado);
				
		int keyOrig = s.tableHash.devolverPosActual(1.0,1.0);
		int keyFin = s.tableHash.devolverPosActual(6.0, 6.0);
		
		s.tableHash.imprimirLista();
		String resEsperado = "1.0;1.0|5.0;5.0|6.0;6.0";
		assertEquals(resEsperado, s.caminoMinimo("Rio Negro 1188", 6.0, 6.0).valorString);
		System.out.println(" ");
	}
	
	@Test
	public void rubroMasCercano(){
		Sistema s = new Sistema();
		s.inicializarSistema(8);
		
		Retorno ret = s.registrarPuntoInteres(1.0, 1.0, Rubro.CAJERO, "Abitab");
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		ret = s.registrarPuntoInteres(2.0, 2.0, Rubro.CAJERO, "hola");
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		ret = s.registrarPuntoInteres(3.0,3.0, Rubro.CENTRO_COMERCIAL, "Nuevo Centro");
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		ret = s.registrarEsquina(4.0, 4.0);
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		ret = s.registrarEsquina(5.0, 5.0);
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		ret = s.registrarPuntoInteres(6.0,6.0, Rubro.FARMACIA, "Cruz Roja");
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		ret = s.registrarEsquina(7.0, 7.0);
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		ret = s.registrarEsquina(8.0, 8.0);
		assertEquals(Retorno.Resultado.OK, ret.resultado);

		ret = s.registrarTramo(1.0, 1.0, 2.0, 2.0, 1);
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		ret = s.registrarTramo(1.0, 1.0, 5.0, 5.0, 3);
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		
		ret = s.registrarTramo(2.0, 2.0, 4.0, 4.0, 6);
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		ret = s.registrarTramo(2.0, 2.0, 5.0, 5.0, 1);
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		
		ret = s.registrarTramo(3.0, 3.0, 4.0, 4.0, 2);
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		
		ret = s.registrarTramo(4.0, 4.0, 6.0, 6.0, 4);
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		
		ret = s.registrarTramo(5.0, 5.0, 6.0, 6.0, 7);
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		
		s.tableHash.imprimirLista();
		int keyOrig = s.tableHash.devolverPosActual(1.0,1.0);
		
		DijkstraRubroCercano drc = new DijkstraRubroCercano();
		drc.dijkstra(s.matrizMapa, s.tableHash, Rubro.CENTRO_COMERCIAL, keyOrig);
		System.out.println(" ");
	}
	
}
