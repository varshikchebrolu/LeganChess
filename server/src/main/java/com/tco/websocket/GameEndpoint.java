package com.tco.websocket;

import com.google.gson.Gson;
import com.tco.game.ChessPiece;
import com.tco.game.exceptions.IllegalMoveException;
import com.tco.game.exceptions.IllegalPositionException;
import com.tco.misc.*;
import com.tco.requests.Request;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.*;

import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

@WebSocket
public class GameEndpoint{

    private Session mySession;
    private static Set<GameEndpoint> endpointQ = new CopyOnWriteArraySet<>();
    private static HashMap<String,Session> userQ = new HashMap<>();

    @OnWebSocketMessage
    public void onMessage(Session session, String msg){
        //System.out.println("THISISMESSAGE" + msg);
        Message myMsg = new Gson().fromJson(msg, Message.class);
        Message reply;
        if(myMsg.type.equals("move")){
            reply = makeMove(myMsg);
            System.out.println("Reply: " + reply.getMsg());
            System.out.println("Attempting to send the message");
            System.out.println(reply.to);
            System.out.println(reply.from);
            //System.out.println(userQ.keySet().toString());
            //System.out.println(userQ.get(reply.to) == null);
            //System.out.println(userQ.get(reply.from) == null);
            sendMessage(reply, userQ.get(reply.user));
            //sendMessage(reply, userQ.get(reply.from)); //update both players

        }
        if(myMsg.type.equals("create")){
            reply = createMatch(myMsg);
            sendMessage(reply, userQ.get(reply.user)); //update both players
            //sendMessage(reply, userQ.get(reply.to));
        }if(myMsg.type.equals("save")){
             reply = saveMatch(myMsg);
             sendMessage(reply, userQ.get(reply.user));
        }

    }
    @OnWebSocketConnect
    public void open(Session session){  //add username to hashmap, associate session with user
        this.mySession = session;
        endpointQ.add(this);
        String temp = session.getUpgradeRequest().getRequestURI().getPath();
        temp = temp.substring(temp.lastIndexOf('/')+1);
        userQ.put(temp, session);
        System.out.println("Users" + userQ.toString());
    }
    @OnWebSocketError
    public void error(Session session, Throwable error){
        System.out.println("ERORORRR");
        //clear session from match
    }
    @OnWebSocketClose
    public void close(Session session, int i, String reason){
        endpointQ.remove(this);
        String temp = session.getUpgradeRequest().getRequestURI().getPath();
        temp = temp.substring(temp.lastIndexOf('/')+1);
        userQ.remove(temp);
    }

    private Message makeMove(Message m){
        System.out.println("Attempting to Make move, game number: " + m.msg);
        Match match = MatchManagement.findActiveMatch(Integer.parseInt(m.msg));

        if(match == null) System.out.println("Match is null :(");

        try {
            ArrayList<String> moves = new ArrayList<>();
            if(match.getGameBoard().getPiece(m.from).getColor() == ChessPiece.Color.WHITE) {
                System.out.println("White Move");
                moves.addAll(match.getChessGame().whiteMove());
            } else {
                System.out.println("Black Move");
                moves.addAll(match.getChessGame().blackMove());
            }
            for(String x : moves){
                System.out.println("Legal Moves:" +x);
            }
            if(moves.contains(match.getGameBoard().getPiece(m.from).toString() + m.from + m.to)) {
                System.out.println("Attempting to move piece from: " + m.from+ " to: " + m.to);
                System.out.println(match.getGameBoard().toString());
                match.getGameBoard().move(m.from, m.to);
                System.out.println(match.getGameBoard().toString());
                System.out.println("Piece Moved move: " + m.from+ " " + m.to );
                String checkMate = match.getChessGame()
                        .checkForCheck(match.getGameBoard(), match
                                .getGameBoard().getPiece(m.to).getColor()) ? "true":"false";
                return new Message("update", m.from, m.to, stringToFEN(match.getGameBoard().toString()) + "," + checkMate, m.user);
            }else{throw new IllegalMoveException();}
        }catch (IllegalPositionException e) {
            System.out.println("Illigal move Err");
            return new Message("invalid", m.from, m.to, "Illegal position", m.user);
        }catch (IllegalMoveException em){
            System.out.println("Illigal Pos Err");
            return new Message("invalid", m.from, m.to, "Illegal Move",m.user);
        }
        //System.out.println("Moving end, return");
        //System.out.println(match.getGameBoard().toString());
        //return new Message("invalid", m.from, m.to, "No Match Found",m.user);
        //return new Message("update", p1, p2, "knbrp3/bqpp4/npp5/rp1p4/p3P1PR/5PPN/4PPQB/3PRBNK", m.user);
    }
    private Message createMatch(Message m){
        try {
            User p1 = UserManagement.getUser(m.from);
            User p2 = UserManagement.getUser(m.to);
            Match temp = MatchManagement.createNewMatch(p1,p2);
            System.out.println("createMatch:"+ temp.getMatchID());
            return new Message("create",p1.getUserName(),p2.getUserName(),String.valueOf(temp.getMatchID()),m.user);
        } catch (UserNotFoundException | MatchCreationException e) {
            e.printStackTrace();
        }
        return new Message("create","","","0","");
    }
    private Message saveMatch(Message m){
        Match temp = MatchManagement.findActiveMatch(Integer.parseInt(m.msg));
        Boolean result = false;
        if(temp != null) result = MatchManagement.saveMatch(temp);
        if(result) return new Message("save","","","Success",m.user);
        return new Message("save","","","failed",m.user);
    }

    private void sendMessage(Message m, Session s){
        String temp = new Gson().toJson(m);
        try {
            s.getRemote().sendString(temp);
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
    private static String convertRow(String input) {
        String temp ="";
        int count = 0;
        for(int i=0; i<8 ;i++){
            if(input.charAt(i) == '*'){
                count++;
                continue;
            }
            if(count > 0){
                if(input.charAt(i) != '*'){
                    temp += count;
                    count = 0;
                    temp += input.charAt(i);
                }
            }else{
            temp += input.charAt(i);
            }
        }
        if(count > 0) temp+=count;
        return temp;
    }
    private static String stringToFEN(String s){
        //System.out.println("String in: "+s);
        String result = "";
        ArrayList<String> rows = new ArrayList<>();
        rows.add(s.substring(56,64));
        rows.add(s.substring(48,56));
        rows.add(s.substring(40,48));
        rows.add(s.substring(32,40));
        rows.add(s.substring(24,32));
        rows.add(s.substring(16,24));
        rows.add(s.substring(8,16));
        rows.add(s.substring(0,8));

        for(int i=0;i<7;i++){
            result += convertRow(rows.get(i));
            result += "/";
        }
        result += convertRow(rows.get(7));
        return result;
    }

}
