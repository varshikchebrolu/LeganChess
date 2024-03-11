package com.tco.requests;

import com.tco.misc.User;
import com.tco.misc.UserManagement;
import com.tco.misc.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetUserRequest extends Request {

    //request schema items populated via GSON
    private String userName;

    //additional items required for response
    private String firstName;
    private String lastName;
    private String email;
    private Boolean successful;
    private int userID;
    private String errorMsg;

    //other
    private final transient Logger log = LoggerFactory.getLogger(NewUserRequest.class);

    @Override
    public void buildResponse(){
        try {
            User u = UserManagement.getUser(this.userName);
            firstName = u.getFName();
            lastName = u.getLName();
            email = u.getEmail();
            userID = u.getUserID();
            successful = true;
            errorMsg = "";
        } catch (UserNotFoundException e) {
            userID = 0;
            successful = false;
            errorMsg = e.getMessage();
        }
    }


    //getters for use in Junit Testing
    public int getUserID() {
        return this.userID;
    }
    public Boolean getSuccessful() {
        return this.successful;
    }
    public String getErrorMsg() {
        return this.errorMsg;
    }
}



