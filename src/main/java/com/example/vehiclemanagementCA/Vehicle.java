package com.example.vehiclemanagementCA;


import java.util.Comparator;

/*
class to define Vehicle object
*/

public class Vehicle implements Comparable<Object> {

    private int iId;
    private String sName;
    private String sFuel;
    private String sLocation;
    private int iPostcode;
    private long lValue;

    //constructor
    public Vehicle() {
    }

    // constructor
    public Vehicle(int iInId, String sInName, String sInFuel, String sInLocation, int iInPostcode, long lInValue) {
        this.iId = iInId;
        this.sName = sInName;
        this.sFuel = sInFuel;
        this.sLocation = sInLocation;
        this.iPostcode = iInPostcode;
        this.lValue = lInValue;
    }

    // the objects can be compared when sorting/searching
    @Override
    public int compareTo(Object obj) {

        /*
		Edit this section so it compares the appropriate
		column you wish to sort by
         */
        Vehicle myVehicles = (Vehicle) obj;
        
        
        if(iPostcode - (myVehicles.getiPostcode())!=0){
            return iPostcode - (myVehicles.getiPostcode());
        }
        else{
            return iId - (myVehicles.getiId());
        }
    }
    
    //Comparator for name column
    //to compare on other columns, modify this or add another comparator
    public Comparator<Vehicle> compareNames = new Comparator<Vehicle>() {
        @Override
        public int compare(Vehicle v1, Vehicle v2) {
            String vehicleName1 = v1.getsName().toLowerCase();
            String vehicleName2 = v2.getsName().toLowerCase();

            
            if (vehicleName1.compareTo(vehicleName2) != 0) {
                return vehicleName1.compareTo(vehicleName2);
            } else {
                return v1.getiId() - v2.getiId();
            }
        }
    };

    //toString method
    @Override
    public String toString() {
        return "Vehicle [ID= " + iId + ", Name= " + sName + ", Fuel= "
                + sFuel + ", Location= " + sLocation + ", Postcode= "
                + iPostcode + ", Value= " + lValue + "]";
    }
    
    //method to create CSV String
    public String createString(){
        String [] vehicleDetails = {Integer.toString(iId),sName,sFuel,sLocation,Integer.toString(iPostcode),Long.toString(lValue)};
        String csvString = String.join(",", vehicleDetails);
        return csvString;
    }

    // getters and setters
    public int getiId() {
        return iId;
    }

    public void setiId(int iId) {
        this.iId = iId;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsFuel() {
        return sFuel;
    }

    public void setsFuel(String sFuel) {
        this.sFuel = sFuel;
    }

    public String getsLocation() {
        return sLocation;
    }

    public void setsLocation(String sLocation) {
        this.sLocation = sLocation;
    }

    public int getiPostcode() {
        return iPostcode;
    }

    public void setiPostcode(int iPostcode) {
        this.iPostcode = iPostcode;
    }

    public long getlValue() {
        return lValue;
    }

    public void setlValue(long lValue) {
        this.lValue = lValue;
    }
}
