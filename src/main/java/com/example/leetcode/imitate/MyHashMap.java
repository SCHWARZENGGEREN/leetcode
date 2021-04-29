package com.example.leetcode.imitate;


/**
 * @Auther: Rxh
 * @Date: 2019/12/10 10:39
 * @Description: 模拟hashmap
 */
public class MyHashMap<K, V> {

    public static void main(String[] args) {
        MyHashMap<Integer, String> myHashMap = new MyHashMap<>();

        for (Integer i = 1; i <= 16; i++) {
            myHashMap.put(i, i.toString());
        }
        System.out.println(myHashMap.get(3));
    }

    private Node<K, V>[] elements;
    private int capacity;
    private int size = 0;

    public MyHashMap() {
        this(16);
    }

    public MyHashMap(int capacity) {
        this.capacity = capacity;
        elements = (Node<K, V>[]) new Object[capacity];
    }

    /**
     * 每次插入前,先计算下key的hash得到一个idx值,然后在map中寻找这个idx对应的节点,
     * 然后如果节点链上没有重复key就把新的及节点挂上去,否则就替换value
     *
     * @param key
     * @param val
     */
    public void put(K key, V val) {
        if (key != null) {
            int idx = getIdx(key);
            Node<K, V> node = elements[idx];
            if (node == null) {
                node = new Node<>();
                node.key = key;
                node.val = val;
                elements[idx] = node;
            } else {
                while (node.next != null) {
                    node = node.next;
                }
                node.next = new Node<>();
                node.next.key = key;
                node.next.val = val;
            }
            size++;
        }
    }

    public V get(K key) {
        if (key != null) {
            int idx = getIdx(key);
            Node<K, V> node = elements[idx];
            if (node != null) {
                while (node != null) {
                    if (node.key.equals(key)) {
                        return node.val;
                    }
                    node = node.next;
                }
            }
        }
        return null;
    }

    private int getIdx(K key) {
        return (capacity - 1) & key.hashCode();
    }

    public int size() {
        return size;
    }

    private static class Node<K, V> {
        public Node<K, V> next;
        public K key;
        public V val;
    }
}
