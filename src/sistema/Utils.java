package sistema;

import java.awt.Desktop;
import java.net.URI;

import dominio.Esquina;
import dominio.Propiedad;
import dominio.Punto;
import dominio.Vendedor;
import estructuras.Hash;

public class Utils {
	
	/*
	 * Verifica que el formato de la cedula sea correcto
	 * @param String ced: cedula a verificar
	 * @return boolean: true: correcto, false: incorrecto
	 */
	public static boolean verificarCedula(String ced){
		if(ced != null && ced.length() == 11){
			if(ced.matches("[\\d]{1}\\.[\\d]{3}\\.[\\d]{3}-\\d{1}")){
				return true;
			}
			return false;
		}
		return false;
	}
	
	/*
	 * Verifica que el formato del celular sea correcto
	 * @param String cel: celular a verificar
	 * @return boolean: true: correcto, false: incorrecto
	 */
	public static boolean verificarCelular(String cel){
		
		if(cel != null && cel.length() == 9){
			if(cel.matches("09[0-9]{7}")){
				return true;
			}
			return false;
		}
		return false;
	}
	
	/*
	 * Verifica que el formato del mail sea correcto
	 * @param String mail: mail a verificar
	 * @return boolean: true: correcto, false: incorrecto
	 */
	public static boolean verificarMail(String mail){
		if(mail != null){
			if(mail.matches("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$")){
				return true;
			}
			return false;
		}
		return false;
	}
	
	/*
	 * Convierte la cedula del vendedor de string a int
	 * @param Vendedor v
	 * @return int
	 */
	public static int convertirCedulaAInt(Vendedor v){
		if(v != null){
			String cedStr = v.getCedula().replace(".", "");
			cedStr = cedStr.replace("-", "");
			int cedulaInt = Integer.parseInt(cedStr);
			return cedulaInt;
		}
		return 0;
	}
	
	/*
	 * Crea un mapa en google maps para mostrar 
	 * los puntos
	 */
	public static void crearMapa(Hash hashPtos){
		String urlMapa = "http://maps.googleapis.com/maps/api/staticmap?center=-34.8988,-56.1660&zoom=13&size=600x600&maptype=roadmap&";
		Punto[] tabla = hashPtos.getTabla();
		
		for(int i = 0; i < hashPtos.getTamañoTabla(); i++){
			if(tabla[i] != null){
				if(tabla[i] instanceof Propiedad){
					//propiedades azul, label P
					urlMapa += "markers=color:blue%7Clabel:P%7C" + tabla[i].getCoordX()  +"," + tabla[i].getCoordY() + "&";
				}else if(tabla[i] instanceof Esquina){
					//esquinas amarillo, label E
					urlMapa += "markers=color:yellow%7Clabel:E%7C" + tabla[i].getCoordX()  +"," + tabla[i].getCoordY() + "&";
				}else{
					//punto de interes rojo, label Pto
					urlMapa += "markers=color:red%7Clabel:I%7C" + tabla[i].getCoordX()  +"," + tabla[i].getCoordY() + "&";
				}
			}
			
		}
		urlMapa += "sensor=false";	 
		
		Desktop desk = Desktop.getDesktop();
		try{
			desk.browse(new URI(urlMapa));
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
	}
}
