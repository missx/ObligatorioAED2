package tests;

import static org.junit.Assert.*;

import interfaces.ISistema;
import org.junit.Test;

import sistema.Enumerados.Rubro;
import sistema.Retorno;
import sistema.Sistema;
import sistema.Retorno.Resultado;

public class TestsSistema {
	

	@Test
	public void testInicializarSistemaNumIncorrecto(){
		ISistema sistema = new Sistema();
		Retorno ret = sistema.inicializarSistema(-10);
		assertEquals(Retorno.Resultado.ERROR_1, ret.resultado);
		
		sistema.destruirSistema();
		ret = sistema.inicializarSistema(0);
		assertEquals(Retorno.Resultado.ERROR_1, ret.resultado);
	}
	
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
		ret = sistema.listadoVendedores();
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

	@Test
	public void testRegistrarEsquinaCorrectamente(){
		ISistema s = new Sistema();
		s.inicializarSistema(10);
		

		Retorno ret = s.registrarEsquina(34.764167,56.213889);
		assertEquals(Retorno.Resultado.OK, ret.resultado);	//Deberia retornar OK
		ret = s.registrarEsquina(-34.905, -56.190);
		assertEquals(Retorno.Resultado.OK, ret.resultado);	//Deberia retornar OK
		ret = s.registrarEsquina(34.851944,56.188333);
		assertEquals(Retorno.Resultado.OK, ret.resultado);	//Deberia retornar OK
		ret = s.registrarEsquina(-39.905, -56.190);
		assertEquals(Retorno.Resultado.OK, ret.resultado);	//Deberia retornar OK
		ret = s.registrarEsquina(32.516667, 54.533333);
		assertEquals(Retorno.Resultado.OK, ret.resultado);	//Deberia retornar OK
		
	}
	
	@Test
	public void testRegistrarEsquinaExcederMasUno(){
		ISistema s = new Sistema();
		s.inicializarSistema(3);
		
		//Crear datos
		Retorno ret = s.registrarEsquina(34.764167,56.213889);
		assertEquals(Retorno.Resultado.OK, ret.resultado);	//Deberia retornar OK
		ret = s.registrarEsquina(-34.905, -56.190);
		assertEquals(Retorno.Resultado.OK, ret.resultado);	//Deberia retornar OK
		ret = s.registrarEsquina(34.851944,56.188333);
		assertEquals(Retorno.Resultado.OK, ret.resultado);	//Deberia retornar OK
		ret = s.registrarEsquina(-39.905, -56.190);
		assertEquals(Retorno.Resultado.ERROR_1, ret.resultado);	//Deberia retornar OK
		ret = s.registrarEsquina(32.516667, 54.533333);
		assertEquals(Retorno.Resultado.ERROR_1, ret.resultado);	//Deberia retornar OK
	}

	@Test
	public void testRegistroEsquinaExistente(){
		ISistema s = new Sistema();
		s.inicializarSistema(4);
		
		//Crear datos
		Retorno ret = s.registrarEsquina(-33.905,-56.188);
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		ret = s.registrarEsquina(-34.905, -56.190);
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		ret = s.registrarEsquina(-39.905,-56.188);
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		ret = s.registrarEsquina(-39.905,-56.188);
		assertEquals(Retorno.Resultado.ERROR_2, ret.resultado);	
	}
	
	
	

	@Test
	public void testRegistroPuntoDeInteresCorrectamente(){
		ISistema s = new Sistema();
		s.inicializarSistema(3);
		
		//Crear datos
		Retorno ret = s.registrarPuntoInteres(34.764167,56.213889, Rubro.CAJERO, "Abitab");
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		ret = s.registrarPuntoInteres(-34.905, -56.190, Rubro.CENTRO_COMERCIAL, "Nuevo Centro");
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		ret = s.registrarPuntoInteres(34.851944,56.188333, Rubro.FARMACIA, "Cruz Roja");
		assertEquals(Retorno.Resultado.OK, ret.resultado);
	}
	
	@Test
	public void testRegistrarPuntoDeInteresExcederMasUno(){
		ISistema s = new Sistema();
		s.inicializarSistema(3);
		
		//Crear datos
		Retorno ret = s.registrarPuntoInteres(34.764167,56.213889, Rubro.CAJERO, "Abitab");
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		ret = s.registrarPuntoInteres(-34.905, -56.190, Rubro.CENTRO_COMERCIAL, "Nuevo Centro");
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		ret = s.registrarPuntoInteres(34.851944,56.188333, Rubro.FARMACIA, "Cruz Roja");
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		ret = s.registrarPuntoInteres(-39.905, -56.190, Rubro.SUPERMERCADO, "Devoto");
		assertEquals(Retorno.Resultado.ERROR_1, ret.resultado);
	}
	
	@Test
	public void testRegistrarPuntoDeInteresErrorNombre(){
		ISistema s = new Sistema();
		s.inicializarSistema(4);
		
		//Crear datos
		Retorno ret = s.registrarPuntoInteres(34.764167,56.213889, Rubro.CAJERO, "Abitab");
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		ret = s.registrarPuntoInteres(-34.905, -56.190, Rubro.CENTRO_COMERCIAL, "Nuevo Centro");
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		ret = s.registrarPuntoInteres(34.851944,56.188333, Rubro.FARMACIA, "Cruz Roja");
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		ret = s.registrarPuntoInteres(-39.905, -56.190, Rubro.SUPERMERCADO, "");
		assertEquals(Retorno.Resultado.ERROR_2, ret.resultado);
		
		s.destruirSistema();
		s.inicializarSistema(4);
		
		//Crear datos
		ret = s.registrarPuntoInteres(34.764167,56.213889, Rubro.CAJERO, "Abitab");
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		ret = s.registrarPuntoInteres(-34.905, -56.190, Rubro.CENTRO_COMERCIAL, "Nuevo Centro");
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		ret = s.registrarPuntoInteres(34.851944,56.188333, Rubro.FARMACIA, "Cruz Roja");
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		ret = s.registrarPuntoInteres(-39.905, -56.190, Rubro.SUPERMERCADO, null);
		assertEquals(Retorno.Resultado.ERROR_2, ret.resultado);
	}
	
	@Test
	public void testRegistroPuntoInteresExistente(){
		ISistema s = new Sistema();
		s.inicializarSistema(3);
		
		//Crear datos
		Retorno ret = s.registrarPuntoInteres(34.764167,56.213889, Rubro.CAJERO, "Abitab");
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		ret = s.registrarPuntoInteres(34.851944,56.188333, Rubro.CENTRO_COMERCIAL, "Nuevo Centro");
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		ret = s.registrarPuntoInteres(34.851944,56.188333, Rubro.FARMACIA, "Cruz Roja");
		assertEquals(Retorno.Resultado.ERROR_3, ret.resultado);
	}
	
	
	
	@Test
	public void testRegistroPropiedadCorrectamente(){
		
	}
	
	@Test
	public void testRegistroPropiedadExcederMasUno(){
		
	}
	
	@Test
	public void testRegistroPropiedadExistente(){
		
	}
	
	
	@Test
	public void testRegistroPuntosTipoVariadoCorrectamente(){
		ISistema s = new Sistema();
		s.inicializarSistema(5);
		
		//Crear datos
		Retorno ret = s.registrarPuntoInteres(34.764167,56.213889, Rubro.CAJERO, "Abitab");
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		ret = s.registrarEsquina(-34.905, -56.190);
		assertEquals(Retorno.Resultado.OK, ret.resultado);	
		ret = s.registrarPuntoInteres(34.851944,56.188333, Rubro.CENTRO_COMERCIAL, "Nuevo Centro");
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		ret = s.registrarEsquina(-39.905,-56.188);
		assertEquals(Retorno.Resultado.OK, ret.resultado);
	}
	
	@Test
	public void testRegistroPuntoTipoVariadoExcederMasUno(){
		ISistema s = new Sistema();
		s.inicializarSistema(3);
		
		//Crear datos
		Retorno ret = s.registrarPuntoInteres(34.764167,56.213889, Rubro.CAJERO, "Abitab");
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		ret = s.registrarEsquina(-34.905, -56.190);
		assertEquals(Retorno.Resultado.OK, ret.resultado);	
		ret = s.registrarPuntoInteres(34.851944,56.188333, Rubro.CENTRO_COMERCIAL, "Nuevo Centro");
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		ret = s.registrarEsquina(-39.905,-56.188);
		assertEquals(Retorno.Resultado.ERROR_1, ret.resultado);
	}
	
	@Test
	public void testRegistroPuntoTipoVariadoExistente(){
		ISistema s = new Sistema();
		s.inicializarSistema(5);
		
		//Crear datos
		Retorno ret = s.registrarPuntoInteres(34.764167,56.213889, Rubro.CAJERO, "Abitab");
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		ret = s.registrarEsquina(-34.905, -56.190);
		assertEquals(Retorno.Resultado.OK, ret.resultado);	
		ret = s.registrarPuntoInteres(34.851944,56.188333, Rubro.CENTRO_COMERCIAL, "Nuevo Centro");
		assertEquals(Retorno.Resultado.OK, ret.resultado);
		ret = s.registrarEsquina(34.851944,56.188333);
		assertEquals(Retorno.Resultado.ERROR_2, ret.resultado);
	}
	
	@Test
	public void testListadoDePropiedadesOK(){
		ISistema s = new Sistema();
		s.inicializarSistema(5);
		
		//Datos de la prueba
		String cedula = "3.702.156-9";
		String nombre = "Omar";
		String email = "omar@gmail.com";
		String celular = "098123456";
		//Estimulo
		Retorno ret = s.registrarVendedor(cedula, nombre, email, celular);
		assertEquals(Retorno.Resultado.OK, ret.resultado);	//Deberia retornar OK
		
		//TODO falta terminar porque falta terminar el asignar propiedad a vendedor
	}
	
	@Test
	public void testListadoDePropiedadesError1(){
		ISistema s = new Sistema();
		s.inicializarSistema(5);
		
		//Estimulo
		Retorno ret = s.listadoPropiedades("3.354.988-2");
		assertEquals(Retorno.Resultado.ERROR_1, ret.resultado);
	}
	
	@Test
	public void testListadoDePropiedadesError2(){
		ISistema s = new Sistema();
		s.inicializarSistema(5);
		
		//Datos de la prueba
		String cedula = "3.702.156-9";
		String nombre = "Omar";
		String email = "omar@gmail.com";
		String celular = "098123456";
		//Estimulo
		Retorno ret = s.registrarVendedor(cedula, nombre, email, celular);
		assertEquals(Retorno.Resultado.OK, ret.resultado);	//Deberia retornar OK
		
		//Estimulo
		Retorno retList = s.listadoPropiedades("3.702.156-9");
		assertEquals(Retorno.Resultado.ERROR_2, retList.resultado);
	}
}
