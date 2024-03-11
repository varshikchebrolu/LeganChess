package com.tco.websocket;

public class Message {

    public String type;  //update,move,chat
    public String from;  //userID(chat) or board location(move)
    public String to;    //userID(chat) or board location(move)
    public String msg;   //FEN string or chat message
    public String user;

    Message(String type, String from, String to, String msg, String user){
        this.type = type;
        this.from = from;
        this.to = to;
        this.msg = msg;
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
