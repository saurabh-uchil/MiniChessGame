package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Board;
import model.Piece;
import view.ChessGameMainApp;
import view.SquareStack;

public class SelectionController implements EventHandler<MouseEvent> {

	private ChessGameMainApp mainApp;

	public SelectionController(ChessGameMainApp mainApp) {
		this.mainApp = mainApp;
	}

	@Override // Override the handle method
	public void handle(MouseEvent e) {
		Board board = mainApp.getBoard();

		SquareStack selectedSquare = (SquareStack) e.getSource();
		int row = selectedSquare.getRow();
		int column = selectedSquare.getColumn();
		SquareStack sqr;

		// Clicking on already selected square
		if (selectedSquare.getIsSelected()) {
			clearSelection();
		}
		// Move if selected destination is valid
		else if (selectedSquare.getIsValidMove()) {

			int oldRow = mainApp.getSelectedPiece().getSQuare().getRowValue();
			int oldColumn = mainApp.getSelectedPiece().getSQuare().getColumnValue();

			Piece removedPiece = mainApp.getBoard().getSquare(row, column).getPiece();

			// Move the piece to a valid location
			mainApp.getSelectedPiece().move(board.getSquare(row, column));

			board.getSquare(row, column).getPiece().setValidMoves(board);

			SquareStack oldSqr = (SquareStack) getNodeById("#SQ_" + oldRow + "_" + oldColumn);
			oldSqr.setPiece(null);

			SquareStack newSqr = (SquareStack) getNodeById("#SQ_" + row + "_" + column);
			newSqr.setPiece(board.getSquare(row, column).getPiece().getImagePath());

			if (removedPiece != null) {
				VBox vbRemoved;
				if (mainApp.getActiveIndex() == 0)
					vbRemoved = (VBox) getNodeById("#vbP2RemovedList");
				else
					vbRemoved = (VBox) getNodeById("#vbP1RemovedList");

				vbRemoved.getChildren().add(new ImageView(removedPiece.getImagePath()));

			}

			clearSelection();

			// Increment moves count
			mainApp.addTotalMoveCount();
			if (mainApp.getTotalMoveCount() % 2 == 0)
				mainApp.addMoveCount();

			// Swap the player turn
			if (mainApp.getActiveIndex() == 0)
				mainApp.setActiveIndex(1);
			else
				mainApp.setActiveIndex(0);

			Text txtMoveCount = (Text) getNodeById("#movecount");
			Text txtTurn = (Text) getNodeById("#currentturn");
			Text txtP1Points = (Text) getNodeById("#p1points");
			Text txtP2Points = (Text) getNodeById("#p2points");

			txtMoveCount.setText("Completed move count: " + mainApp.getMoveCount());
			txtTurn.setText("Turn: " + mainApp.getActivePlayer().getUserId() + " ("
					+ mainApp.getActivePlayer().getColor() + ")");
			txtP1Points.setText("Points earned\t\t: " + mainApp.getPlayers()[0].getPoints());
			txtP2Points.setText("Points earned\t\t: " + mainApp.getPlayers()[1].getPoints());
			
			if(mainApp.getMoveCount() == mainApp.getMaxMoveCount() || mainApp.getPlayers()[0].getPoints() == 30 || mainApp.getPlayers()[1].getPoints() == 30) {
				String winnerId; 
				String endText;
						
				if(mainApp.getPlayers()[0].getPoints() != mainApp.getPlayers()[1].getPoints()) {
					if(mainApp.getPlayers()[0].getPoints() > mainApp.getPlayers()[1].getPoints())
						winnerId = mainApp.getPlayers()[0].getUserId();
					else
						winnerId = mainApp.getPlayers()[1].getUserId();
					
					endText = winnerId + " wins the game!";
				}
				else
					endText = "The game is a tie!"; 
				
				Stage dialogBox = new Stage();
				dialogBox.initModality(Modality.APPLICATION_MODAL);
				dialogBox.setTitle("GAME OVER");
				StackPane spGameOver = new StackPane();
				VBox vbGameOver = new VBox();
				Text txtEndText = new Text(endText);

				txtEndText.setFont(Font.font ("Verdana", 15));
				txtEndText.setFill(Color.WHITE);
				ImageView imgGameOver = new ImageView("/images/game_over.jpg");
				imgGameOver.setFitHeight(200);
				imgGameOver.setFitWidth(350);
				
				Button btnExit = new Button("Exit");
				btnExit.setId("btnExit");
				
				btnExit.setOnAction(event ->{
					dialogBox.close();
					mainApp.login(mainApp.getPrimaryStage());
				});
				
				vbGameOver.getChildren().addAll(txtEndText,btnExit);
				vbGameOver.setAlignment(Pos.BASELINE_CENTER);
				
				
				spGameOver.setPrefHeight(200);
				spGameOver.setPrefWidth(350);
				
				spGameOver.getChildren().add(imgGameOver);
				spGameOver.getChildren().add(vbGameOver);
				
				Scene dialogScene = new Scene(spGameOver,350,200);
				dialogBox.setScene(dialogScene);
				dialogBox.show();
				
				
				
			}
			
			
			
			

		}
		// Check if a piece is selected for first time
		else if (board.getSquare(row, column).getPiece() != null) {
			// Check if the selected piece belongs to active player
			if (board.getSquare(row, column).getPiece().getPlayer() == mainApp.getActivePlayer()) {

				mainApp.setSelectedPiece(board.getSquare(row, column).getPiece());
				board.getSquare(row, column).getPiece().setValidMoves(board);

				boolean[][] arrValidMoves = board.getSquare(row, column).getPiece().getValidMoves();
				for (int i = 0; i < arrValidMoves.length; i++) {
					for (int j = 0; j < arrValidMoves[0].length; j++) {
						sqr = (SquareStack) mainApp.getPrimaryStage().getScene().lookup("#SQ_" + i + "_" + j);
						if (arrValidMoves[i][j] == true) {
							// Highlight the valid moves for the selected piece
							sqr.setIsValidMove(true);
							sqr.highlight();
						} else {
							// Remove all other highlights
							sqr.setIsValidMove(false);
							sqr.removeHighlight();
						}
						// Unselect all squares
						sqr.setIsSelected(false);
					}
				}

				// Select & highlight only current selection
				selectedSquare.setIsSelected(true);
				selectedSquare.highlight();

			}

		}

	}

	private void clearSelection() {
		SquareStack sqr;
		mainApp.setSelectedPiece(null);
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				sqr = (SquareStack) mainApp.getPrimaryStage().getScene().lookup("#SQ_" + i + "_" + j);
				sqr.removeHighlight();
				sqr.setIsValidMove(false);
				sqr.setIsSelected(false);
			}
		}
	}

	private Node getNodeById(String nodeId) {
		return mainApp.getPrimaryStage().getScene().lookup(nodeId);
	}

}
