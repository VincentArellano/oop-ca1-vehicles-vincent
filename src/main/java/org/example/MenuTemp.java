package org.example;

import java.io.FileNotFoundException;
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

        bookingManager = new BookingManager(passengerStore, vehicleManager);

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
                        break;
                    case EXIT:
                        passengerStore.storePassengers();
                        System.out.println("Exit Menu option chosen");
                        break;
                    default:
                        System.out.print("Invalid option - please enter number in range");
                        break;
                }

            } catch (InputMismatchException | NumberFormatException | FileNotFoundException e) {
                System.out.print("Invalid option - please enter number in range");
            }
        } while (option != EXIT);

    }

    private void displayVehicleMenu() {
        final String MENU_ITEMS = "\n*** Vehicle MENU ***\n"
                + "1. Show all Vehicles\n"
                + "2. Find Vehicle by Registration\n"
                + "3. Find Vehicle by Type\n"
                + "4. Find Vehicle by No. of Seats\n"
                + "5. Exit\n"
                + "Enter Option [1,2,3,4,5]";

        final int SHOW_ALL = 1;
        final int FIND_BY_REG = 2;
        final int FIND_BY_TYPE = 3;
        final int FIND_BY_NO_OF_SEATS = 4;
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
                        vehicleManager.filterByType(type);
                        if (vehicleManager.filterByType(type) == null)
                            System.out.println("No vehicles matching the type \"" + type + "\"");
                        else
                            System.out.println("Found vehicles:");
                        for(Vehicle v1 : vehicleManager.filterByType(type)) {
                            System.out.println(v1);
                        }
                        break;
                    case FIND_BY_NO_OF_SEATS:
                        System.out.println("Find Vehicle by No. of Seats");

                    case EXIT:
                        vehicleManager.storeVehicles();
                        System.out.println("Exit Menu option chosen");
                        break;
                    default:
                        System.out.print("Invalid option - please enter number in range");
                        break;
                }

            } catch (InputMismatchException | NumberFormatException | FileNotFoundException e) {
                System.out.print("Invalid option - please enter number in range");
            }
        } while (option != EXIT);
    }

    private void displayBookingMenu() {
        final String MENU_ITEMS = "\n*** Booking MENU ***\n"
                + "1. Show all Booking\n"
                + "2. Show all future Booking\n"
                + "3. Show Booking details\n"
                + "4. Add Booking\n"
                + "5. Find Bookings for a Passenger\n"
                + "6. Delete Booking\n"
                + "7. Show Average Journey\n"
                + "8. Exit\n"
                + "Enter Option [1,2,3,4,5,6,7,8]";

        final int SHOW_ALL = 1;
        final int SHOW_FUTURE_BOOKING = 2;
        final int SHOW_BOOKING_DETAILS = 3;
        final int ADD_BOOKING = 4;
        final int FIND_PASSENGER_BOOKINGS = 5;
        final int DELETE_BOOKING = 6;
        final int SHOW_AVERAGE_JOURNEY = 7;
        final int EXIT = 8;

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
                    case SHOW_BOOKING_DETAILS:
                        System.out.println("Find Bookings Details");
                        System.out.println("Enter Booking ID: ");
                        int bookingId = keyboard.nextInt();
                        keyboard.nextLine();
                        Booking b = bookingManager.showBookingDetails(bookingId);
                        if (b == null)
                            System.out.println("No booking matching the name \"" + bookingId + "\"");
                        else
                            System.out.println("Found Booking: \n" + b);
                        break;
                    case ADD_BOOKING:
                        System.out.println("Available Vehicles:");
                        vehicleManager.displayAllVehicles();
                        System.out.println("\nCurrent Passengers");
                        passengerStore.displayAllPassengers();

                        System.out.println("\nSelect Passenger ID");
                        int addPassengerId = keyboard.nextInt();
                        System.out.println("Select Vehicle ID");
                        int addVehicleId = keyboard.nextInt();
                        System.out.println("Add Year");
                        int addYear = keyboard.nextInt();
                        System.out.println("Add Month");
                        int addMonth = keyboard.nextInt();
                        System.out.println("Add Day");
                        int addDay = keyboard.nextInt();
                        System.out.println("Add Hour");
                        int addHour = keyboard.nextInt();
                        System.out.println("Add Minute");
                        int addMinute = keyboard.nextInt();
                        System.out.println("Add Start Latitude");
                        double addStartLatitude = keyboard.nextDouble();
                        System.out.println("Add Start Longitude");
                        double addStartLongitude = keyboard.nextDouble();
                        System.out.println("Add End Latitude");
                        double addEndLatitude = keyboard.nextDouble();
                        System.out.println("Add End Longitude");
                        double addEndLongitude = keyboard.nextDouble();
                        keyboard.nextLine();

                        bookingManager.addBooking(addPassengerId,addVehicleId,addYear,addMonth,addDay,addHour,addMinute,addStartLatitude,addStartLongitude,addEndLatitude,addEndLongitude);
                        break;
                    case FIND_PASSENGER_BOOKINGS:
                        System.out.println("Find Passenger Bookings");
                        System.out.println("Enter Passenger ID");
                        int passengerId = keyboard.nextInt();
                        keyboard.nextLine();

                        for (Booking b1 : bookingManager.findPassengerBookings(passengerId)) {
                            System.out.println(b1);
                        }
                        break;
                    case DELETE_BOOKING:
                        System.out.println("Delete Booking");
                        System.out.println("Enter Booking ID");
                        int bookingId2 = keyboard.nextInt();
                        keyboard.nextLine();
                        bookingManager.deleteBooking(bookingId2);
                        break;
                    case SHOW_AVERAGE_JOURNEY:
                        System.out.println("Average Length of Booking Journey");

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

