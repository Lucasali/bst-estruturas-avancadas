public class BinarySearchTree<K extends Comparable<K>, V> implements BinarySearchTreeADT<K, V> {
    protected Node<K, V> root;

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public V search(K key) {
        return search(root, key);
    }

    private V search(Node<K, V> node, K key) {
        if (node == null) {
            return null;
        } else if (key.compareTo(node.getKey()) == 0) {
            return node.getValue();
        }
        return search(node.next(key), key);
    }

    @Override
    public void insert(K key, V value) {
        root = insert(root, key, value);
    }

    private Node<K, V> insert(Node<K, V> node, K key, V value) {
        if (node == null) {
            return new Node<K, V>(key, value);
        } else if (key.compareTo(node.getKey()) > 0) {
            node.setRight(insert(node.getRight(), key, value));
        } else if (key.compareTo(node.getKey()) < 0) {
            node.setLeft(insert(node.getLeft(), key, value));
        }

        return node;
    }

    @Override
    public boolean delete(K key) {
        return deleteByCopying(key);
    }

    private boolean deleteByCopying(K key) {
        Node<K, V> parent = null, current = root;

        for (; current != null && key.compareTo(current.getKey()) != 0; parent = current, current = current.next(key))
            ;

        if (current == null) {
            return false;
        } else if (current.getLeft() != null && current.getRight() != null) {
            Node<K, V> tmp = current.getLeft();
            while (tmp.getRight() != null) {
                tmp = tmp.getRight();
            }
            deleteByCopying(tmp.getKey());
            current.setKey(tmp.getKey());
        } else {
            Node<K, V> nextNode = current.getRight() == null ? current.getLeft() : current.getRight();
            if (current.equals(root)) {
                root = nextNode;
            } else if (parent.getLeft().equals(current)) {
                parent.setLeft(nextNode);
            } else {
                parent.setRight(nextNode);
            }
        }

        return true;
    }

    @Override
    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node<K, V> node) {
        if (node != null) {
            System.out.println(node + " ");
            preOrder(node.getLeft());
            preOrder(node.getRight());
        }
    }

    @Override
    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node<K, V> node) {
        if (node != null) {
            inOrder(node.getLeft());
            System.out.println(node + " ");
            inOrder(node.getRight());
        }
    }

    @Override
    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(Node<K, V> node) {
        if (node != null) {
            postOrder(node.getLeft());
            postOrder(node.getRight());
            System.out.println(node + " ");
        }
    }

    @Override
    public void levelOrder() {
        // TODO Auto-generated method stub

    }

    @Override
    public String toString() {
        return root == null ? "[empty]" : printTree(new StringBuffer());
    }

    private String printTree(StringBuffer sb) {
        if (root.getRight() != null) {
            printTree(root.getRight(), true, sb, "");
        }
        sb.append(root + "\n");
        if (root.getLeft() != null) {
            printTree(root.getLeft(), false, sb, "");
        }

        return sb.toString();
    }

    private void printTree(Node<K, V> node, boolean isRight, StringBuffer sb, String indent) {
        if (node.getRight() != null) {
            printTree(node.getRight(), true, sb, indent + (isRight ? "        " : " |      "));
        }
        sb.append(indent + (isRight ? " /" : "\\") + "----- " + node + "\n");
        if (node.getLeft() != null) {
            printTree(node.getLeft(), false, sb, indent + (isRight ? " |      " : "        "));
        }
    }
}