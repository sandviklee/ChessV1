package Chess;

import java.io.File;

import Chess.Chessboard.Chessboard;
import Chess.Chessboard.IO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;



public class MainmenuController {

    public static Stage primaryStage;
    private Scene scene;
    private Parent root;
    public static ChessInit ChessInititalize;
    public static Chessboard chessboard;

    @FXML public TextField player1;
    @FXML public TextField player2;
    public static String player1Name;
    public static String player2Name;


    @FXML
    private void newgameClick(ActionEvent event) throws Exception {
        player1Name = "PLAYER1";
        player2Name = "PLAYER2";

        IO.blackTurn = false;
        IO.whiteTurn = true;
        
        if (!player1.getText().isBlank()) {
            player1Name = player1.getText();
        }
        
        if (!player2.getText().isBlank()) {
            player2Name = player2.getText();
        }
        
        chessboard = new Chessboard();
        ChessInititalize = new ChessInit(chessboard, false);
        ChessInititalize.ChessPlay();
        root = ChessInititalize.getRoot();
        primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @FXML
    private void loadClick(ActionEvent event) throws Exception {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Text files", "*.txt")
        );
        fileChooser.setInitialDirectory(new File("src/main/resources/Chess/SaveFiles"));
        File selectedFile = fileChooser.showOpenDialog(primaryStage);
        
        chessboard = new Chessboard(selectedFile); 
        ChessInititalize = new ChessInit(chessboard, true);
        ChessInititalize.ChessPlay();
        root = ChessInititalize.getRoot();
        primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

}
