/*
 * Ebrahim Qasem, COSC 311, Fall 2019
 * Merge Sort Project
 * 12/10/2019
 * URL:
 * 
 */



import java.util.*;
import java.io.*;

class ExternalSort{
    static long numComparison;
    public static void main(String[] args) throws IOException{
    	System.out.println("Please eneter a number: ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        numComparison = 0;
        generateInput(n);
        // We can store atmost 2 integers at a time in the memory.
        externalMergeSort(n,2);
        //printFile("sorted_array.txt");
        System.out.println("\n"+numComparison + " Comparisons");
        sc.close();
    }
    // Function to print the final output file.
    static void printFile(String file) throws IOException{
        BufferedReader br1 = new BufferedReader(new FileReader(file));
        String s = "";
        while ((s=br1.readLine())!=null) {
            System.out.print(s+" ");
        }
        System.out.println();
        br1.close();
    }
    // Function for externalMergeSort
    static void externalMergeSort(int n, int m) throws IOException{
        int cnt = 0;
        //Open the input file for reading.
        BufferedReader br = new BufferedReader(new FileReader("array.txt"));
        // m is the maximum number of integers which can be stored in memory at a time.
        // Here m is 2.
        // Read m integers at a time and then sort them using mergeSort and store the output in a file.
        for (int i = 0; i < n; i+=m) {
            PrintWriter pw1 = new PrintWriter(new FileWriter("out/out"+cnt+".txt"));  
            int[] arr = new int[m];
            int numcnt = 0;
            for(int j=0;j<m && i+j<n;j++){
                arr[j] = Integer.parseInt(br.readLine());
                numcnt++;
            }
            mergeSort(arr,0,numcnt-1);
            for(int j=0;j<numcnt;j++){
                pw1.println(arr[j]);
            }
            cnt++;
            pw1.close();
        }
        br.close();
        // Now merge the output file to get the final sorted file.
        mergeFile(cnt);
    }
    // Function to merge the output files using minheap.
    static void mergeFile(int cnt) throws IOException{
        MINHEAP mh = new MINHEAP(cnt);
        BufferedReader[] brs = new BufferedReader[cnt];
        for (int i = 0; i < cnt; i++) {
            brs[i] = new BufferedReader(new FileReader("out//out"+i+".txt"));
            mh.insert(Integer.parseInt(brs[i].readLine()), i);
        }
        PrintWriter p = new PrintWriter(new FileWriter("sorted_array.txt"));
        while (true) {
            Pair min = mh.deleteMIN();
            // if minimum = Integer.MAX_VALUE that means we have read all the integers. So we break after that.
            if(min.num==Integer.MAX_VALUE) break;
            p.println(min.num);
            int idx = min.i;
            String s = "";
            // if we have read a file completely, then ......
            if((s=brs[idx].readLine())==null){
                mh.insert(Integer.MAX_VALUE, idx);
            }
            else{
                mh.insert(Integer.parseInt(s), idx);
            }
        }
        for (int j = 0; j < brs.length; j++) {
            brs[j].close();
        }
        p.close();
    }
    // Function for mergeSort
    static void mergeSort(int[] arr, int l, int r){
        if(l<r){
            int mid = l+(r-l)/2;
            mergeSort(arr, l, mid);
            mergeSort(arr, mid+1, r);
            merge(arr, l, mid, r);
        }
    }
    static class Pair {
        int num, i;
        public Pair(int a, int b){
            num = a;
            i = b;
        }
    }
    static void merge(int[] arr, int l, int mid, int r){
        int[] temp1 = new int[mid-l+1];
        int[] temp2 = new int[r-mid];
        for (int i = 0; i < temp2.length; i++) {
            temp2[i] = arr[mid+1+i];
        }
        for (int i = 0; i < temp1.length; i++) {
            temp1[i] = arr[l+i];
        }
        int i=0, j=0, k = l;
        while (i<temp1.length && j<temp2.length) {
            if(temp2[j]>=temp1[i]){
                arr[k++] = temp1[i++];
            }
            else{
                arr[k++] = temp2[j++];
            }
            numComparison++;
            
        }
        while (i<temp1.length) {
            arr[k++] = temp1[i++];
        }
        while (j<temp2.length) {
            arr[k++] = temp2[j++];
        }
    }
    // Generates random inputs.
    static void generateInput(int n) throws IOException{
        Random r = new Random();
        PrintWriter pw = new PrintWriter(new FileWriter("array.txt"));
        for (int i = 0; i < n; i++) {
            pw.println(r.nextInt(100000));
        }
        pw.close();
    }
    static class MINHEAP {
        Pair[] key;
        int n;
        public MINHEAP(int m){
            key = new Pair[m+1];
            n = 0;
        }
        void insert(int key1, int p){
            key[++n] = new Pair(key1, p);
            swim(n);
        }
        void swim(int k){
            while (k>1 && less(k/2, k)) {
                swap(k/2, k);
                k/=2;
            }
        }
        Pair deleteMIN(){
            Pair min = key[1];
            key[1] = key[n--];
            sink(1);
            key[n+1] = null;
            return min;
        }
        void sink(int k){
            while (2*k<=n) {
                int j = 2*k;
                if (j<n && less(j,j+1)) {
                    j++;
                }
                if (!less(k, j)) {
                    break;
                }
                swap(k,j);
                k=j;
            }
        }
        boolean less(int a, int b){
            numComparison++;
            return key[a].num>key[b].num;
        }
        void swap(int a, int b){
            Pair c=key[a];
            key[a] = key[b];
            key[b] = c;
        }
    }
}