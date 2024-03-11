package com.tco.requests;

import com.tco.misc.NewUserCreationException;
import com.tco.misc.User;
import com.tco.misc.UserManagement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NewUserRequest extends Request {

    //request schema items populated via JSON
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String password;

    //additional items required for response
    private Boolean successful;
    private int userID;
    private String errorMsg;

    //other
    private final transient Logger log = LoggerFactory.getLogger(NewUserRequest.class);
    //constructor for use in Junit testing
    public NewUserRequest(String fname, String lname, String userName, String email, String password){
        this.requestType = "NewUser";
        this.firstName = fname;
        this.lastName = lname;
        this.email = email;
        this.userName = userName;
        this.password = password;
            }

    @Override
    public void buildResponse(){
        try {

            User u = UserManagement.createNewUser(this.firstName,this.lastName, this.userName, this.email, this.password);
            userID = u.getUserID();
            successful = true;
            errorMsg = "";
        } catch (NewUserCreationException e) {
            userID = 0;
            successful = false;
            errorMsg = e.getMessage();
        }
    }


    //getters for use in Junit Testing
    public String getFirstName() { return this.firstName; }
    public String getLastName() { return this.lastName; }
    public String getEmail() { return this.email; }
    public String getUserName() { return this.userName; }
    public String getPassword() { return this.password; }
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
