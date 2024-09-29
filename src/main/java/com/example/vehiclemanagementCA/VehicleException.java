
package com.example.vehiclemanagementCA;

/*
VehicleException - Q6 - thrown in AddVehicle
*/
public class VehicleException extends Exception{
    String sMessage = "Input cannot be blank. Please give me something to work with";
    
    public VehicleException(){
        //empty constructor to be used for blank input
    }
    
    public VehicleException(String sInMessage){
        sMessage = sInMessage;
    }
    
    public String getMessage(){
        return sMessage;
    }
}
