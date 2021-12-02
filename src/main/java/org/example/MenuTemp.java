package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuTemp {
    // Components (objects) used in this App
    PassengerStore passengerStore;  // encapsulates access to list of Passengers
    VehicleManager vehicleManager;  // encapsulates access to list of Vehicles
    BookingManager bookingManager;  // deals with all bookings

    public static void main(String[] args) {
        MenuTemp app = new MenuTemp();
        app.start();
    }

    public void start() {
        // create PassengerStore and load all passenger records from text file
        passengerStore = new PassengerStore("passengers.txt");

        // create VehicleManager, and load all vehicles from text file
        vehicleManager = new VehicleManager("vehicles.txt");

        bookingManager = new BookingManager("bookings.txt");
        try {
            displayMainMenu();        // User Interface - Menu
        } catch (IOException e) {
            e.printStackTrace();
        }

        //pMgr.saveToFile();

        System.out.println("Program ending, Goodbye");
    }

    private void displayMainMenu() throws IOException {

        final String MENU_ITEMS = "\n*** MAIN MENU OF OPTIONS ***\n"
                + "1. Passengers\n"
                + "2. Vehicles\n"
                + "3. Bookings\n"
                + "4. Exit\n"
                + "Enter Option [1,2,3,4]";

        final int PASSENGERS = 1;
        final int VEHICLES = 2;
        final int BOOKINGS = 3;
        final int EXIT = 4;

        Scanner keyboard = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("\n" + MENU_ITEMS);
            try {
                String usersInput = keyboard.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option) {
                    case PASSENGERS:
                        System.out.println("Passengers option chosen");
                        displayPassengerMenu();
                        break;
                    case VEHICLES:
                        System.out.println("Vehicles option chosen");
                        displayVehicleMenu();
                        break;
                    case BOOKINGS:
                        System.out.println("Bookings option chosen");
                        displayBookingMenu();
                        break;
                    case EXIT:
                        System.out.println("Exit Menu option chosen");
                        break;
                    default:
                        System.out.print("Invalid option - please enter number in range");
                        break;
                }

            } catch (InputMismatchException | NumberFormatException e) {
                System.out.print("Invalid option - please enter number in range");
            }
        } while (option != EXIT);

        System.out.println("\nExiting Main Menu, goodbye.");

    }

    // Sub-Menu for Passenger operations
    //
    private void displayPassengerMenu() {
        final String MENU_ITEMS = "\n*** PASSENGER MENU ***\n"
                + "1. Show all Passengers\n"
                + "2. Find Passenger by Name\n"
                + "3. Add Passenger\n"
                + "4. Exit\n"
                + "Enter Option [1,2,3,4]";

        final int SHOW_ALL = 1;
        final int FIND_BY_NAME = 2;
        final int ADD_PASSENGER = 3;
        final int EXIT = 4;

        Scanner keyboard = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("\n" + MENU_ITEMS);
            try {
                String usersInput = keyboard.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option) {
                    case SHOW_ALL:
                        System.out.println("Display ALL Passengers");
                        passengerStore.displayAllPassengers();
                        break;
                    case FIND_BY_NAME:
                        System.out.println("Find Passenger by Name");
                        System.out.println("Enter passenger name: ");
                        String name = keyboard.nextLine();
                        Passenger p = passengerStore.findPassengerByName(name);
                        if (p == null)
                            System.out.println("No passenger matching the name \"" + name + "\"");
                        else
                            System.out.println("Found passenger: \n" + p);
                        break;
                    case ADD_PASSENGER:
                        System.out.println("Add Passengers");
                        System.out.println("Name:");
                        String addName = keyboard.nextLine();
                        System.out.println("Email");
                        String addEmail = keyboard.nextLine();
                        System.out.println("Phone");
                        String addPhone = keyboard.nextLine();
                        System.out.println("Latitude");
                        double addLatitude = keyboard.nextDouble();
                        System.out.println("Longitude");
                        double addLongitude = keyboard.nextDouble();
                        keyboard.nextLine();
                        passengerStore.addPassenger(addName, addEmail, addPhone, addLatitude, addLongitude);
                        System.out.println("Passenger added");
                        System.out.println(passengerStore.findPassengerByName(addName));
                        break;
                    case EXIT:
                        System.out.println("Exit Menu option chosen");
                        break;
                    default:
                        System.out.print("Invalid option - please enter number in range");
                        break;
                }

            } catch (InputMismatchException | NumberFormatException e) {
                System.out.print("Invalid option - please enter number in range");
            }
        } while (option != EXIT);

    }

    private void displayVehicleMenu() {
        final String MENU_ITEMS = "\n*** Vehicle MENU ***\n"
                + "1. Show all Vehicles\n"
                + "2. Find Vehicle by Registration\n"
                + "3. Find Vehicle by Type\n"
                + "4. Exit\n"
                + "Enter Option [1,2,3,4]";

        final int SHOW_ALL = 1;
        final int FIND_BY_REG = 2;
        final int FIND_BY_TYPE = 3;
        final int EXIT = 4;

        Scanner keyboard = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("\n" + MENU_ITEMS);
            try {
                String usersInput = keyboard.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option) {
                    case SHOW_ALL:
                        System.out.println("Display ALL Vehicles");
                        vehicleManager.displayAllVehicles();
                        break;
                    case FIND_BY_REG:
                        System.out.println("Find Vehicle by Registration");
                        System.out.println("Enter vehicle registration: ");
                        String registration = keyboard.nextLine();
                        Vehicle v = vehicleManager.findVehicleByRegNum(registration);
                        if (v == null)
                            System.out.println("No vehicle matching the name \"" + registration + "\"");
                        else
                            System.out.println("Found vehicle: \n" + v);
                        break;
                    case FIND_BY_TYPE:
                        System.out.println("Find Vehicle by Type");
                        System.out.println("Enter vehicle type: ");
                        String type = keyboard.nextLine();
                        vehicleManager.findVehicleByType(type);
                        if (vehicleManager.findVehicleByType(type) == null)
                            System.out.println("No vehicles matching the type \"" + type + "\"");
                        else
                            System.out.println("Found vehicles:");
                        for(Vehicle v1 : vehicleManager.findVehicleByType(type)) {
                            System.out.println(v1);
                        }
                        break;
                    case EXIT:
                        System.out.println("Exit Menu option chosen");
                        break;
                    default:
                        System.out.print("Invalid option - please enter number in range");
                        break;
                }

            } catch (InputMismatchException | NumberFormatException e) {
                System.out.print("Invalid option - please enter number in range");
            }
        } while (option != EXIT);
    }

    private void displayBookingMenu() {
        final String MENU_ITEMS = "\n*** Booking MENU ***\n"
                + "1. Show all Booking\n"
                + "2. Show all future Booking\n"
                + "3. Find Booking by Passenger\n"
                + "4. Add Booking\n"
                + "5. Exit\n"
                + "Enter Option [1,2,3,4,5]";

        final int SHOW_ALL = 1;
        final int SHOW_FUTURE_BOOKING = 2;
        final int FIND_BY_PASSENGER = 3;
        final int ADD_BOOKING = 4;
        final int EXIT = 5;

        Scanner keyboard = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("\n" + MENU_ITEMS);
            try {
                String usersInput = keyboard.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option) {
                    case SHOW_ALL:
                        System.out.println("Display ALL Booking");
                        bookingManager.displayAllBooking();
                        break;
                    case SHOW_FUTURE_BOOKING:
                        System.out.println("Display ALL Future Booking");
                        bookingManager.displayBookingInFuture();
                        break;
                    case FIND_BY_PASSENGER:
                        System.out.println("Find Bookings by Passenger");
                        System.out.println("Enter passenger ID: ");
                        int passengerId = keyboard.nextInt();
                        Booking b = bookingManager.findBookingByPassengerId(passengerId);
                        if (b == null)
                            System.out.println("No passenger matching the name \"" + passengerId + "\"");
                        else
                            System.out.println("Found Booking: \n" + b);
                        break;
                    case ADD_BOOKING:
                        System.out.println("Add Booking");

                        //bookingManager.addBooking();
                        System.out.println("Booking added");
                        break;
                    case EXIT:
                        System.out.println("Exit Menu option chosen");
                        break;
                    default:
                        System.out.print("Invalid option - please enter number in range");
                        break;
                }

            } catch (InputMismatchException | NumberFormatException e) {
                System.out.print("Invalid option - please enter number in range");
            }
        } while (option != EXIT);

    }
}

