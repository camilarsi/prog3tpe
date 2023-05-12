package prog3tpe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*Caminos: dado un origen, un destino y un límite “lim” retorna todos los caminos que, partiendo del
vértice origen, llega al vértice de destino sin pasar por más de “lim” arcos. Aclaración importante: en
un camino no se puede pasar 2 veces por el mismo arco.*/

public class ServicioCaminos {
	
	private Grafo<?> grafo;
	private int origen;
	private int destino;
	private int lim;
	private List<List<Integer>> caminos;
	// Servicio caminos
	public ServicioCaminos(Grafo<?> grafo, int origen, int destino, int lim) {
		this.grafo = grafo;
		this.origen = origen;
		this.destino = destino;
		this.lim = lim;
		this.caminos = new ArrayList<List<Integer>>();
		}

	public List<List<Integer>> caminos() {
		
		if (!this.grafo.obtenerAdyacentes(origen).hasNext()) {
			return null;
		}
		while(this.grafo.contieneVertice(origen) && this.grafo.contieneVertice(destino)) {
			ArrayList<Integer> auxCamino = new ArrayList<Integer>();
			ArrayList<Integer> caminoEncontrado = buscarCamino(auxCamino, origen);
			if(caminoEncontrado!= null && !this.caminos.contains(caminoEncontrado)) {
				this.caminos.add(caminoEncontrado);
			}
		}
		return this.caminos;
		
	}

	public ArrayList<Integer> buscarCamino(ArrayList<Integer> auxCamino, int currVertice){
		if(!auxCamino.contains(currVertice)) {
			auxCamino.add(origen);			
		}
		if(currVertice == destino) {
			auxCamino.add(destino);
			return auxCamino;
		}else if(this.grafo.obtenerAdyacentes(currVertice)!= null){
			Iterator<Integer> adyacentes = this.grafo.obtenerAdyacentes(currVertice);
			while(adyacentes.hasNext() && auxCamino.size() < lim) {
				int currAdyacente = adyacentes.next();
				return buscarCamino(auxCamino, currAdyacente);
			}
		} return null;
		
	}
}
