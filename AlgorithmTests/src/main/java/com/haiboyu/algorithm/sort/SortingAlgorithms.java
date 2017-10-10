package com.haiboyu.algorithm.sort;

import java.util.Arrays;

/**
 * This class includes all sorting related algorithms
 * @author Haibo Yu on 10/08/2017.
 */
public class SortingAlgorithms {

    public static void main(String[] args) {

        int[] srcArray = new int[]{3,8,5,9,12,10,1,4};
        SortingAlgorithms sa = new SortingAlgorithms();
        sa.selectionSort(srcArray);
        System.out.println(Arrays.toString(srcArray));
    }

    /**
     * Sort the array using bubble sort algorithm
     * @param srcArray The source array.
     */
    public void bubbleSort(int[] srcArray){
        int length = srcArray.length;
        for(int i=0; i<length-1; i++){
            for(int j=length-1; j>i;j--){
                if(srcArray[j] < srcArray[j-1]){
                    int temp = srcArray[j];
                    srcArray[j] = srcArray[j-1];
                    srcArray[j-1] = temp;
                }              
            }
        }
    }

    /**
     * Sort the array using selection sort algorithm
     * @param srcArray The source array.
     */
    public void selectionSort(int[] srcArray){
        int length = srcArray.length;
        for(int i=0; i<length-1; i++){
            int currentMinIndex = i;
            for(int j=i+1; j<length;j++){
                if(srcArray[j] < srcArray[currentMinIndex]){
                    currentMinIndex = j;
                }
            }
            if(i != currentMinIndex){
                int temp = srcArray[i];
                srcArray[i] = srcArray[currentMinIndex];
                srcArray[currentMinIndex] = temp;
            }
        }
    }

    /**
     * Sort the array using insertion sort algorithm
     * @param srcArray The source array.
     */
    public void insertionSort(int[] srcArray){
        int length = srcArray.length;
        for(int i=1; i<length; i++){
            int currentMinIndex = i;
            for(int j=i+1; j<length;j++){
                if(srcArray[j] < srcArray[currentMinIndex]){
                    currentMinIndex = j;
                }
            }
            if(i != currentMinIndex){
                int temp = srcArray[i];
                srcArray[i] = srcArray[currentMinIndex];
                srcArray[currentMinIndex] = temp;
            }
        }
    }


}
