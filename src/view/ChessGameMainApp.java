package view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.management.PersistentMBean;

import controller.SelectionController;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Bishop;
import model.Board;
import model.Constants;
import model.Knight;
import model.Piece;
import model.Player;
import model.Rook;

public class ChessGameMainApp extends Application {

	private Board chessboard = new Board();
	private int maxMoveCount;
	private int moveCount = 0;
	private int activeIndex = 0;
	private Player players[] = new Player[2];
	// private int[] points = new int[2];
	private Stage pStage;
	private Piece selectedPiece;
	private int totalMoveCount = 0;

	public Board getBoard() {
		return chessboard;
	}

	public Stage getPrimaryStage() {
		return pStage;
	}

	public int getActiveIndex() {
		return activeIndex;
	}

	public void setActiveIndex(int index) {
		this.activeIndex = index;
	}

	public Piece getSelectedPiece() {
		return selectedPiece;
	}

	public void setSelectedPiece(Piece p) {
		this.selectedPiece = p;
	}

	public void setPrimaryStage(Stage pStage) {
		this.pStage = pStage;
	}

	public void addMoveCount() {
		this.moveCount++;
	}

	public int getTotalMoveCount() {
		return this.totalMoveCount;
	}

	public void addTotalMoveCount() {
		this.totalMoveCount++;
	}

	public int getMoveCount() {
		return this.moveCount;
	}

	public int getMaxMoveCount() {
		return this.maxMoveCount;
	}

	public Player getActivePlayer() {
		return players[activeIndex];
	}

	public Player[] getPlayers() {
		return players;
	}

	@Override
	public void start(Stage primaryStage) {

		setPrimaryStage(primaryStage);
		login(primaryStage);

	}

	public void startGame(Stage primaryStage) {
//		// White
//		players[0] = player1;
//		// Black
//		players[1] = player2;

		//setPrimaryStage(primaryStage);

		//maxMoveCount = 1;

		initilaizeGame();

		// Header
		HBox hbHeader = new HBox();
		hbHeader.setAlignment(Pos.CENTER);
		hbHeader.setPrefHeight(65);

		Text txtHeader = new Text("CHESS LIKE GAME");
		txtHeader.setFont(Font.font("Verdana", 40));
		txtHeader.setFill(Color.BLUE);
		hbHeader.getChildren().add(txtHeader);

		GridPane hbFooter = new GridPane();
		Text txtMaxMovesCount = new Text("Maximum move count: " + maxMoveCount);
		txtMaxMovesCount.setId("maxmovecount");
		Text txtMovesCount = new Text("Completed move count: " + moveCount);
		txtMovesCount.setId("movecount");
		Text txtCurrentTurn = new Text(
				"Turn: " + players[activeIndex].getUserId() + " (" + players[activeIndex].getColor() + ")");
		txtCurrentTurn.setId("currentturn");
		hbFooter.add(txtCurrentTurn, 0, 0);
		hbFooter.add(txtMovesCount, 1, 0);
		hbFooter.add(txtMaxMovesCount, 2, 0);
		hbFooter.setHalignment(txtCurrentTurn, HPos.CENTER);
		hbFooter.setHalignment(txtMovesCount, HPos.CENTER);
		hbFooter.setHalignment(txtMaxMovesCount, HPos.CENTER);
		hbFooter.gridLinesVisibleProperty().set(false);
		txtCurrentTurn.setFont(Font.font("Verdana", 15));
		txtCurrentTurn.setFill(Color.BLACK);
		txtMovesCount.setFont(Font.font("Verdana", 15));
		txtMovesCount.setFill(Color.BLACK);
		txtMaxMovesCount.setFont(Font.font("Verdana", 15));
		txtMaxMovesCount.setFill(Color.BLACK);

		ColumnConstraints column1 = new ColumnConstraints(240);
		ColumnConstraints column2 = new ColumnConstraints(265);
		ColumnConstraints column3 = new ColumnConstraints(265);
		hbFooter.getColumnConstraints().addAll(column1, column2, column3);
		RowConstraints row1 = new RowConstraints(75);
		hbFooter.getRowConstraints().addAll(row1);

		// hbFooter.setPrefWidth(900);

		GridPane basePane = new GridPane();
		VBox leftPane = new VBox();
		VBox rightPane = new VBox();

		basePane.gridLinesVisibleProperty().set(true);

		Text lblP1Id = new Text("Player 1\t\t\t: " + players[0].getUserId());
		Text lblP1Points = new Text("Points earned\t\t: " + players[0].getPoints());
		lblP1Points.setId("p1points");
		Text lblP1GameCount = new Text("Games played\t: " + players[0].getGameCount());
		Text lblP1WinCount = new Text("Games won\t\t: " + players[0].getWinCount());

		VBox vbP1Removed = new VBox();
		vbP1Removed.setId("vbP1RemovedList");
		vbP1Removed.setAlignment(Pos.CENTER);

		Text lblP1RemovedHdr = new Text("Removed Player 1 Pieces");
		lblP1RemovedHdr.setStyle("-fx-font: normal bold 14px 'serif' ");

		vbP1Removed.getChildren().add(lblP1RemovedHdr);

		leftPane.getChildren().add(lblP1Id);
		leftPane.getChildren().add(lblP1Points);
		//leftPane.getChildren().add(lblP1GameCount);
		//leftPane.getChildren().add(lblP1WinCount);
		leftPane.getChildren().add(vbP1Removed);

		Text lblP2Id = new Text("Player 2\t\t\t: " + players[1].getUserId());
		Text lblP2Points = new Text("Points earned\t\t: " + players[1].getPoints());
		lblP2Points.setId("p2points");
		Text lblP2GameCount = new Text("Games played\t: " + players[1].getGameCount());
		Text lblP2WinCount = new Text("Games won\t\t: " + players[1].getWinCount());

		VBox vbP2Removed = new VBox();
		vbP2Removed.setId("vbP2RemovedList");
		vbP2Removed.setAlignment(Pos.CENTER);

		Text lblP2RemovedHdr = new Text("Removed Player 2 Pieces");
		lblP2RemovedHdr.setStyle("-fx-font: normal bold 14px 'serif' ");

		vbP2Removed.getChildren().add(lblP2RemovedHdr);

		rightPane.getChildren().add(lblP2Id);
		rightPane.getChildren().add(lblP2Points);
		//rightPane.getChildren().add(lblP2GameCount);
		//rightPane.getChildren().add(lblP2WinCount);
		rightPane.getChildren().add(vbP2Removed);

		leftPane.setPadding(new Insets(10, 20, 10, 15));
		rightPane.setPadding(new Insets(10, 15, 10, 20));

		leftPane.setStyle("-fx-font: normal bold 12px 'serif' ");
		rightPane.setStyle("-fx-font: normal bold 12px 'serif' ");

		// Chess board implementation
		GridPane board = new GridPane();
		board.setId("chessboard");
		final int size = 6;
		draw(board);

		for (int i = 0; i < size; i++) {
			board.getColumnConstraints().add(new ColumnConstraints(75, Control.USE_COMPUTED_SIZE,
					Double.POSITIVE_INFINITY, Priority.ALWAYS, HPos.CENTER, true));
			board.getRowConstraints().add(new RowConstraints(75, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY,
					Priority.ALWAYS, VPos.CENTER, true));
		}

		// combine panes
		basePane.add(leftPane, 0, 0);
		basePane.add(board, 1, 0);
		basePane.add(rightPane, 2, 0);
		basePane.setAlignment(Pos.CENTER);

		basePane.setPrefHeight(500);

		VBox vbOuter = new VBox();
		vbOuter.setStyle("-fx-background-color: lightgray;-fx-border-color: black;");
		vbOuter.getChildren().addAll(hbHeader, basePane, hbFooter);

		primaryStage.setTitle("Chess Like Game Client");
		primaryStage.setScene(new Scene(vbOuter, 820, 650));
		primaryStage.show();

	}

	public void handleHighlights(SquareStack square) {
		square.setOnMouseEntered(e -> {
			if (!square.getIsSelected() && !square.getIsValidMove())
				square.setColor("LIGHTBLUE");
		});

		square.setOnMouseExited(e -> {
			if (!square.getIsSelected() && !square.getIsValidMove()) {
				if ((square.getRow() + square.getColumn()) % 2 == 0)
					square.setColor("WHITE");
				else
					square.setColor("DIMGRAY");
			}
		});

	}

	public void initilaizeGame() {
		// White pieces assigned to bottom of board
		Piece rookLeft = new Rook("r1", Constants.IMG_WHITEROOK);
		Piece bishopLeft = new Bishop("b1", Constants.IMG_WHITEBISHOP);
		Piece knightLeft = new Knight("k1", Constants.IMG_WHITEKNIGHT);
		Piece knightRight = new Knight("k2", Constants.IMG_WHITEKNIGHT);
		Piece bishopRight = new Bishop("b2", Constants.IMG_WHITEBISHOP);
		Piece rookRight = new Rook("r2", Constants.IMG_WHITEROOK);

		chessboard.getSquare(5, 0).occupySquare(rookLeft);
		chessboard.getSquare(5, 1).occupySquare(bishopLeft);
		chessboard.getSquare(5, 2).occupySquare(knightLeft);
		chessboard.getSquare(5, 3).occupySquare(knightRight);
		chessboard.getSquare(5, 4).occupySquare(bishopRight);
		chessboard.getSquare(5, 5).occupySquare(rookRight);

		players[0].addPiece(rookLeft);
		players[0].addPiece(bishopLeft);
		players[0].addPiece(knightLeft);
		players[0].addPiece(knightRight);
		players[0].addPiece(bishopRight);
		players[0].addPiece(rookRight);

		// Black pieces assigned to top of board
		rookLeft = new Rook("R1", Constants.IMG_BLACKROOK);
		bishopLeft = new Bishop("B1", Constants.IMG_BLACKBISHOP);
		knightLeft = new Knight("K1", Constants.IMG_BLACKKNIGHT);
		knightRight = new Knight("K2", Constants.IMG_BLACKKNIGHT);
		bishopRight = new Bishop("B2", Constants.IMG_BLACKBISHOP);
		rookRight = new Rook("R2", Constants.IMG_BLACKROOK);

		chessboard.getSquare(0, 0).occupySquare(rookLeft);
		chessboard.getSquare(0, 1).occupySquare(bishopLeft);
		chessboard.getSquare(0, 2).occupySquare(knightLeft);
		chessboard.getSquare(0, 3).occupySquare(knightRight);
		chessboard.getSquare(0, 4).occupySquare(bishopRight);
		chessboard.getSquare(0, 5).occupySquare(rookRight);

		players[1].addPiece(rookLeft);
		players[1].addPiece(bishopLeft);
		players[1].addPiece(knightLeft);
		players[1].addPiece(knightRight);
		players[1].addPiece(bishopRight);
		players[1].addPiece(rookRight);

		// Set player colors
		players[0].setColor(Constants.COLOR_WHITE);
		players[1].setColor(Constants.COLOR_BLACK);

		// Set valid moves for all initialized pieces
		setAllValidMoves(chessboard);
	}

	private void setAllValidMoves(Board b) {
		for (Player player : players) {
			for (Piece piece : player.getPieceList()) {
				piece.setValidMoves(b);
			}
		}
	}

	public void draw(GridPane board) {
		for (int row = 0; row < 6; row++) {
			for (int col = 0; col < 6; col++) {
				SquareStack square;
				if ((row + col) % 2 == 0)
					square = new SquareStack(row, col, "WHITE");
				else
					square = new SquareStack(row, col, "DIMGRAY");

				if (chessboard.getSquare(row, col).getPiece() != null)
					square.setPiece(chessboard.getSquare(row, col).getPiece().getImagePath());

				board.add(square, col, row);
				handleHighlights(square);

				square.setOnMouseClicked(new SelectionController(this));
			}
		}
	}

	public void login(Stage primaryStage) {
		boolean loginStatus[] = new boolean[2];
		
		Label lblMainHeader = new Label("CHESS LIKE GAME");

		Label lblHeader1 = new Label("Login as player 1");

		// creating label email
		Label lblUserId1 = new Label("User ID");

		// creating label password
		Label lblPassword1 = new Label("Password");

		// Creating Text Filed for email
		TextField txtUserId1 = new TextField();

		// Creating Text Filed for password
		PasswordField txtPassword1 = new PasswordField();

		Label lblHeader2 = new Label("Login as player 2");

		// creating label email
		Label lblUserId2 = new Label("User ID");

		// creating label password
		Label lblPassword2 = new Label("Password");

		// Creating Text Filed for email
		TextField txtUserId2 = new TextField();

		// Creating Text Filed for password
		PasswordField txtPassword2 = new PasswordField();
		
		Label lblP1MaxMoves = new Label("Maximum moves");
		
		TextField txtP1MaxMoves = new TextField();
		
		Label lblP2MaxMoves = new Label("Maximum moves");
		
		TextField txtP2MaxMoves = new TextField();

		// Creating Buttons
		Button btnLogin1 = new Button("Login");
		Button btnClear1 = new Button("Clear");

		Button btnLogin2 = new Button("Login");
		Button btnClear2 = new Button("Clear");

		Button btnSignup = new Button("Signup");

		Button btnStartGame = new Button("START GAME");
		btnStartGame.setId("btnStartGame");
		btnStartGame.setDisable(true);

		btnStartGame.setOnAction(eStart -> {
			players[0] = new Player(txtUserId1.getText(), Constants.COLOR_WHITE);
			// Black
			players[1] = new Player(txtUserId2.getText(), Constants.COLOR_BLACK);
			
			maxMoveCount = (Integer.parseInt(txtP1MaxMoves.getText()) + Integer.parseInt(txtP2MaxMoves.getText()))/2;
			
			startGame(primaryStage);
		});

		// Login Button 1
		btnLogin1.setOnAction(e -> {
			final String url = "jdbc:sqlite:database/details.db";
			PreparedStatement ps = null;
			ResultSet result = null;
			try {
				Connection con = DriverManager.getConnection(url);
				Statement stmnt = con.createStatement();
				ps = con.prepareStatement("SELECT * FROM logindetails WHERE userid =? AND password =?");
				ps.setString(1, txtUserId1.getText());
				ps.setString(2, txtPassword1.getText());
				result = ps.executeQuery();
				if (result.next()) {
					lblHeader1.setText("Success");
					loginStatus[0] = true;
				} else {
					lblHeader1.setText("Invalid Credentials");
				}
			} catch (Exception exception) {
				System.out.println("" + exception.getMessage());
			}
			
			if(loginStatus[0] && loginStatus[1]) {
				if(!txtP1MaxMoves.getText().isEmpty() && !txtP1MaxMoves.getText().isEmpty())
					btnStartGame.setDisable(false);
			}

		});

		// Clear button 1
		btnClear1.setOnAction(e -> {
			txtUserId1.setText("");
			txtPassword1.setText("");
			;
		});

		// Login Button 2
		btnLogin2.setOnAction(e -> {
			final String url = "jdbc:sqlite:database/details.db";
			PreparedStatement ps = null;
			ResultSet result = null;
			try {
				Connection con = DriverManager.getConnection(url);

				ps = con.prepareStatement("SELECT * FROM logindetails WHERE userid =? AND password =?");
				ps.setString(1, txtUserId2.getText());
				ps.setString(2, txtPassword2.getText());
				result = ps.executeQuery();
				if (result.next()) {
					lblHeader2.setText("Success");
					loginStatus[1] = true;
				} else {
					lblHeader2.setText("Invalid Credentials");
				}
				//
			} catch (Exception exception) {
				System.out.println("" + exception.getMessage());
			}
			
			if(loginStatus[0] && loginStatus[1]) {
				btnStartGame.setDisable(false);
			}

		});

		// Clear button 2
		btnClear2.setOnAction(e -> {
			txtUserId2.setText("");
			txtPassword2.setText("");
		});

		// Sign Up Button
		btnSignup.setOnAction(e -> {

			Label lblSignUp = new Label("Sign Up");
			Label lblUserId = new Label("User ID");
			Stage s1 = new Stage();
			// creating label password
			Label lblPassword = new Label("Password");

			// Creating Text Filed for email
			TextField txtUserId = new TextField();

			// Creating Text Filed for password
			PasswordField txtPassword = new PasswordField();

			Button btnSignUp1 = new Button("SignUp");
			Button btnClear = new Button("Clear");

			// Creating a Grid Pane
			GridPane gridPane = new GridPane();

			// Setting size for the pane
			gridPane.setMinSize(400, 200);

			// Setting the padding
			gridPane.setPadding(new Insets(10, 10, 10, 10));

			// Setting the vertical and horizontal gaps between the columns
			gridPane.setVgap(5);
			gridPane.setHgap(5);

			// Setting the Grid alignment
			gridPane.setAlignment(Pos.CENTER);

			// Arranging all the nodes in the grid

			gridPane.add(lblSignUp, 0, 2, 2, 1);
			gridPane.add(lblUserId, 0, 3);
			gridPane.add(txtUserId, 1, 3);
			gridPane.add(lblPassword, 0, 4);
			gridPane.add(txtPassword, 1, 4);

			gridPane.add(btnSignUp1, 0, 5);
			gridPane.add(btnClear, 1, 5);

			// Creating a scene object
			Scene scene = new Scene(gridPane);

			// Setting title to the Stage
			s1.setTitle("Sign Up");

			// Adding scene to the stage
			s1.setScene(scene);

			// Displaying the contents of the stage
			s1.show();

			btnClear.setOnAction(event -> {
				txtUserId.setText("");
				txtPassword.setText("");
			});

			// Sign Up
			btnSignUp1.setOnAction(event -> {
				String userId = txtUserId.getText();
				String password = txtPassword.getText();
				PreparedStatement ps = null;
				ResultSet result = null;
				final String url = "jdbc:sqlite:database/details.db";
				try {
					Connection con = DriverManager.getConnection(url);
					Statement stmnt = con.createStatement();
					// System.out.println("user:"+userId+"password:"+password);
					// stmnt.execute("create table logindetails(userid text,password text)");
					
					ps = con.prepareStatement("SELECT * FROM logindetails WHERE userid =?");
					ps.setString(1, txtUserId.getText());
					result = ps.executeQuery();
					if (result.next()) {
						lblSignUp.setText("User Id already exists");
					}
					else {
					stmnt.execute("insert into logindetails(userid,password)" + "values('" + userId + "','" + password + "')");
					lblSignUp.setText("Success");
					}
					
					result.close();
					con.close();
					
					//s1.close();

				} catch (Exception exception) {
					System.out.println("" + exception.getMessage());
				}
			});

		});

		// Creating a Grid Pane
		GridPane gridPane = new GridPane();

		// Setting size for the pane
		gridPane.setMinSize(400, 200);

		// Setting the padding
		gridPane.setPadding(new Insets(10, 10, 10, 10));

		// Setting the vertical and horizontal gaps between the columns
		gridPane.setVgap(5);
		gridPane.setHgap(5);

		// Setting the Grid alignment
		gridPane.setAlignment(Pos.CENTER);

		// Arranging all the nodes in the grid

		gridPane.add(lblMainHeader, 0, 0, 5, 2);

		gridPane.add(lblHeader1, 0, 2, 2, 1);
		gridPane.add(lblUserId1, 0, 3);
		gridPane.add(txtUserId1, 1, 3);
		gridPane.add(lblPassword1, 0, 4);
		gridPane.add(txtPassword1, 1, 4);

		gridPane.add(lblHeader2, 3, 2, 2, 1);
		gridPane.add(lblUserId2, 3, 3);
		gridPane.add(txtUserId2, 4, 3);
		gridPane.add(lblPassword2, 3, 4);
		gridPane.add(txtPassword2, 4, 4);
		
		gridPane.add(lblP1MaxMoves, 0, 5);
		gridPane.add(txtP1MaxMoves, 1, 5);
		gridPane.add(lblP2MaxMoves, 3, 5);
		gridPane.add(txtP2MaxMoves, 4, 5);
		

		gridPane.add(btnLogin1, 0, 6);
		gridPane.add(btnClear1, 1, 6);

		gridPane.add(btnLogin2, 3, 6);
		gridPane.add(btnClear2, 4, 6);

		gridPane.add(btnSignup, 2, 8);
		gridPane.add(btnStartGame, 2, 9);

		gridPane.setHalignment(lblMainHeader, HPos.CENTER);
		gridPane.setHalignment(btnSignup, HPos.CENTER);
		gridPane.setHalignment(btnStartGame, HPos.CENTER);

		// Creating a scene object
		Scene loginScene = new Scene(gridPane);

		// Setting title to the Stage
		primaryStage.setTitle("Chess Like game Login");
		primaryStage.setScene(loginScene);
		primaryStage.show();
	}
	

	public static void main(String[] args) {
		launch(args);
	}
}