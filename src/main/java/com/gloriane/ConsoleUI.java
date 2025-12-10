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
    System.out.println("3. Search by Phone Number");
    System.out.println("4. Display All Contacts");
    System.out.println("0. Exit");
    System.out.print("Choose an option: ");
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
                    searchByNumber();
                    break;
                case "4":
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
        System.out.println("Enter name: ");
        String name = scanner.nextLine();
        System.out.println("Enter phone number: ");
        String phoneNumber = scanner.nextLine();

        boolean added = ContactDAO.addContact(name, phoneNumber);
        if (added) {
            System.out.println("Contact added successfully.");
        } else {
            System.out.println("Failed to add contact. Contact with this name already exists.");
        }
    }

    public static void searchByName() {
        // TODO: Implement search by name functionality
        System.out.println("Enter name to search: ");
        String name = scanner.nextLine();
        ContactDAO.searchByName(name);  // Call static method directly
    }

    public static void searchByNumber() {
        System.out.println("Enter phone number to search: ");
        String phoneNumber = scanner.nextLine();
        ContactDAO.searchByNumber(phoneNumber);
    }

    public static void displayAllContacts(){
        // TODO: Implement display all contacts functionality
        ContactDAO.displayAllContacts();  // Call static method directly
        }
    }





