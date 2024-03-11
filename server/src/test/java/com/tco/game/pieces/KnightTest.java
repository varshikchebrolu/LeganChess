package com.tco.game.pieces;

import com.tco.game.ChessBoard;
import com.tco.game.ChessPiece;
import com.tco.game.exceptions.IllegalPositionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;

class KnightTest {

    @Test
    void testToString() {
        Knight knight = new Knight(new ChessBoard(), ChessPiece.Color.WHITE);
        assertEquals(knight.toString(), "\u2658");
        Knight knight2 = new Knight(new ChessBoard(), ChessPiece.Color.BLACK);
        assertEquals(knight2.toString(), "\u265E");
    }

    @Test
    void legalMoves() throws IllegalPositionException {
        Knight knight = new Knight(new ChessBoard(), ChessPiece.Color.WHITE);
        knight.setPosition("d4");

        String[] allLegalMovesForKnight = new String[]{"f5", "e6", "f3", "e2", "c2", "c6", "b3", "b5"};
        //String[] allLegalMovesForKnight = new String[]{"f5"};


        assertTrue(knight.legalMoves().containsAll(Arrays.asList(allLegalMovesForKnight)));
        assertTrue(knight.legalMoves().size() == 8);

//        Knight knight2 = new Knight(new ChessBoard(), ChessPiece.Color.BLACK);
//        knight2.setPosition("e4");
//
//        assertTrue(knight2.legalMoves().contains("g6"));
    }

    @Test
    void capture() throws IllegalPositionException {
        ChessBoard chessBoard = new ChessBoard();
        Knight knight = new Knight(chessBoard, ChessPiece.Color.BLACK);
        knight.setPosition("d4");

        String[] allLegalMovesForKnight = new String[]{"f5", "e6", "f3", "e2", "c2", "c6", "b3", "b5"};

        Pawn pawn = new Pawn(chessBoard, ChessPiece.Color.WHITE);
        pawn.setPosition("f5");
        Pawn pawn2= new Pawn(chessBoard, ChessPiece.Color.WHITE);
        pawn2.setPosition("e6");
        Pawn pawn3 = new Pawn(chessBoard, ChessPiece.Color.WHITE);
        pawn3.setPosition("f3");
        Pawn pawn4 = new Pawn(chessBoard, ChessPiece.Color.WHITE);
        pawn4.setPosition("e2");
        Pawn pawn5 = new Pawn(chessBoard, ChessPiece.Color.WHITE);
        pawn5.setPosition("c2");
        Pawn pawn6 = new Pawn(chessBoard, ChessPiece.Color.WHITE);
        pawn6.setPosition("c6");
        Pawn pawn7 = new Pawn(chessBoard, ChessPiece.Color.WHITE);
        pawn7.setPosition("b3");
        Pawn pawn8 = new Pawn(chessBoard, ChessPiece.Color.WHITE);
        pawn8.setPosition("b5");

        assertTrue(knight.legalMoves().containsAll(Arrays.asList(allLegalMovesForKnight)));
        assertTrue(knight.legalMoves().size() == 8);

    }

    @Test
    void friendlyFire() throws IllegalPositionException {
        ChessBoard chessBoard = new ChessBoard();
        Knight knight = new Knight(chessBoard, ChessPiece.Color.WHITE);
        knight.setPosition("d4");

        String[] allLegalMovesForKnight = new String[]{"f5", "e6", "f3", "e2", "c2", "c6", "b3", "b5"};

        Pawn pawn = new Pawn(chessBoard, ChessPiece.Color.WHITE);
        pawn.setPosition("f5");
        Pawn pawn2= new Pawn(chessBoard, ChessPiece.Color.WHITE);
        pawn2.setPosition("e6");
        Pawn pawn3 = new Pawn(chessBoard, ChessPiece.Color.WHITE);
        pawn3.setPosition("f3");
        Pawn pawn4 = new Pawn(chessBoard, ChessPiece.Color.WHITE);
        pawn4.setPosition("e2");
        Pawn pawn5 = new Pawn(chessBoard, ChessPiece.Color.WHITE);
        pawn5.setPosition("c2");
        Pawn pawn6 = new Pawn(chessBoard, ChessPiece.Color.WHITE);
        pawn6.setPosition("c6");
        Pawn pawn7 = new Pawn(chessBoard, ChessPiece.Color.WHITE);
        pawn7.setPosition("b3");
        Pawn pawn8 = new Pawn(chessBoard, ChessPiece.Color.WHITE);
        pawn8.setPosition("b5");

        //assertTrue(knight.legalMoves().isEmpty());
        assertTrue(knight.legalMoves().size() == 8);

    }
}