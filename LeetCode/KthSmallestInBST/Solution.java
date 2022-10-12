package LeetCode.KthSmallestInBST;
// 리트코드 230 번
import java.util.ArrayList;

public class Solution {
    static ArrayList<Integer> elements;
    public int kthSmallest(TreeNode root, int k) {
        elements=new ArrayList<>();
        inorder(root);
        return elements.get(k-1);
    }
    public static void inorder(TreeNode node) {
        if(node != null) {
            inorder(node.left);
            elements.add(node.val);
            inorder(node.right);
        }
    }
}
class TreeNode{
    int val;
    TreeNode left,right;

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
