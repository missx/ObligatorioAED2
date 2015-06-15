package tests;

import static org.junit.Assert.*;
import interfaces.ISistema;

import org.junit.Test;

import sistema.Enumerados.Rubro;
import sistema.Enumerados.TipoPropiedad;
import sistema.Retorno;
import sistema.Sistema;

public class TestsSistemav2 {

	@Test
	public void testInicializarDestruirSistema() {
		Sistema s = new Sistema();
		assertEquals(Retorno.Resultado.OK, s.inicializarSistema(10).resultado);
		assertEquals(Retorno.Resultado.OK, s.destruirSistema().resultado);
	}
	
	@Test
	public void testRegistroVendedor() {
		ISistema sistema = new Sistema();
		sistema.inicializarSistema(10);
		//Datos de la prueba
		assertEquals(Retorno.Resultado.OK, sistema.registrarVendedor("5.555.555-5", "Omar", "omar@gmail.com", "098123456").resultado);	
		assertEquals(Retorno.Resultado.OK, sistema.registrarVendedor("9.999.999-9", "Jorge", "jorge@gmail.com", "091348745").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarVendedor("1.111.111-1", "Maria", "maria@gmail.com", "095672977").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarVendedor("2.222.222-2", "Ximena", "ximena@gmail.com", "094176532").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarVendedor("4.444.444-4", "Analia", "analia@gmail.com", "094873673").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarVendedor("3.333.333-3", "Nicolas", "nicolas@gmail.com", "099987654").resultado);
	}

	@Test
	public void testRegistroVendedorError1() {
		ISistema s = new Sistema();
		s.inicializarSistema(10);
		//Formatos de Cedula incorrectos
		assertEquals(Retorno.Resultado.ERROR_1, 
				s.registrarVendedor("702.515-7", "",  "o@g.com", "098206900").resultado); 
		assertEquals(Retorno.Resultado.ERROR_1, 
				s.registrarVendedor("1702515-7", "",  "o@g.com", "098206900").resultado);
		assertEquals(Retorno.Resultado.ERROR_1, 
				s.registrarVendedor("1.702.51-70", "",  "o@g.com", "098206900").resultado);
		assertEquals(Retorno.Resultado.ERROR_1, 
				s.registrarVendedor("1.702.51--0", "",  "o@g.com", "098206900").resultado);
		assertEquals(Retorno.Resultado.ERROR_1, 
				s.registrarVendedor("..702.510-0", "",  "o@g.com", "098206900").resultado);
		assertEquals(Retorno.Resultado.ERROR_1, 
				s.registrarVendedor("1.zzz.051-7", "",  "o@g.com", "098206900").resultado);
		assertEquals(Retorno.Resultado.ERROR_1, 
				s.registrarVendedor("           ", "",  "o@g.com", "098206900").resultado);

	}
	
	@Test
	public void testRegistroVendedorError2() {
		ISistema sistema = new Sistema();
		sistema.inicializarSistema(10);
		assertEquals(Retorno.Resultado.ERROR_2, sistema.registrarVendedor("5.555.555-5", "Maria", "maria@gmail.com", "195672977").resultado);
		assertEquals(Retorno.Resultado.ERROR_2, sistema.registrarVendedor("5.555.555-5", "Maria", "maria@gmail.com", ""	).resultado);
	}
	
	@Test
	public void testRegistroVendedorError3() {
		ISistema sistema = new Sistema();
		sistema.inicializarSistema(10);
		assertEquals(Retorno.Resultado.ERROR_3, sistema.registrarVendedor("5.555.555-5", "Maria", "mariagmail.com", "098123456").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarVendedor("5.555.555-5", "Maria", "maria@gmail.com", "098123456").resultado);
		assertEquals(Retorno.Resultado.ERROR_3, sistema.registrarVendedor("5.555.555-5", "Maria", "mariagmail.com", "098123456").resultado);
	}
	
	@Test
	public void testRegistroVendedorError4() {
		ISistema sistema = new Sistema();
		sistema.inicializarSistema(10);
		assertEquals(Retorno.Resultado.OK, sistema.registrarVendedor("5.555.555-5", "Maria", "maria@gmail.com", "098123456").resultado);
		assertEquals(Retorno.Resultado.ERROR_4, sistema.registrarVendedor("5.555.555-5", "Maria", "maria@gmail.com", "098123456").resultado);
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
		
		Retorno ret = sistema.registrarVendedor(cedula, nombre, email, celular);
		assertEquals(Retorno.Resultado.OK, ret.resultado);	
		ret = sistema.listadoVendedores();
		assertEquals(Retorno.Resultado.OK, ret.resultado);	
		// El valor string deberia contener los datos del vendedor ingresado
		assertTrue(ret.valorString.contains(cedula)||ret.valorString.contains("37021569")); 
		assertTrue(ret.valorString.contains(nombre));
		assertTrue(ret.valorString.contains(celular));
	}
	
	@Test
	public void testListadoVendedores() {
		ISistema sistema = new Sistema();
		sistema.inicializarSistema(10);
		assertEquals(Retorno.Resultado.OK, sistema.registrarVendedor("5.555.555-5", "Omar", "omar@gmail.com", "098123456").resultado);	
		assertEquals(Retorno.Resultado.OK, sistema.registrarVendedor("9.999.999-9", "Jorge", "jorge@gmail.com", "091348745").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarVendedor("1.111.111-1", "Maria", "maria@gmail.com", "095672977").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarVendedor("2.222.222-2", "Ximena", "ximena@gmail.com", "094176532").resultado);
	
		Retorno res = sistema.listadoVendedores();
		assertEquals(Retorno.Resultado.OK, res.resultado);
		//Validar que estan todos los vendedores
		assertTrue(res.valorString.contains("5.555.555-5")|res.valorString.contains("55555555"));
		assertTrue(res.valorString.contains("9.999.999-9")|res.valorString.contains("99999999"));
		assertTrue(res.valorString.contains("1.111.111-1")|res.valorString.contains("11111111"));
		assertTrue(res.valorString.contains("2.222.222-2")|res.valorString.contains("22222222"));
		//Validar que estan ordenados
		assertTrue(res.valorString.indexOf("5.555.555-5")>res.valorString.indexOf("1.111.111-1") ||
					res.valorString.indexOf("55555555")>res.valorString.indexOf("11111111"));
		assertTrue(res.valorString.indexOf("9.999.999-9")>res.valorString.indexOf("2.222.222-2") ||
				res.valorString.indexOf("99999999")>res.valorString.indexOf("22222222"));
	}

	//TEST MAPA
	@Test
	public void testRegistrarPuntosInteres() {
		ISistema sistema = new Sistema();
		sistema.inicializarSistema(5);		
		assertEquals(Retorno.Resultado.OK, sistema.registrarPuntoInteres(-32.3105104,-58.0759192, Rubro.FARMACIA, "Farmashop").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarPuntoInteres(-32.3105100,-58.0759192, Rubro.CAJERO, "Banred").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarPuntoInteres(-32.3105100,-58.0759100, Rubro.SUPERMERCADO, "Tienda Inglesa").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarPuntoInteres(-32.3102222,-58.0759192, Rubro.CENTRO_COMERCIAL, "Montevideo Shopping").resultado);
		//Nombre vacio
		assertEquals(Retorno.Resultado.ERROR_2, sistema.registrarPuntoInteres(-32.3105144,-58.0759555, Rubro.OTROS, "").resultado);
		//Punto duplicado
		assertEquals(Retorno.Resultado.ERROR_3, sistema.registrarPuntoInteres(-32.3102222,-58.0759192, Rubro.OTROS, "Ferreteria").resultado);
		
		assertEquals(Retorno.Resultado.OK, sistema.registrarPuntoInteres(-32.3105133,-58.0759222, Rubro.PARADA, "Interdepartamental").resultado);
		//Cantidad maxima de puntos
		assertEquals(Retorno.Resultado.ERROR_1, sistema.registrarPuntoInteres(-32.3105144,-58.0759555, Rubro.OTROS, "Ferreteria").resultado);
	}
	
	@Test
	public void testRegistrarEsquinas() {
		ISistema sistema = new Sistema();
		sistema.inicializarSistema(5);
		assertEquals(Retorno.Resultado.OK, sistema.registrarEsquina(-32.3105104,-58.0759192).resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarEsquina(-32.3105100,-58.0759192).resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarEsquina(-32.3105100,-58.0759100).resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarEsquina(-32.3102222,-58.0759192).resultado);
		//Coordenadas duplicadas
		assertEquals(Retorno.Resultado.ERROR_2, sistema.registrarEsquina(-32.3102222,-58.0759192).resultado);
		//Cantidad maxima de puntos
		assertEquals(Retorno.Resultado.OK, sistema.registrarEsquina(-32.3105133,-58.0759222).resultado);
		assertEquals(Retorno.Resultado.ERROR_1, sistema.registrarEsquina(-32.3105144,-58.0759555).resultado);
	}
	
	@Test
	public void testRegistrarPropiedades() {
		ISistema sistema = new Sistema();
		sistema.inicializarSistema(5);
		// No hay vendedores
		assertEquals(Retorno.Resultado.ERROR_3, sistema.registrarPropiedad(-32.3105104,-58.0759192,TipoPropiedad.CASA, "18 de Julio 1234").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarVendedor("5.555.555-5", "Omar", "omar@gmail.com", "098123456").resultado);
		
		assertEquals(Retorno.Resultado.OK, sistema.registrarPropiedad(-32.3105104,-58.0759192,TipoPropiedad.CASA, "18 de Julio 1234").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarPropiedad(-32.3105100,-58.0759192,TipoPropiedad.APARTAMENTO, "Sarmiento 2359").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarPropiedad(-32.3105100,-58.0759100, TipoPropiedad.TERRENO, "Rivera 3450").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarPropiedad(-32.3102222,-58.0759192, TipoPropiedad.CASA, "26 de Marzo 4235").resultado);
		//Coordenada duplicada
		assertEquals(Retorno.Resultado.ERROR_4, sistema.registrarPropiedad(-32.3102222,-58.0759192, TipoPropiedad.CASA, "Paysandu 4234").resultado);
		//Direccion ya existe
		assertEquals(Retorno.Resultado.ERROR_5 , sistema.registrarPropiedad(-32.3101111,-58.0759300, TipoPropiedad.CASA, "26 de Marzo 4235").resultado);
		//Direccion vacia
		assertEquals(Retorno.Resultado.ERROR_2, sistema.registrarPropiedad(-32.3102223,-58.0759153, TipoPropiedad.APARTAMENTO, "").resultado);
		
		assertEquals(Retorno.Resultado.OK, sistema.registrarPropiedad(-32.3105133,-58.0759222, TipoPropiedad.TERRENO, "Marco Bruto 2535").resultado);
		//Cantidad maxima de puntos
		assertEquals(Retorno.Resultado.ERROR_1, sistema.registrarPropiedad(-32.3105144,-58.0759555, TipoPropiedad.CASA, "Ellauri 3208").resultado);		
	
	}
	
	@Test
	public void testEliminarPuntos() {
		ISistema sistema = new Sistema();
		sistema.inicializarSistema(20);
		//Necesito un vendedor al menos
		assertEquals(Retorno.Resultado.OK, sistema.registrarVendedor("5.555.555-5", "Omar", "omar@gmail.com", "098123456").resultado);
		
		assertEquals(Retorno.Resultado.OK, sistema.registrarPuntoInteres(-32.3105104,-58.0759192, Rubro.FARMACIA, "Farmashop").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarPuntoInteres(-32.3105100,-58.0759192, Rubro.CAJERO, "Banred").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarPuntoInteres(-32.3105100,-58.0759100, Rubro.SUPERMERCADO, "Tienda Inglesa").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarPuntoInteres(-32.3102222,-58.0759192, Rubro.CENTRO_COMERCIAL, "Montevideo Shopping").resultado);
		
		assertEquals(Retorno.Resultado.OK, sistema.registrarPropiedad(-32.3105134,-58.0759162,TipoPropiedad.CASA, "18 de Julio 1234").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarPropiedad(-32.3105108,-58.0759152,TipoPropiedad.APARTAMENTO, "Sarmiento 2359").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarPropiedad(-32.3105102,-58.0759102, TipoPropiedad.TERRENO, "Rivera 3450").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarPropiedad(-32.3102202,-58.0759143, TipoPropiedad.CASA, "26 de Marzo 4235").resultado);
		
		assertEquals(Retorno.Resultado.OK, sistema.registrarEsquina(-32.3105104,-58.0759182).resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarEsquina(-32.3105120,-58.0759188).resultado);
		
		assertEquals(Retorno.Resultado.OK, sistema.eliminarPuntoMapa(-32.3105104,-58.0759192).resultado);  //punto de interes
		assertEquals(Retorno.Resultado.OK, sistema.eliminarPuntoMapa(-32.3105108,-58.0759152).resultado); //propiedad
		assertEquals(Retorno.Resultado.OK, sistema.eliminarPuntoMapa(-32.3105120,-58.0759188).resultado); //esquina
		
	}
	
	@Test
	public void testEliminarYAgregarPunto(){
		ISistema sistema = new Sistema();
		sistema.inicializarSistema(2);
		
		assertEquals(Retorno.Resultado.OK, sistema.registrarPuntoInteres(-32.3105104,-58.0759192, Rubro.FARMACIA, "Farmashop").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarEsquina(-32.3105120,-58.0759188).resultado);

		//Eliminar
		assertEquals(Retorno.Resultado.OK, sistema.eliminarPuntoMapa(-32.3105120,-58.0759188).resultado); //esquina
		/*
		//Volver crear otra esquina
		assertEquals(Retorno.Resultado.OK, sistema.registrarEsquina(-32.00,-58.11).resultado);*/
	}
	
	@Test
	public void testEliminarError() {
		ISistema sistema = new Sistema();
		sistema.inicializarSistema(3);
		
		//Eliminar
		assertEquals(Retorno.Resultado.ERROR_1, sistema.eliminarPuntoMapa(-32.3105120,-58.0759188).resultado); //no existe
		
	}
	
	@Test
	public void testTramos() {
		ISistema sistema = new Sistema();
		sistema.inicializarSistema(20);
		
		assertEquals(Retorno.Resultado.OK, sistema.registrarPuntoInteres(-32.3105104,-58.0759192, Rubro.FARMACIA, "Farmashop").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarPuntoInteres(-32.3105100,-58.0759192, Rubro.CAJERO, "Banred").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarPuntoInteres(-32.3105100,-58.0759100, Rubro.SUPERMERCADO, "Tienda Inglesa").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarPuntoInteres(-32.3102222,-58.0759192, Rubro.CENTRO_COMERCIAL, "Montevideo Shopping").resultado);
		
		assertEquals(Retorno.Resultado.OK, sistema.registrarVendedor("5.555.555-5", "Omar", "omar@gmail.com", "098123456").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarPropiedad(-32.3105134,-58.0759162,TipoPropiedad.CASA, "18 de Julio 1234").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarPropiedad(-32.3105108,-58.0759152,TipoPropiedad.APARTAMENTO, "Sarmiento 2359").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarPropiedad(-32.3105102,-58.0759102, TipoPropiedad.TERRENO, "Rivera 3450").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarPropiedad(-32.3102202,-58.0759143, TipoPropiedad.CASA, "26 de Marzo 4235").resultado);
		
		assertEquals(Retorno.Resultado.OK, sistema.registrarEsquina(-32.3105104,-58.0759182).resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarEsquina(-32.3105120,-58.0759188).resultado);
		
		//Peso invalido
		assertEquals(Retorno.Resultado.ERROR_1 , sistema.registrarTramo(-32.3105104,-58.0759192, -32.3105100,-58.0759192, -3).resultado);
		assertEquals(Retorno.Resultado.ERROR_1, sistema.registrarTramo(-32.3105104,-58.0759192, -32.3105100,-58.0759192, 0).resultado);
		//No existe el punto de destino
		assertEquals(Retorno.Resultado.ERROR_2, sistema.registrarTramo(-32.3105104,-58.0759192, -32.3105200,-58.0759200, 80).resultado);
		//No existe el punto de origen
		assertEquals(Retorno.Resultado.ERROR_2, sistema.registrarTramo(-32.3105200,-58.0759200, -32.3105104,-58.0759192, 80).resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarTramo(-32.3105104,-58.0759192, -32.3105100,-58.0759192, 80).resultado);
		//Tramo duplicado
		assertEquals(Retorno.Resultado.ERROR_3, sistema.registrarTramo(-32.3105104,-58.0759192, -32.3105100,-58.0759192, 100).resultado);
		
		assertEquals(Retorno.Resultado.OK, sistema.registrarTramo(-32.3105100,-58.0759100, -32.3102222,-58.0759192, 180).resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarTramo(-32.3105134,-58.0759162, -32.3105108,-58.0759152, 280).resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarTramo(-32.3105108,-58.0759152, -32.3102202,-58.0759143, 380).resultado);
		
		assertEquals(Retorno.Resultado.OK, sistema.eliminarTramo(-32.3105108,-58.0759152, -32.3102202,-58.0759143).resultado);
		assertEquals(Retorno.Resultado.ERROR_1, sistema.eliminarTramo(-38.3105108,-51.0759152, -32.3102202,-58.0759143).resultado);
		assertEquals(Retorno.Resultado.ERROR_2, sistema.eliminarTramo(-32.3105108,-58.0759152, -32.3102202,-58.0759143).resultado); 
		
	}
	
	@Test
	public void testPropiedadesYVendedores() {
		
		Sistema sistema = new Sistema();
		sistema.inicializarSistema(10);
		
		assertEquals(Retorno.Resultado.OK, sistema.registrarVendedor("5.555.555-5", "Omar", "omar@gmail.com", "098123456").resultado);	
		assertEquals(Retorno.Resultado.OK, sistema.registrarVendedor("9.999.999-9", "Jorge", "jorge@gmail.com", "091348745").resultado);
		
		System.out.println(sistema.queueDeVendedores.cantNodos());
		
		assertEquals(Retorno.Resultado.OK, sistema.registrarPropiedad(-32.3105134,-58.0759162, TipoPropiedad.CASA, "18 de Julio 1234").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarPropiedad(-32.3105108,-58.0759152, TipoPropiedad.APARTAMENTO, "Sarmiento 2359").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarPropiedad(-32.3105102,-58.0759102, TipoPropiedad.TERRENO, "Rivera 3450").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarPropiedad(-32.3102202,-58.0759143, TipoPropiedad.CASA, "26 de Marzo 4235").resultado);
		
		System.out.println(sistema.tableHashProp.esVacio());
		
		assertEquals(Retorno.Resultado.OK, sistema.listadoVendedores().resultado);
		//Listados de 2 vendedores, deben tener 2 propiedades cada uno
		Retorno res1 = sistema.listadoPropiedades("5.555.555-5");
		assertEquals(Retorno.Resultado.OK, res1.resultado);
		Retorno res2 = sistema.listadoPropiedades("9.999.999-9");
		assertEquals(Retorno.Resultado.OK, res2.resultado);
		//Ambos tienen mas de un resultado
		assertTrue(res1.valorString.contains("|") && res2.valorString.contains("|"));
		//Las propiedades aparecen en uno o en otro vendedor
		assertTrue(res1.valorString.contains("-32.3105134")&&res1.valorString.contains("-58.0759162")||
				res2.valorString.contains("-32.3105134")&&res2.valorString.contains("-58.0759162"));
		assertTrue(res1.valorString.contains("-32.3105102")&&res1.valorString.contains("-58.0759102")||
				res2.valorString.contains("-32.3105102")&&res2.valorString.contains("-58.0759102"));
		
		
		
		assertEquals(Retorno.Resultado.OK, sistema.registrarVendedor("2.222.222-2", "Ximena", "ximena@gmail.com", "094176532").resultado);
		//Las propiedades deberian re-distribuirse
		assertEquals(Retorno.Resultado.OK, sistema.eliminarVendedor("9.999.999-9").resultado); 
		
		res1 = sistema.listadoPropiedades("5.555.555-5");
		assertEquals(Retorno.Resultado.OK, res1.resultado);
		res2 = sistema.listadoPropiedades("2.222.222-2");
		assertEquals(Retorno.Resultado.OK, res2.resultado);
		//El nuevo vendedor tienen algun resultado
		assertTrue(res2.valorString.length()>0);
		//Las propiedades aparecen en uno o en otro vendedor
		assertTrue(res1.valorString.contains("-32.3105134")&&res1.valorString.contains("-58.0759162")||
				res2.valorString.contains("-32.3105134")&&res2.valorString.contains("-58.0759162"));
		assertTrue(res1.valorString.contains("-32.3105102")&&res1.valorString.contains("-58.0759102")||
				res2.valorString.contains("-32.3105102")&&res2.valorString.contains("-58.0759102"));
		
		//Ahora todas las props deberian quedar en el vendedor nuevo
		assertEquals(Retorno.Resultado.OK, sistema.eliminarVendedor("5.555.555-5").resultado); 
		res2 = sistema.listadoPropiedades("2.222.222-2");
		assertEquals(Retorno.Resultado.OK, res2.resultado);
		assertTrue(res2.valorString.contains("-32.3105108")&&res2.valorString.contains("-58.0759152")&&
				res2.valorString.contains("-32.3102202")&&res2.valorString.contains("-58.0759143"));
		
		assertEquals(Retorno.Resultado.ERROR_2, sistema.eliminarVendedor("2.222.222-2").resultado); //no hay otros vendedores
		
	}	
	
	@Test
	public void caminoMinimo() {
		ISistema sistema = new Sistema();
		sistema.inicializarSistema(20);
		
		assertEquals(Retorno.Resultado.OK, sistema.registrarVendedor("5.555.555-5", "Omar", "omar@gmail.com", "098123456").resultado);
		
		assertEquals(Retorno.Resultado.OK, sistema.registrarEsquina(-34.909039, -56.195530).resultado);//P1
		assertEquals(Retorno.Resultado.OK, sistema.registrarEsquina(-34.908951, -56.194500).resultado);//P2
		assertEquals(Retorno.Resultado.OK, sistema.registrarPropiedad(-34.908880, -56.193299,TipoPropiedad.CASA, "Rio Negro 1188").resultado);//P3
		
		assertEquals(Retorno.Resultado.OK, sistema.registrarEsquina(-34.909980, -56.195498).resultado);//P4
		assertEquals(Retorno.Resultado.OK, sistema.registrarPropiedad(-34.909910, -56.194372,TipoPropiedad.APARTAMENTO, "Maldonado 1250").resultado);//P5
		assertEquals(Retorno.Resultado.OK, sistema.registrarEsquina(-34.909813, -56.193202).resultado);//P6
		
		assertEquals(Retorno.Resultado.OK, sistema.registrarPuntoInteres(-34.910913, -56.195370, Rubro.FARMACIA, "Farmashop").resultado);//P7
		assertEquals(Retorno.Resultado.OK, sistema.registrarPuntoInteres(-34.910886, -56.194254, Rubro.FARMACIA, "Farmashop 2").resultado);//P8
		assertEquals(Retorno.Resultado.OK, sistema.registrarPuntoInteres(-34.910728, -56.193116, Rubro.CAJERO, "RedBrou").resultado);//P9
		
		assertEquals(Retorno.Resultado.OK, sistema.registrarTramo(-34.909039, -56.195530, -34.908951, -56.194500, 7).resultado);//P1-P2
		assertEquals(Retorno.Resultado.OK, sistema.registrarTramo(-34.909039, -56.195530, -34.909980, -56.195498, 2).resultado);//P1-P4
		assertEquals(Retorno.Resultado.OK, sistema.registrarTramo(-34.908951, -56.194500, -34.908880, -56.193299, 8).resultado);//P2-P3
		assertEquals(Retorno.Resultado.OK, sistema.registrarTramo(-34.908951, -56.194500, -34.909910, -56.194372, 12).resultado);//P2-P5
		assertEquals(Retorno.Resultado.OK, sistema.registrarTramo(-34.909813, -56.193202, -34.908880, -56.193299, 3).resultado);//P6-P3
		assertEquals(Retorno.Resultado.OK, sistema.registrarTramo(-34.909813, -56.193202, -34.909910, -56.194372, 18).resultado);//P6-P5
		assertEquals(Retorno.Resultado.OK, sistema.registrarTramo(-34.909813, -56.193202, -34.910728, -56.193116, 12).resultado);//P6-P9
		assertEquals(Retorno.Resultado.OK, sistema.registrarTramo(-34.909910, -56.194372, -34.909980, -56.195498, 2).resultado);//P5-P4
		assertEquals(Retorno.Resultado.OK, sistema.registrarTramo(-34.910913, -56.195370, -34.909980, -56.195498, 6).resultado);//P7-P4
		assertEquals(Retorno.Resultado.OK, sistema.registrarTramo(-34.910913, -56.195370, -34.910886, -56.194254, 10).resultado);//P7-P8
		assertEquals(Retorno.Resultado.OK, sistema.registrarTramo(-34.909910, -56.194372, -34.910886, -56.194254, 3).resultado);//P5-P8
		assertEquals(Retorno.Resultado.OK, sistema.registrarTramo(-34.910728, -56.193116, -34.910886, -56.194254, 10).resultado);//P9-P8
		
		// Camino esperado a la Farmacia- P3-P2-P1-P4-P5-P8 costo 22
		String rutaEsperada = "-34.908880;-56.193299|-34.908951;-56.194500|-34.909039;-56.195530|-34.909980;-56.195498|-34.909910;-56.194372|-34.910886;-56.194254";
		Retorno res = sistema.caminoMinimo("Rio Negro 1188", -34.910886, -56.194254);
		assertEquals(Retorno.Resultado.OK, res.resultado);
		assertEquals(rutaEsperada, res.valorString.replace(" ", ""));
	}
	
	@Test
	public void PuntoMasCercanoSimple() {
		ISistema sistema = new Sistema();
		sistema.inicializarSistema(20);
		
		assertEquals(Retorno.Resultado.OK, sistema.registrarVendedor("5.555.555-5", "Omar", "omar@gmail.com", "098123456").resultado);
		
		assertEquals(Retorno.Resultado.OK, sistema.registrarEsquina(-34.909039, -56.195530).resultado);//P1
		assertEquals(Retorno.Resultado.OK, sistema.registrarEsquina(-34.908951, -56.194500).resultado);//P2
		assertEquals(Retorno.Resultado.OK, sistema.registrarPropiedad(-34.908880, -56.193299,TipoPropiedad.CASA, "Rio Negro 1188").resultado);//P3
		
		assertEquals(Retorno.Resultado.OK, sistema.registrarEsquina(-34.909980, -56.195498).resultado);//P4
		assertEquals(Retorno.Resultado.OK, sistema.registrarPropiedad(-34.909910, -56.194372,TipoPropiedad.APARTAMENTO, "Maldonado 1250").resultado);//P5
		assertEquals(Retorno.Resultado.OK, sistema.registrarEsquina(-34.909813, -56.193202).resultado);//P6
		
		assertEquals(Retorno.Resultado.OK, sistema.registrarPuntoInteres(-34.910913, -56.195370, Rubro.FARMACIA, "Farmashop").resultado);//P7
		assertEquals(Retorno.Resultado.OK, sistema.registrarPuntoInteres(-34.910886, -56.194254, Rubro.FARMACIA, "Farmashop 2").resultado);//P8
		assertEquals(Retorno.Resultado.OK, sistema.registrarPuntoInteres(-34.910728, -56.193116, Rubro.CAJERO, "RedBrou").resultado);//P9
		
		assertEquals(Retorno.Resultado.OK, sistema.registrarTramo(-34.909039, -56.195530, -34.908951, -56.194500, 7).resultado);//P1-P2
		assertEquals(Retorno.Resultado.OK, sistema.registrarTramo(-34.909039, -56.195530, -34.909980, -56.195498, 2).resultado);//P1-P4
		assertEquals(Retorno.Resultado.OK, sistema.registrarTramo(-34.908951, -56.194500, -34.908880, -56.193299, 8).resultado);//P2-P3
		assertEquals(Retorno.Resultado.OK, sistema.registrarTramo(-34.908951, -56.194500, -34.909910, -56.194372, 12).resultado);//P2-P5
		assertEquals(Retorno.Resultado.OK, sistema.registrarTramo(-34.909813, -56.193202, -34.908880, -56.193299, 3).resultado);//P6-P3
		assertEquals(Retorno.Resultado.OK, sistema.registrarTramo(-34.909813, -56.193202, -34.909910, -56.194372, 18).resultado);//P6-P5
		assertEquals(Retorno.Resultado.OK, sistema.registrarTramo(-34.909813, -56.193202, -34.910728, -56.193116, 12).resultado);//P6-P9
		assertEquals(Retorno.Resultado.OK, sistema.registrarTramo(-34.909910, -56.194372, -34.909980, -56.195498, 2).resultado);//P5-P4
		assertEquals(Retorno.Resultado.OK, sistema.registrarTramo(-34.910913, -56.195370, -34.909980, -56.195498, 6).resultado);//P7-P4
		assertEquals(Retorno.Resultado.OK, sistema.registrarTramo(-34.910913, -56.195370, -34.910886, -56.194254, 10).resultado);//P7-P8
		assertEquals(Retorno.Resultado.OK, sistema.registrarTramo(-34.909910, -56.194372, -34.910886, -56.194254, 3).resultado);//P5-P8
		assertEquals(Retorno.Resultado.OK, sistema.registrarTramo(-34.910728, -56.193116, -34.910886, -56.194254, 10).resultado);//P9-P8
		
		// Cajero mas cercano - P3-P6-P9 costo 15
		String cajeroEsperado = "-34.910728;-56.193116";
		Retorno res = sistema.puntoInteresMasCercano("Rio Negro 1188", Rubro.CAJERO);
		assertEquals(Retorno.Resultado.OK, res.resultado);
		assertEquals(cajeroEsperado, res.valorString.replace(" ", ""));
	}
	
	
	@Test
	public void PuntoMasCercano2Puntos() {
		ISistema sistema = new Sistema();
		sistema.inicializarSistema(20);
		
		assertEquals(Retorno.Resultado.OK, sistema.registrarVendedor("5.555.555-5", "Omar", "omar@gmail.com", "098123456").resultado);
		
		assertEquals(Retorno.Resultado.OK, sistema.registrarEsquina(-34.909039, -56.195530).resultado);//P1
		assertEquals(Retorno.Resultado.OK, sistema.registrarEsquina(-34.908951, -56.194500).resultado);//P2
		assertEquals(Retorno.Resultado.OK, sistema.registrarPropiedad(-34.908880, -56.193299,TipoPropiedad.CASA, "Rio Negro 1188").resultado);//P3
		
		assertEquals(Retorno.Resultado.OK, sistema.registrarEsquina(-34.909980, -56.195498).resultado);//P4
		assertEquals(Retorno.Resultado.OK, sistema.registrarPropiedad(-34.909910, -56.194372,TipoPropiedad.APARTAMENTO, "Maldonado 1250").resultado);//P5
		assertEquals(Retorno.Resultado.OK, sistema.registrarEsquina(-34.909813, -56.193202).resultado);//P6
		
		assertEquals(Retorno.Resultado.OK, sistema.registrarPuntoInteres(-34.910913, -56.195370, Rubro.FARMACIA, "Farmashop").resultado);//P7
		assertEquals(Retorno.Resultado.OK, sistema.registrarPuntoInteres(-34.910886, -56.194254, Rubro.FARMACIA, "Farmashop 2").resultado);//P8
		assertEquals(Retorno.Resultado.OK, sistema.registrarPuntoInteres(-34.910728, -56.193116, Rubro.CAJERO, "RedBrou").resultado);//P9
		
		assertEquals(Retorno.Resultado.OK, sistema.registrarTramo(-34.909039, -56.195530, -34.908951, -56.194500, 7).resultado);//P1-P2
		assertEquals(Retorno.Resultado.OK, sistema.registrarTramo(-34.909039, -56.195530, -34.909980, -56.195498, 2).resultado);//P1-P4
		assertEquals(Retorno.Resultado.OK, sistema.registrarTramo(-34.908951, -56.194500, -34.908880, -56.193299, 8).resultado);//P2-P3
		assertEquals(Retorno.Resultado.OK, sistema.registrarTramo(-34.908951, -56.194500, -34.909910, -56.194372, 12).resultado);//P2-P5
		assertEquals(Retorno.Resultado.OK, sistema.registrarTramo(-34.909813, -56.193202, -34.908880, -56.193299, 3).resultado);//P6-P3
		assertEquals(Retorno.Resultado.OK, sistema.registrarTramo(-34.909813, -56.193202, -34.909910, -56.194372, 18).resultado);//P6-P5
		assertEquals(Retorno.Resultado.OK, sistema.registrarTramo(-34.909813, -56.193202, -34.910728, -56.193116, 12).resultado);//P6-P9
		assertEquals(Retorno.Resultado.OK, sistema.registrarTramo(-34.909910, -56.194372, -34.909980, -56.195498, 2).resultado);//P5-P4
		assertEquals(Retorno.Resultado.OK, sistema.registrarTramo(-34.910913, -56.195370, -34.909980, -56.195498, 6).resultado);//P7-P4
		assertEquals(Retorno.Resultado.OK, sistema.registrarTramo(-34.910913, -56.195370, -34.910886, -56.194254, 10).resultado);//P7-P8
		assertEquals(Retorno.Resultado.OK, sistema.registrarTramo(-34.909910, -56.194372, -34.910886, -56.194254, 3).resultado);//P5-P8
		assertEquals(Retorno.Resultado.OK, sistema.registrarTramo(-34.910728, -56.193116, -34.910886, -56.194254, 10).resultado);//P9-P8
		
		// Farmacia mas cercana - P3-P2-P1-P4-P5-P8 costo 22
		String farmaciaEsperada = "-34.910886;-56.194254";
		Retorno res = sistema.puntoInteresMasCercano("Rio Negro 1188", Rubro.FARMACIA);
		assertEquals(Retorno.Resultado.OK, res.resultado);
		assertEquals(farmaciaEsperada, res.valorString.replace(" ", ""));
	}
	
	@Test
	public void PuntoMasCercanoErrores() {
		ISistema sistema = new Sistema();
		sistema.inicializarSistema(5);
		assertEquals(Retorno.Resultado.OK, sistema.registrarVendedor("5.555.555-5", "Omar", "omar@gmail.com", "098123456").resultado);
		
		assertEquals(Retorno.Resultado.OK, sistema.registrarEsquina(-34.908951, -56.194500).resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarPropiedad(-34.908880, -56.193299,TipoPropiedad.CASA, "Rio Negro 1188").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarPuntoInteres(-34.910913, -56.195370, Rubro.FARMACIA, "Farmashop").resultado);
		//Direccion que no existe
		assertEquals(Retorno.Resultado.ERROR_1, sistema.puntoInteresMasCercano("Direccion que no existe", Rubro.CAJERO).resultado);
		//Rubro inexistente
		assertEquals(Retorno.Resultado.ERROR_2, sistema.puntoInteresMasCercano("Rio Negro 1188", Rubro.CAJERO).resultado);
		
	}
	
	@Test
	public void testMapa() {
		ISistema sistema = new Sistema();
		sistema.inicializarSistema(20);
		
		assertEquals(Retorno.Resultado.OK, sistema.registrarVendedor("5.555.555-5", "Omar", "omar@gmail.com", "098123456").resultado);
		
		assertEquals(Retorno.Resultado.OK, sistema.registrarEsquina(-34.909039, -56.195530).resultado);//P1
		assertEquals(Retorno.Resultado.OK, sistema.registrarEsquina(-34.908951, -56.194500).resultado);//P2
		assertEquals(Retorno.Resultado.OK, sistema.registrarPropiedad(-34.908880, -56.193299,TipoPropiedad.CASA, "Rio Negro 1188").resultado);//P3
		
		assertEquals(Retorno.Resultado.OK, sistema.registrarEsquina(-34.909980, -56.195498).resultado);//P4
		assertEquals(Retorno.Resultado.OK, sistema.registrarPropiedad(-34.909910, -56.194372,TipoPropiedad.APARTAMENTO, "Maldonado 1250").resultado);//P5
		assertEquals(Retorno.Resultado.OK, sistema.registrarEsquina(-34.909813, -56.193202).resultado);//P6
		
		assertEquals(Retorno.Resultado.OK, sistema.registrarPuntoInteres(-34.910913, -56.195370, Rubro.FARMACIA, "Farmashop").resultado);//P7
		assertEquals(Retorno.Resultado.OK, sistema.registrarPuntoInteres(-34.910886, -56.194254, Rubro.FARMACIA, "Farmashop 2").resultado);//P8
		assertEquals(Retorno.Resultado.OK, sistema.registrarPuntoInteres(-34.910728, -56.193116, Rubro.CAJERO, "RedBrou").resultado);//P9
		
		assertEquals(Retorno.Resultado.OK, sistema.verMapa().resultado);
	}
		
}
