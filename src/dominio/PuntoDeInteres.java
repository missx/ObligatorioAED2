package dominio;
import sistema.Enumerados.Rubro;

public class PuntoDeInteres extends Punto{

	private Rubro rubro;
	private String nombre;
	
	
	public Rubro getRubro() {
		return rubro;
	}
	public void setRubro(Rubro rubro) {
		this.rubro = rubro;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public PuntoDeInteres(Double x, Double y, Rubro r, String nom){
		super(x, y);
		this.rubro = r;
		this.nombre = nom;
	}
	
	
}
