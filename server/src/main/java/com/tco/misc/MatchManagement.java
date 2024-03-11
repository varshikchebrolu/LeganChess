package com.tco.misc;

import com.tco.game.ChessBoard;
import com.tco.game.ChessGame;
import com.tco.websocket.GameEndpoint;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.websocket.*;


public class MatchManagement extends DataConnection {

    public static MatchManagement mm = new MatchManagement();
    private static ArrayList<Match> activeMatches = new ArrayList<>();
    //private static ArrayList<GameEndpoint> gameStreams = new ArrayList<>();

    private MatchManagement(){
        super();
    }

    public static Match createNewMatch(User playerBlack, User playerWhite) throws MatchCreationException{

            Match myMatch = new Match(playerWhite,playerBlack, new ChessGame(), playerWhite.getUserName());
            String board = myMatch.getGameBoard().toString();
            String query = "INSERT INTO `Matches` (PlayerWhite, PlayerBlack, Board, StartTime, EndTime, CurrentTurn) VALUES (?,?,?,?,?,?)";
            ArrayList<String> var = new ArrayList<>();
            var.add(0, playerWhite.getUserName());
            var.add(1, playerBlack.getUserName());
            var.add(2, board);
            var.add(3, myMatch.getStartTime().toString());
            var.add(4, "");
            var.add(5, playerWhite.getUserName());
            int ID = submitDBUpdate(query, 6, var);
            if(ID > 0){
                myMatch.setMatchID(ID);
                activeMatches.add(myMatch);
                return myMatch;
            }
        throw new MatchCreationException("Error: Could not create new match");
    }

    public static Match getSavedMatch(int matchID){
        Match tempMatch;
        String tempBlack;
        String tempWhite;
        User tempUser1;
        User tempUser2;
        ChessBoard tempBoard;

        String query = "SELECT * FROM `Matches` WHERE MatchID = ?";
        try(ResultSet result = submitDBQuery(query, matchID)) {
            if(result.next()) {
                tempBlack = result.getString("PlayerBlack");
                tempWhite = result.getString("PlayerWhite");
                tempUser1 = UserManagement.getUser(tempWhite);
                tempUser2 = UserManagement.getUser(tempBlack);
                tempBoard = new ChessBoard(result.getString("Board"));
                //System.out.println("checkpoint");
                tempMatch = new Match(tempUser1, tempUser2, new ChessGame(tempBoard), result.getString("CurrentTurn"));
                tempMatch.setMatchID(result.getInt("MatchID"));
                activeMatches.add(tempMatch);
                //System.out.println("match:" + tempMatch.getMatchID());
                return tempMatch;
                //TODO: check if match exists in array already!
            }
        } catch (SQLException s) {
            System.err.println("SQL Error on getUser by username");
            return null;
        } catch ( UserNotFoundException e) {
            System.err.println("User associated with match could not be found!");
            return null;
        }
        System.err.println("MatchID not found!");
        return null;
    }

    public static Boolean deleteMatch(Match match){
        if(activeMatches.contains(match)) {
            activeMatches.remove(match);
        }
        String query = "DELETE FROM Matches Where `MatchID`= ?";
        int count = submitDBUpdate(query, match.getMatchID());
        if(count == 0){
            System.err.println("Error: Could not delete; Match Not found");
            return false;
        }
        return true;
    }

    public static Boolean saveMatch(Match m) {
        ArrayList<String> var = new ArrayList<>();
        var.add(0, m.getGameBoard().toString());
        var.add(1, m.getCurrentTurn());

        String query = "UPDATE `Matches` SET `Board`=?, `CurrentTurn`=?  Where `MatchID`= ?";
        int count = submitDBUpdate(query, 2, var, m.getMatchID());
        if (count == 0) return false;
        if (activeMatches.contains(m)) activeMatches.remove(m);
        return true;
    }

    public static ArrayList<Match> getActiveMatches(){
        return activeMatches;
    }

    public static Match findActiveMatch(int matchid){
        System.out.println("Finding active Matches");
        for (Match m: activeMatches) {
            System.out.println("Match with this num exists: " + m.getMatchID());
            if(m.getMatchID() == matchid) return m;
        }
        return null;
    }

    public static ArrayList<Match> getMatchHistory(int userID){
        //TODO: add query for matches associated with a specific user
        return null;
    }

}
