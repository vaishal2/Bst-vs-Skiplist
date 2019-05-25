package dictionary;

import java.util.Random;

import dictionary.DictionaryInterface.SkippableList;

public class skiplist<T extends Comparable<? super T>> implements SkippableList<T> {

    private final Skip<T> head = new Skip<>(null);
    private final Random rand = new Random();

    @Override
    public void insert(T Keys) {
        Skip<T> SkipNode = new Skip<>(Keys);
        for (int i = 0; i < LEVELS; i++) {
            if (rand.nextInt((int) Math.pow(2, i)) == 0) { 
                insert(SkipNode, i);
            }
        }
    }

    @Override
    public boolean delete(T Del) {
        System.out.println("Deleting " + Del.toString());
        Skip<T> element = search(Del, false);
        if (element == null) return false;
        element.data = null;

        for (int i = 0; i < LEVELS; i++) {
            head.deletelist(i);
        }

        System.out.println();
        return true;
    }
       public Skip<T> closestkeyafter(T data) {
        return search(data, true);
    }


    @Override
    public void print() {
        for (int i = 0; i < LEVELS; i++) {
            head.print(i);
        }

        System.out.println();
    }

    @Override
    public Skip<T> search(T data) {
        return search(data, true);
    }
    
    private void insert(Skip<T> SkipNode, int level) {
        head.insert(SkipNode, level);
    }

    private Skip<T> search(T data, boolean print) {
        Skip<T> result = null;
        for (int i = LEVELS-1; i >= 0; i--) {
            if ((result = head.search(data, i, print)) != null) {
                if (print) {
                    System.out.println("Found " + data.toString() + " at level " + i + ", so stoppped" );
                    System.out.println();
                }

                break;
            }
        }

        return result;
    }

    public static class SkipListImpl extends skiplist {

        public SkipListImpl() {
        }
    }

}

/*private closestkeyafter<T> search(T data, boolean print) {
Skip<T> result = null;
for (int i = LEVELS-1; i >= 0; i--) {
if ((result = head.search(data, i, print)) != null) {
if (print) {
System.out.println("Found " + data.toString()+1 + " at level " + i + ");
System.out.println();
}

break;
}
}

return result;
}*/


class Skip<N extends Comparable<? super N>>
{

    N data;
    @SuppressWarnings("unchecked")
    Skip<N>[] next = (Skip<N>[]) new Skip[SkippableList.LEVELS];

    Skip(N data) {
        this.data = data;
    }

    void deletelist(int level) {
        Skip<N> current = this.next(level);
        while (current != null && current.next(level) != null) {
            if (current.next(level).data == null) {
                Skip<N> successor = current.next(level).next(level);
                current.set(successor, level);
                return;
            }

            current = current.next(level);
        }
    }

    void set(Skip<N> next, int level) {
        this.next[level] = next;
    }

    Skip<N> next(int level) {
        return this.next[level];
    }

    Skip<N> search(N data, int level, boolean print) {
        if (print) {
            System.out.print("Searching for: " + data + " at ");
            print(level);
        }

        Skip<N> result = null;
        Skip<N> current = this.next(level);
        while (current != null && current.data.compareTo(data) < 1) {
            if (current.data.equals(data)) {
                result = current;
                break;
            }

            current = current.next(level);
        }

        return result;
    }

    void insert(Skip<N> SkipNode, int level) {
        Skip<N> current = this.next(level);
        if (current == null) {
            this.set(SkipNode, level);
            return;
        }

        if (SkipNode.data.compareTo(current.data) < 1) {
            this.set(SkipNode, level);
            SkipNode.set(current, level);
            return;
        }

        while (current.next(level) != null && current.data.compareTo(SkipNode.data) < 1 && 
                current.next(level).data.compareTo(SkipNode.data) < 1) {

            current = current.next(level);
        }

        Skip<N> successor = current.next(level);
        current.set(SkipNode, level);
        SkipNode.set(successor, level);
    }
    
    

    void print(int level) {
        System.out.print("level " + level + ": [");
        int length = 0;
        Skip<N> current = this.next(level);
        while (current != null) {
            length++;
            System.out.print(current.data.toString() + " ");
            current = current.next(level);
        }

        System.out.println("], length: " + length);
    }
    
}