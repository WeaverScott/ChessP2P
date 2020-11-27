package chess;

import org.junit.Test;
import static org.junit.Assert.*;
public class Testing {
/**********************************************************************
 * A class in which we test to see if our pieces and other methods
 * work correctly.
 *
 * @author Logan Jaglowski, Sarah, and Lauren
 * @version Winter 2019
 *********************************************************************/

    //tests bishop movement
    @Test
    public void blackLeftBishopTest() {
        ChessModel test = new ChessModel();
        //move pawn out of way
        Move movePawn = new Move(1, 3, 2, 3);
        test.move(movePawn);
        //move bishop
        Move moveBishop = new Move(0, 2, 5, 7);
        test.move(moveBishop);
        assertEquals(test.pieceAt(5, 7).type(),
                new Bishop(Player.
                        BLACK).type());
    }

    //tests white bishop
    @Test
    public void whiteLeftBishopTest() {
        ChessModel test = new ChessModel();
        //move pawn out of way
        Move movePawn = new Move(6, 3, 5, 3);
        test.move(movePawn);
        //move bishop
        Move moveBishop = new Move(7, 2, 6, 3);
        test.move(moveBishop);
        assertEquals(test.pieceAt(6, 3).type(),
                new Bishop(Player.
                        WHITE).type());
    }

    //tests black pawn movement
    @Test
    public void testBlackPawn() {
        ChessModel test = new ChessModel();
        //move pawn out of way
        Move movePawn = new Move(1, 3, 2, 3);
        test.move(movePawn);
        assertEquals(test.pieceAt(2, 3).type(),
                new Pawn(Player.BLACK).
                        type());
    }

    //tests white pawn
    @Test
    public void testWhitePawn() {
        ChessModel test = new ChessModel();
        //move pawn
        Move movePawn = new Move(6, 3, 5, 3);
        test.move(movePawn);
        assertEquals(test.pieceAt(5, 3).type(),
                new Pawn(Player.WHITE).
                        type());
    }

    //tests black king movement
    @Test
    public void blackKingTest() {
        ChessModel test = new ChessModel();
        //move pawn out of way of king
        Move movePawn = new Move(1, 3, 2, 3);
        test.move(movePawn);
        //move king
        Move kingMove = new Move(0, 4, 1, 3);
        test.move(kingMove);
        assertEquals(test.pieceAt(1, 3).type(),
                new King(Player.BLACK).
                        type());
    }

    //tests white king
    @Test
    public void whiteKingTest() {
        ChessModel test = new ChessModel();
        //move pawn out of way of king
        Move movePawn = new Move(6, 3, 5, 3);
        test.move(movePawn);
        //move king
        Move kingMove = new Move(7, 4, 6, 3);
        test.move(kingMove);
        assertEquals(test.pieceAt(6, 3).type(),
                new King(Player.WHITE).
                        type());
    }

    //tests black queen movement
    @Test
    public void queenBlackTest() {
        ChessModel t = new ChessModel();
        //move pawn out of way
        Move movePawn = new Move(1, 3, 2, 3);
        t.move(movePawn);
        //move queen
        Move queenMove = new Move(0, 3, 1, 3);
        t.move(queenMove);
        assertEquals(t.pieceAt(1, 3).type(),
                new Queen(Player.BLACK).
                        type());
    }

    //tests white queen movement
    @Test
    public void queenWhiteTest() {
        ChessModel t = new ChessModel();
        //move pawn out of way
        Move movePawn = new Move(6, 3, 5, 3);
        t.move(movePawn);
        //move queen
        Move queenMove = new Move(7, 3, 6, 3);
        t.move(queenMove);
        assertEquals(t.pieceAt(6, 3).type(),
                new Queen(Player.WHITE).
                        type());
    }

    //tests black knight movement
    @Test
    public void knightBlackTest() {
        ChessModel test = new ChessModel();
        //move knight
        Move knightMove = new Move(0, 1, 2, 0);
        test.move(knightMove);
        assertEquals(test.pieceAt(2, 0).type(),
                new Knight(Player.
                        BLACK).type());
    }

    //tests white knight movement
    @Test
    public void WhiteKnightTest() {
        ChessModel test = new ChessModel();
        //move knight
        Move knightMove = new Move(7, 6, 5, 5);
        test.move(knightMove);
        assertEquals(test.pieceAt(5, 5).type(),
                new Knight(Player.
                        WHITE).type());
    }

    //tests black rook movement
    @Test
    public void rookBlackTest() {
        ChessModel test = new ChessModel();
        //move pawn out of way
        Move movePawn = new Move(1, 0, 3, 0);
        test.move(movePawn);
        //move rook
        Move rookMove = new Move(0, 0, 1, 0);
        test.move(rookMove);
        assertEquals(test.pieceAt(1, 0).type(),
                new Rook(Player.BLACK).
                        type());
    }

    //test white rook
    @Test
    public void rookWhiteTest() {
        ChessModel test = new ChessModel();
        //move pawn out of way
        Move movePawn = new Move(6, 0, 5, 0);
        test.move(movePawn);
        //move rook
        Move rookMove = new Move(7, 0, 6, 0);
        test.move(rookMove);
        assertEquals(test.pieceAt(6, 0).type(),
                new Rook(Player.WHITE).
                        type());
    }

    //tests constructor and currentPlayer()
    @Test
    public void constructorTest() {
        ChessModel test = new ChessModel();
        assertEquals(test.currentPlayer(), Player.WHITE);
    }

    //tests pieceAt() (extraneous, method is used in previous tests)
    @Test
    public void pieceAtTest() {
        ChessModel test = new ChessModel();
        assertEquals(test.pieceAt(1, 1).type(),
                new Pawn(Player.BLACK)
                        .type());
    }

    //tests setNextPlayer() on white player
    @Test
    public void testNextPlayerWhite() {
        ChessModel test = new ChessModel();
        test.setNextPlayer();
        assertEquals(test.currentPlayer(), Player.BLACK);
    }

    //tests setNextPlayer() on black player
    @Test
    public void testNextPlayerBlack() {
        ChessModel test = new ChessModel();
        test.setNextPlayer();
        test.setNextPlayer();
        assertEquals(test.currentPlayer(), Player.WHITE);
    }

    //tests setPiece on occupied square (board [1][1])
    @Test
    public void testSetPieceKing() {
        ChessModel test = new ChessModel();
        test.setPiece(1, 1, new King(Player.BLACK));
        assertEquals(test.pieceAt(1, 1).type(),
                new King(Player.BLACK).type());
    }

    //tests normal move
    @Test
    public void moveTest() {
        ChessModel test = new ChessModel();
        Move m = new Move(1, 1, 2, 1);
        test.move(m);
        assertEquals(test.pieceAt(2, 1).type(),
                new Pawn(Player.BLACK).type());
    }

    //tests move that lands on own piece
    @Test
    public void moveToSelf() {
        ChessModel test = new ChessModel();
        Move self = new Move(0, 0, 1, 1);
        assertFalse(test.isValidMove(self).isMoveSuccessful());
    }

    //tests isValidMove() for when player is in check
    @Test
    public void isValidCheck() {
        ChessModel test = new ChessModel();
        //deleting all pieces not needed for check
        test.setPiece(0, 1, null);
        test.setPiece(0, 2, null);
        test.setPiece(0, 3, null);
        test.setPiece(0, 5, null);
        test.setPiece(0, 6, null);
        test.setPiece(1, 0, null);
        test.setPiece(1, 1, null);
        test.setPiece(1, 2, null);
        test.setPiece(1, 3, null);
        test.setPiece(1, 4, null);
        test.setPiece(1, 5, null);
        test.setPiece(1, 6, null);
        test.setPiece(1, 7, null);
        test.setPiece(6, 0, null);
        test.setPiece(6, 1, null);
        test.setPiece(6, 2, null);
        test.setPiece(6, 3, null);
        test.setPiece(6, 4, null);
        test.setPiece(6, 5, null);
        test.setPiece(6, 6, null);
        test.setPiece(6, 7, null);
        test.setPiece(7, 0, null);
        test.setPiece(7, 1, null);
        test.setPiece(7, 2, null);
        test.setPiece(7, 5, null);
        test.setPiece(7, 6, null);
        test.setPiece(7, 7, null);
        //moving pieces into check
        Move lRook = new Move(0, 0, 0, 2);
        test.move(lRook);
        Move lDownRk = new Move(0, 2, 1, 2);
        test.move(lDownRk);
        Move rRook = new Move(0, 7, 0, 5);
        test.move(rRook);
        Move rDownRk = new Move(0, 5, 1, 5);
        Move king = new Move(0, 4, 1, 4);
        Move qOne = new Move(7, 3, 3, 3);
        test.move(qOne);
        Move qTwo = new Move(3, 3, 3, 4);
        test.move(qTwo);
        test.setNextPlayer();
        assertTrue(test.isValidMove(king).isInCheck());
    }


    //tests isComplete() when game is incomplete
    @Test
    public void isCompleteTest() {
        ChessModel test = new ChessModel();
        assertFalse(test.isComplete());
    }

    //tests inCheck() when king is in checkmate
    @Test
    public void inCheckTrue() {
        ChessModel test = new ChessModel();
        //setting all unneeded pieces for checkmate to null
        test.setPiece(0, 1, null);
        test.setPiece(0, 2, null);
        test.setPiece(0, 3, null);
        test.setPiece(0, 5, null);
        test.setPiece(0, 6, null);
        test.setPiece(1, 0, null);
        test.setPiece(1, 1, null);
        test.setPiece(1, 2, null);
        test.setPiece(1, 3, null);
        test.setPiece(1, 4, null);
        test.setPiece(1, 5, null);
        test.setPiece(1, 6, null);
        test.setPiece(1, 7, null);
        test.setPiece(6, 0, null);
        test.setPiece(6, 1, null);
        test.setPiece(6, 2, null);
        test.setPiece(6, 3, null);
        test.setPiece(6, 4, null);
        test.setPiece(6, 5, null);
        test.setPiece(6, 6, null);
        test.setPiece(6, 7, null);
        test.setPiece(7, 0, null);
        test.setPiece(7, 1, null);
        test.setPiece(7, 2, null);
        test.setPiece(7, 5, null);
        test.setPiece(7, 6, null);
        test.setPiece(7, 7, null);
        //moving pieces into the check
        Move lRook = new Move(0, 0, 0, 3);
        test.move(lRook);
        Move rRook = new Move(0, 7, 0, 5);
        test.move(rRook);
        Move qOne = new Move(7, 3, 2, 3);
        test.move(qOne);
        Move qTwo = new Move(2, 3, 2, 4);
        test.move(qTwo);
        //finally testing
        assertTrue(test.inCheck(Player.BLACK));
    }

    //tests saveLastMove and undoLastMove
    @Test
    public void saveUndoMoveTest() {
        ChessModel t = new ChessModel();
        //moves a knight
        Move m = new Move(0, 1, 2, 0);
        // t.move(m);
        t.saveLastMove(m);
        t.undoLastMove(m);
        assertEquals(t.pieceAt(m.fromRow, m.fromColumn).type(),
                new Knight(Player.BLACK).type());
    }

    //tests setPlayer
    @Test
    public void setPlayerTEst() {
        ChessModel t = new ChessModel();
        t.setPlayer(Player.BLACK);
        assertTrue(t.currentPlayer().equals(Player.BLACK));
    }

    //tests castling
    @Test
    public void castlingTest() {
        ChessModel t = new ChessModel();
        //removes obstructive pieces
        t.setPiece(7, 5, null);
        t.setPiece(7, 6, null);
        //moves the king and calls the castling method
        Move king = new Move(7, 4, 7, 6);
        t.move(king);
        t.rookCastling(king);
        //checks that aim squares are now rook and king
        assertTrue(t.pieceAt(7, 5).type().equals("Rook"));
        assertTrue(t.pieceAt(7, 6).type().equals("King"));
    }

    //tests en passant
    @Test
    public void enPassantTest() {
        ChessModel t = new ChessModel();
        t.setPiece(3, 5, new Pawn(Player.WHITE));
        Move blackPawnMove = new Move(1, 6, 3, 6);
        t.setLastMove(blackPawnMove);
        t.move(blackPawnMove);
        Move whitePawnMove = new Move(3, 5, 2, 6);
        t.setCurrentMove(whitePawnMove);
        if (t.isEnPassant(t.getCurrentMove(), t.getLastMove())) {
            t.removeFromBoard(t.getLastMove().toRow, t.getLastMove().
                    toColumn);
        }
        t.move(whitePawnMove);
        assertTrue(t.pieceAt(2, 6).type().equals("Pawn"));
        assertTrue(t.pieceAt(3, 6) == null);
    }

    //tests the pawn promotion
    @Test
    public void testPawnPromotion() {
    ChessModel t = new ChessModel();
    t.setPiece(1,0,new Pawn(Player.WHITE));
    t.setPiece(0,0,null);
    Move pawn = new Move(1,0,0,0);
    t.move(pawn);
    t.pawnPromoted(pawn);
}

    //tests the checkmate
    @Test
    public void testWhiteIsComplete() {
        ChessModel test = new ChessModel();
        test.setPiece(0,0, null);
        test.setPiece(0, 1, null);
        test.setPiece(0, 2, null);
        test.setPiece(0, 3, null);
        test.setPiece(0, 5, null);
        test.setPiece(0, 6, null);
        test.setPiece(0,7, null);
        test.setPiece(1, 0, null);
        test.setPiece(1, 1, null);
        test.setPiece(1, 2, null);
        test.setPiece(1, 3, null);
        test.setPiece(1, 4, null);
        test.setPiece(1, 5, null);
        test.setPiece(1, 6, null);
        test.setPiece(1, 7, null);
        test.setPiece(1,0,new Queen(Player.WHITE));
        test.setPiece(0,1, new Queen(Player.WHITE));
        assertTrue(test.isComplete());
    }

    //tests the checkmate
    @Test
    public void testBlackIsComplete() {
        ChessModel test = new ChessModel();
        test.setPiece(7,0, null);
        test.setPiece(7, 1, null);
        test.setPiece(7, 2, null);
        test.setPiece(7, 3, null);
        test.setPiece(7, 5, null);
        test.setPiece(7, 6, null);
        test.setPiece(7,7, null);
        test.setPiece(6, 0, null);
        test.setPiece(6, 1, null);
        test.setPiece(6, 2, null);
        test.setPiece(6, 3, null);
        test.setPiece(6, 4, null);
        test.setPiece(6, 5, null);
        test.setPiece(6, 6, null);
        test.setPiece(6, 7, null);
        test.setPiece(7,0,new Queen(Player.BLACK));
        test.setPiece(6,1, new Queen(Player.BLACK));
        assertTrue(test.isComplete());
    }

    //tests if a move is dangerous or not
    @Test
    public void testIsDangerous() {
        ChessModel test = new ChessModel();
        assertFalse(test.isDangerous(1,6));
    }
}


