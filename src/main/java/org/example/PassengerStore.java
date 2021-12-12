package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PassengerStore {

    private final ArrayList<Passenger> passengerList;

    public PassengerStore(String fileName) {
        this.passengerList = new ArrayList<>();
        loadPassengerDataFromFile(fileName);
    }

    public List<Passenger> getAllPassengers() {
        return this.passengerList;
    }

    public void displayAllPassengers() {
        for (Passenger p : this.passengerList) {
            System.out.println(p.toString());
        }
    }

    /**
     * Read Passenger records from a text file and create and add Passenger
     * objects to the PassengerStore.
     */
    private void loadPassengerDataFromFile(String filename) {

        try {
            Scanner sc = new Scanner(new File(filename));
//           Delimiter: set the delimiter to be a comma character ","
//                    or a carriage-return '\r', or a newline '\n'
            sc.useDelimiter("[,\r\n]+");

            while (sc.hasNext()) {
                int id = sc.nextInt();
                String name = sc.next();
                String email = sc.next();
                String phone = sc.next();
                double latitude = sc.nextDouble();
                double longitude = sc.nextDouble();

                // construct a Passenger object and add it to the passenger list
                passengerList.add(new Passenger(id, name, email, phone, latitude, longitude));
            }
            sc.close();

        } catch (IOException e) {
            System.out.println("Exception thrown. " + e);
        }
    }

    // TODO - see functional spec for details of code to add
    public void addPassenger(String name, String email, String phone, double latitude, double longitude) {

        // construct a new passenger object

        Passenger passenger = new Passenger(name, email, phone, latitude, longitude);

        // test to see if Passenger already exists ????

        // loop through to see if it is in the list

        boolean found = false;

        for (Passenger p : passengerList) {

            if (p.equals(passenger)) {

                found = true;

                break;

            }

        }

        // add the new passenger to the passenger list (if not already there)

        if (found == false) {

            passengerList.add(passenger);
            System.out.println("Passenger Added");

        } else {

            System.out.println("*** addPassenger() - passenger already exists - no duplicates allowed");

        }
        // better approach - contains will call equals() to compare passengers

        //if(! passengerList.contains(passenger))

        //    passengerList.add(passenger);


    }

    public Passenger findPassengerByName(String name){
        for(Passenger p :passengerList){
            if(p.getName().equalsIgnoreCase(name)){
                return p;
            }
        }
        return null;
    }

    public Passenger findPassengerById(int passengerId){
        for(Passenger p :passengerList){
            if(p.getId()==passengerId){
                return p;
            }
        }
        return null;
    }

    public void deletePassenger(int passengerId) {
        Passenger p = findPassengerById(passengerId);
        if (p != null) {
            passengerList.remove(p);
            System.out.println("Passenger Deleted");
        } else {
            System.out.println("Cannot find passenger with that passenger id");
        }
    }

    public void storePassengers() throws FileNotFoundException {
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
