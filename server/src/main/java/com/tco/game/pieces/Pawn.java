package com.tco.game.pieces;

import com.tco.game.ChessBoard;
import com.tco.game.ChessPiece;
import com.tco.game.exceptions.IllegalPositionException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class Pawn extends ChessPiece {
    public Pawn(ChessBoard board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return this.color == ChessPiece.Color.WHITE ? "\u2659" : "\u265F";
    }

    @Override
    public String getName(){ return this.color == ChessPiece.Color.WHITE ? "P" : "p";}

    @Override
    public ArrayList<String> legalMoves() {
        ArrayList<String> moves = new ArrayList<String>();


        //Regular Move until end of board for each color
        try {
            if (color == Color.WHITE && board.getPiece(rowColToString(row - 1, column + 1)) == null) {
                moves.add(rowColToString(row - 1, column + 1));
            }
        } catch (IllegalPositionException e) {

        }
        try {
            if (color == Color.BLACK && board.getPiece(rowColToString(row + 1, column - 1)) == null) {
                moves.add(rowColToString(row + 1, column - 1));
            }
        } catch (IllegalPositionException e) {

        }

        //Capture
        if (color == Color.WHITE) {
            try {
                if (board.getPiece(rowColToString(row - 1, column)) != null) {
                    moves.add(rowColToString(row - 1, column));
                }
            } catch (IllegalPositionException e) {
            }
            try {
                if (board.getPiece(rowColToString(row, column + 1)) != null) {
                    moves.add(rowColToString(row, column + 1));
                }
            } catch (IllegalPositionException e) {
            }
        }
        if (color == Color.BLACK) {
            try {
                if (board.getPiece(rowColToString(row + 1, column)) != null) {
                    moves.add(rowColToString(row + 1, column));
                }
            } catch (IllegalPositionException e) {
            }
            try {
                if (board.getPiece(rowColToString(row, column - 1)) != null) {
                    moves.add(rowColToString(row, column - 1));
                }
            } catch (IllegalPositionException e) {
            }
        }

        Iterator<String> moveIterator = moves.iterator();
        while (moveIterator.hasNext()) {
            String move = moveIterator.next();
            try {
                if (board.getPiece(move) != null) {
                    Color c = board.getPiece(move).getColor();
                    if (board.getPiece(move).getColor() == color) {
                        moveIterator.remove();
                    }
                }
            } catch (IllegalPositionException e) {
                e.printStackTrace();
            }
        }

        Set<String> s = new LinkedHashSet<>(moves);
        ArrayList<String> moveNoDuplicates = new ArrayList<>(s);
        return moveNoDuplicates;
    }

    private String rowColToString(int col, int row) {
        return ((char) ('a' + col) + String.valueOf(row + 1));
    }
}
