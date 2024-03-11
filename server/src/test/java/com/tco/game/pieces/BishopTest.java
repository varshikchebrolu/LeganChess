package com.tco.game.pieces;

import com.tco.game.ChessBoard;
import com.tco.game.ChessPiece;
import com.tco.game.exceptions.IllegalPositionException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BishopTest {

    @Test
    void testToString() {
        Bishop bishop = new Bishop(new ChessBoard(), ChessPiece.Color.WHITE);
        assertEquals(bishop.toString(), "\u2657");
        Bishop bishop2 = new Bishop(new ChessBoard(), ChessPiece.Color.BLACK);
        assertEquals(bishop2.toString(), "\u265D");
    }
    @Test
    void test() throws IllegalPositionException {
        ChessBoard chessBoard = new ChessBoard();
        Bishop bishop = new Bishop(chessBoard, ChessPiece.Color.WHITE);
        chessBoard.placePiece(bishop, "d5");
        assertTrue(chessBoard.getPiece("d5") == bishop);
        assertTrue(bishop.getPosition().equals("d5"));
    }

    @Test
    void testy() throws IllegalPositionException {
        ChessBoard chessBoard = new ChessBoard();
        Bishop bishop = new Bishop(chessBoard, ChessPiece.Color.WHITE);
        chessBoard.placePiece(bishop, "a1");

        assertTrue(bishop.legalMoves().contains("b2"));
    }


    @Test
    void legalMoves() throws IllegalPositionException {
        Bishop bishop = new Bishop(new ChessBoard(), ChessPiece.Color.WHITE);
        bishop.setPosition("e4");
        assertTrue(bishop.legalMoves().size() == 13);
    }
    @Test
    void corners() throws IllegalPositionException {
        Bishop bishop = new Bishop(new ChessBoard(), ChessPiece.Color.WHITE);
        bishop.setPosition("a1");
        assertTrue(bishop.legalMoves().size() == 7);
        bishop.setPosition("a8");
        assertTrue(bishop.legalMoves().size() == 7);
        bishop.setPosition("h1");
        assertTrue(bishop.legalMoves().size() == 7);
        bishop.setPosition("h8");
        assertTrue(bishop.legalMoves().size() == 7);

        assertTrue(bishop.legalMoves().contains("a1"));
    }
    @Test
    void capture() {
        ChessBoard chessBoard = new ChessBoard();
        for(int i = 0 ; i < 8;i++){
            for(int j = 1; j < 9;j++) {
                chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.BLACK),((char)('a' + i) + String.valueOf(j)));
            }
        }
        Bishop bishop = new Bishop(chessBoard, ChessPiece.Color.WHITE);
        chessBoard.placePiece(bishop,"e4");
        //System.out.println(bishop.legalMoves().toString());
        assertTrue(bishop.legalMoves().size() == 4);
        assertTrue(bishop.legalMoves().containsAll(Arrays.asList(new String[]{"d5", "f5", "d3", "f3"})));
    }

    @Test
    void friendlyFire() {
        ChessBoard chessBoard = new ChessBoard();
        Bishop bishop = new Bishop(chessBoard, ChessPiece.Color.WHITE);
        chessBoard.placePiece(bishop, "e4");
        chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.WHITE), "c6");
        chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.WHITE), "g2");
        chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.WHITE), "c2");
        chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.WHITE), "g6");

        String[] moves = {"d5","d3","f5","f3"};
        assertTrue(bishop.legalMoves().size() == 4);
        assertTrue(bishop.legalMoves().containsAll(Arrays.asList(moves)));
    }

}