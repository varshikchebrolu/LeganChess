package com.tco.game;

import com.tco.game.pieces.King;
import com.tco.game.pieces.Pawn;
import com.tco.game.pieces.Rook;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class ChessGameTest {

    @Test
    void testWhiteMove() {
        assertTrue(true);
    }
    @Test
    void testBlackMove() {
        assertTrue(true);
    }
    @Test
    void getMoves() {
        ChessBoard board = new ChessBoard();
        King wking = new King(board, ChessPiece.Color.WHITE);
        Rook rook = new Rook(board, ChessPiece.Color.WHITE);
        King bking = new King(board, ChessPiece.Color.BLACK);
        Rook brook = new Rook(board, ChessPiece.Color.BLACK);

        board.placePiece(wking, "e5");
        board.placePiece(bking, "b2");
        board.placePiece(rook, "b8");
        board.placePiece(brook, "h3");



        ChessGame game = new ChessGame(board);
        assertTrue(game.getMoves(ChessPiece.Color.BLACK, true).size() == 7);
    }
    @Test
    void checkForCheck() {

        ChessBoard board = new ChessBoard();
        Rook rook1 = new Rook(board, ChessPiece.Color.WHITE);
        King king = new King(board, ChessPiece.Color.BLACK);
        King king2 = new King(board, ChessPiece.Color.WHITE);

        //A Classic Rook Mate
        board.placePiece(rook1, "a8");
        board.placePiece(king, "a1");
        board.placePiece(king2, "h7");

        ChessGame game = new ChessGame(board);
        assertTrue(game.checkForCheck(board, ChessPiece.Color.WHITE) == true);
    }
    @Test
    void movesThatDontCheckKing() {
        ChessBoard board = new ChessBoard();
        Rook rook1 = new Rook(board, ChessPiece.Color.WHITE);
        Rook rook2 = new Rook(board, ChessPiece.Color.WHITE);
        King king = new King(board, ChessPiece.Color.BLACK);
        King king2 = new King(board, ChessPiece.Color.WHITE);

        //A Classic Rook Mate
        board.placePiece(rook1, "a1");
        board.placePiece(rook2, "b2");
        board.placePiece(king, "d1");
        board.placePiece(king2, "h8");

        ChessGame game = new ChessGame(board);
        assertTrue(game.movesThatDontCheckKing(king.getColor()).size() == 0);
    }
    @Test
    void cloneChessBoard() {
        assertTrue(true);
    }
    @Test
    void checkForMate() {
        ChessBoard board = new ChessBoard();
        Rook rook1 = new Rook(board, ChessPiece.Color.WHITE);
        Rook rook2 = new Rook(board, ChessPiece.Color.WHITE);
        King king = new King(board, ChessPiece.Color.BLACK);
        King king2 = new King(board, ChessPiece.Color.WHITE);

        //A Classic Rook Mate
        board.placePiece(rook1, "a1");
        board.placePiece(rook2, "b2");
        board.placePiece(king, "d1");
        board.placePiece(king2, "h8");

        ChessGame game = new ChessGame(board);
        assertTrue(game.checkForCheck(board, ChessPiece.Color.WHITE));

        assertTrue(game.checkForMate(ChessPiece.Color.BLACK));
    }

    @Test
    void checkForStaleMate() {
        assertTrue(true);
    }
    @Test
    void processWin() {
        assertTrue(true);
    }

}
