import java.util.*;
    

public class Graph {
    public Map<String,ArrayList<Vertex>> nameVertexMap;

    public Graph(){
    	 nameVertexMap = new HashMap<String, Vertex>();
    }
    

    public void addEdge(String start, String end){
        Vertex v = getVertex(sourceName);
        Vertex w = getVertex(destName);
	    VertexMap.get(v.name).add(w);
	    VertexMap.get(v.name).add(v);
    }

    private Vertex getVertex(String vertexName){
        if(vertexName == null){
            return null;
        }

        Vertex v = vertexMap.get(vertexName);
        if(v == null){
            v = new Vertex(vertexName);
            vertexMap.put(vertexName, v);
        }
        return v;
    }





    //CHANGE
    public void dfs(String startName, String goalName){
        clearAll(); // initialize graph
        Set<Vertex> exploredSet = new HashSet<Vertex>();
        Stack<Vertex> nodes = new Stack<Vertex>();
        Vertex v = vertexMap.get(startName);
        Vertex goal = vertexMap.get(goalName);
        v.dist = 0;
        nodes.push(v);
        while(!nodes.isEmpty()){
             v = nodes.pop();
             exploredSet.add(v);
             if(v.equals(goal)){
                 printPath(v.name);
                 return;
             }
             for(Edge e : v.adj){
                 if(!exploredSet.contains(e.v)){
                     e.v.prev = v;
                     e.v.dist = v.dist + e.cost;
                     nodes.push(e.v);
                 }
             }
        }
        System.out.println("Could not find goal vertex");
    }



    //CHANGE
    public boolean hasCycle(){
	return getCycles().keySet().size() > 0;	
    }

    public Map<String, String> getCycles(){
        clearAll(); // initialize graph
	Map<String,String> cycles = new HashMap<String,String>();
        Set<Vertex> visitedSet = new HashSet<Vertex>();
        Set<Vertex> recursionStackSet = new HashSet<Vertex>();
        boolean cycle = false;
        for(Vertex v: vertexMap.values()){
            if(!visitedSet.contains(v)){
               	cycleCheckerHelper(v, visitedSet, recursionStackSet, cycles);
            }
        }
	return cycles;
    }

    private void cycleCheckerHelper(Vertex v, Set<Vertex> visitedSet, Set<Vertex> recursionStackSet, Map<String,String> cycles){
        visitedSet.add(v);
        recursionStackSet.add(v);
        for(Edge e: v.adj){
            if(!visitedSet.contains(e.v)){
                cycleCheckerHelper(e.v, visitedSet, recursionStackSet, cycles);
            } else if (recursionStackSet.contains(e.v)) {
                cycles.put(e.v.name, v.name);
            }
        }
        recursionStackSet.remove(v);
    }

    public void bfs(String startName, String goalName){
        clearAll(); // initialize graph
        Set<Vertex> exploredSet = new HashSet<Vertex>();
        Queue<Vertex> nodes = new LinkedList<Vertex>();
        Vertex v = vertexMap.get(startName);
        Vertex goal = vertexMap.get(goalName);
        v.dist = 0;
        nodes.add(v);
        while(!nodes.isEmpty()){
            v = nodes.poll();
            exploredSet.add(v);
            if(v.equals(goal)){
                printPath(v.name);
                return;
            }
            for(Edge e : v.adj){
                if(!exploredSet.contains(e.v)){
                    e.v.prev = v;
                    e.v.dist = v.dist + e.cost;
                    nodes.add(e.v);
                }
            }
        }
        System.out.println("Could not find goal vertex");
    }

    public void ucs(String startName, String goalName){
        clearAll(); // initialize graph
        Set<Vertex> exploredSet = new HashSet<Vertex>();
        PriorityQueue<Vertex> nodes = new PriorityQueue<Vertex>(vertexMap.size(),new Comparator<Vertex>(){
            public int compare(Vertex v1, Vertex v2){
                double diff = v1.dist - v2.dist;
                if (diff < 0){
                    return -1;
                } else if (diff > 0) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        Vertex v = vertexMap.get(startName);
        Vertex goal = vertexMap.get(goalName);
        v.dist = 0;
        nodes.add(v);
        while(!nodes.isEmpty()){
            v = nodes.poll();
            exploredSet.add(v);
            if(v.equals(goal)){
                printPath(v.name);
                return;
            }
            for(Edge e : v.adj){
                if(!exploredSet.contains(e.v)){
                    e.v.prev = v;
                    e.v.dist = v.dist + e.cost;
                    nodes.add(e.v);
                }
            }
        }
        System.out.println("Could not find goal vertex");
    }

    // Precondition: assumes the graph is a tree.
    // WARNING: Output not defined for non-tree graphs, may not terminate
    // Output: a hashMap representing the subtree rooted at root
    // key is node name, value is parent name, null at root
    private Map<String, String> toNodeParentHashMap(Vertex root) {
    	Map<String, String> output = new HashMap<String, String>();
	Stack<Vertex> stack = new Stack<Vertex>();
	stack.push(root);
	output.put(root.name, null);
	while(!stack.isEmpty()){
		Vertex node = stack.pop();
		
		for(Edge e : node.adj) {
			output.put(e.v.name, node.name);
			stack.push(e.v);
		}
	}

	return output;
    }

    public Map<String, String> getNodeParentHashMap(String rootName){
    	return toNodeParentHashMap(nameVertexMap.get(rootName));
    }





    class Edge {
    public Vertex v;
    public double cost;

    public Edge(Vertex d, double c){
        this.v = d;
        this.cost = c;
    }

}


    private Map<String, Vertex> vertexMap = new HashMap<String, Vertex>();
}



class Vertex {
    public String name;
    public List<Vertex> adj;
    public Vertex prev;
    public int scratch;
    public ClassNode node;

    public Vertex(String nm){
        this.name = nm;
        this.adj = new LinkedList<Edge>();
        clear();

    }

    public void clear(){
        this.prev = null;
        this.scratch = 0;
    }

    @Override
    public boolean equals(Object other){
        if(other instanceof Vertex){
            Vertex otherV = (Vertex) other;
            return this.name.equals(otherV.name);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode(){
        return this.name.hashCode();
    }

    public String toString(){
        return name;
    }
}