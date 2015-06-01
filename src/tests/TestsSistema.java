package tests;

import static org.junit.Assert.*;
import interfaces.ISistema;

import org.junit.Test;

import dominio.Vendedor;
import sistema.Retorno;
import sistema.Sistema;
import sistema.Retorno.Resultado;

public class TestsSistema {

	@Test
	public void testRegistroVendedor() {
		ISistema sistema = new Sistema();
		sistema.inicializarSistema(10);
		//Datos de la prueba
		String cedula = "3.702.156-9";
		String nombre = "Omar";
		String email = "omar@gmail.com";
		String celular = "098123456";
		//Estimulo
		Retorno ret = sistema.registrarVendedor(cedula, nombre, email, celular);
		assertEquals(Retorno.Resultado.OK, ret.resultado);	//Deberia retornar OK
		
	}

	
	@Test
	public void testListadoVendedor() {
		Sistema sistema = new Sistema();
		sistema.inicializarSistema(10);
		//Datos de la prueba
		String cedula = "3.702.156-9";
		String nombre = "Omar";
		String email = "omar@gmail.com";
		String celular = "098123456";
		//Estimulo
		Retorno ret = sistema.registrarVendedor(cedula, nombre, email, celular);
		assertEquals(Retorno.Resultado.OK, ret.resultado);	//Deberia retornar OK
		ret = sistema.listadoVendedores();
		System.out.println("retvalorstr " + ret.valorString);
		System.out.println(sistema.arbolDeVendedores.cantNodos());
		assertEquals(Retorno.Resultado.OK, ret.resultado);	//Deberia retornar OK
		// El valor string deberia contener los datos del vendedor ingresado
		assertTrue(ret.valorString.contains(cedula) && ret.valorString.contains(nombre) && ret.valorString.contains(celular));
		
	}
	
	@Test
	public void testListadoVendedorConVarios(){
		Sistema sistema = new Sistema();
		sistema.inicializarSistema(10);
		//Datos de la prueba
		//V1
		String cedula = "3.702.156-9";
		String nombre = "Omar";
		String email = "omar@gmail.com";
		String celular = "098123456";
		//V2
		String cedula1 = "4.702.156-9";
		String nombre1 = "Luis";
		String email1 = "luis@gmail.com";
		String celular1 = "099548554";
		//V3
		String cedula2 = "3.602.156-9";
		String nombre2 = "Ana";
		String email2 = "ana@gmail.com";
		String celular2 = "091455874";
		
		//Estimulo
		Retorno ret = sistema.registrarVendedor(cedula, nombre, email, celular);
		assertEquals(Retorno.Resultado.OK, ret.resultado);	//Deberia retornar OK
		Retorno ret1 = sistema.registrarVendedor(cedula1, nombre1, email1, celular1);
		assertEquals(Retorno.Resultado.OK, ret1.resultado);	//Deberia retornar OK
		Retorno ret2 = sistema.registrarVendedor(cedula2, nombre2, email2, celular2);
		assertEquals(Retorno.Resultado.OK, ret2.resultado);	//Deberia retornar OK
		
		Retorno retListado = sistema.listadoVendedores();
		assertEquals(Retorno.Resultado.OK, retListado.resultado);	//Deberia retornar OK
		// El valor string deberia contener los datos del vendedor ingresado
		assertTrue(retListado.valorString.contains(cedula) && retListado.valorString.contains(nombre) && retListado.valorString.contains(celular) &&
				retListado.valorString.contains(cedula1) && retListado.valorString.contains(nombre1) && retListado.valorString.contains(celular1) &&
				retListado.valorString.contains(cedula2) && retListado.valorString.contains(nombre2) && retListado.valorString.contains(celular2));
		
	}
	
	@Test
	public void testEliminarVendedor(){
		Sistema sis = new Sistema();
		sis.inicializarSistema(10);
		//Datos de la prueba
		//V1
		String cedula = "3.702.156-9";
		String nombre = "Omar";
		String email = "omar@gmail.com";
		String celular = "098123456";
		//V2
		String cedula1 = "4.702.156-9";
		String nombre1 = "Luis";
		String email1 = "luis@gmail.com";
		String celular1 = "099548554";
		//V3
		String cedula2 = "3.602.156-9";
		String nombre2 = "Ana";
		String email2 = "ana@gmail.com";
		String celular2 = "091455874";
		
		//Estimulo
		Retorno ret = sis.registrarVendedor(cedula, nombre, email, celular);
		assertEquals(Retorno.Resultado.OK, ret.resultado);	//Deberia retornar OK
		Retorno ret1 = sis.registrarVendedor(cedula1, nombre1, email1, celular1);
		assertEquals(Retorno.Resultado.OK, ret1.resultado);	//Deberia retornar OK
		Retorno ret2 = sis.registrarVendedor(cedula2, nombre2, email2, celular2);
		assertEquals(Retorno.Resultado.OK, ret2.resultado);	//Deberia retornar OK
		
		assertEquals(sis.arbolDeVendedores.cantNodos(), 3);
		assertEquals(sis.queueDeVendedores.cantNodos(), 3);
		
		Retorno retEliminar = sis.eliminarVendedor("3.602.156-9");
		assertEquals(Retorno.Resultado.OK, retEliminar.resultado);
		retEliminar = sis.eliminarVendedor("5.484.245-2");
		assertEquals(Retorno.Resultado.ERROR_1, retEliminar.resultado);	
		
		assertEquals(sis.arbolDeVendedores.cantNodos(), 2);
		assertEquals(sis.queueDeVendedores.cantNodos(), 2);
		
	}

}
