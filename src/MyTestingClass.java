import java.util.Random;


/**
 * A simple class representing a key for testing purposes.
 * It contains an integer data field and provides a custom
 * hashCode() method for ensuring a uniform distribution
 * of keys across a hash table.
 */
public class MyTestingClass {
    private int data; // Integer data field for the key

    /**
     * Constructs a MyTestingClass object with the given data.
     * @param data The integer data for the key.
     */
    public MyTestingClass(int data) {
        this.data = data;
    }

    /**
     * Retrieves the integer data associated with the key.
     * @return The integer data of the key.
     */
    public int getData() {
        return data;
    }

    /**
     * Custom hashCode() method implementation to ensure a
     * uniform distribution of keys across the hash table.
     * @return The hash code computed based on the data modulo 11.
     */
    @Override
    public int hashCode() {
        return data % 11; // Modulo 11 to distribute keys across 11 buckets
    }

    /**
     * Main method for testing the MyHashTable class.
     * It creates a hash table, adds random elements to it,
     * and prints the number of elements in each bucket.
     * @param args The command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Create a hash table for testing
        MyHashTable<MyTestingClass, Integer> table = new MyHashTable<>();

        // Adding random 10000 elements to the hash table
        Random rand = new Random();
        for (int i = 0; i < 10000; i++) {
            int randomData = rand.nextInt(100); // Generate random data
            MyTestingClass key = new MyTestingClass(randomData);
            table.put(key, randomData);
        }

        // Printing the number of elements in each bucket of the hash table
        for (int i = 0; i < table.chainArray.length; i++) {
            int count = 0;
            MyHashTable<MyTestingClass, Integer>.HashNode<MyTestingClass, Integer> node = table.chainArray[i];

            // Counting the number of elements in the current bucket
            while (node != null) {
                count++;
                node = node.next;
            }
            System.out.println("Slot " + i + ": " + count + " elements");
        }
    }
}