package com.tco.game.pieces;

import com.tco.game.ChessBoard;
import com.tco.game.ChessPiece;
import com.tco.game.exceptions.IllegalPositionException;

import java.util.ArrayList;
import java.util.Iterator;


public class Knight extends ChessPiece {
    public Knight(ChessBoard board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return this.color == Color.WHITE ? "\u2658" : "\u265E";
    }
    public String getName(){ return this.color == Color.WHITE ? "N" : "n";}

    @Override
    public ArrayList<String> legalMoves() {
        ArrayList<String> moves = new ArrayList<String>();

        try {
            if (board.getPiece(rowColToString(row + 1, column + 2)) == null) {
                moves.add(rowColToString(row + 1, column + 2));
            }
        } catch (IllegalPositionException ignored) {
        }
        try {
            if (board.getPiece(rowColToString(row + 2, column + 1)) == null) {
                moves.add(rowColToString(row + 2, column + 1));
            }
        } catch (IllegalPositionException ignored) {
        }

        try {
            if (board.getPiece(rowColToString(row - 2, column + 1)) == null) {
                moves.add(rowColToString(row - 2, column + 1));
            }
        } catch (IllegalPositionException ignored) {
        }
        try {
            if (board.getPiece(rowColToString(row - 1, column + 2)) == null) {
                moves.add(rowColToString(row - 1, column + 2));
            }
        } catch (IllegalPositionException ignored) {
        }

        try {
            if (board.getPiece(rowColToString(row - 1, column - 2)) == null) {
                moves.add(rowColToString(row - 1, column - 2));
            }
        } catch (IllegalPositionException ignored) {
        }
        try {
            if (board.getPiece(rowColToString(row - 2, column - 1)) == null) {
                moves.add(rowColToString(row - 2, column - 1));
            }
        } catch (IllegalPositionException ignored) {
        }

        try {
            if (board.getPiece(rowColToString(row + 1, column - 2)) == null) {
                moves.add(rowColToString(row + 1, column - 2));
            }
        } catch (IllegalPositionException ignored) {
        }
        try {
            if (board.getPiece(rowColToString(row + 2, column - 1)) == null) {
                moves.add(rowColToString(row + 2, column - 1));
            }
        } catch (IllegalPositionException ignored) {
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