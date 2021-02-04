package com.desafio.searchEngine.utils;

import java.util.Comparator;
import java.util.List;

public class Sorter {

    public static <T> List<T> bubbleSortAsc(List<T> list, Comparator<T> comparator) {
        List<T> sortList = list;
        for (int i = 0; i < sortList.size() - 1; i++) {
            for (int j = 0; j < sortList.size() - i - 1; j++) {
                if (comparator.compare(sortList.get(i), sortList.get(i + 1)) <= 0) {
                    T aux = sortList.get(i);
                    sortList.set(i, sortList.get(i + 1));
                    sortList.set(i + 1, aux);
                }
            }
        }

        return sortList;
    }

    public static <T> List<T> bubbleSortDesc(List<T> list, Comparator<T> comparator) {
        List<T> sortList = list;
        for (int i = 0; i < sortList.size() - 1; i++) {
            for (int j = 0; j < sortList.size() - i - 1; j++) {
                if (comparator.compare(sortList.get(i), sortList.get(i + 1)) < 0) {
                    T aux = sortList.get(i);
                    sortList.set(i, sortList.get(i + 1));
                    sortList.set(i + 1, aux);
                }
            }
        }

        return sortList;
    }
}
