public class App {
    public static void main(String[] args) {
        BinarySearchTree<Integer, Integer> tree = new BinarySearchTree<>();
        tree.insert(4, 0);
        tree.insert(3, 0);
        tree.insert(2, 0);
        tree.insert(1, 0);
        System.out.println(tree.degreeTree());
        System.out.println(tree);
    }
}
