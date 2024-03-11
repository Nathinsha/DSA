class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
public class MaxBinaryTree {
    public static void main(String[] args) {
        int[] inputArray = {3, 2, 1, 6, 0, 5};
        MaxBinaryTree maxBinaryTree = new MaxBinaryTree();
        TreeNode result = maxBinaryTree.constructMaximumBinaryTree(inputArray);
        printInorder(result);
    }

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return constructBinarymax(nums, 0, nums.length - 1);
    }

    public TreeNode constructBinarymax(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        int position = maxroot(nums, start, end);
        TreeNode root = new TreeNode(nums[position]);
        root.left = constructBinarymax(nums, start, position - 1);
        root.right = constructBinarymax(nums, position + 1, end);
        return root;
    }

    public int maxroot(int[] arr, int start, int end) {
        int max = Integer.MIN_VALUE, maxidx = 0;
        for (int i = start; i <= end; i++) {
            if (max < arr[i]) {
                max = arr[i];
                maxidx = i;
            }
        }
        return maxidx;
    }

    private static void printInorder(TreeNode node) {
        if (node != null) {
            printInorder(node.left);
            System.out.print(node.val + " ");
            printInorder(node.right);
        }
    }
}


/*Step 1:

constructMaximumBinaryTree(nums)
         |
constructBinarymax(nums, 0, 5)
Step 2:


constructBinarymax(nums, 0, 5)
         |
[3] -- [2] -- [1] -- [6] -- [0] -- [5]
The maximum element in the range [0, 5] is at index 3 (value 6), creating the root node.
Recursive call on the left subtree: constructBinarymax(nums, 0, 2)
Recursive call on the right subtree: constructBinarymax(nums, 4, 5)
Step 3 (Left Subtree):


constructBinarymax(nums, 0, 2)
         |
[3] -- [2] -- [1]
The maximum element in the range [0, 2] is at index 0 (value 3), creating a node.
Recursive call on the right subtree: constructBinarymax(nums, 1, 2)
Step 4 (Left Subtree - Right Child):

constructBinarymax(nums, 1, 2)
         |
              [2]
The maximum element in the range [1, 2] is at index 1 (value 2), creating a node.
Step 5 (Right Subtree):

constructBinarymax(nums, 4, 5)
         |
                    [5]
The maximum element in the range [4, 5] is at index 5 (value 5), creating a node.
Step 6 (Right Subtree - Right Child):


constructBinarymax(nums, 5, 5)
         |
                        [0]
A single-node subtree is created with the element at index 5 (value 0).
The final Maximum Binary Tree:


           [6]
          /   \
       [3]    [5]
         \      /
        [2]   [0]
The tree is constructed step by step through recursive calls, with each node representing the maximum element in its respective range.*/