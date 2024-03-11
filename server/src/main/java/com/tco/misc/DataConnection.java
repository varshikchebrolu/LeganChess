package com.tco.misc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Console;
import java.sql.*;
import java.util.ArrayList;

public abstract class DataConnection {

    static String useTunnel = System.getenv("CS414_USE_DATABASE_TUNNEL");
    static String dbUser = "backlira";
    static String dbPassword = "831046791";
    static String dbURL = "jdbc:mariadb://127.0.0.1:56247/cs414_team4";

    DataConnection() {
        if (useTunnel == null || useTunnel.equals("false")) {
            //dbURL = "jdbc:mariadb://faure.cs.colostate.edu/cs414_team4";
        } else {
            dbURL = "jdbc:mariadb://127.0.0.1:56247/cs414_team4";
        }

    }
     public static ResultSet submitDBQuery(String query, int variableCount, ArrayList<String> variables) {

        try (Connection dbConnection = DriverManager.getConnection(dbURL, dbUser, dbPassword);
             PreparedStatement myStatement = dbConnection.prepareStatement(query))
        {

            for (int index = 1; index <= variableCount; index++) {
                myStatement.setString(index, variables.get(index-1));
            }

             ResultSet results = myStatement.executeQuery();
            return results;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static ResultSet submitDBQuery(String query, int i) {

        try (Connection dbConnection = DriverManager.getConnection(dbURL, dbUser, dbPassword);
             PreparedStatement myStatement = dbConnection.prepareStatement(query);)
        {
            myStatement.setInt(1, i);
            ResultSet results = myStatement.executeQuery();
            return results;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static int submitDBUpdate(String query, int i) {

        try (Connection dbConnection = DriverManager.getConnection(dbURL, dbUser, dbPassword);
             PreparedStatement myStatement = dbConnection.prepareStatement(query);)
        {
            myStatement.setInt(1, i);
            int count = myStatement.executeUpdate();
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    public static int submitDBUpdate(String query, int variableCount, ArrayList<String> variables) {

        try (Connection dbConnection = DriverManager.getConnection(dbURL, dbUser, dbPassword);
             PreparedStatement myStatement = dbConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);)
        {
            for (int index = 1; index <= variableCount; index++) {
                myStatement.setString(index, variables.get(index-1));
            }
            int count = myStatement.executeUpdate();
            if(count == 1){
                ResultSet rs = myStatement.getGeneratedKeys();
                if(rs.next()) return rs.getInt(1);
            }
            return -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    public static int submitDBUpdate(String query, int variableCount, ArrayList<String> variables, int i) {

        try (Connection dbConnection = DriverManager.getConnection(dbURL, dbUser, dbPassword);
             PreparedStatement myStatement = dbConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);)
        {
            for (int index = 1; index <= variableCount; index++) {
                myStatement.setString(index, variables.get(index-1));
            }
            myStatement.setInt(variableCount+1, i);
            int count = myStatement.executeUpdate();
            if(count == 1){
                ResultSet rs = myStatement.getGeneratedKeys();
                if(rs.next()) return rs.getInt(1);
            }
            return -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
