package dominio;
import sistema.Enumerados.Rubro;

public class PuntoDeInteres extends Punto{

	private Rubro rubro;
	private String nombre;
	
	
	private Rubro getRubro() {
		return rubro;
	}
	private void setRubro(Rubro rubro) {
		this.rubro = rubro;
	}
	private String getNombre() {
		return nombre;
	}
	private void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public PuntoDeInteres(Double x, Double y, Rubro r, String nom){
		super(x, y);
		this.rubro = r;
		this.nombre = nom;
	}
	
	
}
