package com.tco.requests;

import com.tco.misc.BadPasswordException;
import com.tco.misc.User;
import com.tco.misc.UserManagement;
import com.tco.misc.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginRequest extends Request{

    //request schema items populated via GSON
    private String email;
    private String password;

    //additional items required for response
    private String firstName;
    private String lastName;
    private String userName;
    private Boolean successful;
    private int userID;
    private String errorMsg;

    //other
    private final transient Logger log = LoggerFactory.getLogger(NewUserRequest.class);

    @Override
    public void buildResponse(){
        try {
            User u = UserManagement.login(this.email, this.password);
            firstName = u.getFName();
            lastName = u.getLName();
            userName = u.getUserName();
            email = u.getEmail();
            userID = u.getUserID();
            successful = true;
            errorMsg = "";
        } catch (UserNotFoundException | BadPasswordException e) {
            userID = 0;
            successful = false;
            errorMsg = e.getMessage();
        }
    }


    //getters for use in Junit Testing
    public String getFirstName() { return this.firstName; }
    public String getLastName() { return this.lastName; }
    public String getUserName() { return this.userName; }
    public String getEmail() { return this.email; }
    public int getUserID() {
        return this.userID;
    }
    public String getPassword() { return this.password; }
    public Boolean getSuccessful() {
        return this.successful;
    }
    public String getErrorMsg() {
        return this.errorMsg;
    }
}

