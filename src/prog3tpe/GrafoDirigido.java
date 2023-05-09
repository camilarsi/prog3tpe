package prog3tpe;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;

public class GrafoDirigido<T> implements Grafo<T> {
	
	private HashMap<Integer, LinkedList<Arco<T>>> listaVertice = new HashMap<Integer, LinkedList<Arco<T>>>();
	
	@Override
	public void agregarVertice(int verticeId) {
		LinkedList<Arco<T>> l = new LinkedList<Arco<T>>();
		listaVertice.put(verticeId, l);
	}

	@Override
	public void borrarVertice(int verticeId) {
		listaVertice.remove(verticeId);
	}

	@Override
	public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
		Arco<T> nuevo = new Arco<T>(verticeId1, verticeId2, etiqueta);
		listaVertice.get(verticeId1).add(nuevo);
	}

	@Override
	public void borrarArco(int verticeId1, int verticeId2) {
		LinkedList<Arco<T>> l = listaVertice.get(verticeId1);
		for(Arco<T> a : l) {
			if(a.getVerticeDestino() == (verticeId2)) {
				l.remove(a);
			}
		}
		
	}

	@Override
	public boolean contieneVertice(int verticeId) { 
		if(listaVertice.get(verticeId).equals(null)) { // return (listaVertice.containsKey(verticeId));
			return false;
		}
		else {
			return true;	
		}
		
	}

	@Override
	public boolean existeArco(int verticeId1, int verticeId2) {
		LinkedList<Arco<T>> l = listaVertice.get(verticeId1);
		for(Arco<T> a : l) {
			int verticeD = a.getVerticeDestino();
			if(verticeD == verticeId2) {
				return true;
			}
		
		}
		return false;
	}

	@Override
	public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
		if(existeArco(verticeId1, verticeId2)) {
			LinkedList<Arco<T>> l = listaVertice.get(verticeId1);
			for(Arco<T> a : l) {
				if(a.getVerticeDestino() == verticeId2) {
					return a;
				}
			}
		}
		return null;
	}

	@Override
	public int cantidadVertices() {
		return listaVertice.size();
	}

	@Override
	public int cantidadArcos() {
		int cantidadA = 0;
		for(int i = 0; i <= this.cantidadVertices(); i++ ){
			LinkedList<Arco<T>> l = listaVertice.get(i);
			cantidadA += l.size(); 
		}
		return cantidadA;
	}
	/* si listaVertices es: {4:[...], 5:[...]}, entonces se trata de acceder al key 00 y se rompe.
	 public int cantidadArcos() {
		int cantidadA = 0;
	  	Set<Integer> ids = this.listaVertice.keySet();
		for(Integer id : ids) {
			cantidadA += listaVertice.get(id).size();
		}
		return cantidadA;
	}*/

	@Override
	public Iterator<Integer> obtenerVertices() {
		return listaVertice.keySet().iterator();
	}

	@Override
	public Iterator<Integer> obtenerAdyacentes(int verticeId) {
		ArrayList<Integer> array = new ArrayList<Integer>();
		LinkedList<Arco<T>> l = listaVertice.get(verticeId);
		for(Arco<T> a : l) {
			array.add(a.getVerticeDestino());
		}
		return array.iterator();
	}

	/*@Override
	public Iterator<Arco<T>> obtenerArcos() {
		ArrayList<Arco<T>> array = new ArrayList<Arco<T>>();
		
		for(LinkedList<Arco<T>> : listaVertice.keySet()) {
			LinkedList<Arco<T>> l = listaVertice.get(i);
			array.addAll(l);
		}
		return array.iterator();
	}
	*/
	@Override
	public Iterator<Arco<T>> obtenerArcos(int verticeId) {
		LinkedList<Arco<T>> l = listaVertice.get(verticeId);
		return l.iterator();
		}

	@Override
	public Iterator<Arco<T>> obtenerArcos() {
		LinkedList<Arco<T>> iterators = new LinkedList<Arco<T>>();
		Set<Integer> ids = this.listaVertice.keySet();
		for(Integer id : ids) {
			iterators.addAll(listaVertice.get(id));
		}
		return iterators.iterator();
	}

	
}