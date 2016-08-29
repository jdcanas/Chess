package standard;

import common.ChessCoordinate;
import common.ChessException;
import common.ChessGame;
import common.ChessPiece;
import common.ChessPieceType;
import common.ChessPlayerColor;
import common.MoveResult;
import standard.validation.EndTurnValidator;
import standard.validation.PreTurnValidator;
import strategies.StandardEndTurnStrategy;

public class StandardChessGame implements ChessGame {
    StandardBoard board;
    ChessPlayerColor currTurn;
    
    PreTurnValidator preTurnValidator;
    EndTurnValidator endTurnValidator;
    
    StandardEndTurnStrategy gameStatusResolver;
  
	public StandardChessGame(ChessPlayerColor movesFirst) {
		board = new StandardBoard();
		currTurn = movesFirst;
		
		preTurnValidator = new PreTurnValidator();
		endTurnValidator = new EndTurnValidator();
		
		gameStatusResolver = new StandardEndTurnStrategy(board);
	}

	@Override
	public MoveResult makeMove(ChessPieceType pieceType, ChessCoordinate from, ChessCoordinate to)
			throws ChessException {
		
		preTurnValidator.validate(from, to, board);
		
		board.movePiece(new StandardPiece(currTurn, pieceType), to);
		
		endTurnValidator.validate(from, to, board);
		
		return gameStatusResolver.getResult();
	}

	@Override
	public ChessPiece getPieceAt(ChessCoordinate where) {
		return board.getPiece(where);
	}

	@Override
	public String getPrintableBoard() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
