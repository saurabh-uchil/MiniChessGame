package model;

public abstract class Piece {
	protected String pieceId;
	protected String status;
	protected Square square;
	protected Player player;
	protected String imgPath;
	protected boolean[][] validMoves;
	
	//protected String type;
	//protected int playerNumber;
	
	public Piece(String id, String imagePath) {
		this.status = "available";
		this.validMoves = new boolean[6][6];
		this.pieceId = id;
		this.imgPath = imagePath;
		
		//this.type = type;
		//this.id = id;
	}
	
	public Piece() {
		
	}
	
	//Getters
	public String getId() {
		return pieceId;
	}
	
	public String getStatus() {
		return status;
	}
	
	public Square getSQuare() {
		return square;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public String getImagePath() {
		return imgPath;
	}
	
	public boolean[][] getValidMoves(){
		return validMoves;
	}
	
	//Setters
	public void setId(String Id) {
		this.pieceId = Id;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public void setSquare(Square currentSquare) {
		this.square = currentSquare;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public abstract void setValidMoves(Board b);
	
	
	public boolean isAvailable() {		
		if(this.square != null)
			return true;
		else 
			return false;
	}
	
	public boolean move(Square newPosition) {		
		if(isValidMove(newPosition)) {
			square.releaseSquare();
			newPosition.occupySquare(this);
			//setValidMoves(b);
			return true;
		}
		else
			return false;
	}
	
	public boolean isValidMove(Square newPosition) {
		if(validMoves[newPosition.getRowValue()][newPosition.getColumnValue()])
			return true;
		else
			return false;			
	};
	
	public String showValidMovesMatrix() {
		String matrix = "";
		
		for(int i = 0; i < validMoves.length; i++) {
			for(int j = 0; j < validMoves[0].length; j++) {
				if(validMoves[i][j])
					matrix += "1 ";
				else
					matrix += "0 ";
			}
			matrix += "\n";
		}
		
		return matrix;
	}
	
	public void resetValidMoves() {
		for(int i = 0; i < validMoves.length; i++) {
			for(int j = 0; j < validMoves[0].length; j++) {
				validMoves[i][j] = false;
			}
		}
	}
	
	
}
