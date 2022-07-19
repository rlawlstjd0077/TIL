package leetcode.graph;

import java.util.*;

/**
 * 133. Clone Graph (https://leetcode.com/problems/clone-graph/)
 */
class CloneGraph {
    final Map<Integer, Node> visited = new HashMap<>();

    public Node cloneGraph(Node node) {
        visitRecursive(node);
        return visited.get(1);
    }

    void visitRecursive(Node sourceNode) {
        Node cloneNode = getOrCraeteNode(sourceNode.val);

        for (Node node: sourceNode.neighbors) {
            if (!visited.containsKey(node.val)) {
                visitRecursive(node);
            }
            cloneNode.neighbors.add(visited.get(node.val));
        }
    }

    Node getOrCraeteNode(int val){
        if (visited.containsKey(val)) {
            return visited.get(val);
        } else {
            Node node = new Node(val);
            visited.put(val, node);
            return node;
        }
    }
}

// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return val == node.val && Objects.equals(neighbors, node.neighbors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(val, neighbors);
    }
}