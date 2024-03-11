package com.tco.game.pieces;

import org.junit.jupiter.api.Test;
import com.tco.game.ChessBoard;
import com.tco.game.ChessPiece;
import com.tco.game.exceptions.IllegalPositionException;

import static org.junit.jupiter.api.Assertions.*;

class PawnTest {

    @Test
    void testToString() {
        Pawn pawn = new Pawn(new ChessBoard(), ChessPiece.Color.WHITE);
        assertEquals(pawn.toString(), "\u2659");
        Pawn pawn2 = new Pawn(new ChessBoard(), ChessPiece.Color.BLACK);
        assertEquals(pawn2.toString(), "\u265F");
    }

    @Test
    void captures() {
        ChessBoard board = new ChessBoard();
        Pawn pawn1 = new Pawn(board, ChessPiece.Color.WHITE);
        board.placePiece(pawn1, "e4");

        Pawn pawn2 = new Pawn(board, ChessPiece.Color.BLACK);
        board.placePiece(pawn2, "e5");

        assertTrue(pawn1.legalMoves().size() == 2);
        assertTrue(pawn1.legalMoves().contains("e5"));
        assertTrue(pawn1.legalMoves().contains("d5"));

        assertTrue(pawn1.legalMoves().size() == 2);
        assertTrue(pawn2.legalMoves().contains("e4"));
        assertTrue(pawn2.legalMoves().contains("f4"));


        board.placePiece(new Pawn(board, ChessPiece.Color.BLACK), "d5");
        board.placePiece(new Pawn(board, ChessPiece.Color.WHITE), "f4");
        assertTrue(pawn1.legalMoves().size() == 1);
        assertTrue(pawn1.legalMoves().size() == 1);
    }

    @Test
    void friendlyFire() {
        ChessBoard chessBoard = new ChessBoard();
        Pawn pawn = new Pawn(chessBoard, ChessPiece.Color.WHITE);
        chessBoard.placePiece(pawn,"a1");
        chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.WHITE),"a2");

        assertTrue(pawn.legalMoves().size() == 0);

        Pawn pawn2 = new Pawn(chessBoard, ChessPiece.Color.WHITE);
        chessBoard.placePiece(pawn2,"h8");
        chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.WHITE),"h7");

        assertTrue(pawn2.legalMoves().size() == 0);
    }
}