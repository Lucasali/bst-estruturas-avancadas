public class Node<K extends Comparable<K>, V> {
    private K key;
    private V value;
    private Node<K, V> left, right;
    private int height;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public Node<K, V> next(K other) {
        return other.compareTo(key) < 0 ? left : right;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

    public Node<K, V> getLeft() {
        return left;
    }

    public void setLeft(Node<K, V> left) {
        this.left = left;
    }

    public Node<K, V> getRight() {
        return right;
    }

    public void setRight(Node<K, V> right) {
        this.right = right;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "" + key;
    }

}