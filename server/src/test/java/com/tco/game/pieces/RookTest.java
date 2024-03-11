package com.tco.game.pieces;

import com.tco.game.ChessBoard;
import com.tco.game.ChessPiece;
import com.tco.game.exceptions.IllegalPositionException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class RookTest {

    @Test
    void testToString() {
        Rook rook = new Rook(new ChessBoard(), ChessPiece.Color.WHITE);
        assertEquals(rook.toString(), "\u2656");
        Rook rook2 = new Rook(new ChessBoard(), ChessPiece.Color.BLACK);
        assertEquals(rook2.toString(), "\u265C");
    }

    @Test
    void legalMoves() throws IllegalPositionException {
        Rook rook = new Rook(new ChessBoard(), ChessPiece.Color.WHITE);
        rook.setPosition("e4");
        assertTrue(rook.legalMoves().size() == 14);
    }

    @Test
    void corners() throws IllegalPositionException {
        Rook rook = new Rook(new ChessBoard(), ChessPiece.Color.WHITE);
        String[] moves = {"a2","a3","a4","a5","a6","a7","a8","b1","c1","d1","e1","f1","g1","h1"};
        rook.setPosition("a1");
        assertTrue(rook.legalMoves().size() == 14);
        assertTrue(rook.legalMoves().containsAll(Arrays.asList(moves)));
        rook.setPosition("a8");
        assertTrue(rook.legalMoves().size() == 14);
        rook.setPosition("h1");
        assertTrue(rook.legalMoves().size() == 14);
        rook.setPosition("h8");
        assertTrue(rook.legalMoves().size() == 14);
    }

    @Test
    void capture() {
        ChessBoard chessBoard = new ChessBoard();
        for(int i = 0 ; i < 8;i++){
            for(int j = 1; j < 9;j++) {
                chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.BLACK),((char)('a' + i) + String.valueOf(j)));
            }
        }
        Rook rook = new Rook(chessBoard, ChessPiece.Color.WHITE);
        chessBoard.placePiece(rook, "e4");


        String[] moves = {"e5","e3","d4","f4"};
        assertTrue(rook.legalMoves().size() == 4);
        assertTrue(rook.legalMoves().containsAll(Arrays.asList(moves)));
    }
    @Test
    void friendlyFire() {
        ChessBoard chessBoard = new ChessBoard();
        Rook rook = new Rook(chessBoard, ChessPiece.Color.WHITE);
        chessBoard.placePiece(rook, "e4");
        chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.WHITE), "e6");
        chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.WHITE), "e2");
        chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.WHITE), "g4");
        chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.WHITE), "c4");

        String[] moves = {"e5","e3","d4","f4"};
        assertTrue(rook.legalMoves().size() == 4);
        assertTrue(rook.legalMoves().containsAll(Arrays.asList(moves)));
    }

}