/*
 * Ebrahim Qasem, COSC 311, Fall 2019
 * Merge Sort Project
 * 12/10/2019
 * URL:
 * 
 */

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
public class InternalSort {
	public static int count = 0;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please eneter a number: ");
		int n = sc.nextInt();
		
		int[] arr = generateInput(n);;
		
		System.out.println("Before Sorting");
		for(int i=0; i<arr.length; i++) {
			System.out.print(arr[i]);
			if(i<arr.length - 1)
				System.out.print(", ");
		}
		mergeSort(arr, arr.length);
		
		System.out.println();
		System.out.println("After Sorting");
		for(int i=0; i<arr.length; i++) {
			System.out.print(arr[i]);
			if(i<arr.length - 1)
				System.out.print(", ");
		}
		System.out.println("\n"+count + " Comparisons");
		
	}
	
	public static void mergeSort(int[] a, int n) {
	    if (n < 2) {
	        return;
	    }
	    int mid = n / 2;
	    int[] l = new int[mid];
	    int[] r = new int[n - mid];
	 
	    for (int i = 0; i < mid; i++) {
	        l[i] = a[i];
	    }
	    for (int i = mid; i < n; i++) {
	        r[i - mid] = a[i];
	    }
	    mergeSort(l, mid);
	    mergeSort(r, n - mid);
	 
	    merge(a, l, r, mid, n - mid);
	}
	
	public static void merge(
			  int[] a, int[] l, int[] r, int left, int right) {
			  
			    int i = 0, j = 0, k = 0;
			    while (i < left && j < right) {
			        if (l[i] <= r[j]) {
			            a[k++] = l[i++];
			            count++;
			        }
			        else {
			            a[k++] = r[j++];
			            count++;
			        }
			    }
			    while (i < left) {
			        a[k++] = l[i++];
			        count++;
			    }
			    while (j < right) {
			        a[k++] = r[j++];
			        count++;
			    }
			}
	
	static int[] generateInput(int n) {
		Random r = new Random();
		int[] array = new int[n];
		for (int i = 0; i < n; i++) {
			array[i] = r.nextInt();	
		}
		return array;
	}

}
