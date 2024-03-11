package com.tco.game.pieces;

import com.tco.game.ChessBoard;
import com.tco.game.ChessPiece;
import com.tco.game.exceptions.IllegalPositionException;

import java.util.ArrayList;
import java.util.Iterator;

public class Bishop extends ChessPiece {
    public Bishop(ChessBoard board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return this.color == Color.WHITE ? "\u2657" : "\u265D";
    }
    public String getName(){ return this.color == Color.WHITE ? "B" : "b";}

    @Override
    public ArrayList<String> legalMoves() {
        ArrayList<String> moves = new ArrayList<String>();


        for (int i = 1; i < 8; i++) {
            try {
                if (board.getPiece(rowColToString(row + i, column + i)) != null) {
                    moves.add(rowColToString(row + i, column + i));
                    break;
                } else {
                    moves.add(rowColToString(row + i, column + i));
                }
            } catch (IllegalPositionException ignored) {
                break;
            }
        }
        for (int i = 1; i < 8; i++) {
            try {
                if (board.getPiece(rowColToString(row + i, column - i)) != null) {
                    moves.add(rowColToString(row + i, column - i));
                    break;
                } else {
                    moves.add(rowColToString(row + i, column - i));
                }
            } catch (IllegalPositionException ignored) {
                break;
            }
        }
        for (int i = 1; i < 8; i++) {
            try {
                if (board.getPiece(rowColToString(row - i, column + i)) != null) {
                    moves.add(rowColToString(row - i, column + i));
                    break;
                } else {
                    moves.add(rowColToString(row - i, column + i));
                }
            } catch (IllegalPositionException ignored) {
                break;
            }
        }
        for (int i = 1; i < 8; i++) {
            try {
                if (board.getPiece(rowColToString(row - i, column - i)) != null) {
                    moves.add(rowColToString(row - i, column - i));
                    break;
                } else {
                    moves.add(rowColToString(row - i, column - i));
                }
            } catch (IllegalPositionException ignored) {
                break;
            }
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
                e.printStackTrace();
            }
        }
        return moves;
    }

    private String rowColToString(int col, int row) {
        return ((char) ('a' + col) + String.valueOf(row + 1));
    }
}