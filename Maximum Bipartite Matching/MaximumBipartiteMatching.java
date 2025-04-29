import java.io.*;
import java.util.*;

public class MaximumBipartiteMatching {
    static List<String> V = new ArrayList<>();
    static List<String> U = new ArrayList<>();
    static int[][] matrix;
    static Map<String, String> match = new HashMap<>();
    static Map<String, String> label = new HashMap<>();
    
    //Reading and initializing all data from input
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        String[] vArray = reader.readLine().trim().split("\\s+");
        String[] uArray = reader.readLine().trim().split("\\s+");

        V.addAll(Arrays.asList(vArray));
        U.addAll(Arrays.asList(uArray));
        int vSize = V.size();
        int uSize = U.size();
        matrix = new int[vSize][uSize];

        for (int i = 0; i < vSize; i++) {
            String[] line = reader.readLine().trim().split("\\s+");
            for (int j = 0; j < uSize; j++) {
                matrix[i][j] = Integer.parseInt(line[j]);
            }
        }
        reader.close();

        maximumBipartiteMatching();

        //Print to output.txt
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
        for (String v : V) {
            writer.write(v + " ");
        }
        writer.newLine();
        for (String u : U) {
            writer.write(u + " ");
        }
        writer.newLine();

        int[][] outputMatrix = new int[vSize][uSize];
        for (Map.Entry<String, String> entry : match.entrySet()) {
            String v = entry.getKey();
            String u = entry.getValue();
            int i = V.indexOf(v);
            int j = U.indexOf(u);
            if ( i == -1 || j == -1) {
                continue;
            }
            outputMatrix[i][j] = 1;
        }
        for (int i = 0; i < vSize; i++) {
            for (int j = 0; j < uSize; j++) {
                writer.write(outputMatrix[i][j] + " ");
            }
            writer.newLine();
        }
        writer.close();
    }

    //Max Bipartite Matching Algo:
    static void maximumBipartiteMatching(){
        Queue<String> queue = new LinkedList<>();
        match.clear();

        for (String v : V) {
            if (!isMatched(v)) {
                queue.add(v);
            }
        }

        while (!queue.isEmpty()) {
            String w = queue.poll();
            if (V.contains(w)) { 
                int wIndex = V.indexOf(w);
                for (int j = 0; j < U.size(); j++) {
                    if (matrix[wIndex][j] == 1) {
                        String u = U.get(j);
                        if (!isMatched(u)) { //augment the matrix
                            match.put(w, u);
                            String v = w;
                            while (label.containsKey(v)) {
                                String uPrev = label.get(v);
                                String vPrev = getMatchedVertex(uPrev);
                                if (vPrev == null) break;
                                match.remove(vPrev);
                                match.put(vPrev, v);
                                v = vPrev;
                            }
                            label.clear();
                            //restart queue
                            queue.clear();
                            for (String freeV : V) {
                                if (!isMatched(freeV)) {
                                    queue.add(freeV);
                                }
                            }
                            break; //exit the for loop
                        } else if (isMatched(u) && label.get(u) == null) {
                                label.put(u, w);
                                queue.add(u);
                        }
                    }
                }
            } else { // w in U and matched
                String v = getMatchedVertex(w);
                if (v != null && !label.containsKey(v)) {
                    label.put(v, w);
                    queue.add(v);
                }
            }
        }
    }

    static boolean isMatched(String vertex) {
        return match.containsKey(vertex) || match.containsValue(vertex);
    }

    static String getMatchedVertex(String u) {
        for (Map.Entry<String, String> entry : match.entrySet()) {
            if (entry.getValue().equals(u)) {
                return entry.getKey();
            }
        }
        return null;
    }
}

