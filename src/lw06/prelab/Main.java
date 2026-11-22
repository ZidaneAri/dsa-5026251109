package lw06.prelab;

import java.util.Scanner;

public class Main {

    public static Node insert(Node root, int data) {
        if (root == null) {
            return new Node(data); //1
        }

        if (data < root.data) { //2
            root.left = insert(root.left, data);
        } else if (data > root.data) { //3
            root.right = insert(root.right, data); //4
        }

        return root;
    }

    public static boolean search(Node root, int target) {
        if (root == null) { //5
            return false;
        }

        if (target == root.data) { //6
            return true;
        } else if (target < root.data) { //7
            return search(root.left, target); //8
        } else {
            return search(root.right, target); //9
        }
    }

    public static void preorder(Node root) {
        if (root != null) { //10
            System.out.print(root.data + " ");
            preorder(root.left); //11
            preorder(root.right); //12
        }
    }

    public static void inorder(Node root) {
        if (root != null) { 
            inorder(root.left); //13
            System.out.print(root.data + " ");
            inorder(root.right); //14
        }
    }

    public static void postorder(Node root) {
        if (root != null) {
            postorder(root.left); //15
            postorder(root.right); //16
            System.out.print(root.data + " ");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("treenumbers.txt"));

        Node root = null;

        if (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (!line.isEmpty()) {
                String[] tokens = line.split("\\s+");
                for (String token : tokens) {
                    int val = Integer.parseInt(token);
                    root = insert(root, val);
                }
            }
        }

        int target = 0;
        if (sc.hasNextInt()) {
            target = sc.nextInt();
        }

        System.out.print("Preorder : ");
        preorder(root);
        System.out.println();

        System.out.print("Inorder : ");
        inorder(root);
        System.out.println();

        System.out.print("Postorder : ");
        postorder(root);
        System.out.println();

        boolean found = search(root, target);
        System.out.println("Search " + target + " : " + found);

        sc.close();
    }
}