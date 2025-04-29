import java.io.*;
import java.util.*;

public class Sort {	
    public static void main(String[] args) throws IOException {
        // Read input from input.txt
        Scanner scanner = new Scanner(new File("input.txt"));
        List<Double> numList = new ArrayList<>();
        while (scanner.hasNextDouble()) {
            numList.add(scanner.nextDouble());
        }
        scanner.close();

        double[] numbers = numList.stream().mapToDouble(Double::doubleValue).toArray();
        double[] mergeInput = numbers.clone();
        double[] quickInput = numbers.clone();


        //Time and run Merge Sort
        long startMerge = System.nanoTime();
        mergeSort(mergeInput);
        long endMerge = System.nanoTime();
        System.out.println("Merge Sort execution time (ms): " + (endMerge - startMerge) / 1_000_000.0);
        
        //Time and run Quick Sort
        long startQuick = System.nanoTime();
        quickSort(quickInput, 0, quickInput.length - 1);
        long endQuick = System.nanoTime();
        System.out.println("Quick Sort execution time (ms): " + (endQuick - startQuick) / 1_000_000.0);

        //Print both result to txt file
        PrintWriter writer = new PrintWriter("output.txt");
        writer.println("Sorted by Merge Sort:");
        for (double num : mergeInput) {
            writer.print(num + " ");
        }
        writer.println();

        writer.println("Sorted by Quick Sort:");
        for (double num : quickInput){
            writer.print(num + " ");
        }
        writer.println();

        writer.close();
    }

    public static void mergeSort(double[] a) {
        int size = a.length;
        if (size<2) //halting condition
            return;
        int mid = size/2;
        int leftSize = mid;
        int rightSize = size-mid;
        double[] left = new double[leftSize];
        double[] right = new double[rightSize];
        //left array
        for(int i=0;i<mid;i++)
            left[i] = a[i];
        //right array
        for(int i=mid;i<size;i++)
            right[i-mid] = a[i];
        mergeSort(left); //recursive calls
        mergeSort(right);
        //merge part
        merge(left,right,a);
    }
    public static void merge(double[] left, double[] right, double[] a) {
        int leftSize = left.length;
        int rightSize = right.length;
        int i = 0; //left index
        int j = 0; //right index
        int k = 0; // merged index
        while(i<leftSize && j<rightSize){ //the left and right arrays are not empty
            if(left[i]<right[j]) {
                a[k] = left[i];
                i++;
                k++;
            }
            else {
                a[k] = right[j];
                j++;
                k++;
            }
        }
        //left overs
        while(i<leftSize) {
            a[k] = left[i];
            i++;
            k++;
        }
        while(j<rightSize) {
            a[k]=right[j];
            j++;
            k++;
        }
    }
    public static void quickSort(double[] a,int start, int end) {
        if(start>=end)
            return;
        int pivot = partition(a, start, end);
        quickSort(a, start, pivot-1);//left
        quickSort(a, pivot+1, end); //right
        
    }
    public static int partition(double a[], int start, int end) {
        double pivot = a[end];
        int i = start;
        for(int j=start;j<=end;j++) {
            if(a[j]<pivot) {
                double temp = a[i];
                a[i] = a[j];
                a[j] = temp;
                i++;
            }
        }
        double temp = a[i];
        a[i] = a[end];
        a[end] = temp;
        return i;
    }
}