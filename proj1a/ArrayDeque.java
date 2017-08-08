public class ArrayDeque<Item> {
    private Item[] items;
    private int nextFirst;
    private int nextLast;
    private int size;

    public ArrayDeque() {
        items = (Item[]) new Object[8];
        nextFirst = 0;
        nextLast = 1;
        size = 0;
    }

    // Adds an item to the front of the Deque.
    public void addFirst(Item item) {
        items[nextFirst] = item;
        if (nextFirst == 0) {
            nextFirst = items.length - 1;
        }else {
            nextFirst -= 1;
        }

        size += 1;
    }

    // Adds an item to the back of the Deque.
    public void addLast(Item item) {
        items[nextLast] = item;
        if (nextLast == items.length - 1) {
            nextLast = 0;
        }else {
            nextLast += 1;
        }

        size += 1;
    }

    // Returns true if deque is empty, false otherwise.
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    // Returns the number of items in the Deque.
    public int size() {
        return size;
    }

    // Prints the items in the Deque from first to last, separated by a space.
    public void printDeque() {
        for (int i = 0; i < items.length; i++) {
            if(items[i] != null) {
                System.out.print(items[i] + " ");
            }
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
        sentinel.next.next.prev = sentinel;

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
        sentinel.prev.prev.next = sentinel;

        size -= 1;
        return itemRemoved;
    }

    // Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
    // If no such item exists, returns null. Must not alter the deque!
    public Item get(int index) {
        if (index >= items.length) {
            return null;
        }
        return items[index];
    }
}
