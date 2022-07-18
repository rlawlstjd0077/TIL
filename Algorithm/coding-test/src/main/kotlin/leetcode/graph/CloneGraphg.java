package leetcode.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 133. Clone Graph (https://leetcode.com/problems/clone-graph/)
 */
class CloneGrapghg {
    public static void main(String[] args) {

        final Node node1 = new Node(1);
        final Node node2 = new Node(2);
        final Node node3 = new Node(3);
        final Node node4 = new Node(4);

        node1.neighbors.add(node2);
        node1.neighbors.add(node4);

        node2.neighbors.add(node1);
        node2.neighbors.add(node3);

        node3.neighbors.add(node2);
        node3.neighbors.add(node4);

        node4.neighbors.add(node1);
        node4.neighbors.add(node3);

        Node result = new Solution81().cloneGraph(node1);
        System.out.println();
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
}

class Solution81 {
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