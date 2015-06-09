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
		s.inicializarSistema(5);
		
		Esquina e = new Esquina(-34.9055, -56.1892);
		Retorno ret = s.registrarEsquina(-34.9055,-56.1892);
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		ret = s.registrarPuntoInteres(-34.9058, -56.1927, Rubro.CAJERO, "hola");
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		ret = s.registrarPuntoInteres(-34.9032, -56.2040, Rubro.CAJERO, "hola");
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		ret = s.registrarEsquina(-34.9059,-56.1929);
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		ret = s.registrarEsquina(-34.9039,-56.2049);
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		
		ret = s.registrarTramo(-34.9055, -56.1892, -34.9058, -56.1927, 4);
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		ret = s.registrarTramo(-34.9032, -56.2040, -34.9055, -56.1892, 3);
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		ret = s.registrarTramo(-34.9058, -56.1927, -34.9032, -56.2040, 1);
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		ret = s.registrarTramo(-34.9059, -56.1929, -34.9039, -56.2049, 5);
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		ret = s.registrarTramo(-34.9058, -56.1927, -34.9039, -56.2049, 2);
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		
		int keyOrig = s.tableHash.h(e.getCoordX(), e.getCoordY());
		Dijkstra d = new Dijkstra();
		d.dijkstra(s.matrizMapa, keyOrig);
		d.imprimirCamino(s.matrizMapa);
	}
}
