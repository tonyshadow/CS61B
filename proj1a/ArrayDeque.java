public class ArrayDeque<Item> {
    private Item[] items;
    private int nextFirst;
    private int nextLast;
    private int size;

    private static int REFACTOR = 2;
    private static double RATIO = 0.25;

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

    // Help find the real index of the index th item in the whole ArrayDeque.
    public int realIndex(int index) {
        return (index + nextFirst + 1) % items.length;
    }

    public void resize(int capacity) {
        Item[] newArray = (Item[]) new Object[capacity];
        int indexFirst = indexAfter(nextFirst);
        if (indexFirst + size > items.length) {
            System.arraycopy(items, realIndex(0), newArray, 0, items.length - indexFirst);
            System.arraycopy(items, 0, newArray, items.length - indexFirst + 1, size);
        }else {
            System.arraycopy(items, indexFirst, newArray, 0, size);
        }
        items = newArray;
        nextFirst = items.length;
        nextLast = size;
    }

    public void usageRatio() {
        if (items.length >= 16) {
            if ((double) size / items.length < RATIO) {
                resize(size / 2);
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
        return itemRemoved;
    }

    // Removes and returns the item at the back of the Deque. If no such item exists, returns null.
    public Item removeLast() {
        if (isEmpty()) {
            return null;
        }
        Item itemRemoved = get(size - 1);
        items[realIndex(size - 1)] = null;
        nextLast = realIndex(size - 1);

        size -= 1;
        return itemRemoved;
    }

    // Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
    // If no such item exists, returns null. Must not alter the deque!
    public Item get(int index) {
        if (index >= size) {
            return null;
        }
        int i = realIndex(index);
        return items[i];
    }
}
