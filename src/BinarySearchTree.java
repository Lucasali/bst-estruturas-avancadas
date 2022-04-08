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
            return new Node<>(key, value);
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
            } else {
                assert parent != null;
                if (parent.getLeft().equals(current)) {
                    parent.setLeft(nextNode);
                } else {
                    parent.setRight(nextNode);
                }
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
    public int countNodes() {
        return countNodes(root);
    }

    private int countNodes(Node<K, V> node){
        int count = 0;
        if (node != null){
            count++;
            count+=countNodes(node.getRight()); //Se o node nao for null, vai ser adicionado no count
            count+=countNodes(node.getLeft());
        }
        return count;
    }

    @Override
    public int countInternalNodes() {
        return countInternalNodes(root)-1;
    }

    private int countInternalNodes(Node<K, V> node){
        int count = 0;
        if (node != null && !node.isLeaf()){
            count++;
            count+=countInternalNodes(node.getRight());
            count+=countInternalNodes(node.getLeft());
        }
        return count;
    }

    @Override
    public int countLeaves() {
        return countLeaves(root);
    }

    private int countLeaves(Node<K, V> node){
        int count = 0;
        if (node != null && node.isLeaf() || node == root){  //o codigo precisa escalar o node root se nao vai dar 0
            count++;                                         //mas ainda nao funciona pois eu tenho certeza que o
            count+=countLeaves(node.getRight());             //count so contou o root e nao o leaf
            count+=countLeaves(node.getLeft());
        }
        return count;
    }

    @Override
    public int degree(K key) {
        //QUANDO ENCONTRAR O NÓ QUE CORRESPONDE À CHAVE, VER QUANTOS FILHOS TEM 0 - 2
        Node<K,V> node = searchNode(root, key);
        if(node == null){return -1;}
        int cont = 0;
        if(node.getLeft() != null){
            cont ++;
        }
        if(node.getRight() != null){
            cont ++;
        }
        return cont;
    }

    private Node<K,V> searchNode(Node<K,V> node, K key) {
        if (node == null) {
            return null;
        } else if (key.equals(node.getKey())) {
            return node;
        }
        return searchNode(node.next(key), key);
    }

    @Override
    public int degreeTree() {
        int degree = 0;
        if(isEmpty()){return -1;}
        return searchDegreeTree(root, degree);
    }

    private int searchDegreeTree(Node<K,V> node, int degreeTree) {
        if(node != null){
            if(node.getLeft() != null && node.getRight() != null){
                return 2;
            }
            if(node.getLeft() != null){
                return searchDegreeTree(node.next(node.getKey()), degreeTree);
            }
            if(node.getRight() != null){
                return searchDegreeTree(node.next(node.getKey()), degreeTree++);
            }
        }
        return 1;
    }

    @Override
    public int height(K key) {
        //QUANDO ENCONTRAR O NÓ QUE CORRESPONDE À CHAVE, VER QUANTOS FILHOS TEM 0 - 2
        if(isEmpty()){return -1;}
        Node<K,V> node = searchNode(root, key);
        if(node == null){return -1;}
        return searchHeight(root, key, 0)-1;
    }

    private int searchHeight(Node<K,V> node, K key, int count) {
        count ++;
        if (key.equals(node.getKey())) {
            return count;
        }
        return searchHeight(node.next(key), key, count);
    }

    @Override
    public int heightTree() {
        if(isEmpty()){return -1;}
        return searchHeightTree(root);
    }

    private int searchHeightTree(Node<K,V> node){
        if(node != null){

            int left = searchHeightTree(node.getLeft());
            int right = searchHeightTree(node.getRight());

            if (right<left){return left+1;}
            return right+1;
        }
        else{return -1;}
    }

    @Override
    public int depth(K key) {
        //QUANDO ENCONTRAR O NÓ QUE CORRESPONDE À CHAVE, VER QUANTOS FILHOS TEM 0 - 2
        if(isEmpty()) {
            return -1;
        }
        Node<K,V> node = searchNode(root, key);
        if(node == null){
            return -1;
        }
        return searchDepth(root, key, 0)-1;
    }

    private int searchDepth(Node<K,V> node, K key, int count) {
        count++;
        if (key.equals(node.getKey())) {
            return count;
        }
        return searchDepth(node.next(key), key, count);
    }

    @Override
    public String ancestors(K key) {
        //QUANDO ENCONTRAR O NÓ QUE CORRESPONDE À CHAVE, VER QUANTOS FILHOS TEM 0 - 2
        if(isEmpty()){return null;}
        Node<K,V> node = searchNode(root, key);
        if(node == null){return null;}
        return searchAncestors(root, key, "");
    }

    private String searchAncestors(Node<K,V> node, K key, String a) {
        a += node.getKey()+" ";
        if (key.equals(node.getKey())) {
            return a;
        }
        return searchAncestors(node.next(key), key, a);
    }

    @Override
    public String descendents(K key) {
        //QUANDO ENCONTRAR O NÓ QUE CORRESPONDE À CHAVE, VER QUANTOS FILHOS TEM 0 - 2
        if(isEmpty()){return null;}
        Node<K,V> node = searchNode(root, key);
        if(node == null){return null;}

        return searchDescendents(root, key, "", false);
    }

    /*private String searchDescendents(Node<K,V> node, K key, String a, boolean b) {

        if (key.equals(node.getKey())) {
            a += node.getKey()+" ";

            if(node.getLeft() != null){
                a+=node.getLeft().getKey()+" ";
            }
            if(node.getRight() != null){
                a+=node.getRight().getKey()+" ";
            }
            return a;
        }
        return searchDescendents(node.next(key), key, a);
    }*/



    /*private String searchDescendents(Node<K,V> node, K key, String descendents, boolean verifyKey) {

        if(node != null){
            if (node != null && key.equals(node.getKey()) ) {
                verifyKey = true;
            }
            if(node != null && verifyKey){
                descendents += node.getKey()+" ";
            }
            //System.out.println(node.getKey());
            return searchDescendents(node.next(key), key, descendents, verifyKey);
            searchDescendents(node.next(key), key, descendents, verifyKey);
        }
        return descendents;
    }*/

    private String searchDescendents(Node<K,V> node, K key, String descendents, boolean verifyKey){
        if(node != null){
            if (key.equals(node.getKey())) {
                verifyKey = true;
            }
            if(verifyKey){
                descendents += node.getKey()+" ";
            }
            searchDescendents(node.getLeft(), key, descendents, verifyKey);
            searchDescendents(node.getRight(), key, descendents, verifyKey);
        }
        return descendents;
    }

    @Override
    public String toString() {
        return root == null ? "[empty]" : printTree(new StringBuffer());
    }

    private String printTree(StringBuffer sb) {
        if (root.getRight() != null) {
            printTree(root.getRight(), true, sb, "");
        }
        sb.append(root).append("\n");
        if (root.getLeft() != null) {
            printTree(root.getLeft(), false, sb, "");
        }

        return sb.toString();
    }

    private void printTree(Node<K, V> node, boolean isRight, StringBuffer sb, String indent) {
        if (node.getRight() != null) {
            printTree(node.getRight(), true, sb, indent + (isRight ? "        " : " |      "));
        }
        sb.append(indent).append(isRight ? " /" : "\\").append("----- ").append(node).append("\n");
        if (node.getLeft() != null) {
            printTree(node.getLeft(), false, sb, indent + (isRight ? " |      " : "        "));
        }
    }
}