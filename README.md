# Algorithms and Data Structures in Java

A collection of algorithms and data structure utility classes, created as self-assigned projects while studing Princeton's online courses Algorithms I, Algorithms II, and Analysis of Algorithms.

## Contents

* **UnionFinder**, a class providing objects for fast `union` and `find` methods using path compression.
* **BinarySearcher**, a class providing a static `search` method that searches a **sorted** array of comparables using binary search.
* **LinkedList** and **DoublyLinkedList** classes providing low-level dynamic collections with methods to add/remove from the start/end of the list, and iterators.
* **Queue** and **Stack** classes, built on top of **LinkedList** with the usual FIFO and LIFO semantics (respectively).
* **DynamicArray** which allows regular array access and dynamically grows and shrinks as you add items at the end. Grows when full and shrinks when 1/4 full (to avoid thrashing).
* **SimpleSorts**, a collection of static `sort` methods that operate on generic arrays of Comparable objects, or with Comparators.
* **MergeSort** and **Quicksort**, static `sort` methods that operate on a generic array of Comparable objects, or with a Comparator.

## Testing

Run `make test` from the root directory to build the project, build the tests, and run the tests with jUnit.

