
package com.example.vehiclemanagementCA;


import java.io.*;
import java.util.*;

/*
Class to read the data from vehicles.csv into an ArrayList<Vehicle>
*/

public class ReadVehiclesData {

    //vars
    private ArrayList<Vehicle> val = new ArrayList<>();
    private Vehicle vehicle = new Vehicle();
    
    /*
    This function reads the vehicles.csv file and creates Vehicle objects, which are then stored in an ArrayList<Vehicle>
    */
    public void readVehiclesData() throws IOException {
        //parsing and reading the CSV file data into the object array
        File directory = new File("./");
        //store file name as string to easily modify it if needed
        String vehiclesFileName = "//vehicles.csv";
        String fileName = directory.getAbsolutePath() + vehiclesFileName;

        
        //read the file and retrieve data, otherwise throw file not found exception
        //store in ArrayList to allow it to scale
        try (Scanner scanner = new Scanner(new File(fileName))) {
            
            String sGetData;
            
            //skip the header in the CSV file
            scanner.nextLine();

            //iterate through the remaining lines, split string and pass the split strings to their corresponding set methods
            while (scanner.hasNextLine()) {
                sGetData = scanner.nextLine();
                String[] data = sGetData.split(",");

                vehicle.setiId(Integer.parseInt(data[0]));
                vehicle.setsName(data[1]);
                vehicle.setsFuel(data[2]);
                vehicle.setsLocation(data[3]);
                vehicle.setiPostcode(Integer.parseInt(data[4]));
                vehicle.setlValue(Long.parseLong(data[5]));

                //add to arraylist, create new vehicle object for next loop
                val.add(vehicle);
                vehicle = new Vehicle();
            }
            //closes the scanner
        } //if the file is not found, display an error and exit the program with code -1
        catch (FileNotFoundException notFoundError) {
            System.out.println("Error - vehicles.csv not found\r\n" + notFoundError.getMessage());
            System.exit(-1);
        }

        // we can print details due to overridden toString method in the class below
        //System.out.println(val.get(0).toString());
        //System.out.println(val.get(1).toString());
        
    }
    
    //getter for ArrayList<Vehicle>
    public ArrayList<Vehicle> getVehicles(){
        return val;
    }
    
    //print a sample of the ArrayList
    //TODO: select number of records to print
    public void printSample(){
        for (int i = 0; i < 100; i++) {
            System.out.println(val.get(i).toString());
        }
        System.out.println("........................");
        for (int i = 100; i < 200; i++) {
            System.out.println(val.get(i).toString());
        }
    }
    
    //returns comparator from Vehicle class to allow comparing vehicles by name
    //easier to get it from here than Vehicle
    public Comparator<Vehicle> nameComparator(){
        return vehicle.compareNames;
    }
}

