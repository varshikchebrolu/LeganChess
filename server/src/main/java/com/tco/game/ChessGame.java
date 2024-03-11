package com.tco.game;

import com.tco.game.exceptions.IllegalPositionException;
import com.tco.game.pieces.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ChessGame {

    private ChessBoard chessBoard;

    public ChessGame() {
        chessBoard = new ChessBoard();
        chessBoard.initialize();
    }

    public ChessGame(ChessBoard board) {
        this.chessBoard = board;
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public ArrayList<String> legalMoves(String location) throws IllegalPositionException {
            ChessPiece p = chessBoard.getPiece(location);
            System.out.println("Piece:" +p.getName());
            return p.legalMoves();
    }



    public ArrayList<String> whiteMove() {
        //System.out.println("In White Move");
        if(checkForCheck(chessBoard, ChessPiece.Color.BLACK)) {
            //System.out.println("in CHeck true");
            if(checkForMate(ChessPiece.Color.BLACK)) {
                processsWin(ChessPiece.Color.BLACK);
            }
            //Get out of Check!
            return getMoves(ChessPiece.Color.WHITE, true);
        }
        //System.out.println("Not in check");
        return getMoves(ChessPiece.Color.WHITE, false);
        //Move wherever
    }


    public ArrayList<String> getMoves(ChessPiece.Color color, boolean inCheck) {
        ArrayList<String> moves = new ArrayList<>();
        ArrayList<ChessPiece> pieces = chessBoard.getAllPiecesOfColor(color);
        if(inCheck) {
            moves.addAll(movesThatDontCheckKing(color));
        } else {
            moves.addAll(movesThatDontCheckKing(color));
        }

        return moves;
    }

    public ArrayList<String> blackMove() {
        if(checkForCheck(chessBoard, ChessPiece.Color.WHITE)) {
            if(checkForMate(ChessPiece.Color.WHITE)) {
                processsWin(ChessPiece.Color.WHITE);
            }
            //Get out of Check!
            return getMoves(ChessPiece.Color.BLACK, true);
        }
        return getMoves(ChessPiece.Color.BLACK, false);
        //Move wherever
    }

    public boolean checkForCheck(ChessBoard board, ChessPiece.Color enemyColor) {
        //System.out.println("Checking for check");

        if(board.getAllTargetedSquaresOfColor(enemyColor)
                .contains(board.getKing(enemyColor == ChessPiece.Color.WHITE? ChessPiece.Color.BLACK: ChessPiece.Color.WHITE)
                        .getPosition()))
        return true;

        return false;
    }



    public ArrayList<String> movesThatDontCheckKing(ChessPiece.Color colorOfSideThatMustMove) {

        ArrayList<String> moves = new ArrayList<>();
        ChessBoard clonedBoard = cloneChessBoard(chessBoard);

        for(ChessPiece piece: clonedBoard.getAllPiecesOfColor(colorOfSideThatMustMove)) {
            String initialPosition = piece.getPosition();
            ChessPiece pieceAtTargetPosition = null;
            ;
            for(String move: piece.legalMoves()) {

                try {
                    if(clonedBoard.getPiece(move) != null) {pieceAtTargetPosition = clonedBoard.getPiece(move);}
                } catch (IllegalPositionException e) {
                    continue;
                }

                clonedBoard.wipeBoardAtPosition(initialPosition);

                clonedBoard.placePiece(piece, move);

                if(!checkForCheck(clonedBoard, colorOfSideThatMustMove == ChessPiece.Color.WHITE ? ChessPiece.Color.BLACK: ChessPiece.Color.WHITE)) {

                    moves.add(piece.toString() + initialPosition + move);
                }

                clonedBoard.wipeBoardAtPosition(move);

                clonedBoard.placePiece(piece, initialPosition);

                if(pieceAtTargetPosition != null)
                clonedBoard.placePiece(pieceAtTargetPosition, move);

            }
        }

        return moves;

    }

    private ChessBoard cloneChessBoard(ChessBoard boardToClone) {
        ChessBoard tempBoard = new ChessBoard();
        for(ChessPiece piece: boardToClone.getAllPiecesOfColor(ChessPiece.Color.WHITE)) {
            if(piece.getClass() == Pawn.class) { tempBoard.placePiece(new Pawn(tempBoard, ChessPiece.Color.WHITE), piece.getPosition()); }
            else if(piece.getClass() == Bishop.class) { tempBoard.placePiece(new Bishop(tempBoard, ChessPiece.Color.WHITE), piece.getPosition()); }
            else if(piece.getClass() == Knight.class) { tempBoard.placePiece(new Knight(tempBoard, ChessPiece.Color.WHITE), piece.getPosition()); }
            else if(piece.getClass() == Rook.class) { tempBoard.placePiece(new Rook(tempBoard, ChessPiece.Color.WHITE), piece.getPosition()); }
            else if(piece.getClass() == King.class) { tempBoard.placePiece(new King(tempBoard, ChessPiece.Color.WHITE), piece.getPosition()); }
            else if(piece.getClass() == Queen.class) { tempBoard.placePiece(new Queen(tempBoard, ChessPiece.Color.WHITE), piece.getPosition()); }
        }
        for(ChessPiece piece: chessBoard.getAllPiecesOfColor(ChessPiece.Color.BLACK)) {
            if(piece.getClass() == Pawn.class) { tempBoard.placePiece(new Pawn(tempBoard, ChessPiece.Color.BLACK), piece.getPosition()); }
            else if(piece.getClass() == Bishop.class) { tempBoard.placePiece(new Bishop(tempBoard, ChessPiece.Color.BLACK), piece.getPosition()); }
            else if(piece.getClass() == Knight.class) { tempBoard.placePiece(new Knight(tempBoard, ChessPiece.Color.BLACK), piece.getPosition()); }
            else if(piece.getClass() == Rook.class) { tempBoard.placePiece(new Rook(tempBoard, ChessPiece.Color.BLACK), piece.getPosition()); }
            else if(piece.getClass() == King.class) { tempBoard.placePiece(new King(tempBoard, ChessPiece.Color.BLACK), piece.getPosition()); }
            else if(piece.getClass() == Queen.class) { tempBoard.placePiece(new Queen(tempBoard, ChessPiece.Color.BLACK), piece.getPosition()); }
        }
        return tempBoard;
    }

    //Check is a prerequisite of checkmate in the code
    public boolean checkForMate(ChessPiece.Color colorThatMustMove) {
        return movesThatDontCheckKing(colorThatMustMove) == null || movesThatDontCheckKing(colorThatMustMove).isEmpty(); }

    public boolean checkForStaleMate(ChessPiece.Color color) { return movesThatDontCheckKing(color).isEmpty(); }

    public void processsWin(ChessPiece.Color colorWhoWon) {}
}
