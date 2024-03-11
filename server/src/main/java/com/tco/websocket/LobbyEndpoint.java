package com.tco.websocket;

import com.google.gson.Gson;
import com.tco.misc.JSONValidator;
import com.tco.misc.UserManagement;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.*;

import javax.websocket.EndpointConfig;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArraySet;

@WebSocket
public class LobbyEndpoint {

    private Session mySession;
    //private static Set<LobbyEndpoint> endpointQ = new CopyOnWriteArraySet<>();
    private static HashMap<String,Integer> userQ = new HashMap<>();
    private static Queue<Session> sessionQ = new ConcurrentLinkedQueue<>();

    @OnWebSocketMessage
    public void onMessage(Session session, String msg){
        System.out.println("msg received");
        Message temp = new Gson().fromJson(msg, Message.class);
        if(temp.type == "list"){
            ArrayList<String> userList = UserManagement.getActiveUsers();
            String output = new Gson().toJson(userList);
            try {
                session.getRemote().sendString(output);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            //echos messages back to all connected users
            for (Session s : sessionQ) {
                try {
                    s.getRemote().sendString(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @OnWebSocketConnect
    public void open(Session session){
        //this.mySession = session;
        sessionQ.add(session);
        //userQ.put(session, userID);
    }
    @OnWebSocketError
    public void error(Session session, Throwable error){
        //clear session from match
    }
    @OnWebSocketClose
    public void close(Session session,int i, String reason){
        sessionQ.remove(session);
        //userQ.remove(session);
    }

}