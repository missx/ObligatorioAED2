package interfaces;

public interface IQueue {
	
	/*
	 * Inserta un elem al final de la lista
	 */
	public void enqueue(Object elem);	
	
	/*
	 * Saca un elem del ppio de la lista
	 */
	public void dequeue();
	
	/*
	 * Retorna el primer elem de la lista
	 */
	public Object front();
	
	/*
	 * True si y solo si esta vacia la lista
	 */
	public boolean isEmpty();
	
	/*
	 * True si y solo si esta llena la lista
	 */
	public boolean isFull();
	
}
