/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        for (int i = 1; i < args.length; i++)
            queue.enqueue(args[i]);

        for (int i = 0; i < k; i++)
            StdOut.print(queue.dequeue() + "\n");
    }
}
