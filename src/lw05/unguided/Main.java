package lw05.unguided;

import java.util.*;

public class Main {

    static int[][] graph;
    static int size;
    static String[] names;

    static int[] bfsLevel;
    static int[] bfsPrev;
    static int[] dijkstraPrev;

    // Adds a two-way flight corridor (complete, do not change)
    static void addEdge(int from, int to, int minutes) {
        graph[from][to] = minutes;
        graph[to][from] = minutes;
    }

    // BFS: finds the route with the fewest stops/hops
    static List<Integer> bfs(int start) {
        List<Integer> order = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        bfsLevel = new int[size];
        bfsPrev = new int[size];

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            int current = queue.poll(); //1
            order.add(current);

            for (int next = 0; next < size; next++) {
                if (graph[current][next] > 0 && !visited.contains(next)) { //2
                    visited.add(next);
                    queue.add(next); //3
                    bfsLevel[next] = bfsLevel[current] + 1; //4
                    bfsPrev[next] = current;    //5
                }
            }
        }
        return order;
    }

    // Prints hubs level by level
    static void printLevels(List<Integer> order) {
        int lastLevel = bfsLevel[order.get(order.size() - 1)];

        for (int level = 0; level <= lastLevel; level++) {
            System.out.println();
            System.out.print("Level " + level + ": ");

            boolean first = true;

            for (int i = 0; i < order.size(); i++) {
                int hub = order.get(i);

                if (bfsLevel[hub] == level) { //1
                    if (!first) {
                        System.out.print(", ");
                    }

                    System.out.print(names[hub]);
                    first = false;
                }
            }
        }
        System.out.println();
    }

    // Dijkstra: finds the fastest route
    static void dijkstraAlg(int start) {
        int[] dist = new int[size];

        for (int i = 0; i < size; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        dist[start] = 0;

        boolean[] visited = new boolean[size];
        dijkstraPrev = new int[size];

        for (int i = 0; i < size; i++) {
            int current = findTheNextNode(visited, dist);

            if (current == -1) {
                break;
            }

            visited[current] = true; //1

            for (int next = 0; next < size; next++) {
                if (graph[current][next] > 0
                        && !visited[next]) {

                    int newDist = dist[current] + graph[current][next];

                    if (graph[current][next] > 0 && dist[current] != Integer.MAX_VALUE && newDist < dist[next]) { //2
                        dist[next] = newDist;
                        dijkstraPrev[next] = current;  //3
                    }
                }
            }
        }
    }

    // Finds the unvisited hub with the smallest distance
    static int findTheNextNode(boolean[] visited, int[] dist) {
        int min = Integer.MAX_VALUE;
        int closest = -1;

        for (int i = 0; i < size; i++) {
            if (!visited[i] && dist[i] < min) {
                min = dist[i];
                closest = i;
            }
        }

        return closest;
    }

    // Rebuilds the route using prev[]
    static List<Integer> buildPath(int current, int[] prev, int start) {
        List<Integer> path;

        if (current == start) {
            path = new ArrayList<>();
        } else {
            path = buildPath(prev[current], prev, start); //1
        }

        path.add(current);
        return path;
    }

    // Calculates the total travel time
    static int pathTime(List<Integer> path) {
        int total = 0;

        for (int i = 0; i < path.size() - 1; i++) {
            total += graph[path.get(i)][path.get(i + 1)]; //1
        }

        return total;
    }

    // Prints the route (complete, do not change)
    static void printPath(List<Integer> path) {
        for (int i = 0; i < path.size(); i++) {
            System.out.print(names[path.get(i)]);

            if (i < path.size() - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }

    // Prints route information (complete, do not change)
    static void printSummary(String title, List<Integer> path) {
        System.out.println("=== " + title + " ===");
        printPath(path);

        System.out.println("Stops: " + (path.size() - 1)
                + " | Time: " + pathTime(path) + " min");
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(Main.class.getResourceAsStream("delivery.txt"))) {
      //try (Scanner sc = new Scanner(Main.class.getResoruceAsStream("express.txt"))) {
            size = sc.nextInt();
            names = new String[size];
            graph = new int[size][size];

            for (int i = 0; i < size; i++) {
                names[i] = sc.next();
            }

            int edges = sc.nextInt();
            for (int i = 0; i < edges; i++) {
                int from = sc.nextInt();
                int to = sc.nextInt();
                int minutes = sc.nextInt();

                addEdge(from, to, minutes);
            }

            int start = sc.nextInt();
            int dest = sc.nextInt();

            System.out.print("=== Hub Levels from Central ===");
            List<Integer> bfsOrder = bfs(start);
            printLevels(bfsOrder);

            List<Integer> bfsPath = buildPath(dest, bfsPrev, start);
            printSummary("Fewest Stops from " + names[start] + " to " + names[dest], bfsPath);

            dijkstraAlg(start);
            List<Integer> dijkstraPath = buildPath(dest, dijkstraPrev, start);
            printSummary("Fastest Route from " + names[start] + " to " + names[dest], dijkstraPath);
        
            System.out.println("=== Comparison ===");
            System.out.println("The fastest route saves " + (pathTime(bfsPath) - pathTime(dijkstraPath)) + " min but needs " + (dijkstraPath.size() - bfsPath.size()) + " more stops.");
        } 
    }
}