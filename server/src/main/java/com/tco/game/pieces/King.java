package com.tco.game.pieces;

import com.tco.game.ChessBoard;
import com.tco.game.ChessPiece;
import com.tco.game.exceptions.IllegalPositionException;

import java.util.ArrayList;
import java.util.Iterator;

public class King extends ChessPiece {
    public King(ChessBoard board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return this.color == Color.WHITE ? "\u2654" : "\u265A";
    }
    public String getName(){ return this.color == Color.WHITE ? "K" : "k";}

    @Override
    public ArrayList<String> legalMoves() {

        ArrayList<String> moves = new ArrayList<>();

        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (j == 0 && i == 0) {
                    continue;
                }
                if (row + i < 8 && row+i>=0) {
                    if (column + j < 8 && column+j >= 0) {
                        moves.add(rowColToString(row + i, column + j));
                    }
                }
            }
        }


        Iterator<String> moveIterator = moves.iterator();
        //System.out.println(moves.size());
        while (moveIterator.hasNext()) {
            String move = moveIterator.next();
            //System.out.println(move);
            try {
                if (board.getPiece(move) != null) {
                    if (board.getPiece(move).getColor() == color) {
                        moveIterator.remove();
                        continue;

                    }
                }
                Color notUs = this.color == Color.WHITE ? Color.BLACK :Color.WHITE;
                for(String enemyTargetedSquare: this.board.getAllTargetedSquaresOfColor(notUs)) {
                    if(move.equals(enemyTargetedSquare)) {
                        moveIterator.remove();
                        continue;
                    }
                }

            } catch (IllegalPositionException e) {
                moveIterator.remove();
                //e.printStackTrace();
            }
        }
        return moves;
    }
    public ArrayList<String> legalMovesKing() {
        ArrayList<String> moves = new ArrayList<>();

        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (j == 0 && i == 0) {
                    continue;
                }
                if (row + i < 8 && row+i>=0) {
                    if (column + j < 8 && column+j >= 0) {
                        moves.add(rowColToString(row + i, column + j));
                    }
                }
            }
        }


        Iterator<String> moveIterator = moves.iterator();
        //System.out.println(moves.size());
        while (moveIterator.hasNext()) {
            String move = moveIterator.next();
            //System.out.println(move);
            try {
                if (board.getPiece(move) != null) {
                    if (board.getPiece(move).getColor() == color) {
                        moveIterator.remove();
                        continue;

                    }
                }

            } catch (IllegalPositionException e) {
                moveIterator.remove();
                //e.printStackTrace();
            }
        }
        return moves;
    }


    private String rowColToString(int col, int row) {
        return ((char) ('a' + col) + String.valueOf(row + 1));
    }
}
