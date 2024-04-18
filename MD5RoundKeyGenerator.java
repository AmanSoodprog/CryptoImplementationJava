import java.util.*;

public class DepthLimitedSearch {

    static class Graph {
        Map<Character, List<Character>> adjacencyList;

        public Graph() {
            adjacencyList = new HashMap<>();
        }

        public void addEdge(Character source, Character destination) {
            adjacencyList.computeIfAbsent(source, k -> new ArrayList<>()).add(destination);
            adjacencyList.computeIfAbsent(destination, k -> new ArrayList<>()).add(source);
        }
    }

    public static List<Character> depthLimitedSearch(Graph graph, Character start, Character goal, int depthLimit) {
        if (start == goal) {
            return Arrays.asList(start);
        }

        Set<Character> visited = new HashSet<>();
        Deque<Node> stack = new ArrayDeque<>();
        stack.push(new Node(start, new ArrayList<>(Arrays.asList(start))));

        while (!stack.isEmpty()) {
            Node currentNode = stack.pop();
            Character currentNodeValue = currentNode.value;
            List<Character> currentPath = currentNode.path;

            if (!visited.contains(currentNodeValue)) {
                visited.add(currentNodeValue);
                if (currentPath.size() <= depthLimit) {
                    for (Character neighbor : graph.adjacencyList.getOrDefault(currentNodeValue, Collections.emptyList())) {
                        List<Character> newPath = new ArrayList<>(currentPath);
                        newPath.add(neighbor);
                        if (neighbor.equals(goal)) {
                            return newPath;
                        }
                        stack.push(new Node(neighbor, newPath));
                    }
                }
            }
        }
        return null;
    }

    static class Node {
        Character value;
        List<Character> path;

        Node(Character value, List<Character> path) {
            this.value = value;
            this.path = path;
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addEdge('A', 'B');
        graph.addEdge('A', 'C');
        graph.addEdge('B', 'D');
        graph.addEdge('B', 'E');
        graph.addEdge('C', 'F');
        graph.addEdge('E', 'F');

        Character startNode = 'A';
        Character goalNode = 'F';
        int depthLimit = 4;

        List<Character> path = depthLimitedSearch(graph, startNode, goalNode, depthLimit);
        if (path != null) {
            int distance = path.size() - 1;
            System.out.println("Shortest distance between " + startNode + " and " + goalNode + " is " + distance);
        } else {
            System.out.println("Not found within limit.");
        }
    }
}
