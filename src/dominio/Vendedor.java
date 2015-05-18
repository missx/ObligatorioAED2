package dominio;

import interfaces.IListaVendedor;

public class Vendedor implements IListaVendedor{

	private String cedula;
	private String nombre;
	private String email;
	private String celular;
	
	@Override
	public int largo() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean esVacio() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pertenece(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void agregarPpio(Object o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void borrarPpio() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void borrar(Object o) {
		// TODO Auto-generated method stub
		
	}

	private String getCedula() {
		return cedula;
	}

	private void setCedula(String cedula) {
		this.cedula = cedula;
	}

	private String getNombre() {
		return nombre;
	}

	private void setNombre(String nombre) {
		this.nombre = nombre;
	}

	private String getEmail() {
		return email;
	}

	private void setEmail(String email) {
		this.email = email;
	}

	private String getCelular() {
		return celular;
	}

	private void setCelular(String celular) {
		this.celular = celular;
	}
	
	public Vendedor(String nom, String ced, String cel, String email){
		this.cedula = ced;
		this.celular = cel;
		this.email = email;
		this.nombre = nom;
	}

}
