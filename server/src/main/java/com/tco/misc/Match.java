package com.tco.misc;

import com.tco.game.ChessBoard;
import com.tco.game.ChessGame;
import org.eclipse.jetty.websocket.api.Session;


import java.time.LocalDateTime;

public class Match {

    private User playerWhite;
    private User playerBlack;
    private int matchID;
    private ChessGame game;
    private String currentTurn; //who's turn is it?
    private int turnCount;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Session session;

    Match(User player1, User player2, ChessGame game, String currentPlayer){
        this.playerWhite = player1;
        this.playerBlack = player2;
        this.game = game;
        currentTurn = currentPlayer;
        this.turnCount = 0;
        this.startTime = LocalDateTime.now();
    }

    public User getBlackPlayer() {
        return playerBlack;
    }

    public User getWhitePlayer() {
        return playerWhite;
    }

    public int getMatchID() {
        return matchID;
    }

    public ChessBoard getGameBoard() {
        return this.game.getChessBoard();
    }
    public ChessGame getChessGame() {
        return this.game;
    }
    public void setChessGame(ChessGame game) {
        this.game = game;
    }

    public String getCurrentTurn() {
        return currentTurn;
    }

    public int getTurnCount() {
        return turnCount;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public Session getSession() {
        return session;
    }

    public void setBlackPlayer(User blackPlayer) {
        this.playerBlack = blackPlayer;
    }

    public void setWhitePlayer(User whitePlayer) {
        this.playerWhite = whitePlayer;
    }

    public void setMatchID(int matchID) {
        this.matchID = matchID;
    }

    public void setGameBoard(ChessBoard gameBoard) {
        this.game = new ChessGame(gameBoard);
    }

    public void setCurrentTurn(String currentPlayer) {
        this.currentTurn = currentPlayer;
    }

    public void setTurnCount(int turnCount) {
        this.turnCount = turnCount;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
