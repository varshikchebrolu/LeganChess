package com.tco.game;

import com.tco.game.exceptions.IllegalMoveException;
import com.tco.game.exceptions.IllegalPositionException;
import com.tco.game.pieces.*;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class ChessBoard {
    private ChessPiece[][] board;

    public ChessBoard() {
        board = new ChessPiece[8][8];
    }
    public ChessBoard(String init){
        board = new ChessPiece[8][8];
        initialize(init);


    }
    public void initialize() {

        //White
        placePiece(new Pawn(this, ChessPiece.Color.WHITE),"d1");
        placePiece(new Pawn(this, ChessPiece.Color.WHITE),"e2");
        placePiece(new Pawn(this, ChessPiece.Color.WHITE),"f2");
        placePiece(new Pawn(this, ChessPiece.Color.WHITE),"f3");
        placePiece(new Pawn(this, ChessPiece.Color.WHITE),"g3");
        placePiece(new Pawn(this, ChessPiece.Color.WHITE),"g4");
        placePiece(new Pawn(this, ChessPiece.Color.WHITE),"e4");
        placePiece(new Pawn(this, ChessPiece.Color.WHITE),"h5");


        placePiece(new Rook(this, ChessPiece.Color.WHITE),"e1");
        placePiece(new Rook(this, ChessPiece.Color.WHITE),"h4");

        placePiece(new Knight(this, ChessPiece.Color.WHITE),"g1");
        placePiece(new Knight(this, ChessPiece.Color.WHITE),"h3");

        placePiece(new Bishop(this, ChessPiece.Color.WHITE),"f1");
        placePiece(new Bishop(this, ChessPiece.Color.WHITE),"h2");

        placePiece(new Queen(this, ChessPiece.Color.WHITE),"g2");
        placePiece(new King(this, ChessPiece.Color.WHITE),"h1");

        //Black

        placePiece(new Pawn(this, ChessPiece.Color.BLACK),"a4");
        placePiece(new Pawn(this, ChessPiece.Color.BLACK),"b5");
        placePiece(new Pawn(this, ChessPiece.Color.BLACK),"b6");
        placePiece(new Pawn(this, ChessPiece.Color.BLACK),"c6");
        placePiece(new Pawn(this, ChessPiece.Color.BLACK),"c7");
        placePiece(new Pawn(this, ChessPiece.Color.BLACK),"d7");
        placePiece(new Pawn(this, ChessPiece.Color.BLACK),"e8");
        placePiece(new Pawn(this, ChessPiece.Color.BLACK),"d5");

        placePiece(new Rook(this, ChessPiece.Color.BLACK),"a5");
        placePiece(new Rook(this, ChessPiece.Color.BLACK),"d8");

        placePiece(new Knight(this, ChessPiece.Color.BLACK),"b8");
        placePiece(new Knight(this, ChessPiece.Color.BLACK),"a6");

        placePiece(new Bishop(this, ChessPiece.Color.BLACK),"c8");
        placePiece(new Bishop(this, ChessPiece.Color.BLACK),"a7");

        placePiece(new Queen(this, ChessPiece.Color.BLACK),"b7");
        placePiece(new King(this, ChessPiece.Color.BLACK),"a8");
    }
    public void initialize(String s){
        //System.out.println("string: " + s.length());
        ChessPieceFactory factory = new ChessPieceFactory();
        int index = 0;
        for(int row=1; row<9; row++){
            for(char col='a'; col<'i'; col++){
                ChessPiece temp = factory.newChesspiece(this, s.charAt(index));
                if (temp != null) placePiece(temp, ""+col+row);
                index++;
            }
        }
    }

    public ArrayList<ChessPiece> getAllPiecesOfColor(ChessPiece.Color color) {
        ArrayList<ChessPiece> pieces = new ArrayList<>();
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                if(board[i][j] == null)
                    continue;
                if(board[i][j].getColor() == color) {
                    pieces.add(board[i][j]);
                }
            }
        }
        return pieces;
    }

    public ChessPiece getKing(ChessPiece.Color color) {
        //System.out.println("GetKing");
        ArrayList<ChessPiece> pieces = getAllPiecesOfColor(color);
        for(ChessPiece piece: pieces) {
            if(piece.getClass() == King.class) {
                //System.out.println("Found king at: " + piece.getPosition());
                //System.out.println("Success");
                return piece;
            }
        }
        //This is safe because there must always be a king on the board of both colors in a game
        System.out.println("Return Null");
        return null;
    }

    public ArrayList<String> getAllTargetedSquaresOfColor(ChessPiece.Color color) {
        //System.out.println("Getting targetedsquares");
        ArrayList<String> moves = new ArrayList<>();
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                if(board[i][j] == null)
                    continue;
                if(board[i][j].getColor() == color) {
                    if(board[i][j].getClass() == King.class) {
                        King king = (King) board[i][j];
                        moves.addAll( king.legalMovesKing());
                        continue;
                    }
                    moves.addAll(board[i][j].legalMoves());
                }
            }
        }
        Set<String> s = new LinkedHashSet<>(moves);
        ArrayList<String> movesWithoutDuplicates = new ArrayList<String>();
        movesWithoutDuplicates.addAll(s);
        //System.out.println("Success");
        return  movesWithoutDuplicates;
    }

    public void wipeBoardAtPosition(String position) {
        if(position.length() != 2 || position.charAt(0) > 'h' || position.charAt(0) < 'a' || position.charAt(1) > '8' || position.charAt(1) < '1' )
            try {
                throw new IllegalPositionException();
            } catch (IllegalPositionException e) {
                return;
            }
        board[position.charAt(0)-'a'][Integer.parseInt(String.valueOf(position.charAt(1)))-1] = null;
    }

    public ChessPiece getPiece(String position) throws IllegalPositionException {
        if(position.length() != 2 || position.charAt(0) > 'h' || position.charAt(0) < 'a' || position.charAt(1) > '8' || position.charAt(1) < '1' )
            throw new IllegalPositionException();
        return board[position.charAt(0)-'a'][Integer.parseInt(String.valueOf(position.charAt(1)))-1];
    }
    public boolean placePiece(ChessPiece piece, String position) {
        //System.out.println("Placing piece at postion: " + position);
        try {
            if(getPiece(position) != null && getPiece(position).color == piece.color) {
                return false;
            }
        } catch (IllegalPositionException e) {
            return false;
        }
        //System.out.println("Replaceing piece at: " + position);
        board[position.charAt(0)-'a'][Integer.parseInt(String.valueOf(position.charAt(1)))-1] = piece;
        try {
            //System.out.println("Final Kin Ps: " + position);
            piece.setPosition(position);
        } catch (IllegalPositionException e) {
            e.printStackTrace();
        }
        return true;
    }
    public void move(String fromPosition, String toPosition) throws IllegalMoveException {
        try {
            ChessPiece piece = getPiece(fromPosition);
            if(piece.legalMoves().contains(toPosition)) {
                placePiece(piece, toPosition);
                wipeBoardAtPosition(fromPosition);
            } else {
                throw new IllegalMoveException();
            }
        } catch (IllegalPositionException e) {
            return;
        }
    }

    public String toString(){
        String result = "";
        for(int i=0; i<8 ; i++){
            for(int j=0; j<8; j++){
                ChessPiece temp = this.board[j][i];
                if(temp == null) result += "*";
                else result += temp.getName();
            }
            //result += "/";
        }
        return result;
    }
}
