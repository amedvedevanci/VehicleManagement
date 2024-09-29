package com.example.vehiclemanagementCA;

import java.io.*;
import java.util.*;


/*
Part 2 - Q5 and Q6
Class to create a new vehicle record.
Throws VehicleException on invalid input
*/

public class AddVehicle {
    
    public static void main(String[] args) throws IOException {
        
        //vars - vehicle details
        int iId;
        String sName="";
        String sFuel="";
        String sLocation="";
        int iPostcode=-1;
        long lValue=-1;
        
        boolean invalidInput;
        
        //read vehicles.csv
        ReadVehiclesData vdata = new ReadVehiclesData();
        vdata.readVehiclesData();
        
        //fetch the Vehicles ArrayList
        ArrayList<Vehicle> vehicleAL = vdata.getVehicles();
        Scanner sc = new Scanner(System.in);
        
        iId = computeiId();
        //System.out.println(iId);
        
        //try to take name input. Throw an exception if name is blank or contains only numbers and non-word characters
        do{
            invalidInput = false;
            
            try{
                System.out.println("Please enter vehicle name: ");
                sName = sc.nextLine();
                if(sName.isBlank() || sName.equals("")){
                    throw new VehicleException();
                }
                else if(sName.matches("^[0-9\\W]+$")){
                    throw new VehicleException("At least one letter would be nice");
                }
                else{
                    System.out.println(sName+" was added");
                }
            }
            catch(VehicleException e){
                invalidInput = true;
                System.out.println(e.getMessage());
            }
            catch(InputMismatchException e){
                invalidInput = true;
                System.out.println("Please enter a valid string.");
            }
        }while(invalidInput);
        
        //try to take fuel input. Throw an exception if name is blank or contains only numbers and non-word characters
        //or if it does not match the fuel types provided in the set. Set is used because it cannot contain duplicate values
        Set fuelSet = getFuelSet();
        do {
            invalidInput = false;

            try {
                System.out.println("Please enter vehicle fuel type: ");
                sFuel = sc.nextLine();
                if (sFuel.isBlank() || sFuel.equals("")) {
                    throw new VehicleException();
                } else if (sFuel.matches("^[0-9]+$")) {
                    throw new VehicleException("At least one letter would be nice");
                } else if(!fuelSet.contains(sFuel)){
                    throw new VehicleException("Fuel type not recognised. Please enter one of the following without spaces or other characters:\r\n"+printFuelSet());
                } else {
                    System.out.println(sFuel + " was added");
                }
            } catch (VehicleException e) {
                invalidInput = true;
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                invalidInput = true;
                System.out.println("Please enter a valid string.");
            }
        } while (invalidInput);
        
        //try to take location input. Throw an exception if name is blank or contains only numbers and non-word characters
        do {
            invalidInput = false;

            try {
                System.out.println("Please enter vehicle location: ");
                sLocation = sc.nextLine();
                if (sLocation.isBlank() || sLocation.equals("")) {
                    throw new VehicleException();
                } else if (sLocation.matches("^[0-9]+$")) {
                    throw new VehicleException("At least one letter would be nice");
                } else {
                    System.out.println(sLocation + " was added");
                }
            } catch (VehicleException e) {
                invalidInput = true;
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                invalidInput = true;
                System.out.println("Please enter a valid string.");
            }
        } while (invalidInput);
        
        //postcode. Throws an exception if non-numeric or negative
        do {
            invalidInput = false;
            try {
                Scanner tempsc = new Scanner(System.in);
                System.out.println("Please enter vehicle postcode: ");
                iPostcode = tempsc.nextInt();
                if(iPostcode<0){
                    throw new VehicleException("Positive numbers only please");
                }
            }
            catch (VehicleException e) {
                invalidInput = true;
                System.out.println(e.getMessage());
            }
            catch (InputMismatchException e) {
                invalidInput = true;
                System.out.println("Please enter a numeric value.");
            }
        } while (invalidInput);
        
        //value. Throws an exception if non-numeric or negative
        do {
            invalidInput = false;
            try {
                Scanner tempsc = new Scanner(System.in);
                System.out.println("Please enter vehicle value: ");
                lValue = tempsc.nextLong();
                if (lValue < 0) {
                    throw new VehicleException("You're paying me to buy a car?");
                }
            } catch (VehicleException e) {
                invalidInput = true;
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                invalidInput = true;
                System.out.println("Please enter a numeric value.");
            }
        } while (invalidInput);
        
        
        //create new Vehicle object with the parameters provided, add it to ArrayList
        Vehicle newVehicle = new Vehicle(iId,sName,sFuel,sLocation,iPostcode,lValue);
        vehicleAL.add(newVehicle);
        
        //print record
        System.out.println(vehicleAL.get(iId-1).toString());
        //print elements as CSV
        String CSVString = newVehicle.createString();
        System.out.println(CSVString);
        
        
        //append the CSVString to vehicles.csv
        //this would be better done somehow within ReadVehiclesData
        String fileName = "vehicles.csv";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true))) {
            bw.append("\n"+CSVString);
            System.out.println("Vehicle "+iId+" was appended to " + fileName);
        } catch (IOException e) { //IOException should contain FileNotFound
            e.printStackTrace();
        }
    }
    
    //method to compute iId from size of current vehicles data
    public static int computeiId() throws IOException{
        ReadVehiclesData vdata = new ReadVehiclesData();
        vdata.readVehiclesData();
        ArrayList<Vehicle> vehicleAL = vdata.getVehicles();
        
        int iId = vehicleAL.size()+1;
        return iId;
    }
    
    //creates and gets fuelSet. This can be expanded as needed
    public static Set getFuelSet(){
        Set<String> fuelSet = new HashSet<>();
        fuelSet.add("Diesel");
        fuelSet.add("Electric");
        fuelSet.add("Gasoline");
        fuelSet.add("Hybrid");
        fuelSet.add("Petrol");
        
        return fuelSet;
    }
    
    public static String printFuelSet(){
        Set fuelSet = getFuelSet();
        Object[] fuelArray = fuelSet.toArray();

        String fuelString="\r\n";
        for (int i = 0; i < fuelArray.length; i++) {
            fuelString += fuelArray[i]+"\r\n";
        }
        return fuelString;
    }
}
