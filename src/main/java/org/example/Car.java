package org.example;

public class Car extends Vehicle{
    private double numOfSeats;

    public Car(String type, String make, String model, double milesPerKwH,
               String registration, double costPerMile,
               int year, int month, int day,
               int mileage, double latitude, double longitude,
               int numOfSeats)
    {
        super(type,make,model,milesPerKwH,
                registration,costPerMile,
                year,month,day,
                mileage,latitude,longitude);

        this.numOfSeats = numOfSeats;
    }

    public Car(int id, String type, String make, String model, double milesPerKwH,
               String registration, double costPerMile,
               int year, int month, int day,
               int mileage, double latitude, double longitude,
               int numOfSeats)
    {
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
