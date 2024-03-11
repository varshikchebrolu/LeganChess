package com.tco.requests;

import com.tco.misc.UserManagement;
import com.tco.misc.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogoutRequest extends Request{
    //request schema items populated via GSON
    private int userID;

    //additional items required for response
    private Boolean successful;
    private String errorMsg;

    //other
    private final transient Logger log = LoggerFactory.getLogger(NewUserRequest.class);

    @Override
    public void buildResponse(){
        try {
            Boolean u = UserManagement.logout(this.userID);
            successful = u;
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
