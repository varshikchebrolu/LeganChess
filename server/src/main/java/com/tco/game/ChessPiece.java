package com.tco.game;

import com.tco.game.exceptions.IllegalPositionException;

import java.util.ArrayList;

public abstract class ChessPiece {
    public enum Color {WHITE,BLACK};
    protected ChessBoard board;
    protected int row;
    protected int column;
    protected Color color;

    public ChessPiece(ChessBoard board, Color color) {
        this.board = board;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
    public String getPosition() {
        return((char)('a' + this.row) + String.valueOf(this.column+1));
    }
    public void setPosition(String position) throws IllegalPositionException {
       // System.out.println("ChessPirce King: " + position);
        int col = Integer.parseInt(String.valueOf(position.charAt(1)))-1;
        int row = position.charAt(0)-'a';
        if(row > 7 || col > 7 || row <0 || col < 0) {
            throw new IllegalPositionException();
        } else {
            //System.out.println("Row: " + row + " Col: " + col);
            this.row = row;
            this.column = col;
        }
    }
    abstract public String toString();
    abstract public String getName();
    abstract public ArrayList<String> legalMoves();


}
