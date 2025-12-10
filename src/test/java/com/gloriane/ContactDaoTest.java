package com.gloriane;

import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ContactDaoTest {
    //@Test: marks a method as a test method to be run by the testing framework.marks a method a test case.
    // @BeforeEach: indicates that the annotated method should be executed before each test method in the current class.Runs before each test method.
    // @AfterEach: indicates that the annotated method should be executed after each test method in the current class.Runs after each test method.
    // @displayName: provides a custom name for the test method when it is reported by the testing framework.Gives a custom name to the test method.
    // @parameterizedTest: indicates that the annotated method is a parameterized test that will be run multiple times with different arguments.Runs the test method multiple times with different arguments.supports parameterized tests.

    //Test cases for ContactDAO methods would go here
    // For example:
    // @Test case for adding a contact
    @Test
    @Order(1)
    @DisplayName("Test adding a contact")
    void addContact() {
        // Test logic for adding a contact
        // Scenario: Add a new contact and verify it was added successfully
        // Expected outcome: Contact is added without errors
        String expectedName = "John Doe";
        String expectedPhone = "123-456-7890";
        
        // Actual addition
        boolean isAdded = ContactDAO.addContact(expectedName, expectedPhone);
        
        // Verify the result
        Assertions.assertTrue(isAdded);
        Assertions.assertEquals(expectedName, "John Doe");
    }

}

