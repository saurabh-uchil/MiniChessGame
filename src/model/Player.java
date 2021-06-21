package model;
import java.util.ArrayList;

public class Player {
	private String userId;
	private int points;
	private int gameCount;
	private int winCount;
	private String color;
	//private boolean isTurn;
	private ArrayList<Piece> piecesList;
	
	public Player(String userId, String color) {
		this.userId = userId;
		this.points = 0;
		this.gameCount = 0;
		this.winCount = 0;
		piecesList = new ArrayList<Piece>();
	}
	
	//Accessors
	public String getUserId() {
		return userId;
	}
	
	public int getPoints() {
		return points;
	}
	
	public int getGameCount() {
		return gameCount;
	}
	
	public int getWinCount() {
		return winCount;
	}
	
//	public boolean getIsTurn() {
//		return isTurn;
//	}
	
	public String getColor() {
		return this.color;
	}
	
	public ArrayList<Piece> getPieceList(){
		return piecesList;
	}
	
	//Mutators
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public void setPoints(int points) {
		this.points = points;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
//	public void setIsTurn(boolean isTurn) {
//		this.isTurn = isTurn; 
//	}
	
	public void addPoints() {
		this.points += 5;
	}
	
	public void addGameCount() {
		this.gameCount += 1;
	}
	
	public void addWinCount() {
		this.gameCount += 1;
	}
	
	public void addPiece(Piece p) {
		piecesList.add(p);
		p.setPlayer(this);
	}
	
	
	public String getAvailablePieceIds() {
		String IdList= "";
		for(Piece p : piecesList) {
			if(p.isAvailable())
				IdList += p.getId() + ',';
		}
		
		return IdList.substring(0, IdList.length() - 1);
	}
	
}
