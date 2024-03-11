package com.tco.game.pieces;

import com.tco.game.ChessBoard;
import com.tco.game.ChessPiece;
import com.tco.game.exceptions.IllegalPositionException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class KingTest {

    @Test
    void testToString() {
        King king = new King(new ChessBoard(), ChessPiece.Color.WHITE);
        assertEquals(king.toString(), "\u2654");
        King king2 = new King(new ChessBoard(), ChessPiece.Color.BLACK);
        assertEquals(king2.toString(), "\u265A");
    }

    @Test
    void legalMoves() throws IllegalPositionException {
        King king = new King(new ChessBoard(), ChessPiece.Color.WHITE);
        king.setPosition("e4");
        ArrayList<String> moves = king.legalMoves();
        assertTrue(king.legalMoves().size() == 8);
        String[] allLegalMovesForKing = new String[]{"d5", "e5", "f5", "d4", "f4", "d3", "e3", "f3"};
        assertTrue(king.legalMoves().containsAll(Arrays.asList(allLegalMovesForKing)));
    }
    @Test
    void cornerTest() throws IllegalPositionException {
        King king = new King(new ChessBoard(), ChessPiece.Color.WHITE);
        king.setPosition("a1");
        ArrayList<String> moves = king.legalMoves();
        assertTrue(king.legalMoves().size() == 3);
        String[] allLegalMovesForKing = new String[]{"a2", "b1", "b2"};
        assertTrue(king.legalMoves().containsAll(Arrays.asList(allLegalMovesForKing)));

        king = new King(new ChessBoard(), ChessPiece.Color.BLACK);
        king.setPosition("h8");
        moves = king.legalMoves();
        assertTrue(king.legalMoves().size() == 3);
        allLegalMovesForKing = new String[]{"h7", "g8", "g7"};
        assertTrue(king.legalMoves().containsAll(Arrays.asList(allLegalMovesForKing)));
    }
    @Test
    void captures() throws IllegalPositionException {
        ChessBoard board = new ChessBoard();
        King king = new King(board, ChessPiece.Color.WHITE);
        board.placePiece(king, "e4");
        ArrayList<String> pawnPositions = king.legalMoves();
        for(String position: pawnPositions) {
            Pawn pawn = new Pawn(board, ChessPiece.Color.BLACK);
            board.placePiece(pawn, position);
        }
        ArrayList<String> moves = king.legalMoves();
        assertTrue(king.legalMoves().size() == 8);
        String[] allLegalMovesForKing = new String[]{"d5", "e5", "f5", "d4", "f4", "d3", "e3", "f3"};
        assertTrue(king.legalMoves().containsAll(Arrays.asList(allLegalMovesForKing)));
    }
    @Test
    void surrounded() throws IllegalPositionException {
        ChessBoard board = new ChessBoard();
        King king = new King(board, ChessPiece.Color.WHITE);
        board.placePiece(king, "e4");
        ArrayList<String> pawnPositions = king.legalMoves();
        for(String position: pawnPositions) {
            Pawn pawn = new Pawn(board, ChessPiece.Color.WHITE);
            board.placePiece(pawn, position);
        }
        //assertTrue(king.legalMoves().size() == 0);
        assertTrue(king.legalMoves().isEmpty());
    }
    @Test
    void cantMoveIntoCheck() throws IllegalPositionException {
        ChessBoard board = new ChessBoard();
        King king = new King(board, ChessPiece.Color.WHITE);
        Rook rook = new Rook(board, ChessPiece.Color.BLACK);
        board.placePiece(king, "a1");
        board.placePiece(rook, "a8");

        assertTrue(king.legalMoves().size() == 2);
        assertTrue(!king.legalMoves().contains("a2"));
    }

}