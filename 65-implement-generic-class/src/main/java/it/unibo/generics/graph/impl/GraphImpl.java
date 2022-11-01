package it.unibo.generics.graph.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.unibo.generics.graph.api.Graph;

public class GraphImpl<N> implements Graph<N> {

    // private ArrayList<N> graphs = new ArrayList<>();
    private Map<N, List<N>> graphMap = new HashMap<>();

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
        }
        return setOfNodes;
    }

    @Override
    public Set<N> linkedNodes(Object node) {
        Set<N> setOfNodes = new HashSet<N>();
        for (N n : graphMap.get(node)) {
            setOfNodes.add(n);
        }
        return setOfNodes;
    }

    @Override
    public List<N> getPath(Object source, Object target) {
        List<N> path = new LinkedList<>();
        // TODO Auto-generated method stub
        return path;
    }

}
