package com.tco.game.pieces;

import com.tco.game.ChessBoard;
import com.tco.game.ChessPiece;
import com.tco.game.exceptions.IllegalPositionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueenTest {

    @Test
    void testToString() {
        Queen queen = new Queen(new ChessBoard(), ChessPiece.Color.WHITE);
        assertEquals(queen.toString(), "\u2655");
        Queen queen2 = new Queen(new ChessBoard(), ChessPiece.Color.BLACK);
        assertEquals(queen2.toString(), "\u265B");
    }

    @Test
    void legalMoves() throws IllegalPositionException {
        ChessBoard chessBoard = new ChessBoard();
        Queen queen = new Queen(chessBoard, ChessPiece.Color.WHITE);
        queen.setPosition("e4");

        assertTrue(queen.legalMoves().contains("e5"));
        assertTrue(queen.legalMoves().contains("h7"));
        assertTrue(queen.legalMoves().size() == 27);

        Queen queen2 = new Queen(chessBoard, ChessPiece.Color.BLACK);
        queen2.setPosition("e5");

        assertTrue(queen2.legalMoves().contains("e4"));
        assertTrue(queen.legalMoves().contains("e6"));


    }

    @Test
    void friendlyFire() throws IllegalPositionException {
        ChessBoard chessBoard = new ChessBoard();

        Queen queen = new Queen(chessBoard, ChessPiece.Color.WHITE);
        queen.setPosition("e4");


        Pawn pawn = new Pawn(chessBoard, ChessPiece.Color.WHITE);
        pawn.setPosition("e5");
        Pawn pawn1 = new Pawn(chessBoard, ChessPiece.Color.WHITE);
        pawn1.setPosition("f5");
        Pawn pawn2 = new Pawn(chessBoard, ChessPiece.Color.WHITE);
        pawn2.setPosition("d5");
        Pawn pawn3 = new Pawn(chessBoard, ChessPiece.Color.WHITE);
        pawn3.setPosition("e3");
        Pawn pawn4 = new Pawn(chessBoard, ChessPiece.Color.WHITE);
        pawn4.setPosition("f3");
        Pawn pawn5 = new Pawn(chessBoard, ChessPiece.Color.WHITE);
        pawn5.setPosition("d3");
        Pawn pawn6 = new Pawn(chessBoard, ChessPiece.Color.WHITE);
        pawn6.setPosition("d4");
        Pawn pawn7 = new Pawn(chessBoard, ChessPiece.Color.WHITE);
        pawn7.setPosition("f4");

        Pawn pawn9 = new Pawn(chessBoard, ChessPiece.Color.WHITE);
        pawn9.setPosition("h7");

        assertTrue(queen.legalMoves().size() == 27);

    }
}