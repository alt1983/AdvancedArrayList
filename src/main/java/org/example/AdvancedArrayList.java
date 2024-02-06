package org.example;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author
 * Advanced realization of ArrayList with merge sorting.
 */
public class AdvancedArrayList<T> extends AbstractList<T> {
    private int size = 0;
    private static final int CAPACITY = 16;
    private Object[] elements;

    public AdvancedArrayList() {
        elements = new Object[CAPACITY];
    }

    public AdvancedArrayList(int capacity) {
        elements = new Object[capacity];
    }

    /**
     * Adding element to AdvancedArrayList
     * boolean add(T e)
     * @param element - adding object
     * @return true if adding with success
     */
    @Override
    public boolean add(T element) {
        if((size > 0)&&(size == elements.length)) increaseCapacity();
        elements[size] = element;
        size++;
        return true;
    }

    /**
     * Adding element to AdvancedArrayList
     * void add(int index, T element)
     * @exception IndexOutOfBoundsException if there is no such index in AdvancedArrayList
     * @param index - index of element of AdvancedArrayList to add
     * @param element - adding object
     * @return void
     */
    @Override
    public void add(int index, T element) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size " + index);
        }
        if((size > 0)&&(size == elements.length)) increaseCapacity();
        for (int i = size - 1; i >= index; i--) {
            elements[i + 1] = elements[i];
        }
        elements[index] = element;
        size++;
    }

    /**
     * Getting element from AdvancedArrayList by index
     * T get(int index)
     * @exception IndexOutOfBoundsException if there is no such index in AdvancedArrayList
     * @param index - index of element of AdvancedArrayList to get
     * @return object from AdvancedArrayList
     */
    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size " + index);
        }
        return (T) elements[index];
    }

    /**
     * Setting element in AdvancedArrayList by index
     * T set(int index, T element)
     * @exception IndexOutOfBoundsException if there is no such index in AdvancedArrayList
     * @param index - index of element of AdvancedArrayList to set value
     * @param element - setting new object
     * @return old object with index removed by new object
     */
    @Override
    public T set(int index, T element) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size " + index);
        }
        T oldElement = get(index);
        add(index, element);
        return oldElement;
    }

    /**
     * Removing element from AdvancedArrayList by index
     * T remove(int index)
     * @exception IndexOutOfBoundsException if there is no such index in AdvancedArrayList
     * @param index - index of element in AdvancedArrayList to remove
     * @return removed object
     */
    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size " + index);
        }
        Object element = elements[index];
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        size--;
        return (T) element;
    }

    /**
     * Removing element from AdvancedArrayList by Object with first finding
     * boolean remove(Object o)
     * @exception NullPointerException if there is null in param o
     * @param o - object in AdvancedArrayList to be removed
     * @return true if object was found and removed
     */
    @Override
    public boolean remove(Object o) {
        if (o == null) {
            throw new NullPointerException("Invalid agrument");
        }
        for (int i = 0; i < size; i++) {
            if((o).equals(elements[i])){
                for (int j = i; j < size - 1; j++) {
                    elements[j] = elements[j + 1];
                }
                size--;
                return true;
            }
        }
        return false;
    }

    /**
     * Getting size of AdvancedArrayList
     * int size()
     * @return size of AdvancedArrayList
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Clearing of AdvancedArrayList
     * void clear()
     * @return void
     */
    @Override
    public void clear(){
        elements = new Object[CAPACITY];
        size = 0;
    }

    /**
     * Increasing capacity of AdvancedArrayList
     * void increaseCapacity()
     * @return void
     */
    private void increaseCapacity() {
        int newSize = elements.length * 2;
        elements = Arrays.copyOf(elements, newSize);
    }

    /**
     * Splitting function in merge sorting
     * Object[] split(Object[] m, Comparator<? super T> c)
     * @param m - massive of sorting elements
     * @param c - comparator function
     * @return massive of sorting elements
     */
    private Object[] split(Object[] m, Comparator<? super T> c) {
        if(m.length == 1) return m;
        if(m.length == 2) return merge(new Object[]{m[0]},new Object[]{m[1]},c);
        Object[] left = new Object[m.length/2];
        Object[] right = new Object[m.length - left.length];
        for(int i = 0;i<left.length;i++){
            left[i] = m[i];
        }
        for(int i = 0;i<right.length;i++){
            right[i] = m[i+left.length];
        }
        return merge(split(left,c),split(right,c),c);
    }

    /**
     * Merging function in merge sorting
     * Object[] merge(Object[] a1, Object[] a2, Comparator<? super T> c)
     * @param a1 - first part of massive of sorting elements
     * @param a2 - second part of massive of sorting elements
     * @param c - comparator function
     * @return massive of sorting elements
     */
    private Object[] merge(Object[] a1, Object[] a2, Comparator<? super T> c) {
        Object[] res = new Object[a1.length + a2.length];
        int n = a1.length;
        int m = a2.length;
        int i = 0,j =0,k=0;
        while(i<n && j<m){
            if(c.compare((T)a1[i],(T)a2[j])<=0){
                res[k] = a1[i];
                i++;
            }else{
                res[k] = a2[j];
                j++;
            }
            k++;
        }
        while(i<n){
            res[k] = a1[i];
            i++;
            k++;
        }
        while(j<m){
            res[k] = a2[j];
            j++;
            k++;
        }
        return res;
    }

    /**
     * Merge sorting of AdvancedArrayList
     * void sort(Comparator<? super T> c)
     * @exception IllegalArgumentException if there is no elements to sort
     * @param c - comparator with function of sorting
     * @return void
     */
    @Override
    public void sort(Comparator<? super T> c){
        if(size == 0) throw new IllegalArgumentException("There is no data");
       Object[] valued = new Object[size];
       for(int i=0;i<size;i++){
           valued[i] = elements[i];
       }
       Object[] sorted = split(valued,c);
        for(int i=0;i<size;i++){
            elements[i] = sorted[i];
        }
    }

}
