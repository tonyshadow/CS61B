public class LinkedListDeque<Item> {
    private class Node {
        public Item item;
        public Node prev;
        public Node next;

        public Node(Node p, Item i, Node n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    /** Constructor with item.
     public LinkedListDeque(Item i) {
        sentinel = new Node(null, null, null);
        sentinel.next = new Node(sentinel, i, sentinel);
        sentinel.front = sentinel.next;
        size = 0;
     }
     */

    // Adds an item to the front of the Deque.
    public void addFirst(Item item) {
        sentinel.next.prev = new Node(sentinel, item, sentinel.next);
        sentinel.next = sentinel.next.prev;

        // Alternative method:
        //sentinel.next = new Node(sentinel, item, sentinel.next);
        //sentinel.next.next.front = sentinel.next;

        size += 1;
    }

    // Adds an item to the back of the Deque.
    public void addLast(Item item) {
        sentinel.prev.next = new Node(sentinel.prev, item, sentinel);
        sentinel.prev = sentinel.prev.next;

        size += 1;
    }

    // Returns true if deque is empty, false otherwise.
    public boolean isEmpty() {
        return size == 0;
    }

    // Returns the number of items in the Deque.
    public int size() {
        return size;
    }

    // Prints the items in the Deque from first to last, separated by a space.
    public void printDeque() {
        Node p = sentinel.next;
        while (p != sentinel) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    // Removes and returns the item at the front of the Deque. If no such item exists, returns null.
    public Item removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Item itemRemoved = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;

        size -= 1;
        return itemRemoved;
    }

    // Removes and returns the item at the back of the Deque. If no such item exists, returns null.
    public Item removeLast() {
        if (isEmpty()) {
            return null;
        }
        Item itemRemoved = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;

        size -= 1;
        return itemRemoved;
    }

    // Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
    // If no such item exists, returns null. Must not alter the deque!
    public Item get(int index) {
        if (index >= size) {
            return null;
        }
        Node p = sentinel.next;
        while (index > 0) {
            p = p.next;
            index -= 1;
        }
        return p.item;
    }

    private Item getRecursiveHelper(int index, Node p) {
        if (index >= size) {
            return null;
        }else if(index == 0) {
            return p.item;
        }else {
            return getRecursiveHelper(index - 1, p.next);
        }
    }

    public Item getRecursive(int index) {
        return getRecursiveHelper(index, sentinel.next);
    }
}