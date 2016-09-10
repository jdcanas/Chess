package utilities;

import java.util.ArrayList;

import Controller.Controller;
import common.ChessCoordinate;
import common.ChessGameType;
import common.ChessPieceType;
import common.ChessPlayerColor;
import common.Move;
import common.MoveResult;
import standard.StandardBoard;
import standard.StandardChessGame;

public class ValidMoveGeneratorController {
	private static ValidMoveGeneratorController instance = null;
	private ValidMoveGenerator moveGenerator;
	private ArrayList<Move> whiteValidMoves = new ArrayList<Move>();
	private ArrayList<Move> blackValidMoves = new ArrayList<Move>();
	private ChessGameType gameType;
	private boolean isMovingIntoCheckValid;

	private ValidMoveGeneratorController() {
		moveGenerator = new ValidMoveGenerator(null, null, null, null, null, true);
	}
	
	public void refresh(ChessGameType gameType, StandardBoard board, boolean isMovingIntoCheckValid) {
		this.gameType = gameType;
		this.isMovingIntoCheckValid = isMovingIntoCheckValid;
		generateValidMoves(board);
	}
	
	public ArrayList<Move> getValidMoves(ChessPlayerColor color) {
		ArrayList<Move> moves = color == ChessPlayerColor.WHITE ? whiteValidMoves : blackValidMoves;
		return moves;
	}
	
	public ArrayList<Move> getWhiteValidMoves() {
		return whiteValidMoves;
	}
	
	public ArrayList<Move> getBlaxkValidMoves() {
		return blackValidMoves;
	}
	
	private void generateValidMoves(StandardBoard board) {
		whiteValidMoves = getMovesForColor(ChessPlayerColor.WHITE, board, isMovingIntoCheckValid);
		blackValidMoves = getMovesForColor(ChessPlayerColor.BLACK, board, isMovingIntoCheckValid);
	}
	
	public ArrayList<Move> getMovesForColor(ChessPlayerColor color, StandardBoard board, boolean isMovingIntoCheckValid) {
		ArrayList<ChessCoordinate> pieceLocs = getPiecesForColor(color, board);
		ArrayList<Move> moves = new ArrayList<Move>();
		
		for (ChessCoordinate c: pieceLocs) {
			moveGenerator.refresh(gameType, color, board.getPiece(c).getType(), c, board, isMovingIntoCheckValid);
			moves.addAll(moveGenerator.getValidMoves());
		}
		
		return moves;
	}
	
	public ArrayList<ChessCoordinate> getPiecesForColor(ChessPlayerColor color, StandardBoard board) {
		ArrayList<ChessCoordinate> newPieceLocs = new ArrayList<ChessCoordinate>();
		
		ArrayList<ChessCoordinate> pieceLocs = board.getPieceLocations();
		
		for (ChessCoordinate c: pieceLocs) {
			if (board.getPiece(c).getColor() == color) {
				newPieceLocs.add(c);
			}
		}
		
		return newPieceLocs;
	}
	


	/**
	 * Returns the single instance of the ViewEcentController.
	 * @return the instance of this controller.
	 */
	public static ValidMoveGeneratorController getInstance(){
		if(instance == null){
			instance = new ValidMoveGeneratorController();
		}
		return instance;
	}
}
