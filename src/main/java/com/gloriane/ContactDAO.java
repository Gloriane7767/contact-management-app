package com.gloriane;

// Data Access & logic Object for storing and managing contacts
public class ContactDAO {
    // This array will store contacts in a String [] as "name|mobile"
    static String[] contacts = new String[5];
    // This keeps track of how many contacts we have saved
    static int size = 0;

    // ADD CONTACT with duplicate checking
    public static boolean addContact(String name, String phoneNumber) {
        if (name.isEmpty() || phoneNumber.isEmpty()) {
            System.out.println("Name and phone number cannot be empty.");
            return false;
        }

        // Check for duplicates using loops
        for (int i = 0; i < size; i++) {
            int pipeIndex = contacts[i].indexOf('|'); // Find the pipe character position
            String contactName = contacts[i].substring(0, pipeIndex); // Extract the name part (everything before the pipe)
             if (contactName.equalsIgnoreCase(name)) {
                return false; // Duplicate found
            }
        }
        // Manually resize array when full
        if (size >= contacts.length) {
            resizeArray();
        }

        contacts[size] = name + "|" + phoneNumber;
        size++;
        return true;
    }

    // Manually resize the array when full
    private static void resizeArray() {
        String[] newArray = new String[contacts.length * 2];
        for (int i = 0; i < contacts.length; i++) {
            newArray[i] = contacts[i];
        }
        contacts = newArray;
    }

    // Search contact with option to choose by name or number
    public static void searchContact(java.util.Scanner scanner) {
        System.out.println("\nSearch Options:");
        System.out.println("a - Search by name");
        System.out.println("b - Search by phone number");
        System.out.print("Choose an option (a/b): ");
        
        String choice = scanner.nextLine().toLowerCase();
        
        switch (choice) {
            case "a":
                System.out.print("Enter name to search: ");
                String name = scanner.nextLine();
                searchByNameHelper(name);
                break;
            case "b":
                System.out.print("Enter phone number to search: ");
                String phoneNumber = scanner.nextLine();
                searchByNumber(phoneNumber);
                break;
            default:
                System.out.println("Invalid option. Please choose 'a' or 'b'.");
        }
    }

    // Helper method for searching by name
    private static void searchByNameHelper(String name) {
        if (name.isEmpty()) {
            System.out.println("Search name cannot be empty.");
            return;
        }

        boolean found = false;
        for (int i = 0; i < size; i++) {
            int pipeIndex = contacts[i].indexOf('|');
            String contactName = contacts[i].substring(0, pipeIndex);
            String contactPhone = contacts[i].substring(pipeIndex + 1);
            if (contactName.equalsIgnoreCase(name)) {
                System.out.println("Found: " + contactName + " | " + contactPhone);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No contacts found with the name: " + name);
        }
    }

    public static void searchByNumber(String phoneNumber) {
        if (phoneNumber.isEmpty()) {
            System.out.println("Search phone number cannot be empty.");
            return;
        }

        boolean found = false;
        for (int i = 0; i < size; i++) {
            int pipeIndex = contacts[i].indexOf('|');
            String contactName = contacts[i].substring(0, pipeIndex);
            String contactPhone = contacts[i].substring(pipeIndex + 1);
            if (contactPhone.equals(phoneNumber)) {
                System.out.println("Found: " + contactName + " | " + contactPhone);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No contacts found with the phone number: " + phoneNumber);
        }
    }

    // Display all contacts using loops
    public static void displayAllContacts() {
        if (size == 0) {
            System.out.println("No contacts to display.");
            return;
        }

        System.out.println("All Contacts:");
        for (int i = 0; i < size; i++) {
            // Find the pipe character position
            int pipeIndex = contacts[i].indexOf('|');

            // Extract name and phone parts
            String contactName = contacts[i].substring(0, pipeIndex);
            String contactPhone = contacts[i].substring(pipeIndex + 1);

            System.out.println((i + 1) + ". " + contactName + " | " + contactPhone);
        }
    }

    // Delete contact with element shifting
    public static boolean deleteContact(String name) {
        if (name.isEmpty()) {
            System.out.println("Name to delete cannot be empty.");
            return false;
        }

        // Find contact using loops
        int indexToDelete = -1;
        for (int i = 0; i < size; i++) {
                int pipeIndex = contacts[i].indexOf('|');
                String contactName = contacts[i].substring(0, pipeIndex);

                if (contactName.equalsIgnoreCase(name)) {
                    indexToDelete = i;
                    break;
                }
            }

            if (indexToDelete == -1) {
            return false;
        }

        // Shift elements to fill the gap
        for (int i = indexToDelete; i < size - 1; i++) {
            contacts[i] = contacts[i + 1];
        }

        contacts[size - 1] = null;
        size--;
        return true;
    }

    // Update contact with option to choose what to update
    // A method signature: nametag,input and output via scanner or boolean result
    public static void updateContact(java.util.Scanner scanner) {
        System.out.print("Enter current name of contact to update: ");
        String currentName = scanner.nextLine();
        
        if (currentName.isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }
        
        // Find the contact first
        int contactIndex = -1; // Start: "I don't know where the contact is"
        for (int i = 0; i < size; i++) { // Search through all contacts
            int pipeIndex = contacts[i].indexOf('|');
            String contactName = contacts[i].substring(0, pipeIndex); // Extract contact name from position i
            if (contactName.equalsIgnoreCase(currentName)) {  // Is this the contact we're looking for?
                contactIndex = i; // "Found it at position i!"
                break; // Stop searching
            }
        }

        // After the loop:
        if (contactIndex == -1) {   // Still -1 = never found the contact
            System.out.println("Contact not found.");
            return;
        }
        /*
        } else {
        // contactIndex = 0, 1, 2, etc. = found at that position
         // Now we can update contacts[contactIndex]
            }
         */

        // Show current contact details
        int pipeIndex = contacts[contactIndex].indexOf('|');
        String name = contacts[contactIndex].substring(0, pipeIndex);
        String phone = contacts[contactIndex].substring(pipeIndex + 1);
        System.out.println("Current contact: " + name + " | " + phone);
        
        // Ask what to update
        System.out.println("\nWhat do you want to update?");
        System.out.println("a - Update name");
        System.out.println("b - Update phone number");
        System.out.print("Choose an option (a/b): ");
        
        String choice = scanner.nextLine().toLowerCase();
        
        switch (choice) {
            case "a":
                System.out.print("Enter new name: ");
                String newName = scanner.nextLine();
                if (!newName.isEmpty()) {
                    contacts[contactIndex] = newName + "|" + phone;
                    System.out.println("Name updated successfully.");
                } else {
                    System.out.println("Name cannot be empty.");
                }
                break;
            case "b":
                System.out.print("Enter new phone number: ");
                String newPhone = scanner.nextLine();
                if (!newPhone.isEmpty()) {
                    contacts[contactIndex] = name + "|" + newPhone;
                    System.out.println("Phone number updated successfully.");
                } else {
                    System.out.println("Phone number cannot be empty.");
                }
                break;
            default:
                System.out.println("Invalid option. Please choose 'a' or 'b'.");
        }
    }
    public static void sortContacts() {
        // Simple bubble sort implementation
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                int pipeIndex1 = contacts[j].indexOf('|');
                String name1 = contacts[j].substring(0, pipeIndex1);

                int pipeIndex2 = contacts[j + 1].indexOf('|');
                String name2 = contacts[j + 1].substring(0, pipeIndex2);

                if (name1.compareToIgnoreCase(name2) > 0) {
                    // Swap contacts
                    String temp = contacts[j];
                    contacts[j] = contacts[j + 1];
                    contacts[j + 1] = temp;
                }
            }
        }
    }
}

