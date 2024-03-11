package com.tco.misc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class UserManagement extends DataConnection{

    public static UserManagement um = new UserManagement();
    private static ArrayList<User> activeUsers = new ArrayList<>();

    private UserManagement(){
        super();
    }

    public static User createNewUser(String fname,String lname, String userName, String email, String password) throws NewUserCreationException {
        try{
            User u = getUser(userName);
            u = getUserByEmail(email);
            throw new NewUserCreationException("Error: User already exists.");

        }catch (UserNotFoundException e) {
            String query = "INSERT INTO `Users` (FirstName, LastName, Username, Email, Password) VALUES (?,?,?,?,?)";
            ArrayList<String> var = new ArrayList<>();
            var.add(0, fname);
            var.add(1,lname);
            var.add(2, userName);
            var.add(3, email);
            var.add(4, password);
            int count = submitDBUpdate(query, 5, var);
            try {
                User user = getUser(userName);
                if (user.getUserID() > 0) {
                    activeUsers.add(user);
                    return user;
                }
            } catch (UserNotFoundException er) {
                throw new NewUserCreationException("Error: Could not create new user at this time.");
            }
        }
        return null;
    }

    public static boolean changeUserPassword(String email, String oldPassword, String newPassword){
        ArrayList<String> var = new ArrayList<>();
        var.add(0,newPassword);
        User u = searchActiveUsers(email);
        try {
            if(u != null) {
                if(!u.getPassword().equals(oldPassword)) throw new BadPasswordException("Incorrect Password!");
                String query = "UPDATE `Users` SET 'Password'=? Where `UserName`= ?";
                int count = submitDBUpdate(query,2, var);
                if(count == 0) return false;
                return true;
            }throw new UserNotFoundException("User not logged in!");
        } catch (UserNotFoundException | BadPasswordException throwables) {
            return false;
        }
    }
    public static boolean deleteUser(int userID) throws UserNotFoundException {
        User user = getUser(userID);
        if(user.getUserID() == userID) {
            String query = "DELETE FROM Users Where `UserID`= ?";
            int count = submitDBUpdate(query, userID);
            if(count == 0) throw new UserNotFoundException("Error: Could not delete; User Not found");
            if(activeUsers.contains(user)) {
                activeUsers.remove(user);
            }
            return true;
        }
        return false;
    }
    //Check username/password
    public static User login(String email, String password) throws UserNotFoundException, BadPasswordException {
//        User u = searchActiveUsers(email);
        User u = getUserByEmail(email);
        if(u!=null){
            //u = getUserByEmail(email);
            if(u.getPassword().equals(password)) {
                if(!activeUsers.contains(u)) activeUsers.add(u);
                return u;
            }
            throw new BadPasswordException("Error: Incorrect Password!");
        }
        throw new UserNotFoundException("Error: User not found");
    }

    public static Boolean logout(int userID) throws UserNotFoundException{
        User u= getUser(userID);
        if(activeUsers.contains(u)){
            activeUsers.remove(u);
            return true;
        }else{
            throw new UserNotFoundException("User not logged in.");
        }
    }
    private static User searchActiveUsers(String email){
        for(int i=0; i<activeUsers.size();i++){
            if(activeUsers.get(i).getEmail().equals(email)) return activeUsers.get(i);
        }
        return null;
    }
    public static ArrayList<String> getActiveUsers(){
        ArrayList<String> temp = new ArrayList<>();
        for (User u :activeUsers){
            temp.add(u.getUserName());
        }
        return temp;
    }
    //get user by username
    public static User getUser(String userName) throws UserNotFoundException {
        ArrayList<String> var = new ArrayList<>();
        var.add(0,userName);
        String query = "SELECT * FROM Users Where `UserName`= ?";
        try(ResultSet result = submitDBQuery(query, 1, var )) {
            if(result.next()) return new User(result.getString("FirstName"), result.getString("LastName"), result.getString("UserName"), result.getString("Email"),result.getString("Password"), result.getInt("UserID"));
        } catch (SQLException throwables) {
            System.err.println("SQL Error on getUser by username");
        }
        throw new UserNotFoundException("User not found!");
    }
    //get user by ID#
    public static User getUser(int userID) throws UserNotFoundException {
        String query = "SELECT * FROM Users Where `UserID`= ?";
        try(ResultSet result = submitDBQuery(query, userID)) {
            if(result.next()) return new User(result.getString("FirstName"), result.getString("LastName"), result.getString("UserName"), result.getString("Email"),result.getString("Password"), result.getInt("UserID"));
        } catch (SQLException throwables) {
            System.err.println("SQL Error on getUser by ID");
        }
        throw new UserNotFoundException("User not found!");
    }
    public static User getUserByEmail(String email) throws UserNotFoundException{
        ArrayList<String> var = new ArrayList<>();
        var.add(0,email);
        String query = "SELECT * FROM Users Where `Email`= ?";
        try(ResultSet result = submitDBQuery(query, 1, var )) {
            if(result.next()) return new User(result.getString("FirstName"), result.getString("LastName"), result.getString("UserName"), result.getString("Email"),result.getString("Password"), result.getInt("UserID"));
        } catch (SQLException throwables) {
            System.err.println("SQL Error on getUserByEmail");
        }
        throw new UserNotFoundException("User not found!");

    }
}

