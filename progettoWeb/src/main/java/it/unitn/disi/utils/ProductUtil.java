/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.utils;

import it.unitn.disi.entities.Product;

/**
 *
 * @author root
 */
public class ProductUtil {

    public static void mergeSortPrice(Product[] inputArray) {
        int size = inputArray.length;
        if (size < 2) {
            return;
        }
        int mid = size / 2;
        int leftSize = mid;
        int rightSize = size - mid;
        Product[] left = new Product[leftSize];
        Product[] right = new Product[rightSize];
        for (int i = 0; i < mid; i++) {
            left[i] = inputArray[i];

        }
        for (int i = mid; i < size; i++) {
            right[i - mid] = inputArray[i];
        }
        mergeSortPrice(left);
        mergeSortPrice(right);
        mergePrice(left, right, inputArray);
    }

    private static void mergePrice(Product[] left, Product[] right, Product[] arr) {
        int leftSize = left.length;
        int rightSize = right.length;
        int i = 0, j = 0, k = 0;
        while (i < leftSize && j < rightSize) {
            if (left[i].getShopProduct().getPrice() <= right[j].getShopProduct().getPrice()) {
                arr[k] = left[i];
                i++;
                k++;
            } else {
                arr[k] = right[j];
                k++;
                j++;
            }
        }
        while (i < leftSize) {
            arr[k] = left[i];
            k++;
            i++;
        }
        while (j < rightSize) {
            arr[k] = right[j];
            k++;
            j++;
        }
    }

    public static void mergeSortReview(Product[] inputArray) {
        int size = inputArray.length;
        if (size < 2) {
            return;
        }
        int mid = size / 2;
        int leftSize = mid;
        int rightSize = size - mid;
        Product[] left = new Product[leftSize];
        Product[] right = new Product[rightSize];
        for (int i = 0; i < mid; i++) {
            left[i] = inputArray[i];

        }
        for (int i = mid; i < size; i++) {
            right[i - mid] = inputArray[i];
        }
        mergeSortReview(left);
        mergeSortReview(right);
        mergeReview(left, right, inputArray);
    }

    private static void mergeReview(Product[] left, Product[] right, Product[] arr) {
        int leftSize = left.length;
        int rightSize = right.length;
        int i = 0, j = 0, k = 0;
        while (i < leftSize && j < rightSize) {
            if (left[i].getAverageReview() <= right[j].getAverageReview()) {
                arr[k] = left[i];
                i++;
                k++;
            } else {
                arr[k] = right[j];
                k++;
                j++;
            }
        }
        while (i < leftSize) {
            arr[k] = left[i];
            k++;
            i++;
        }
        while (j < rightSize) {
            arr[k] = right[j];
            k++;
            j++;
        }
    }

    public static void rovesciaArray(Product[] a) {
        Product temp;

        for (int i = 0; i < a.length / 2; i++) {
            temp = a[i];
            a[i] = a[a.length - 1 - i];
            a[a.length - 1 - i] = temp;
        }

    }
}
