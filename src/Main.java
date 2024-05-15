public class Main {
    public static void main(String[] args) {
        // Create a binary search tree
        BST<Integer, String> bst = new BST<>();

        // Insert key-value pairs
        bst.put(5, "Value = 5");
        bst.put(3, "Value = 3");
        bst.put(7, "Value = 7");
        bst.put(2, "Value = 2");
        bst.put(4, "Value = 4");
        bst.put(6, "Value = 6");
        bst.put(8, "Value = 8");

        // Print the size of the binary search tree
        System.out.println("Size of BST: " + bst.size());

        // Retrieve values by keys
        System.out.println("Value associated with key 4: " + bst.get(4));
        System.out.println("Value associated with key 10: " + bst.get(10));

        // Delete a key-value pair
        bst.delete(7);

        // Print the size of the binary search tree after deletion
        System.out.println("Size of BST after deletion: " + bst.size());

        // Iterate over the binary search tree and print key-value pairs
        System.out.println("Key-value pairs in BST:");
        for (BST.Entry<Integer, String> entry : bst) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
    }
}