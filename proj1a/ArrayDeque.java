public class ArrayDeque<Item> {
    private Item[] items;
    private int nextFirst;
    private int nextLast;
    private int size;

    private static int REFACTOR = 2;

    public ArrayDeque() {
        items = (Item[]) new Object[8];
        nextFirst = 0;
        nextLast = 1;
        size = 0;
    }

    // Help find index before a given index.
    private int indexBefore(int index) {
        return (index + items.length - 1) % items.length;
    }

    // Help find index after a given index.
    private int indexAfter(int index) {
        return (index + 1) % items.length;
    }

    private void resize(int capacity) {
        Item[] newArray = (Item[]) new Object[capacity];
        int indexFirst = indexAfter(nextFirst);
        if (indexFirst + size > items.length) {
            System.arraycopy(items, indexFirst, newArray, 0, items.length - indexFirst);
            System.arraycopy(items, 0, newArray, items.length - indexFirst, size - items.length + indexFirst);
        }else {
            System.arraycopy(items, indexFirst, newArray, 0, size);
        }
        items = newArray;
        nextFirst = items.length - 1;
        nextLast = size;
    }

    private void checkRatio() {
        if (items.length >= 16) {
            double RATIO = 0.25;
            if ((double) size / items.length < RATIO) {
                resize(items.length / 2);
            }
        }
    }

    // Adds an item to the front of the Deque.
    public void addFirst(Item item) {
        if (size == items.length) {
            resize(size * REFACTOR);
        }
        items[nextFirst] = item;
        nextFirst = indexBefore(nextFirst);

        size += 1;
    }

    // Adds an item to the back of the Deque.
    public void addLast(Item item) {
        if (size == items.length) {
            resize(size * REFACTOR);
        }
        items[nextLast] = item;
        nextLast = indexAfter(nextLast);

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
        for (int i = 0; i < size; i++) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }

    // Removes and returns the item at the front of the Deque. If no such item exists, returns null.
    public Item removeFirst() {
        if (isEmpty()) {
            return null;
        }
        int indexFirst = indexAfter(nextFirst);
        Item itemRemoved = items[indexFirst];
        items[indexFirst] = null;
        nextFirst = indexFirst;

        size -= 1;
        checkRatio();
        return itemRemoved;
    }

    // Removes and returns the item at the back of the Deque. If no such item exists, returns null.
    public Item removeLast() {
        if (isEmpty()) {
            return null;
        }
        int indexLast = indexBefore(nextLast);
        Item itemRemoved = items[indexLast];
        items[indexLast] = null;
        nextLast = indexLast;

        size -= 1;
        checkRatio();
        return itemRemoved;
    }

    // Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
    // If no such item exists, returns null. Must not alter the deque!
    public Item get(int index) {
        if (index >= size) {
            return null;
        }
        int realIndex = (nextFirst + 1 + index) % items.length;
        return items[realIndex];
    }
}
