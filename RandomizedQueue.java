/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int n;
    private Item[] items;

    // construct an empty randomized queue
    public RandomizedQueue() {
        n = 0;
        items = (Item[]) new Object[2];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException();

        if (n == 0) {
            n++;
            items[0] = item;
            return;
        }

        if (items.length == n)
            resize(2 * n);

        int randomIndex = StdRandom.uniform(n);
        Item temp = items[randomIndex];
        items[randomIndex] = item;
        items[n++] = temp;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException();

        if (n <= items.length / 4)
            resize(items.length / 2);

        // choose random position
        int randomIndex = StdRandom.uniform(n);
        // store item to return later
        Item item = items[randomIndex];
        // overwrite random position with last element
        items[randomIndex] = items[--n];
        // deallocate last element to avoid loitering
        items[n] = null;

        return item;
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++)
            copy[i] = items[i];
        items = copy;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException();
        int randomIndex = StdRandom.uniform(n);
        return items[randomIndex];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item> {
        private int i;
        private int[] randomIndices;

        public RandomIterator() {
            i = 0;
            randomIndices = new int[n];
            for (int j = 0; j < n; j++)
                randomIndices[j] = j;
            StdRandom.shuffle(randomIndices);
        }

        public boolean hasNext() {
            return i < n;
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Item item = items[randomIndices[i++]];
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> rd = new RandomizedQueue<Integer>();
        rd.enqueue(1);
        StdOut.print(" size: " + rd.size() + "\n");
        rd.enqueue(2);
        StdOut.print(" size: " + rd.size() + "\n");
        rd.enqueue(3);
        StdOut.print(" size: " + rd.size() + "\n");
        rd.enqueue(4);
        StdOut.print(" size: " + rd.size() + "\n");
        rd.enqueue(5);
        StdOut.print(" size: " + rd.size() + "\n");
        rd.dequeue();
        rd.sample();
        for (int i : rd)
            StdOut.print(i);
    }
}
