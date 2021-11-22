package org.example;

public class Car extends Vehicle{
    private double numOfSeats;   // measured in number of seats.  For Car And 4x4

    public Car(String type, String make, String model, double milesPerKwH,
               String registration, double costPerMile,
               int year, int month, int day,
               int mileage, double latitude, double longitude,
               int numOfSeats)
    {
        // call superclass constructor to initialize the fields defined in Vehicle
        super(type,make,model,milesPerKwH,
                registration,costPerMile,
                year,month,day,
                mileage,latitude,longitude);

        this.numOfSeats = numOfSeats;
    }

    // Constructor version to be used to recreate a Car that was read from file.
    // It will have already been allocated an id.
    //
    public Car(int id, String type, String make, String model, double milesPerKwH,
               String registration, double costPerMile,
               int year, int month, int day,
               int mileage, double latitude, double longitude,
               int numOfSeats)
    {
        // call superclass constructor to initialize the fields defined in Vehicle
        super(id,type,make,model,milesPerKwH,
                registration,costPerMile,
                year,month,day,
                mileage,latitude,longitude);

        this.numOfSeats = numOfSeats;
    }

    public double getNumOfSeats() {
        return numOfSeats;
    }
    public void setNumOfSeats(double numOfSeats) {
        this.numOfSeats = numOfSeats;
    }

    @Override
    public String toString() {
        return "Car{" +
                "numOfSeats=" + numOfSeats +
                "} " + super.toString();
    }
}
