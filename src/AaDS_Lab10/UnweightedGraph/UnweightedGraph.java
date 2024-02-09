package AaDS_Lab10.UnweightedGraph;

import java.util.*;

@SuppressWarnings("unchecked")
class AdjacencyListGraph<T> {
    private Map<T, Set<T>> adjacencyList;

    public AdjacencyListGraph() {
        this.adjacencyList = new HashMap<>();
    }

    // Add a vertex to the graph
    public void addVertex(T vertex) {
        if (!adjacencyList.containsKey(vertex)) {
            adjacencyList.put(vertex, new HashSet<>());
        }
    }

    // Remove a vertex from the graph
    public void removeVertex(T vertex) {
        // Remove the vertex from all adjacency lists
        for (Set<T> neighbors : adjacencyList.values()) {
            neighbors.remove(vertex);
        }
        // Remove the vertex's own entry in the adjacency list
        adjacencyList.remove(vertex);
    }

    // Add an edge to the graph
    public void addEdge(T source, T destination) {
        addVertex(source);
        addVertex(destination);

        adjacencyList.get(source).add(destination);
        adjacencyList.get(destination).add(source); // for undirected graph
    }

    // Remove an edge from the graph
    public void removeEdge(T source, T destination) {
        if (adjacencyList.containsKey(source)) {
            adjacencyList.get(source).remove(destination);
        }
        if (adjacencyList.containsKey(destination)) {
            adjacencyList.get(destination).remove(source); // for undirected graph
        }
    }

    // Get all neighbors of a vertex
    public Set<T> getNeighbors(T vertex) {
        return adjacencyList.getOrDefault(vertex, new HashSet<>());
    }

    public void DFS(T startVertex) {
        Set<T> visited = new HashSet<>();
        DFSUtil(startVertex, visited);
    }

    private void DFSUtil(T vertex, Set<T> visited) {
        // Mark the current node as visited and print it
        visited.add(vertex);
        System.out.print(vertex + " ");

        // Recur for all the vertices adjacent to this vertex
        for (T neighbor : getNeighbors(vertex)) {
            if (!visited.contains(neighbor)) {
                DFSUtil(neighbor, visited);
            }
        }
    }


    public void DFSNonRecursive(T startVertex) {
        Set<T> visited = new HashSet<>();
        Stack<T> stack = new Stack<>();

        stack.push(startVertex);
        while (!stack.isEmpty()) {
            T vertex = stack.pop();
            if (!visited.contains(vertex)) {
                visited.add(vertex);
                System.out.print(vertex + " ");
                for (T neighbor : getNeighbors(vertex)) {
                    if (!visited.contains(neighbor)) {
                        stack.push(neighbor);
                    }
                }
            }
        }
    }

    public void BFS(T startVertex) {
        Set<T> visited = new HashSet<>();
        Queue<T> queue = new LinkedList<>();

        visited.add(startVertex);
        queue.add(startVertex);

        while (!queue.isEmpty()) {
            T vertex = queue.poll();
            System.out.print(vertex + " ");

            for (T neighbor : getNeighbors(vertex)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
    }

    public void findPath(T startVertex, T endVertex) {
        Set<T> visited = new HashSet<>();
        Stack<T> invertedPath = new Stack<>();
        visited.add(startVertex);
        invertedPath.push(startVertex);

        while(!invertedPath.isEmpty() && !invertedPath.peek().equals(endVertex)) {
            T currentVertex = invertedPath.peek();
            T tmp = currentVertex;

            for(T vertex : getNeighbors(currentVertex)) {
                tmp = vertex;
                if(!visited.contains(vertex)) {
                    break;
                }
            }

            if(!visited.contains(tmp)) {
                visited.add(tmp);
                invertedPath.push(tmp);
            }
            else {
                invertedPath.pop();
            }
        }

        Stack<T> path = new Stack<>();
        while(!invertedPath.isEmpty()) {
            path.push(invertedPath.pop());
        }
        while(!path.isEmpty()) {
            System.out.println(path.pop());
        }
    }

    public boolean isAdjacent(int vertexA, int vertexB){
        return adjacencyList.containsKey(vertexA)
                && adjacencyList.get(vertexA).contains(vertexB);
    }

//    @Override
//    public String toString() {
//        String ret = new String();
//        for (Entry<T, Set<T>> vertex : adjacencyList.entrySet())
//            ret += vertex.getKey() + ": " + vertex.getValue() + "\n";
//        return ret;
//    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        for (Map.Entry<T, Set<T>> vertex : adjacencyList.entrySet()) {
            ret.append(vertex.getKey()).append(": ").append(vertex.getValue()).append("\n");
        }
        ret.append("\n");
        return ret.toString();
    }

}

@SuppressWarnings("unchecked")
public class UnweightedGraph {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfCommands = Integer.parseInt(scanner.nextLine());
        AdjacencyListGraph graph = null;

        for (int i = 0; i < numberOfCommands; i++) {
            String command = scanner.nextLine();
            String[] parts = command.split(" ");

            switch (parts[0]) {
                case "CREATE":
                    graph = new AdjacencyListGraph();
                    break;
                case "ADDEDGE":
                    int v1 = Integer.parseInt(parts[1]);
                    int v2 = Integer.parseInt(parts[2]);
                    graph.addEdge(v1, v2);
                    break;
                case "DELETEEDGE":
                    v1 = Integer.parseInt(parts[1]);
                    v2 = Integer.parseInt(parts[2]);
                    graph.removeEdge(v1, v2);
                    break;
                case "ADJACENT":
                    v1 = Integer.parseInt(parts[1]);
                    v2 = Integer.parseInt(parts[2]);
                    System.out.println(graph.isAdjacent(v1, v2));
                    break;
                case "PRINTGRAPH":
                    System.out.print(graph.toString());
                    break;
            }
        }
        scanner.close();
    }
}
