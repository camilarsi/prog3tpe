package prog3tpe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

public class ServicioBFS {

	private Grafo<?> grafo;
	private HashMap<Integer, String> verticeVisitas = new HashMap<Integer, String>();
	private Queue<Integer> fila;
	
	public ServicioBFS(Grafo<?> grafo) {
		this.grafo = grafo;
	}
	
	private Iterator<Integer> agregarNoVisitados() {
			Iterator<Integer> i = grafo.obtenerVertices();
		    while(i.hasNext()) {
		    	Integer in = i.next();
		    	verticeVisitas.put(in, "no_visitado");
		    }
		    return i;
	}
	
	public List<Integer> bfsForest() {
		agregarNoVisitados();
		Iterator<Integer> i = grafo.obtenerVertices();
		while(i.hasNext()) {
			if(verticeVisitas.get(i.next()) == "no_visitado"){
				this.fila.addAll(this.BFS(i.next()));
			}
		}
		ArrayList<Integer> visitados = new ArrayList<Integer>(this.fila);
		return visitados;
	}
	
	public Queue<Integer> BFS(Integer i){
		verticeVisitas.replace(i, "visitado");
		this.fila.add(i);
	    while(!this.fila.isEmpty()) {
	    	Iterator<Integer> it = grafo.obtenerAdyacentes(i);
	    	while(it.hasNext()) {
	    		Integer adyacente = it.next();
	    		if(verticeVisitas.get(adyacente) == "no_visitado") {
		    		verticeVisitas.replace(adyacente, "visitado");
		    		this.fila.add(adyacente);
	    		}	
	    	}
	    }
	    return this.fila;
	}	
}