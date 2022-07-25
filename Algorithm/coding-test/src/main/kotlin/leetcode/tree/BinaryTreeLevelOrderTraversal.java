package leetcode.tree;

import javax.swing.tree.TreeNode;
import java.util.*;
import java.util.stream.Collectors;

public class BinaryTreeLevelOrderTraversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }

        Map<Integer, List<Integer>> result = new LinkedHashMap<>();
        retrieve(root, 0, result);
        return result.values()
                .stream().collect(Collectors.toList());
    }

    private void retrieve(TreeNode node, int level, Map<Integer, List<Integer>> result) {
        if (node != null) {
            result.computeIfAbsent(level, e -> new ArrayList<>()).add(node.val);
            retrieve(node.left, level + 1, result);
            retrieve(node.right, level + 1, result);
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}


