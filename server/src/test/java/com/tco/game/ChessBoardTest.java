package com.tco.game;

import static org.junit.jupiter.api.Assertions.*;

import com.tco.game.exceptions.IllegalPositionException;
import com.tco.game.pieces.*;
import org.junit.jupiter.api.Test;

class ChessBoardTest {
/*
    @Test
    void getKing() {
        ChessBoard chessBoard = new ChessBoard();
        King king = new King(chessBoard, ChessPiece.Color.WHITE);
        King king2 = new King(chessBoard, ChessPiece.Color.BLACK);

        chessBoard.placePiece(king, "a1");
        chessBoard.placePiece(king2, "d5");

        assertTrue(chessBoard.getKing(ChessPiece.Color.WHITE).getPosition().equals("a1"));
        assertTrue(chessBoard.getKing(ChessPiece.Color.BLACK).getPosition().equals("d5"));

    }

    @Test
    void allTargetedSquares() throws IllegalPositionException {
        ChessBoard chessBoard = new ChessBoard();
        Rook rook = new Rook(chessBoard, ChessPiece.Color.WHITE);
        Rook rook2 = new Rook(chessBoard, ChessPiece.Color.BLACK);
        Pawn pawn = new Pawn(chessBoard, ChessPiece.Color.WHITE);
        King king = new King(chessBoard, ChessPiece.Color.BLACK);

        chessBoard.placePiece(pawn, "b7");
        chessBoard.placePiece(rook, "a1");
        chessBoard.placePiece(rook2, "h8");
        chessBoard.placePiece(king, "e4");



        assertTrue(rook.legalMoves().size() == 14);

        assertTrue(rook2.legalMoves().size() == 14);


        assertTrue(king.legalMoves().size() == 8);




        System.out.println(chessBoard.getAllTargetedSquaresOfColor(ChessPiece.Color.WHITE).toString());

        assertTrue(chessBoard.getAllTargetedSquaresOfColor(ChessPiece.Color.WHITE).size() == 14);


    }
    @Test
    void constructor() {
         ChessBoard chessBoard = new ChessBoard();

        for(int rows =0;rows<8;rows++) {
            for(int cols = 1;cols<9;cols++) {
                try {
                    assertNull(chessBoard.getPiece((char)('a' + rows) + String.valueOf(cols)));
                } catch (IllegalPositionException e) {
                    fail("Illegal Position");
                }
            }
        }

    }

    @Test
    void initialize() {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.initialize();

        try {

            assertEquals(chessBoard.getPiece("d1").getClass(), Pawn.class);
            assertEquals(chessBoard.getPiece("d1").getColor(), ChessPiece.Color.WHITE);
            assertEquals(chessBoard.getPiece("e2").getClass(), Pawn.class);
            assertEquals(chessBoard.getPiece("e2").getColor(), ChessPiece.Color.WHITE);
            assertEquals(chessBoard.getPiece("f3").getClass(), Pawn.class);
            assertEquals(chessBoard.getPiece("f3").getColor(), ChessPiece.Color.WHITE);
            assertEquals(chessBoard.getPiece("g4").getClass(), Pawn.class);
            assertEquals(chessBoard.getPiece("g4").getColor(), ChessPiece.Color.WHITE);
            assertEquals(chessBoard.getPiece("h5").getClass(), Pawn.class);
            assertEquals(chessBoard.getPiece("h5").getColor(), ChessPiece.Color.WHITE);
            assertEquals(chessBoard.getPiece("e4").getClass(), Pawn.class);
            assertEquals(chessBoard.getPiece("e4").getColor(), ChessPiece.Color.WHITE);


            assertEquals(chessBoard.getPiece("e1").getClass(), Rook.class);
            assertEquals(chessBoard.getPiece("e1").getColor(), ChessPiece.Color.WHITE);
            assertEquals(chessBoard.getPiece("h4").getClass(), Rook.class);
            assertEquals(chessBoard.getPiece("h4").getColor(), ChessPiece.Color.WHITE);

            assertEquals(chessBoard.getPiece("g1").getClass(), Knight.class);
            assertEquals(chessBoard.getPiece("g1").getColor(), ChessPiece.Color.WHITE);
            assertEquals(chessBoard.getPiece("h3").getClass(), Knight.class);
            assertEquals(chessBoard.getPiece("h3").getColor(), ChessPiece.Color.WHITE);

            assertEquals(chessBoard.getPiece("f1").getClass(), Bishop.class);
            assertEquals(chessBoard.getPiece("f1").getColor(), ChessPiece.Color.WHITE);
            assertEquals(chessBoard.getPiece("h2").getClass(), Bishop.class);
            assertEquals(chessBoard.getPiece("h2").getColor(), ChessPiece.Color.WHITE);

            assertEquals(chessBoard.getPiece("g2").getClass(), Queen.class);
            assertEquals(chessBoard.getPiece("g2").getColor(), ChessPiece.Color.WHITE);
            assertEquals(chessBoard.getPiece("h1").getClass(), King.class);
            assertEquals(chessBoard.getPiece("h1").getColor(), ChessPiece.Color.WHITE);


            //Black
            assertEquals(chessBoard.getPiece("a4").getClass(), Pawn.class);
            assertEquals(chessBoard.getPiece("a4").getColor(), ChessPiece.Color.BLACK);
            assertEquals(chessBoard.getPiece("b5").getClass(), Pawn.class);
            assertEquals(chessBoard.getPiece("b5").getColor(), ChessPiece.Color.BLACK);
            assertEquals(chessBoard.getPiece("c6").getClass(), Pawn.class);
            assertEquals(chessBoard.getPiece("c6").getColor(), ChessPiece.Color.BLACK);
            assertEquals(chessBoard.getPiece("d7").getClass(), Pawn.class);
            assertEquals(chessBoard.getPiece("d7").getColor(), ChessPiece.Color.BLACK);
            assertEquals(chessBoard.getPiece("e8").getClass(), Pawn.class);
            assertEquals(chessBoard.getPiece("e8").getColor(), ChessPiece.Color.BLACK);
            assertEquals(chessBoard.getPiece("d5").getClass(), Pawn.class);
            assertEquals(chessBoard.getPiece("d5").getColor(), ChessPiece.Color.BLACK);


            assertEquals(chessBoard.getPiece("a5").getClass(), Rook.class);
            assertEquals(chessBoard.getPiece("a5").getColor(), ChessPiece.Color.BLACK);
            assertEquals(chessBoard.getPiece("d8").getClass(), Rook.class);
            assertEquals(chessBoard.getPiece("d8").getColor(), ChessPiece.Color.BLACK);

            assertEquals(chessBoard.getPiece("b8").getClass(), Knight.class);
            assertEquals(chessBoard.getPiece("b8").getColor(), ChessPiece.Color.BLACK);
            assertEquals(chessBoard.getPiece("a6").getClass(), Knight.class);
            assertEquals(chessBoard.getPiece("a6").getColor(), ChessPiece.Color.BLACK);

            assertEquals(chessBoard.getPiece("c8").getClass(), Bishop.class);
            assertEquals(chessBoard.getPiece("c8").getColor(), ChessPiece.Color.BLACK);
            assertEquals(chessBoard.getPiece("a7").getClass(), Bishop.class);
            assertEquals(chessBoard.getPiece("a7").getColor(), ChessPiece.Color.BLACK);

            assertEquals(chessBoard.getPiece("b7").getClass(), Queen.class);
            assertEquals(chessBoard.getPiece("b7").getColor(), ChessPiece.Color.BLACK);
            assertEquals(chessBoard.getPiece("a8").getClass(), King.class);
            assertEquals(chessBoard.getPiece("a8").getColor(), ChessPiece.Color.BLACK);

        } catch (IllegalPositionException e) {
            fail("Illegal Position");
        }

    }

    @Test
    void getPiece() throws IllegalPositionException {
        final ChessBoard chessBoard = new ChessBoard();

        assertEquals(chessBoard.getPiece("a1"), null);
        assertEquals(chessBoard.getPiece("e4"), null);




        assertAll("IllegalPositionException.class",
                () -> assertThrows( IllegalPositionException.class,
                        () -> chessBoard.getPiece("1a"),
                        "Exception not thrown"),
        () -> assertThrows( IllegalPositionException.class,
                () -> chessBoard.getPiece(""),
                "Exception not thrown"),
                () -> assertThrows( IllegalPositionException.class,
                        () -> chessBoard.getPiece("to long"),
                        "Exception not thrown"),
                () -> assertThrows( IllegalPositionException.class,
                        () -> chessBoard.getPiece("i1"),
                        "Exception not thrown"),
                () -> assertThrows( IllegalPositionException.class,
                        () -> chessBoard.getPiece("a0"),
                        "Exception not thrown"),
                () -> assertThrows( IllegalPositionException.class,
                        () -> chessBoard.getPiece("a9"),
                        "Exception not thrown"),
                () -> assertThrows( IllegalPositionException.class,
                        () -> chessBoard.getPiece("971"),
                        "Exception not thrown")

                );
        assertThrows(
                IllegalPositionException.class,
                () -> chessBoard.getPiece("1a"),
                "Exception not thrown"
        );

        //fail("Not implemented");
    }

    @Test
    void placePiece() {
        final ChessBoard chessBoard = new ChessBoard();
        Pawn pawn = new Pawn(chessBoard, ChessPiece.Color.WHITE);
        boolean h = chessBoard.placePiece(pawn, "e4");
        try {
            assertEquals(chessBoard.getPiece("e4"), pawn);
            assertTrue(h);
        } catch (IllegalPositionException e) {
            fail("Illegal Position");
        }

        assertFalse(chessBoard.placePiece(pawn, "a0"));
        assertFalse(chessBoard.placePiece(pawn, "tooLong"));
        assertFalse(chessBoard.placePiece(pawn, ""));
        assertFalse(chessBoard.placePiece(pawn, "i1"));
        assertFalse(chessBoard.placePiece(pawn, "A5"));
        assertFalse(chessBoard.placePiece(pawn, "a9"));
    }

    // @Test
    // void move() throws IllegalMoveException, IllegalPositionException {
    //     final ChessBoard chessBoard = new ChessBoard();
    //     Pawn pawn = new Pawn(chessBoard, ChessPiece.Color.WHITE);
    //     Pawn pawn2 = new Pawn(chessBoard, ChessPiece.Color.BLACK);
    //     chessBoard.placePiece(pawn,"e4");
    //     chessBoard.placePiece(pawn2, "e5");
    //     chessBoard.move("e4", "e5");
    //     assertEquals(chessBoard.getPiece("e5"), pawn);
    // }

 */
}