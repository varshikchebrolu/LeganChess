package com.tco.misc;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String password;

    private int userID;
    List<Integer> savedGames;       //list of gameIDs
    List<Integer> matchHistory;     //list of gameIDs

    User(String fname, String lname, String userName, String email, String password, int userID){
        this.firstName = fname;
        this.lastName = lname;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.userID = userID;

        savedGames = new ArrayList<>();
        matchHistory = new ArrayList<>();
    }

    public int getUserID() {
        return userID;
    }
    public String getFName() {
        return firstName;
    }
    public String getLName() { return lastName;}
    public String getUserName() {
        return userName;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setUserID(int i){
        this.userID = i;
    }
}
