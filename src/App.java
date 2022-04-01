public class App {
    public static void main(String[] args) throws Exception {
        BinarySearchTree<Integer, Integer> tree = new BinarySearchTree<Integer, Integer>();

        tree.insert(1, 10);
        tree.insert(2, null);
        tree.insert(3, 5);
        tree.insert(4, 2);
        System.out.println(tree.toString());
    }
}
