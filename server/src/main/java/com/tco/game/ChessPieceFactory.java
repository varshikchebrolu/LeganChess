package com.tco.game;

import com.tco.game.pieces.*;

public class ChessPieceFactory {

    public ChessPiece newChesspiece(ChessBoard board, char type){

        if(type == 'K'){
           return new King(board, ChessPiece.Color.WHITE);
        }else if(type == 'Q'){
            return new Queen(board, ChessPiece.Color.WHITE);
        }else if(type == 'R'){
            return new Rook(board, ChessPiece.Color.WHITE);
        }else if(type == 'N'){
            return new Knight(board, ChessPiece.Color.WHITE);
        }else if(type == 'B'){
            return new Bishop(board, ChessPiece.Color.WHITE);
        }else if(type == 'P'){
            return new Pawn(board, ChessPiece.Color.WHITE);
        }else if(type == 'k'){
            return new King(board, ChessPiece.Color.BLACK);
        }else if(type == 'q'){
            return new Queen(board, ChessPiece.Color.BLACK);
        }else if(type == 'r'){
            return new Rook(board, ChessPiece.Color.BLACK);
        }else if(type == 'n'){
            return new Knight(board, ChessPiece.Color.BLACK);
        }else if(type == 'b'){
            return new Bishop(board, ChessPiece.Color.BLACK);
        }else if(type == 'p'){
            return new Pawn(board, ChessPiece.Color.BLACK);
        }else{ return null;}

    }
}
