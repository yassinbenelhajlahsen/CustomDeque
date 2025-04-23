# DoublyLinkedList Deque (Java)

This project implements a **double-ended queue (deque)** data structure in Java using a **doubly linked list**. It supports constant-time addition and removal of elements from both ends, as well as efficient traversal using a custom iterator.

## Features

- Constant-time `addFirst`, `addLast`, `removeFirst`, and `removeLast`
- Indexed access: `get(index)`, `set(index, element)`, `add(index, element)`, `remove(index)`
- Implements `Iterable<E>` with a custom iterator
- `toString()` and `toReverseString()` for readable traversal in both directions
- Utility methods: `contains`, `indexOf`, `lastIndexOf`, `clear`, `isEmpty`, `size`

---

## Getting Started

### Requirements

- Java 8 or higher
- A Java IDE (e.g., IntelliJ IDEA, Eclipse) or build tools (e.g., Maven, Gradle)

### Compilation

You can compile the file directly using:

```bash
javac Test.java
```

## Usage Example
```Java
DoublyLinkedList<String> deque = new DoublyLinkedList<>();

deque.addFirst("world");
deque.addFirst("hello");
deque.addLast("!");

System.out.println(deque); // Output: [hello, world, !]

deque.removeFirst();       // Removes "hello"
deque.removeLast();        // Removes "!"

System.out.println(deque); // Output: [world]

System.out.println(deque.getFirst()); // Output: world
```
# Class Overview

## `DoublyLinkedList<E>` (implements `Iterable<E>`)

### Inner Classes
- **`Node<E>`** — Represents a node with `data`, `next`, and `previous` references.
- **`DoublyLinkedListIterator`** — Custom iterator that supports `hasNext()` and `next()`.

---

## ⏱️ Time Complexity

| Operation                        | Time Complexity |
|----------------------------------|-----------------|
| `addFirst` / `addLast`           | O(1)            |
| `removeFirst` / `removeLast`     | O(1)            |
| `get(index)` / `set(index, e)`   | O(n)            |
| `contains` / `indexOf` / `lastIndexOf` | O(n)      |
| `add(index, element)` / `remove(index)` | O(n)     |

---

## Author

**Yassin Benelhajlahsen**  
Brooklyn College, Computer Science Major  
📧 Email: yassinbenelhajlahsen@gmail.com 

