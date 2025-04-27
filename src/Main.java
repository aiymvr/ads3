public class Main {
    public static void main(String[] args) {
        // ============================
        // MyHashTable Usage Section
        // ============================

        // Create a hash table with 100 buckets
        MyHashTable<MyTestingClass, Student> table = new MyHashTable<>(100);

        // Add 10,000 random entries to the hash table
        for (int i = 0; i < 10000; i++) {
            int f1 = (int)(Math.random() * 10000); // generate first feature
            int f2 = (int)(Math.random() * 10000); // generate second feature

            // Create key and value objects
            MyTestingClass key = new MyTestingClass(f1, f2);
            Student value = new Student(i, "Student" + i);

            // Put the key-value pair into the hash table
            table.put(key, value);
        }


        // Get the number of elements in each bucket of the hash table
        int[] bucketSizes = table.getBucketSizes();

        // Print out how many elements are in each bucket
        System.out.println("HashTable Bucket Distribution:");
        for (int i = 0; i < bucketSizes.length; i++) {
            System.out.println("Bucket " + i + ": " + bucketSizes[i] + " elements");
        }

        System.out.println("\n===============================");
        System.out.println("Demonstrating BST Traversal");
        System.out.println("===============================\n");

        // Create a BST instance and add key-value pairs
        BST<Integer, String> tree = new BST<>();
        tree.put(50, "Fifty");
        tree.put(30, "Thirty");
        tree.put(70, "Seventy");
        tree.put(20, "Twenty");
        tree.put(40, "Forty");
        tree.put(60, "Sixty");
        tree.put(80, "Eighty");

        // Iterate through the BST using in-order traversal
        System.out.println("In-order Traversal of BST:");
        for (var elem : tree) {
            System.out.println("key is " + elem.getKey() + " and value is " + elem.getValue());
        }

        // Print total size of BST
        System.out.println("\nTotal size of BST: " + tree.size());

    }
}
