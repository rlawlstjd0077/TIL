package leetcode.tree;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeLevelOrderTraversalTest {
    @Test
    void test() {
        BinaryTreeLevelOrderTraversal.TreeNode node15 = new BinaryTreeLevelOrderTraversal.TreeNode(15);
        BinaryTreeLevelOrderTraversal.TreeNode node7 = new BinaryTreeLevelOrderTraversal.TreeNode(7);
        BinaryTreeLevelOrderTraversal.TreeNode node9 = new BinaryTreeLevelOrderTraversal.TreeNode(9);
        BinaryTreeLevelOrderTraversal.TreeNode node20 = new BinaryTreeLevelOrderTraversal.TreeNode(20, node15, node7);
        BinaryTreeLevelOrderTraversal.TreeNode root = new BinaryTreeLevelOrderTraversal.TreeNode(3, node9, node20);

        assertEquals(new BinaryTreeLevelOrderTraversal().levelOrder(root), new ArrayList<List<Integer>>() {{
            add(List.of(3));
            add(Arrays.asList(9, 20));
            add(Arrays.asList(15, 7));
        }});
    }

    @Test
    void test2() {
        BinaryTreeLevelOrderTraversal.TreeNode root = new BinaryTreeLevelOrderTraversal.TreeNode(1);

        assertEquals(new BinaryTreeLevelOrderTraversal().levelOrder(root), new ArrayList<List<Integer>>() {{
            add(List.of(1));
        }});
    }
}