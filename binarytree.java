
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

    public class binarytree {
        static class Node {
            int data;
            Node left, right;

            public Node(int key) {
                this.data = key;
            }
        }

        static void inorder(Node root) {
            if (root == null)
                return;

            inorder(root.left);
            System.out.print(root.data + " ");
            inorder(root.right);
        }

        static Node deletion(Node root, int key) {
            if (root == null)
                return null;

            Map<Node, Node> parentMap = new HashMap<>();
            Node keyNode = null, temp = null;

            Queue<Node> q = new LinkedList<>();
            q.add(root);

            // Do level order traversal to find deepest
            // node(temp), node to be deleted (key_node)
            // and parent of deepest node(last)
            while (!q.isEmpty()) {
                temp = q.remove();

                if (temp.data == key)
                    keyNode = temp;

                if (temp.left != null) {
                    parentMap.put(temp.left, temp); // storing the parent-child relationship
                    q.add(temp.left);
                }

                if (temp.right != null) {
                    parentMap.put(temp.right, temp); // storing the parent-child relationship
                    q.add(temp.right);
                }
            }

            if (keyNode != null) {
                keyNode.data = temp.data; // replacing key_node's data with deepest node's data
                Node parent = parentMap.get(temp);
                if (parent != null) {
                    if (parent.left == temp) {
                        parent.left = null;
                    } else {
                        parent.right = null;
                    }
                }
            }

            return root;
        }

        public static void main(String args[]) {
            Node root = new Node(9);
            root.left = new Node(2);
            root.left.left = new Node(4);
            root.left.right = new Node(7);
            root.right = new Node(8);

            System.out.print("Inorder traversal before deletion: ");
            inorder(root);
            System.out.println();

            int key = 7;
            root = deletion(root, key);

            System.out.print("Inorder traversal after deletion: ");
            inorder(root);
            System.out.println();
        }
    }

