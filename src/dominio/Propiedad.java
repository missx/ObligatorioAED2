package dominio;

import sistema.Enumerados.TipoPropiedad;

public class Propiedad extends Punto{
	
	private TipoPropiedad tipo;
	private String direccion;
	private String cedulaVendedor;
	
	
	public TipoPropiedad getTipo() {
		return tipo;
	}
	public void setTipo(TipoPropiedad tipo) {
		this.tipo = tipo;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getCedulaVendedor() {
		return cedulaVendedor;
	}
	public void setCedulaVendedor(String cedulaVendedor) {
		this.cedulaVendedor = cedulaVendedor;
	}
	
	public Propiedad(Double coordX, Double coordY, TipoPropiedad tipo, String dir, String ciVendedor){
		super(coordX, coordY);
		this.direccion = dir;
		this.tipo = tipo;
		this.cedulaVendedor = ciVendedor;
	}
	
	public String listadoDeCoordenadas(){
		String listado = super.getCoordX() + ";" + super.getCoordY() + "|";
		System.out.println("listado 1 " + listado);
		return listado;
	}
	
	
	
	
	
}
