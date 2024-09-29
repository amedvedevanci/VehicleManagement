package com.example.vehiclemanagementCA;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/*
    Tester App to help run SortVehiclesData (bubble sort and quick sort) and SearchVehiclesData (binary search) for Part 1
 */
public class TesterApp {
    public static void main(String[] args) throws IOException {
        //read vehicles.csv
        ReadVehiclesData vdata = new ReadVehiclesData();
        vdata.readVehiclesData();
        
        //fetch the Vehicles ArrayList
        ArrayList<Vehicle> vehicleAL = vdata.getVehicles();
        Scanner sc = new Scanner(System.in);
        
        /*
        //print a sample size of the ArrayList (currently 10 records)
        System.out.println("Printing first 200 elements of initial list:");
        vdata.printSample();
        */
        
        SortVehiclesData<Vehicle> sortVehicles = new SortVehiclesData<>();
        
        //QuickSort - Q3
        //first, shuffling the collection to try and avoid worst-case of sorted collection
        //https://docs.oracle.com/javase/8/docs/api/java/util/Collections.html#shuffle-java.util.List-
        //runs in linear time - O(N) - not really ideal.
        
        /*
        //change this to sort a different number of records
        int numberOfRecords = 100;
        
        long lStartTime = System.currentTimeMillis();
        //Collections.shuffle(vehicleAL);
        sortVehicles.quickSort(vehicleAL, 0, numberOfRecords);
        long lEndTime = System.currentTimeMillis();
        long lDuration = lEndTime - lStartTime;
        
        System.out.println("Quick Sort of " + numberOfRecords + " elements took " + lDuration + " milliseconds");
        */
        
        
        /*print sample after quicksort
        System.out.println("\r\n_______________________\r\nAfter quick sort of first 100 by postcode:");
        vdata.printSample();
        */
        
        
        // Question 2 - Bubble sort analysis
        /*
        //change this to sort a different number of records
        int numberOfRecords = 100;
        
        long lStartTime = System.currentTimeMillis();
        sortVehicles.bubbleSort(vehicleAL,numberOfRecords);
        long lEndTime = System.currentTimeMillis();
        long lDuration = lEndTime - lStartTime;
        
        System.out.println("Bubble Sort of " + numberOfRecords + " elements took " + lDuration + " milliseconds");
        */
        
        /* print sample after bubble sort
        
        System.out.println("\r\n_______________________\r\nAfter bubble sort of first 100 by postcode:");
        vdata.printSample();
        */
        
        //Q4 - binary Search
        //first sorting the collection on the name column
        Collections.sort(vehicleAL, vdata.nameComparator());
        //vdata.printSample();
        //System.out.println(vehicleAL.get(0).getsName().compareTo(vehicleAL.get(1).getsName()));
        
        SearchVehiclesData searchVehicles = new SearchVehiclesData();
        System.out.println("Enter the name of the car you would like to search for:");
        String searchString = sc.nextLine();
        String searchResult = searchVehicles.binarySearchStrings(vehicleAL, searchString, 0, vehicleAL.size());
        System.out.println(searchResult);
        
    }
}
