
package com.example.vehiclemanagementCA;

import java.util.*;

/*
Class to perform Bubble Sort and Quick Sort - Q1 and Q3
*/

public class SortVehiclesData<ElementType> extends ArrayList<ElementType> {
    
    //adding specific Vehicle bubbleSort method since for the moment, the overridden compareTo method is not
    //being accessed in the generic bubbleSort
    public void bubbleSort(ArrayList<Vehicle> vehicleAL, int end) {

        int i, j;

        for (i = 0; i < end; i++) {
            //nested loop ignores the last element(s) since this is known to be sorted after the first pass
            //the number of loops already completed are the number of loops subtracted from the last element
            for (j = 0; j < end - 1 - i; j++) {

                //store elements in temp variables
                Vehicle firstVehicle = vehicleAL.get(j);
                Vehicle secondVehicle = vehicleAL.get(j + 1);

                //compare the vehicle elements using the compareTo override in the Vehicle class
                //if the element at current position is greater than next position, swap the elements
                if (vehicleAL.get(j).compareTo(vehicleAL.get(j + 1)) > 0) {

                    //remove firstVehicle from first position, insert secondVehicle into first position
                    vehicleAL.remove(j);
                    vehicleAL.add(j, secondVehicle);

                    //remove secondVehicle from second position, insert firstVehicle into second position
                    vehicleAL.remove(j + 1);
                    vehicleAL.add(j + 1, firstVehicle);
                }
            }
        }
    }
    
    public void quickSort(ArrayList<Vehicle> vehicleAL, int start, int end) {
              
        int pivotIndex;

        if (start < end) {

            /*
                select pivot and re-arrange elements in two partitions such as
                all array[start … p-1] are less than pivot = array [p] and
                all array[p+1 … end] are >= pivot
             */
            pivotIndex = partition(vehicleAL, start, end);

            // sort first partition of the array (from start to pivot_index-1)
            quickSort(vehicleAL, start, pivotIndex - 1);

            //sort second partition of the array
            quickSort(vehicleAL, pivotIndex + 1, end);

        } else {
            //pass, division completed, base case achieved
        }
    }

    private int partition(ArrayList<Vehicle> vehicleAL, int start, int end) {
        int up, down, mid;
        Vehicle pivot;

        //calculate mid index
        mid = (start+end)/2;
        
        //if the element at the start index is smaller or equal to mid index, pass
        //since we are not implementing a 3 way partition for now, not doing anything else if the elements are equal
        //this approach is still flawed in the case that all three elements are equal
        if(vehicleAL.get(start).compareTo(vehicleAL.get(mid))<=0){
            //pass
        }
        //if the element at the start index is greater than mid index, swap the elements
        else if(vehicleAL.get(start).compareTo(vehicleAL.get(mid))>0){
            Vehicle startVehicle = vehicleAL.get(start);
            vehicleAL.set(start, vehicleAL.get(mid));
            vehicleAL.set(mid, startVehicle);
        }
        
        //if the element at the mid index is smaller than or equal to end index, pass
        if (vehicleAL.get(mid).compareTo(vehicleAL.get(end)) <= 0) {
            //pass
        } 
        //if the element at the mid index is greater than the element at end index, swap the elements
        else if (vehicleAL.get(mid).compareTo(vehicleAL.get(end)) > 0) {
            Vehicle endVehicle = vehicleAL.get(end);
            vehicleAL.set(end, vehicleAL.get(mid));
            vehicleAL.set(mid, endVehicle);
        }
        
        // select the mid element as pivot
        pivot = vehicleAL.get(mid);

        up = start;
        down = end;

        // as long as UP and DOWN indexes did not pass each other
        while (up < down) {
            // increment UP index until found first element higher than pivot
            while (up < end && (vehicleAL.get(up)).compareTo(pivot) <= 0) {
                up++;
            }

            // decrement DOWN until found first element smaller than  pivot
            while (down > start && (vehicleAL.get(down).compareTo(pivot) >= 0)) {
                down--;
            }

            // if UP and DOWN indexes did not pass each other
            if (up < down) {
                Vehicle elementUp = vehicleAL.get(up);
                //swap the two elements found
                vehicleAL.set(up, vehicleAL.get(down));
                vehicleAL.set(down, elementUp);
            }
        }

        // UP and DOWN indexes have passed each other, so swap pivot with the element on DOWN position
        vehicleAL.set(start, vehicleAL.get(down));
        vehicleAL.set(down, pivot);
        return down;
    }
    
    /*
    public void bubbleSort(int end) {

        int i, j;
        Comparable currentElement, nextElement;

        for (i = 0; i < end; i++) {
            //nested loop ignores the last element since this is known to be sorted after the first pass
            for (j = 0; j < end - 1 - i; j++) {
                
                //get the elements at the current position and the next position
                currentElement = (Comparable) get(j);
                nextElement = (Comparable) get(j+1);

                //if the element at current position is greater than next position, swap the elements
                if (currentElement.compareTo(nextElement) > 0) {

                    //store elements in temp variables
                    //TODO: there has got to be a more efficient way to do this
                    ElementType firstElement = get(j);
                    ElementType secondElement = get(j + 1);

                    //remove firstElement from first position, insert secondElement into first position
                    remove(j);
                    add(j, secondElement);

                    //remove secondElement from second position, insert firstElement into second position
                    remove(j + 1);
                    add(j + 1, firstElement);
                }
            }
        }
    }
    */
}
