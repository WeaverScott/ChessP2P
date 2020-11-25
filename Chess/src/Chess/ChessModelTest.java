package Chess;

import org.junit.Test;

import static org.junit.Assert.*;

public class ChessModelTest {

    @Test
    public void pieceTypes() {
        ChessModel test = new ChessModel();
        int pieceTypeCount = 0;

        if (test.pieceAt(0, 0).type().equals("Rook")) {
            pieceTypeCount += 1;
        }
        if (test.pieceAt(0, 1).type().equals("Knight")) {
            pieceTypeCount += 1;
        }
        if (test.pieceAt(0, 2).type().equals("Bishop")) {
            pieceTypeCount += 1;
        }
        if (test.pieceAt(0, 3).type().equals("King")) {
            pieceTypeCount += 1;
        }
        if (test.pieceAt(0, 4).type().equals("Queen")) {
            pieceTypeCount += 1;
        }
        if (test.pieceAt(1, 0).type().equals("Pawn")) {
            pieceTypeCount += 1;
        }

        assertTrue(pieceTypeCount == 6);
    }

    @Test
    public void whitePawnOneSpaceUpFirstMove() {
        ChessModel test = new ChessModel();

        test.move(new Move(6, 0, 5, 0));
        assertTrue(test.pieceAt(5, 0).type().equals("Pawn") && test.pieceAt(5, 0).player() == Player.WHITE);
    }

    @Test
    public void whitePawnTwoSpacesUpFirstMove() {
        ChessModel test = new ChessModel();

        test.move(new Move(6, 0, 4, 0));
        assertTrue(test.pieceAt(4, 0).type().equals("Pawn") && test.pieceAt(4, 0).player() == Player.WHITE);
    }

    @Test
    public void whitePawnTakeUpRight() {
        ChessModel test = new ChessModel();
        test.setPiece(5, 1, new Pawn(Player.BLACK));

        test.move(new Move(6, 0, 5, 1));
        assertTrue(test.pieceAt(5, 1).type().equals("Pawn") && test.pieceAt(5, 1).player() == Player.WHITE);
    }

    @Test
    public void whitePawnTakeUpLeft() {
        ChessModel test = new ChessModel();
        test.setPiece(5, 0, new Pawn(Player.BLACK));

        test.move(new Move(6, 1, 5, 0));
        assertTrue(test.pieceAt(5, 0).type().equals("Pawn") && test.pieceAt(5, 0).player() == Player.WHITE);
    }

    @Test
    public void pawnInvalidMoves() {
        ChessModel test = new ChessModel();
        test.setPiece(4, 1, new Pawn(Player.BLACK));
        test.setPiece(4, 5, new Pawn(Player.WHITE));

        int invalidCount = 0;

        // black pawn invalid movement (8 tests)
        if (!test.isValidMove(new Move(4, 1, 4, 0))) {   // black pawn left
            invalidCount += 1;
        }
        if (!test.isValidMove(new Move(4, 1, 4, 2))) {   // black pawn right
            invalidCount += 1;
        }
        if (!test.isValidMove(new Move(4, 1, 3, 1))) {   // black pawn up
            invalidCount += 1;
        }
        if (!test.isValidMove(new Move(4, 1, 3, 0))) {   // black pawn up left
            invalidCount += 1;
        }
        if (!test.isValidMove(new Move(4, 1, 3, 2))) {   // black pawn up right
            invalidCount += 1;
        }
        if (!test.isValidMove(new Move(4, 1, 5, 2))) {   // black pawn down right
            invalidCount += 1;
        }
        if (!test.isValidMove(new Move(4, 1, 5, 0))) {   // black pawn down left
            invalidCount += 1;
        }
        if (!test.isValidMove(new Move(4, 1, 6, 1))) {   // black pawn down two after first turn
            invalidCount += 1;
        }


        // white pawn invalid movement (8 tests)
        if (!test.isValidMove(new Move(4, 5, 4, 4))) {   // white pawn left
            invalidCount += 1;
        }
        if (!test.isValidMove(new Move(4, 5, 4, 6))) {   // white pawn right
            invalidCount += 1;
        }
        if (!test.isValidMove(new Move(4, 5, 5, 5))) {   // white pawn down
            invalidCount += 1;
        }
        if (!test.isValidMove(new Move(4, 5, 5, 4))) {   // white pawn down left
            invalidCount += 1;
        }
        if (!test.isValidMove(new Move(4, 5, 5, 6))) {   // white pawn down right
            invalidCount += 1;
        }
        if (!test.isValidMove(new Move(4, 5, 3, 6))) {   // white pawn up right
            invalidCount += 1;
        }
        if (!test.isValidMove(new Move(4, 5, 3, 4))) {   // white pawn up left
            invalidCount += 1;
        }
        if (!test.isValidMove(new Move(4, 5, 2, 5))) {   // white pawn up two after first turn
            invalidCount += 1;
        }

        assertTrue(invalidCount == 16);
    }

    @Test
    public void pawnInvalidJump() {
        ChessModel test = new ChessModel();
        test.setPiece(6, 1, new Pawn(Player.BLACK));
        test.setPiece(5, 1, new Pawn(Player.WHITE));

        int invalidCount = 0;

        // black pawn invalid jump movement
        if (!test.isValidMove(new Move(6, 1, 4, 1))) {
            invalidCount += 1;
        }
        // white pawn invalid jump movement
        if (!test.isValidMove(new Move(5, 1, 7, 1))) {
            invalidCount += 1;
        }

        assertTrue(invalidCount == 2);
    }

    @Test
    public void bishopInvalidMoves() {
        ChessModel test = new ChessModel();
        test.setPiece(4, 1, new Bishop(Player.BLACK));

        int invalidCount = 0;

        // black bishop invalid movement (4 tests)
        if (!test.isValidMove(new Move(4, 1, 4, 0))) {   // bishop left
            invalidCount += 1;
        }
        if (!test.isValidMove(new Move(4, 1, 4, 2))) {   // bishop right
            invalidCount += 1;
        }
        if (!test.isValidMove(new Move(4, 1, 3, 1))) {   // bishop up
            invalidCount += 1;
        }
        if (!test.isValidMove(new Move(4, 1, 5, 1))) {   // bishop down
            invalidCount += 1;
        }

        assertTrue(invalidCount == 4);
    }

    @Test
    public void bishopValidMoves() {
        ChessModel test = new ChessModel();
        test.setPiece(4, 1, new Bishop(Player.BLACK));

        int validCount = 0;

        // bishop valid movement
        if (test.isValidMove(new Move(4, 1, 5, 0))) {   // bishop up-left
            validCount += 1;
        }
        if (test.isValidMove(new Move(4, 1, 5, 2))) {   // bishop up-right
            validCount += 1;
        }
        if (test.isValidMove(new Move(4, 1, 3, 2))) {   // bishop down-right
            validCount += 1;
        }
        if (test.isValidMove(new Move(4, 1, 3, 0))) {   // bishop down-left
            validCount += 1;
        }

        assertTrue(validCount == 4);
    }

    @Test
    public void rookInvalidMovement() {
        ChessModel test = new ChessModel();
        test.setPiece(4, 1, new Rook(Player.BLACK));
        test.setPiece(4, 5, new Rook(Player.WHITE));

        int invalidCount = 0;

        // black rook invalid movement (4 tests)
        if (!test.isValidMove(new Move(4, 1, 3, 0))) {   // black rook up left
            invalidCount += 1;
        }
        if (!test.isValidMove(new Move(4, 1, 3, 2))) {   // black rook up right
            invalidCount += 1;
        }
        if (!test.isValidMove(new Move(4, 1, 5, 2))) {   // black rook down right
            invalidCount += 1;
        }
        if (!test.isValidMove(new Move(4, 1, 5, 0))) {   // black rook down left
            invalidCount += 1;
        }

        assertTrue(invalidCount == 4);
    }

    @Test
    public void rookValidMoves() {
        ChessModel test = new ChessModel();
        test.setPiece(4, 1, new Rook(Player.BLACK));

        int validCount = 0;

        // rook valid movement
        if (test.isValidMove(new Move(4, 1, 3, 1))) {   // rook up
            validCount += 1;
        }
        if (test.isValidMove(new Move(4, 1, 4, 2))) {   // rook right
            validCount += 1;
        }
        if (test.isValidMove(new Move(4, 1, 5, 1))) {   // rook down
            validCount += 1;
        }
        if (test.isValidMove(new Move(4, 1, 4, 0))) {   // rook left
            validCount += 1;
        }

        assertTrue(validCount == 4);
    }

    @Test
    public void knightInvalidMovement() {
        ChessModel test = new ChessModel();
        test.setPiece(4, 1, new Knight(Player.BLACK));

        int invalidCount = 0;

        // knight invalid movement (7 tests)
        if (!test.isValidMove(new Move(4, 1, 4, 0))) {   // knight left
            invalidCount += 1;
        }
        if (!test.isValidMove(new Move(4, 1, 4, 2))) {   // knight right
            invalidCount += 1;
        }
        if (!test.isValidMove(new Move(4, 1, 3, 1))) {   // knight up
            invalidCount += 1;
        }
        if (!test.isValidMove(new Move(4, 1, 3, 0))) {   // knight up left
            invalidCount += 1;
        }
        if (!test.isValidMove(new Move(4, 1, 3, 2))) {   // knight up right
            invalidCount += 1;
        }
        if (!test.isValidMove(new Move(4, 1, 5, 2))) {   // knight down right
            invalidCount += 1;
        }
        if (!test.isValidMove(new Move(4, 1, 5, 0))) {   // knight down left
            invalidCount += 1;
        }

        assertTrue(invalidCount == 7);
    }

    @Test
    public void knightValidMoves() {
        ChessModel test = new ChessModel();
        test.setPiece(4, 1, new Knight(Player.BLACK));

        int validCount = 0;

        // knight valid movement
        if (test.isValidMove(new Move(4, 1, 2, 0))) {   // knight up2 left1
            validCount += 1;
        }
        if (test.isValidMove(new Move(4, 1, 2, 2))) {   // knight up2 right1
            validCount += 1;
        }
        if (test.isValidMove(new Move(4, 1, 6, 0))) {   // knight down2 left1
            validCount += 1;
        }
        if (test.isValidMove(new Move(4, 1, 6, 2))) {   // knight down2 right1
            validCount += 1;
        }

        test.setPiece(4, 2, new Knight(Player.BLACK));
        if (test.isValidMove(new Move(4, 2, 3, 0))) {   // knight up1 left2
            validCount += 1;
        }
        if (test.isValidMove(new Move(4, 2, 3, 4))) {   // knight up1 right2
            validCount += 1;
        }
        if (test.isValidMove(new Move(4, 2, 5, 0))) {   // knight down1 left2
            validCount += 1;
        }
        if (test.isValidMove(new Move(4, 2, 5, 4))) {   // knight down1 right2
            validCount += 1;
        }

        assertTrue(validCount == 8);
    }

    @Test
    public void kingInvalidMovement() {
        ChessModel test = new ChessModel();
        test.setPiece(4, 2, new Knight(Player.BLACK));

        int invalidCount = 0;

        // black king invalid movement (8 tests)
        if (!test.isValidMove(new Move(4, 2, 4, 0))) {   // black king left 2
            invalidCount += 1;
        }
        if (!test.isValidMove(new Move(4, 2, 4, 4))) {   // black king right 2
            invalidCount += 1;
        }
        if (!test.isValidMove(new Move(4, 2, 2, 2))) {   // black king up 2
            invalidCount += 1;
        }
        if (!test.isValidMove(new Move(4, 2, 6, 2))) {   // black king down 2
            invalidCount += 1;
        }
        if (!test.isValidMove(new Move(4, 2, 2, 0))) {   // black king up 2 left 2
            invalidCount += 1;
        }
        if (!test.isValidMove(new Move(4, 2, 2, 4))) {   // black king up 2 right 2
            invalidCount += 1;
        }
        if (!test.isValidMove(new Move(4, 2, 6, 4))) {   // black king down 2 right 2
            invalidCount += 1;
        }
        if (!test.isValidMove(new Move(4, 2, 6, 0))) {   // black king down 2 left 2
            invalidCount += 1;
        }

        assertTrue(invalidCount == 8);
    }

    @Test
    public void kingValidMoves() {
        ChessModel test = new ChessModel();
        test.setPiece(4, 1, new King(Player.BLACK));

        int validCount = 0;

        // knight valid movement
        if (test.isValidMove(new Move(4, 1, 3, 1))) {   // king up
            validCount += 1;
        }
        if (test.isValidMove(new Move(4, 1, 3, 2))) {   // king up right
            validCount += 1;
        }
        if (test.isValidMove(new Move(4, 1, 4, 2))) {   // king right
            validCount += 1;
        }
        if (test.isValidMove(new Move(4, 1, 5, 2))) {   // king down right
            validCount += 1;
        }
        if (test.isValidMove(new Move(4, 1, 5, 1))) {   // king down
            validCount += 1;
        }
        if (test.isValidMove(new Move(4, 1, 5, 0))) {   // king down left
            validCount += 1;
        }
        if (test.isValidMove(new Move(4, 1, 4, 0))) {   // king left
            validCount += 1;
        }
        if (test.isValidMove(new Move(4, 1, 3, 0))) {   // king up left
            validCount += 1;
        }

        assertTrue(validCount == 8);
    }

//    @Test
//    public void queenInvalidMovement() {
//        ChessModel test = new ChessModel();
//        test.setPiece(6, 1, new Queen(Player.BLACK));
//        test.setPiece(5, 1, new Queen(Player.WHITE));
//
//        int invalidCount = 0;
//
//        // black pawn invalid jump movement
//        if (!test.isValidMove(new Move(6, 1, 4, 1))) {
//            invalidCount += 1;
//        }
//        // white pawn invalid jump movement
//        if (!test.isValidMove(new Move(5, 1, 7, 1))) {
//            invalidCount += 1;
//        }
//
//        assertTrue(invalidCount == 2);
//    }

    @Test
    public void notInCheckTest() {
        ChessModel test = new ChessModel();

        assertTrue(!test.inCheck(Player.BLACK));
    }

    @Test
    public void inCheckAndCheckmateBlackTest() {
        ChessModel test = new ChessModel();

        // leave only black king
        test.setPiece(0, 0, null);
        test.setPiece(0, 1, null);
        test.setPiece(0, 2, null);
        test.setPiece(0, 4, null);
        test.setPiece(0, 5, null);
        test.setPiece(0, 6, null);
        test.setPiece(0, 7, null);
        test.setPiece(1, 0, null);
        test.setPiece(1, 1, null);
        test.setPiece(1, 2, null);
        test.setPiece(1, 3, null);
        test.setPiece(1, 4, null);
        test.setPiece(1, 5, null);
        test.setPiece(1, 6, null);
        test.setPiece(1, 7, null);


        // make row of white queens so black king can't go anywhere
        test.setPiece(3, 0, new Queen(Player.WHITE));
        test.setPiece(3, 1, new Queen(Player.WHITE));
        test.setPiece(3, 2, new Queen(Player.WHITE));
        test.setPiece(3, 3, new Queen(Player.WHITE));
        test.setPiece(3, 4, new Queen(Player.WHITE));
        test.setPiece(3, 5, new Queen(Player.WHITE));
        test.setPiece(3, 6, new Queen(Player.WHITE));
        test.setPiece(3, 7, new Queen(Player.WHITE));

        assertTrue(test.inCheck(Player.BLACK));
        assertTrue(test.isComplete());
    }

    @Test
    public void inCheckAndCheckmateWhiteTest() {
        ChessModel test = new ChessModel();

        // leave only white king
        test.setPiece(7, 0, null);
        test.setPiece(7, 1, null);
        test.setPiece(7, 2, null);
        test.setPiece(7, 3, null);
        test.setPiece(7, 5, null);
        test.setPiece(7, 6, null);
        test.setPiece(7, 7, null);
        test.setPiece(6, 0, null);
        test.setPiece(6, 1, null);
        test.setPiece(6, 2, null);
        test.setPiece(6, 3, null);
        test.setPiece(6, 4, null);
        test.setPiece(6, 5, null);
        test.setPiece(6, 6, null);
        test.setPiece(6, 7, null);


        // make row of black queens so white king can't go anywhere
        test.setPiece(3, 0, new Queen(Player.BLACK));
        test.setPiece(3, 1, new Queen(Player.BLACK));
        test.setPiece(3, 2, new Queen(Player.BLACK));
        test.setPiece(3, 3, new Queen(Player.BLACK));
        test.setPiece(3, 4, new Queen(Player.BLACK));
        test.setPiece(3, 5, new Queen(Player.BLACK));
        test.setPiece(3, 6, new Queen(Player.BLACK));
        test.setPiece(3, 7, new Queen(Player.BLACK));

        test.move(new Move(7, 4, 6, 4));

        assertTrue(test.inCheck(Player.WHITE));
        assertTrue(test.isComplete());
    }

    @Test
    public void notInCheckmateTest() {
        ChessModel test = new ChessModel();


        assertTrue(!test.isComplete());
    }

    @Test
    public void setPlayerWhiteTest() {
        ChessModel test = new ChessModel();

        test.setPlayerWhite();

        assertTrue(test.currentPlayer() == Player.WHITE);
    }

    @Test
    public void setNextPlayerTest1() {
        ChessModel test = new ChessModel();

        test.setNextPlayer();

        assertTrue(test.currentPlayer() == Player.BLACK);
    }

    @Test
    public void setNextPlayerTest2() {
        ChessModel test = new ChessModel();

        test.setNextPlayer();
        test.setNextPlayer();

        assertTrue(test.currentPlayer() == Player.WHITE);
    }

    @Test
    public void numRowsTest() {
        ChessModel test = new ChessModel();

        assertTrue(test.numRows() == 8);
    }

    @Test
    public void numColsTest() {
        ChessModel test = new ChessModel();

        assertTrue(test.numColumns() == 8);
    }

    @Test
    public void setBlackKingLocTest() {
        ChessModel test = new ChessModel();

        test.setBlackKingLocs(0, 3);

        assertTrue(test.pieceAt(0, 3).type().equals("King") && test.pieceAt(0,3).player() == Player.BLACK);
    }

    @Test
    public void setWhiteKingLocTest() {
        ChessModel test = new ChessModel();

        test.setWhiteKingLocs(7, 4);

        assertTrue(test.pieceAt(7, 4).type().equals("King") && test.pieceAt(7,4).player() == Player.WHITE);
    }

    @Test
    public void moveConstructorTest() {
        Move testMove = new Move();
        assertTrue(testMove.fromRow == 0 && testMove.fromColumn == 0 && testMove.toRow == 0 && testMove.toColumn == 0);
    }

    @Test
    public void moveToStringTest() {
        Move testMove = new Move();
        assertTrue(testMove.toString().equals("Move [fromRow=0, fromColumn=0, toRow=0, toColumn=0]"));
    }
}