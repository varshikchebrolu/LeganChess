package com.tco.misc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUser {

    private User tempUser;

    @BeforeEach
    public void createUser() {
        tempUser = new User("bob","test","testUserBob","bob@test.com","12345",  0 );
    }

    @Test
    @DisplayName("Verify Constructor")
    public void testConstructor() {
        assertNotNull(tempUser);
    }
    @Test
    @DisplayName("Test getUserID(). Null until added to userManagement.")
    public void testgetUserID() {
        assertEquals(0,tempUser.getUserID());

    }
    @Test
    @DisplayName("Test getName()")
    public void testgetUserName() {
        assertEquals("testUserBob", tempUser.getUserName());
        tempUser = new User("fred", "test", "test","test","test",  0);
        assertNotEquals("testUserBob", tempUser.getUserName());
        assertEquals("test", tempUser.getUserName());
    }
    @Test
    @DisplayName("Test getUserFName()")
    public void testgetFName() {
        assertEquals("bob", tempUser.getFName());
        tempUser = new User("fred", "test", "test","test","test",  0);
        assertNotEquals("bob", tempUser.getFName());
        assertEquals("fred", tempUser.getFName());
    }
    @Test
    @DisplayName("Test getEmail()")
    public void testgetEmail() {
        assertEquals("bob@test.com", tempUser.getEmail());
        tempUser = new User("fred", "test", "test","test","test",  0);
        assertNotEquals("bob@test.com", tempUser.getEmail());
        assertEquals("test", tempUser.getEmail());
    }
    @Test
    @DisplayName("Test getPassword()")
    public void testgetPassword() {
        assertEquals("12345", tempUser.getPassword());
        tempUser = new User("fred", "test", "test","test","test",  0);
        assertNotEquals("12345", tempUser.getPassword());
        assertEquals("test", tempUser.getPassword());

    }
    @Test
    @DisplayName("Test setEmail()")
    public void testsetEmail(){
        assertEquals("bob@test.com", tempUser.getEmail());
        tempUser.setEmail("hello@test.com");
        assertEquals("hello@test.com", tempUser.getEmail());
        assertNotEquals("bob@test.com", tempUser.getEmail());
    }
    @Test
    @DisplayName("Test setPassword()")
    public void testsetPassword(){
        assertEquals("12345", tempUser.getPassword());
        tempUser.setPassword("67890");
        assertNotEquals("12345", tempUser.getPassword());
        assertEquals("67890", tempUser.getPassword());
    }
}