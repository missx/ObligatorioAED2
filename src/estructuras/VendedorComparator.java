package estructuras;

import java.util.Comparator;
import dominio.Vendedor;

public class VendedorComparator implements Comparator<Object>{

	@Override
	public int compare(Object o1, Object o2) {
		if(((Vendedor) o1).getCedula().compareToIgnoreCase(((Vendedor)o2).getCedula()) == 1) return 1;
		if(((Vendedor)o1).getCedula().compareToIgnoreCase(((Vendedor)o2).getCedula()) == 0) return 0;
		else return -1;
	}

}
