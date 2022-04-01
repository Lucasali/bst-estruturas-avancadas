public interface BinarySearchTreeADT<K, V> {
    public void clear();

    public boolean isEmpty();

    public V search(K key);

    public void insert(K key, V value);

    public boolean delete(K key);

    public void preOrder();

    public void inOrder();

    public void postOrder();

    public void levelOrder();
}
