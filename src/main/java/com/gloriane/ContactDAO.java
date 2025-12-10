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

    // Search using loops
    public static void searchByName(String name) {
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

    // Update contact using loops
    public static boolean updateContact(String name, String newPhoneNumber) {
        if (name.isEmpty() || newPhoneNumber.isEmpty()) {
            System.out.println("Name and new phone number cannot be empty.");
            return false;
        }

        for (int i = 0; i < size; i++) {
            int pipeIndex = contacts[i].indexOf('|');
            String contactName = contacts[i].substring(0, pipeIndex);

            if (contactName.equalsIgnoreCase(name)) {
                contacts[i] = contactName + "|" + newPhoneNumber;
                return true;
            }
        }
        return false;
    }
}

