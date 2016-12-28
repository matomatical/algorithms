# Algorithms and Data Structures in Java

A collection of algorithms and data structure utility classes, created as self-assigned projects while studing Princeton's online courses Algorithms I, Algorithms II, and Analysis of Algorithms.

## Contents

* **UnionFinder**, a class providing objects for fast `union` and `find` methods using path compression.
* **BinarySearcher**, a class providing a static `search` method that searches a **sorted** array of comparables using binary search.
* **LinkedList** and **DoublyLinkedList** classes providing low-level dynamic collections with methods to add/remove from the start/end of the list, and iterators.
* **Queue** and **Stack** classes, built on top of **LinkedList** with the usual FIFO and LIFO semantics (respectively).
* **Sort**, a collection of static helper methods for sorting algorithms and applications, such as swapping, comparing and checking if an array is sorted.
* A collection of classes with static `sort` methods that operate on a generic arrays of Comparable objects, or with Comparators. Including:
    - **BubbleSort**, using a strategy with a slight improvement that performs a smaller number of comparisons compared to the usual strategy for some inputs.
    - **InsertionSort**, using the usual strategy.
    - **SelectionSort**, using the usual strategy.
    - **MergeSort**, using the usual strategy.
    - **Quicksort**, using random pivot selection and a three-way partitioning scheme.
    - **HeapSort**, using an in-place max heap temporarily built on top of the array.
* **DynamicArray**, allowing regular array access and dynamically growing and shrinking as you add items at the end. Grows when full and shrinks when 1/4 full (to half size, to avoid thrashing).
* **PriorityQueue** class, offering a value-based ordered collection of comparable items, using a max heap built on top of a **DynamicArray**.
* **BinarySearchTree** class, offering simple key-value association storage with `insert`, `search`, `contains`, `delete`, and `nullsearch*` methods (* `nullsearch` returns null if a key is not found, rather than throwing an exception).
* **AVLTree** class, offering the same methods as the **BinarySearchTree**, but with guaranteed *O(log(n))* runtime for each operation (where *n* is the number of elements in the tree) by maintaining a balanced binary search tree.

## Testing

Run `make test` from the root directory to build the project, build the tests, and run the tests with jUnit. With this target, test output will be filtered not to show extensive stack traces. To show full output, use `make test-v` (v for verbose).