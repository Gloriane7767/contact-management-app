package com.gloriane;

import java.util.Scanner;

// This class will ONLY handle the menu and user input.
// It will NOT store contacts. That job belongs to ContactDAO.
public class ConsoleUI {
public static Scanner scanner = new Scanner(System.in);

public static void printMenu() {
    System.out.println(" === Contact Management ===");
    System.out.println("1. Add Contact");
    System.out.println("2. Search by Name");
    System.out.println("3. Display All Contacts");
    System.out.println("0. Exit");
}

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            printMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addContact();
                    break;
                case "2":
                    searchByName();
                    break;
                case "3":
                    displayAllContacts();
                    break;
                case "0":
                    running = false;
                    System.out.println("Exiting the application. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static void addContact() {
        // TODO: Implement add contact functionality
        System.out.println("Add contact feature not implemented yet.");

    }

    public static void searchByName() {
        // TODO: Implement search by name functionality
        System.out.println("Search by name feature not implemented yet.");
    }

    public static void displayAllContacts() {
        // TODO: Implement display all contacts functionality
        System.out.println("Display all contacts feature not implemented yet.");
    }




}
