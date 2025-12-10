package com.gloriane;

// Data Access & logic Object for storing and managing contacts
public class ContactDAO {

    // This array will store up to 100 contacts in the format "name|mobile"
    static String[] contacts = new String[5];

    // This keeps track of how many contacts we have saved
    static int size = 0;

    // ---------------- ADD CONTACT ----------------
    public  static boolean addContact(String name, String phoneNumber) {
        String newContact = name + "|" + phoneNumber;

        if (name.isEmpty() || phoneNumber.isEmpty()) {
            System.out.println("Name or mobile number cannot be empty.");
            return false;
            }
        if (size >= contacts.length) {
            System.out.println("Contact list is full. Cannot add more contacts.");
            return false;
        }
        contacts[size] = name + "|" + phoneNumber;
        size++;
        return true;
    }

    public static void searchByName(String name) {
        // TODO: Implement search functionality
    }

    public static void displayAllContacts() {
        // TODO: Implement display functionality
    }
}