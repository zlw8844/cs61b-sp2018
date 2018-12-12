public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private static final int scaleUpFactor = 2;
    private static final double scaleDownFactor = 0.5;
    private static final double sizeUseFactor = 0.25;
    private static final int arrRedLow = 16;
    /** Creates an empty list. */
    public ArrayDeque() {
        items = (T[])new Object[8];
        size = 0;
        nextFirst = items.length/2;
        nextLast = items.length/2 + 1;
    }

    public void resize(int capacity){
        T[] newArray = (T[]) new Object[capacity];
        int idxStartItems = addOne(nextFirst);
        int idxEndItems = minusOne(nextLast);
        if (idxEndItems > idxStartItems){
            int cntCopyFirst = idxEndItems - idxStartItems + 1;
            System.arraycopy(items, idxStartItems, newArray, capacity/4, cntCopyFirst);
        }
        else{
            int cntCopyFirst =  items.length - idxStartItems;
            int cntCopySecond = size - cntCopyFirst;
            System.arraycopy(items, idxStartItems, newArray, capacity/4, cntCopyFirst);
            System.arraycopy(items, 0, newArray, capacity/4 + cntCopyFirst, cntCopySecond);
        }
        items = newArray;
        nextFirst = capacity/4 - 1;
        nextLast = nextFirst + size + 1;
    }

    private int minusOne(int x){
        x = x - 1;
        if (x < 0){
            x = items.length-1;
        }
        return x;
    }

    private int addOne(int x){
        x = x + 1;
        if (x == items.length){
            x = 0;
        }
        return x;
    }
    /** add to first of the array*/
    public void addFirst(T item){
        if (size == items.length){
            resize((int) (items.length*scaleUpFactor));
        }
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size += 1;
    }

    /** add to end of the array*/
    public void addLast(T item){
        if (size == items.length){
            resize((int) (items.length*scaleUpFactor));
        }
        items[nextLast] = item;
        nextLast = addOne(nextLast);
        size += 1;
    }

    /** is ArrayDeque empty*/
    public boolean isEmpty(){
        return (size == 0);
    }

    /** size the of array*/
    public int size(){
        return size;
    }

    /** print ArrayDeque*/
    public void printDeque(){
        int i = 0;
        while (i < size){
            int idx = addOne(nextFirst+i);
            System.out.print(items[idx] + " ");
            i += 1;
        }
    }

    /** remove first*/
    public T removeFirst(){
        if (size == 0){
            return null;
        }
        int curIdx = addOne(nextFirst);
        T res = items[curIdx];
        items[curIdx] = null;
        nextFirst = addOne(nextFirst);
        size -= 1;
        if (items.length >= arrRedLow && size < (int)(items.length*sizeUseFactor)){
            resize((int) (items.length*scaleDownFactor));
        }
        return res;
    }

    /** remove last*/
    public T removeLast(){
        if (size == 0){
            return null;
        }
        int curIdx = minusOne(nextLast);
        T res = items[curIdx];
        items[curIdx] = null;
        nextLast = minusOne(nextLast);
        size -= 1;
        if (items.length >= arrRedLow && size < (int)(items.length*sizeUseFactor)){
            resize((int) (items.length*scaleDownFactor));
        }
        return res;
    }


    /** Gets the ith item in the array */
    public T get(int i) {
        int idx = nextFirst + 1 + i;
        if (idx < items.length){
            return items[idx];
        }
        return items[idx-items.length];
    }

    public static void main(String[] args){
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


        /**test addLast*/
        for (int i=0; i<24; i++){
            A.addLast(i+1);
        }
        System.out.println("A size: " + A.size());
        A.printDeque();
        System.out.println('\n');

        /**test removeFirst and removeLast*/
        for (int i=0; i<24; i++){
            A.removeFirst();
            A.removeLast();
        }
        System.out.println("A size: " + A.size());
        A.printDeque();
        System.out.println('\n');




    }
} 