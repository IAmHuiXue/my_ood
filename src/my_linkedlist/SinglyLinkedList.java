package my_linkedlist;

import org.w3c.dom.Node;

import java.util.NoSuchElementException;

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */

public class SinglyLinkedList {
    // Linked List does not have to have an instructor like ArrayList
    // as we do not have to create an initial container like array

    static class Node {
        private int value;
        private Node next;

        public Node(int element) {
            value = element;
        }
    }

    private Node head;
    private Node tail;
    private int size;

//    public SinglyLinkedList() {
//        size = 0;
//    }

    public void addFirst(int element) {
        Node newNode = new Node(element);
        if (size == 0) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        size++;
    } // O(1)

    public void addLast(int element) {
        Node newNode = new Node(element);
        if (size == 0) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    } // O(1)

    public void deleteFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        if (size == 1) { // size == 1 is also a special case
            head = tail = null;
        } else {
            Node node = head;
            head = head.next;
            node.next = null;
        }
        size--;
    } // O(1)

    public void deleteLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        if (size == 1) {
            head = tail = null;
        } else {
            Node node = head;
            while (node.next != tail) {
                node = node.next;
            }
            node.next = null; // for better GC
            tail = node;
        }
        size--;
    } // O(n)

    public boolean contains(int element) {
        return indexOf(element) != -1;
    }

    public int indexOf(int element) {
        // if not found, return -1
        int index = 0;
        Node node = head;
        while (node != null) {
            if (node.value == element) {
                return index;
            }
            node = node.next;
            index++;
        }
        return -1;
    } // O(n)

    /**
     * Get the value of the index-th node in the linked list.
     * If the index is invalid, return -1.
     */
    public int get(int index) {
        if (index < 0 || index >= size) {
            return -1;
        }
        Node node = head;
        int count = 0;
        while (count < index) {
            node = node.next;
            count++;
        }
        return node.value;
    }

    /**
     * Add a node of value val before the index-th node in the linked list.
     * If index equals to the length of linked list, the node will be appended to the end of linked list.
     * If index is greater than the length, the node will not be inserted.
     */
    public void addAtIndex(int index, int val) {
        if (index < 0 || index > size) {
            return;
        }
        if (index == 0) {
            addFirst(val);
            return;
        }
        if (index == size) {
            addLast(val);
            return;
        }
        //  0 < index < size
        Node newNode = new Node(val);
        int count = 0;
        Node node = head;
        while (count++ < index - 1) {
            node = node.next;
        }
        newNode.next = node.next;
        node.next = newNode;
        size++;
    }

    /**
     * Delete the index-th node in the linked list,
     * if the index is valid.
     */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }
        if (index == 0) {
            deleteFirst();
            return;
        }
        if (index == size - 1) {
            deleteLast();
            return;
        }
        // 0 < index < size - 1
        int count = 0;
        Node node = head;
        while (count++ < index - 1) {
            node = node.next;
        }
        node.next = node.next.next;
        size--;
    }

    private boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public int[] toArray() {
        if (size == 0) {
            return null;
        }
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = head.value;
            head = head.next;
        }
        return array;
    } // O(n)
}
