import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class HorspoolMatching {
    static final int ALPHABET_SIZE = 27; 
    //Shift Table:
    public static int[] makeShiftTable(String pattern) {
        int m = pattern.length();
        int[] table = new int[ALPHABET_SIZE];
        //initialize table
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            table[i] = m;
        }

        //Fill table to pattern
        for (int j = 0; j < m - 1; j++) {
            char c = pattern.charAt(j);
            table[charToIndex(c)] = m - 1 - j;
        }

        return table;
    }

    public static int charToIndex(char c) {
        return (c == ' ') ? 26 : (c -'a');
    }

    //Horspool's String Matching Algo:
    public static int horspoolMatching(String pattern, String text, int[] table){
        int  m = pattern.length();
        int n = text.length();
        int i = m -1;
        while (i <= n-1) {
            int k = 0;
            while (k <= m-1 && pattern.charAt(m-1-k) == text.charAt(i - k)) {
                k++;
            }
            if (k == m) {
                return i - m + 1;
            } else {
                char c = text.charAt(i);
                i += table[charToIndex(c)];
            }
        }
        return -1;
    }

        public static void main(String[] args) throws IOException {
        //Read input file
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        String pattern = reader.readLine().toLowerCase();
        String text = reader.readLine().toLowerCase();
        reader.close();

        for (int idx = 0; idx < text.length(); idx++) {
            System.out.println(idx + ": " + text.charAt(idx));
        }
        //Make shift table
        int[] shiftTable = makeShiftTable(pattern);

        //Execution Time
        long startTime = System.nanoTime();
        int matchIndex = horspoolMatching(pattern, text, shiftTable);
        long endTime = System.nanoTime();

        //Print output to file
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
        writer.write(Integer.toString(matchIndex));
        writer.newLine();
        writer.close();

        //Consol timing output
        System.out.println("Match found at index: " + matchIndex);
        System.out.println("Execution time (ns): " + (endTime - startTime));
    }
    
}
