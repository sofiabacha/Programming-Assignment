import java.io.*;
import java.util.*;

public class Floyd {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        List<String[]> lines = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line.trim().split("\\s+"));
        }
        reader.close();

        int n = lines.size();
        double[][] D = new double[n][n];

        //Initializing matrix
        for (int i = 0; i < n; i++) {
            String[] values = lines.get(i); 
            for (int j = 0; j < n; j++) {
                D[i][j] = Double.parseDouble(values[j]);
            }
        }

        //Floyd's Algorithm 
        long startTime = System.nanoTime();
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    D[i][j] = Math.min(D[i][j], D[i][k] + D[k][j]);
                }
            }
        }
        long endTime = System.nanoTime();

        //Print to output file
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                writer.write(String.format("%.2f", D[i][j]) + " ");
            }
            writer.newLine();
        }
        writer.close();

        System.out.println("Execution time (ns): " + (endTime - startTime));
    }
}