package AaDS_Lab11.DistanceFinder;

import java.util.*;


// Not using the aud code using List implementation and also including edge class to track order
class Graph {
    private Map<String, List<Edge>> graph = new HashMap<>();

    public void addRoad(String src, String dest, double distance) {
        this.graph.putIfAbsent(src, new ArrayList<>());
        this.graph.get(src).add(new Edge(dest, distance));
    }

    public List<String> shortestPath(String start, String end) {
        Map<String, Double> distances = new HashMap<>();
        Map<String, String> predecessors = new HashMap<>();
        PriorityQueue<Edge> queue = new PriorityQueue<>(Comparator.comparingDouble(e -> e.distance));

        for (String city : graph.keySet()) {
            distances.put(city, Double.MAX_VALUE);
        }
        distances.put(start, (double) 0);
        queue.add(new Edge(start, 0));

        while (!queue.isEmpty()) {
            Edge current = queue.poll();
            for (Edge edge : graph.get(current.target)) {
                double distanceThroughCurrent = distances.get(current.target) + edge.distance;
                if (distanceThroughCurrent < distances.get(edge.target)) {
                    distances.put(edge.target, distanceThroughCurrent);
                    predecessors.put(edge.target, current.target);
                    queue.add(new Edge(edge.target, distances.get(edge.target)));
                }
            }
        }

        List<String> path = new ArrayList<>();
        for (String at = end; at != null; at = predecessors.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }

    public double getDistance(String src, String dest) {
        if (!graph.containsKey(src)) {
            return -1;
        }
        for (Edge edge : graph.get(src)) {
            if (edge.target.equals(dest)) {
                return edge.distance;
            }
        }
        return -1;
    }

    static class Edge {
        String target;
        double distance;

        Edge(String target, double distance) {
            this.target = target;
            this.distance = distance;
        }
    }
}


public class DistanceFinder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Graph graph = new Graph();
        int N = Integer.parseInt(scanner.nextLine());
        int M = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < M; i++) {
            String[] roadData = scanner.nextLine().split(" ");
            String startCity = roadData[1];
            String endCity = roadData[3];
            double distance = Double.parseDouble(roadData[4]);
            graph.addRoad(startCity, endCity, distance);
        }

        String start = scanner.nextLine();
        String end = scanner.nextLine();

        List<String> pathTo = graph.shortestPath(start, end);
        List<String> pathBack = graph.shortestPath(end, start);

        System.out.println(String.join(" ", pathTo));
        System.out.println(String.join(" ", pathBack));

        double totalDistance = calculateTotalDistance(graph, pathTo) + calculateTotalDistance(graph, pathBack);
        System.out.println(totalDistance);
    }

    private static double calculateTotalDistance(Graph graph, List<String> path) {
        double totalDistance = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            totalDistance += graph.getDistance(path.get(i), path.get(i + 1));
        }
        return totalDistance;
    }
}

