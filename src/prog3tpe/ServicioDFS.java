package prog3tpe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ServicioDFS {

	private Grafo<?> grafo;
	private HashMap<Integer, String> verticeEstados = new HashMap<Integer, String>();
	public ServicioDFS(Grafo<?> grafo) {
		this.grafo = grafo;
	}
	
	private void agregarEstados() {
		Iterator<Integer> i = grafo.obtenerVertices();
	    while(i.hasNext()) {
	    	Integer in = i.next();
	    	verticeEstados.put(in, "white");
	    }
	}
	
	public List<Integer> dfsForest() {
		this.agregarEstados();
		Iterator<Integer> i = grafo.obtenerVertices();
		ArrayList<Integer> id = new ArrayList<Integer>();
		while(i.hasNext()) {
			if(verticeEstados.get(i.next()) == "white"){
				id.addAll(this.visit(i.next()));
			}
		}
		return id;
	}
	public Grafo<?> getGrafo() {
		return grafo;
	}
	public ArrayList<Integer> visit(Integer i) {
		ArrayList<Integer> array = new ArrayList<Integer>();
		array.add(i);
		verticeEstados.replace(i, "yellow");
		Iterator<Integer> it = grafo.obtenerAdyacentes(i);
	    while(it.hasNext()) {
	    	Integer adyacente = it.next();
	    	if(verticeEstados.get(adyacente) == "white") {
	    		array.addAll(visit(adyacente));
	    	}
	    }

		return array;
	}

}