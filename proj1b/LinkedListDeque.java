public class LinkedListDeque<Item> implements Deque<Item> {
    private class Node {
        private Item item;
        private Node pre;
        private Node next;
    }
    private Node sentinel;
    private int size;


    public LinkedListDeque() {
        sentinel = new Node();
        sentinel.pre = sentinel;
        sentinel.next = sentinel;
        size = 0;

    }

    @Override
    public void addFirst(Item item) {
        size += 1;
        Node node = new Node();
        node.item = item;
        node.next = sentinel.next;
        node.pre = sentinel;
        sentinel.next.pre = node;
        sentinel.next = node;
    }

    @Override
    public void addLast(Item item) {
        size += 1;
        Node node = new Node();
        node.item = item;
        node.next = sentinel;
        node.pre = sentinel.pre;
        sentinel.pre.next = node;
        sentinel.pre = node;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        Node ptr = sentinel;
        while (ptr.next != sentinel) {
            System.out.print(ptr.next.item + " ");
            ptr = ptr.next;
        }
    }

    @Override
    public Item removeFirst() {
        if (size == 0) {
            return null;
        }
        size -= 1;
        Item res = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.pre = sentinel;
        return res;
    }

    @Override
    public Item removeLast() {
        if (size == 0) {
            return null;
        }
        size -= 1;
        Item res = sentinel.pre.item;
        sentinel.pre = sentinel.pre.pre;
        sentinel.pre.next = sentinel;
        return res;
    }

    @Override
    public Item get(int index) {
        if (index < 0 || index > size - 1) {
            return null;
        }
        Node ptr = sentinel;
        if (index < size / 2) {
            for (int i = 0; i <= index; i++) {
                ptr = ptr.next;
            }
            return ptr.item;
        } else {
            int revIndex = size - index - 1;
            for (int i = 0; i <= revIndex; i++) {
                ptr = ptr.pre;
            }
            return ptr.item;
        }
    }

    public Item getRecursive(int index) {
        return getRecursiveHelper(sentinel.next, index);
    }

    private Item getRecursiveHelper(Node node, int idx) {
        if (idx == 0) {
            return node.item;
        } else {
            return getRecursiveHelper(node.next, idx - 1);
        }
    }
    /*
    public static void main(String[] args) {
        LinkedListDeque<Integer> L = new LinkedListDeque<Integer>();
        L.addFirst(1);
        System.out.println("all values: ");
        L.printDeque();
        System.out.println();

        L.addFirst(2);
        System.out.println("all values: ");
        L.printDeque();
        System.out.println();

        int val = L.removeFirst();
        System.out.println("removed value: " + val);
        System.out.println("all values: ");
        L.printDeque();
        System.out.println();

        L.addLast(2);
        System.out.println("all values: ");
        L.printDeque();
        System.out.println();

        L.removeLast();
        System.out.println("all values: ");
        L.printDeque();
        System.out.println();

        L.addLast(2);
        L.addLast(3);
        L.addLast(4);
        System.out.println("all values: ");
        L.printDeque();
        System.out.println();
        System.out.println("0th value: " + L.get(0));
        System.out.println("1st value: " + L.get(1));
        System.out.println("2nd value: " + L.get(2));
        System.out.println("3th value: " + L.get(3));
        System.out.println("4th value: " + L.get(4));

        System.out.println("0th value: " + L.getRecursive(0));
        System.out.println("1st value: " + L.getRecursive(1));
        System.out.println("2nd value: " + L.getRecursive(2));
        System.out.println("3th value: " + L.getRecursive(3));
        System.out.println("4th value: " + L.getRecursive(4));
   }
   */
}
