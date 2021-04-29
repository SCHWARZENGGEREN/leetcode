package com.example.leetcode.other.b_tree;


/**
 * @Auther: Rxh
 * @Date: 2020/11/6 11:20
 * @Description: 模拟B_Tree数据结构
 * B_tree是一种平衡树结构
 * https://blog.csdn.net/tfstone/article/details/80951878
 */
public class BTree {

    public static void main(String[] args) {
        BTreeNode<String> bTree = buildBTree();

        System.out.println(searchBTree(23, bTree));
    }


    private static BTreeNode<String> buildBTree() {
        BTreeNode<String> root = new BTreeNode<>();
        buildTreeNode(root, 10, 20);

        root.childRefs[0] = new BTreeNode<>();
        buildTreeNode(root.childRefs[0], 4, 7);

        root.childRefs[0].childRefs[0] = new BTreeNode<>();
        buildTreeNode(root.childRefs[0].childRefs[0], 2, 3);
        root.childRefs[0].childRefs[1] = new BTreeNode<>();
        buildTreeNode(root.childRefs[0].childRefs[1], 5, 6);
        root.childRefs[0].childRefs[2] = new BTreeNode<>();
        buildTreeNode(root.childRefs[0].childRefs[2], 8, 9);


        root.childRefs[1] = new BTreeNode<>();
        buildTreeNode(root.childRefs[1], 14, 17);

        root.childRefs[1].childRefs[0] = new BTreeNode<>();
        buildTreeNode(root.childRefs[1].childRefs[0], 12, 13);
        root.childRefs[1].childRefs[1] = new BTreeNode<>();
        buildTreeNode(root.childRefs[1].childRefs[1], 15, 16);
        root.childRefs[1].childRefs[2] = new BTreeNode<>();
        buildTreeNode(root.childRefs[1].childRefs[2], 18, 19);


        root.childRefs[2] = new BTreeNode<>();
        buildTreeNode(root.childRefs[2], 24, 27);

        root.childRefs[2].childRefs[0] = new BTreeNode<>();
        buildTreeNode(root.childRefs[2].childRefs[0], 22, 23);
        root.childRefs[2].childRefs[1] = new BTreeNode<>();
        buildTreeNode(root.childRefs[2].childRefs[1], 25, 26);
        root.childRefs[2].childRefs[2] = new BTreeNode<>();
        buildTreeNode(root.childRefs[2].childRefs[2], 28, 29);

        return root;
    }

    private static void buildTreeNode(BTreeNode<String> node, int... nums) {
        node.datas = new Node[nums.length];
        for (int i = 0; i < nums.length; i++) {
            node.datas[i] = buildNode(nums[i]);
        }
        node.childRefs = new BTreeNode[nums.length + 1];
    }

    private static Node<String> buildNode(int num) {
        Node<String> node = new Node<>();
        node.node = num;
        node.data = num + "";
        return node;
    }

    /**
     * 先二分法找出key范围对应引用,拿到引用依次向下搜索
     * 如果key>node,则向右侧分支搜索;如果key<node,则向左侧分支搜索;
     *
     * @param key
     * @param node
     * @return
     */
    public static <T> T searchBTree(int key, BTreeNode<T> node) {
        if (node == null) return null;//没有匹配到
        int destIdx = binarySearchNode(key, node.datas, 0, node.datas.length - 1);
        int destKey = node.datas[destIdx].node;
        if (destKey == key) {//TODO 边界问题
            return node.datas[destIdx].data;
        } else if (destKey > key) {
            return searchBTree(key, node.childRefs[destIdx]);
        } else {
            return searchBTree(key, node.childRefs[destIdx + 1]);
        }
    }

    /**
     * 找出匹配key的node或者相近的node idx
     *
     * @param key
     * @param keys
     * @return
     */
    public static int binarySearchNode(int key, Node[] keys, int start, int end) {
        //如果key<=start or start在数组头或者尾 or key介于start和start+1之间,返回
        if (start == end)
            return start;

        int mid = (start + end) / 2;
        if (keys[mid].node > key) {
            end = mid;
        } else if (keys[mid].node < key) {
            start = mid + 1;//由于int除法默认偏向小的方向,因此为避免start+1=end时无限循环,将角标右移
        } else {
            return mid;
        }

        return binarySearchNode(key, keys, start, end);
    }


    private static class BTreeNode<T> {
        //每个非叶子节点由n-1个key和n个指针组成,且每个key左右各有一个指针
        //key为从小打到
        Node<T>[] datas;//n-1个key以及数据
        BTreeNode<T>[] childRefs;//n个引用
    }

    private static class Node<T> {
        int node;//节点
        T data;//数据引用
    }
}
