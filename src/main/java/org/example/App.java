package org.example;

/** Vincent Arellano SD2a
 * This Vehicle Bookings Management Systems manages the booking of Vehicles
 * by Passengers.
 *
 * This program reads from 3 text files:
 * "vehicles.txt", "passengers.txt", and "next-id-store.txt"
 * You should be able to see them in the project pane.
 * You will create "bookings.txt" at a later stage, to store booking records.
 *
 * "next-id-store.txt" contains one number ("201"), which will be the
 * next auto-generated id to be used to when new vehicles, passengers, or
 * bookings are created.  The value in the file will be updated when new objects
 * are created - but not when objects are recreated from records in
 * the files - as they already have IDs.  Dont change it - it will be updated by
 * the IdGenerator class.
 */

public class App
{
    public static void main(String[] args)
    {
        System.out.println("\nWelcome to the VEHICLE BOOKINGS MANAGEMENT SYSTEM - CA1 for OOP\n");

        // create PassengerStore and load it with passenger records from text file
        PassengerStore passengerStore = new PassengerStore("passengers.txt");
        System.out.println("List of all passengers:");
        passengerStore.displayAllPassengers();

        VehicleManager vehicleManager = new VehicleManager("vehicles.txt");
        System.out.println("\nList of all Vehicles:");
        vehicleManager.displayAllVehicles();

        System.out.println("\nFind Vehicle By Registration Number");
        String reg = "172LH234106";
        Vehicle v = vehicleManager.findVehicleByRegNum(reg);
        if(v!=null){
            System.out.println(v);
        }
        else{
            System.out.println("Vehicle Not Found");
        }

        passengerStore.addPassenger("Philip Nolan","pnolan@dcu.ie","01-5654327",53.6666,-6.876756);

        passengerStore.addPassenger("Philip Nolan","philip1223456@mit.ie","01-5654327",53.6666,-6.876756);



        System.out.println("\nList of all passengers:");

        passengerStore.displayAllPassengers();

        System.out.println("Program exiting... Goodbye");
    }
}
