package AaDS_Lab10.SocialNetwork;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Stack;

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

    // this is what we'll be modifying to solve the problem
    private boolean findPathHelper(T currentVertex, T endVertx, Set<T> visited, List<T> path) {
        visited.add(currentVertex);
        path.add(currentVertex);

        if (currentVertex.equals(endVertx)) return true; // we want to end once we've traversed all vertexes

        for (T neighbor : getNeighbors(currentVertex)) {
            if (!visited.contains(neighbor)) {
                if (findPathHelper(neighbor, endVertx, visited, path)) return true; // same as before
            }
        }

        path.remove(path.size() - 1);
        return false;

    }

    public void findPath(T startVertex, T endVertex) {
        Set<T> visited = new HashSet<>();
        Stack<T> path = new Stack<>();

        if (findPathHelper(startVertex, endVertex, visited, path)) {
            for (T vertex : path) {
                System.out.println(vertex);
            }
        } else {
            System.out.println("Path not found;");
        }
    }

    public int findDegreeOfSeparation(T startVertex, T endVertex) {
        if (!adjacencyList.containsKey(startVertex) || !adjacencyList.containsKey(endVertex)) {
            return -1; // return -1 if either vertex is not in the graph
        }

        Set<T> visited = new HashSet<>();
        Queue<T> queue = new LinkedList<>();
        Map<T, Integer> distances = new HashMap<>();

        visited.add(startVertex);
        queue.add(startVertex);
        distances.put(startVertex, 0);

        while (!queue.isEmpty()) {
            T vertex = queue.poll();
            for (T neighbor : getNeighbors(vertex)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                    distances.put(neighbor, distances.get(vertex) + 1);

                    if (neighbor.equals(endVertex)) {
                        return distances.get(neighbor);
                    }
                }
            }
        }

        return -1; // return -1 if no path exists
    }


    @Override
    public String toString() {
        String ret = new String();
        for (Entry<T, Set<T>> vertex : adjacencyList.entrySet())
            ret += vertex.getKey() + ": " + vertex.getValue() + "\n";
        return ret;
    }


}

@SuppressWarnings("unchecked")
class AdjacencyMatrixGraph<T> {
    private int numVertices;
    private int[][] matrix;
    private T[] vertices;

    @SuppressWarnings("unchecked")
    public AdjacencyMatrixGraph(int numVertices) {
        this.numVertices = numVertices;
        matrix = new int[numVertices][numVertices];
        vertices = (T[]) new Object[numVertices];
    }

    public void addVertex(int index, T data) {
        vertices[index] = data;
    }

    public T getVertex(int index) {
        return vertices[index];
    }

    public void addEdge(int source, int destination) {
        matrix[source][destination] = 1;
        matrix[destination][source] = 1; // For undirected graph
    }

    public boolean isEdge(int source, int destination) {
        return matrix[source][destination] == 1;
    }


    public void removeEdge(int source, int destination) {
        matrix[source][destination] = 0;
        matrix[destination][source] = 0; // For undirected graph
    }

    @SuppressWarnings("unchecked")
    public void removeVertex(int vertexIndex) {
        if (vertexIndex < 0 || vertexIndex >= numVertices) {
            throw new IndexOutOfBoundsException("Vertex index out of bounds!");
        }
        int[][] newMatrix = new int[numVertices - 1][numVertices - 1];
        T[] newVertices = (T[]) new Object[numVertices - 1];
        // Copy the vertices and matrix excluding the given vertex
        int ni = 0;
        for (int i = 0; i < numVertices; i++) {
            if (i == vertexIndex) continue;
            int nj = 0;
            for (int j = 0; j < numVertices; j++) {
                if (j == vertexIndex) continue;
                newMatrix[ni][nj] = matrix[i][j];
                nj++;
            }
            newVertices[ni] = vertices[i];
            ni++;
        }
        // Replace the old matrix and vertices with the new ones
        matrix = newMatrix;
        vertices = newVertices;
        numVertices--;
    }

    public List<T> getNeighbors(int vertexIndex) {
        List<T> neighbors = new ArrayList<>();
        for (int i = 0; i < matrix[vertexIndex].length; i++) {
            if (matrix[vertexIndex][i] == 1) {
                neighbors.add(vertices[i]);
            }
        }
        return neighbors;
    }


    public AdjacencyListGraph<T> toAdjacencyList() {
        AdjacencyListGraph<T> result = new AdjacencyListGraph<>();

        for (int i = 0; i < numVertices; i++) {
            result.addVertex(vertices[i]);
        }

        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (matrix[i][j] > 0) {
                    result.addEdge(vertices[i], vertices[j]);
                }
            }
        }
        return result;
    }


    @Override
    public String toString() {
        String ret = "  ";
        for (int i = 0; i < numVertices; i++)
            ret += vertices[i] + " ";
        ret += "\n";
        for (int i = 0; i < numVertices; i++) {
            ret += vertices[i] + " ";
            for (int j = 0; j < numVertices; j++)
                ret += matrix[i][j] + " ";
            ret += "\n";
        }
        return ret;
    }

}

@SuppressWarnings("unchecked")
public class SocialNetwork {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int userCount = Integer.parseInt(sc.nextLine());
        AdjacencyListGraph<String> graph = new AdjacencyListGraph<>();

        for (int i = 0; i < userCount; i++) {
            String userInfo = sc.nextLine();
            String[] userParts = userInfo.split(" ");

            // Check if userParts has at least 3 parts (name, surname, number of friends)
            if (userParts.length < 3) {
                System.err.println("Invalid user info format.");
                continue;
            }

            String userName = userParts[0] + " " + userParts[1];
            int friendCount = Integer.parseInt(userParts[2]);

            graph.addVertex(userName);
            for (int j = 0; j < friendCount; j++) {
                String friendName = sc.nextLine();
                graph.addEdge(userName, friendName);
            }
        }

        String user1 = sc.nextLine();
        String user2 = sc.nextLine();

        int degreeOfSeparation = graph.findDegreeOfSeparation(user1, user2);
        System.out.println(degreeOfSeparation);

        sc.close();
    }
}


