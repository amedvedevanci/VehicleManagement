
package com.example.vehiclemanagementCA;

import java.lang.annotation.ElementType;
import java.util.ArrayList;
import java.util.Scanner;

public class SearchVehiclesData {
    
    
    
    //binary search - Q4

    String resultString;

    public String binarySearchStrings(ArrayList<Vehicle> vehicleAL, String searchString, int low, int high) {

        //get the mid value
        int mid = (low + high) / 2;
        String currentName = vehicleAL.get(mid).getsName().toLowerCase();
        
        //print the name for testing purposes
        //System.out.println(currentName);

        //return true if it's equal to target value, aka base case
        if (searchString.equalsIgnoreCase(currentName)) {

            //find the first instance of the element
            //iterate from the current index towards current startIndex
            //technically these could be added to the resultString as we go
            for (int i = mid; i >= low; i--) {
                if(vehicleAL.get(i).getsName().equalsIgnoreCase(searchString)){
                    resultString += (vehicleAL.get(i).toString()+"\r\n");
                }
                //Since the list is sorted, when the searchString no longer equals currentName, we're done here
                else{
                    i=low-1;
                }
            }
            //do the same from current index to current endIndex
            for (int i = mid; i < high; i++) {
                if(vehicleAL.get(i).getsName().equalsIgnoreCase(searchString)){
                    resultString += (vehicleAL.get(i).toString()+"\r\n");
                }
                //Since the list is sorted, when the searchString no longer equals currentName, we're done here
                else{
                    i=high;
                }
            }
            
            return resultString;
            
        }
        
        //search for partial match: If the search string is present either at the beginning regardless of case,
        //ask the user to confirm if the current name is matching. Call startsWith function and return the resultString
        else if (currentName.startsWith(searchString.toLowerCase())) {
            resultString = startsWith(vehicleAL, searchString, currentName, low, mid, high);
            return resultString;
        }
        //check the base case: if start is greater than or equal to end
        else if (low >= high) {
            //return statement
            return "Name was not found";
        } else if (searchString.toLowerCase().compareTo(currentName) < 0) {
            //recursive call: start - middle, drop second half
            return binarySearchStrings(vehicleAL, searchString, low, mid - 1);
        } else {
            //recursive call: middle - end, drop first half
            return binarySearchStrings(vehicleAL, searchString, mid + 1, high);
        }

    }
    
    public String startsWith(ArrayList<Vehicle> vehicleAL, String searchString, String currentName, int low, int mid, int high){
        
        System.out.println("Partial match found: " + currentName);
        System.out.println("Is this the correct vehicle? Type Y or N. To quit, type X");

        Scanner sc = new Scanner(System.in);
        String ans = sc.next();

        if(ans.equalsIgnoreCase("X")){
            System.exit(0);
            return "";
        }
        else if (ans.equalsIgnoreCase("Y")) {

            String realSearchString = currentName.toLowerCase();
            
            //find the first instance of the element
            //iterate from the current index towards current startIndex
            //technically these could be added to the resultString as we go
            for (int i = mid; i >= low; i--) {
                if (realSearchString.equalsIgnoreCase(vehicleAL.get(i).getsName())) {
                    resultString += (vehicleAL.get(i).toString() + "\r\n");
                } //Since the list is sorted, when the searchString no longer equals currentName, we're done here
                else {
                    i = low-1;
                }
            }
            //do the same from current index to current endIndex
            for (int i = mid; i < high; i++) {
                if (realSearchString.equalsIgnoreCase(vehicleAL.get(i).getsName())) {
                    resultString += (vehicleAL.get(i).toString() + "\r\n");
                } //Since the list is sorted, when the searchString no longer equals currentName, we're done here
                else {
                    i = high;
                }
            }

            return resultString;
        } 
        else if (ans.equalsIgnoreCase("N")) {
            /*
                Otherwise, all we know is that the current name starts with the searchString
                But is not the correct name.
                So can iterate in a similar fashion, this time looking for elements that start with searchString
                while ignoring results that are equal to the currentName since that's not it.
            
                it's not mid, so start backwards iteration at mid-1 and forwards iteration at mid+1
             */

            for (int i = mid-1; i >= low; i--) {
                if (currentName.equalsIgnoreCase(vehicleAL.get(i).getsName())) {
                    //do nothing
                } else if (vehicleAL.get(i).getsName().toLowerCase().startsWith(searchString.toLowerCase())) {
                    //can pass in mid-1 as new mid
                    return startsWith(vehicleAL, searchString, vehicleAL.get(i).getsName(), low, i, high);
                }
                //otherwise the name has not been found, however there's another iteration to go
                else{
                    break;
                }
            }
            //do the same from current index to current endIndex
            for (int i = mid+1; i < high; i++) {
                if (currentName.equalsIgnoreCase(vehicleAL.get(i).getsName())) {
                    //pass
                }
                else if (vehicleAL.get(i).getsName().toLowerCase().startsWith(searchString.toLowerCase())) {
                    //can pass in mid+1 as new mid
                    return startsWith(vehicleAL, searchString, vehicleAL.get(i).getsName(), low, i, high);
                }
                else {
                    resultString = "Name was not found";
                }
            }
            return resultString;
            

        } 
        else{
            return "Answer was invalid";
        }
    }

}

