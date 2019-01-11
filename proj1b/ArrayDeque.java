public class ArrayDeque<Item> implements Deque<Item>{
    private Item[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private static final int SCALE_UP_FACTOR = 2;
    private static final double SCALE_DOWN_FACTOR = 0.5;
    private static final double SIZE_USE_FACTOR = 0.25;
    private static final int ARR_RED_LOW = 16;
    /** Creates an empty list. */
    public ArrayDeque() {
        items = (Item[]) new Object[8];
        size = 0;
        nextFirst = items.length / 2;
        nextLast = items.length / 2 + 1;
    }

    private void resize(int capacity) {
        Item[] newArray = (Item[]) new Object[capacity];
        int idxStartItems = addOne(nextFirst);
        int idxEndItems = minusOne(nextLast);
        if (idxEndItems > idxStartItems) {
            int cntCopyFirst = idxEndItems - idxStartItems + 1;
            System.arraycopy(items, idxStartItems, newArray, capacity / 4, cntCopyFirst);
        } else {
            int cntCopyFirst =  items.length - idxStartItems;
            int cntCopySecond = size - cntCopyFirst;
            System.arraycopy(items, idxStartItems, newArray, capacity / 4, cntCopyFirst);
            System.arraycopy(items, 0, newArray, capacity / 4 + cntCopyFirst, cntCopySecond);
        }
        items = newArray;
        nextFirst = capacity / 4 - 1;
        nextLast = nextFirst + size + 1;
    }

    private int minusOne(int x) {
        x = x - 1;
        if (x < 0) {
            x = items.length - 1;
        }
        return x;
    }

    private int addOne(int x) {
        x = x + 1;
        if (x == items.length) {
            x = 0;
        }
        return x;
    }

    @Override
    /** add to first of the array*/
    public void addFirst(Item item) {
        if (size == items.length) {
            resize((int) (items.length * SCALE_UP_FACTOR));
        }
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size += 1;
    }

    @Override
    /** add to end of the array*/
    public void addLast(Item item) {
        if (size == items.length) {
            resize((int) (items.length * SCALE_UP_FACTOR));
        }
        items[nextLast] = item;
        nextLast = addOne(nextLast);
        size += 1;
    }

    @Override
    /** is ArrayDeque empty*/
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    /** size the of array*/
    public int size() {
        return size;
    }

    @Override
    /** print ArrayDeque*/
    public void printDeque() {
        int i = 0;
        while (i < size) {
            int idx = addOne(nextFirst + i);
            System.out.print(items[idx] + " ");
            i += 1;
        }
    }

    @Override
    /** remove first*/
    public Item removeFirst() {
        if (size == 0) {
            return null;
        }
        int curIdx = addOne(nextFirst);
        Item res = items[curIdx];
        items[curIdx] = null;
        nextFirst = addOne(nextFirst);
        size -= 1;
        if (items.length >= ARR_RED_LOW && size < (int) (items.length * SIZE_USE_FACTOR)) {
            resize((int) (items.length * SCALE_DOWN_FACTOR));
        }
        return res;
    }

    @Override
    /** remove last*/
    public Item removeLast() {
        if (size == 0) {
            return null;
        }
        int curIdx = minusOne(nextLast);
        Item res = items[curIdx];
        items[curIdx] = null;
        nextLast = minusOne(nextLast);
        size -= 1;
        if (items.length >= ARR_RED_LOW && size < (int) (items.length * SIZE_USE_FACTOR)) {
            resize((int) (items.length * SCALE_DOWN_FACTOR));
        }
        return res;
    }

    @Override
    /** Gets the ith item in the array */
    public Item get(int i) {
        int idx = nextFirst + 1 + i;
        if (idx < items.length) {
            return items[idx];
        }
        return items[idx - items.length];
    }
    /*
    public static void main(String[] args) {
        ArrayDeque<Integer> A = new ArrayDeque<>();
        System.out.println("A size: " + A.size());
        A.printDeque();

        A.addFirst(2);
        A.addLast(3);
        A.addFirst(1);
        A.addLast(4);
        System.out.println("A size: " + A.size());
        A.printDeque();
        System.out.println('\n');
        A.removeFirst();
        A.removeLast();
        A.removeFirst();
        A.removeLast();
        A.addFirst(1);
        A.addLast(2);
        System.out.println("A size: " + A.size());
        A.printDeque();


        //test addLast
        for (int i = 0; i < 24; i++) {
            A.addLast( i + 1);
        }
        System.out.println("A size: " + A.size());
        A.printDeque();
        System.out.println('\n');

        //test removeFirst and removeLast
        for (int i = 0; i < 24; i++) {
            A.removeFirst();
            A.removeLast();
        }
        System.out.println("A size: " + A.size());
        A.printDeque();
        System.out.println('\n');
    }
    */
}

