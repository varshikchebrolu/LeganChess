package com.tco.game.pieces;

import com.tco.game.ChessBoard;
import com.tco.game.ChessPiece;
import com.tco.game.exceptions.IllegalPositionException;

import java.util.ArrayList;
import java.util.Iterator;

public class Rook extends ChessPiece {
    public Rook(ChessBoard board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return this.color == Color.WHITE ? "\u2656" : "\u265C";
    }
    public String getName(){ return this.color == Color.WHITE ? "R" : "r";}

    @Override
    public ArrayList<String> legalMoves() {
        ArrayList<String> moves = new ArrayList<String>();

        for (int i = 1; i < 8; i++) {
            try {
                if (board.getPiece(rowColToString(row + i, column)) != null) {
                    moves.add(rowColToString(row + i, column));
                    break;
                }else {
                    moves.add(rowColToString(row + i, column));
                }
            } catch (IllegalPositionException ignored) {break;}
        }
        for (int i = 1; i < 8; i++) {
            try {
                if (board.getPiece(rowColToString(row - i, column)) != null) {
                    moves.add(rowColToString(row - i, column));
                    break;
                }else {
                    moves.add(rowColToString(row - i, column));
                }
            } catch (IllegalPositionException ignored) {break;}
        }
        for (int i = 1; i < 8; i++) {
            try {
                if (board.getPiece(rowColToString(row, column +i)) != null) {
                    moves.add(rowColToString(row , column+i));
                    break;
                }else {
                    moves.add(rowColToString(row, column+i));
                }
            } catch (IllegalPositionException ignored) {break;}
        }
        for (int i = 1; i < 8; i++) {
            try {
                if (board.getPiece(rowColToString(row, column -i)) != null) {
                    moves.add(rowColToString(row , column-i));
                    break;
                }else {
                    moves.add(rowColToString(row, column-i));
                }
            } catch (IllegalPositionException ignored) {break;}
        }




        Iterator<String> moveIterator = moves.iterator();
        while (moveIterator.hasNext()) {
            String move = moveIterator.next();
            try {
                if (board.getPiece(move) != null) {
                    if (board.getPiece(move).getColor() == color) {
                        moveIterator.remove();
                    }
                }
            } catch (IllegalPositionException e) {
                moveIterator.remove();
            }
        }
        return moves;
    }

    private String rowColToString(int col, int row) {
        return ((char) ('a' + col) + String.valueOf(row + 1));
    }
}
