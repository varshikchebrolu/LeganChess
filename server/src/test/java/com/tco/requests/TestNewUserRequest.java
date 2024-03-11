package com.tco.requests;

import com.tco.misc.User;
import com.tco.misc.UserManagement;
import com.tco.misc.UserNotFoundException;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestNewUserRequest {

    private NewUserRequest request;

    @Test
    @Order(1)
    @DisplayName("Request type is \"NewUser\"")
    public void testType() {
        request = new NewUserRequest("test","test","test2","test","12345");
        String type = request.getRequestType();
        assertEquals("NewUser", type);
    }

    @Test
    @Order(2)
    @DisplayName("Good Response returns UserID > 0 and successful == true" )
    public void testGoodResponse(){
        request = new NewUserRequest("test","test","testmore","testmore", "12345");
        request.buildResponse();
        assertTrue(request.getUserID() > 0);
        assertTrue(request.getSuccessful());


    }

    @Test
    @Order(3)
    @DisplayName("Bad Response return UserID == 0 and an error msg")
    public void testBadResponse() {
        request = new NewUserRequest("bob","test","testmore","testmore", "12345");
        request.buildResponse();
        assertEquals(request.getUserID() ,0);
        assertFalse(request.getSuccessful());
        assertEquals("Error: User already exists.", request.getErrorMsg());

        //Cleanup entry from database
        try {
            User u = UserManagement.getUser("testmore");
            UserManagement.deleteUser(u.getUserID());
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
    }


}