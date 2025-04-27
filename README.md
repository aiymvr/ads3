#  HashTable and BST in Java(choose "master" branch to see)

##  Description

This project demonstrates the implementation of a custom `MyHashTable` using **separate chaining** and a custom `BST` (Binary Search Tree) with support for:

- Insertion, retrieval, and deletion
- In-order traversal using an `Iterator`
- Access to both key and value in iteration
- Size tracking of the BST

---

##  Data Structures

### 1. MyHashTable<K, V>
- Generic hash table using **separate chaining** with `LinkedList`-based buckets.
- Supports `put(K, V)`, `get(K)`, `remove(K)`, and bucket size diagnostics.
- Custom `MyTestingClass` used as a key with a manually tuned `hashCode()` for **uniform distribution**.

### 2. MyTestingClass
- Custom key class for hashing with two integer fields.
- Implements `equals()` and a balanced `hashCode()` for optimal distribution.

### 3. BST<K extends Comparable<K>, V>
- Binary Search Tree implementation.
- Supports `put()`, `get()`, `delete()`, `size()`, and `iterator()`.
- Iterator supports **in-order traversal** and returns key-value pairs via `getKey()` and `getValue()`.

---

## 💡 Features

- Add 10,000 random key-value pairs to the hash table  
-  Print bucket sizes for distribution analysis  
-  BST with full in-order traversal   

---
