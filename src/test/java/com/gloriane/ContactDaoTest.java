package com.gloriane;

import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ContactDaoTest {
    //@Test: marks a method as a test method to be run by the testing framework.marks a method a test case.
    // @BeforeEach: indicates that the annotated method should be executed before each test method in the current class.Runs before each test method.
    // @AfterEach: indicates that the annotated method should be executed after each test method in the current class.Runs after each test method.
    // @displayName: provides a custom name for the test method when it is reported by the testing framework.Gives a custom name to the test method.
    // @parameterizedTest: indicates that the annotated method is a parameterized test that will be run multiple times with different arguments.Runs the test method multiple times with different arguments.supports parameterized tests.

    @BeforeEach
    void setUp() {
        // Reset ContactDAO state before each test
        ContactDAO.contacts = new String[5];
        ContactDAO.size = 0;
    }

    @Test
    @Order(1)
    @DisplayName("Test adding a contact")
    void addContact() {
        // Scenario: Add a new contact and verify it was added successfully
        // Expected outcome: Contact is added without errors
        String expectedName = "John Doe";
        String expectedPhone = "123-456-7890";
        
        boolean isAdded = ContactDAO.addContact(expectedName, expectedPhone);
        
        Assertions.assertTrue(isAdded);
        Assertions.assertEquals(1, ContactDAO.size);
    }

    @Test
    @Order(2)
    @DisplayName("Test adding duplicate contact")
    void addDuplicateContact() {
        // Scenario: Add duplicate contact and verify it's rejected
        // Expected outcome: Second addition returns false
        String name = "Jane Smith";
        String phone = "987-654-3210";
        
        ContactDAO.addContact(name, phone);
        boolean isDuplicateAdded = ContactDAO.addContact(name, "111-222-3333");
        
        Assertions.assertFalse(isDuplicateAdded);
        Assertions.assertEquals(1, ContactDAO.size);
    }

    @Test
    @Order(3)
    @DisplayName("Test adding contact with empty name")
    void addContactEmptyName() {
        // Scenario: Add contact with empty name
        // Expected outcome: Addition returns false
        boolean isAdded = ContactDAO.addContact("", "123-456-7890");
        
        Assertions.assertFalse(isAdded);
        Assertions.assertEquals(0, ContactDAO.size);
    }

    @Test
    @Order(4)
    @DisplayName("Test adding contact with empty phone")
    void addContactEmptyPhone() {
        // Scenario: Add contact with empty phone number
        // Expected outcome: Addition returns false
        boolean isAdded = ContactDAO.addContact("John Doe", "");
        
        Assertions.assertFalse(isAdded);
        Assertions.assertEquals(0, ContactDAO.size);
    }

    @Test
    @Order(5)
    @DisplayName("Test deleting existing contact")
    void deleteExistingContact() {
        // Scenario: Delete an existing contact
        // Expected outcome: Contact is deleted successfully
        ContactDAO.addContact("Alice Johnson", "555-1234");
        ContactDAO.addContact("Bob Wilson", "555-5678");
        
        boolean isDeleted = ContactDAO.deleteContact("Alice Johnson");
        
        Assertions.assertTrue(isDeleted);
        Assertions.assertEquals(1, ContactDAO.size);
    }

    @Test
    @Order(6)
    @DisplayName("Test deleting non-existing contact")
    void deleteNonExistingContact() {
        // Scenario: Try to delete a contact that doesn't exist
        // Expected outcome: Deletion returns false
        ContactDAO.addContact("Charlie Brown", "555-9999");
        
        boolean isDeleted = ContactDAO.deleteContact("Unknown Person");
        
        Assertions.assertFalse(isDeleted);
        Assertions.assertEquals(1, ContactDAO.size);
    }

    @Test
    @Order(7)
    @DisplayName("Test deleting with empty name")
    void deleteContactEmptyName() {
        // Scenario: Try to delete with empty name
        // Expected outcome: Deletion returns false
        boolean isDeleted = ContactDAO.deleteContact("");
        
        Assertions.assertFalse(isDeleted);
    }

    @Test
    @Order(8)
    @DisplayName("Test updating existing contact")
    void updateExistingContact() {
        // Scenario: Update phone number of existing contact
        // Expected outcome: Contact is updated successfully
        ContactDAO.addContact("David Lee", "111-1111");
        
        boolean isUpdated = ContactDAO.updateContact("David Lee", "222-2222");
        
        Assertions.assertTrue(isUpdated);
    }

    @Test
    @Order(9)
    @DisplayName("Test updating non-existing contact")
    void updateNonExistingContact() {
        // Scenario: Try to update a contact that doesn't exist
        // Expected outcome: Update returns false
        boolean isUpdated = ContactDAO.updateContact("Unknown Person", "333-3333");
        
        Assertions.assertFalse(isUpdated);
    }

    @Test
    @Order(10)
    @DisplayName("Test updating with empty name")
    void updateContactEmptyName() {
        // Scenario: Try to update with empty name
        // Expected outcome: Update returns false
        boolean isUpdated = ContactDAO.updateContact("", "444-4444");
        
        Assertions.assertFalse(isUpdated);
    }

    @Test
    @Order(11)
    @DisplayName("Test updating with empty phone")
    void updateContactEmptyPhone() {
        // Scenario: Try to update with empty phone number
        // Expected outcome: Update returns false
        ContactDAO.addContact("Emma Davis", "555-0000");
        
        boolean isUpdated = ContactDAO.updateContact("Emma Davis", "");
        
        Assertions.assertFalse(isUpdated);
    }

    @Test
    @Order(12)
    @DisplayName("Test array resizing")
    void testArrayResizing() {
        // Scenario: Add more contacts than initial array size
        // Expected outcome: Array resizes automatically
        for (int i = 1; i <= 6; i++) {
            ContactDAO.addContact("Contact" + i, "555-000" + i);
        }
        
        Assertions.assertEquals(6, ContactDAO.size);
        Assertions.assertTrue(ContactDAO.contacts.length >= 6);
    }

}

