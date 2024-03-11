package com.tco.misc;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestUserManagement {

    public User tempUser;
    public int tempID;

    @Test
    @Order(1)
    @DisplayName("Verify user creation")
    public void createUser() {
        try {
            tempUser = UserManagement.createNewUser("bob1","testUserBob","bobtest1","bob@joes.com","12345");
            tempID = tempUser.getUserID();

        } catch (NewUserCreationException e) {

        }
        assertTrue(tempID > 0);
    }
    @Test
    @Order(2)
    @DisplayName("Test getUser")
    public void testGetUserByID() {

        try {
            tempUser = UserManagement.getUser(tempID);
        } catch (UserNotFoundException e) {
        }
        assertEquals(tempUser.getUserName(),"bobtest1");

    }
    @Test
    @Order(3)
    @DisplayName("Verify search for user by Username")
    public void testGetUserbyUserName() {
        try {
            tempUser = UserManagement.getUser("bobtest1");
        } catch (UserNotFoundException e) {

        }
        assertEquals(tempUser.getUserID(),tempID);

    }


    @Test
    @Order(4)
    @DisplayName("Verify search for user by username/password")
    public void testCheckCredentials() {
        try {
            User u = UserManagement.login("bob@joes.com","12345");
            assertNotNull(u);
        } catch (UserNotFoundException e) {

        } catch (BadPasswordException e) {

        }
        assertThrows(UserNotFoundException.class, ()-> UserManagement.login("fredtest","12345"));
        assertThrows(BadPasswordException.class, ()-> UserManagement.login("bob@joes.com","badpassword"));

    }

    @Test
    @Order(5)
    @DisplayName("Verify Deletion")
    public void testDeleteUser() {
        try {
            Boolean result = UserManagement.deleteUser(tempID);
            assertTrue(result);
        } catch (UserNotFoundException e) {

        }
        assertThrows(UserNotFoundException.class, ()-> UserManagement.deleteUser(tempID));
    }


}