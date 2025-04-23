import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<E> implements Iterable<E> {

    @Override
    public Iterator<E> iterator() {
        return new DoublyLinkedListIterator();
    }

    private class DoublyLinkedListIterator implements Iterator<E> {
        private Node<E> current;

        public DoublyLinkedListIterator() {
            current = head;
        }
        @Override
        public boolean hasNext() {
            return (current != null);
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            E result = current.data;
            current = current.next;
            return result;
        }
    }

    private static class Node<E> {
        private E data;
        private Node<E> previous; // pointer to previous node
        private Node<E> next;     // pointer to next node

        public Node(E data, Node<E> previous, Node<E> next) {
            this.data = data;
            this.previous = previous;
            this.next = next;
        }

        public Node(E data) {
            this(data, null, null);
        }
    }

    private Node<E> head;   // pointer to the first node of this list
    private Node<E> tail;   // pointer to the last node of this list
    private int size;       // the number of nodes currently in this list

    public DoublyLinkedList() {
        tail = head = null;
        size = 0;
    }

    public void addFirst(E element) {
        Node<E> newNode = new Node<>(element, null, head);

        if (head != null) {
            head.previous = newNode;
        }
        head = newNode;
        size++;

        if (tail == null) {
            tail = head;
        }
    }


    public void addLast(E element) {
        if (isEmpty()) addFirst(element);

        else {
            Node<E> newNode = new Node<>(element, tail, null);

            if (tail != null) {
                tail.next = newNode;
            }
            tail = newNode;
            size++;

            if (head == null) {
                head = tail;
            }
        }
    }


    public E getFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        else return head.data;
    }

    public E getLast() {
        if (isEmpty()) throw new NoSuchElementException();
        else return tail.data;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> current = head;

        while (current != null) {
            sb.append(current.data);

            if (current != tail) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }

    public String toReverseString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> current = tail;

        while (current != null) {
            sb.append(current.data);

            if (current != head) {
                sb.append(", ");
            }
            current = current.previous;
        }
        sb.append("]");
        return sb.toString();
    }

    public E removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        else {
            E result = head.data;
            head = head.next;
            size--;

            if (head == null) {
                tail = null;
            } else {
                head.previous = null;
            }

            return result;
        }
    }


    public E removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        else {
            E result = tail.data;
            tail = tail.previous;
            size--;

            if (isEmpty()) {
                head = null;
            } else {
                tail.next = null;
            }

            return result;
        }
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }


    public boolean contains(Object o) {
        Node<E> current = head;
        for (int i = 0; i < size; i++) {
            if (current.data.equals(o)) return true;
            else current = current.next;
        }
        return false;
    }

    public boolean add(E e) {
        addLast(e);
        return true;
    }

    public boolean remove(Object o) {
        Node<E> current = head;

        while (current != null) {
            if ((o == null && current.data == null) || (o != null && o.equals(current.data))) {
                // Node is at the head
                if (current == head) {
                    head = head.next;
                    if (head != null) {
                        head.previous = null;
                    } else {
                        tail = null; // List is now empty
                    }
                }
                // Node is at the tail
                else if (current == tail) {
                    tail = tail.previous;
                    if (tail != null) {
                        tail.next = null;
                    } else {
                        head = null; // List is now empty
                    }
                }
                // Node is in the middle
                else {
                    current.previous.next = current.next;
                    current.next.previous = current.previous;
                }

                size--;
                return true;
            }

            current = current.next;
        }

        return false;
    }

    public int indexOf(Object o) {
        if (isEmpty()) return -1;

        Node<E> current = head;
        for (int i = 0; i < size; i++) {
            if (current.data.equals(o)) return i;
            else current = current.next;
        }
        return -1;
    }

    public int lastIndexOf(Object o) {
        if (isEmpty()) return -1;

        Node<E> current = tail;
        for (int i = size - 1; i >= 0; i--) {
            if (current.data.equals(o)) return i;
            else current = current.previous;
        }
        return -1;
    }

    public E get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        Node<E> current;
        if (size / 2 > index) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.previous;
            }
        }
        return current.data;

    }

    public E set(int index, E element) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        Node<E> current;

        if (size / 2 > index) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        }
        else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.previous;
            }
        }
        if (isEmpty()) return element;
        E oldValue = current.data;
        current.data = element;
        return oldValue;
    }

    public void add(int index, E element) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();

        if (index == 0) {
            addFirst(element);
        } else if (index == size) {
            addLast(element);
        } else {
            Node<E> current = head;
            if (index < size / 2) { // if index is in the first half of the list
                for (int i = 0; i < index - 1; i++) current = current.next;

            } else { // if index is in the second half of the list
                current = tail;
                for (int i = size - 1; i > index; i--) current = current.previous;

            }
            Node<E> newNode = new Node<>(element, current.previous, current);

            if (current.previous != null) {
                current.previous.next = newNode;
            }

            current.previous = newNode;
            newNode.next = current;
            size++;

        }

    }


    public E remove(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        E element;
        Node<E> current = head;
        if (index == 0) {
            element = head.data;
            removeFirst();
            return element;

        } else if (index == size-1) {
            element = tail.data;
            removeLast();
            return element;

        } else {
            if (size / 2 > index) {
                for (int i = 0; i < index; i++) current = current.next;

            } else {
                current = tail;
                for (int i = size - 1; i > index; i--) current=current.previous;
            }
        }

        element = current.data;
        current.previous.next = current.next;
        current.next.previous = current.previous;

        size--;
        return element;
    }
}

