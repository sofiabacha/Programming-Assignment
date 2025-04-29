import java.io.*;
import java.util.*;

public class Bonus_HeapSort {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        int n = Integer.parseInt(reader.readLine());
        String[] tokens = reader.readLine().trim().split("\\s+");
        int[] H = new int[n + 1];
        for (int i = 0; i < n; i++) {
            H[i + 1] = Integer.parseInt(tokens[i]);
        }
        reader.close();

        heapBottomUp(H, n);

        int[] heapSnapshot = Arrays.copyOfRange(H, 1, n + 1);

        //Heapsort
        int heapSize = n;
        int[] sorted = new int[n];
        for (int i = n; i >= 2; i--) {
            int temp = H[1];
            H[1] = H[i];
            H[i] = temp;
            sorted[n - i] = H[i];
            heapSize--;
            heapify(H, 1, heapSize);
        }
        sorted[n - 1] = H[1];

        //Print to output.txt
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
        //First Line: MaxHeap Bottom Up 
        for (int i = 0; i < heapSnapshot.length; i++) {
            writer.write(heapSnapshot[i] + " ");
        }
        writer.newLine();
        //Second Line: Sorted Heap
        for (int i = 0; i < sorted.length; i++) {
            writer.write(sorted[i] + " ");
        }
        writer.newLine();
        writer.close();
    }

    //heapBottomUp same as before
    public static void heapBottomUp(int[] H, int n) {
        for (int i = n/2; i >= 1; i--) {
            int k = i;
            int v = H[k];
            boolean heap = false; 

            while (!heap && 2 * k <= n) {  
                int j = 2 * k;
                if (j < n && H[j] < H[j+1]){
                    j++; 
                }
                if (v >= H[j]) {
                    heap = true;
                } else {
                    H[k] = H[j];
                    k = j;
                }
            }
            H[k] = v;
        }
    }

    //Heapify algoithm
    public static void heapify(int[] H, int i, int n) {
        int largest = i;
        int left = 2 * i;
        int right = 2 * i + 1;
        if (left <= n && H[left] > H[largest]) {
            largest = left; 
        }
        if (right <= n && H[right] > H[largest]) {
            largest = right; 
        }
        if (largest != i) {
            int temp = H[i];
            H[i] = H[largest];
            H[largest] = temp;
            heapify(H, largest, n);
        }
    }
}