package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class VehicleManager {
    private final ArrayList<Vehicle> vehicleList;

    public VehicleManager(String fileName) {
        this.vehicleList = new ArrayList<>();
        loadVehiclesFromFile(fileName);
    }

    public void displayAllVehicles() {
        for (Vehicle v : vehicleList)
            System.out.println(v.toString());
    }

    public void loadVehiclesFromFile(String fileName) {
        try {
            Scanner sc = new Scanner(new File(fileName));
            sc.useDelimiter("[,\r\n]+");

            while (sc.hasNext()) {
                int id = sc.nextInt();
                String type = sc.next();
                String make = sc.next();
                String model = sc.next();
                double milesPerKwH = sc.nextDouble();
                String registration = sc.next();
                double costPerMile = sc.nextDouble();
                int year = sc.nextInt();   // last service date
                int month = sc.nextInt();
                int day = sc.nextInt();
                int mileage = sc.nextInt();
                double latitude = sc.nextDouble();
                double longitude = sc.nextDouble();

                if (type.equalsIgnoreCase("Van") ||
                        type.equalsIgnoreCase("Truck")) {
                    int loadSpace = sc.nextInt();
                    vehicleList.add(new Van(id, type, make, model, milesPerKwH,
                            registration, costPerMile,
                            year, month, day,
                            mileage, latitude, longitude,
                            loadSpace));
                }

                else if (type.equalsIgnoreCase("Car") ||
                        type.equalsIgnoreCase("4x4")) {
                    int numOfSeats = sc.nextInt();
                    vehicleList.add(new Car(id, type, make, model, milesPerKwH,
                            registration, costPerMile,
                            year, month, day,
                            mileage, latitude, longitude,
                            numOfSeats));
                }

            }
            sc.close();

        } catch (IOException e) {
            System.out.println("Exception thrown. " + e);
        }
    }

    public Vehicle findVehicleByRegNum(String registration){
        for(Vehicle v :vehicleList){
            if(v.getRegistration().equalsIgnoreCase(registration)){
                return v;
            }
        }
        return null;
    }

    public Vehicle findVehicleById(int vehicleId){
        for(Vehicle v :vehicleList){
            if(v.getId()==vehicleId){
                return v;
            }
        }
        return null;
    }


    public double findVehicleCost(int vehicleId){
        for(Vehicle v :vehicleList){
            if(v.getId()==vehicleId){
                return v.getCostPerMile();
            }
        }
        return 0.00;
    }

    public List<Vehicle> filterByType(String type) {
        List<Vehicle> filteredList = new ArrayList<>();

        for (Vehicle p : this.vehicleList) {
            if (p.getType().equalsIgnoreCase(type))
                filteredList.add(p);
        }

        ComparatorVehicleRegistration comp = new ComparatorVehicleRegistration();
        Collections.sort(filteredList,comp);

        return filteredList;
    }


    public List<Vehicle> filterByNoOfSeats(double numOfSeats) {
        List<Vehicle> filteredList = new ArrayList<>();

        for (Vehicle p : this.vehicleList) {
            if (p.getNumOfSeats() == numOfSeats)
                filteredList.add(p);
        }

        ComparatorVehicleRegistration comp = new ComparatorVehicleRegistration();
        Collections.sort(filteredList,comp);

        return filteredList;
    }

    public void storeVehicles() throws FileNotFoundException {
        Scanner console = new Scanner(System.in);
        System.out.println("Input file: ");
        String inputFileName = console.next();
        System.out.print("Output file: ");
        String outputFileName = console.next();

        File inputFile = new File(inputFileName);
        Scanner in = new Scanner(inputFile);

        PrintWriter out = new PrintWriter(outputFileName);


        double total = 0;

        while (in.hasNextDouble())
        {
            double value = in.nextDouble();

            out.printf("%15.2f\n", value);
            total = total + value;
        }

        out.printf("Total: %8.2f\n", total);

        in.close();
        out.close();
    }

}
