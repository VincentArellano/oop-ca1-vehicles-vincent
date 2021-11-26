package org.example;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class BookingManager {
    private final ArrayList<Booking> bookingList;
    private PassengerStore passengerStore;
    private VehicleManager vehicleManager;

    // Constructor
    public BookingManager() {
        this.bookingList = new ArrayList<>();
    }

    public void displayAllBooking() {
        for (Booking b : this.bookingList) {
            System.out.println(b.toString());
        }
    }

    private void loadBookingDataFromFile(String filename) {

        try {
            Scanner sc = new Scanner(new File(filename));
            sc.useDelimiter("[,\r\n]+");

            while (sc.hasNext()) {
                int bookingId = sc.nextInt();
                int passengerId = sc.nextInt();
                int vehicleId = sc.nextInt();
                int year = sc.nextInt();
                int month = sc.nextInt();
                int day = sc.nextInt();
                int hour = sc.nextInt();
                int minute = sc.nextInt();
                double startLatitude = sc.nextDouble();
                double startLongitude = sc.nextDouble();
                double endLatitude = sc.nextDouble();
                double endLongitude = sc.nextDouble();
                double cost = sc.nextDouble();

                bookingList.add(new Booking(bookingId, passengerId, vehicleId, year, month, day, hour, minute, startLatitude, startLongitude, endLatitude, endLongitude, cost));
            }
            sc.close();

        } catch (IOException e) {
            System.out.println("Exception thrown. " + e);
        }
    }

    public void addBooking(int passengerId, int vehicleId, int year, int month, int day, int hour, int minute, double startLatitude, double startLongitude, double endLatitude, double endLongitude, double cost) {
        Booking booking = new Booking(passengerId, vehicleId, year, month, day, hour, minute, startLatitude, startLongitude, endLatitude, endLongitude, cost);

        boolean found = false;

        for (Booking b : bookingList) {

            if (b.equals(booking)) {

                found = true;

                break;

            }

        }

        if (found == false) {

            bookingList.add(booking);

        } else {

            System.out.println("*** addBooking() - booking already exists - no duplicates allowed");

        }
    }
}
