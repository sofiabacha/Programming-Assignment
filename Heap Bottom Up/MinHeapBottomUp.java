import java.io.*;

public class MinHeapBottomUp {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        int n = Integer.parseInt(reader.readLine());
        String[] tokens = reader.readLine().split(" ");
        int[] H = new int[n + 1];
  
        for (int i = 1; i <= n; i ++) {
            H[i] = Integer.parseInt(tokens[i-1]);
        }
        reader.close();
        
        long startTime = System.nanoTime();
        heapBottomUp(H, n);
        long endTime = System.nanoTime();

        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
        for (int i = 1; i <= n; i ++) {
            writer.write(H[i] + " ");
        }
        writer.newLine();;
        writer.close();

        System.out.println("Execution time (ns): " + (endTime - startTime));
    }

    public static void heapBottomUp(int[] H, int n) {
        for (int i = n/2; i >= 1; i--) {
            int k = i;
            int v = H[k];
            boolean heap = false;

            while(!heap && 2*k <= n) {
                int j = 2*k;
                if (j < n && H[j] > H[j +1]) {
                    j++;
                }
                if (v <= H[j]) {
                    heap = true;
                } else {
                    H[k] = H[j];
                    k = j;
                }
            }
            H[k] = v;
        }
    }
}
