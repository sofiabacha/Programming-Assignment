import java.io.*;
import java.util.*;

public class BoyerMoore {
    private static final int ALPHABET_SIZE = 27;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        String pattern = reader.readLine();
        String text = reader.readLine();
        reader.close();
        
        //timing
        long startTime = System.nanoTime();
        int matchIndex = boyerMooreSearch(text, pattern);
        long endTime = System.nanoTime();

        //Outputs:
        //print to txt file
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
        writer.write(Integer.toString(matchIndex));
        writer.close();
        //print time in terminal
        System.out.println("Boyer-Moore execution time (ms): " + (endTime - startTime) / 1_000_000.0);
    }

    private static int boyerMooreSearch(String text, String pattern) {
        int[] badChar = badCharTable(pattern);
        int n = text.length();
        int m = pattern.length();
        int shift = 0;

        while (shift <= (n - m)) {
            int j = m - 1;
            while (j >= 0 && pattern.charAt(j) == text.charAt(shift + j))
                j--;
            if (j < 0) { //match found
                return shift;
            } else {
                char wrongChar = text.charAt(shift + j);
                int badShift = badChar[getIndex(wrongChar)];
                shift += Math.max(1, j-badShift);
            }
        }
        return -1; //no match found
    }

    private static int[] badCharTable(String pattern) {
        int[] badChar = new int[ALPHABET_SIZE];
        Arrays.fill(badChar, -1);
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            badChar[getIndex(c)] = i;
        }
        return badChar;
    }

    private static int getIndex(char c) {
        if (c == ' ') {
            return 26;
        } else {
            return c - 'a';
        }
    }

}