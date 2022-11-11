package it.unibo.generics.graph.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.text.html.HTMLDocument.RunElement;

import it.unibo.generics.graph.api.Graph;

public class GraphImpl<N> implements Graph<N> {

    // private ArrayList<N> graphs = new ArrayList<>();
    private Map<N, List<N>> graphMap = new LinkedHashMap<>();

    public GraphImpl() {
    }

    @Override
    public void addNode(N node) {
        graphMap.putIfAbsent(node, new ArrayList<N>());
    }

    @Override
    public void addEdge(N source, N target) {
        if (source != null && target != null) {
            graphMap.get(source).add(target);
            // graphMap.get(target).add(source);
        }

    }

    @Override
    public Set<N> nodeSet() {
        Set<N> setOfNodes = new HashSet<N>();
        for (N n : graphMap.keySet()) {
            setOfNodes.add(n);
            // System.out.println("nodeSet: " + setOfNodes);
        }
        return setOfNodes;
    }

    @Override
    public Set<N> linkedNodes(Object node) {
        Set<N> setOfNodes = new HashSet<N>();
        for (N n : graphMap.get(node)) {
            setOfNodes.add(n);
        }
        // System.out.println("linkedNodes: " + setOfNodes);
        return setOfNodes;
    }

    @Override
    public List<N> getPath(N source, N target) {
        List<N> path = new LinkedList<>();
        // Map<N, Set<N>> map = bfs();
        // List<N> map = bfs();
        // List<N> map = new LinkedList<>();
        ArrayList<N> visited = new ArrayList<>();
        Boolean foundSource = false;
        Boolean foundTarget = false;
        N father = null;
        path.add(0, target);
        while ((!foundSource) || (!foundTarget)) {
            for (var val : graphMap.entrySet()) {
                for (N n : val.getValue()) {
                    if (!visited.contains(n)) {
                        if ((n == source) && foundTarget) {
                            path.add(0, n);
                            foundSource = true;
                            break;
                        }
                        if (n == target) {
                            father = val.getKey();
                            System.out.print("## target is: " + target);
                            System.out.println("  -  father is: " + father);
                            path.add(0, father);
                            visited.add(n);
                            foundTarget = true;
                            break;
                        }
                    }
                }
                // System.out.println(val.getKey() + " -> " + val.getValue());
            }

        }
        System.out.println("## Visited: " + visited);
        return path;
    }

    /**
     * BFS - level by level
     * enqueue any starting vertex
     * while queue is not empty
     * ____p=dequeue()
     * ____if p is unvisited
     * ________mark it visited
     * ________enqueue all adiacent unvisited vertices of p which are not in queue
     */
    // Map<N, Set<N>> bfs() {
    // List<N> queue = new LinkedList<>();
    // List<N> visited = new ArrayList<>();
    // Map<N, Set<N>> bfs = new HashMap<>();
    // N start = (N) graphMap.keySet().toArray()[0];
    // queue.add(start);
    // while (!queue.isEmpty()) {
    // Set<N> others = new TreeSet<>();
    // N currentNode = queue.remove(0);
    // visited.add(currentNode);
    // for (N n : graphMap.get(currentNode)) {
    // if (!visited.contains(n)) {
    // queue.add(n);
    // }
    // others.add(n);
    // }
    // bfs.put(currentNode, others);
    // }
    // return bfs;
    // }
    //
    // List<N> bfs() {
    // List<N> queue = new LinkedList<>();
    // List<N> visited = new ArrayList<>();
    // List<N> bfs = new LinkedList<>();
    // N start = (N) graphMap.keySet().toArray()[1];
    // queue.add(start);
    // while (!queue.isEmpty()) {
    // Set<N> others = new TreeSet<>();
    // N currentNode = queue.remove(0);
    // visited.add(currentNode);
    // for (N n : graphMap.get(currentNode)) {
    // if (!visited.contains(n)) {
    // queue.add(n);
    // }
    // others.add(n);

    // }
    // bfs.add(currentNode);
    // }

    // return bfs;
    // }
}
