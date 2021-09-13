package my_linkedlist;

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */

public class DoublyLinkedList {
    static class ListNode {
        int value;
        ListNode next;
        ListNode prev;

        public ListNode(int value) {
            this.value = value;
        }
    }

    ListNode head;
    ListNode tail;
    int size;

    /** Initialize your data structure here. */
    public DoublyLinkedList() {
        head = tail = null;
        size = 0;
    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if (index < 0 || index >= size) {
            return -1;
        }
        ListNode pointer = head;
        for (int i = 0; i < index; i++) {
            pointer = pointer.next;
        }
        return pointer.value;
    }

    /** Add a node of value val before the first element of the linked list. After the insertion,
     the new node will be the first node of the linked list. */
    public void addAtHead(int value) {
        ListNode node = new ListNode(value);
        if (size == 0) {
            head = tail = node;
        } else {
            node.next = head;
            head.prev = node;
            head = node;
        }
        size++;
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int value) {
        ListNode node = new ListNode(value);
        if (size == 0) {
            head = tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        size++;
    }

    /** Add a node of value val before the index-th node in the linked list.
     If index equals to the length of linked list, the node will be appended to the end of linked list.
     If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int value) {
        if (index < 0 || index > size) {
            return;
        }
        if (index == 0) {
            addAtHead(value);
            return;
        }
        if (index == size) {
            addAtTail(value);
            return;
        }
        // 0 < index < size
        ListNode pointer = head;
        int count = 0;
        while (count++ < index) {
            pointer = pointer.next;
        }
        ListNode node = new ListNode(value);
        // handling index == 0 && index == size simplifies the following cases
        // without checking NPE

        node.next = pointer;
        node.prev = pointer.prev;
//        if (pointer.prev != null) {
            pointer.prev.next = node;
//        }
        pointer.prev = node;
        size++;
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }
        if (index == 0) {
            deleteAtHead();
            return;
        }
        if (index == size - 1) {
            deleteAtTail();
            return;
        }
        // 0 < index < size - 1
        ListNode node = head;
        int count = 0;
        while (count++ < index) {
            node = node.next;
        }
        // handling index == 0 && index == size - 1 simplifies the following cases
        // without checking NPE

//        if (node.prev != null) {
            node.prev.next = node.next;
//        }
//        if (node.next != null) {
            node.next.prev = node.prev;
//        }
        node.next = node.prev = null;
        size--;
    }

    private void deleteAtHead() {
        if (head == tail) {
            head = tail = null;
        } else {
            ListNode node = head;
            head = head.next;
            head.prev = null;
            node.next = null;
        }
        size--;
    }

    private void deleteAtTail() {
        if (head == tail) {
            head = tail = null;
        } else {
            ListNode node = tail;
            tail = tail.prev;
            tail.next = null;
            node.prev = null;
        }
        size--;
    }
}
