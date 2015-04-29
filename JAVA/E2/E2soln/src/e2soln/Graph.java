package e2soln;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;

/**
 * 
 * @author Jamie
 *
 */
public class Graph<T> {

	private Map<T, Set<T>> graph;

	public Graph(){
		graph = new HashMap<T, Set<T>>();
	}

	public void addEdge(T node1, T node2){
		//create any missing nodes
		if (!graph.containsKey(node1)){
			graph.put(node1, null);
		}
		if (!graph.containsKey(node2)){
			graph.put(node2, null);
		}
		Set<T> Node1Set = graph.get(node1);
		Set<T> Node2Set = graph.get(node2);
		if (!Node1Set.contains(node2)){
			//add Node2 to Node1's set
			Node1Set.add(node2);
			if (!Node2Set.contains(node1)){
				//add Node1 to Node2's set
				Node2Set.add(node1);
			}

		}
	}


public boolean areAdjacent(T node1, T node2) throws EdgeException, Exception{
	//create any missing nodes
	if (!graph.containsKey(node1)){
		throw new EdgeException();
	}
	if (!graph.containsKey(node2)){
		throw new EdgeException();
	}
	Set<T> Node1Set = graph.get(node1);
	return Node1Set.contains(node2);
}

public Set<T> getNodes(){
	return graph.keySet();
}

public Set<T> getNeighbours(T node){
	return graph.get(node);
}

/**
 * Returns the number of nodes in this Graph.
 * @return The number of nodes in this Graph.
 */
public int getNumNodes() {
    return getNodes().size();   
}

/**
 * Returns the number of edges in this undirected Graph.
 * @return The number of edges in this undirected Graph. 
 */
public int getNumEdges() {  
    int total = 0;

    for (T node : getNodes()) {
        total += getNeighbours(node).size();
    }

    return total / 2;
}

@Override
public String toString() {       
    String result = "";
    result += "Number of nodes: " + getNumNodes() + "\n";
    result += "Number of edges: " + getNumEdges() + "\n";
    
    for (T node: getNodes()) {
        result += node + " is adjacent to: ";
        for  (T neighbour: getNeighbours(node)) {
            result += neighbour + " ";
        }
        result += "\n";
    }
    return result;
}
}

