package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import dominio.Esquina;
import estructuras.Dijkstra;
import sistema.Retorno;
import sistema.Sistema;
import sistema.Enumerados.Rubro;

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
		ret = s.registrarTramo(2.0, 2.0, 4.0, 4.0, 4);
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
		int keyFin = s.tableHash.devolverPosActual(1.0, 1.0);
		
		s.tableHash.imprimirLista();
		
		Dijkstra d = new Dijkstra();
		d.dijkstra(s.matrizMapa, keyOrig, keyFin);
		d.imprimirCamino(s.matrizMapa);
	}
}
