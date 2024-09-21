package ru.job4j.extra;

public class TreeNode {
    Worker worker;
    TreeNode left;
    TreeNode right;

    public TreeNode(Worker worker) {
        this.worker = worker;
        this.left = null;
        this.right = null;
    }
}
