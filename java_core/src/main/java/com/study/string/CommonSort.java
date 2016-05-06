package com.study.string;

import com.sun.tools.javac.util.ArrayUtils;
import com.sun.tools.javac.util.StringUtils;

import java.util.Arrays;

/**
 * Created by wangwei on 16/4/28.
 */
public class CommonSort {

    public static void main(String[] args) {
        int[] array = {3,5,74,2,674,2,4,78,95,3};
//        bubbleSort(array);
//        quickSort(array, 0, array.length - 1);
//        selectSort(array);
//        insertSort(array);
        System.out.println(Arrays.toString(array));
        mergeSoft(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));

    }

    /**
     * 冒泡排序：原理大的放到后面
     * @param array
     */
    public static void bubbleSort(int[] array) {
        int temp;
        for (int i = 0; i < array.length - 1; i ++) {
            for (int j = i + 1; j < array.length; j ++) {
                if (array[i] > array [j]) {
                    temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    /**
     * 快速排序
     * 原理：选中其中的一个元素，作为基准，从两边开始比较，大的放后面，小的放前面
     * 当i==j的时候，左侧的都比基数小，右侧的都比基数大，然后左侧的单独比较排序，右侧的单独比较排序
     * @param array
     */
    public static void quickSort(int[] array, int start, int end) {
        if (start < end) {
            int base = array[start];
            System.out.println(base);
            int i = start, j = end;
            int temp;
            while (i <= j) {
                while (array[i] < base && i < end) {
                    i ++;
                }
                while (array[j] > base && j > start) {
                    j --;
                }
                if (i <= j) {
                    System.out.println("i:" + i + ",j:" + j);
                    temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                    i ++;
                    j --;
                }
                System.out.println(Arrays.toString(array));
            }
            System.out.println("---" + Arrays.toString(array));
            if (start < j) {
                quickSort(array, start, j);
            }
            if (end > i) {
                quickSort(array, i, end);
            }
        }
    }

    /**
     * 选择排序
     * 原理：从头往后一直寻找最小的元素然后与指针位置替换
     * @param array
     */
    public static void selectSort(int[] array) {
        int size = array.length;
        int temp;
        for (int i = 0; i < size; i ++) {
            int k = i;
            //找到最小的元素位置k
            for (int j = size - 1; j > i; j --) {
                if (array[j] < array[k]) {
                    k = j;
                }
            }
            temp = array[i];
            array[i] = array[k];
            array[k] = temp;
        }

    }


    /**
     * 插入排序
     * 原理：指针前面的全部已经排好顺序
     * 拿出一个数，然后从指针位置往前进行比较，直到大于某个数的时候，替换
     * @param array
     */
    public static void insertSort(int[] array) {
        int size = array.length;
        int temp, j;
        for (int i = 1; i < size; i ++) {
            temp = array[i];
            for (j = i; j > 0 && temp < array[j-1]; j --) {
                array[j] = array[j-1];
            }
            array[j] = temp;
        }
    }


    /**
     * 归并排序
     * 原理：将数组从中间一分为2，然后又合并起来，不断递归，最后成为两个元素各1个的数组排序。
     * @param array
     */
    public static void mergeSoft(int[] array, int start, int end) {
        if (start < end) {
            int index = (end + start) / 2;
            mergeSoft(array, start, index);
            mergeSoft(array, index + 1, end);
            merge(array, start, index, end);
        }
    }
    public static void merge(int[] array, int start, int index, int end) {
        int[] tempArray = new int[array.length];
        int i = start;
        int j = index + 1;
        int tempIndex = start;
        while (i <= index && j <= end) {
            if (array[i] < array[j]) {
                tempArray[tempIndex++] = array[i++];
            } else {
                tempArray[tempIndex++] = array[j++];
            }
        }
        while (i <= index) {
            tempArray[tempIndex++] = array[i++];
        }
        while (j <= end) {
            tempArray[tempIndex++] = array[j++];
        }
        for (int k = start; k <= end; k ++) {
            array[k] = tempArray[k];
        }
    }

}
